// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.google.android.exoplayer.ExoPlayer$ExoPlayerComponent;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.exoplayer.ExoPlaybackException;
import java.io.IOException;
import android.view.Surface;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.MediaCodecTrackRenderer$DecoderInitializationException;
import android.media.MediaCodec$CryptoException;
import com.google.android.exoplayer.TimeRange;
import com.google.android.exoplayer.audio.AudioTrack$WriteException;
import com.google.android.exoplayer.audio.AudioTrack$InitializationException;
import android.util.Pair;
import org.json.JSONObject;
import com.google.android.exoplayer.ExoPlayer$Factory;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.LoadControl;
import com.google.android.exoplayer.chunk.ChunkSource;
import com.google.android.exoplayer.chunk.ChunkSampleSource;
import com.google.android.exoplayer.chunk.FormatEvaluator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.dash.DashTrackSelector;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.google.android.exoplayer.chunk.FormatEvaluator$AdaptiveEvaluator;
import com.google.android.exoplayer.upstream.TransferListener;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.dash.DefaultDashTrackSelector;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.DefaultLoadControl;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.netflix.mediaclient.Log;
import com.google.android.exoplayer.drm.DrmSessionManager;
import android.os.Handler;
import android.content.Context;
import com.google.android.exoplayer.dash.DashChunkSource;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.upstream.BandwidthMeter$EventListener;
import com.google.android.exoplayer.dash.DashChunkSource$EventListener;
import com.google.android.exoplayer.chunk.ChunkSampleSource$EventListener;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer$EventListener;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer$EventListener;
import com.google.android.exoplayer.ExoPlayer$Listener;

class PlayerWithStaticMPD implements ExoPlayer$Listener, MediaCodecAudioTrackRenderer$EventListener, MediaCodecVideoTrackRenderer$EventListener, ChunkSampleSource$EventListener, DashChunkSource$EventListener, BandwidthMeter$EventListener
{
    private static final int AUDIO_BUFFER_SEGMENTS = 54;
    private static final int BUFFER_SEGMENT_SIZE = 65536;
    static final int RENDERER_COUNT = 2;
    private static final String TAG = "OfflinePlayback_Player";
    static final int TYPE_AUDIO = 1;
    static final int TYPE_VIDEO = 0;
    private static final int VIDEO_BUFFER_SEGMENTS = 200;
    private TrackRenderer mAudioRender;
    private IPlayerListener mCallback;
    private ExoPlayer mExoPlayer;
    private int mHeight;
    private MediaPresentationDescription mMPD;
    private boolean mPlaybackStarted;
    private PlaybackStat mPlaybackStat;
    private int mPlayerState;
    private long mSeekToMs;
    DashChunkSource mVideoChunkSource;
    private String mVideoDecoderName;
    private int mWidth;
    TrackRenderer[] renderers;
    
    PlayerWithStaticMPD(final Context context, final Handler handler, final IPlayerListener mCallback, final MediaPresentationDescription mmpd, final DrmSessionManager drmSessionManager, final long mSeekToMs) {
        this.mPlayerState = 0;
        this.mWidth = 0;
        this.mHeight = 0;
        Log.d("OfflinePlayback_Player", "()");
        this.mCallback = mCallback;
        this.mMPD = mmpd;
        this.mSeekToMs = mSeekToMs;
        this.createRendersAndPlayer(context, handler, drmSessionManager);
        Log.d("OfflinePlayback_Player", "() done");
    }
    
