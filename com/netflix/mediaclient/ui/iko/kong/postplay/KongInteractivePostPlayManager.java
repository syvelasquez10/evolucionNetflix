// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.view.TextureView;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.io.IOException;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.util.ThreadUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import android.content.Context;
import com.facebook.device.yearclass.YearClass;
import com.netflix.mediaclient.Log;
import java.util.Collections;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.ui.player.PostPlay;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.graphics.BitmapFactory$Options;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.view.View;
import android.os.Handler;
import android.animation.Animator;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.iko.InteractivePostPlayManager;

public class KongInteractivePostPlayManager implements InteractivePostPlayManager
{
    public static final String TAG = "KongInteractivePostPlayManager";
    private ViewGroup battleIntroContainer;
    private KongBattleIntroScreen battleIntroScreen;
    private VideoDetails battleLoseVideoDetails;
    private ImageView battleResultAvatar;
    private KongBattleResultScreen battleResultScreen;
    private VideoDetails battleWinVideoDetails;
    private String bgScoreUrl;
    private float bgScoreVolume;
    private KongBackgroundScreen bgScreen;
    Runnable cachingResourcesRunnable;
    private Animator currentAnimation;
    private KongInteractivePostPlayManager$BattleVideoDetailsForPlaybackCallback detailsForPlaybackCallback;
    private Runnable exitToStandardPostPlay;
    private int failureCount;
    private ViewGroup gear1Group;
    private ViewGroup gear2Group;
    private KongGearSelectionScreen gearSelectionScreen;
    private final Handler handler;
    private boolean hideAnimations;
    private boolean inKongPostPlay;
    private String initialState;
    private boolean isCachingInProgress;
    private boolean isEndOfPlayPostPlay;
    private boolean isHighPerfDevice;
    private boolean isInGearSelection;
    private boolean isInKongPostPlay;
    private boolean isLowPerfDevice;
    private View kongPostplayContainer;
    private MediaPlayerWrapper mediaPlayerWrapper;
    private ViewGroup nextEpisodeContainer;
    private VideoDetails nextEpisodeDetails;
    private BitmapFactory$Options options;
    private final PlayerFragment playerFragment;
    private final PostPlay postPlay;
    private boolean postPlayPaused;
    KongInteractivePostPlayManager$POST_PLAY_STATE postPlayState;
    private String postPlayType;
    private ViewGroup powerUpContainer;
    private KongPowerUpScreen powerUpScreen;
    private List<String> preCacheableResources;
    private int resourceResponseCounter;
    private Map<String, LocalCachedFileMetadata> resourceToLocalCacheFileMap;
    private int retryCounter;
    private KongSoundPoolManager soundPoolManager;
    private int trackId;
    KongInteractivePostPlayManager$UI_STATE uiState;
    private KongUnlockScreen unlockScreen;
    private ViewGroup unlockingGearContainer;
    Runnable verifySoundPoolLoadedRunnable;
    
