// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.animation.TimeInterpolator;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayContext;
import java.util.List;
import android.view.animation.DecelerateInterpolator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

public abstract class PostPlay
{
    private static final int DEFAULT_FETCH_POSTPLAY_MS = 10000;
    private static final int DEFAULT_INTERRUPTER_TIMEOUT_IN_MS = 3600000;
    private static final int DEFAULT_OFFSET_MS = 10000;
    protected static final int INTERRUPTER_COUNT = 3;
    private static final int INTERRUPTER_VALUE_IN_MS = 120000;
    private static final double SIXTY_PERCENT = 0.6;
    protected static final String TAG = "nf_postplay";
    protected AdvancedImageView mBackground;
    protected int mFetchPostplayOffsetMs;
    protected boolean mInPostPlay;
    protected int mInterrputerTimeoutOffset;
    protected View mInterrupter;
    protected View mInterrupterContinue;
    protected View mInterrupterPlayFromStart;
    protected View mInterrupterStop;
    protected View mMoreButton;
    protected NetflixActivity mNetflixActivity;
    protected int mOffsetMs;
    private final DecelerateInterpolator mPanAnimationInterpolator;
    protected View mPlayButton;
    protected PlayerFragment mPlayerFragment;
    protected View mPostPlay;
    protected List<PostPlayContext> mPostPlayContexts;
    protected boolean mPostPlayDataExist;
    protected PostPlay$PostPlayDataFetchStatus mPostPlayDataFetchStatus;
    protected boolean mPostPlayDismissed;
    protected View mPostPlayIgnoreTap;
    protected List<PostPlayVideo> mPostPlayVideos;
    protected View mStopButton;
    protected TextView mSynopsis;
    protected TextView mTitle;
    private final Runnable onInterrupterDismiss;
    private final Runnable onInterrupterStart;
    
    protected PostPlay(final NetflixActivity mNetflixActivity) {
        this.mPanAnimationInterpolator = new DecelerateInterpolator();
        this.mOffsetMs = 10000;
        this.mFetchPostplayOffsetMs = 10000;
        this.mInterrputerTimeoutOffset = 3600000;
        this.onInterrupterStart = new PostPlay$6(this);
        this.onInterrupterDismiss = new PostPlay$7(this);
        this.mNetflixActivity = mNetflixActivity;
        this.findViewsCommon();
        this.findViews();
        this.setClickListeners();
        this.mOffsetMs = mNetflixActivity.getResources().getInteger(2131427345) * 1000;
        this.mFetchPostplayOffsetMs = this.mOffsetMs;
        this.mPostPlayDataFetchStatus = PostPlay$PostPlayDataFetchStatus.notStarted;
    }
    
    protected PostPlay(final PlayerFragment mPlayerFragment) {
        this(mPlayerFragment.getNetflixActivity());
        this.mPlayerFragment = mPlayerFragment;
        if (this.mPlayerFragment.getCurrentAsset() != null && this.mPlayerFragment.getCurrentAsset().getPostPlayVideoPlayed() >= 3 && this.mPlayerFragment.getState().noUserInteraction()) {
            Log.d("nf_postplay", "This is 3rd consecutive auto play with no user interaction, start interrupter timeout");
            this.mPlayerFragment.getHandler().postDelayed(this.onInterrupterStart, 120000L);
        }
    }
    
