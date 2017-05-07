// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import android.widget.Toast;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.view.Menu;
import java.io.Serializable;
import android.os.Bundle;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.util.Pair;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import android.widget.TextView;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.Playable;
import android.app.Activity;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.DeviceUtils;
import android.os.Parcelable;
import android.content.Intent;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.widget.SeekBar;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import android.os.SystemClock;
import com.netflix.mediaclient.ui.common.RatingDialogFrag;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import android.content.Context;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.details.EpisodeListFrag;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.ui.common.Social;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.SeasonDetails;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import android.os.Handler;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.media.Language;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.ui.common.VideoDetailsProvider;
import com.netflix.mediaclient.ui.details.EpisodeRowView;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class MdxPlaycardActivity extends NetflixActivity implements EpisodeRowListenerProvider, EpisodeRowListener, VideoDetailsProvider, MessageResponseProvider, Callback, MdxTargetSelectionDialogInterface, ErrorHandlerCallbacks, MdxKeyEventCallbacks
{
    private static final int DELAY_POST = 1000;
    private static final int DELAY_REMOVING_SOCIAL = 3000;
    private static final String PARAM_LANGUAGE = "mdxui_language";
    private static final String PARAM_RATING = "mdxui_rating";
    private static final String PARAM_SOCIAL_NOT_VISIBLE = "mdxui_socialNotVisible";
    static final String TAG = "mdxui";
    private final SeekBar$OnSeekBarChangeListener audioPositionListener;
    private final DialogMessageReceiver dialogMessageReceiver;
    private final View$OnClickListener episodesListener;
    private Language language;
    private boolean mBoxartDownloaded;
    private Asset mCurrentPlayout;
    private String mCurrentTarget;
    protected Handler mHandler;
    private boolean mIsTablet;
    private VideoDetails mPlayable;
    private RemotePlayer mPlayer;
    private float mRating;
    private final RemotePlayer.RemoteTargetUiListener mRemotePlaybackCallback;
    private PlayerScreenResourceHelper mResources;
    private Language mSavedLanguage;
    private PlaycardScreen mScreen;
    private SeasonDetails mSeasonDetails;
    private ServiceManager mServiceManager;
    private long mSimulatedCurrentTimelinePosition;
    private boolean mSocialDoNotShare;
    private final Social.SocialProviderCallback mSocialProviderCallback;
    private final PlaycardWorkflowState mState;
    private long mTimelineUpdateThreadFiredTime;
    private MdxErrorHandler mdxErrorHandler;
    private MdxKeyEventHandler mdxKeyEventHandler;
    private final Runnable onEverySecond;
    private final View$OnClickListener playPauseListener;
    private final View$OnClickListener ratingListener;
    private final View$OnClickListener skipBackListener;
    private final View$OnClickListener stopListener;
    private final SeekBar$OnSeekBarChangeListener videoPositionListener;
    
    public MdxPlaycardActivity() {
        this.mState = new PlaycardWorkflowState();
        this.mHandler = new Handler();
        this.dialogMessageReceiver = new DialogMessageReceiver((DialogMessageReceiver.Callback)this);
        this.stopListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                Log.d("mdxui", "Stop remote playback and exit");
                MdxPlaycardActivity.this.mPlayer.stop(true);
            }
        };
        this.episodesListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                if (MdxPlaycardActivity.this.mPlayable == null) {
                    Log.e("mdxui", "mPlayable is null!");
                    return;
                }
                if (!(MdxPlaycardActivity.this.mPlayable instanceof EpisodeDetails)) {
                    Log.e("mdxui", "mPlayable is not episode detail!");
                    return;
                }
                Log.d("mdxui", "Start episodes dialog");
                final NetflixDialogFrag create = EpisodeListFrag.create(MdxPlaycardActivity.this.mPlayable.getParentId(), null, false);
                create.onManagerReady(MdxPlaycardActivity.this.mServiceManager, 0);
                create.setCancelable(true);
                MdxPlaycardActivity.this.showDialog(create);
            }
        };
        this.playPauseListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (MdxPlaycardActivity.this.destroyed()) {
                    Log.d("mdxui", "Activity was finishing, exit!");
                    return;
                }
                if (MdxPlaycardActivity.this.mState.paused) {
                    Log.d("mdxui", "TRICKPLAY: Remote playback was paused, resume.");
                    MdxPlaycardActivity.this.mPlayer.resume();
                    MdxPlaycardActivity.this.mScreen.setPausePlayButtonState(false);
                    MdxPlaycardActivity.this.mState.paused = false;
                    MdxPlaycardActivity.this.startSimulatedVideoPositionUpdate();
                    return;
                }
                Log.d("mdxui", "TRICKPLAY: Remote playback was playing, pause.");
                MdxPlaycardActivity.this.mPlayer.pause();
                MdxPlaycardActivity.this.mScreen.setPausePlayButtonState(true);
                MdxPlaycardActivity.this.mState.paused = true;
                MdxPlaycardActivity.this.stopSimulatedVideoPositionUpdate();
            }
        };
        this.ratingListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (MdxPlaycardActivity.this.destroyed()) {
                    Log.d("mdxui", "Activity was finishing, exit!");
                    return;
                }
                if (MdxPlaycardActivity.this.mPlayable == null) {
                    Log.e("mdxui", "Playable is NULL. This should NOT happen!");
                    return;
                }
                LogUtils.reportRateActionStarted((Context)MdxPlaycardActivity.this, null, MdxPlaycardActivity.this.getUiScreen());
                if (Log.isLoggable("mdxui", 3)) {
                    Log.d("mdxui", "User set rating " + MdxPlaycardActivity.this.mRating);
                    Log.d("mdxui", "User rating from VideoDetails " + MdxPlaycardActivity.this.mPlayable.getUserRating());
                }
                String s;
                if (StringUtils.isEmpty(s = MdxPlaycardActivity.this.mPlayable.getParentTitle())) {
                    s = MdxPlaycardActivity.this.mPlayable.getTitle();
                }
                final RatingDialogFrag instance = RatingDialogFrag.newInstance(MdxPlaycardActivity.getRating(MdxPlaycardActivity.this.mPlayable, MdxPlaycardActivity.this.mRating), MdxPlaycardActivity.getVideoId(MdxPlaycardActivity.this.mPlayable), s);
                instance.setCancelable(true);
                MdxPlaycardActivity.this.showDialog(instance);
            }
        };
        this.skipBackListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                Log.d("mdxui", "Skip back 10 seconds");
                MdxPlaycardActivity.this.mState.lastActionTime = SystemClock.elapsedRealtime();
                MdxPlaycardActivity.this.mScreen.setBufferingVisibility(true);
                MdxPlaycardActivity.this.mScreen.changeActionState(false, false);
                MdxPlaycardActivity.this.mPlayer.skipBackThirtySeconds();
                MdxPlaycardActivity.this.stopSimulatedVideoPositionUpdate();
            }
        };
        this.videoPositionListener = (SeekBar$OnSeekBarChangeListener)new SeekBar$OnSeekBarChangeListener() {
            private boolean skipSeek(final NetflixSeekBar netflixSeekBar) {
                if (netflixSeekBar == null) {
                    return false;
                }
                boolean b = false;
                if (netflixSeekBar.inSnapPosition()) {
                    Log.d("mdxui", "Back to start position after snap, do NOT seek!");
                    b = true;
                    MdxPlaycardActivity.this.mState.timelineExitOnSnap = true;
                }
                else if (MdxPlaycardActivity.this.mState.timelineInSnapZone) {
                    Log.d("mdxui", "We are in snap zone, reset progress bar to it and update labels");
                    b = true;
                    MdxPlaycardActivity.this.mState.timelineExitOnSnap = true;
                }
                Log.d("mdxui", "tate.timelineExitOnSnap " + MdxPlaycardActivity.this.mState.timelineExitOnSnap);
                return b;
            }
            
            public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
                final boolean b2 = false;
                Log.d("mdxui", "Progress: " + n + ", fromUser " + b);
                if (b && MdxPlaycardActivity.this.mState.draggingInProgress && MdxPlaycardActivity.this.mScreen != null && MdxPlaycardActivity.this.mPlayer != null) {
                    final boolean access$800 = MdxPlaycardActivity.this.isSeekInSnapZone(n, seekBar, MdxPlaycardActivity.this.mPlayer.getDuration());
                    if (access$800) {
                        if (MdxPlaycardActivity.this.mState.timelineInSnapZone) {
                            if (seekBar instanceof NetflixSeekBar) {
                                final NetflixSeekBar netflixSeekBar = (NetflixSeekBar)seekBar;
                                if (Log.isLoggable("mdxui", 3)) {
                                    Log.d("mdxui", "Snap to position was in progress " + netflixSeekBar.isSnapInProgress());
                                }
                                if (netflixSeekBar.isSnapInProgress()) {
                                    Log.d("mdxui", "We were already in snap zone. Do nothing! Just return!");
                                    return;
                                }
                                Log.d("mdxui", "We were already in snap zone. Update labels!");
                                MdxPlaycardActivity.this.mScreen.setProgress(n, -1, false);
                                MdxPlaycardActivity.this.mState.updatePosition(n);
                            }
                            else {
                                Log.e("mdxui", "Not NETFLIX seekbar!");
                            }
                        }
                        else {
                            Log.d("mdxui", "In snap zone. Snap!");
                            MdxPlaycardActivity.this.mScreen.snapToPosition(MdxPlaycardActivity.this.mState.timelineStartSeekPosition, -1);
                            MdxPlaycardActivity.this.mState.updatePosition(MdxPlaycardActivity.this.mState.timelineStartSeekPosition);
                        }
                    }
                    else {
                        Log.d("mdxui", "Not in snap zone.");
                        MdxPlaycardActivity.this.mScreen.setProgress(n, -1, false);
                        MdxPlaycardActivity.this.mState.updatePosition(n);
                    }
                    MdxPlaycardActivity.this.mScreen.showBif(MdxPlaycardActivity.this.getBifFrame(seekBar.getProgress()));
                    Log.d("mdxui", "onProgressChange showing bif");
                    final PlaycardScreen access$801 = MdxPlaycardActivity.this.mScreen;
                    boolean b3 = b2;
                    if (!MdxPlaycardActivity.this.mIsTablet) {
                        b3 = true;
                    }
                    access$801.moveCurrentTimeWithTimeline(b3, true);
                    MdxPlaycardActivity.this.mState.timelineInSnapZone = access$800;
                    return;
                }
                if (!b && MdxPlaycardActivity.this.mState.draggingInProgress && MdxPlaycardActivity.this.mScreen != null) {
                    Log.d("mdxui", "Not from user and state.draggingInProgress is true ");
                    MdxPlaycardActivity.this.mScreen.setProgress(n, -1, false);
                    MdxPlaycardActivity.this.mState.updatePosition(n);
                }
            }
            
            public void onStartTrackingTouch(final SeekBar seekBar) {
                synchronized (this) {
                    MdxPlaycardActivity.this.mState.draggingInProgress = true;
                    MdxPlaycardActivity.this.stopSimulatedVideoPositionUpdate();
                    int timelineStartSeekPosition;
                    if (seekBar instanceof NetflixSeekBar) {
                        timelineStartSeekPosition = ((NetflixSeekBar)seekBar).setDentVisible(true);
                    }
                    else {
                        Log.e("mdxui", "Not a Netflix seekbar!");
                        timelineStartSeekPosition = seekBar.getProgress();
                    }
                    MdxPlaycardActivity.this.mState.resetTimeline();
                    MdxPlaycardActivity.this.mState.timelineStartSeekPosition = timelineStartSeekPosition;
                    MdxPlaycardActivity.this.mState.updatePosition(timelineStartSeekPosition);
                    MdxPlaycardActivity.this.mState.timelineInSnapZone = true;
                    MdxPlaycardActivity.this.mScreen.setLastTimeState(true);
                    MdxPlaycardActivity.this.mScreen.startCurrentTime(MdxPlaycardActivity.this.getBifFrame(timelineStartSeekPosition));
                    MdxPlaycardActivity.this.mScreen.changeActionState(false, true);
                    MdxPlaycardActivity.this.mScreen.updateScreenOnVideoSeekStart();
                    MdxPlaycardActivity.this.mScreen.setVideoMatadataVisibility(false);
                }
            }
            
            public void onStopTrackingTouch(final SeekBar progressByBif) {
            Label_0215_Outer:
                while (true) {
                    boolean b = true;
                    while (true) {
                    Label_0292:
                        while (true) {
                            NetflixSeekBar netflixSeekBar;
                            synchronized (this) {
                                if (Log.isLoggable("mdxui", 3)) {
                                    Log.d("mdxui", "VIDEO:: onStopTrackingTouch called " + progressByBif.getProgress() + ", duration " + progressByBif.getMax());
                                }
                                MdxPlaycardActivity.this.mState.draggingInProgress = false;
                                MdxPlaycardActivity.this.mScreen.setVideoMatadataVisibility(true);
                                netflixSeekBar = null;
                                if (progressByBif instanceof NetflixSeekBar) {
                                    netflixSeekBar = (NetflixSeekBar)progressByBif;
                                }
                                else {
                                    Log.e("mdxui", "Not a Netflix seekbar!");
                                }
                                final boolean skipSeek = this.skipSeek(netflixSeekBar);
                                if (!skipSeek) {
                                    final int setProgressByBif = MdxPlaycardActivity.setProgressByBif(progressByBif);
                                    Log.d("mdxui", "Seek!");
                                    MdxPlaycardActivity.this.mScreen.setBufferingVisibility(true);
                                    MdxPlaycardActivity.this.mPlayer.seek(setProgressByBif);
                                    progressByBif.setEnabled(false);
                                    MdxPlaycardActivity.this.mScreen.setLastTimeState(false);
                                    if (netflixSeekBar != null) {
                                        netflixSeekBar.setDentVisible(false);
                                    }
                                    if (Log.isLoggable("mdxui", 3)) {
                                        Log.d("mdxui", "Stop current time " + skipSeek);
                                    }
                                    final PlaycardScreen access$400 = MdxPlaycardActivity.this.mScreen;
                                    if (!skipSeek) {
                                        access$400.stopCurrentTime(b);
                                        MdxPlaycardActivity.this.mState.resetTimeline();
                                        return;
                                    }
                                    break Label_0292;
                                }
                            }
                            Log.d("mdxui", "Do not seek!");
                            netflixSeekBar.setProgress(MdxPlaycardActivity.this.mState.timelineStartSeekPosition);
                            MdxPlaycardActivity.this.startSimulatedVideoPositionUpdate();
                            MdxPlaycardActivity.this.mScreen.updateScreenOnVideoSeekCompleted();
                            continue Label_0215_Outer;
                        }
                        b = false;
                        continue;
                    }
                }
            }
        };
        this.audioPositionListener = (SeekBar$OnSeekBarChangeListener)new SeekBar$OnSeekBarChangeListener() {
            public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
            }
            
            public void onStartTrackingTouch(final SeekBar seekBar) {
                Log.d("mdxui", "Start volume change");
                MdxPlaycardActivity.this.mState.audioSeekToInProgress = true;
            }
            
            public void onStopTrackingTouch(final SeekBar seekBar) {
                Log.d("mdxui", "volume::onStopTrackingTouch called");
                MdxPlaycardActivity.this.mState.audioSeekToInProgress = false;
                final int volume = seekBar.getProgress() * 100 / seekBar.getMax();
                if (Log.isLoggable("mdxui", 3)) {
                    Log.d("mdxui", "Set volume to: raw " + seekBar.getProgress() + ", percentage: " + volume);
                }
                MdxPlaycardActivity.this.mPlayer.setVolume(volume);
            }
        };
        this.mRemotePlaybackCallback = new RemotePlayer.RemoteTargetUiListener() {
            @Override
            public void cancelDialog() {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                MdxPlaycardActivity.this.removeVisibleDialog();
            }
            
            @Override
            public void endOfPlayback() {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                MdxPlaycardActivity.this.destroy();
            }
            
            @Override
            public void error(final int n, final String s) {
                MdxPlaycardActivity.this.mdxErrorHandler.handleMdxError(n, s);
            }
            
            @Override
            public void mdxStateChanged(final boolean b) {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                if (b) {
                    Log.d("mdxui", "MDX is ready, we should not really get this, since we are not staying up when we got NOTREADY");
                    return;
                }
                Log.d("mdxui", "MDX is NOT ready, back to previous UI screen.");
                MdxPlaycardActivity.this.destroy();
            }
            
            @Override
            public void showDialog(final RemoteDialog remoteDialog) {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                final ShowMessageDialogFrag instance = ShowMessageDialogFrag.newInstance(remoteDialog);
                instance.onManagerReady(MdxPlaycardActivity.this.mServiceManager, 0);
                instance.setCancelable(true);
                MdxPlaycardActivity.this.showDialog(instance);
            }
            
            @Override
            public void targetListChanged() {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                MdxPlaycardActivity.this.setTargetSelection();
            }
            
            @Override
            public void updateDuration(final int n) {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                if (Log.isLoggable("mdxui", 3)) {
                    Log.d("mdxui", "Init video seekbar to duration " + n);
                }
                if (n > 0) {
                    MdxPlaycardActivity.this.mScreen.getBottomPanel().initProgress(n);
                    return;
                }
                Log.w("mdxui", "We received illegal duration! Ignore!");
            }
            
            @Override
            public void updateLanguage(final Language language) {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                MdxPlaycardActivity.this.language = language;
                MdxPlaycardActivity.this.mScreen.setLanguage(language);
            }
            
            @Override
            public void updateTargetCapabilities(final MdxTargetCapabilities mdxTargetCapabilities) {
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                if (mdxTargetCapabilities == null) {
                    Log.w("mdxui", "Capabilities is null!");
                    MdxPlaycardActivity.this.mScreen.getTopPanel().setVolumeChangeSupported(false, 1);
                    return;
                }
                if (Log.isLoggable("mdxui", 3)) {
                    Log.d("mdxui", "Capabilities " + mdxTargetCapabilities);
                }
                int volumeStep;
                if ((volumeStep = mdxTargetCapabilities.getVolumeStep()) <= 0) {
                    volumeStep = 1;
                }
                MdxPlaycardActivity.this.mScreen.getTopPanel().setVolumeChangeSupported(mdxTargetCapabilities.isVolumeControl(), volumeStep);
            }
            
            @Override
            public void updateUi(final RemoteTargetState remoteTargetState) {
                if (!MdxPlaycardActivity.this.destroyed()) {
                    if (Log.isLoggable("mdxui", 3)) {
                        Log.d("mdxui", "Update video seekbar to position " + remoteTargetState.positionInSeconds + ", duration " + remoteTargetState.duration);
                        Log.d("mdxui", "Update audio seekbar to position " + remoteTargetState.volume);
                        Log.d("mdxui", "Should update volume " + !MdxPlaycardActivity.this.mState.audioSeekToInProgress);
                    }
                    if (MdxPlaycardActivity.this.mPlayer.getPositionInSeconds() >= 0 && !MdxPlaycardActivity.this.mState.draggingInProgress) {
                        Log.d("mdxui", "Update video seekbar");
                        MdxPlaycardActivity.this.setProgress(remoteTargetState.positionInSeconds, remoteTargetState.duration);
                        MdxPlaycardActivity.this.mSimulatedCurrentTimelinePosition = remoteTargetState.positionInSeconds * 1000;
                    }
                    if (MdxPlaycardActivity.this.mPlayer.getVolume() >= 0 && !MdxPlaycardActivity.this.mState.audioSeekToInProgress) {
                        Log.d("mdxui", "Update volume");
                        MdxPlaycardActivity.this.mScreen.getTopPanel().setAudioProgress(remoteTargetState.volume);
                    }
                    MdxPlaycardActivity.this.mScreen.setBufferingVisibility(remoteTargetState.buffering);
                    MdxPlaycardActivity.this.mScreen.changeActionState(!remoteTargetState.buffering, false);
                    MdxPlaycardActivity.this.mScreen.setPausePlayButtonState(remoteTargetState.paused);
                    if (!remoteTargetState.buffering && !MdxPlaycardActivity.this.mState.draggingInProgress) {
                        MdxPlaycardActivity.this.getScreen().stopBif();
                        MdxPlaycardActivity.this.mScreen.updateScreenOnVideoSeekCompleted();
                        MdxPlaycardActivity.this.mScreen.getBottomPanel().getTimeline().setEnabled(true);
                    }
                    if (remoteTargetState.buffering || remoteTargetState.paused) {
                        MdxPlaycardActivity.this.stopSimulatedVideoPositionUpdate();
                        return;
                    }
                    if (!remoteTargetState.paused) {
                        MdxPlaycardActivity.this.startSimulatedVideoPositionUpdate();
                    }
                }
            }
            
            @Override
            public void updateVideoMetadata() {
                Log.d("mdxui", "updateVideoMetadata...");
                if (MdxPlaycardActivity.this.destroyed()) {
                    return;
                }
                final VideoDetails videoDetail = MdxPlaycardActivity.this.mServiceManager.getMdx().getVideoDetail();
                if (MdxPlaycardActivity.this.mCurrentPlayout != null) {
                    Log.d("mdxui", "Video details from asset exist, use them.");
                    if (!MdxPlaycardActivity.isSameVideoPlaying(MdxPlaycardActivity.this.mServiceManager.getMdx(), MdxPlaycardActivity.this.mCurrentPlayout.getPlayableId())) {
                        Log.d("mdxui", "Different video, update UI...");
                        MdxPlaycardActivity.this.mBoxartDownloaded = false;
                        MdxPlaycardActivity.this.populateUi(true);
                        return;
                    }
                    if (MdxPlaycardActivity.this.mPlayable == null) {
                        Log.d("mdxui", "Same video. Playable was null, set it!");
                        MdxPlaycardActivity.this.mPlayable = videoDetail;
                        MdxPlaycardActivity.this.mBoxartDownloaded = false;
                        MdxPlaycardActivity.this.populateUi(true);
                        return;
                    }
                    if (MdxPlaycardActivity.this.mBoxartDownloaded) {
                        Log.d("mdxui", "Same video, playable know, image downloaded, done.");
                        return;
                    }
                    Log.d("mdxui", "Same video. Image was not know before, update UI...");
                    MdxPlaycardActivity.this.mBoxartDownloaded = false;
                    MdxPlaycardActivity.this.populateUi(true);
                }
                else {
                    Log.d("mdxui", "Aasset is null, use known playable.");
                    if (videoDetail == null || videoDetail.getPlayableId() == null || MdxPlaycardActivity.this.mPlayable == null || !videoDetail.getPlayableId().equals(MdxPlaycardActivity.this.mPlayable.getPlayableId())) {
                        Log.d("mdxui", "Different video, update UI...");
                        MdxPlaycardActivity.this.mPlayable = videoDetail;
                        MdxPlaycardActivity.this.mBoxartDownloaded = false;
                        MdxPlaycardActivity.this.populateUi(true);
                        return;
                    }
                    if (MdxPlaycardActivity.this.mBoxartDownloaded) {
                        Log.d("mdxui", "Same video. Image was not know before, update UI...");
                        MdxPlaycardActivity.this.mBoxartDownloaded = false;
                        MdxPlaycardActivity.this.populateUi(true);
                        return;
                    }
                    Log.d("mdxui", "Same video is already playing, image downloaded, do nothing...");
                }
            }
        };
        this.onEverySecond = new Runnable() {
            @Override
            public void run() {
                if (MdxPlaycardActivity.this.destroyed() || MdxPlaycardActivity.this.mState.draggingInProgress) {
                    Log.d("mdxui", "METADATA exit");
                    return;
                }
                final long n = System.currentTimeMillis() - MdxPlaycardActivity.this.mTimelineUpdateThreadFiredTime;
                if (MdxPlaycardActivity.this.mTimelineUpdateThreadFiredTime > 0L && n > 0L) {
                    MdxPlaycardActivity.access$2214(MdxPlaycardActivity.this, n);
                    final int n2 = (int)MdxPlaycardActivity.this.mSimulatedCurrentTimelinePosition / 1000;
                    final int max = MdxPlaycardActivity.this.mScreen.getBottomPanel().getTimeline().getMax();
                    if (Log.isLoggable("mdxui", 3)) {
                        Log.d("mdxui", "Faked update to position " + n2 + " in sec for simulated " + MdxPlaycardActivity.this.mSimulatedCurrentTimelinePosition + " in ms. Duration is " + max);
                    }
                    MdxPlaycardActivity.this.setProgress(n2, max);
                }
                MdxPlaycardActivity.this.mTimelineUpdateThreadFiredTime = System.currentTimeMillis();
                MdxPlaycardActivity.this.mHandler.postDelayed(MdxPlaycardActivity.this.onEverySecond, 1000L);
            }
        };
        this.mSocialProviderCallback = new Social.SocialProviderCallback() {
            @Override
            public void doNotShare() {
                MdxPlaycardActivity.this.mSocialDoNotShare = true;
                if (MdxPlaycardActivity.this.mServiceManager == null || !MdxPlaycardActivity.this.mServiceManager.isReady()) {
                    Log.e("mdxui", "Service manager is NOT ready. This should NOT happen!");
                }
                else if (!MdxPlaycardActivity.this.destroyed()) {
                    final VideoDetails access$100 = MdxPlaycardActivity.this.mPlayable;
                    if (access$100 == null) {
                        Log.e("mdxui", "Video detail is missing. This should NOT happen!");
                        return;
                    }
                    MdxPlaycardActivity.this.mScreen.getTopPanel().getSocial().changeActionState(false);
                    MdxPlaycardActivity.this.mServiceManager.hideVideo(access$100.getPlayableId(), new SimpleManagerCallback() {
                        @Override
                        public void onVideoHide(final int n) {
                            if (Log.isLoggable("mdxui", 3)) {
                                Log.d("mdxui", "Video is hidden status code " + n);
                            }
                        }
                    });
                    MdxPlaycardActivity.this.mHandler.postDelayed((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (MdxPlaycardActivity.this.destroyed()) {
                                return;
                            }
                            Log.d("mdxui", "Hide social all");
                            final Social social = MdxPlaycardActivity.this.mScreen.getTopPanel().getSocial();
                            social.setSharingDisabled(true);
                            social.hide();
                        }
                    }, 3000L);
                }
            }
            
            @Override
            public void extendTimeoutTimer() {
                MdxPlaycardActivity.this.extendTimeoutTimer();
            }
        };
    }
    
    static /* synthetic */ long access$2214(final MdxPlaycardActivity mdxPlaycardActivity, long mSimulatedCurrentTimelinePosition) {
        mSimulatedCurrentTimelinePosition += mdxPlaycardActivity.mSimulatedCurrentTimelinePosition;
        return mdxPlaycardActivity.mSimulatedCurrentTimelinePosition = mSimulatedCurrentTimelinePosition;
    }
    
    private Asset createAssetFromIntent() {
        final Intent intent = this.getIntent();
        if (intent == null) {
            Log.d("mdxui", "No new request to start playback, check if remote playback is in progress");
            return null;
        }
        final Parcelable parcelableExtra = intent.getParcelableExtra("PAYLOAD");
        if (parcelableExtra != null && !(parcelableExtra instanceof Asset)) {
            Log.e("mdxui", "This should NEVER happen, payload is not Asset!");
            return null;
        }
        if (parcelableExtra != null && parcelableExtra instanceof Asset) {
            Log.d("mdxui", "Payload is Asset...");
            return (Asset)parcelableExtra;
        }
        final Asset fromIntent = Asset.fromIntent(intent);
        if (!fromIntent.isEmpty()) {
            Log.d("mdxui", "Payload not found in parcelable but intent contained Asset data...");
            return fromIntent;
        }
        Log.d("mdxui", "Intent has no Asset data.");
        return null;
    }
    
    private PlaycardScreen createScreen() {
        final PlaycardScreen.Listeners listeners = new PlaycardScreen.Listeners();
        listeners.videoPositionListener = this.videoPositionListener;
        listeners.playPauseListener = this.playPauseListener;
        listeners.audioPositionListener = this.audioPositionListener;
        listeners.skipBackListener = this.skipBackListener;
        listeners.stopListener = this.stopListener;
        listeners.episodeSelectorListener = this.episodesListener;
        listeners.ratingListener = this.ratingListener;
        return new PlaycardScreen(this, listeners);
    }
    
    private static Intent createShowIntent(final Context context) {
        return new Intent(context, (Class)MdxPlaycardActivity.class);
    }
    
    public static Intent createShowIntentClearTop(final Context context) {
        return createShowIntent(context).setAction("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").addFlags(603979776);
    }
    
    private void downloadBoxart(final VideoDetails videoDetails) {
        String s2;
        if (this.isTablet() || DeviceUtils.isLandscape((Context)this)) {
            final String s = s2 = videoDetails.getHighResolutionLandscapeBoxArtUrl();
            if (Log.isLoggable("mdxui", 3)) {
                Log.d("mdxui", String.format("Device is in landscape or we are in tablet UI id:%s url:%s", videoDetails.getPlayableId(), s));
                s2 = s;
            }
        }
        else {
            final String s3 = s2 = videoDetails.getHighResolutionPortraitBoxArtUrl();
            if (Log.isLoggable("mdxui", 3)) {
                Log.d("mdxui", String.format("Device is in portrait id %s, url %s", videoDetails.getPlayableId(), s3));
                s2 = s3;
            }
        }
        String boxshotURL;
        if ((boxshotURL = s2) == null) {
            Log.e("mdxui", "HigRes box art is not available, default to LoRes");
            if ((boxshotURL = videoDetails.getBoxshotURL()) == null) {
                Log.e("mdxui", "LoRes boxart URL is also not available. Stop.");
                return;
            }
        }
        if (Log.isLoggable("mdxui", 3)) {
            Log.d("mdxui", "Go to download image " + boxshotURL);
        }
        this.mBoxartDownloaded = true;
        this.mServiceManager.getImageLoader().showImg(this.mScreen.getMainImage(), boxshotURL, IClientLogging.AssetType.boxArt, "", false, false);
    }
    
    private ByteBuffer getBifFrame(final int n) {
        if (ServiceManagerUtils.isMdxAgentAvailable(this.mServiceManager)) {
            return this.mServiceManager.getMdx().getBifFrame(n * 1000);
        }
        return null;
    }
    
    private int getDentDelta(final int n) {
        return n * 2 / 100;
    }
    
    public static RatingDialogFrag.Rating getRating(final VideoDetails videoDetails, float value) {
        final float n = 0.0f;
        final float n2 = 0.0f;
        final RatingDialogFrag.Rating rating = new RatingDialogFrag.Rating();
        if (videoDetails.getUserRating() <= 0.0f && value <= 0.0f) {
            Log.d("mdxui", "User did not changed ratings before, use predicted rating");
            if (videoDetails.getPredictedRating() < 0.0f) {
                value = n2;
            }
            else {
                value = videoDetails.getPredictedRating();
            }
            rating.value = value;
            rating.user = false;
            return rating;
        }
        if (value > 0.0f && videoDetails.getUserRating() != value) {
            Log.d("mdxui", "User changed ratings, but video object is not updated on callback from web api, use user set rating");
            rating.value = value;
            rating.user = true;
            return rating;
        }
        Log.d("mdxui", "User changed rating before, use user rating");
        if (videoDetails.getUserRating() < 0.0f) {
            value = n;
        }
        else {
            value = videoDetails.getUserRating();
        }
        rating.value = value;
        rating.user = true;
        return rating;
    }
    
    public static String getVideoId(final VideoDetails videoDetails) {
        if (videoDetails instanceof EpisodeDetails) {
            Log.d("mdxui", "Episode, use show ID as video ID");
            return ((EpisodeDetails)videoDetails).getShowId();
        }
        Log.d("mdxui", "Movie, use movie ID as video ID");
        return videoDetails.getPlayableId();
    }
    
    private int getWindowFlags() {
        return 524288;
    }
    
    private boolean isSameVideoPlaybackPending() {
        final WebApiUtils.VideoIds videoIds = this.mServiceManager.getMdx().getVideoIds();
        if (videoIds == null) {
            return false;
        }
        if (Log.isLoggable("mdxui", 3)) {
            if (this.mCurrentPlayout != null) {
                Log.d("mdxui", "mCurrentPlayout.getPlayableId(): " + this.mCurrentPlayout.getPlayableId());
            }
            else {
                Log.d("mdxui", "mCurrentPlayout is null ");
            }
            Log.d("mdxui", "pendingVideo.episode: '" + videoIds.episode + "'");
            Log.d("mdxui", "pendingVideo.episodeId: '" + videoIds.episodeId + "'");
            Log.d("mdxui", "pendingVideo.catalogId: '" + videoIds.catalogId + "'");
        }
        if (videoIds.episode) {
            Log.d("mdxui", "Episode is pending");
            return String.valueOf(videoIds.episodeId).equals(this.mCurrentPlayout.getPlayableId());
        }
        Log.d("mdxui", "Movie is pending");
        return String.valueOf(videoIds.catalogId).equals(this.mCurrentPlayout.getPlayableId());
    }
    
    public static boolean isSameVideoPlaying(final IMdx mdx, final String s) {
        final VideoDetails videoDetail = mdx.getVideoDetail();
        if (Log.isLoggable("mdxui", 3)) {
            if (StringUtils.isNotEmpty(s)) {
                Log.d("mdxui", "mCurrentPlayout.getPlayableId(): " + s);
            }
            else {
                Log.d("mdxui", "mCurrentPlayout is empty");
            }
            if (videoDetail != null) {
                Log.d("mdxui", "currentVideo.getPlayableId(): " + videoDetail.getPlayableId());
            }
            else {
                Log.d("mdxui", "currentVideo is null ");
            }
        }
        if (videoDetail != null && videoDetail.getPlayableId() != null && videoDetail.getPlayableId().equals(s)) {
            Log.d("mdxui", "Same video is playing, just sync...");
            return true;
        }
        Log.d("mdxui", "Video is not currently playing or different video, start play if play is not already pending...");
        return false;
    }
    
    private boolean isSeekInSnapZone(final int n, final SeekBar seekBar, int dentDelta) {
        dentDelta = this.getDentDelta(dentDelta);
        final int timelineStartSeekPosition = this.mState.timelineStartSeekPosition;
        return n >= timelineStartSeekPosition - dentDelta && n <= timelineStartSeekPosition + dentDelta;
    }
    
    private static Language jsonToLanguage(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.d("mdxui", "Language can not be restored, json not found.");
            return null;
        }
        try {
            return Language.restoreLanguage(s);
        }
        catch (JSONException ex) {
            Log.e("mdxui", "Failed to restore language!", (Throwable)ex);
            return null;
        }
    }
    
    private static String languageToJSON(final Language language) {
        try {
            return language.toJsonString();
        }
        catch (JSONException ex) {
            Log.e("mdxui", "Failed to create JSON string from language! ", (Throwable)ex);
            return null;
        }
    }
    
    public static void playVideo(final Activity activity, final Playable playable, final PlayContext playContext) {
        playVideo(activity, Asset.create(playable, playContext));
    }
    
    public static void playVideo(final Activity activity, final Asset asset) {
        if (Log.isLoggable("mdxui", 3)) {
            Log.d("mdxui", "Asset to playback: " + asset);
        }
        final Intent showIntent = createShowIntent((Context)activity);
        showIntent.addFlags(131072);
        if (asset != null && !asset.isEmpty()) {
            asset.toIntent(showIntent);
        }
        activity.startActivity(showIntent);
    }
    
    private void populatePlayable(final Playable playable, final boolean b) {
        if (playable == null) {
            Log.e("mdxui", "Playable should NOT be null here!");
        }
        else {
            ThreadUtils.assertOnMain();
            if (b) {
                Log.d("mdxui", "Bookmark data already exist, do not overide it");
            }
            else {
                Log.d("mdxui", "Update bookmark");
            }
            if (Log.isLoggable("mdxui", 3)) {
                if (playable.isPlayableEpisode()) {
                    Log.d("mdxui", "Episode is playing, populate from " + playable);
                }
                else {
                    Log.d("mdxui", "Movie is playing, populate from " + playable);
                }
            }
            final TextView deviceTextView = this.mScreen.getDeviceTextView();
            final TextView titleTextView = this.mScreen.getTitleTextView();
            titleTextView.setVisibility(0);
            deviceTextView.setText((CharSequence)this.getString(2131493174, new Object[] { ServiceManagerUtils.getCurrentDeviceFriendlyName(this.mServiceManager) }));
            final TextView subtitleTextView = this.mScreen.getSubtitleTextView();
            if (playable.isPlayableEpisode()) {
                titleTextView.setText((CharSequence)playable.getParentTitle());
                subtitleTextView.setVisibility(0);
                subtitleTextView.setText((CharSequence)this.getString(2131493185, new Object[] { playable.getSeasonNumber(), playable.getEpisodeNumber(), playable.getTitle() }));
                Log.d("mdxui", "Subitle: " + (Object)subtitleTextView.getText());
            }
            else {
                subtitleTextView.setVisibility(8);
                titleTextView.setText((CharSequence)playable.getTitle());
            }
            if (Log.isLoggable("mdxui", 3)) {
                Log.d("mdxui", "Title: " + (Object)titleTextView.getText());
            }
            this.mScreen.getTopPanel().getSocial().setSharingDisabled(!playable.getFbDntShare());
            this.mSocialDoNotShare = !playable.getFbDntShare();
            if (playable.getFbDntShare()) {
                Log.d("mdxui", "Social should be shared!");
                this.mScreen.getTopPanel().getSocial().show();
            }
            this.mScreen.getTopPanel().getSocial().changeActionState(false);
            final int playableBookmarkPosition = playable.getPlayableBookmarkPosition();
            final int runtime = playable.getRuntime();
            if (Log.isLoggable("mdxui", 3)) {
                if (b) {
                    Log.d("mdxui", "Skiping bookmark update");
                }
                else {
                    Log.d("mdxui", "Update bookmark [sec] " + playableBookmarkPosition);
                }
                Log.d("mdxui", "Update duration [sec] " + runtime);
                Log.d("mdxui", "Do not share visible " + playable.getFbDntShare());
            }
            this.mScreen.getBottomPanel().initProgress(runtime);
            if (!b) {
                this.mScreen.getBottomPanel().setProgress(playableBookmarkPosition, runtime, true, true);
                this.mSimulatedCurrentTimelinePosition = playableBookmarkPosition * 1000;
            }
            this.mScreen.getTopPanel().setEpisodeReady(false);
            if (playable instanceof EpisodeDetails) {
                Log.d("mdxui", "Playable is instance of EpisodeDetails, episode data are available, get episodes");
                this.mServiceManager.fetchSeasonDetails(((EpisodeDetails)playable).getSeasonId(), new FetchSeasonDetailsCallback());
            }
            else {
                Log.d("mdxui", "Not instance of EpisodeDetails, do nothing");
            }
            if (playable instanceof VideoDetails) {
                Log.d("mdxui", "Playable is VideoDetails: box arts known");
                this.downloadBoxart((VideoDetails)playable);
            }
        }
    }
    
    private void putActivityOnTopOfLockScreen() {
        if (AndroidUtils.getAndroidVersion() < 16) {
            this.getWindow().setFlags(this.getWindowFlags(), this.getWindowFlags());
            return;
        }
        this.getWindow().addFlags(this.getWindowFlags());
    }
    
    public static void registerReceiver(final Activity activity, final BroadcastReceiver broadcastReceiver) {
        Log.d("mdxui", "Register receiver");
        final IntentFilter intentFilter = new IntentFilter("ui_rating");
        intentFilter.addAction("nflx_button_selected");
        intentFilter.addAction("nflx_button_canceled");
        intentFilter.addCategory("LocalIntentNflxUi");
        intentFilter.setPriority(999);
        try {
            LocalBroadcastManager.getInstance((Context)activity).registerReceiver(broadcastReceiver, intentFilter);
        }
        catch (Throwable t) {
            Log.e("mdxui", "Failed to register ", t);
        }
    }
    
    private void setProgress(final int n, final int n2) {
        if (!this.mState.draggingInProgress) {
            if (Log.isLoggable("mdxui", 3)) {
                Log.d("mdxui", "PA#setProgress:: Position: " + n + ", duration: " + n2);
            }
            this.mScreen.setProgress(n, n2, true, true);
        }
        else if (Log.isLoggable("mdxui", 5)) {
            Log.w("mdxui", "PA#setProgress:: Draging in progress " + n + ", duration: " + n2);
        }
    }
    
    public static int setProgressByBif(final SeekBar seekBar) {
        final int progress = seekBar.getProgress();
        final int progress2 = progress / 10 * 10;
        if (progress2 == progress) {
            if (Log.isLoggable("mdxui", 3)) {
                Log.d("mdxui", "Right on target, no need to ajust seekbar position " + progress + " [sec]");
            }
            return progress2;
        }
        if (Log.isLoggable("mdxui", 3)) {
            Log.d("mdxui", "Progres : " + progress + " [sec] vs. bif position " + progress2 + " [sec]");
        }
        seekBar.setProgress(progress2);
        return progress2;
    }
    
    private void setTargetSelection() {
        final Pair<String, String>[] targetList = this.mServiceManager.getMdx().getTargetList();
        if (targetList == null || targetList.length < 1) {
            Log.w("mdxui", "We lost all MDX targets! Nothing to initialize!");
            return;
        }
        this.mScreen.getTopPanel().setMdxTargetSelector(new MdxTargetSelection(targetList, this.mServiceManager.getMdx().getCurrentTarget()));
    }
    
    private void startSimulatedVideoPositionUpdate() {
        synchronized (this) {
            if (!this.destroyed()) {
                this.mHandler.removeCallbacks(this.onEverySecond);
                this.mTimelineUpdateThreadFiredTime = System.currentTimeMillis();
                this.mHandler.postDelayed(this.onEverySecond, 1000L);
                Log.d("mdxui", "===>> Screen update thread started");
            }
        }
    }
    
    private void stopSimulatedVideoPositionUpdate() {
        synchronized (this) {
            if (!this.destroyed()) {
                this.mHandler.removeCallbacks(this.onEverySecond);
                Log.d("mdxui", "===>> Screen update thread canceled");
            }
        }
    }
    
    private void syncPlayer() {
        final boolean b = true;
        boolean b2 = true;
        final VideoDetails videoDetail = this.mServiceManager.getMdx().getVideoDetail();
        if (this.mCurrentPlayout == null) {
            Log.d("mdxui", "Current playout asset is missing, ask MDX agent for status");
            if (videoDetail != null) {
                Log.d("mdxui", "Video metadata is available, sets it to UI");
                this.populateUi(false);
            }
            this.mPlayer.sync();
            return;
        }
        if (Log.isLoggable("mdxui", 3)) {
            Log.d("mdxui", "Current playout asset exist " + this.mCurrentPlayout + ", check if it is already playing");
            Log.d("mdxui", "Video that is currently playing " + videoDetail);
        }
        this.mScreen.setEpisodePlaying(this.mCurrentPlayout.isEpisode());
        if (isSameVideoPlaying(this.mServiceManager.getMdx(), this.mCurrentPlayout.getPlayableId())) {
            Log.d("mdxui", "Same video is playing, just sync...");
            if (this.mSimulatedCurrentTimelinePosition <= 0L) {
                b2 = false;
            }
            this.populateUi(b2);
            this.mPlayer.sync();
            return;
        }
        if (this.isSameVideoPlaybackPending()) {
            Log.d("mdxui", "Same video play is pending, just sync...");
            this.populateUi(this.mSimulatedCurrentTimelinePosition > 0L && b);
            this.mPlayer.sync();
            return;
        }
        Log.d("mdxui", "Video is not currently playing or different video, start play...");
        this.mScreen.getTopPanel().setEpisodeReady(false);
        this.populatePlayable(new TitlePlayable(this.mCurrentPlayout), false);
        this.mPlayer.play(this.mCurrentPlayout);
    }
    
    public static void unregisterReceiver(final Activity activity, final BroadcastReceiver broadcastReceiver) {
        try {
            LocalBroadcastManager.getInstance((Context)activity).unregisterReceiver(broadcastReceiver);
        }
        catch (Throwable t) {
            Log.e("mdxui", "Failed to unregister ", t);
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            private void restoreLanguage() {
                if (MdxPlaycardActivity.this.mSavedLanguage != null) {
                    Log.d("mdxui", "Restored language metadata found, restore");
                    MdxPlaycardActivity.this.mScreen.setLanguage(MdxPlaycardActivity.this.mSavedLanguage);
                    MdxPlaycardActivity.this.mSavedLanguage = null;
                    return;
                }
                Log.d("mdxui", "Restored language metadata not found, skip restore");
            }
            
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                if (Log.isLoggable("mdxui", 3)) {
                    Log.d("mdxui", "ServiceManager ready: " + n);
                }
                ThreadUtils.assertOnMain();
                MdxPlaycardActivity.this.mPlayer = new RemotePlayer(MdxPlaycardActivity.this, MdxPlaycardActivity.this.mRemotePlaybackCallback);
                MdxPlaycardActivity.this.mIsTablet = serviceManager.isTablet();
                MdxPlaycardActivity.this.mResources = PlayerScreenResourceHelper.newInstance(MdxPlaycardActivity.this.mIsTablet);
                if (MdxPlaycardActivity.this.mIsTablet) {
                    Log.d("mdxui", "Loading tablet layout");
                    MdxPlaycardActivity.this.setContentView(2130903095);
                }
                else {
                    Log.d("mdxui", "Loading phone layout");
                    MdxPlaycardActivity.this.setContentView(2130903094);
                }
                MdxPlaycardActivity.this.mServiceManager = serviceManager;
                if (!ServiceManagerUtils.isMdxAgentAvailable(MdxPlaycardActivity.this.mServiceManager)) {
                    Log.d("mdxui", "DESTROY: Unable to receive handle to mdx manager object, finishing activity ");
                    MdxPlaycardActivity.this.destroy();
                }
                MdxPlaycardActivity.this.mCurrentTarget = MdxPlaycardActivity.this.mServiceManager.getMdx().getCurrentTarget();
                if (Log.isLoggable("mdxui", 3)) {
                    Log.d("mdxui", "Current target is " + MdxPlaycardActivity.this.mCurrentTarget);
                }
                MdxPlaycardActivity.this.mScreen = MdxPlaycardActivity.this.createScreen();
                this.restoreLanguage();
                MdxPlaycardActivity.this.setTargetSelection();
                MdxPlaycardActivity.this.mScreen.getDeviceTextView().setText((CharSequence)MdxPlaycardActivity.this.getString(2131493174, new Object[] { ServiceManagerUtils.getCurrentDeviceFriendlyName(MdxPlaycardActivity.this.mServiceManager) }));
                MdxPlaycardActivity.this.mScreen.setBufferingVisibility(true);
                MdxPlaycardActivity.this.mScreen.changeActionState(false, false);
                Log.d("mdxui", "Synchronize playcard UI with MDX agent");
                MdxPlaycardActivity.this.syncPlayer();
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
                Log.e("mdxui", "DESTROY: NetflixService is NOT available!");
                MdxPlaycardActivity.this.destroy();
            }
        };
    }
    
    @Override
    public void destroy() {
        this.finish();
    }
    
    @Override
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.mdxKeyEventHandler.handleKeyEvent(keyEvent, this.mServiceManager, this.mPlayer) || super.dispatchKeyEvent(keyEvent);
    }
    
    void extendTimeoutTimer() {
        this.mState.lastActionTime = SystemClock.elapsedRealtime();
    }
    
    @Override
    public long getCurrentPositionMs() {
        return this.getSimulatedCurrentTimelinePosition();
    }
    
    public String getCurrentTarget() {
        return this.mCurrentTarget;
    }
    
    String getCurrentTitle() {
        if (this.mPlayable == null) {
            return "";
        }
        return this.mPlayable.getTitle();
    }
    
    @Override
    protected DataContext getDataContext() {
        DataContext dataContext = null;
        if (this.mCurrentPlayout != null) {
            dataContext = new DataContext();
            dataContext.setRow(this.mCurrentPlayout.getListPos());
            dataContext.setRank(this.mCurrentPlayout.getVideoPos());
            dataContext.setRequestId(this.mCurrentPlayout.getRequestId());
            dataContext.setTrackId(this.mCurrentPlayout.getTrackId());
        }
        return dataContext;
    }
    
    @Override
    public EpisodeRowListener getEpisodeRowListener() {
        return this;
    }
    
    public Language getLanguage() {
        return null;
    }
    
    @Override
    public PlayContext getPlayContext() {
        if (this.mCurrentPlayout == null) {
            return PlayContext.EMPTY_CONTEXT;
        }
        return this.mCurrentPlayout;
    }
    
    @Override
    public RemotePlayer getPlayer() {
        return this.mPlayer;
    }
    
    PlaycardScreen getScreen() {
        return this.mScreen;
    }
    
    long getSimulatedCurrentTimelinePosition() {
        return this.mSimulatedCurrentTimelinePosition;
    }
    
    Social.SocialProviderCallback getSocialProviderCallback() {
        return this.mSocialProviderCallback;
    }
    
    PlaycardWorkflowState getState() {
        return this.mState;
    }
    
    @Override
    public MdxTargetSelection getTargetSelection() {
        return this.mScreen.getTopPanel().getMdxTargetSelector();
    }
    
    public PlayerScreenResourceHelper getUiResources() {
        return this.mResources;
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.playback;
    }
    
    @Override
    public VideoDetails getVideoDetails() {
        return this.mPlayable;
    }
    
    @Override
    public String getVideoId() {
        if (this.mPlayable == null) {
            return null;
        }
        return this.mPlayable.getPlayableId();
    }
    
    @Override
    public int getVolumeAsPercent() {
        return this.mScreen.getTopPanel().getSound().getProgress();
    }
    
    @Override
    public void handleDialogButton(final String s, final String s2) {
        if (this.mdxErrorHandler.handleDialogButton(s, s2)) {
            return;
        }
        Log.e("mdxui", "Unknown dialog responding!");
    }
    
    @Override
    public void handleDialogCancel(final String s) {
        if (this.mdxErrorHandler.handleDialogCancel(s)) {
            return;
        }
        Log.e("mdxui", "Unknown dialog responding on user cancel!");
    }
    
    @Override
    public void handleUserRatingChange(final String s, final float mRating) {
        if (Log.isLoggable("mdxui", 3)) {
            Log.d("mdxui", "Change user settings for received video id: " + s + " to rating " + mRating);
        }
        final String videoId = getVideoId(this.mPlayable);
        if (s != null && s.equals(videoId)) {
            Log.d("mdxui", "This is rating for current playback, update it");
            this.mRating = mRating;
        }
        else {
            Log.w("mdxui", "This is not update for current playable!");
        }
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.w("mdxui", "Can't set rating because service man is null");
            return;
        }
        serviceManager.setVideoRating(videoId, (int)mRating, this.getPlayContext().getTrackId(), new SetVideoRatingCallback(this, mRating));
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public boolean isPlayingLocally() {
        return false;
    }
    
    @Override
    public boolean isPlayingRemotely() {
        return true;
    }
    
    public boolean isTablet() {
        return this.mIsTablet;
    }
    
    @Override
    public void notifyPlayingBackLocal() {
        this.finish();
    }
    
    @Override
    public void notifyPlayingBackRemote() {
    }
    
    @Override
    public void onActionExecuted() {
        Log.d("mdxui", "Remove dialog");
        this.removeDialogFrag();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.putActivityOnTopOfLockScreen();
        this.mState.reset();
        this.mCurrentPlayout = this.createAssetFromIntent();
        this.mdxErrorHandler = new MdxErrorHandler("mdxui", this, (MdxErrorHandler.ErrorHandlerCallbacks)this);
        this.mdxKeyEventHandler = new MdxKeyEventHandler((MdxKeyEventHandler.MdxKeyEventCallbacks)this);
        if (Log.isLoggable("mdxui", 2)) {
            final StringBuilder append = new StringBuilder().append("TRACK_ID: ");
            Serializable value;
            if (this.mCurrentPlayout == null) {
                value = "n/a";
            }
            else {
                value = this.mCurrentPlayout.getTrackId();
            }
            Log.v("mdxui", append.append(value).toString());
        }
        if (bundle != null) {
            this.mRating = bundle.getFloat("mdxui_rating");
            this.mSocialDoNotShare = bundle.getBoolean("mdxui_socialNotVisible");
            this.mSavedLanguage = jsonToLanguage(bundle.getString("mdxui_language"));
        }
        Log.d("mdxui", "onCreate done");
    }
    
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        return false;
    }
    
    @Override
    protected void onDestroy() {
        Log.d("mdxui", "====> Destroying MdxPlaycardActivity");
        if (this.mScreen != null) {
            this.mScreen.destroy();
        }
        this.mCurrentPlayout = null;
        final RemotePlayer mPlayer = this.mPlayer;
        if (mPlayer != null) {
            mPlayer.destroy();
        }
        super.onDestroy();
        Log.d("mdxui", "====> Destroying MdxPlaycard done");
    }
    
    @Override
    public void onEpisodeSelectedForPlayback(final EpisodeDetails episodeDetails, final PlayContext playContext) {
        this.removeDialogFrag();
        playVideo(this, episodeDetails, this.getPlayContext());
    }
    
    protected void onNewIntent(final Intent intent) {
        Log.d("mdxui", "onNewIntent");
        this.setIntent(intent);
        final Asset assetFromIntent = this.createAssetFromIntent();
        if (assetFromIntent != null) {
            this.mCurrentPlayout = assetFromIntent;
        }
        this.mRating = 0.0f;
        this.mSocialDoNotShare = false;
        this.mSeasonDetails = null;
        if (this.mServiceManager == null || !this.mServiceManager.isReady()) {
            Log.d("mdxui", "Service manager is NOT ready, wait for callback. Exit");
            return;
        }
        if (assetFromIntent == null) {
            Log.d("mdxui", "Asset is null or empty, sync to current playback if any...");
            this.syncPlayer();
            return;
        }
        if (Log.isLoggable("mdxui", 2)) {
            final StringBuilder append = new StringBuilder().append("TRACK_ID: ");
            Serializable value;
            if (this.mCurrentPlayout == null) {
                value = "n/a";
            }
            else {
                value = this.mCurrentPlayout.getTrackId();
            }
            Log.v("mdxui", append.append(value).toString());
        }
        Log.d("mdxui", "Start NEW play...");
        this.populatePlayable(new TitlePlayable(this.mCurrentPlayout), false);
        this.mScreen.setBufferingVisibility(true);
        this.mScreen.changeActionState(false, false);
        this.mPlayable = null;
        this.mScreen.setEpisodePlaying(this.mCurrentPlayout.isEpisode());
        this.mScreen.getTopPanel().setEpisodeReady(false);
        this.mPlayer.play(this.mCurrentPlayout);
        Log.d("mdxui", "onNewIntent done");
    }
    
    @Override
    public void onResponse(final String s) {
        if (Log.isLoggable("mdxui", 3)) {
            Log.d("mdxui", "User selected " + s);
        }
        this.mPlayer.sendDialogResponse(s);
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putFloat("mdxui_rating", this.mRating);
        bundle.putBoolean("mdxui_socialNotVisible", this.mSocialDoNotShare);
        if (this.mScreen == null) {
            Log.w("mdxui", "onSaveInstanceState:: Unable to save language, screen is null");
            return;
        }
        if (this.language != null) {
            final String languageToJSON = languageToJSON(this.language);
            if (Log.isLoggable("mdxui", 3)) {
                Log.d("mdxui", "onSaveInstanceState:: Saving language: " + languageToJSON);
            }
            if (StringUtils.isNotEmpty(languageToJSON)) {
                bundle.putString("mdxui_language", languageToJSON);
            }
            return;
        }
        Log.w("mdxui", "onSaveInstanceState:: Unable to save language, language object is null!");
    }
    
    public boolean onSearchRequested() {
        return false;
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("mdxui", "onStart");
        registerReceiver(this, this.dialogMessageReceiver);
    }
    
    @Override
    protected void onStop() {
        Log.d("mdxui", "MdxPlaycardActivity::onStop called");
        super.onStop();
        unregisterReceiver(this, this.dialogMessageReceiver);
        Log.d("mdxui", "MdxPlaycardActivity::onStop done");
    }
    
    @Override
    public void onVolumeSet(final int progress) {
        this.mScreen.getTopPanel().getSound().setProgress(progress);
    }
    
    void populateUi(final boolean b) {
        if (!ServiceManagerUtils.isMdxAgentAvailable(this.mServiceManager)) {
            Log.e("mdxui", "Service is not retrieved. We should NOT be here!");
            return;
        }
        this.populatePlayable(this.mPlayable = this.mServiceManager.getMdx().getVideoDetail(), b);
    }
    
    public void restorePlaybackAfterSnap() {
        Log.d("mdxui", "restorePlaybackAfterSnap.");
        if (!this.destroyed()) {
            this.mState.lastActionTime = SystemClock.elapsedRealtime();
        }
        this.mScreen.changeActionState(true, true);
    }
    
    @Override
    protected boolean showMdxInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSignOutInMenu() {
        return false;
    }
    
    public void stopRemotePlayback() {
        if (this.mPlayer != null) {
            this.mPlayer.stop(true);
            return;
        }
        this.destroy();
    }
    
    private class FetchSeasonDetailsCallback extends LoggingManagerCallback
    {
        public FetchSeasonDetailsCallback() {
            super("mdxui");
        }
        
        @Override
        public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final int n) {
            super.onSeasonDetailsFetched(seasonDetails, n);
            if (n != 0) {
                Log.w("mdxui", "SeasonsFetched:: Invalid status code");
                return;
            }
            if (seasonDetails == null) {
                Log.e("mdxui", "Requested season details is null!");
                return;
            }
            MdxPlaycardActivity.this.mSeasonDetails = seasonDetails;
            MdxPlaycardActivity.this.runOnUiThread((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (MdxPlaycardActivity.this.mSeasonDetails == null) {
                        Log.e("mdxui", "Season is null, should NOT happen!");
                        return;
                    }
                    MdxPlaycardActivity.this.mScreen.getTopPanel().setEpisodeReady(true);
                }
            });
        }
    }
    
    public static class SetVideoRatingCallback extends LoggingManagerCallback
    {
        private final NetflixActivity activity;
        private final float rating;
        
        public SetVideoRatingCallback(final NetflixActivity activity, final float rating) {
            super("mdxui");
            this.activity = activity;
            this.rating = rating;
        }
        
        @Override
        public void onVideoRatingSet(final int n) {
            super.onVideoRatingSet(n);
            if (this.activity.destroyed()) {
                return;
            }
            if (n != 0) {
                Log.w("mdxui", "Invalid status code failed");
                Toast.makeText((Context)this.activity, 2131493137, 1).show();
                Log.d("mdxui", "Report rate action ended");
                final LogUtils.LogReportErrorArgs logReportErrorArgs = new LogUtils.LogReportErrorArgs(n, ActionOnUIError.displayedError, "", null);
                LogUtils.reportRateActionEnded((Context)this.activity, logReportErrorArgs.getReason(), logReportErrorArgs.getError(), null, (int)this.rating);
                return;
            }
            Log.v("mdxui", "Rating has been updated ok");
            Toast.makeText((Context)this.activity, 2131493138, 1).show();
            LogUtils.reportRateActionEnded((Context)this.activity, IClientLogging.CompletionReason.success, null, null, (int)this.rating);
        }
    }
}
