// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.model.leafs.PostPlayAction;
import com.netflix.model.leafs.PostPlayItem;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.text.TextUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IPlayer;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import com.netflix.model.leafs.PostPlayExperience;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.model.leafs.InteractivePostplay;
import android.widget.LinearLayout;
import com.netflix.mediaclient.ui.iko.InteractivePostPlayManager;
import com.netflix.mediaclient.util.TimeUtils$CountdownTimer;

public abstract class PostPlay
{
    private static final int DEFAULT_FETCH_POSTPLAY_MS = 10000;
    protected static final int DEFAULT_INTERRUPTER_COUNT = 3;
    private static final int DEFAULT_INTERRUPTER_TIMEOUT_IN_MS = 3600000;
    private static final int DEFAULT_OFFSET_MS = 10000;
    private static final int INTERRUPTER_VALUE_IN_MS = 120000;
    protected static final String TAG = "nf_postplay";
    protected TimeUtils$CountdownTimer autoplayTimer;
    protected InteractivePostPlayManager interactivePostPlayManager;
    public boolean isInteractivePostPlay;
    protected LinearLayout mBackgroundContainer;
    protected PostPlay$FetchPostPlayForPlaybackCallback mFetchPostPlayForPlaybackCallback;
    protected int mFetchPostplayOffsetMs;
    protected boolean mInPostPlay;
    private InteractivePostplay mInteractivePostPlay;
    protected int mInterrputerTimeoutOffset;
    protected View mInterrupter;
    protected View mInterrupterContinue;
    protected View mInterrupterPlayFromStart;
    protected View mInterrupterStop;
    protected LinearLayout mItemsContainer;
    protected NetflixActivity mNetflixActivity;
    protected int mOffsetMs;
    protected PlayerFragment mPlayerFragment;
    protected View mPostPlay;
    protected boolean mPostPlayDataExist;
    protected PostPlay$PostPlayDataFetchStatus mPostPlayDataFetchStatus;
    protected boolean mPostPlayDismissed;
    protected PostPlayExperience mPostPlayExperience;
    protected View mPostPlayIgnoreTap;
    private boolean mSeamless;
    protected TextView mSynopsis;
    protected TextView mTitle;
    private final Runnable onInterrupterDismiss;
    private final Runnable onInterrupterStart;
    
    protected PostPlay(final NetflixActivity mNetflixActivity) {
        this.mOffsetMs = 10000;
        this.mFetchPostplayOffsetMs = 10000;
        this.mInterrputerTimeoutOffset = 3600000;
        this.mSeamless = false;
        this.onInterrupterStart = new PostPlay$7(this);
        this.onInterrupterDismiss = new PostPlay$8(this);
        this.mNetflixActivity = mNetflixActivity;
        this.findViewsCommon();
        this.findViews();
        this.setClickListeners();
        this.mOffsetMs = mNetflixActivity.getResources().getInteger(2131492886) * 1000;
        this.mFetchPostplayOffsetMs = this.mOffsetMs;
        this.mPostPlayDataFetchStatus = PostPlay$PostPlayDataFetchStatus.notStarted;
    }
    
    protected PostPlay(final PlayerFragment mPlayerFragment) {
        final int n = 3;
        this(mPlayerFragment.getNetflixActivity());
        this.mPlayerFragment = mPlayerFragment;
        final Asset currentAsset = this.mPlayerFragment.getCurrentAsset();
        if (currentAsset == null) {
            if (Log.isLoggable()) {
                Log.d("nf_postplay", "PostPlay: current asset is null");
            }
        }
        else {
            final int autoPlayMaxCount = currentAsset.getAutoPlayMaxCount();
            final int postPlayVideoPlayed = currentAsset.getPostPlayVideoPlayed();
            int n2;
            if (autoPlayMaxCount <= -1) {
                n2 = n;
                if (Log.isLoggable()) {
                    Log.d("nf_postplay", "PostPlay: autoPlayMaxCount = " + autoPlayMaxCount + " resetting to " + 3);
                    n2 = n;
                }
            }
            else {
                n2 = autoPlayMaxCount;
            }
            if (Log.isLoggable()) {
                Log.d("nf_postplay", "PostPlay: postPlayVideoPlayerCount = " + postPlayVideoPlayed + " autoPlayMaxCount = " + n2);
            }
            if (postPlayVideoPlayed >= n2 && this.mPlayerFragment.getState().noUserInteraction()) {
                Log.d("nf_postplay", "This is 3rd consecutive auto play with no user interaction, start interrupter timeout");
                this.mPlayerFragment.getHandler().postDelayed(this.onInterrupterStart, 120000L);
            }
        }
    }
    