    private void createRendersAndPlayer(final Context context, final Handler handler, final DrmSessionManager drmSessionManager) {
        final DefaultLoadControl defaultLoadControl = new DefaultLoadControl(new DefaultAllocator(65536));
        final DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter(handler, this);
        this.mVideoChunkSource = new DashChunkSource(this.mMPD, DefaultDashTrackSelector.newVideoInstance(context, true, true), new DefaultUriDataSource(context, defaultBandwidthMeter, Util.getUserAgent(context, "OfflinePlayback_Player")), new FormatEvaluator$AdaptiveEvaluator(defaultBandwidthMeter));
        final MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer = new MediaCodecVideoTrackRenderer(context, new ChunkSampleSource(this.mVideoChunkSource, defaultLoadControl, 13107200, handler, this, 0), new ExoVideoCodecSelector(), 1, 5000L, drmSessionManager, true, handler, this, 50);
        this.mAudioRender = new MediaCodecAudioTrackRenderer(new ChunkSampleSource(new DashChunkSource(this.mMPD, DefaultDashTrackSelector.newAudioInstance(), new DefaultUriDataSource(context, defaultBandwidthMeter, Util.getUserAgent(context, "OfflinePlayback_Player")), new FormatEvaluator$AdaptiveEvaluator(defaultBandwidthMeter)), defaultLoadControl, 3538944, handler, this, 1), MediaCodecSelector.DEFAULT, drmSessionManager, true, handler, this, AudioCapabilities.getCapabilities(context), 3);
        (this.renderers = new TrackRenderer[2])[0] = mediaCodecVideoTrackRenderer;
        this.renderers[1] = this.mAudioRender;
        (this.mExoPlayer = ExoPlayer$Factory.newInstance(2, 1000, 5000)).addListener(this);
        this.mExoPlayer.seekTo(this.mSeekToMs);
        this.mExoPlayer.prepare(this.renderers);
        this.play();
    }
    
    private void setVideoDecoderName(final String mVideoDecoderName) {
        if (mVideoDecoderName.toLowerCase().contains("video") || mVideoDecoderName.toLowerCase().contains("vp9") || mVideoDecoderName.toLowerCase().contains("hevc") || mVideoDecoderName.toLowerCase().contains("h265") || mVideoDecoderName.toLowerCase().contains("avc") || mVideoDecoderName.toLowerCase().contains("h264")) {
            this.mVideoDecoderName = mVideoDecoderName;
            this.mPlaybackStat = new PlaybackStat(this.mVideoDecoderName);
        }
    }
    
    public String getAudioCurrentTrackId() {
        final int trackCount = this.mExoPlayer.getTrackCount(1);
        final int selectedTrack = this.mExoPlayer.getSelectedTrack(1);
        if (selectedTrack < trackCount) {
            if (Log.isLoggable()) {
                Log.d("OfflinePlayback_Player", "getAudioCurrentTrackId " + this.mExoPlayer.getTrackFormat(1, selectedTrack).trackId);
            }
            return this.mExoPlayer.getTrackFormat(1, selectedTrack).trackId;
        }
        return null;
    }
    
    public int getBufferedPercentage() {
        return this.mExoPlayer.getBufferedPercentage();
    }
    
    public long getBufferedPosition() {
        return this.mExoPlayer.getBufferedPosition();
    }
    
    public long getCurrentPosition() {
        return this.mExoPlayer.getCurrentPosition();
    }
    
    public long getDuration() {
        return this.mExoPlayer.getDuration();
    }
    
    public JSONObject getPlaybackStatJSON() {
        if (this.mPlaybackStat != null) {
            return this.mPlaybackStat.getJSON();
        }
        return null;
    }
    
    public String getVideoDecoderName() {
        return this.mVideoDecoderName;
    }
    
    public Pair<Integer, Integer> getVideoResolution() {
        return (Pair<Integer, Integer>)Pair.create((Object)this.mWidth, (Object)this.mHeight);
    }
    
    public boolean isPlaying() {
        return this.mExoPlayer.getPlaybackState() == 4 && this.mExoPlayer.getPlayWhenReady();
    }
    
    @Override
    public void onAudioTrackInitializationError(final AudioTrack$InitializationException ex) {
        this.mCallback.playerError(new ExoPlaybackError(ExoPlaybackError$ExoPlaybackErrorCode.AUDIOTRACK_INIT_ERROR, ex.toString(), OfflinePlaybackState.PLAYBACK_INIT.toString(), Log.getStackTraceString(ex)));
        Log.d("OfflinePlayback_Player", "onAudioTrackInitializationError");
    }
    
