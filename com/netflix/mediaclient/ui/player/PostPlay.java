// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.animation.TimeInterpolator;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$OnClickListener;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.servicemgr.UserProfile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.ui.Asset;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.PostPlayVideo;
import java.util.List;
import android.view.animation.DecelerateInterpolator;
import android.view.View;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

public abstract class PostPlay
{
    private static final int DEFAULT_INTERRUPTER_TIMEOUT_IN_MS = 3600000;
    private static final int DEFAULT_OFFSET = 10000;
    protected static final int INTERRUPTER_COUNT = 3;
    private static final int INTERRUPTER_VALUE_IN_MS = 120000;
    private static final double SIXTEEN_NINE_RATIO = 1.77;
    private static final double SIXTY_PERCENT = 0.6;
    protected static final String TAG = "nf_postplay";
    protected AdvancedImageView mBackground;
    protected PlayerActivity mContext;
    protected boolean mInPostPlay;
    protected int mInterrputerTimeoutOffset;
    protected View mInterrupter;
    protected View mInterrupterContinue;
    protected View mInterrupterPlayFromStart;
    protected View mInterrupterStop;
    protected View mMoreButton;
    protected int mOffset;
    private DecelerateInterpolator mPanAnimationInterpolator;
    protected View mPlayButton;
    protected View mPostPlay;
    protected boolean mPostPlayDataExist;
    protected boolean mPostPlayDismissed;
    protected View mPostPlayIgnoreTap;
    protected List<PostPlayVideo> mPostPlayVideos;
    protected View mStopButton;
    protected TextView mSynopsis;
    protected TextView mTitle;
    private final Runnable onInterrupterDismiss;
    private final Runnable onInterrupterStart;
    
    protected PostPlay(final PlayerActivity mContext) {
        this.mPanAnimationInterpolator = new DecelerateInterpolator();
        this.mOffset = 10000;
        this.mInterrputerTimeoutOffset = 3600000;
        this.onInterrupterStart = new Runnable() {
            @Override
            public void run() {
                if (!PostPlay.this.mContext.getState().noUserInteraction()) {
                    Log.d("nf_postplay", "Interrupter process, there was user interaction in meantime. Do nothing");
                    return;
                }
                if (PostPlay.this.mContext.getScreen().getState() == PlayerUiState.Loading) {
                    Log.d("nf_postplay", "This is 3rd consecutive auto play with no user interaction, but after 2 minutes we are still loading, postpone for 2 more minutes");
                    PostPlay.this.mContext.getHandler().postDelayed((Runnable)this, 120000L);
                    return;
                }
                if (PostPlay.this.mInterrupter != null) {
                    Log.d("nf_postplay", "This is 3rd consecutive auto play with no user interaction, after 2 minutes start interrupter mode");
                    PostPlay.this.mContext.doPause();
                    PostPlay.this.mInterrupter.setVisibility(0);
                    PostPlay.this.mContext.getScreen().moveToState(PlayerUiState.Interrupter);
                    PostPlay.this.mContext.getHandler().postDelayed(PostPlay.this.onInterrupterDismiss, (long)PostPlay.this.mInterrputerTimeoutOffset);
                    return;
                }
                Log.w("nf_postplay", "Interrupter UI NOT found, this should not happen!");
            }
        };
        this.onInterrupterDismiss = new Runnable() {
            @Override
            public void run() {
                Log.d("nf_postplay", "After 60 minutes of waiting for user input, stop player ui");
                if (!PostPlay.this.mContext.destroyed()) {
                    PostPlay.this.mContext.finish();
                }
            }
        };
        this.mContext = mContext;
        this.findViewsCommon();
        this.findViews();
        this.setClickListeners();
        if (this.mContext.getCurrentAsset() != null && this.mContext.getCurrentAsset().getPostPlayVideoPlayed() >= 3 && this.mContext.getState().noUserInteraction()) {
            Log.d("nf_postplay", "This is 3rd consecutive auto play with no user interaction, start interrupter timeout");
            this.mContext.getHandler().postDelayed(this.onInterrupterStart, 120000L);
        }
    }
    
