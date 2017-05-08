// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.coppola.details;

import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import android.view.KeyEvent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.content.res.Configuration;
import java.io.Serializable;
import android.os.Parcelable;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.Activity;
import android.view.Window;
import android.widget.LinearLayout$LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.util.Coppola1Utils;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import android.widget.ImageView;
import android.widget.FrameLayout;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.view.View;
import android.view.Menu;
import android.app.Fragment;
import com.netflix.mediaclient.ui.details.MovieDetailsActivity$BackStackData;
import java.util.Stack;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import com.netflix.mediaclient.ui.details.DetailsActivity;

public class CoppolaDetailsActivity extends DetailsActivity implements NetflixDialogFrag$DialogCanceledListenerProvider, PlayContextProvider
{
    private static int MIN_SAMSUNG_POST_DRAW_INTERVAL_MS = 0;
    public static final float PLAYABLE_AREA_RATIO = 2.5f;
    public static final String PLAYBACK_START_SECONDS = "playback_start_seconds";
    public static final String PUSH_TO_LANDSCAPE_FLAG = "push_to_landscape";
    private static final String TAG = "CoppolaDetailsActivity";
    private boolean bIsInPortrait;
    private final Stack<MovieDetailsActivity$BackStackData> backStack;
    private Fragment detailsFrag;
    private Menu dynamicToolbarMenu;
    private View imageContainer;
    private PlayerFragment playerFragment;
    private int startOrientation;
    private VideoType videoType;
    
    static {
        CoppolaDetailsActivity.MIN_SAMSUNG_POST_DRAW_INTERVAL_MS = 400;
    }
    
    public CoppolaDetailsActivity() {
        this.startOrientation = 0;
        this.backStack = new Stack<MovieDetailsActivity$BackStackData>();
    }
    