    @Override
    public void onAudioTrackUnderrun(final int n, final long n2, final long n3) {
        Log.d("OfflinePlayback_Player", "onAudioTrackUnderrun");
    }
    
    @Override
    public void onAudioTrackWriteError(final AudioTrack$WriteException ex) {
        this.mCallback.playerError(new ExoPlaybackError(ExoPlaybackError$ExoPlaybackErrorCode.AUDIOTRACK_WRITE_ERROR, ex.toString(), OfflinePlaybackState.PLAYBACK_PLAY.toString(), Log.getStackTraceString(ex)));
        Log.d("OfflinePlayback_Player", "onAudioTrackWriteError");
    }
    
    @Override
    public void onAvailableRangeChanged(final int n, final TimeRange timeRange) {
        Log.d("OfflinePlayback_Player", "DashChunkSource onAvailableRangeChanged");
    }
    
    @Override
    public void onBandwidthSample(final int n, final long n2, final long n3) {
        if (Log.isLoggable()) {}
    }
    
    @Override
    public void onCryptoError(final MediaCodec$CryptoException ex) {
        this.mCallback.playerError(new ExoPlaybackError(ExoPlaybackError$ExoPlaybackErrorCode.CRYPTO_ERROR, ex.toString(), OfflinePlaybackState.PLAYBACK_PLAY.toString(), Log.getStackTraceString((Throwable)ex)));
        Log.e("OfflinePlayback_Player", (Throwable)ex, "MediaCodecTrackRenderer onCryptoError", new Object[0]);
    }
    
    @Override
    public void onDecoderInitializationError(final MediaCodecTrackRenderer$DecoderInitializationException ex) {
        this.mCallback.playerError(new ExoPlaybackError(ExoPlaybackError$ExoPlaybackErrorCode.DECODER_INIT_ERROR, ex.toString(), OfflinePlaybackState.PLAYBACK_INIT.toString(), Log.getStackTraceString(ex)));
        Log.d("OfflinePlayback_Player", "MediaCodecTrackRenderer onDecoderInitializationError");
    }
    