    public static PostPlayFactory.PostPlayType getPostPlayType(final PlayerActivity playerActivity, final boolean b) {
        final DeviceCategory deviceCategory = playerActivity.getServiceManager().getDeviceCategory();
        int n;
        if (deviceCategory == null || deviceCategory == DeviceCategory.UNKNOWN || deviceCategory == DeviceCategory.PHONE) {
            n = 0;
        }
        else {
            n = 1;
        }
        boolean b2 = false;
        final boolean b3 = false;
        boolean b5;
        if (b) {
            final Intent intent = playerActivity.getIntent();
            final boolean b4 = b5 = intent.getBooleanExtra("extra_get_details_has_next_episode", (boolean)(0 != 0));
            if (VideoType.create(intent.getStringExtra("extra_get_details_video_type")) == VideoType.MOVIE) {
                b2 = true;
                b5 = b4;
            }
        }
        else {
            final Asset currentAsset = playerActivity.getCurrentAsset();
            b5 = b3;
            if (currentAsset != null) {
                b5 = currentAsset.isNextPlayableEpisode();
                if (!currentAsset.isEpisode()) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
            }
        }
        if (n != 0) {
            if (b2) {
                Log.d("nf_postplay", "RecommendationForTablet postplay layout");
                return PostPlayFactory.PostPlayType.RecommendationForTablet;
            }
            if (b5) {
                Log.d("nf_postplay", "EpisodesForTablet postplay layout");
                return PostPlayFactory.PostPlayType.EpisodesForTablet;
            }
            Log.d("nf_postplay", "RecommendationForTablet postplay layout");
            return PostPlayFactory.PostPlayType.RecommendationForTablet;
        }
        else {
            if (b2) {
                Log.d("nf_postplay", "Phone recommendation (no) postplay layout");
                return PostPlayFactory.PostPlayType.RecommendationForPhone;
            }
            if (b5) {
                Log.d("nf_postplay", "Phone episodes postplay layout");
                return PostPlayFactory.PostPlayType.EpisodesForPhone;
            }
            Log.d("nf_postplay", "There will be no next episode, use phone recommendation (no) postplay layout");
            return PostPlayFactory.PostPlayType.RecommendationForPhone;
        }
    }
    
