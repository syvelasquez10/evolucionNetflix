// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.netflix.mediaclient.service.player.bif.OfflineBifManager$1;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.RandomAccessFile;
import com.netflix.mediaclient.service.player.bif.BasicBifManager;
import com.netflix.mediaclient.media.PlayoutMetadata;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Logblob;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;
import com.netflix.mediaclient.service.player.exoplayback.logblob.StateChanged;
import com.netflix.mediaclient.service.player.exoplayback.logblob.StartPlay;
import com.netflix.mediaclient.service.player.exoplayback.logblob.ResumePlay;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.logblob.BaseLogblob;
import com.netflix.mediaclient.service.player.exoplayback.logblob.EndPlay;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.manifest.Stream;
import org.json.JSONObject;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.Time;
import com.netflix.mediaclient.util.NetflixTransactionIdGenerator;
import com.netflix.mediaclient.service.pdslogging.PdsPlayInterface;
import com.netflix.mediaclient.media.manifest.VideoTrack;
import java.util.List;
import android.view.Surface;
import com.netflix.mediaclient.service.offline.subtitles.SubtitleOfflineManager;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.pdslogging.PdsPlaySessionInterface;
import com.netflix.mediaclient.service.player.bif.OfflineBifManager;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.media.Subtitle;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;
import android.util.Pair;
import com.netflix.mediaclient.util.activitytracking.ActivityTracker;

public class OfflinePlaybackSession implements IPlaybackSession, IPlayerListener
{
    private static final long CALLBACK_INTERVAL_MS = 1000L;
    private static final String TAG = "OfflinePlayback_Session";
    private long mABitrate;
    private ActivityTracker mActivityTracker;
    private String mAdlid;
    private Pair<Integer, Integer> mAspectRatio;
    private AudioSource[] mAudioSource;
    private AudioSubtitleDefaultOrderInfo[] mAudioSubtitleDefaultOrderInfo;
    private BatteryStats mBatteryStats;
    private final long mBookmark;
    private final IPlaybackSession$PlaybackSessionCallback mCallback;
    private ServiceAgent$ConfigurationAgentInterface mConfigAgent;
    private final Context mContext;
    private Subtitle mCurrentSubtitleTrack;
    private String mDxid;
    private boolean mEndPlayLogged;
    private boolean mErrorLogged;
    private final IClientLogging mLoggingAgent;
    private final Handler mMainHandler;
    private final long mMovieId;
    private final OfflinePlaybackInterface mOfflineAgent;
    private OfflineBifManager mOfflineBifManger;
    private PlayerWithStaticMPD mOfflinePlayer;
    private String mOxid;
    private PdsPlaySessionInterface mPdsPlaySession;
    private final Runnable mPeriodicCallback;
    private PlayContext mPlayContext;
    private PlayTimeTracker mPlayTracker;
    private long mPlaybackTS;
    private PowerStatusReceiver mPowerStatusRcvr;
    private Pair<Integer, Integer> mReportedAspectRatio;
    private OfflinePlaybackSession$ResumePlayReason mResumePlayReason;
    private boolean mStartPlayLogged;
    private Subtitle[] mSubtitle;
    private String mSubtitledlid;
    private final SubtitleOfflineManager mSubtitles;
    private Surface mSurface;
    private long mUserPlay;
    private long mVBitrate;
    private String mVdlid;
    private List<VideoTrack> mVideoTracks;
    private String mXid;
    
    OfflinePlaybackSession(final Context mContext, final Handler mMainHandler, final IPlaybackSession$PlaybackSessionCallback mCallback, final ServiceAgent$ConfigurationAgentInterface mConfigAgent, final OfflinePlaybackInterface mOfflineAgent, final IClientLogging mLoggingAgent, final PdsPlayInterface pdsPlayInterface, final SubtitleOfflineManager mSubtitles, final long mMovieId, final long mBookmark, final PlayContext mPlayContext) {
        this.mResumePlayReason = OfflinePlaybackSession$ResumePlayReason.none;
        this.mPlaybackTS = 0L;
        this.mPeriodicCallback = new OfflinePlaybackSession$2(this);
        this.mContext = mContext;
        this.mMainHandler = mMainHandler;
        this.mCallback = mCallback;
        this.mConfigAgent = mConfigAgent;
        this.mOfflineAgent = mOfflineAgent;
        this.mLoggingAgent = mLoggingAgent;
        this.mXid = NetflixTransactionIdGenerator.generateXid();
        this.mMovieId = mMovieId;
        this.mBookmark = mBookmark;
        this.mSubtitles = mSubtitles;
        this.mUserPlay = Time.mono();
        this.mPlayContext = mPlayContext;
        this.requestOfflineManifest(mOfflineAgent, mMovieId);
        this.mPdsPlaySession = pdsPlayInterface.createPdsPlaySession(Long.toString(mMovieId), this.getSessionId(), this.mBookmark, mPlayContext);
        this.mBatteryStats = BatteryStats.createBatteryStats(this.mContext);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        mContext.registerReceiver((BroadcastReceiver)(this.mPowerStatusRcvr = new PowerStatusReceiver(this.mBatteryStats)), intentFilter);
    }
    
