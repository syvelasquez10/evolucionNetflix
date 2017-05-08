// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.Fragment;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.DialogFragment;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class MdxMiniPlayerFrag extends NetflixFrag implements IMiniPlayerFrag
{
    private static final boolean DISABLED = false;
    private static final String EXTRA_SAVED_POSITION_SECONDS = "saved_position_seconds";
    public static final boolean FORCE_SHOW_WITH_DUMMY_DATA = false;
    private static final String NOTIFY_SHOW_AND_DISABLE_INTENT = "com.netflix.mediaclient.ui.mdx.NOTIFY_SHOW_AND_DISABLE_INTENT";
    private static final long SEEKBAR_UPDATE_DELAY_MS = 1000L;
    private static final String TAG = "MdxMiniPlayerFrag";
    private static final MdxMiniPlayerFrag$SharedState state;
    private NetflixActivity activity;
    private VideoDetails currentVideo;
    private boolean draggingInProgress;
    private IMdx dummyMdx;
    private final Handler handler;
    private boolean isEndOfPlayback;
    private boolean isInBackground;
    private boolean isShowing;
    private LanguageSelector languageSelector;
    private final LanguageSelector$LanguageSelectorCallback languageSelectorCallback;
    private MdxErrorHandler mdxErrorHandler;
    private final MdxKeyEventHandler$MdxKeyEventCallbacks mdxKeyEventCallbacks;
    private MdxKeyEventHandler mdxKeyEventHandler;
    private final IMdxMiniPlayerViewCallbacks mdxMiniPlayerViewCallbacks;
    private String parentActivityClass;
    private RemotePlayer remotePlayer;
    private final RemotePlayer$RemoteTargetUiListener remoteTargetUiListener;
    private int savedPositionSeconds;
    private long simulatedCurrentTimelinePositionMs;
    private long simulatedVideoPositionTimeFiredMs;
    private final Runnable updateSeekBarRunnable;
    private MdxMiniPlayerViews views;
    
    static {
        state = new MdxMiniPlayerFrag$SharedState(null);
    }
    
    public MdxMiniPlayerFrag() {
        this.handler = new Handler();
        this.updateSeekBarRunnable = new MdxMiniPlayerFrag$3(this);
        this.languageSelectorCallback = new MdxMiniPlayerFrag$4(this);
        this.remoteTargetUiListener = new MdxMiniPlayerFrag$5(this);
        this.mdxMiniPlayerViewCallbacks = new MdxMiniPlayerFrag$6(this);
        this.mdxKeyEventCallbacks = new MdxMiniPlayerFrag$7(this);
    }
    
    private void hideDialogFragmentIfNecessary() {
        final DialogFragment dialogFragment = this.activity.getDialogFragment();
        if (dialogFragment instanceof MdxMiniPlayerFrag$MdxMiniPlayerDialog) {
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
            if (this.isInBackground || AndroidUtils.isActivityFinishedOrDestroyed((Context)this.activity)) {
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
            if (fragmentManager != null) {
                fragmentManager.beginTransaction().hide((Fragment)this).commit();
                fragmentManager.executePendingTransactions();
            }
            this.hideDialogFragmentIfNecessary();
            this.hideVisibleDialogIfNecessary();
            this.activity.notifyMdxMiniPlayerHidden();
        }
    }
    
    private void hideVisibleDialogIfNecessary() {
        if (this.activity.getVisibleDialog() instanceof MdxMiniPlayerFrag$MdxMiniPlayerDialog) {
            Log.d("MdxMiniPlayerFrag", "MDX dialog currently shown - hiding");
            this.activity.removeVisibleDialog();
        }
    }
    
    private void log(final String s) {
        if (Log.isLoggable()) {
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
            if (this.isInBackground || AndroidUtils.isActivityFinishedOrDestroyed((Context)this.activity)) {
                this.log("Frag is in BG - should just show self on resume");
            }
            else {
                this.log("Showing MDX Player frag");
                final FragmentManager fragmentManager = this.getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().show((Fragment)this).commit();
                    fragmentManager.executePendingTransactions();
                    this.activity.notifyMdxMiniPlayerShown(false);
                }
            }
        }
    }
    
    private void startSimulatedVideoPositionUpdate(final long n) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.activity)) {
            this.handler.removeCallbacks(this.updateSeekBarRunnable);
            this.simulatedCurrentTimelinePositionMs = n * 1000L;
            this.simulatedVideoPositionTimeFiredMs = System.currentTimeMillis();
            this.handler.postDelayed(this.updateSeekBarRunnable, 1000L);
            this.log("Simulated position update +started+");
        }
    }
    
    private void stopSimulatedVideoPositionUpdate() {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.activity)) {
            this.handler.removeCallbacks(this.updateSeekBarRunnable);
            this.log("Simulated position update -stopped-");
        }
    }
    
    private void updateLanguage() {
        this.log("updateLanguage()");
        this.views.setLanguageButtonEnabled(this.mdxMiniPlayerViewCallbacks.isLanguageReady());
    }
    
    private void updateVideoMetadata(final VideoDetails currentVideo) {
        this.currentVideo = currentVideo;
        this.log("Updating metadata: " + this.currentVideo + ", hash: " + this.currentVideo.hashCode());
        if (this.currentVideo.getType() == VideoType.EPISODE) {
            this.views.updateTitleText(this.currentVideo.getPlayable().getParentTitle());
            String s = this.activity.getString(2131296644, new Object[] { this.currentVideo.getPlayable().getSeasonAbbrSeqLabel(), this.currentVideo.getPlayable().getEpisodeNumber(), this.currentVideo.getTitle() });
            if (this.currentVideo.isNSRE()) {
                s = this.activity.getString(2131296645, new Object[] { this.currentVideo.getTitle() });
            }
            this.views.updateSubtitleText(s);
        }
        else {
            this.views.updateTitleText(this.currentVideo.getTitle());
            this.views.updateSubtitleText("");
        }
        this.views.updateDeviceNameText(ServiceManagerUtils.getCurrentDeviceFriendlyName(this.getServiceManager()));
        this.views.setEpisodesButtonVisibile(this.currentVideo.getType() != VideoType.MOVIE);
        this.log("Setting seek bar max: " + this.currentVideo.getPlayable().getRuntime());
        this.views.setProgressMax(this.currentVideo.getPlayable().getRuntime());
        int positionInSeconds;
        if (this.remotePlayer == null) {
            positionInSeconds = 0;
        }
        else {
            positionInSeconds = this.remotePlayer.getPositionInSeconds();
        }
        this.log(String.format("updating seek pos - remote pos: %d, playable bookmark pos: %d, saved pos: %d", positionInSeconds, this.currentVideo.getPlayable().getPlayableBookmarkPosition(), this.savedPositionSeconds));
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
                playableBookmarkPosition = this.currentVideo.getPlayable().getPlayableBookmarkPosition();
            }
        }
        if (playableBookmarkPosition > 0) {
            this.log("Setting seek progress: " + playableBookmarkPosition);
            this.views.setProgress(playableBookmarkPosition);
        }
        this.views.updateImage(this.currentVideo);
        if (this.currentVideo instanceof EpisodeDetails) {
            this.log("Video is instance of EpisodeDetails, fetching episodes...");
            this.getServiceManager().getBrowse().fetchSeasonDetails(((EpisodeDetails)this.currentVideo).getSeasonId(), new MdxMiniPlayerFrag$FetchSeasonDetailsCallback(this));
        }
        else {
            this.log("Video is not instance of EpisodeDetails");
        }
        this.updateLanguage();
    }
    
    private void updateVisibility(final boolean b, final boolean b2) {
        if (Log.isLoggable()) {
            this.log("updateVisibility, frag isVisible: " + this.isShowing + ", show: " + b + ", paused: " + b2);
        }
        if (this.isShowing != b) {
            if (b) {
                this.showSelf();
                if (this.remotePlayer != null) {
                    this.remotePlayer.sync();
                }
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
    
    @Override
    public void attachMenuItem(final MdxMenu mdxMenu) {
        this.views.attachMenuItem(mdxMenu);
    }
    
    public boolean destroyed() {
        return this.isDestroyed();
    }
    
    @Override
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.mdxKeyEventHandler.handleKeyEvent(keyEvent, this.getServiceManager(), this.remotePlayer);
    }
    
    public long getCurrentPositionMs() {
        return this.simulatedCurrentTimelinePositionMs;
    }
    
    public PlayContext getPlayContext() {
        return PlayContext.EMPTY_CONTEXT;
    }
    
    public Playable getPlayable() {
        if (this.isPlayingRemotely() && this.currentVideo != null) {
            return this.currentVideo.getPlayable();
        }
        return null;
    }
    
    public RemotePlayer getPlayer() {
        return this.remotePlayer;
    }
    
    @Override
    public View getSlidingPanelDragView() {
        return this.views.getSlidingPanelDragView();
    }
    
    public MdxTargetSelection getTargetSelection() {
        final IMdx mdx = this.mdxMiniPlayerViewCallbacks.getMdx();
        return new MdxTargetSelection(mdx.getTargetList(), mdx.getCurrentTarget(), this.getServiceManager().getConfiguration().getPlaybackConfiguration().isLocalPlaybackEnabled());
    }
    
    public String getVideoId() {
        if (this.currentVideo == null) {
            return null;
        }
        return this.currentVideo.getId();
    }
    
    public VideoType getVideoType() {
        if (this.currentVideo == null) {
            return null;
        }
        return this.currentVideo.getType();
    }
    
    @Override
    public int getVolume() {
        return MdxMiniPlayerFrag.state.mostRecentVolume;
    }
    
    public boolean handleBackPressed() {
        return false;
    }
    
    @Override
    public void initMdxComponents() {
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
        this.views.onManagerReady(this.getServiceManager());
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public boolean isMdxMenuEnabled() {
        return this.views.computeMdxMenuEnabled(MdxMiniPlayerFrag.state.controlsEnabled);
    }
    
    public boolean isPlayingLocally() {
        return false;
    }
    
    public boolean isPlayingRemotely() {
        return this.isShowing && !this.isEndOfPlayback;
    }
    
    @Override
    public boolean isShowing() {
        return this.isShowing;
    }
    
    public void notifyPlayingBackLocal() {
        this.hideSelf();
    }
    
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
        this.log("savedPositionSeconds: " + this.savedPositionSeconds);
        this.activity.registerReceiverWithAutoUnregister(new MdxMiniPlayerFrag$1(this), "com.netflix.mediaclient.ui.mdx.NOTIFY_SHOW_AND_DISABLE_INTENT");
        this.mdxKeyEventHandler = new MdxKeyEventHandler(this.mdxKeyEventCallbacks);
        this.mdxErrorHandler = new MdxErrorHandler("MdxMiniPlayerFrag", this.activity);
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
    
    public void onEpisodeSelectedForPlayback(final EpisodeDetails episodeDetails, final PlayContext playContext) {
        this.hideDialogFragmentIfNecessary();
        PlaybackLauncher.startPlaybackAfterPIN(this.activity, episodeDetails.getPlayable(), playContext);
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        ThreadUtils.assertOnMain();
        if (this.activity == null || AndroidUtils.isActivityFinishedOrDestroyed((Context)this.activity)) {
            this.log("Activity is null or destroyed - bailing early");
            return;
        }
        final MementoFrag mementoFrag = (MementoFrag)this.getFragmentManager().findFragmentById(2131755564);
        if (mementoFrag != null) {
            mementoFrag.onManagerReady(serviceManager, status);
        }
        this.log("manager ready");
        this.initMdxComponents();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        if (this.remotePlayer != null) {
            this.remotePlayer.destroy();
            this.remotePlayer = null;
        }
        this.views.updateToEmptyState(false);
        this.hideSelf();
    }
    
    @Override
    public void onPanelCollapsed() {
        this.views.onPanelCollapsed();
        if (this.isEndOfPlayback) {
            this.isEndOfPlayback = false;
            this.hideSelf();
        }
    }
    
    @Override
    public void onPanelExpanded() {
        this.views.onPanelExpanded();
    }
    
    @Override
    public void onPanelSlide(final float n) {
        this.views.onPanelSlide(n);
    }
    
    public void onResume() {
        super.onResume();
        this.views.onResume();
    }
    
    @Override
    public void onResumeFragments() {
        this.log("onResumeFragments");
        this.isInBackground = false;
        if (this.getServiceManager() == null) {
            this.hideSelfInternal();
            return;
        }
        if (Log.isLoggable()) {
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
    
    @Override
    public void sendDialogResponse(final String s) {
        if (this.remotePlayer != null) {
            this.remotePlayer.sendDialogResponse(s);
        }
    }
    
    @Override
    public void setVolume(final int n) {
        MdxMiniPlayerFrag.state.mostRecentVolume = n;
        if (this.remotePlayer != null) {
            this.remotePlayer.setVolume(n);
        }
    }
}