    public static PostPlayFactory$PostPlayType getPostPlayType(final NetflixActivity netflixActivity, final Asset asset) {
        boolean b = true;
        boolean nextPlayableEpisode = false;
        netflixActivity.getServiceManager().isUserLoggedIn();
        final DeviceCategory deviceCategory = netflixActivity.getServiceManager().getDeviceCategory();
        final boolean nonMemberPlayback = netflixActivity.getServiceManager().isNonMemberPlayback();
        boolean b2;
        if (deviceCategory == null || deviceCategory == DeviceCategory.UNKNOWN || deviceCategory == DeviceCategory.PHONE) {
            b2 = false;
        }
        else {
            b2 = true;
        }
        if (asset != null) {
            nextPlayableEpisode = asset.isNextPlayableEpisode();
            if (asset.isEpisode()) {
                b = false;
            }
        }
        else {
            b = false;
        }
        if (nonMemberPlayback) {
            if (b2) {
                Log.d("nf_postplay", "SignupForTablet post_play layout");
                return PostPlayFactory$PostPlayType.SignupForTablet;
            }
            Log.d("nf_postplay", "SignupForPhone post_play layout");
            return PostPlayFactory$PostPlayType.SignupForPhone;
        }
        else if (b2) {
            if (b) {
                Log.d("nf_postplay", "RecommendationForTablet post_play layout");
                return PostPlayFactory$PostPlayType.RecommendationForTablet;
            }
            if (nextPlayableEpisode) {
                Log.d("nf_postplay", "EpisodesForTablet post_play layout");
                return PostPlayFactory$PostPlayType.EpisodesForTablet;
            }
            Log.d("nf_postplay", "RecommendationForTablet post_play layout");
            return PostPlayFactory$PostPlayType.RecommendationForTablet;
        }
        else {
            if (b) {
                Log.d("nf_postplay", "Phone recommendation (no) post_play layout");
                return PostPlayFactory$PostPlayType.RecommendationForPhone;
            }
            if (nextPlayableEpisode) {
                Log.d("nf_postplay", "Phone episodes post_play layout");
                return PostPlayFactory$PostPlayType.EpisodesForPhone;
            }
            Log.d("nf_postplay", "There will be no next episode, use phone recommendation (no) post_play layout");
            return PostPlayFactory$PostPlayType.RecommendationForPhone;
        }
    }
    
    private int getStartOfCredits() {
        if (this.mPostPlayExperience != null && this.mSeamless) {
            return this.mPostPlayExperience.getSeamlessEnd();
        }
        return this.mPlayerFragment.getCurrentAsset().getEndtime();
    }
    