    private void addTopGradientIfNeeded() {
        final FrameLayout frameLayout = (FrameLayout)this.detailsFrag.getView().findViewById(2131689896);
        if (frameLayout.findViewById(2131689483) == null) {
            final ImageView imageView = new ImageView((Context)this);
            imageView.setImageResource(2130837992);
            imageView.setId(2131689483);
            frameLayout.addView((View)imageView, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, (int)this.getResources().getDimension(2131362150), 48));
        }
    }
    
    private Fragment createEpisodesFrag() {
        if (Coppola1Utils.showNewEpisodesFrag((Context)this)) {
            return (Fragment)CoppolaShowDetailsFrag.create(this.videoId, this.getEpisodeId(), true);
        }
        return (Fragment)EpisodesFrag.create(this.videoId, this.getEpisodeId(), true);
    }
    
    private void doOnManagerReady() {
        this.getHandler().post((Runnable)new CoppolaDetailsActivity$3(this));
    }
    
    private String getPlayableId() {
        if (StringUtils.isNotEmpty(this.episodeId)) {
            return this.episodeId;
        }
        return this.videoId;
    }
    
    private String getPlayableVideoType() {
        if (this.videoType == VideoType.MOVIE) {
            return VideoType.MOVIE.getValue();
        }
        if (StringUtils.isNotEmpty(this.episodeId)) {
            return VideoType.EPISODE.getValue();
        }
        return VideoType.SHOW.getValue();
    }
    
    private void handleNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        final VideoType videoType = this.videoType;
        this.refreshVideoTypeAndContext();
        if (this.getServiceManager() == null || !this.getServiceManager().isReady()) {
            Log.w("CoppolaDetailsActivity", "SPY-9355 - CoppolaDetailsActivity::handleNewIntent() was called when ServiceManager not ready - skipping...");
            ErrorLoggingManager.logHandledException("SPY-9355 - CoppolaDetailsActivity::handleNewIntent() was called when ServiceManager not ready - skipping...");
            return;
        }
        this.fillVideoAndEpisodeIds();
        this.playerFragment.resetCurrentPlayback();
        this.playerFragment.setExternalBundle(PlayerFragment.getBundle(this.getPlayableId(), this.getPlayableVideoType(), this.playContext));
        this.playerFragment.requestDetailsIfNeeded(this.getServiceManager());
        this.startDPTTISession();
        if (videoType == VideoType.MOVIE) {
            if (this.videoType == VideoType.MOVIE) {
                ((MovieDetailsFrag)this.detailsFrag).setVideoId(this.videoId);
                this.reattachFragment(this.detailsFrag);
                ((MovieDetailsFrag)this.detailsFrag).scrollTop();
                Log.i("CoppolaDetailsActivity", "onNewIntent() for movie");
            }
            else {
                this.getFragmentManager().beginTransaction().remove(this.detailsFrag).commitAllowingStateLoss();
                this.detailsFrag = this.createEpisodesFrag();
                this.getFragmentManager().beginTransaction().add(2131689756, this.detailsFrag).commitAllowingStateLoss();
                ((EpisodesFrag)this.detailsFrag).setVideoId(this.videoId);
                this.doOnManagerReady();
                Log.i("CoppolaDetailsActivity", "onNewIntent() for show after movie");
                this.invalidateOptionsMenu();
            }
        }
        else if (this.videoType == VideoType.SHOW) {
            ((EpisodesFrag)this.detailsFrag).setVideoId(this.videoId);
            this.reattachFragment(this.detailsFrag);
            Log.i("CoppolaDetailsActivity", "onNewIntent() for show");
        }
        else {
            this.getFragmentManager().beginTransaction().remove(this.detailsFrag).commitAllowingStateLoss();
            this.detailsFrag = MovieDetailsFrag.create(this.videoId);
            this.getFragmentManager().beginTransaction().add(2131689756, this.detailsFrag).commitAllowingStateLoss();
            ((MovieDetailsFrag)this.detailsFrag).setVideoId(this.videoId);
            this.doOnManagerReady();
            Log.i("CoppolaDetailsActivity", "onNewIntent() for movie after show");
            this.invalidateOptionsMenu();
        }
        this.registerLoadingStatusCallback();
    }
    
    private void hidePlayerUI() {
        final View viewById = this.findViewById(2131689755);
        viewById.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new CoppolaDetailsActivity$4(this, viewById));
    }
    
    private void reAttachMdxMiniPlayer() {
        this.reattachFragment((Fragment)this.getMdxMiniPlayerFrag());
        this.getHandler().post((Runnable)new CoppolaDetailsActivity$1(this));
    }
    
    private void reattachFragment(final Fragment fragment) {
        if (fragment == null) {
            Log.w("CoppolaDetailsActivity", "reattachFragment - frag is null");
        }
        else {
            this.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commitAllowingStateLoss();
            if (fragment instanceof NetflixFrag) {
                ((NetflixFrag)fragment).onManagerReady(this.getServiceManager(), CommonStatus.OK);
                return;
            }
            if (fragment instanceof NetflixDialogFrag) {
                ((NetflixDialogFrag)fragment).onManagerReady(this.getServiceManager(), CommonStatus.OK);
                return;
            }
            if (Log.isLoggable()) {
                Log.w("CoppolaDetailsActivity", "Couldn't call omManagerReady() - unrecognized type: " + fragment);
            }
        }
    }
    
    private void refreshVideoTypeAndContext() {
        this.videoType = (VideoType)this.getIntent().getSerializableExtra("extra_video_type");
        this.setPlayContext((PlayContext)this.getIntent().getParcelableExtra("extra_playcontext"));
    }
    
    private void removeControlsIfNeeded() {
        if (this.bIsInPortrait) {
            this.playerFragment.clearPanel();
            this.playerFragment.showNavigationBar();
            return;
        }
        this.playerFragment.hideNavigationBar();
    }
    
    private void removeStatusBar() {
        this.requestWindowFeature(1);
        this.getWindow().setFlags(1024, 1024);
    }
    
    private void setupImageContainer() {
        final TappableSurfaceView tappableSurfaceView = (TappableSurfaceView)this.playerFragment.getView().findViewById(2131690123);
        if (this.bIsInPortrait) {
            this.getNetflixActionBar().show(false);
            final int n = (int)(DeviceUtils.getScreenHeightInPixels((Context)this) / 2.5f);
            this.imageContainer.getLayoutParams().height = n;
            if (tappableSurfaceView != null) {
                ((ViewGroup)tappableSurfaceView.getParent()).getLayoutParams().height = n;
            }
            final Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(0);
            window.addFlags(65536);
            if (this.detailsFrag != null && this.detailsFrag.getView() != null) {
                final View viewById = this.detailsFrag.getView().findViewById(16908298);
                if (viewById instanceof RecyclerView) {
                    ((RecyclerView)viewById).getAdapter().notifyDataSetChanged();
                }
                this.addTopGradientIfNeeded();
            }
        }
        else {
            this.getNetflixActionBar().hide(false);
            this.imageContainer.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
            if (tappableSurfaceView != null) {
                ((ViewGroup)tappableSurfaceView.getParent()).getLayoutParams().height = DeviceUtils.getScreenHeightInPixels((Context)this) - 1;
            }
        }
        if (tappableSurfaceView != null) {
            tappableSurfaceView.postDelayed((Runnable)new CoppolaDetailsActivity$2(this, tappableSurfaceView), (long)CoppolaDetailsActivity.MIN_SAMSUNG_POST_DRAW_INTERVAL_MS);
        }
        if (this.startOrientation == 0) {
            int startOrientation;
            if (this.bIsInPortrait) {
                startOrientation = 1;
            }
            else {
                startOrientation = 2;
            }
            this.startOrientation = startOrientation;
        }
        if (this.startOrientation == 2 && this.bIsInPortrait) {
            this.reattachFragment(this.detailsFrag);
            this.startOrientation = 1;
        }
    }
    
    private void updatePlayContextBrowsePlayMode() {
        final PlayContext playContext = this.getPlayContext();
        if (playContext != null) {
            if (Log.isLoggable()) {
                Log.v("CoppolaDetailsActivity", "Updating play context, browsePlay: " + Coppola1Utils.isBrowsePlay(this));
            }
            playContext.setBrowsePlay(Coppola1Utils.isBrowsePlay(this));
        }
    }
    
    @Override
    protected void configureLinearLayout() {
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        final int intExtra = this.getIntent().getIntExtra("playback_start_seconds", -1);
        final String playableId = this.getPlayableId();
        Fragment playerFragment = null;
        if (StringUtils.isNotEmpty(playableId)) {
            playerFragment = PlayerFragment.createPlayerFragment(playableId, this.getPlayableVideoType(), this.playContext, intExtra);
            this.hidePlayerUI();
        }
        return playerFragment;
    }
    
    @Override
    protected Fragment createSecondaryFrag() {
        if (this.videoType == VideoType.MOVIE) {
            return MovieDetailsFrag.create(this.videoId);
        }
        return this.createEpisodesFrag();
    }
    
    @Override
    public boolean destroyed() {
        return AndroidUtils.isActivityFinishedOrDestroyed((Context)this);
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903091;
    }
    
    @Override
    public NetflixDialogFrag$DialogCanceledListener getDialogCanceledListener() {
        return this.playerFragment.getDialogCanceledListener();
    }
    
    @Override
    public AbsEpisodeView$EpisodeRowListener getEpisodeRowListener() {
        if (MdxUtils.isCurrentMdxTargetAvailable(this.getServiceManager())) {
            final AbsEpisodeView$EpisodeRowListener episodeRowListener = super.getEpisodeRowListener();
            if (episodeRowListener != null) {
                return episodeRowListener;
            }
        }
        return this.playerFragment.getEpisodeRowListener();
    }
    
    @Override
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    @Override
    public VideoType getVideoType() {
        return this.videoType;
    }
    
    @Override
    protected boolean handleBackPressed() {
        if (!this.bIsInPortrait) {
            return this.playerFragment.handleBackPressed();
        }
        if (!this.backStack.isEmpty()) {
            final MovieDetailsActivity$BackStackData movieDetailsActivity$BackStackData = this.backStack.pop();
            final Intent intent = new Intent();
            intent.putExtra("extra_video_id", movieDetailsActivity$BackStackData.videoId);
            intent.putExtra("extra_playcontext", (Parcelable)movieDetailsActivity$BackStackData.playContext);
            VideoType videoType;
            if (movieDetailsActivity$BackStackData.bIsShow) {
                videoType = VideoType.SHOW;
            }
            else {
                videoType = VideoType.MOVIE;
            }
            intent.putExtra("extra_video_type", (Serializable)videoType);
            this.setIntent(intent);
            this.handleNewIntent(intent);
            return true;
        }
        return super.handleBackPressed();
    }
    
    public void handleMDXIconClick() {
        this.playerFragment.handleMdxClick();
    }
    
    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
        Log.d("CoppolaDetailsActivity", "Check if MDX status is changed");
        if (this.playerFragment != null) {
            this.playerFragment.setTargetSelection();
        }
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        boolean bIsInPortrait = true;
        super.onConfigurationChanged(configuration);
        if (configuration.orientation != 1) {
            bIsInPortrait = false;
        }
        this.bIsInPortrait = bIsInPortrait;
        this.removeControlsIfNeeded();
        this.setupImageContainer();
        this.updatePlayContextBrowsePlayMode();
        this.reAttachMdxMiniPlayer();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.removeStatusBar();
        this.refreshVideoTypeAndContext();
        super.onCreate(bundle);
        this.imageContainer = this.findViewById(2131689754);
        if (this.getIntent() != null && this.getIntent().hasExtra("push_to_landscape")) {
            this.setRequestedOrientation(6);
        }
        this.bIsInPortrait = DeviceUtils.isPortrait((Context)this);
        this.dynamicToolbarMenu = this.getNetflixActionBar().getToolbar().getMenu();
        this.getNetflixActionBar().hidelogo();
        this.getSupportActionBar().setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.playerFragment = (PlayerFragment)this.getPrimaryFrag();
        this.detailsFrag = this.getSecondaryFrag();
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (this.dynamicToolbarMenu == menu) {
            super.onCreateOptionsMenu(menu, menu2);
        }
        else if (!Coppola1Utils.isAutoplay((Context)this) && this.dynamicToolbarMenu != null) {
            this.dynamicToolbarMenu.clear();
            super.onCreateOptionsMenu(this.dynamicToolbarMenu, null);
        }
    }
    
    @Override
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return (!this.bIsInPortrait && this.playerFragment.onKeyDown(n, keyEvent)) || super.onKeyDown(n, keyEvent);
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        if (PlayerActivity.shouldResumePreviousPlay(this.getIntent(), intent)) {
            Log.d("CoppolaDetailsActivity", "Got same video ID - resuming the playback...");
            return;
        }
        this.setIntent(intent);
        if (this.playerFragment == null) {
            ErrorLoggingManager.logHandledException("SPY-8343 - CoppolaDetailsActivity received onNewIntent() prior to onCreate() - skipping to avoid crash");
            return;
        }
        if (StringUtils.isNotEmpty(this.getVideoId())) {
            final MovieDetailsActivity$BackStackData movieDetailsActivity$BackStackData = new MovieDetailsActivity$BackStackData(this.getVideoId(), this.getPlayContext(), null);
            movieDetailsActivity$BackStackData.bIsShow = (this.videoType == VideoType.SHOW);
            this.backStack.push(movieDetailsActivity$BackStackData);
        }
        this.handleNewIntent(intent);
    }
    
    @Override
    public void onPlayVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
        if (this.playerFragment != null) {
            this.playerFragment.onPlayVerified(b, playVerifierVault);
        }
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        this.setupImageContainer();
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (this.playerFragment != null) {
            this.playerFragment.onWindowFocusChanged(b);
        }
    }
    
    public void setEpisodesLayoutCurrentEpisodeId(final String currentEpisodeId, final int n) {
        if (this.detailsFrag instanceof CoppolaShowDetailsFrag) {
            ((CoppolaShowDetailsFrag)this.detailsFrag).setCurrentEpisodeId(currentEpisodeId);
        }
        if (this.detailsFrag instanceof EpisodesFrag) {
            ((EpisodesFrag)this.detailsFrag).switchSeason(n, false);
        }
    }
    
    @Override
    public void setPlayContext(final PlayContext playContext) {
        super.setPlayContext(playContext);
        this.updatePlayContextBrowsePlayMode();
    }
    
    public void updateIntent(final Asset asset) {
        if (this.getIntent() == null) {
            Log.e("CoppolaDetailsActivity", "SPY-9106 - got null getIntent() inside CoppolaDetailsActivity - skipping intent update");
            ErrorLoggingManager.logHandledException("SPY-9106 - got null getIntent() inside CoppolaDetailsActivity - skipping intent update");
            return;
        }
        asset.toIntent(this.getIntent());
    }
}
