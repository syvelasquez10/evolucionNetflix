// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.servicemgr.SeasonDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.Playable;
import android.view.View;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.VideoType;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.Fragment;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.widget.SeekBar;
import com.netflix.mediaclient.util.ThreadUtils;
import android.app.DialogFragment;
import android.app.Dialog;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Language;
import java.util.HashSet;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.Set;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.ui.details.EpisodeRowView;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class MdxMiniPlayerFrag extends NetflixFrag implements EpisodeRowListener, Callback, MdxTargetSelectionDialogInterface
{
    private static final boolean DISABLED = false;
    private static final String EXTRA_SAVED_POSITION_SECONDS = "saved_position_seconds";
    public static final boolean FORCE_SHOW_WITH_DUMMY_DATA = false;
    private static final String NOTIFY_SHOW_AND_DISABLE_INTENT = "com.netflix.mediaclient.ui.mdx.NOTIFY_SHOW_AND_DISABLE_INTENT";
    private static final long SEEKBAR_UPDATE_DELAY_MS = 1000L;
    private static final String TAG = "MdxMiniPlayerFrag";
    private static final Set<String> dontShareIdSet;
    private static final SharedState state;
    private NetflixActivity activity;
    private VideoDetails currentVideo;
    private final DialogMessageReceiver dialogMessageReceiver;
    private boolean draggingInProgress;
    private IMdx dummyMdx;
    private final Handler handler;
    private boolean isEndOfPlayback;
    private boolean isInBackground;
    private boolean isShowing;
    private LanguageSelector languageSelector;
    private final LanguageSelector.LanguageSelectorCallback languageSelectorCallback;
    private ServiceManager manager;
    private MdxErrorHandler mdxErrorHandler;
    private final MdxKeyEventHandler.MdxKeyEventCallbacks mdxKeyEventCallbacks;
    private MdxKeyEventHandler mdxKeyEventHandler;
    private final MdxMiniPlayerViews.MdxMiniPlayerViewCallbacks mdxMiniPlayerViewCallbacks;
    private String parentActivityClass;
    private RemotePlayer remotePlayer;
    private final RemotePlayer.RemoteTargetUiListener remoteTargetUiListener;
    private int savedPositionSeconds;
    private long simulatedCurrentTimelinePositionMs;
    private long simulatedVideoPositionTimeFiredMs;
    private final Runnable updateSeekBarRunnable;
    private MdxMiniPlayerViews views;
    
    static {
        state = new SharedState();
        dontShareIdSet = new HashSet<String>();
    }
    
    public MdxMiniPlayerFrag() {
        this.handler = new Handler();
        this.dialogMessageReceiver = new DialogMessageReceiver((DialogMessageReceiver.Callback)this);
        this.updateSeekBarRunnable = new Runnable() {
            @Override
            public void run() {
                if (MdxMiniPlayerFrag.this.activity.destroyed() || MdxMiniPlayerFrag.this.draggingInProgress) {
                    MdxMiniPlayerFrag.this.log("skipping seekbar update");
                    return;
                }
                final long n = System.currentTimeMillis() - MdxMiniPlayerFrag.this.simulatedVideoPositionTimeFiredMs;
                if (MdxMiniPlayerFrag.this.simulatedVideoPositionTimeFiredMs > 0L && n > 0L) {
                    MdxMiniPlayerFrag.access$914(MdxMiniPlayerFrag.this, n);
                    MdxMiniPlayerFrag.this.views.setProgress((int)MdxMiniPlayerFrag.this.simulatedCurrentTimelinePositionMs / 1000);
                }
                MdxMiniPlayerFrag.this.simulatedVideoPositionTimeFiredMs = System.currentTimeMillis();
                MdxMiniPlayerFrag.this.handler.postDelayed(MdxMiniPlayerFrag.this.updateSeekBarRunnable, 1000L);
            }
        };
        this.languageSelectorCallback = new LanguageSelector.LanguageSelectorCallback() {
            @Override
            public void languageChanged(final Language language, final boolean b) {
                Log.v("MdxMiniPlayerFrag", "Language changed via dialog: " + language);
                if (MdxMiniPlayerFrag.this.remotePlayer != null) {
                    MdxMiniPlayerFrag.this.remotePlayer.changeLanguage(language);
                    MdxMiniPlayerFrag.this.remotePlayer.requestAudioAndSubtitleData();
                }
                MdxMiniPlayerFrag.this.updateLanguage();
            }
            
            @Override
            public void updateDialog(final Dialog dialog) {
                MdxMiniPlayerFrag.this.log("Updating dialog");
                MdxMiniPlayerFrag.this.activity.updateVisibleDialog(dialog);
            }
            
            @Override
            public void userCanceled() {
                MdxMiniPlayerFrag.this.log("User canceled selection");
            }
            
            @Override
            public boolean wasPlaying() {
                return false;
            }
        };
        this.remoteTargetUiListener = new RemotePlayer.RemoteTargetUiListener() {
            private void handleSeekbarUpdate(final RemoteTargetState remoteTargetState) {
                if (!MdxMiniPlayerFrag.this.draggingInProgress && MdxMiniPlayerFrag.state.controlsEnabled) {
                    if (MdxMiniPlayerFrag.this.remotePlayer.getPositionInSeconds() >= 0) {
                        MdxMiniPlayerFrag.this.log("Update video seekbar - pos: " + remoteTargetState.positionInSeconds);
                        MdxMiniPlayerFrag.this.views.setProgress(remoteTargetState.positionInSeconds);
                    }
                    if (remoteTargetState.buffering || remoteTargetState.paused) {
                        MdxMiniPlayerFrag.this.stopSimulatedVideoPositionUpdate();
                        return;
                    }
                    if (!remoteTargetState.paused && MdxMiniPlayerFrag.this.isShowing()) {
                        MdxMiniPlayerFrag.this.startSimulatedVideoPositionUpdate(remoteTargetState.positionInSeconds);
                    }
                }
            }
            
            private boolean isErrorRequireDisableControl(final int n) {
                return n >= 100 && n < 300;
            }
            
            @Override
            public void cancelDialog() {
                if (MdxMiniPlayerFrag.this.activity.destroyed()) {
                    return;
                }
                if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
                    MdxMiniPlayerFrag.this.log("cancelDialog");
                }
                MdxMiniPlayerFrag.this.activity.removeVisibleDialog();
            }
            
            @Override
            public void endOfPlayback() {
                if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
                    MdxMiniPlayerFrag.this.log("endOfPlayback");
                }
                MdxMiniPlayerFrag.this.isEndOfPlayback = true;
                MdxMiniPlayerFrag.this.views.setControlsEnabled(false);
                MdxMiniPlayerFrag.this.activity.notifyMdxEndOfPlayback();
                MdxMiniPlayerFrag.state.reset();
                MdxMiniPlayerFrag.this.currentVideo = null;
            }
            
            @Override
            public void error(final int n, final String s) {
                if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
                    MdxMiniPlayerFrag.this.log("error - code: " + n + ", descrip: " + s);
                }
                MdxMiniPlayerFrag.this.isEndOfPlayback = true;
                if (MdxMiniPlayerFrag.this.isShowing()) {
                    MdxMiniPlayerFrag.this.mdxErrorHandler.handleMdxError(n, s);
                }
                if (this.isErrorRequireDisableControl(n)) {
                    MdxMiniPlayerFrag.this.views.setControlsEnabled(false);
                    MdxMiniPlayerFrag.this.views.enableMdxMenu();
                    MdxMiniPlayerFrag.this.activity.notifyMdxEndOfPlayback();
                }
            }
            
            @Override
            public void mdxStateChanged(final boolean b) {
                if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
                    MdxMiniPlayerFrag.this.log("mdxStateChanged, ready: " + b);
                }
            }
            
            @Override
            public void showDialog(final RemoteDialog remoteDialog) {
                if (MdxMiniPlayerFrag.this.activity.destroyed()) {
                    return;
                }
                if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
                    MdxMiniPlayerFrag.this.log("showDialog, " + remoteDialog.toString());
                }
                final ShowMessageDialogFrag instance = ShowMessageDialogFrag.newInstance(remoteDialog);
                instance.onManagerReady(MdxMiniPlayerFrag.this.manager, 0);
                instance.setCancelable(true);
                MdxMiniPlayerFrag.this.activity.showDialog(instance);
            }
            
            @Override
            public void targetListChanged() {
                MdxMiniPlayerFrag.this.log("targetListChanged");
            }
            
            @Override
            public void updateDuration(final int progressMax) {
                if (MdxMiniPlayerFrag.this.activity.destroyed()) {
                    return;
                }
                MdxMiniPlayerFrag.this.log("updateDuration, " + progressMax);
                if (progressMax > 0) {
                    MdxMiniPlayerFrag.this.views.setProgressMax(progressMax);
                    return;
                }
                Log.w("MdxMiniPlayerFrag", "We received an invalid duration - ignoring");
            }
            
            @Override
            public void updateLanguage(final Language language) {
                if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
                    MdxMiniPlayerFrag.this.log("updateLanguage from remote player: " + language);
                }
                MdxMiniPlayerFrag.this.updateLanguage();
            }
            
            @Override
            public void updateTargetCapabilities(final MdxTargetCapabilities mdxTargetCapabilities) {
                if (MdxMiniPlayerFrag.this.activity.destroyed()) {
                    return;
                }
                if (mdxTargetCapabilities == null) {
                    Log.w("MdxMiniPlayerFrag", "Capabilities is null!");
                    MdxMiniPlayerFrag.this.updateVolumeState(false);
                    return;
                }
                if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
                    MdxMiniPlayerFrag.this.log("updateTargetCapabilities, " + mdxTargetCapabilities.toString());
                }
                MdxMiniPlayerFrag.this.updateVolumeState(mdxTargetCapabilities.isVolumeControl());
            }
            
            @Override
            public void updateUi(final RemoteTargetState remoteTargetState) {
                final boolean b = false;
                ThreadUtils.assertOnMain();
                if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
                    MdxMiniPlayerFrag.this.log("updateUi, " + remoteTargetState.toString());
                }
                MdxMiniPlayerFrag.this.isEndOfPlayback = false;
                MdxMiniPlayerFrag.state.mostRecentVolume = remoteTargetState.volume;
                final MdxMiniPlayerViews access$300 = MdxMiniPlayerFrag.this.views;
                boolean controlsEnabled = b;
                if (!remoteTargetState.buffering) {
                    controlsEnabled = b;
                    if (remoteTargetState.showMiniPlayer) {
                        controlsEnabled = true;
                    }
                }
                access$300.setControlsEnabled(controlsEnabled);
                MdxMiniPlayerFrag.this.updateVisibility(remoteTargetState.showMiniPlayer, remoteTargetState.paused);
                this.handleSeekbarUpdate(remoteTargetState);
            }
            
            @Override
            public void updateVideoMetadata() {
                MdxMiniPlayerFrag.this.log("updateVideoMetadata");
                if (MdxMiniPlayerFrag.this.manager == null) {
                    return;
                }
                final IMdx mdx = MdxMiniPlayerFrag.this.mdxMiniPlayerViewCallbacks.getMdx();
                final VideoDetails videoDetail = mdx.getVideoDetail();
                if (MdxMiniPlayerFrag.this.currentVideo != null && MdxUtils.isSameVideoPlaying(mdx, MdxMiniPlayerFrag.this.currentVideo.getPlayableId())) {
                    MdxMiniPlayerFrag.this.log("Same video is already playing, doing nothing");
                    return;
                }
                if (videoDetail == null) {
                    Log.w("MdxMiniPlayerFrag", "null video details provided by mdx agent");
                    return;
                }
                MdxMiniPlayerFrag.this.log("Different video, updating to: " + videoDetail.getTitle());
                MdxMiniPlayerFrag.this.updateVideoMetadata(videoDetail);
            }
        };
        this.mdxMiniPlayerViewCallbacks = new MdxMiniPlayerViews.MdxMiniPlayerViewCallbacks() {
            private long startTrackingTouchTime;
            
            @Override
            public float getCurrentRating() {
                return MdxMiniPlayerFrag.state.currRating;
            }
            
            @Override
            public VideoDetails getCurrentVideo() {
                return MdxMiniPlayerFrag.this.currentVideo;
            }
            
            @Override
            public ServiceManager getManager() {
                return MdxMiniPlayerFrag.this.manager;
            }
            
            @Override
            public IMdx getMdx() {
                return MdxMiniPlayerFrag.this.manager.getMdx();
            }
            
            @Override
            public boolean isEpisodeReady() {
                return MdxMiniPlayerFrag.state.isEpisodeReady;
            }
            
            @Override
            public boolean isLanguageReady() {
                Language language;
                if (MdxMiniPlayerFrag.this.remotePlayer == null) {
                    language = null;
                }
                else {
                    language = MdxMiniPlayerFrag.this.remotePlayer.getLanguage();
                }
                return language != null && language.isLanguageSwitchEnabled();
            }
            
            @Override
            public boolean isPanelExpanded() {
                return MdxMiniPlayerFrag.this.activity.isPanelExpanded();
            }
            
            @Override
            public boolean isPlayingRemotely() {
                return MdxMiniPlayerFrag.this.isPlayingRemotely();
            }
            
            @Override
            public boolean isRemotePlayerReady() {
                return MdxMiniPlayerFrag.this.remotePlayer != null;
            }
            
            @Override
            public boolean isVideoUnshared() {
                return MdxMiniPlayerFrag.state.isVideoUnshared;
            }
            
            @Override
            public void notifyControlsEnabled(final boolean controlsEnabled) {
                MdxMiniPlayerFrag.state.controlsEnabled = controlsEnabled;
            }
            
            @Override
            public void onPauseClicked() {
                if (MdxMiniPlayerFrag.this.remotePlayer != null) {
                    MdxMiniPlayerFrag.this.remotePlayer.pause();
                }
            }
            
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
            }
            
            @Override
            public void onResumeClicked() {
                if (MdxMiniPlayerFrag.this.remotePlayer != null) {
                    MdxMiniPlayerFrag.this.remotePlayer.resume();
                }
            }
            
            @Override
            public void onShowLanguageSelectorDialog() {
                if (MdxMiniPlayerFrag.this.remotePlayer != null) {
                    final Language language = MdxMiniPlayerFrag.this.remotePlayer.getLanguage();
                    MdxMiniPlayerFrag.this.log("Displaying language dialog, language: " + language);
                    MdxMiniPlayerFrag.this.languageSelector.display(language);
                }
            }
            
            @Override
            public void onSkipBackClicked() {
                if (MdxMiniPlayerFrag.this.remotePlayer != null) {
                    MdxMiniPlayerFrag.this.remotePlayer.skipBackThirtySeconds();
                    MdxMiniPlayerFrag.this.stopSimulatedVideoPositionUpdate();
                }
            }
            
            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {
                Log.v("MdxMiniPlayerFrag", "onStartTrackingTouch");
                MdxMiniPlayerFrag.this.draggingInProgress = true;
                this.startTrackingTouchTime = System.nanoTime();
                MdxMiniPlayerFrag.this.stopSimulatedVideoPositionUpdate();
            }
            
            @Override
            public void onStopClicked() {
                if (MdxMiniPlayerFrag.this.remotePlayer != null) {
                    MdxMiniPlayerFrag.this.remotePlayer.stop(false);
                }
            }
            
            @Override
            public void onStopTrackingTouch(final SeekBar progressByBif, final boolean b) {
                Log.v("MdxMiniPlayerFrag", "onStopTrackingTouch, pos: " + progressByBif.getProgress());
                MdxMiniPlayerFrag.this.draggingInProgress = false;
                if (b) {
                    final int n = (int)((System.nanoTime() - this.startTrackingTouchTime) / 1000000000L);
                    final int progress = progressByBif.getProgress() + n;
                    if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
                        Log.v("MdxMiniPlayerFrag", "Seconds elapsed during seek (back to snap position): " + n + ", new time: " + progress);
                    }
                    MdxMiniPlayerFrag.this.views.setProgress(progress);
                    MdxMiniPlayerFrag.this.startSimulatedVideoPositionUpdate(progress);
                    return;
                }
                Log.v("MdxMiniPlayerFrag", "Seeking...");
                MdxMiniPlayerFrag.this.views.setControlsEnabled(false);
                MdxMiniPlayerFrag.this.remotePlayer.seek(MdxUtils.setProgressByBif(progressByBif));
            }
        };
        this.mdxKeyEventCallbacks = new MdxKeyEventHandler.MdxKeyEventCallbacks() {
            @Override
            public int getVolumeAsPercent() {
                return MdxMiniPlayerFrag.this.getVolume();
            }
            
            @Override
            public void onVolumeSet(final int mostRecentVolume) {
                MdxMiniPlayerFrag.state.mostRecentVolume = mostRecentVolume;
            }
        };
    }
    
    static /* synthetic */ long access$914(final MdxMiniPlayerFrag mdxMiniPlayerFrag, long simulatedCurrentTimelinePositionMs) {
        simulatedCurrentTimelinePositionMs += mdxMiniPlayerFrag.simulatedCurrentTimelinePositionMs;
        return mdxMiniPlayerFrag.simulatedCurrentTimelinePositionMs = simulatedCurrentTimelinePositionMs;
    }
    
    private void hideDialogFragmentIfNecessary() {
        final DialogFragment dialogFragment = this.activity.getDialogFragment();
        if (dialogFragment instanceof MdxMiniPlayerDialog) {
            Log.d("MdxMiniPlayerFrag", "MDX mini player dialog frag currently shown - hiding");
            dialogFragment.dismiss();
        }
    }
    
    private void hideSelf() {
        synchronized (this) {
            this.log("hideSelf()");
            this.isShowing = false;
            MdxMiniPlayerFrag.state.shouldShowSelf = false;
            this.stopSimulatedVideoPositionUpdate();
            this.views.updateMdxMenu();
            if (this.isInBackground || AndroidUtils.isActivityFinishedOrDestroyed(this.activity)) {
                this.log("Frag is in BG - should just hide self on resume");
            }
            else {
                this.hideSelfInternal();
            }
        }
    }
    
    @SuppressLint({ "CommitTransaction" })
    private void hideSelfInternal() {
        synchronized (this) {
            this.log("Hiding MDX Player frag (internal)");
            final FragmentManager fragmentManager = this.getFragmentManager();
            fragmentManager.beginTransaction().hide((Fragment)this).commit();
            fragmentManager.executePendingTransactions();
            this.hideDialogFragmentIfNecessary();
            this.hideVisibleDialogIfNecessary();
            this.activity.notifyMdxMiniPlayerHidden();
        }
    }
    
    private void hideVisibleDialogIfNecessary() {
        if (this.activity.getVisibleDialog() instanceof MdxMiniPlayerDialog) {
            Log.d("MdxMiniPlayerFrag", "MDX dialog currently shown - hiding");
            this.activity.removeVisibleDialog();
        }
    }
    
    private void initMdxComponents() {
        this.log("initMdxComponents()");
        final IMdx mdx = this.mdxMiniPlayerViewCallbacks.getMdx();
        if (mdx != null) {
            final VideoDetails videoDetail = mdx.getVideoDetail();
            if (videoDetail != null) {
                this.updateVideoMetadata(videoDetail);
                this.views.setControlsEnabled(MdxMiniPlayerFrag.state.controlsEnabled);
                this.updateVisibility(MdxMiniPlayerFrag.state.shouldShowSelf, mdx.isPaused());
            }
            this.remotePlayer = new RemotePlayer(this.activity, this.remoteTargetUiListener);
            if (this.isShowing()) {
                if (MdxMiniPlayerFrag.state.controlsEnabled) {
                    this.log("Controls are enabled & mini player is showing. Requesting subs and dubs...");
                    this.remotePlayer.requestAudioAndSubtitleData();
                }
                this.log("Syncing with remote player...");
                this.remotePlayer.sync();
            }
        }
        this.languageSelector = LanguageSelector.createInstance(this.activity, DeviceUtils.isTabletByContext((Context)this.activity), this.languageSelectorCallback);
        this.views.onManagerReady(this.manager);
    }
    
    private void log(final String s) {
        if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
            Log.v("MdxMiniPlayerFrag", this.parentActivityClass + ": " + s);
        }
    }
    
    public static void sendShowAndDisableIntent(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.ui.mdx.NOTIFY_SHOW_AND_DISABLE_INTENT"));
    }
    
    private void showAndDisable() {
        this.showSelf();
        this.stopSimulatedVideoPositionUpdate();
        this.views.setControlsEnabled(false);
    }
    
    private void showSelf() {
        synchronized (this) {
            this.log("showSelf()");
            this.isShowing = true;
            MdxMiniPlayerFrag.state.shouldShowSelf = true;
            this.views.updateMdxMenu();
            if (this.isInBackground || AndroidUtils.isActivityFinishedOrDestroyed(this.activity)) {
                this.log("Frag is in BG - should just show self on resume");
            }
            else {
                this.log("Showing MDX Player frag");
                final FragmentManager fragmentManager = this.getFragmentManager();
                fragmentManager.beginTransaction().show((Fragment)this).commit();
                fragmentManager.executePendingTransactions();
                this.activity.notifyMdxMiniPlayerShown();
            }
        }
    }
    
    private void startSimulatedVideoPositionUpdate(final long n) {
        if (!this.activity.destroyed()) {
            this.handler.removeCallbacks(this.updateSeekBarRunnable);
            this.simulatedCurrentTimelinePositionMs = n * 1000L;
            this.simulatedVideoPositionTimeFiredMs = System.currentTimeMillis();
            this.handler.postDelayed(this.updateSeekBarRunnable, 1000L);
            this.log("Simulated position update +started+");
        }
    }
    
    private void stopSimulatedVideoPositionUpdate() {
        if (!this.activity.destroyed()) {
            this.handler.removeCallbacks(this.updateSeekBarRunnable);
            this.log("Simulated position update -stopped-");
        }
    }
    
    private void updateLanguage() {
        if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
            Log.v("MdxMiniPlayerFrag", "updateLanguage()");
        }
        this.views.setLanguageButtonEnabled(this.mdxMiniPlayerViewCallbacks.isLanguageReady());
    }
    
    private void updateVideoMetadata(final VideoDetails currentVideo) {
        final boolean b = true;
        this.currentVideo = currentVideo;
        this.log("Updating metadata: " + this.currentVideo + ", hash: " + this.currentVideo.hashCode());
        if (this.currentVideo.getType() == VideoType.EPISODE) {
            this.views.updateTitleText(this.currentVideo.getParentTitle());
            this.views.updateSubtitleText(this.activity.getString(2131493249, new Object[] { this.currentVideo.getSeasonNumber(), this.currentVideo.getEpisodeNumber(), this.currentVideo.getTitle() }));
        }
        else {
            this.views.updateTitleText(this.currentVideo.getTitle());
            this.views.updateSubtitleText("");
        }
        this.views.updateDeviceNameText(ServiceManagerUtils.getCurrentDeviceFriendlyName(this.manager));
        this.views.setEpisodesButtonVisibile(this.currentVideo.getType() != VideoType.MOVIE);
        this.log("Setting seek bar max: " + this.currentVideo.getRuntime());
        this.views.setProgressMax(this.currentVideo.getRuntime());
        int positionInSeconds;
        if (this.remotePlayer == null) {
            positionInSeconds = 0;
        }
        else {
            positionInSeconds = this.remotePlayer.getPositionInSeconds();
        }
        this.log(String.format("updating seek pos - remote pos: %d, playable bookmark pos: %d, saved pos: %d", positionInSeconds, this.currentVideo.getPlayableBookmarkPosition(), this.savedPositionSeconds));
        final int savedPositionSeconds = this.savedPositionSeconds;
        this.savedPositionSeconds = -1;
        int playableBookmarkPosition = savedPositionSeconds;
        if (savedPositionSeconds <= 0) {
            int positionInSeconds2;
            if (this.remotePlayer == null) {
                positionInSeconds2 = 0;
            }
            else {
                positionInSeconds2 = this.remotePlayer.getPositionInSeconds();
            }
            playableBookmarkPosition = positionInSeconds2;
            if (positionInSeconds2 <= 0) {
                playableBookmarkPosition = this.currentVideo.getPlayableBookmarkPosition();
            }
        }
        if (playableBookmarkPosition > 0) {
            this.log("Setting seek progress: " + playableBookmarkPosition);
            this.views.setProgress(playableBookmarkPosition);
        }
        this.views.updateImage(this.currentVideo);
        if (this.currentVideo instanceof EpisodeDetails) {
            this.log("Video is instance of EpisodeDetails, fetching episodes...");
            this.manager.fetchSeasonDetails(((EpisodeDetails)this.currentVideo).getSeasonId(), new FetchSeasonDetailsCallback());
        }
        else {
            this.log("Video is not instance of EpisodeDetails");
        }
        MdxMiniPlayerFrag.state.isVideoUnshared = (this.currentVideo.getFbDntShare() || MdxMiniPlayerFrag.dontShareIdSet.contains(this.currentVideo.getPlayableId()));
        this.views.setSharingButtonVisibility(!MdxMiniPlayerFrag.state.isVideoUnshared && b);
        this.log("updateVideoMetadata, unshared state is: " + MdxMiniPlayerFrag.state.isVideoUnshared);
        this.updateLanguage();
    }
    
    private void updateVisibility(final boolean b, final boolean b2) {
        if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
            this.log("updateVisibility, frag isVisible: " + this.isShowing + ", show: " + b + ", paused: " + b2);
        }
        if (this.isShowing != b) {
            if (b) {
                this.showSelf();
            }
            else {
                this.hideSelf();
            }
        }
        this.views.updatePlayPauseButton(b2);
        this.views.setVolumeButtonVisibility(MdxMiniPlayerFrag.state.isVolumeEnabled);
    }
    
    private void updateVolumeState(final boolean b) {
        MdxMiniPlayerFrag.state.isVolumeEnabled = b;
        this.views.setVolumeButtonVisibility(b);
    }
    
    public void attachMenuItem(final MdxMenu mdxMenu) {
        this.views.attachMenuItem(mdxMenu);
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.mdxKeyEventHandler.handleKeyEvent(keyEvent, this.manager, this.remotePlayer);
    }
    
    @Override
    public long getCurrentPositionMs() {
        return this.simulatedCurrentTimelinePositionMs;
    }
    
    @Override
    public PlayContext getPlayContext() {
        return PlayContext.EMPTY_CONTEXT;
    }
    
    @Override
    public RemotePlayer getPlayer() {
        return this.remotePlayer;
    }
    
    public View getSlidingPanelDragView() {
        return this.views.getSlidingPanelDragView();
    }
    
    @Override
    public MdxTargetSelection getTargetSelection() {
        final IMdx mdx = this.mdxMiniPlayerViewCallbacks.getMdx();
        return new MdxTargetSelection(mdx.getTargetList(), mdx.getCurrentTarget());
    }
    
    @Override
    public Playable getVideoDetails() {
        if (this.isPlayingRemotely()) {
            return this.currentVideo;
        }
        return null;
    }
    
    public int getVolume() {
        return MdxMiniPlayerFrag.state.mostRecentVolume;
    }
    
    @Override
    public void handleDialogButton(final String s, final String s2) {
        if (this.mdxErrorHandler.handleDialogButton(s, s2)) {}
    }
    
    @Override
    public void handleDialogCancel(final String s) {
        if (this.mdxErrorHandler.handleDialogCancel(s)) {}
    }
    
    @Override
    public void handleUserRatingChange(final String s, final float currRating) {
        if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
            this.log("Change user settings for received video id: " + s + " to rating: " + currRating);
        }
        final String videoId = MdxUtils.getVideoId(this.currentVideo);
        if (s != null && s.equals(videoId)) {
            this.log("This is rating for current playback, update it");
            MdxMiniPlayerFrag.state.currRating = currRating;
        }
        else {
            this.log("This is not update for current playable!");
        }
        if (this.manager == null) {
            this.log("Can't set rating because service man is null");
            return;
        }
        this.manager.setVideoRating(videoId, (int)MdxMiniPlayerFrag.state.currRating, PlayContext.EMPTY_CONTEXT.getTrackId(), new MdxUtils.SetVideoRatingCallback(this.activity, MdxMiniPlayerFrag.state.currRating));
    }
    
    public void hide() {
        this.hideSelf();
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    public boolean isMdxMenuEnabled() {
        return this.views.computeMdxMenuEnabled(MdxMiniPlayerFrag.state.controlsEnabled);
    }
    
    @Override
    public boolean isPlayingLocally() {
        return false;
    }
    
    @Override
    public boolean isPlayingRemotely() {
        return this.isShowing;
    }
    
    public boolean isShowing() {
        return this.isShowing;
    }
    
    @Override
    public void notifyPlayingBackLocal() {
        this.hideSelf();
    }
    
    @Override
    public void notifyPlayingBackRemote() {
        this.views.setControlsEnabled(false);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.parentActivityClass = this.getActivity().getClass().getSimpleName();
        this.activity = (NetflixActivity)this.getActivity();
        this.log("onCreate()");
        int int1;
        if (bundle == null) {
            int1 = -1;
        }
        else {
            int1 = bundle.getInt("saved_position_seconds", -1);
        }
        this.savedPositionSeconds = int1;
        this.activity.registerReceiverWithAutoUnregister(new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                MdxMiniPlayerFrag.this.showAndDisable();
            }
        }, "com.netflix.mediaclient.ui.mdx.NOTIFY_SHOW_AND_DISABLE_INTENT");
        this.mdxKeyEventHandler = new MdxKeyEventHandler(this.mdxKeyEventCallbacks);
        this.mdxErrorHandler = new MdxErrorHandler("MdxMiniPlayerFrag", this.activity, (MdxErrorHandler.ErrorHandlerCallbacks)new MdxErrorHandler.ErrorHandlerCallbacks() {
            @Override
            public void destroy() {
            }
        });
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        this.views = new MdxMiniPlayerViews(this.activity, this.mdxMiniPlayerViewCallbacks, this);
        this.log("Updating to empty state, controls enabled: " + MdxMiniPlayerFrag.state.controlsEnabled);
        this.views.updateToEmptyState(MdxMiniPlayerFrag.state.controlsEnabled);
        return this.views.getContentView();
    }
    
    @Override
    public void onDestroy() {
        if (this.remotePlayer != null) {
            this.remotePlayer.destroy();
        }
        super.onDestroy();
    }
    
    @Override
    public void onEpisodeSelectedForPlayback(final EpisodeDetails episodeDetails, final PlayContext playContext) {
        this.hideDialogFragmentIfNecessary();
        PlaybackLauncher.startPlaybackAfterPIN(this.activity, episodeDetails, playContext);
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final int n) {
        super.onManagerReady(manager, n);
        ThreadUtils.assertOnMain();
        if (this.activity == null || AndroidUtils.isActivityFinishedOrDestroyed(this.activity)) {
            this.log("Activity is null or destroyed - bailing early");
            return;
        }
        this.manager = manager;
        this.log("manager ready");
        this.initMdxComponents();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
        this.manager = null;
        if (this.remotePlayer != null) {
            this.remotePlayer.destroy();
            this.remotePlayer = null;
        }
        this.views.updateToEmptyState(false);
        this.hideSelf();
    }
    
    public void onPanelCollapsed() {
        this.views.onPanelCollapsed();
        if (this.isEndOfPlayback) {
            this.isEndOfPlayback = false;
            this.hideSelf();
        }
    }
    
    public void onPanelExpanded() {
        this.views.onPanelExpanded();
    }
    
    public void onPanelSlide(final float n) {
        this.views.onPanelSlide(n);
    }
    
    public void onResume() {
        super.onResume();
        this.views.onResume();
    }
    
    public void onResumeFragments() {
        this.log("onResumeFragments");
        this.isInBackground = false;
        if (this.manager == null) {
            this.hideSelfInternal();
            return;
        }
        if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
            this.log("on resume - currentVideo: " + this.currentVideo + ", shouldShowSelf: " + MdxMiniPlayerFrag.state.shouldShowSelf);
        }
        if (this.currentVideo == null || !MdxMiniPlayerFrag.state.shouldShowSelf) {
            this.hideSelf();
            return;
        }
        this.showSelf();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        synchronized (this) {
            super.onSaveInstanceState(bundle);
            bundle.putInt("saved_position_seconds", this.views.getProgress());
            this.isInBackground = true;
        }
    }
    
    public void onStart() {
        super.onStart();
        MdxUtils.registerReceiver(this.activity, this.dialogMessageReceiver);
    }
    
    public void onStop() {
        super.onStop();
        MdxUtils.unregisterReceiver(this.activity, this.dialogMessageReceiver);
    }
    
    public void sendDialogResponse(final String s) {
        if (this.remotePlayer != null) {
            this.remotePlayer.sendDialogResponse(s);
        }
    }
    
    public void setVolume(final int n) {
        MdxMiniPlayerFrag.state.mostRecentVolume = n;
        if (this.remotePlayer != null) {
            this.remotePlayer.setVolume(n);
        }
    }
    
    public void unshareVideo() {
        if (this.manager == null || !this.manager.isReady()) {
            Log.w("MdxMiniPlayerFrag", "Service manager is NOT ready - can't unshare video");
        }
        else if (!this.activity.destroyed()) {
            if (this.currentVideo == null) {
                Log.w("MdxMiniPlayerFrag", "Video detail is missing. This should NOT happen!");
                return;
            }
            this.log("Unsharing video...");
            this.views.setSharingButtonEnabled(false);
            final String playableId = this.currentVideo.getPlayableId();
            this.manager.hideVideo(playableId, new LoggingManagerCallback("MdxMiniPlayerFrag") {
                @Override
                public void onVideoHide(final int n) {
                    if (MdxMiniPlayerFrag.this.activity.destroyed()) {
                        return;
                    }
                    MdxMiniPlayerFrag.this.views.setSharingButtonVisibility(n == 0);
                    if (n == 0) {
                        MdxMiniPlayerFrag.this.log("onVideoHide, unshared state is: true");
                        MdxMiniPlayerFrag.state.isVideoUnshared = true;
                        MdxMiniPlayerFrag.dontShareIdSet.add(playableId);
                    }
                    MdxMiniPlayerFrag.this.log("DEBUG: onVideoHide status: " + n);
                }
            });
        }
    }
    
    private class FetchSeasonDetailsCallback extends LoggingManagerCallback
    {
        public FetchSeasonDetailsCallback() {
            super("MdxMiniPlayerFrag");
        }
        
        @Override
        public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final int n) {
            super.onSeasonDetailsFetched(seasonDetails, n);
            ThreadUtils.assertOnMain();
            if (n != 0) {
                return;
            }
            if (seasonDetails == null) {
                Log.w("MdxMiniPlayerFrag", "Season is null, should NOT happen!");
            }
            MdxMiniPlayerFrag.state.isEpisodeReady = (seasonDetails != null);
            MdxMiniPlayerFrag.this.views.setControlsEnabled(MdxMiniPlayerFrag.state.controlsEnabled);
        }
    }
    
    public interface MdxMiniPlayerDialog
    {
    }
    
    private static class SharedState
    {
        boolean controlsEnabled;
        float currRating;
        boolean isEpisodeReady;
        boolean isVideoUnshared;
        boolean isVolumeEnabled;
        int mostRecentVolume;
        boolean shouldShowSelf;
        
        public void reset() {
            Log.v("MdxMiniPlayerFrag", "resetting shared state");
            this.shouldShowSelf = false;
            this.controlsEnabled = false;
            this.isEpisodeReady = false;
            this.isVideoUnshared = false;
            this.isVolumeEnabled = false;
            this.currRating = -1.0f;
        }
    }
}