    private String getActivityData() {
        String string = "";
        if (this.mActivityTracker != null) {
            this.mActivityTracker.stopTrackingActivityUpdates();
            string = this.mActivityTracker.getActivityData().toString();
            this.mActivityTracker = null;
        }
        return string;
    }
    
    private JSONObject getPlayTimeJson() {
        if (this.mPlayTracker != null) {
            return this.mPlayTracker.getPlayTimeJson();
        }
        return PlayTimeTracker.getDefaultTimeJson();
    }
    
    private void handleManifest(final OfflinePlaybackInterface$OfflineManifest offlinePlaybackInterface$OfflineManifest) {
        this.mAudioSubtitleDefaultOrderInfo = offlinePlaybackInterface$OfflineManifest.getAudioSubtitleDefaultOrderInfo();
        this.mSubtitle = offlinePlaybackInterface$OfflineManifest.getSubtitleTrackList();
        this.mAudioSource = offlinePlaybackInterface$OfflineManifest.getAudioTrackList();
        this.mPdsPlaySession.onManifest(offlinePlaybackInterface$OfflineManifest);
        this.mOxid = offlinePlaybackInterface$OfflineManifest.getOxId();
        this.mDxid = offlinePlaybackInterface$OfflineManifest.getDxId();
        this.mVideoTracks = offlinePlaybackInterface$OfflineManifest.getVideoTrackList();
        final List<Stream> streams = this.mVideoTracks.get(0).streams;
        this.mVBitrate = streams.get(0).bitrate;
        this.mVdlid = streams.get(0).downloadable_id;
        this.mAspectRatio = offlinePlaybackInterface$OfflineManifest.getAspectWidthHeight();
        final byte[] offlineKeySetId = offlinePlaybackInterface$OfflineManifest.getOfflineKeySetId();
        while (true) {
            Label_0397: {
                if (offlineKeySetId == null || offlineKeySetId.length <= 0) {
                    break Label_0397;
                }
                Log.logByteArrayRaw("OfflinePlayback_Session", "has KeySetId", offlineKeySetId);
                try {
                    final OfflineDrmSession offlineDrmSession = new OfflineDrmSession(offlineKeySetId);
                    final String bifFile = offlinePlaybackInterface$OfflineManifest.getBifFile();
                    if (Log.isLoggable()) {
                        Log.d("OfflinePlayback_Session", "onManifestResponse BitUrl " + bifFile);
                    }
                    if (bifFile != null) {
                        this.mOfflineBifManger = new OfflineBifManager(offlinePlaybackInterface$OfflineManifest.getBifFile());
                    }
                    this.mOfflinePlayer = new PlayerWithStaticMPD(this.mContext, this.mMainHandler, this, offlinePlaybackInterface$OfflineManifest.getMpd(), offlineDrmSession, this.mBookmark);
                    this.mPlaybackTS = this.mBookmark;
                    if (this.mSurface != null) {
                        this.mOfflinePlayer.setSurface(this.mSurface);
                    }
                    this.mPlayTracker = new PlayTimeTracker();
                    return;
                }
                catch (Throwable t) {
                    if (Log.isLoggable()) {
                        Log.d("OfflinePlayback_Session", "fail to create OfflineDrmSession " + t);
                    }
                    this.reportStartPlay(OfflinePlaybackState.MANIFEST_PROCESSING.toString(), "OfflinePlayback.DrmSessionRestoreFailed", t.getMessage());
                    this.mPdsPlaySession.stop(this.getPlayTimeJson(), OfflinePlaybackState.MANIFEST_PROCESSING.toString(), "OfflinePlayback.DrmSessionRestoreFailed");
                    this.mCallback.handleError(new ExoPlaybackError(ExoPlaybackError$ExoPlaybackErrorCode.SESSION_INIT_ERROR, "OfflineDrmSession failed", OfflinePlaybackState.MANIFEST_PROCESSING.toString(), null));
                    return;
                }
            }
            Log.d("OfflinePlayback_Session", "invalid offline KeySetId, assume it is clear content");
            final OfflineDrmSession offlineDrmSession = null;
            continue;
        }
    }
    