    private boolean inPostPlay(final int n) {
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "inPostPlay() - called with null PlayerFragment!");
        }
        else if (this.mPlayerFragment.isActivityValid()) {
            final IPlayer player = this.mPlayerFragment.getPlayer();
            if (player != null) {
                final Asset currentAsset = this.mPlayerFragment.getCurrentAsset();
                if (currentAsset != null) {
                    final int duration = player.getDuration();
                    final int n2 = this.getStartOfCredits() * 1000;
                    int mOffsetMs;
                    if (currentAsset.isSupplementalVideo()) {
                        mOffsetMs = (int)TimeUnit.SECONDS.toMillis(2L);
                    }
                    else {
                        mOffsetMs = this.mOffsetMs;
                    }
                    if (Log.isLoggable()) {
                        Log.d("nf_postplay", "Duration " + duration);
                        Log.d("nf_postplay", "Endtime " + n2);
                        Log.d("nf_postplay", "Current position " + n);
                    }
                    if (duration - n2 <= 0) {
                        Log.w("nf_postplay", "Duration is zero. Seems that we didn't process it correctly yet (episode switching is performing). Skipping post_play check...");
                        return false;
                    }
                    int n3;
                    if (duration - n2 < mOffsetMs) {
                        Log.d("nf_postplay", "End time is too close to end of play. Use default offset instead.");
                        n3 = duration - mOffsetMs;
                    }
                    else if (n2 > duration) {
                        Log.d("nf_postplay", "End time is greater than duration! Use default offset instead.");
                        n3 = duration - mOffsetMs;
                    }
                    else {
                        n3 = n2;
                    }
                    return n > n3;
                }
            }
        }
        return false;
    }
    
    public void destroy() {
        if (this.isInteractivePostPlay && this.interactivePostPlayManager != null) {
            this.interactivePostPlayManager.onDestroy();
        }
        if (this.autoplayTimer != null) {
            this.autoplayTimer.stopTimer();
        }
        this.mNetflixActivity.getHandler().removeCallbacks(this.onInterrupterStart);
        this.mNetflixActivity.getHandler().removeCallbacks(this.onInterrupterDismiss);
        Log.d("nf_postplay", "User exist playback and post_play if it was in progress, report as such");
        UserActionLogUtils.reportEndPostPlay((Context)this.mPlayerFragment.getActivity(), IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.playback, null, true, false, null, null, 0);
    }
    
    protected void doTransitionFromPostPlay() {
    }
    
    protected abstract void doTransitionToPostPlay();
    
    public void endOfPlay() {
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "endOfPlay() - called with null PlayerFragment!");
            return;
        }
        if (this.isInteractivePostPlay && this.interactivePostPlayManager != null && this.interactivePostPlayManager.waitUntilEndOfPlay()) {
            this.startInteractivePostPlay(true);
        }
        this.mPlayerFragment.getSubtitleManager().clear();
    }
    
    public void fetchPostPlayVideos(final String s, final VideoType videoType, final PostPlayRequestContext postPlayRequestContext) {
        while (true) {
            Label_0133: {
                synchronized (this) {
                    if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.mNetflixActivity) && ConnectivityUtils.isConnected((Context)this.mNetflixActivity)) {
                        if (TextUtils.isEmpty((CharSequence)s) || this.mNetflixActivity.getServiceManager() == null) {
                            break Label_0133;
                        }
                        Log.d("nf_postplay", "Fetch post_play videos...");
                        final PostPlay$FetchPostPlayForPlaybackCallback mFetchPostPlayForPlaybackCallback = new PostPlay$FetchPostPlayForPlaybackCallback(this);
                        ErrorLoggingManager.leaveBreadcrumb("nf_postplay: Requesting post play response for video ID: " + s + " Type: " + videoType);
                        this.mNetflixActivity.getServiceManager().getBrowse().fetchPostPlayVideos(s, videoType, postPlayRequestContext, mFetchPostPlayForPlaybackCallback);
                        this.mFetchPostPlayForPlaybackCallback = mFetchPostPlayForPlaybackCallback;
                    }
                    return;
                }
            }
            Log.e("nf_postplay", "Unable to fetch post_play videos!");
        }
    }
    
    public void fetchPostPlayVideosIfNeeded(final String s, final VideoType videoType, final PostPlayRequestContext postPlayRequestContext) {
        Log.d("nf_postplay", "fetchPostPlayVideosIfNeeded starts");
        if (this.mPostPlayDataFetchStatus == PostPlay$PostPlayDataFetchStatus.started) {
            Log.d("nf_postplay", "fetchPostPlayVideosIfNeeded: Fetch of post_play data already in progress, do nothing.");
            return;
        }
        if (this.mPostPlayDataFetchStatus != PostPlay$PostPlayDataFetchStatus.notStarted) {
            Log.d("nf_postplay", "fetchPostPlayVideosIfNeeded: Fetching post_play was postponed, go and fetch it...");
            this.fetchPostPlayVideos(s, videoType, postPlayRequestContext);
            return;
        }
        Log.d("nf_postplay", "fetchPostPlayVideosIfNeeded: First time, postplaydata not fetched, check if we need to postpone data retrieval...");
        if (this.shouldPostponeFetchPostPlayData()) {
            Log.d("nf_postplay", "fetchPostPlayVideosIfNeeded: Postponing fetch of post_play data until playback starts...");
            this.mPostPlayDataFetchStatus = PostPlay$PostPlayDataFetchStatus.postponed;
            return;
        }
        Log.d("nf_postplay", "fetchPostPlayVideosIfNeeded: Fetching post_play data now, too close to start of post_play...");
        this.fetchPostPlayVideos(s, videoType, postPlayRequestContext);
    }
    
    protected abstract void findViews();
    
    protected void findViewsCommon() {
        this.mInterrupterPlayFromStart = this.mNetflixActivity.findViewById(2131690085);
        this.mInterrupterContinue = this.mNetflixActivity.findViewById(2131690084);
        this.mBackgroundContainer = (LinearLayout)this.mNetflixActivity.findViewById(2131690156);
        this.mItemsContainer = (LinearLayout)this.mNetflixActivity.findViewById(2131690152);
        this.mInterrupterStop = this.mNetflixActivity.findViewById(2131690086);
        this.mPostPlayIgnoreTap = this.mNetflixActivity.findViewById(2131690150);
        this.mInterrupter = this.mNetflixActivity.findViewById(2131690083);
        this.mPostPlay = this.mNetflixActivity.findViewById(2131690148);
    }
    
    public PlayerFragment getController() {
        return this.mPlayerFragment;
    }
    
    protected abstract int getLengthOfAutoPlayCountdow();
    
    protected abstract UserActionLogging$PostPlayExperience getPostPlayExpirience();
    
    protected boolean hasValidPlayAction(final PostPlayItem postPlayItem) {
        if (postPlayItem != null) {
            final PostPlayAction playAction = postPlayItem.getPlayAction();
            if (playAction != null) {
                ErrorLoggingManager.leaveBreadcrumb("nf_postplay: Checking post play play action video ID: " + playAction.getVideoId());
                if (playAction.getPlayBackVideo() != null && playAction.getPlayBackVideo().getPlayable() != null) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void hide() {
        if (this.mPostPlay != null) {
            this.mPostPlay.setVisibility(8);
        }
    }
    
    protected boolean isAutoPlayEnabled() {
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "isAutoPlayEnabled() - called with null PlayerFragment!");
        }
        else {
            if (!this.mPlayerFragment.isActivityValid()) {
                Log.e("nf_postplay", "Activity not found! Auto post_play is NOT enabled. This should NOT happen!");
                return false;
            }
            final Asset currentAsset = this.mPlayerFragment.getCurrentAsset();
            if (currentAsset == null) {
                Log.e("nf_postplay", "Asset not found! Auto post_play is NOT enabled. This should NOT happen!");
                return false;
            }
            if (!currentAsset.isAutoPlayEnabled()) {
                Log.d("nf_postplay", "Autoplay is disabled for this title");
                return false;
            }
            final ServiceManager serviceManager = this.mPlayerFragment.getServiceManager();
            if (serviceManager != null) {
                final UserProfile currentProfile = serviceManager.getCurrentProfile();
                if (currentProfile != null) {
                    if (currentProfile.isAutoPlayEnabled()) {
                        Log.d("nf_postplay", "Autoplay is enabled for this profile");
                        return true;
                    }
                    Log.d("nf_postplay", "Autoplay is disabled for this profile");
                    return false;
                }
            }
        }
        return false;
    }
    
    protected boolean isAutoPlayUsed() {
        return this.isAutoPlayEnabled();
    }
    
    public boolean isInPostPlay() {
        return this.mInPostPlay;
    }
    
    protected boolean isPostPlayAllowed() {
        if (!this.isPostPlayEnabled()) {
            Log.d("nf_postplay", "PostPlay is not enabled.");
            return false;
        }
        if (this.mPostPlayDismissed) {
            Log.d("nf_postplay", "PostPlay was dismissed by an user, do not start it again.");
            return false;
        }
        return true;
    }
    
    protected boolean isPostPlayEnabled() {
        return this.mPostPlayDataExist || this.isInteractivePostPlay;
    }
    
    public void logPostPlayImpression(final String s, final VideoType videoType, final String s2) {
        if (!TextUtils.isEmpty((CharSequence)s) && this.mNetflixActivity.getServiceManager() != null) {
            Log.d("nf_postplay", "Logging post play impression");
            this.mNetflixActivity.getServiceManager().getBrowse().logPostPlayImpression(s, videoType, s2, new PostPlay$LogPostPlayImpressionCallback(this));
            return;
        }
        Log.e("nf_postplay", "Unable to log post play impression!");
    }
    
    void moveFromInterruptedToPlaying() {
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "moveFromInterruptedToPlaying() - called with null PlayerFragment!");
            return;
        }
        Log.d("nf_postplay", "Interrupter mode, continue");
        this.mPlayerFragment.getScreen().clearPanel();
        this.mPlayerFragment.doUnpause();
        this.mInterrupter.setVisibility(4);
        this.mPlayerFragment.getSubtitleManager().setSubtitleVisibility(true);
    }
    
    void moveFromInterruptedToPlayingFromStart() {
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "moveFromInterruptedToPlayingFromStart() - called with null PlayerFragment!");
            return;
        }
        this.mInterrupter.setVisibility(4);
        Log.d("nf_postplay", "Interrupter mode, play from start");
        this.mPlayerFragment.getScreen().clearPanel();
        this.mInterrupter.setVisibility(4);
        this.mPlayerFragment.getSubtitleManager().setSubtitleVisibility(true);
        this.mPlayerFragment.doSeek(0);
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
    }
    
    public void onPause() {
        if (this.isInPostPlay() && this.isInteractivePostPlay && this.interactivePostPlayManager != null) {
            this.interactivePostPlayManager.onPause();
        }
    }
    
    public void onResume() {
        if (this.isInPostPlay() && this.isInteractivePostPlay && this.interactivePostPlayManager != null) {
            this.interactivePostPlayManager.onResume();
        }
    }
    
    public void onStart() {
        if (this.isInPostPlay() && this.isInteractivePostPlay && this.interactivePostPlayManager != null) {
            this.interactivePostPlayManager.onStart();
        }
    }
    
    public void onStop() {
        if (this.isInPostPlay() && this.isInteractivePostPlay && this.interactivePostPlayManager != null) {
            this.interactivePostPlayManager.onStop();
        }
    }
    
    protected void onTick(final int n) {
    }
    
    public void postPlayDismissed() {
        this.mPostPlayDismissed = true;
    }
    
    public void reportNextPlayFailed(final boolean b) {
        if (this.shouldReportPostplay()) {
            Log.d("nf_postplay", "User starts next play and it failed, report as such");
            UserActionLogUtils.reportEndPostPlay((Context)this.mNetflixActivity, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.playback, null, b, false, null, null, 0);
        }
    }
    
    public void reset() {
        this.mPostPlayExperience = null;
        this.mPostPlayDataFetchStatus = PostPlay$PostPlayDataFetchStatus.postponed;
        this.mPostPlayDataExist = false;
        this.interactivePostPlayManager = null;
    }
    
    public void setBackgroundImageVisible(final boolean b) {
        if (this.mBackgroundContainer != null) {
            if (!b) {
                this.mBackgroundContainer.setVisibility(4);
                return;
            }
            this.mBackgroundContainer.setVisibility(0);
        }
    }
    
    protected void setClickListeners() {
        if (this.mPostPlayIgnoreTap != null) {
            this.mPostPlayIgnoreTap.setOnTouchListener((View$OnTouchListener)new PostPlay$1(this));
        }
        if (this.mInterrupterContinue != null) {
            this.mInterrupterContinue.setOnClickListener((View$OnClickListener)new PostPlay$2(this));
        }
        if (this.mInterrupterStop != null) {
            this.mInterrupterStop.setOnClickListener((View$OnClickListener)new PostPlay$3(this));
        }
        else {
            Log.e("nf_postplay", "setClickListeners() - mInterrupterStop handler was not set!");
        }
        if (this.mInterrupterPlayFromStart != null) {
            this.mInterrupterPlayFromStart.setOnClickListener((View$OnClickListener)new PostPlay$4(this));
        }
    }
    
    protected void setupAutoPlay(final PostPlayRequestContext postPlayRequestContext) {
        (this.autoplayTimer = new TimeUtils$CountdownTimer(this.mNetflixActivity)).setTime(this.mPostPlayExperience.getAutoplaySeconds());
        this.autoplayTimer.setOnFinish(new PostPlay$5(this, new PostPlayCallToAction(this.mNetflixActivity, this.mPlayerFragment, this.mPostPlayExperience.getItems().get(this.mPostPlayExperience.getItemsInitialIndex()).getPlayAction(), postPlayRequestContext)));
        this.autoplayTimer.setOnTick(new PostPlay$6(this));
    }
    
    protected boolean shouldPostponeFetchPostPlayData() {
        boolean b = true;
        boolean b2 = true;
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "shouldPostponeFetchPostPlayData() - called with null PlayerFragment!");
        }
        else {
            final Asset currentAsset = this.mPlayerFragment.getCurrentAsset();
            if (currentAsset != null) {
                final int n = currentAsset.getPlaybackBookmark() * 1000;
                final int n2 = currentAsset.getDuration() * 1000;
                int n3;
                if ((n3 = n) < 0) {
                    Log.d("nf_postplay", "Invalid bookmark, reset to 0");
                    n3 = 0;
                }
                if (n2 <= 0) {
                    Log.d("nf_postplay", "We do not have duration, do not postpone!");
                    return false;
                }
                if (n3 + this.mFetchPostplayOffsetMs >= n2) {
                    b = false;
                }
                b2 = b;
                if (Log.isLoggable()) {
                    Log.d("nf_postplay", " Duration (ms): " + n2);
                    Log.d("nf_postplay", " Fetch Postplay Offset (ms): " + n2);
                    Log.d("nf_postplay", " Bookmark (ms): " + n2);
                    Log.d("nf_postplay", " Postpone catching playback " + b);
                    return b;
                }
            }
        }
        return b2;
    }
    
    protected boolean shouldReportPostplay() {
        return true;
    }
    
    public void startInteractivePostPlay(final boolean b) {
        if (this.isInteractivePostPlay && this.interactivePostPlayManager != null) {
            this.interactivePostPlayManager.startPostPlay(b);
        }
    }
    
    void transitionFromPostPlay() {
        Log.d("nf_postplay", "Transition from post play execute!");
        this.mInPostPlay = false;
        this.postPlayDismissed();
        if (this.mPostPlay != null) {
            this.mPostPlay.setVisibility(4);
            this.mPostPlay.setFitsSystemWindows(false);
        }
        if (this.shouldReportPostplay()) {
            Log.d("nf_postplay", "User dismissed post_play, report as such");
            UserActionLogUtils.reportEndPostPlay((Context)this.mNetflixActivity, IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.playback, null, true, false, null, null, 0);
        }
        this.doTransitionFromPostPlay();
    }
    
    public void transitionToPostPlay() {
        Log.d("nf_postplay", "Transition to post play execute!");
        this.mInPostPlay = true;
        if (this.mPostPlay != null && (this.mPlayerFragment == null || !this.mPlayerFragment.isInPortrait())) {
            this.mPostPlay.setFitsSystemWindows(true);
        }
        if (this.isInteractivePostPlay && this.interactivePostPlayManager != null) {
            if (!this.interactivePostPlayManager.waitUntilEndOfPlay()) {
                this.startInteractivePostPlay(false);
            }
            return;
        }
        if (this.mPostPlayExperience != null) {
            if (this.mPostPlay != null) {
                this.mPostPlay.setVisibility(0);
            }
            if (this.shouldReportPostplay()) {
                UserActionLogUtils.reportStartPostPlay((Context)this.mNetflixActivity, this.isAutoPlayUsed(), this.getLengthOfAutoPlayCountdow(), this.getPostPlayExpirience());
                final PostPlayItem postPlayItem = this.mPostPlayExperience.getItems().get(0);
                this.logPostPlayImpression(String.valueOf(postPlayItem.getVideoId()), null, postPlayItem.getImpressionData());
            }
            this.doTransitionToPostPlay();
            return;
        }
        ErrorLoggingManager.logHandledException("SPY-10544 - Error transitioning to post play. No post play experience defined.");
    }
    
    protected abstract void updateOnPostPlayVideosFetched();
    
    public boolean updatePlaybackPosition(final int n) {
        if (!this.isPostPlayAllowed()) {
            return false;
        }
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "updatePlaybackPosition() - called with null PlayerFragment!");
            return false;
        }
        final boolean inPostPlay = this.inPostPlay(n);
        if (this.mInPostPlay && inPostPlay) {
            Log.d("nf_postplay", "Already in post play");
            return true;
        }
        if (this.mPlayerFragment.getScreen().getState() == PlayerUiState.Interrupter) {
            Log.d("nf_postplay", "In Interrupter mode, do nothing");
            return false;
        }
        if (this.mInPostPlay && !inPostPlay) {
            Log.d("nf_postplay", "Transition from post play to normal");
            this.transitionFromPostPlay();
            return false;
        }
        if (!this.mInPostPlay && inPostPlay) {
            Log.d("nf_postplay", "Transition to post play");
            this.mPlayerFragment.getScreen().moveToState(PlayerUiState.PostPlay);
            return true;
        }
        Log.d("nf_postplay", "Not in in post play");
        return false;
    }
    
    public boolean wasPostPlayDismissed() {
        return this.mPostPlayDismissed;
    }
}
