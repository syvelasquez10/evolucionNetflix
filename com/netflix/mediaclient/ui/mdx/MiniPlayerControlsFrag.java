// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.util.ViewUtils;
import android.app.FragmentTransaction;
import com.netflix.mediaclient.ui.details.RoleDetailsFrag;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.falkor.CachedModelProxy$FetchTurboCollectionVideosTask;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.app.Fragment;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.FragmentManager;
import android.app.DialogFragment;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class MiniPlayerControlsFrag extends NetflixFrag implements AbsEpisodeView$EpisodeRowListener, IHandleBackPress, NetflixRatingBar$RatingBarDataProvider, IMiniPlayerFrag, MdxUtils$MdxTargetSelectionDialogInterface
{
    private static final boolean DISABLED = false;
    private static final String EXTRA_SAVED_POSITION_SECONDS = "saved_position_seconds";
    public static final boolean FORCE_SHOW_WITH_DUMMY_DATA = false;
    private static final String NOTIFY_SHOW_AND_DISABLE_INTENT = "com.netflix.mediaclient.ui.mdx.NOTIFY_SHOW_AND_DISABLE_INTENT";
    private static final long SEEKBAR_UPDATE_DELAY_MS = 1000L;
    private static final String TAG = "MdxMiniPlayerFrag";
    private static final MiniPlayerControlsFrag$SharedState state;
    private NetflixActivity activity;
    private VideoDetails currentVideo;
    private boolean draggingInProgress;
    private IMdx dummyMdx;
    private ViewGroup episodeEndContainer;
    private GridLayoutManager gridLayoutManager;
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
    int numColumns;
    private String parentActivityClass;
    private RecyclerViewHeaderAdapter relatedAdapter;
    private View relatedGridGroup;
    private TextView relatedGridTitle;
    private RecyclerView relatedRecyclerView;
    private int relatedViewHeight;
    private int relatedViewWidth;
    private RemotePlayer remotePlayer;
    private final RemotePlayer$RemoteTargetUiListener remoteTargetUiListener;
    private int savedPositionSeconds;
    private long simulatedCurrentTimelinePositionMs;
    private long simulatedVideoPositionTimeFiredMs;
    private ViewGroup titleEndContainer;
    private final Runnable updateSeekBarRunnable;
    private MdxMiniPlayerViews views;
    
    static {
        state = new MiniPlayerControlsFrag$SharedState(null);
    }
    
    public MiniPlayerControlsFrag() {
        this.handler = new Handler();
        this.numColumns = 3;
        this.relatedViewWidth = -1;
        this.relatedViewHeight = -1;
        this.updateSeekBarRunnable = new MiniPlayerControlsFrag$5(this);
        this.languageSelectorCallback = new MiniPlayerControlsFrag$6(this);
        this.remoteTargetUiListener = new MiniPlayerControlsFrag$7(this);
        this.mdxMiniPlayerViewCallbacks = new MiniPlayerControlsFrag$8(this);
        this.mdxKeyEventCallbacks = new MiniPlayerControlsFrag$9(this);
    }
    
    private void collapseMiniPlayerForPhone() {
        if (!DeviceUtils.isTabletByContext((Context)this.getActivity()) && DeviceUtils.isLandscape((Context)this.getActivity())) {
            this.views.playcardCaret.setAlpha(0);
            return;
        }
        this.views.playcardCaret.setAlpha(1.0f);
    }
    
    private void findViews(final View view) {
        this.relatedRecyclerView = (RecyclerView)view.findViewById(2131690024);
        this.relatedGridTitle = (TextView)view.findViewById(2131690023);
        this.relatedGridGroup = view.findViewById(2131690022);
    }
    
    private void hideDialogFragmentIfNecessary() {
        final DialogFragment dialogFragment = this.activity.getDialogFragment();
        if (dialogFragment instanceof MiniPlayerControlsFrag$MdxMiniPlayerDialog) {
            Log.d("MdxMiniPlayerFrag", "MDX mini player dialog frag currently shown - hiding");
            dialogFragment.dismiss();
        }
    }
    
    private void hideMementoLoading() {
        if (this.isActivityValid()) {
            final FragmentManager fragmentManager = this.getFragmentManager();
            if (fragmentManager != null) {
                final MementoFrag mementoFrag = (MementoFrag)fragmentManager.findFragmentById(2131690020);
                if (mementoFrag != null) {
                    mementoFrag.hideLoading();
                }
            }
        }
    }
    
    private void hideSelf() {
        synchronized (this) {
            this.log("hideSelf()");
            this.isShowing = false;
            MiniPlayerControlsFrag.state.shouldShowSelf = false;
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
            fragmentManager.beginTransaction().hide((Fragment)this).commit();
            fragmentManager.executePendingTransactions();
            this.hideDialogFragmentIfNecessary();
            this.hideVisibleDialogIfNecessary();
            this.hideRDP();
            this.activity.notifyMdxMiniPlayerHidden();
        }
    }
    
    private void hideVisibleDialogIfNecessary() {
        if (this.activity.getVisibleDialog() instanceof MiniPlayerControlsFrag$MdxMiniPlayerDialog) {
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
    
    private void setupRecyclerViewItemDecoration() {
        this.relatedRecyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131361871), this.numColumns));
    }
    
    private void showAndDisable() {
        this.showSelf(PersistentConfig.inMementoTest((Context)this.getActivity()));
        this.stopSimulatedVideoPositionUpdate();
        this.views.setControlsEnabled(false);
    }
    
    private void showSelf(final boolean b) {
        while (true) {
            Label_0077: {
                synchronized (this) {
                    this.log("showSelf()");
                    this.isShowing = true;
                    MiniPlayerControlsFrag.state.shouldShowSelf = true;
                    this.views.updateMdxMenu();
                    if (this.isInBackground || AndroidUtils.isActivityFinishedOrDestroyed((Context)this.activity)) {
                        this.log("Frag is in BG - should just show self on resume");
                    }
                    else {
                        if (this.currentVideo != null) {
                            break Label_0077;
                        }
                        this.log("currentVideo is null - show self failed");
                    }
                    return;
                }
            }
            this.log("Showing MDX Player frag");
            final FragmentManager fragmentManager = this.getFragmentManager();
            if (fragmentManager != null) {
                fragmentManager.beginTransaction().show((Fragment)this).commit();
                fragmentManager.executePendingTransactions();
                this.activity.notifyMdxMiniPlayerShown(b);
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
            this.views.updateSubtitleText(this.activity.getString(2131231088, new Object[] { this.currentVideo.getPlayable().getSeasonAbbrSeqLabel(), this.currentVideo.getPlayable().getEpisodeNumber(), this.currentVideo.getTitle() }));
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
            this.getServiceManager().getBrowse().fetchSeasonDetails(((EpisodeDetails)this.currentVideo).getSeasonId(), new MiniPlayerControlsFrag$FetchSeasonDetailsCallback(this));
        }
        else {
            this.log("Video is not instance of EpisodeDetails");
        }
        final MementoFrag mementoFrag = (MementoFrag)this.getFragmentManager().findFragmentById(2131690020);
        if (mementoFrag != null) {
            mementoFrag.setVideoId(this.currentVideo.getPlayable().getParentId());
            mementoFrag.fetchData();
        }
        final PostPlayFrag postPlayFrag = (PostPlayFrag)this.getFragmentManager().findFragmentById(2131690021);
        if (postPlayFrag != null) {
            postPlayFrag.setVideo(this.currentVideo);
        }
        this.updateLanguage();
    }
    
    private void updateVisibility(final boolean b, final boolean b2, final boolean b3) {
        if (Log.isLoggable()) {
            this.log("updateVisibility, frag isVisible: " + this.isShowing + ", show: " + b + ", paused: " + b2);
        }
        if (this.isShowing != b) {
            if (b) {
                this.showSelf(b3);
                if (this.remotePlayer != null) {
                    this.remotePlayer.sync();
                }
            }
            else {
                this.hideSelf();
            }
        }
        this.views.updatePlayPauseButton(b2);
        this.views.setVolumeButtonVisibility(MiniPlayerControlsFrag.state.isVolumeEnabled);
    }
    
    private void updateVolumeState(final boolean b) {
        MiniPlayerControlsFrag.state.isVolumeEnabled = b;
        this.views.setVolumeButtonVisibility(b);
    }
    
    @Override
    public void attachMenuItem(final MdxMenu mdxMenu) {
        this.views.attachMenuItem(mdxMenu);
    }
    
    @Override
    public boolean destroyed() {
        return this.isDestroyed();
    }
    
    @Override
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return this.mdxKeyEventHandler.handleKeyEvent(keyEvent, this.getServiceManager(), this.remotePlayer);
    }
    
    public void fetchRelatedCollection(final String s, final String text) {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.w("MdxMiniPlayerFrag", "Manager is null/notReady - can't reload data");
            return;
        }
        if (this.relatedGridTitle != null) {
            this.relatedGridTitle.setText((CharSequence)text);
        }
        Log.v("MdxMiniPlayerFrag", "Fetching data for turbo collection ID: " + s);
        serviceManager.getBrowse().fetchTask(new CachedModelProxy$FetchTurboCollectionVideosTask(Long.valueOf(s), 0, 20), new MiniPlayerControlsFrag$3(this, "MdxMiniPlayerFrag"));
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
    public Playable getPlayable() {
        if (this.isPlayingRemotely() && this.currentVideo != null) {
            return this.currentVideo.getPlayable();
        }
        return null;
    }
    
    @Override
    public RemotePlayer getPlayer() {
        return this.remotePlayer;
    }
    
    @Override
    public View getSlidingPanelDragView() {
        return this.views.getSlidingPanelDragView();
    }
    
    @Override
    public MdxTargetSelection getTargetSelection() {
        final IMdx mdx = this.mdxMiniPlayerViewCallbacks.getMdx();
        return new MdxTargetSelection(mdx.getTargetList(), mdx.getCurrentTarget(), this.getServiceManager().getConfiguration().getPlaybackConfiguration().isLocalPlaybackEnabled());
    }
    
    @Override
    public String getVideoId() {
        if (this.currentVideo == null) {
            return null;
        }
        return this.currentVideo.getId();
    }
    
    @Override
    public VideoType getVideoType() {
        if (this.currentVideo == null) {
            return null;
        }
        return this.currentVideo.getType();
    }
    
    @Override
    public int getVolume() {
        return MiniPlayerControlsFrag.state.mostRecentVolume;
    }
    
    @Override
    public boolean handleBackPressed() {
        if (this.relatedGridGroup.getVisibility() == 0) {
            final AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            ((Animation)alphaAnimation).setDuration(600L);
            this.relatedGridGroup.setVisibility(8);
            this.hideMementoLoading();
            this.views.rotateCaretTo(0);
            this.getNetflixActivity().setSlidingEnabled(true);
            this.relatedGridGroup.startAnimation((Animation)alphaAnimation);
            return true;
        }
        final PostPlayFrag postPlayFrag = (PostPlayFrag)this.getFragmentManager().findFragmentById(2131690021);
        if (postPlayFrag != null && postPlayFrag.isShowingTitleEnd()) {
            this.getActivity().sendBroadcast(new Intent("com.netflix.mediaclient.service.ACTION_CLOSE_MINI_PLAYER"));
            return true;
        }
        return this.hideRDP();
    }
    
    public boolean hideRDP() {
        final RoleDetailsFrag roleDetailsFrag = (RoleDetailsFrag)this.getFragmentManager().findFragmentById(2131690025);
        if (roleDetailsFrag != null && !roleDetailsFrag.isHidden() && roleDetailsFrag.isResumed()) {
            final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
            beginTransaction.setCustomAnimations(2131034114, 2131034115);
            beginTransaction.hide((Fragment)roleDetailsFrag);
            roleDetailsFrag.resetScroll();
            beginTransaction.commitAllowingStateLoss();
            this.getNetflixActivity().setSlidingEnabled(true);
            return true;
        }
        return false;
    }
    
    public void hideRelatedGroup() {
        ViewUtils.setVisibleOrGone(this.relatedGridGroup, false);
    }
    
    @Override
    public void initMdxComponents() {
        this.log("initMdxComponents()");
        final IMdx mdx = this.mdxMiniPlayerViewCallbacks.getMdx();
        if (mdx != null) {
            final VideoDetails videoDetail = mdx.getVideoDetail();
            if (videoDetail != null) {
                this.updateVideoMetadata(videoDetail);
                this.views.setControlsEnabled(MiniPlayerControlsFrag.state.controlsEnabled);
                this.updateVisibility(MiniPlayerControlsFrag.state.shouldShowSelf, mdx.isPaused(), false);
            }
            this.remotePlayer = new RemotePlayer(this.activity, this.remoteTargetUiListener);
            if (this.isShowing()) {
                if (MiniPlayerControlsFrag.state.controlsEnabled) {
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
        return this.views.computeMdxMenuEnabled(MiniPlayerControlsFrag.state.controlsEnabled);
    }
    
    @Override
    public boolean isPlayingLocally() {
        return false;
    }
    
    @Override
    public boolean isPlayingRemotely() {
        return this.isShowing && !this.isEndOfPlayback;
    }
    
    @Override
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
        this.log("savedPositionSeconds: " + this.savedPositionSeconds);
        this.activity.registerReceiverWithAutoUnregister(new MiniPlayerControlsFrag$1(this), "com.netflix.mediaclient.ui.mdx.NOTIFY_SHOW_AND_DISABLE_INTENT");
        this.mdxKeyEventHandler = new MdxKeyEventHandler(this.mdxKeyEventCallbacks);
        this.mdxErrorHandler = new MdxErrorHandler("MdxMiniPlayerFrag", this.activity);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        this.views = new MiniPlayerViews(this.activity, this.mdxMiniPlayerViewCallbacks, this);
        this.log("Updating to empty state, controls enabled: " + MiniPlayerControlsFrag.state.controlsEnabled);
        this.views.updateToEmptyState(MiniPlayerControlsFrag.state.controlsEnabled);
        this.findViews(this.views.getContentView());
        this.hideRDP();
        this.collapseMiniPlayerForPhone();
        this.setupRecyclerView();
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
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        ThreadUtils.assertOnMain();
        if (this.activity == null || AndroidUtils.isActivityFinishedOrDestroyed((Context)this.activity)) {
            this.log("Activity is null or destroyed - bailing early");
            return;
        }
        final MementoFrag mementoFrag = (MementoFrag)this.getFragmentManager().findFragmentById(2131690020);
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
            this.hideRDP();
        }
        if (this.relatedGridGroup != null) {
            this.relatedGridGroup.setVisibility(4);
            this.hideMementoLoading();
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
            this.log("on resume - currentVideo: " + this.currentVideo + ", shouldShowSelf: " + MiniPlayerControlsFrag.state.shouldShowSelf);
        }
        if (this.currentVideo == null || !MiniPlayerControlsFrag.state.shouldShowSelf) {
            this.hideSelf();
            return;
        }
        this.showSelf(false);
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
        MiniPlayerControlsFrag.state.mostRecentVolume = n;
        if (this.remotePlayer != null) {
            this.remotePlayer.setVolume(n);
        }
    }
    
    protected void setupRecyclerView() {
        if (this.relatedRecyclerView == null) {
            return;
        }
        this.relatedRecyclerView.setFocusable(false);
        this.relatedGridGroup.setVisibility(8);
        this.hideMementoLoading();
        int numColumns;
        if (DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            numColumns = 4;
        }
        else {
            numColumns = 3;
        }
        this.numColumns = numColumns;
        this.setupRecyclerViewLayoutManager();
        this.setupRecyclerViewAdapter();
        this.setupRecyclerViewItemDecoration();
    }
    
    protected void setupRecyclerViewAdapter() {
        this.relatedAdapter = new SimilarItemsGridViewAdapter(true, this.numColumns, new MiniPlayerControlsFrag$2(this));
        this.relatedRecyclerView.setAdapter(this.relatedAdapter);
    }
    
    protected void setupRecyclerViewLayoutManager() {
        this.gridLayoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns);
        this.relatedRecyclerView.setLayoutManager(this.gridLayoutManager);
    }
}