    private void handleSubtitleUpdate(final int n) {
        while (true) {
            Label_0079: {
                synchronized (this) {
                    if (Log.isLoggable()) {
                        Log.d("OfflinePlayback_Session", "Update PTS received " + n);
                    }
                    if (this.getCurrentSubtitleTrack() == null) {
                        Log.d("OfflinePlayback_Session", "Subtitles are not visible, do not send any update");
                    }
                    else {
                        if (this.mSubtitles != null) {
                            break Label_0079;
                        }
                        Log.d("OfflinePlayback_Session", "Subtitle manager is not available.");
                    }
                    return;
                }
            }
            final SubtitleOfflineManager subtitleOfflineManager;
            if (subtitleOfflineManager.getSubtitleParser() == null) {
                Log.d("OfflinePlayback_Session", "Subtitle data is not available.");
                return;
            }
            if (!subtitleOfflineManager.getSubtitleParser().isReady()) {
                Log.d("OfflinePlayback_Session", "Subtitle data is not ready yet!");
                return;
            }
            if (!this.isPlaying()) {
                Log.d("OfflinePlayback_Session", "Not playing, do NOT send subtitle screen update");
                return;
            }
            this.mCallback.handleSubtitleUpdate(subtitleOfflineManager.getSubtitleParser().getSubtitlesForPosition(n));
        }
    }
    
    private Pair<Integer, Integer> reportAspectRatio(final Pair<Integer, Integer> pair, final Pair<Integer, Integer> pair2) {
        final int intValue = (int)pair.first;
        final int intValue2 = (int)pair.second;
        int n = (int)pair2.second * (int)pair2.first;
        int n2 = intValue2;
        int n3 = intValue;
        if (n <= 0) {
            n = 345600;
            n3 = intValue;
            n2 = intValue2;
        }
        while (n3 * n2 > n) {
            n3 /= 2;
            n2 /= 2;
        }
        return (Pair<Integer, Integer>)Pair.create((Object)n3, (Object)n2);
    }
    