    public KongInteractivePostPlayManager(final PostPlay postPlay, final KongInteractivePostPlayModel kongInteractivePostPlayModel) {
        final boolean b = true;
        this.handler = new Handler();
        this.preCacheableResources = Collections.emptyList();
        this.resourceToLocalCacheFileMap = Collections.emptyMap();
        this.uiState = KongInteractivePostPlayManager$UI_STATE.LOADING;
        this.postPlayState = KongInteractivePostPlayManager$POST_PLAY_STATE.INIT;
        this.verifySoundPoolLoadedRunnable = new KongInteractivePostPlayManager$4(this);
        this.cachingResourcesRunnable = new KongInteractivePostPlayManager$9(this);
        this.postPlay = postPlay;
        this.playerFragment = postPlay.getController();
        if (this.playerFragment == null) {
            Log.e("KongInteractivePostPlayManager", "Player fragment is null. Cannot initialize Kong post play manager");
            return;
        }
        this.bgScreen = new KongBackgroundScreen(this);
        this.unlockScreen = new KongUnlockScreen(this);
        this.battleIntroScreen = new KongBattleIntroScreen(this);
        this.gearSelectionScreen = new KongGearSelectionScreen(this);
        this.battleResultScreen = new KongBattleResultScreen(this);
        this.powerUpScreen = new KongPowerUpScreen(this);
        this.populatePostPlayData(kongInteractivePostPlayModel);
        this.inflateViews((ViewGroup)this.playerFragment.getView());
        this.initMediaPlayer();
        this.initKongSoundVOManager();
        final Integer value = YearClass.get((Context)this.playerFragment.getActivity());
        if (value != null) {
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", "YearClass is - " + value);
            }
            this.isLowPerfDevice = (value < 2012);
            this.isHighPerfDevice = (value >= 2015 && b);
        }
        this.options = new BitmapFactory$Options();
    }
    
    private void loadBackgroundScreenResources() {
        if (this.bgScreen != null) {
            this.bgScreen.loadResources();
        }
    }
    
    private void loadBattleIntroScreenResources() {
        if (this.battleIntroScreen != null) {
            this.battleIntroScreen.loadResources();
        }
    }
    
    private void loadBattleIntroScreenSoundResources() {
        if (this.battleIntroScreen != null) {
            this.battleIntroScreen.loadSoundPoolResources();
        }
    }
    
    private void loadBattleResultScreenResources() {
        if (this.battleResultScreen != null) {
            this.battleResultScreen.loadResources();
        }
    }
    
    private void loadBattleResultScreenSoundResources() {
        if (this.battleResultScreen != null) {
            this.battleResultScreen.loadSoundPoolResources();
        }
    }
    
    private void loadGearSelectionScreenResources() {
        if (this.gearSelectionScreen != null) {
            this.gearSelectionScreen.loadResources();
        }
    }
    
    private void loadGearSelectionScreenSoundResources() {
        if (this.gearSelectionScreen != null) {
            this.gearSelectionScreen.loadSoundPoolResources();
        }
    }
    
    private void loadPowerUpScreenResources() {
        if (this.powerUpScreen != null) {
            this.powerUpScreen.loadResources();
        }
    }
    
    private void loadPowerUpScreenSoundResources() {
        if (this.powerUpScreen != null) {
            this.powerUpScreen.loadSoundPoolResources();
        }
    }
    
    private void loadSoundResources() {
        AsyncTaskCompat.execute(new KongInteractivePostPlayManager$5(this));
    }
    
    private void loadUnlockScreenResources() {
        if (this.unlockScreen != null) {
            this.unlockScreen.loadResources();
        }
    }
    
    private void loadUnlockScreenSoundResources() {
        if (this.unlockScreen != null) {
            this.unlockScreen.loadSoundPoolResources();
        }
    }
    
    private void releaseBitmapMemory() {
        if (this.bgScreen != null) {
            this.bgScreen.releaseBitmapResources();
        }
        if (this.unlockScreen != null) {
            this.unlockScreen.releaseBitmapResources();
        }
        if (this.battleIntroScreen != null) {
            this.battleIntroScreen.releaseBitmapResources();
        }
        if (this.powerUpScreen != null) {
            this.powerUpScreen.releaseBitmapResources();
        }
        if (this.battleResultScreen != null) {
            this.battleResultScreen.releaseBitmapResources();
        }
        if (this.gearSelectionScreen != null) {
            this.gearSelectionScreen.releaseBitmapResources();
        }
    }
    
    private void showPostPlay() {
        if (this.bgScreen != null) {
            this.bgScreen.showProgress(false);
        }
        this.show();
        this.bgScreen.onResourcesLoaded();
        this.unlockScreen.onResourcesLoaded();
        this.battleIntroScreen.onResourcesLoaded();
        this.powerUpScreen.onResourcesLoaded();
        this.battleResultScreen.onResourcesLoaded();
        this.gearSelectionScreen.onResourcesLoaded();
        this.transitionToKongPostPlay();
        this.startPostPlayAnimation();
    }
    
    boolean areResourcesSuccessfullyLoaded() {
        return !this.isCachingInProgress && this.failureCount <= 0;
    }
    
    void cacheResource(final String s) {
        if (this.getActivity() == null) {
            Log.e("KongInteractivePostPlayManager", "Player activity is null, cannot cache resources");
        }
        else {
            if (StringUtils.isEmpty(s)) {
                ++this.resourceResponseCounter;
                Log.e("KongInteractivePostPlayManager", "Invalid request to cache resource for an empty or null url.");
                return;
            }
            final ServiceManager serviceManager = ServiceManager.getServiceManager(this.getActivity());
            if (serviceManager != null) {
                if (Log.isLoggable()) {
                    Log.d("KongInteractivePostPlayManager", "Fetching and caching resource " + s);
                }
                serviceManager.fetchAndCacheResource(s, IClientLogging$AssetType.interactiveContent, new KongInteractivePostPlayManager$2(this));
                return;
            }
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", "Service manger is either null, cannot cache resource - " + s);
            }
        }
    }
    
    void cancelCurrentAnimation() {
        if (this.currentAnimation != null) {
            this.currentAnimation.removeAllListeners();
            this.currentAnimation.cancel();
        }
    }
    
    void checkFailuresAndRetry() {
        this.isCachingInProgress = false;
        if (this.failureCount > 0) {
            final String string = "Caching resources failed. Number of resources failed - " + this.failureCount;
            this.showDebugToast(string);
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", string);
            }
            if (this.retryCounter < 3L) {
                this.handler.post(this.cachingResourcesRunnable);
                ++this.retryCounter;
            }
        }
    }
    
    void cleanup() {
        this.handler.removeCallbacksAndMessages((Object)null);
        this.releaseResources();
        this.releaseBitmapMemory();
    }
    
    Bitmap fetchImageFromCache(final String s) {
        return this.fetchImageFromCache(s, null);
    }
    
    Bitmap fetchImageFromCache(final String s, final BitmapFactory$Options bitmapFactory$Options) {
        ThreadUtils.assertNotOnMain();
        if (StringUtils.isEmpty(s)) {
            Log.e("KongInteractivePostPlayManager", "Empty or null request url to load image from cache");
            return null;
        }
        if (!this.resourceToLocalCacheFileMap.containsKey(s)) {
            Log.e("KongInteractivePostPlayManager", "Request url not cached locally. Ignore loading resource.");
            return null;
        }
        final LocalCachedFileMetadata localCachedFileMetadata = this.resourceToLocalCacheFileMap.get(s);
        if (localCachedFileMetadata == null) {
            Log.e("KongInteractivePostPlayManager", "Request url cached locally, but metadata information is empty or null.");
            return null;
        }
        final String localUrl = localCachedFileMetadata.getLocalUrl();
        if (Log.isLoggable()) {
            Log.d("KongInteractivePostPlayManager", "Loading image from cache for url " + s + " Local url = " + localUrl);
        }
        final File file = new File(localUrl);
        if (!file.exists()) {
            return null;
        }
        try {
            return BitmapFactory.decodeByteArray(FileUtils.readFileToByteArray(file), (int)localCachedFileMetadata.getByteOffset(), (int)localCachedFileMetadata.getByteSize(), bitmapFactory$Options);
        }
        catch (IOException ex2) {
            Log.e("KongInteractivePostPlayManager", "Error loading image from cache for url " + localUrl);
            return null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            Log.e("KongInteractivePostPlayManager", "Exiting kong post play because of OutOfMemoryError - " + outOfMemoryError);
            this.handler.post(this.exitToStandardPostPlay);
            return null;
        }
        catch (Exception ex) {
            Log.e("KongInteractivePostPlayManager", "Error loading image from cache for url " + localUrl + " Exception - " + ex.getMessage());
            return null;
        }
        return null;
    }
    
    NetflixActivity getActivity() {
        if (this.getPlayerFragment() == null) {
            return null;
        }
        return this.playerFragment.getNetflixActivity();
    }
    
    PressAnimationFrameLayout getBattleIntroCompoundView() {
        return this.battleIntroScreen.getBattleIntroCompoundView();
    }
    
    ViewGroup getBattleIntroContainer() {
        return this.battleIntroContainer;
    }
    
    KongBattleIntroScreen getBattleIntroScreen() {
        return this.battleIntroScreen;
    }
    
    int getBattleLoseTrackId() {
        if (this.gearSelectionScreen == null) {
            Log.d("KongInteractivePostPlayManager", "Gear selection screen is null. No track id available for battle lost video");
            return -1;
        }
        return this.gearSelectionScreen.getBattleLoseTrackId();
    }
    
    int getBattleLoseVideoId() {
        return this.gearSelectionScreen.getBattleLostVideoId();
    }
    
    ImageView getBattleResultAvatar() {
        return this.battleResultAvatar;
    }
    
    int getBattleWinTrackId() {
        if (this.gearSelectionScreen == null) {
            Log.d("KongInteractivePostPlayManager", "Gear selection screen is null. No track id available for battle won video");
            return -1;
        }
        return this.gearSelectionScreen.getBattleWinTrackId();
    }
    
    int getBattleWinVideoId() {
        return this.gearSelectionScreen.getBattleWinVideoId();
    }
    
    ViewGroup getGear1Group() {
        return this.gear1Group;
    }
    
    ViewGroup getGear2Group() {
        return this.gear2Group;
    }
    
    Handler getHandler() {
        return this.handler;
    }
    
    ViewGroup getNextEpisodeContainer() {
        return this.nextEpisodeContainer;
    }
    
    int getNextEpisodeVideoId() {
        return this.battleIntroScreen.getNextEpisodeVideoId();
    }
    
    AdvancedImageView getNextEpisodeView() {
        return this.battleIntroScreen.getNextEpisodeView();
    }
    
    PlayerFragment getPlayerFragment() {
        return this.playerFragment;
    }
    
    KongInteractivePostPlayManager$POST_PLAY_STATE getPostPlayState() {
        return this.postPlayState;
    }
    
    ViewGroup getPowerUpContainer() {
        return this.powerUpContainer;
    }
    
    public LocalCachedFileMetadata getResourceToLocalCacheFileMap(final String s) {
        if (StringUtils.isEmpty(s) || this.resourceToLocalCacheFileMap == null) {
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", "Request for a null url from resources map.");
            }
            return null;
        }
        return this.resourceToLocalCacheFileMap.get(s);
    }
    
    KongSoundPoolManager getSoundPoolManager() {
        if (this.soundPoolManager != null) {
            return this.soundPoolManager;
        }
        return null;
    }
    
    ViewGroup getUnlockingGearContainer() {
        return this.unlockingGearContainer;
    }
    
    void hide() {
        this.inKongPostPlay = false;
        this.cancelCurrentAnimation();
        this.handler.removeCallbacksAndMessages((Object)null);
        ViewUtils.setVisibleOrGone(this.kongPostplayContainer, false);
        ViewUtils.setVisibleOrGone((View)this.battleIntroContainer, false);
        ViewUtils.setVisibleOrGone((View)this.nextEpisodeContainer, false);
        ViewUtils.setVisibleOrGone((View)this.battleResultAvatar, false);
        ViewUtils.setVisibleOrGone((View)this.unlockingGearContainer, false);
        ViewUtils.setVisibleOrGone((View)this.powerUpContainer, false);
        ViewUtils.setVisibleOrGone((View)this.gear1Group, false);
        ViewUtils.setVisibleOrGone((View)this.gear2Group, false);
    }
    
    View inflateViews(final ViewGroup viewGroup) {
        viewGroup.addView(this.kongPostplayContainer = ((LayoutInflater)this.getActivity().getSystemService("layout_inflater")).inflate(2130903237, viewGroup, false));
        this.unlockingGearContainer = (ViewGroup)this.kongPostplayContainer.findViewById(2131690089);
        this.powerUpContainer = (ViewGroup)this.kongPostplayContainer.findViewById(2131690102);
        this.gear1Group = (ViewGroup)this.kongPostplayContainer.findViewById(2131690099);
        this.gear2Group = (ViewGroup)this.kongPostplayContainer.findViewById(2131690100);
        this.battleIntroContainer = (ViewGroup)this.kongPostplayContainer.findViewById(2131690094);
        this.nextEpisodeContainer = (ViewGroup)this.kongPostplayContainer.findViewById(2131690090);
        this.battleResultAvatar = (ImageView)this.kongPostplayContainer.findViewById(2131690101);
        this.bgScreen.initViews(this.kongPostplayContainer);
        this.unlockScreen.initViews((View)this.unlockingGearContainer);
        this.powerUpScreen.initViews((View)this.powerUpContainer);
        this.gearSelectionScreen.initViews(this.kongPostplayContainer);
        this.battleIntroScreen.initViews(this.kongPostplayContainer);
        this.battleResultScreen.initViews(this.kongPostplayContainer);
        ViewUtils.setVisibleOrGone(this.kongPostplayContainer, false);
        return this.kongPostplayContainer;
    }
    
    void initKongSoundVOManager() {
        if (this.soundPoolManager == null) {
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", "SoundPool manager is null.");
            }
            this.soundPoolManager = new KongSoundPoolManager(this);
        }
    }
    
    void initMediaPlayer() {
        this.mediaPlayerWrapper = new MediaPlayerWrapper(null, true, -1, this.bgScoreVolume, IClientLogging$AssetType.interactiveContent, new KongInteractivePostPlayManager$7(this));
    }
    
    void initMediaPlayerForBgAudio() {
        if (StringUtils.isEmpty(this.bgScoreUrl)) {
            Log.e("KongInteractivePostPlayManager", "Not starting media player for background sound");
            return;
        }
        if (this.mediaPlayerWrapper == null) {
            this.initMediaPlayer();
        }
        final LocalCachedFileMetadata localCachedFileMetadata = this.resourceToLocalCacheFileMap.get(this.bgScoreUrl);
        if (localCachedFileMetadata == null) {
            Log.e("KongInteractivePostPlayManager", "Media player audio is cached locally, but metadata information is null.");
            return;
        }
        this.mediaPlayerWrapper.setDataSource(localCachedFileMetadata.getLocalUrl(), localCachedFileMetadata.getByteOffset(), localCachedFileMetadata.getByteSize());
        this.mediaPlayerWrapper.initializeMediaPlayer();
    }
    
    boolean isActivityValid() {
        return this.getPlayerFragment() != null && this.playerFragment.isActivityValid();
    }
    
    public boolean isEndOfPlayPostPlay() {
        return this.isEndOfPlayPostPlay;
    }
    
    public boolean isHighPerfDevice() {
        return this.isHighPerfDevice;
    }
    
    boolean isInGearSelection() {
        return this.isInGearSelection;
    }
    
    boolean isInKongPostPlay() {
        return this.isInKongPostPlay;
    }
    
    public boolean isPostPlayPaused() {
        return this.postPlayPaused;
    }
    
    void loadImageBitmapFromCache(final ImageView imageView, final String s) {
        this.loadImageBitmapFromCache(imageView, s, null);
    }
    
    void loadImageBitmapFromCache(final ImageView imageView, final String s, final BitmapFactory$Options bitmapFactory$Options) {
        ThreadUtils.assertNotOnMain();
        if (imageView == null) {
            Log.e("KongInteractivePostPlayManager", "Request to load url into a null ImageView = " + s);
        }
        else {
            if (!this.inKongPostPlay) {
                Log.d("KongInteractivePostPlayManager", "Not in kong post play. Ignoring request to load bitmap.");
                return;
            }
            final Bitmap fetchImageFromCache = this.fetchImageFromCache(s, bitmapFactory$Options);
            if (fetchImageFromCache != null) {
                this.handler.post((Runnable)new KongInteractivePostPlayManager$3(this, imageView, fetchImageFromCache));
            }
        }
    }
    
    void loadPostPlayResources() {
        if (!this.isActivityValid()) {
            Log.w("KongInteractivePostPlayManager", "Activity isn't in a valid state - no need to load post play resources");
            return;
        }
        ThreadUtils.assertNotOnMain();
        this.handler.removeCallbacks(this.exitToStandardPostPlay);
        this.initKongSoundVOManager();
        this.initMediaPlayerForBgAudio();
        if (this.soundPoolManager != null) {
            this.soundPoolManager.createSoundPool();
        }
        else {
            Log.d("KongInteractivePostPlayManager", "SoundPoolManager is null, cannot create soundPool instance.");
        }
        this.loadBackgroundScreenResources();
        this.loadUnlockScreenResources();
        this.loadPowerUpScreenResources();
        this.loadBattleIntroScreenResources();
        this.loadGearSelectionScreenResources();
        this.loadBattleResultScreenResources();
        this.handler.postDelayed(this.verifySoundPoolLoadedRunnable, 1000L);
    }
    
    public void moveToUIState() {
        switch (KongInteractivePostPlayManager$11.$SwitchMap$com$netflix$mediaclient$ui$iko$kong$postplay$KongInteractivePostPlayManager$UI_STATE[this.uiState.ordinal()]) {
            default: {}
            case 1: {
                this.showPostPlay();
            }
            case 2: {
                this.showUnlockedGear();
            }
            case 3: {
                this.resumeBattleIntro();
            }
            case 4: {
                this.resumeGearSelection();
            }
            case 5: {
                this.showBattleResult();
            }
            case 6: {
                this.showPowerUp();
            }
        }
    }
    
    @Override
    public void onDestroy() {
        this.cleanup();
    }
    
    @Override
    public void onPause() {
        if (this.soundPoolManager != null) {
            this.soundPoolManager.autoPause();
        }
        if (this.mediaPlayerWrapper != null) {
            this.mediaPlayerWrapper.pausePlayback();
        }
        if (this.currentAnimation != null && this.currentAnimation.isRunning()) {
            this.currentAnimation.pause();
        }
        this.postPlayPaused = true;
    }
    
    boolean onResourcesLoaded() {
        if (!this.isActivityValid()) {
            return false;
        }
        this.isInKongPostPlay = true;
        this.moveToUIState();
        return true;
    }
    
    @Override
    public void onResume() {
        if (this.soundPoolManager != null) {
            this.soundPoolManager.autoResume();
        }
        if (this.mediaPlayerWrapper != null) {
            this.mediaPlayerWrapper.resumePlayback();
        }
        if (this.currentAnimation != null && this.currentAnimation.isPaused()) {
            this.currentAnimation.resume();
        }
        this.postPlayPaused = false;
    }
    
    @Override
    public void onStart() {
        if (this.soundPoolManager == null) {
            this.initKongSoundVOManager();
            this.soundPoolManager.createSoundPool();
        }
        if (this.mediaPlayerWrapper == null) {
            this.initMediaPlayer();
        }
        if (this.inKongPostPlay) {
            this.loadSoundResources();
            this.handler.removeCallbacks(this.verifySoundPoolLoadedRunnable);
            this.handler.postDelayed(this.verifySoundPoolLoadedRunnable, 1000L);
        }
    }
    
    @Override
    public void onStop() {
        if (this.currentAnimation != null) {
            this.currentAnimation.end();
            this.currentAnimation = null;
        }
        this.releaseResources();
    }
    
    void populatePostPlayData(final KongInteractivePostPlayModel kongInteractivePostPlayModel) {
        if (kongInteractivePostPlayModel == null) {
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", "Interactive post play data is null. Cannot populate resources.");
            }
        }
        else {
            if (Log.isLoggable()) {
                Log.v("KongInteractivePostPlayManager", "Kong interactive postPlay data: - " + kongInteractivePostPlayModel);
            }
            this.preCacheableResources = kongInteractivePostPlayModel.getPreCacheableResourcesList();
            this.resourceToLocalCacheFileMap = kongInteractivePostPlayModel.getResourceUrlToLocalCachedFileMetadataMap();
            this.initialState = kongInteractivePostPlayModel.getInitialState();
            this.postPlayState = KongInteractivePostPlayManager$POST_PLAY_STATE.valueOf(this.initialState);
            this.postPlayType = kongInteractivePostPlayModel.getPostPlayType();
            this.trackId = kongInteractivePostPlayModel.getTrackId();
            this.hideAnimations = kongInteractivePostPlayModel.shouldHideAnimation();
            this.bgScreen.loadPostPlayData(kongInteractivePostPlayModel);
            this.unlockScreen.loadPostPlayData(kongInteractivePostPlayModel);
            this.battleIntroScreen.loadPostPlayData(kongInteractivePostPlayModel);
            this.powerUpScreen.loadPostPlayData(kongInteractivePostPlayModel);
            this.battleResultScreen.loadPostPlayData(kongInteractivePostPlayModel);
            this.gearSelectionScreen.loadPostPlayData(kongInteractivePostPlayModel);
            final KongInteractivePostPlayModel$KongSound backgroundSound = kongInteractivePostPlayModel.getBackgroundSound();
            if (backgroundSound != null) {
                this.bgScoreUrl = backgroundSound.getUrl();
                this.bgScoreVolume = backgroundSound.getVolume();
            }
            this.detailsForPlaybackCallback = new KongInteractivePostPlayManager$BattleVideoDetailsForPlaybackCallback(this);
            final int battleWinVideoId = this.gearSelectionScreen.getBattleWinVideoId();
            final int battleLostVideoId = this.gearSelectionScreen.getBattleLostVideoId();
            final int nextEpisodeVideoId = this.battleIntroScreen.getNextEpisodeVideoId();
            if (battleWinVideoId > 0) {
                this.requestDetails(String.valueOf(battleWinVideoId), VideoType.MOVIE);
            }
            if (battleLostVideoId > 0) {
                this.requestDetails(String.valueOf(battleLostVideoId), VideoType.MOVIE);
            }
            if (nextEpisodeVideoId > 0) {
                this.requestDetails(String.valueOf(nextEpisodeVideoId), VideoType.EPISODE);
            }
        }
    }
    
    void releaseResources() {
        this.handler.removeCallbacksAndMessages((Object)null);
        if (this.mediaPlayerWrapper != null) {
            this.mediaPlayerWrapper.releaseResources();
            this.mediaPlayerWrapper = null;
        }
        if (this.soundPoolManager != null) {
            this.soundPoolManager.release();
            this.soundPoolManager = null;
        }
    }
    
    public void removeHandlerCallbacksAndMessages() {
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages((Object)null);
        }
    }
    
    void requestDetails(final String s, final VideoType videoType) {
        if (StringUtils.isNotEmpty(s)) {
            if (videoType == VideoType.MOVIE) {
                this.getActivity().getServiceManager().getBrowse().fetchMovieDetails(s, null, this.detailsForPlaybackCallback);
            }
            else {
                if (VideoType.EPISODE.equals(videoType)) {
                    this.getActivity().getServiceManager().getBrowse().fetchEpisodeDetails(s, null, this.detailsForPlaybackCallback);
                    return;
                }
                throw new IllegalStateException("Invalid video type: " + videoType);
            }
        }
    }
    
    void requestDetails(final String s, final VideoType videoType, final KongInteractivePostPlayManager$BattleVideoDetailsForPlaybackCallback kongInteractivePostPlayManager$BattleVideoDetailsForPlaybackCallback) {
        if (StringUtils.isNotEmpty(s)) {
            if (videoType == VideoType.MOVIE) {
                this.getActivity().getServiceManager().getBrowse().fetchMovieDetails(s, null, kongInteractivePostPlayManager$BattleVideoDetailsForPlaybackCallback);
            }
            else {
                if (VideoType.EPISODE.equals(videoType)) {
                    this.getActivity().getServiceManager().getBrowse().fetchEpisodeDetails(s, null, kongInteractivePostPlayManager$BattleVideoDetailsForPlaybackCallback);
                    return;
                }
                throw new IllegalStateException("Invalid video type: " + videoType);
            }
        }
    }
    
    public void resumeBattleIntro() {
        if (this.battleIntroScreen != null) {
            this.battleIntroScreen.playBattleSound();
            this.battleIntroScreen.startTimer();
        }
    }
    
    public void resumeGearSelection() {
        if (this.gearSelectionScreen != null) {
            this.gearSelectionScreen.playPrepareBattleSound();
            this.gearSelectionScreen.setCountdownTimerText();
        }
    }
    
    public void setCurrentAnimation(final Animator currentAnimation) {
        this.currentAnimation = currentAnimation;
    }
    
    void setTitleText(final String titleText) {
        if (this.bgScreen != null) {
            this.bgScreen.setTitleText(titleText);
        }
    }
    
    public void setUserInteraction() {
        if (this.playerFragment != null) {
            this.playerFragment.setUserInteraction();
        }
    }
    
    boolean shouldHideAnimation() {
        return this.hideAnimations || this.isLowPerfDevice || (this.bgScreen != null && this.bgScreen.getForceToggleAnimations());
    }
    
    void show() {
        this.kongPostplayContainer.setAlpha(0.0f);
        ViewUtils.setVisibleOrGone(this.kongPostplayContainer, true);
        this.kongPostplayContainer.animate().alpha(1.0f).start();
    }
    
    void showBattleIntro() {
        if (this.battleIntroScreen == null) {
            Log.e("KongInteractivePostPlayManager", "Battle intro screen is null. Cannot show screen.");
            return;
        }
        this.uiState = KongInteractivePostPlayManager$UI_STATE.PICK_BATTLE;
        if (Log.isLoggable()) {
            Log.d("KongInteractivePostPlayManager", "Kong Post Play UI State - " + this.uiState);
        }
        this.isInGearSelection = false;
        this.battleIntroScreen.start();
    }
    
    void showBattleResult() {
        if (this.battleResultScreen == null) {
            Log.e("KongInteractivePostPlayManager", "Battle result screen is null. Cannot show screen.");
            return;
        }
        this.uiState = KongInteractivePostPlayManager$UI_STATE.BATTLE_RESULT;
        if (Log.isLoggable()) {
            Log.d("KongInteractivePostPlayManager", "Kong Post Play UI State - " + this.uiState);
        }
        this.battleResultScreen.start();
    }
    
    void showDebugToast(final String s) {
    }
    
    void showGearSelection() {
        if (this.gearSelectionScreen == null) {
            Log.e("KongInteractivePostPlayManager", "Gear selection screen is null. Cannot show screen.");
            return;
        }
        this.isInGearSelection = true;
        this.uiState = KongInteractivePostPlayManager$UI_STATE.GEAR_SELECTION;
        if (Log.isLoggable()) {
            Log.d("KongInteractivePostPlayManager", "Kong Post Play UI State - " + this.uiState);
        }
        this.gearSelectionScreen.start();
    }
    
    void showPowerUp() {
        if (this.powerUpScreen == null) {
            Log.e("KongInteractivePostPlayManager", "Power up screen is null. Cannot show screen.");
            return;
        }
        this.uiState = KongInteractivePostPlayManager$UI_STATE.POWER_UP;
        if (Log.isLoggable()) {
            Log.d("KongInteractivePostPlayManager", "Kong Post Play UI State - " + this.uiState);
        }
        this.powerUpScreen.start();
    }
    
    void showUnlockedGear() {
        if (this.unlockScreen == null) {
            Log.e("KongInteractivePostPlayManager", "Unlock screen is null. Cannot show screen.");
            return;
        }
        this.uiState = KongInteractivePostPlayManager$UI_STATE.UNLOCKING;
        if (Log.isLoggable()) {
            Log.d("KongInteractivePostPlayManager", "Kong Post Play UI State - " + this.uiState);
        }
        this.unlockScreen.start();
    }
    
    void startPlayback(final int n, int battleWinVideoId, final VideoType videoType, final boolean b) {
        if (this.getPlayerFragment() == null) {
            Log.e("KongInteractivePostPlayManager", "Player Fragment is null.");
            return;
        }
        final PlayContextImp playContextImp = new PlayContextImp("iko_request_id", battleWinVideoId, -1, -1);
        VideoDetails videoDetails = null;
        battleWinVideoId = this.gearSelectionScreen.getBattleWinVideoId();
        final int battleLostVideoId = this.gearSelectionScreen.getBattleLostVideoId();
        final int nextEpisodeVideoId = this.battleIntroScreen.getNextEpisodeVideoId();
        if (n == battleWinVideoId) {
            videoDetails = this.battleWinVideoDetails;
        }
        else if (n == battleLostVideoId) {
            videoDetails = this.battleLoseVideoDetails;
        }
        else if (n == nextEpisodeVideoId) {
            videoDetails = this.nextEpisodeDetails;
        }
        if (videoDetails == null) {
            this.requestDetails(String.valueOf(n), videoType, new KongInteractivePostPlayManager$6(this, playContextImp, b));
            ErrorLoggingManager.logHandledException(String.format("IKO-Kong: Video details in null for next battle/episode video.", new Object[0]));
            this.postPlay.reportNextPlayFailed(false);
            return;
        }
        this.playerFragment.playNextVideo(videoDetails.getPlayable(), playContextImp, b);
        this.releaseResources();
    }
    
    @Override
    public void startPostPlay(final boolean isEndOfPlayPostPlay) {
        this.isEndOfPlayPostPlay = isEndOfPlayPostPlay;
        this.hide();
        this.inKongPostPlay = true;
        this.uiState = KongInteractivePostPlayManager$UI_STATE.LOADING;
        if (Log.isLoggable()) {
            Log.d("KongInteractivePostPlayManager", "Kong Post Play UI State - " + this.uiState);
        }
        if (this.bgScreen != null) {
            this.bgScreen.showProgress(true);
        }
        if (this.failureCount > 0 && this.retryCounter >= 3L) {
            this.startPreCachingResources();
        }
        if (this.kongPostplayContainer != null) {
            this.kongPostplayContainer.setAlpha(0.0f);
            ViewUtils.setVisibleOrGone(this.kongPostplayContainer, true);
        }
        this.verifyAndLoadResources();
        this.exitToStandardPostPlay = new KongInteractivePostPlayManager$1(this);
        this.handler.postDelayed(this.exitToStandardPostPlay, 5000L);
    }
    
    void startPostPlayAnimation() {
        this.handler.postDelayed((Runnable)new KongInteractivePostPlayManager$8(this), 1000L);
    }
    
    @Override
    public void startPreCachingResources() {
        this.handler.post(this.cachingResourcesRunnable);
    }
    
    void transitionToKongPostPlay() {
        if (this.isActivityValid()) {
            this.playerFragment.doPause();
            this.playerFragment.getWindow().clearFlags(1024);
            this.playerFragment.showNavigationBar();
            return;
        }
        Log.e("KongInteractivePostPlayManager", "PlayerFragment is null. Should not happen.");
    }
    
    void verifyAndLoadResources() {
        this.handler.post((Runnable)new KongInteractivePostPlayManager$10(this));
    }
    
    @Override
    public boolean waitUntilEndOfPlay() {
        return this.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.RESULT;
    }
}