    public static PostPlayFactory$PostPlayType getPostPlayType(final NetflixActivity netflixActivity, final Asset asset) {
        boolean b = true;
        boolean nextPlayableEpisode = false;
        final DeviceCategory deviceCategory = netflixActivity.getServiceManager().getDeviceCategory();
        int n;
        if (deviceCategory == null || deviceCategory == DeviceCategory.UNKNOWN || deviceCategory == DeviceCategory.PHONE) {
            n = 0;
        }
        else {
            n = 1;
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
        if (n != 0) {
            if (b) {
                Log.d("nf_postplay", "RecommendationForTablet postplay layout");
                return PostPlayFactory$PostPlayType.RecommendationForTablet;
            }
            if (nextPlayableEpisode) {
                Log.d("nf_postplay", "EpisodesForTablet postplay layout");
                return PostPlayFactory$PostPlayType.EpisodesForTablet;
            }
            Log.d("nf_postplay", "RecommendationForTablet postplay layout");
            return PostPlayFactory$PostPlayType.RecommendationForTablet;
        }
        else {
            if (b) {
                Log.d("nf_postplay", "Phone recommendation (no) postplay layout");
                return PostPlayFactory$PostPlayType.RecommendationForPhone;
            }
            if (nextPlayableEpisode) {
                Log.d("nf_postplay", "Phone episodes postplay layout");
                return PostPlayFactory$PostPlayType.EpisodesForPhone;
            }
            Log.d("nf_postplay", "There will be no next episode, use phone recommendation (no) postplay layout");
            return PostPlayFactory$PostPlayType.RecommendationForPhone;
        }
    }
    
    private static int getSafeVideoId(final Playable playable) {
        try {
            return Integer.parseInt(playable.getPlayableId());
        }
        catch (Throwable t) {
            if (Log.isLoggable()) {
                Log.e("nf_postplay", "Unable to get video ud from playable " + playable);
            }
            return 0;
        }
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
                    final int n2 = currentAsset.getEndtime() * 1000;
                    if (Log.isLoggable()) {
                        Log.d("nf_postplay", "Duration " + duration);
                        Log.d("nf_postplay", "Endtime " + n2);
                        Log.d("nf_postplay", "Current position " + n);
                    }
                    if (duration - n2 <= 0) {
                        Log.w("nf_postplay", "Duration is zero. Seems that we didn't process it correctly yet (episode switching is performing). Skipping postplay check...");
                        return false;
                    }
                    int n3;
                    if (duration - n2 < this.mOffsetMs) {
                        Log.d("nf_postplay", "End time is too close to end of play. Use default offset instead.");
                        n3 = duration - this.mOffsetMs;
                    }
                    else if ((n3 = n2) > duration) {
                        Log.d("nf_postplay", "End time is greater than duration! Use default offset instead.");
                        n3 = duration - this.mOffsetMs;
                    }
                    if (n > n3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void destroy() {
        this.mNetflixActivity.getHandler().removeCallbacks(this.onInterrupterStart);
        this.mNetflixActivity.getHandler().removeCallbacks(this.onInterrupterDismiss);
        Log.d("nf_postplay", "User exist playback and postplay if it was in progress, report as such");
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
        this.mPlayerFragment.getSubtitleManager().clear();
    }
    
    public void fetchPostPlayVideos(final String s, final VideoType videoType) {
        if (!TextUtils.isEmpty((CharSequence)s) && this.mNetflixActivity.getServiceManager() != null) {
            Log.d("nf_postplay", "Fetch postplay videos...");
            this.mNetflixActivity.getServiceManager().getBrowse().fetchPostPlayVideos(s, videoType, new PostPlay$FetchPostPlayForPlaybackCallback(this));
            return;
        }
        Log.e("nf_postplay", "Unable to fetch postplay videos!");
    }
    
    public void fetchPostPlayVideosIfNeeded(final String s, final VideoType videoType) {
        if (this.mPostPlayDataFetchStatus == PostPlay$PostPlayDataFetchStatus.started) {
            Log.d("nf_postplay", "Fetch of postplay data already in progress, do nothing.");
            return;
        }
        if (this.mPostPlayDataFetchStatus != PostPlay$PostPlayDataFetchStatus.notStarted) {
            Log.d("nf_postplay", "Fetching postplay was postponed, go and fetch it...");
            this.fetchPostPlayVideos(s, videoType);
            return;
        }
        Log.d("nf_postplay", "First time, postplaydata not fetched, check if we need to postpone data retrieval...");
        if (this.shouldPostponeFetchPostPlayData()) {
            Log.d("nf_postplay", "Postponing fetch of postplay data until playback starts...");
            this.mPostPlayDataFetchStatus = PostPlay$PostPlayDataFetchStatus.postponed;
            return;
        }
        Log.d("nf_postplay", "Fetching postplay data now, too close to start of postplay...");
        this.fetchPostPlayVideos(s, videoType);
    }
    
    abstract void findViews();
    
    protected void findViewsCommon() {
        this.mInterrupterPlayFromStart = this.mNetflixActivity.findViewById(2131624373);
        this.mInterrupterContinue = this.mNetflixActivity.findViewById(2131624372);
        this.mBackground = (AdvancedImageView)this.mNetflixActivity.findViewById(2131624413);
        this.mSynopsis = (TextView)this.mNetflixActivity.findViewById(2131624411);
        this.mInterrupterStop = this.mNetflixActivity.findViewById(2131624374);
        this.mPostPlayIgnoreTap = this.mNetflixActivity.findViewById(2131624409);
        this.mMoreButton = this.mNetflixActivity.findViewById(2131624401);
        this.mPlayButton = this.mNetflixActivity.findViewById(2131624399);
        this.mStopButton = this.mNetflixActivity.findViewById(2131624400);
        this.mTitle = (TextView)this.mNetflixActivity.findViewById(2131624410);
        this.mInterrupter = this.mNetflixActivity.findViewById(2131624371);
        this.mPostPlay = this.mNetflixActivity.findViewById(2131624406);
    }
    
    protected abstract int getLengthOfAutoPlayCountdow();
    
    protected abstract UserActionLogging$PostPlayExperience getPostPlayExpirience();
    
    protected abstract void handlePlayNow(final boolean p0);
    
    protected void handleStop(final boolean b) {
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "inPostPlay() - called with null PlayerFragment!");
            return;
        }
        this.mPlayerFragment.finish();
    }
    
    protected boolean isAutoPlayEnabled() {
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "isAutoPlayEnabled() - called with null PlayerFragment!");
        }
        else {
            if (!this.mPlayerFragment.isActivityValid()) {
                Log.e("nf_postplay", "Activity not found! Auto postplay is NOT enabled. This should NOT happen!");
                return false;
            }
            final Asset currentAsset = this.mPlayerFragment.getCurrentAsset();
            if (currentAsset == null) {
                Log.e("nf_postplay", "Asset not found! Auto postplay is NOT enabled. This should NOT happen!");
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
        return this.mPostPlayDataExist;
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
    
    public void onPause() {
    }
    
    public void onResume() {
    }
    
    public void postPlayDismissed() {
        this.mPostPlayDismissed = true;
    }
    
    protected void reportNextPlay(final Playable playable, final PlayContext playContext, final boolean b, final Integer n) {
        if (this.shouldReportPostplay()) {
            Log.d("nf_postplay", "User starts next play, report as such");
            UserActionLogUtils.reportEndPostPlay((Context)this.mNetflixActivity, IClientLogging$CompletionReason.success, IClientLogging$ModalView.playback, null, b, true, getSafeVideoId(playable), n, playContext.getTrackId());
        }
    }
    
    protected void reportNextPlayFailed(final boolean b) {
        if (this.shouldReportPostplay()) {
            Log.d("nf_postplay", "User starts next play and it failed, report as such");
            UserActionLogUtils.reportEndPostPlay((Context)this.mNetflixActivity, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.playback, null, b, false, null, null, 0);
        }
    }
    
    public void setBackgroundImageVisible(final boolean b) {
        if (this.mBackground != null) {
            if (!b) {
                this.mBackground.setVisibility(4);
                return;
            }
            this.mBackground.setVisibility(0);
        }
    }
    
    protected void setClickListeners() {
        if (this.mPostPlayIgnoreTap != null) {
            this.mPostPlayIgnoreTap.setOnTouchListener((View$OnTouchListener)new PostPlay$1(this));
        }
        if (this.mPlayButton != null) {
            this.mPlayButton.setOnClickListener((View$OnClickListener)new PostPlay$2(this));
        }
        if (this.mInterrupterContinue != null) {
            this.mInterrupterContinue.setOnClickListener((View$OnClickListener)new PostPlay$3(this));
        }
        if (this.mInterrupterStop != null) {
            this.mInterrupterStop.setOnClickListener((View$OnClickListener)new PostPlay$4(this));
        }
        else {
            Log.e("nf_postplay", "setClickListeners() - mInterrupterStop handler was not set!");
        }
        if (this.mInterrupterPlayFromStart != null) {
            this.mInterrupterPlayFromStart.setOnClickListener((View$OnClickListener)new PostPlay$5(this));
        }
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
    
    public void startBackgroundAutoPan() {
        if (this.mBackground != null && !DeviceUtils.isLandscape((Context)this.mNetflixActivity) && this.mBackground.getMeasuredWidth() == 0) {
            this.mBackground.getLayoutParams().height = (int)(DeviceUtils.getScreenHeightInPixels((Context)this.mNetflixActivity) * 0.6);
            this.mBackground.getLayoutParams().width = (int)(this.mBackground.getLayoutParams().height * 1.778f);
            this.mBackground.animate().setStartDelay(1000L).setDuration((long)this.mOffsetMs).x((float)(this.mBackground.getLayoutParams().height - this.mBackground.getLayoutParams().width)).setInterpolator((TimeInterpolator)this.mPanAnimationInterpolator);
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
            Log.d("nf_postplay", "User dismissed postplay, report as such");
            UserActionLogUtils.reportEndPostPlay((Context)this.mNetflixActivity, IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.playback, null, true, false, null, null, 0);
        }
        this.doTransitionFromPostPlay();
    }
    
    void transitionToPostPlay() {
        Log.d("nf_postplay", "Transition to post play execute!");
        this.mInPostPlay = true;
        if (this.mPostPlay != null) {
            this.mPostPlay.setVisibility(0);
            this.mPostPlay.setFitsSystemWindows(true);
        }
        if (this.shouldReportPostplay()) {
            UserActionLogUtils.reportStartPostPlay((Context)this.mNetflixActivity, this.isAutoPlayUsed(), this.getLengthOfAutoPlayCountdow(), this.getPostPlayExpirience());
        }
        this.doTransitionToPostPlay();
        this.startBackgroundAutoPan();
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