    @Override
    public void onDecoderInitialized(final String videoDecoderName, final long n, final long n2) {
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Player", "MediaCodecTrackRenderer onDecoderInitialized" + videoDecoderName);
        }
        this.setVideoDecoderName(videoDecoderName);
    }
    
    @Override
    public void onDownstreamFormatChanged(final int n, final Format format, final int n2, final long n3) {
    }
    
    @Override
    public void onDrawnToSurface(final Surface surface) {
        Log.d("OfflinePlayback_Player", "onDrawnToSurface");
    }
    
    @Override
    public void onDroppedFrames(final int n, final long n2) {
        Log.d("OfflinePlayback_Player", "onDroppedFrames: count: " + n + " elapsed: " + n2);
        if (this.mPlaybackStat != null) {
            this.mPlaybackStat.updateDecoderStat(n, n2);
        }
    }
    
    @Override
    public void onLoadCanceled(final int n, final long n2) {
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Player", "ChunkSampleSource onLoadCanceled " + n);
        }
    }
    
    @Override
    public void onLoadCompleted(final int n, final long n2, final int n3, final int n4, final Format format, final long n5, final long n6, final long n7, final long n8) {
        if (Log.isLoggable()) {}
    }
    
    @Override
    public void onLoadError(final int n, final IOException ex) {
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Player", "ChunkSampleSource onLoadError " + n + ", " + ex);
        }
        final IPlayerListener mCallback = this.mCallback;
        ExoPlaybackError$ExoPlaybackErrorCode exoPlaybackError$ExoPlaybackErrorCode;
        if (n == 1) {
            exoPlaybackError$ExoPlaybackErrorCode = ExoPlaybackError$ExoPlaybackErrorCode.AUDIO_LOAD_ERROR;
        }
        else {
            exoPlaybackError$ExoPlaybackErrorCode = ExoPlaybackError$ExoPlaybackErrorCode.VIDEO_LOAD_ERROR;
        }
        mCallback.playerError(new ExoPlaybackError(exoPlaybackError$ExoPlaybackErrorCode, ex.toString(), OfflinePlaybackState.PLAYBACK_PLAY.toString(), Log.getStackTraceString(ex)));
    }
    
    @Override
    public void onLoadStarted(final int n, final long n2, final int n3, final int n4, final Format format, final long n5, final long n6) {
        if (Log.isLoggable()) {}
    }
    
    @Override
    public void onPlayWhenReadyCommitted() {
        Log.d("OfflinePlayback_Player", "onPlayWhenReadyCommitted");
    }
    
    @Override
    public void onPlayerError(final ExoPlaybackException ex) {
        this.mCallback.playerError(new ExoPlaybackError(ExoPlaybackError$ExoPlaybackErrorCode.PLAYER_ERROR, ex.toString(), OfflinePlaybackState.PLAYBACK_PLAY.toString(), Log.getStackTraceString(ex)));
        Log.d("OfflinePlayback_Player", "onPlayerError");
    }
    
    @Override
    public void onPlayerStateChanged(final boolean b, final int mPlayerState) {
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Player", "onPlayerStateChanged  =>" + mPlayerState);
        }
        if (mPlayerState == 4 && mPlayerState != this.mPlayerState && !this.mPlaybackStarted) {
            this.mCallback.playerPrepared();
        }
        if (mPlayerState == 4) {
            if (b) {
                this.mCallback.playerStarted();
                this.mPlaybackStarted = true;
            }
            else if (this.mPlaybackStarted) {
                this.mCallback.playerPaused();
            }
        }
        if (mPlayerState == 3) {
            this.mCallback.playerBuffering();
        }
        if (mPlayerState == 5) {
            this.mCallback.playerStopped();
            this.mPlaybackStarted = false;
        }
        this.mPlayerState = mPlayerState;
    }
    
    @Override
    public void onUpstreamDiscarded(final int n, final long n2, final long n3) {
    }
    
    @Override
    public void onVideoSizeChanged(final int mWidth, final int mHeight, final int n, final float n2) {
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Player", "onVideoSizeChanged " + mWidth + " X " + mHeight);
        }
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    public void pause() {
        this.mExoPlayer.setPlayWhenReady(false);
    }
    
    public void play() {
        Log.d("OfflinePlayback_Player", "play() ");
        this.mExoPlayer.setPlayWhenReady(true);
    }
    
    public void release() {
        this.mExoPlayer.release();
    }
    
    public void seekTo(final long n) {
        final long seekToTime = this.mVideoChunkSource.getSeekToTime(1000L * n);
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Player", "seekTo() " + n + ", will seek to " + seekToTime);
        }
        this.mExoPlayer.seekTo(seekToTime);
    }
    
    public boolean selectAudioTrack(final String s) {
        if (Log.isLoggable()) {
            Log.d("OfflinePlayback_Player", "selectAudioTrack " + s);
        }
        if (StringUtils.safeEquals(this.getAudioCurrentTrackId(), s)) {
            return true;
        }
        for (int trackCount = this.mExoPlayer.getTrackCount(1), i = 0; i < trackCount; ++i) {
            if (StringUtils.safeEquals(this.mExoPlayer.getTrackFormat(1, i).trackId, s)) {
                if (Log.isLoggable()) {
                    Log.d("OfflinePlayback_Player", "selectAudioTrack index = " + i);
                }
                this.mExoPlayer.setSelectedTrack(1, i);
                return true;
            }
        }
        return false;
    }
    
    public void setAudioDuck(final boolean b) {
        if (b) {
            this.mExoPlayer.sendMessage(this.mAudioRender, 1, 0.3f);
            return;
        }
        this.mExoPlayer.sendMessage(this.mAudioRender, 1, 1.0f);
    }
    
    public void setSurface(final Surface surface) {
        this.mExoPlayer.sendMessage(this.renderers[0], 1, surface);
    }
    
    public void stop() {
        this.mExoPlayer.stop();
    }
}