    private void reportEndPlay(final OfflinePlaybackSession$EndPlayReason offlinePlaybackSession$EndPlayReason, final String s, final String s2, final String s3, final String s4) {
        Log.d("OfflinePlayback_Session", "reportEndPlay: ");
        if (this.mEndPlayLogged || this.mErrorLogged) {
            Log.d("OfflinePlayback_Session", "reportEndPlay: Already logged or error reported");
            return;
        }
        this.mBatteryStats.updateBatteryStat(false);
        final long mono = Time.mono();
        final long mUserPlay = this.mUserPlay;
        final long currentPosition = this.getCurrentPosition();
        final boolean connectedOrConnecting = ConnectivityUtils.isConnectedOrConnecting(this.mContext);
        LogArguments$LogLevel logArguments$LogLevel = LogArguments$LogLevel.INFO;
        if (offlinePlaybackSession$EndPlayReason == OfflinePlaybackSession$EndPlayReason.error) {
            logArguments$LogLevel = LogArguments$LogLevel.ERROR;
            this.mErrorLogged = true;
        }
        JSONObject playbackStatJSON = null;
        if (this.mOfflinePlayer != null) {
            playbackStatJSON = this.mOfflinePlayer.getPlaybackStatJSON();
        }
        Log.d("OfflinePlayback_Session", "reportEndPlay: BatteryStat:" + this.mBatteryStats.getJSON());
        Log.d("OfflinePlayback_Session", "reportEndPlay: PlayTimeTracker" + this.mPlayTracker);
        try {
            this.sendBlob(new EndPlay(logArguments$LogLevel, this.mMovieId, this.mPlayContext.getTrackId(), this.mXid, this.mOxid, this.mDxid, mono - mUserPlay, currentPosition, this.mPlayTracker.getMovieTotalInMs() / 1000L, offlinePlaybackSession$EndPlayReason.name(), connectedOrConnecting, playbackStatJSON, this.mBatteryStats.getJSON(), s, s2, s3, s4));
            this.mEndPlayLogged = true;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    private void reportResumePlay(final OfflinePlaybackSession$ResumePlayReason offlinePlaybackSession$ResumePlayReason) {
        try {
            this.sendBlob(new ResumePlay(this.mMovieId, this.getCurrentPosition(), Time.mono() - this.mUserPlay, this.mXid, this.mOxid, this.mDxid, offlinePlaybackSession$ResumePlayReason.name(), this.mVdlid, this.mVBitrate, this.mAdlid, this.mABitrate));
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    private void reportStartPlay(final String s, final String s2, final String s3) {
        final long n = Time.mono() - this.mUserPlay;
        final boolean connectedOrConnecting = ConnectivityUtils.isConnectedOrConnecting(this.mContext);
        LogArguments$LogLevel logArguments$LogLevel = LogArguments$LogLevel.INFO;
        if (s != null) {
            logArguments$LogLevel = LogArguments$LogLevel.ERROR;
        }
        this.mBatteryStats.updateBatteryStat(true);
        while (true) {
            try {
                this.sendBlob(new StartPlay(logArguments$LogLevel, this.mMovieId, this.mPlayContext.getTrackId(), this.mXid, this.mOxid, this.mDxid, n, this.mBookmark, this.mVdlid, this.mVBitrate, this.mAdlid, this.mABitrate, n, connectedOrConnecting, s, s2, s3));
                if (s != null) {
                    this.mErrorLogged = true;
                }
                else if (ActivityTracker.canUseActivityTracker(this.mConfigAgent, this.mContext)) {
                    this.mActivityTracker = new ActivityTracker(this.mContext);
                }
                this.mStartPlayLogged = true;
                return;
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            continue;
        }
    }
    
    private void reportStateChange(final String s, final String s2) {
        try {
            this.sendBlob(new StateChanged(this.mXid, this.mOxid, this.mDxid, Time.mono() - this.mUserPlay, this.getCurrentPosition(), s, s2));
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    private void requestOfflineManifest(final OfflinePlaybackInterface offlinePlaybackInterface, final long n) {
        Log.d("OfflinePlayback_Session", "requestOfflineManifest movieId=" + n);
        offlinePlaybackInterface.requestOfflineManifest(n, new OfflinePlaybackSession$1(this));
    }
    
    private void sendBlob(final BaseLogblob baseLogblob) {
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Session", "sendBlob: " + baseLogblob.toJsonString() + " " + baseLogblob.getType());
        }
        this.mLoggingAgent.getLogblobLogging().sendLogblob(baseLogblob);
    }
    
    private void updateCurrentAudioTrackData() {
        final String audioCurrentTrackId = this.mOfflinePlayer.getAudioCurrentTrackId();
        if (audioCurrentTrackId != null) {
            final AudioSource[] mAudioSource = this.mAudioSource;
            for (int length = mAudioSource.length, i = 0; i < length; ++i) {
                final AudioSource audioSource = mAudioSource[i];
                if (StringUtils.notEmptyAndEquals(audioSource.getId(), audioCurrentTrackId)) {
                    this.mAdlid = audioSource.getStreams().get(0).downloadable_id;
                    this.mABitrate = audioSource.getStreams().get(0).bitrate;
                }
            }
        }
        this.mPlayTracker.updateCurrrentPlayDlids(this.mVdlid, this.mAdlid, this.mSubtitledlid);
    }
    
    @Override
    public AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo() {
        return this.mAudioSubtitleDefaultOrderInfo;
    }
    
    @Override
    public AudioSource[] getAudioTrackList() {
        return this.mAudioSource;
    }
    
    @Override
    public ByteBuffer getBifFrame(final int n) {
        if (this.mOfflineBifManger != null) {
            return this.mOfflineBifManger.getIndexFrame(n);
        }
        return null;
    }
    
    @Override
    public int getBufferedPercentage() {
        if (this.mOfflinePlayer != null) {
            return this.mOfflinePlayer.getBufferedPercentage();
        }
        return 0;
    }
    
    @Override
    public long getBufferedPosition() {
        if (this.mOfflinePlayer != null) {
            return this.mOfflinePlayer.getBufferedPosition();
        }
        return 0L;
    }
    
    @Override
    public AudioSource getCurrentAudioTrack() {
        if (this.mOfflinePlayer != null) {
            this.mOfflinePlayer.getAudioCurrentTrackId();
        }
        return null;
    }
    
    @Override
    public long getCurrentPlayableId() {
        return this.mMovieId;
    }
    
    @Override
    public long getCurrentPosition() {
        if (this.mOfflinePlayer != null) {
            return this.mOfflinePlayer.getCurrentPosition();
        }
        return 0L;
    }
    
    @Override
    public Subtitle getCurrentSubtitleTrack() {
        return this.mCurrentSubtitleTrack;
    }
    
    @Override
    public long getDuration() {
        if (this.mOfflinePlayer != null) {
            return this.mOfflinePlayer.getDuration();
        }
        return 0L;
    }
    
    @Override
    public PlayoutMetadata getPlayoutMetadata() {
        if (this.mOfflinePlayer != null) {
            return new PlayoutMetadata((int)this.mVBitrate, this.mOfflinePlayer.getVideoResolution(), this.mOfflinePlayer.getVideoDecoderName());
        }
        return null;
    }
    
    @Override
    public String getSessionId() {
        return this.mXid;
    }
    
    @Override
    public Subtitle[] getSubtitleTrackList() {
        return this.mSubtitle;
    }
    
    @Override
    public Pair<Integer, Integer> getWidthHeight() {
        if (this.mReportedAspectRatio == null) {
            this.mReportedAspectRatio = this.reportAspectRatio(this.mAspectRatio, this.mOfflinePlayer.getVideoResolution());
        }
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Session", "AspectRatio convert from " + this.mAspectRatio.first + " X " + this.mAspectRatio.second + " to " + this.mReportedAspectRatio.first + " X " + this.mReportedAspectRatio.second);
        }
        return this.mReportedAspectRatio;
    }
    
    @Override
    public boolean isPlaying() {
        return this.mOfflinePlayer != null && this.mOfflinePlayer.isPlaying();
    }
    
    @Override
    public void pause() {
        if (this.mOfflinePlayer != null) {
            Log.d("OfflinePlayback_Session", "pause: ");
            this.mOfflinePlayer.pause();
            this.mOfflineAgent.notifyPause(this.mMovieId);
            this.mPdsPlaySession.pause();
        }
    }
    
    @Override
    public void play() {
        if (this.mOfflinePlayer != null) {
            Log.d("OfflinePlayback_Session", "play: ");
            this.mOfflinePlayer.play();
            this.mPdsPlaySession.play();
        }
    }
    
    @Override
    public void playerBuffering() {
        this.play();
    }
    
    @Override
    public void playerError(final ExoPlaybackError exoPlaybackError) {
        if (this.mStartPlayLogged) {
            this.reportEndPlay(OfflinePlaybackSession$EndPlayReason.error, exoPlaybackError.getUiDisplayErrorCode(), "OfflinePlayback.PlaybackFailed", exoPlaybackError.getExceptionStack(), this.getActivityData());
            this.mPdsPlaySession.stop(this.getPlayTimeJson(), exoPlaybackError.getUiDisplayErrorCode(), "OfflinePlayback.PlaybackFailed");
        }
        else {
            this.reportStartPlay(OfflinePlaybackState.PLAYBACK_INIT.toString(), "OfflinePlayback.PlaybackFailed", exoPlaybackError.getExceptionStack());
        }
        this.mCallback.handleError(exoPlaybackError);
        this.mOfflineAgent.notifyPlayError(this.mMovieId);
    }
    
    @Override
    public void playerPaused() {
        this.reportStateChange("Playing", "Paused");
    }
    
    @Override
    public void playerPrepared() {
        this.updateCurrentAudioTrackData();
        this.mCallback.handlePrepared();
    }
    
    @Override
    public void playerStarted() {
        if (!this.mStartPlayLogged) {
            this.reportStartPlay(null, null, null);
        }
        else {
            if (this.mResumePlayReason == OfflinePlaybackSession$ResumePlayReason.none) {
                this.reportStateChange("Paused", "Playing");
            }
            else {
                this.reportResumePlay(this.mResumePlayReason);
            }
            this.mResumePlayReason = OfflinePlaybackSession$ResumePlayReason.none;
        }
        this.mOfflineAgent.notifyPlay(this.mMovieId);
        this.mCallback.handleStarted();
        this.mCallback.handlePlaying();
        this.mMainHandler.post(this.mPeriodicCallback);
    }
    
    @Override
    public void playerStopped() {
        this.reportEndPlay(OfflinePlaybackSession$EndPlayReason.ended, null, null, null, this.getActivityData());
        this.mPdsPlaySession.stop(this.getPlayTimeJson(), null, null);
        this.mOfflineAgent.notifyStop(this.mMovieId);
        this.mCallback.handleStopped();
    }
    
    @Override
    public void release() {
        if (this.mOfflinePlayer != null) {
            this.mOfflinePlayer.release();
        }
        else {
            this.mOfflineAgent.abortManifestRequest(this.mMovieId);
        }
        if (this.mOfflineBifManger != null) {
            this.mOfflineBifManger.release();
            this.mOfflineBifManger = null;
        }
        if (this.mSubtitles != null) {
            this.mSubtitles.close();
        }
        this.mContext.unregisterReceiver((BroadcastReceiver)this.mPowerStatusRcvr);
    }
    
    @Override
    public void seekTo(final long mPlaybackTS) {
        if (this.mOfflinePlayer != null) {
            Log.d("OfflinePlayback_Session", "seekTo: ");
            if (this.mResumePlayReason == OfflinePlaybackSession$ResumePlayReason.none) {
                this.mResumePlayReason = OfflinePlaybackSession$ResumePlayReason.repos;
            }
            this.mPlaybackTS = mPlaybackTS;
            this.mOfflinePlayer.seekTo(mPlaybackTS);
            this.mPdsPlaySession.seekTo(mPlaybackTS);
            if (this.mSubtitles != null && this.mSubtitles.getSubtitleParser() != null) {
                this.mSubtitles.getSubtitleParser().seeked((int)mPlaybackTS);
            }
        }
    }
    
    @Override
    public boolean selectAudioTrack(final AudioSource audioSource) {
        if (this.mOfflinePlayer != null) {
            final boolean selectAudioTrack = this.mOfflinePlayer.selectAudioTrack(audioSource.getId());
            if (selectAudioTrack) {
                this.updateCurrentAudioTrackData();
            }
            return selectAudioTrack;
        }
        return false;
    }
    
    @Override
    public boolean selectTracks(final AudioSource audioSource, final Subtitle mCurrentSubtitleTrack, final boolean b) {
        final boolean b2 = false;
        if (this.mOfflinePlayer == null) {
            return false;
        }
        this.mCurrentSubtitleTrack = mCurrentSubtitleTrack;
        if (this.mCurrentSubtitleTrack != null) {
            this.mSubtitledlid = this.mCurrentSubtitleTrack.getDownloadableId();
        }
        else {
            this.mSubtitledlid = null;
        }
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Session", "Selected track Audio: " + audioSource);
            Log.d("OfflinePlayback_Session", "Selected track Subtitle: " + mCurrentSubtitleTrack);
            Log.d("OfflinePlayback_Session", "User changed: " + b);
        }
        boolean selectAudioTrack;
        if (b) {
            final boolean b3 = selectAudioTrack = this.selectAudioTrack(audioSource);
            if (b3) {
                this.updateCurrentAudioTrackData();
                selectAudioTrack = b3;
            }
        }
        else {
            Log.d("OfflinePlayback_Session", "Initial set of audio and subtitle. ExoPlayer sets initial audio, do subtitle only!");
            selectAudioTrack = b2;
        }
        this.mSubtitles.changeSubtitle(mCurrentSubtitleTrack, 0.0f, this.mBookmark);
        if (mCurrentSubtitleTrack == null) {
            Log.d("OfflinePlayback_Session", "Removing subtitles");
        }
        this.mPlayTracker.updateCurrrentPlayDlids(this.mVdlid, this.mAdlid, this.mSubtitledlid);
        return selectAudioTrack;
    }
    
    @Override
    public void setAudioDuck(final boolean audioDuck) {
        if (this.mOfflinePlayer != null) {
            this.mOfflinePlayer.setAudioDuck(audioDuck);
        }
    }
    
    @Override
    public void setSurface(final Surface surface) {
        if (this.mOfflinePlayer != null) {
            this.mOfflinePlayer.setSurface(surface);
        }
        this.mSurface = surface;
    }
    
    @Override
    public void stop() {
        Log.d("OfflinePlayback_Session", "stop: ");
        if (this.mOfflinePlayer != null) {
            this.mOfflinePlayer.stop();
            this.mMainHandler.removeCallbacks(this.mPeriodicCallback);
            this.mOfflineAgent.notifyStop(this.mMovieId);
            this.reportEndPlay(OfflinePlaybackSession$EndPlayReason.stopped, null, null, null, this.getActivityData());
            Log.d("OfflinePlayback_Session", "stop: " + this.getPlayTimeJson());
            this.mPdsPlaySession.stop(this.getPlayTimeJson(), null, null);
            return;
        }
        this.mOfflineAgent.abortManifestRequest(this.mMovieId);
    }
}