    private boolean inPostPlay(final int n) {
        final PlayerActivity mContext = this.mContext;
        if (mContext != null) {
            final IPlayer player = mContext.getPlayer();
            if (player != null) {
                final Asset currentAsset = mContext.getCurrentAsset();
                if (currentAsset != null) {
                    final int duration = player.getDuration();
                    final int n2 = currentAsset.getEndtime() * 1000;
                    if (Log.isLoggable("nf_postplay", 3)) {
                        Log.d("nf_postplay", "Duration " + duration);
                        Log.d("nf_postplay", "Endtime " + n2);
                        Log.d("nf_postplay", "Current position " + n);
                    }
                    int n3;
                    if (duration - n2 < this.mOffset) {
                        Log.d("nf_postplay", "End time is too close to end of play. Use default offset instead.");
                        n3 = duration - this.mOffset;
                    }
                    else if ((n3 = n2) > duration) {
                        Log.d("nf_postplay", "End time is greater than duration! Use default offset instead.");
                        n3 = duration - this.mOffset;
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
        this.mContext.getHandler().removeCallbacks(this.onInterrupterStart);
        this.mContext.getHandler().removeCallbacks(this.onInterrupterDismiss);
    }
    
    protected void doTransitionFromPostPlay() {
    }
    
    protected abstract void doTransitionToPostPlay();
    
    public void endOfPlay() {
        this.mContext.getSubtitleManager().clear();
    }
    
    abstract void findViews();
    
    protected void findViewsCommon() {
        this.mInterrupterPlayFromStart = this.mContext.findViewById(2131165509);
        this.mInterrupterContinue = this.mContext.findViewById(2131165508);
        this.mBackground = (AdvancedImageView)this.mContext.findViewById(2131165561);
        this.mSynopsis = (TextView)this.mContext.findViewById(2131165559);
        this.mInterrupterStop = this.mContext.findViewById(2131165510);
        this.mPostPlayIgnoreTap = this.mContext.findViewById(2131165557);
        this.mMoreButton = this.mContext.findViewById(2131165549);
        this.mPlayButton = this.mContext.findViewById(2131165547);
        this.mStopButton = this.mContext.findViewById(2131165548);
        this.mTitle = (TextView)this.mContext.findViewById(2131165558);
        this.mInterrupter = this.mContext.findViewById(2131165507);
        this.mPostPlay = this.mContext.findViewById(2131165554);
    }
    
    protected abstract void handlePlayNow(final boolean p0);
    
    protected void handleStop(final boolean b) {
        this.mContext.finish();
    }
    
    public void init(final String s) {
        this.mContext.getServiceManager().fetchPostPlayVideos(s, new FetchPostPlayForPlaybackCallback());
        this.mOffset = this.mContext.getResources().getInteger(2131427335) * 1000;
    }
    
    protected boolean isAutoPlayEnabled() {
        final PlayerActivity mContext = this.mContext;
        if (mContext == null) {
            Log.e("nf_postplay", "Activity not found! Auto postplay is NOT enabled. This should NOT happen!");
        }
        else {
            final Asset currentAsset = mContext.getCurrentAsset();
            if (currentAsset == null) {
                Log.e("nf_postplay", "Asset not found! Auto postplay is NOT enabled. This should NOT happen!");
                return false;
            }
            if (!currentAsset.isAutoPlayEnabled()) {
                Log.d("nf_postplay", "Autoplay is disabled for this title");
                return false;
            }
            final ServiceManager serviceManager = mContext.getServiceManager();
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
        Log.d("nf_postplay", "Interrupter mode, continue");
        this.mContext.getScreen().clearPanel();
        this.mContext.doUnpause();
        this.mInterrupter.setVisibility(4);
        this.mContext.getSubtitleManager().setSubtitleVisibility(true);
    }
    
    void moveFromInterruptedToPlayingFromStart() {
        this.mInterrupter.setVisibility(4);
        Log.d("nf_postplay", "Interrupter mode, play from start");
        this.mContext.getScreen().clearPanel();
        this.mInterrupter.setVisibility(4);
        this.mContext.getSubtitleManager().setSubtitleVisibility(true);
        this.mContext.doSeek(0);
    }
    
    public void onPause() {
    }
    
    public void onResume() {
    }
    
    public void postPlayDismissed() {
        this.mPostPlayDismissed = true;
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
            this.mPostPlayIgnoreTap.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    Log.d("nf_postplay", "Hijacking tap, do nothing");
                    return true;
                }
            });
        }
        if (this.mPlayButton != null) {
            this.mPlayButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    PostPlay.this.handlePlayNow(false);
                }
            });
        }
        if (this.mInterrupterContinue != null) {
            this.mInterrupterContinue.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    Log.d("nf_postplay", "Interrupter mode, continue");
                    PostPlay.this.moveFromInterruptedToPlaying();
                }
            });
        }
        if (this.mInterrupterStop != null) {
            this.mInterrupterStop.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    Log.d("nf_postplay", "Interrupter mode, stop");
                    PostPlay.this.mContext.finish();
                }
            });
        }
        if (this.mInterrupterPlayFromStart != null) {
            this.mInterrupterPlayFromStart.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    PostPlay.this.moveFromInterruptedToPlayingFromStart();
                }
            });
        }
    }
    
    public void startBackgroundAutoPan() {
        if (this.mBackground != null && !DeviceUtils.isLandscape((Context)this.mContext) && this.mBackground.getMeasuredWidth() == 0) {
            this.mBackground.getLayoutParams().height = (int)(DeviceUtils.getScreenHeightInPixels((Context)this.mContext) * 0.6);
            this.mBackground.getLayoutParams().width = (int)(this.mBackground.getLayoutParams().height * 1.77);
            this.mBackground.animate().setStartDelay(1000L).setDuration((long)this.mOffset).x((float)(this.mBackground.getLayoutParams().height - this.mBackground.getLayoutParams().width)).setInterpolator((TimeInterpolator)this.mPanAnimationInterpolator);
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
        this.doTransitionFromPostPlay();
    }
    
    void transitionToPostPlay() {
        Log.d("nf_postplay", "Transition to post play execute!");
        this.mInPostPlay = true;
        if (this.mPostPlay != null) {
            this.mPostPlay.setVisibility(0);
            this.mPostPlay.setFitsSystemWindows(true);
        }
        this.doTransitionToPostPlay();
        this.startBackgroundAutoPan();
    }
    
    protected abstract void updateOnPostPlayVideosFetched(final List<PostPlayVideo> p0);
    
    public boolean updatePlaybackPosition(final int n) {
        if (!this.isPostPlayAllowed()) {
            return false;
        }
        final boolean inPostPlay = this.inPostPlay(n);
        if (this.mInPostPlay && inPostPlay) {
            Log.d("nf_postplay", "Already in post play");
            return true;
        }
        if (this.mContext.getScreen().getState() == PlayerUiState.Interrupter) {
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
            this.mContext.getScreen().moveToState(PlayerUiState.PostPlay);
            return true;
        }
        Log.d("nf_postplay", "Not in in post play");
        return false;
    }
    
    public boolean wasPostPlayDismissed() {
        return this.mPostPlayDismissed;
    }
    
    private class FetchPostPlayForPlaybackCallback extends LoggingManagerCallback
    {
        public FetchPostPlayForPlaybackCallback() {
            super("nf_postplay");
        }
        
        @Override
        public void onPostPlayVideosFetched(final List<PostPlayVideo> mPostPlayVideos, final int n) {
            final boolean b = false;
            super.onPostPlayVideosFetched(mPostPlayVideos, n);
            if (PostPlay.this.mContext.destroyed()) {
                return;
            }
            if (n != 0 || mPostPlayVideos == null) {
                Log.w("nf_postplay", "Error loading post play data");
                PostPlay.this.mPostPlayDataExist = false;
                return;
            }
            if (Log.isLoggable("nf_postplay", 3) && mPostPlayVideos != null) {
                Log.d("nf_postplay", "Postplay data retrieved " + mPostPlayVideos.size());
            }
            PostPlay.this.mPostPlayVideos = mPostPlayVideos;
            final PostPlay this$0 = PostPlay.this;
            boolean mPostPlayDataExist = b;
            if (mPostPlayVideos != null) {
                mPostPlayDataExist = b;
                if (mPostPlayVideos.size() > 0) {
                    mPostPlayDataExist = true;
                }
            }
            this$0.mPostPlayDataExist = mPostPlayDataExist;
            PostPlay.this.updateOnPostPlayVideosFetched(mPostPlayVideos);
        }
    }
}
