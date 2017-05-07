// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.util.MdxUtils$SetVideoRatingCallback;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.servicemgr.model.Playable;
import android.view.View;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.Fragment;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.DialogFragment;
import com.netflix.mediaclient.Log;
import java.util.HashSet;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.Set;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.EpisodeRowView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class MdxMiniPlayerFrag extends NetflixFrag implements EpisodeRowView$EpisodeRowListener, DialogMessageReceiver$Callback, MdxUtils$MdxTargetSelectionDialogInterface
{
    private static final boolean DISABLED = false;
    private static final String EXTRA_SAVED_POSITION_SECONDS = "saved_position_seconds";
    public static final boolean FORCE_SHOW_WITH_DUMMY_DATA = false;
    private static final String NOTIFY_SHOW_AND_DISABLE_INTENT = "com.netflix.mediaclient.ui.mdx.NOTIFY_SHOW_AND_DISABLE_INTENT";
    private static final long SEEKBAR_UPDATE_DELAY_MS = 1000L;
    private static final String TAG = "MdxMiniPlayerFrag";
    private static final Set<String> dontShareIdSet;
    private static final MdxMiniPlayerFrag$SharedState state;
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
    private final LanguageSelector$LanguageSelectorCallback languageSelectorCallback;
    private ServiceManager manager;
    private MdxErrorHandler mdxErrorHandler;
    private final MdxKeyEventHandler$MdxKeyEventCallbacks mdxKeyEventCallbacks;
    private MdxKeyEventHandler mdxKeyEventHandler;
    private final MdxMiniPlayerViews$MdxMiniPlayerViewCallbacks mdxMiniPlayerViewCallbacks;
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
        dontShareIdSet = new HashSet<String>();
    }
    
    public MdxMiniPlayerFrag() {
        this.handler = new Handler();
        this.dialogMessageReceiver = new DialogMessageReceiver(this);
        this.updateSeekBarRunnable = new MdxMiniPlayerFrag$4(this);
        this.languageSelectorCallback = new MdxMiniPlayerFrag$5(this);
        this.remoteTargetUiListener = new MdxMiniPlayerFrag$6(this);
        this.mdxMiniPlayerViewCallbacks = new MdxMiniPlayerFrag$7(this);
        this.mdxKeyEventCallbacks = new MdxMiniPlayerFrag$8(this);
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
        if (this.activity.getVisibleDialog() instanceof MdxMiniPlayerFrag$MdxMiniPlayerDialog) {
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
            this.views.updateTitleText(this.currentVideo.getPlayable().getParentTitle());
            this.views.updateSubtitleText(this.activity.getString(2131493227, new Object[] { this.currentVideo.getPlayable().getSeasonNumber(), this.currentVideo.getPlayable().getEpisodeNumber(), this.currentVideo.getTitle() }));
        }
        else {
            this.views.updateTitleText(this.currentVideo.getTitle());
            this.views.updateSubtitleText("");
        }
        this.views.updateDeviceNameText(ServiceManagerUtils.getCurrentDeviceFriendlyName(this.manager));
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
            this.manager.getBrowse().fetchSeasonDetails(((EpisodeDetails)this.currentVideo).getSeasonId(), new MdxMiniPlayerFrag$FetchSeasonDetailsCallback(this));
        }
        else {
            this.log("Video is not instance of EpisodeDetails");
        }
        if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
            this.log("isVideoUnshared - can be shared on facebook: " + this.currentVideo.getPlayable().canBeSharedOnFacebook() + ", don't share set contains id: " + MdxMiniPlayerFrag.dontShareIdSet.contains(this.currentVideo.getPlayable().getPlayableId()));
        }
        MdxMiniPlayerFrag.state.isVideoUnshared = (!this.currentVideo.getPlayable().canBeSharedOnFacebook() || MdxMiniPlayerFrag.dontShareIdSet.contains(this.currentVideo.getPlayable().getPlayableId()));
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
        return new MdxTargetSelection(mdx.getTargetList(), mdx.getCurrentTarget(), this.manager.getConfiguration().getPlaybackConfiguration().isLocalPlaybackEnabled());
    }
    
    @Override
    public Playable getVideoDetails() {
        if (this.isPlayingRemotely() && this.currentVideo != null) {
            return this.currentVideo.getPlayable();
        }
        return null;
    }
    
    public int getVolume() {
        return MdxMiniPlayerFrag.state.mostRecentVolume;
    }
    
    @Override
    public void handleUserRatingChange(final String s, final float currUserRating) {
        if (Log.isLoggable("MdxMiniPlayerFrag", 2)) {
            this.log("Change user settings for received video id: " + s + " to rating: " + currUserRating);
        }
        final String videoId = MdxUtils.getVideoId(this.currentVideo);
        if (s != null && s.equals(videoId)) {
            this.log("This is rating for current playback, update it");
            MdxMiniPlayerFrag.state.currUserRating = currUserRating;
        }
        else {
            this.log("This is not update for current playable!");
        }
        if (this.manager == null) {
            this.log("Can't set rating because service man is null");
            return;
        }
        this.manager.getBrowse().setVideoRating(videoId, this.currentVideo.getType(), (int)MdxMiniPlayerFrag.state.currUserRating, PlayContext.EMPTY_CONTEXT.getTrackId(), new MdxUtils$SetVideoRatingCallback(this.activity, MdxMiniPlayerFrag.state.currUserRating));
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
        return this.isShowing && !this.isEndOfPlayback;
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
    
    @Override
    public void onEpisodeSelectedForPlayback(final EpisodeDetails episodeDetails, final PlayContext playContext) {
        this.hideDialogFragmentIfNecessary();
        PlaybackLauncher.startPlaybackAfterPIN(this.activity, episodeDetails.getPlayable(), playContext);
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final Status status) {
        super.onManagerReady(manager, status);
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
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
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
            final String playableId = this.currentVideo.getPlayable().getPlayableId();
            this.manager.getBrowse().hideVideo(playableId, new MdxMiniPlayerFrag$2(this, "MdxMiniPlayerFrag", playableId));
        }
    }
}
