// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.res.Configuration;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayContext;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import android.view.ViewGroup$MarginLayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import com.netflix.mediaclient.Log;
import android.app.Activity;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.widget.TextView;

public class PostPlayForEpisodes extends PostPlay
{
    protected TextView episodeBadge;
    private boolean mAutoPlayEnabled;
    protected View mAutoPlayView;
    protected TextView mInfoTitleView;
    private int mTimer;
    protected int mTimerValue;
    protected TextView mTimerView;
    private final Runnable onEverySecond;
    
    PostPlayForEpisodes(final NetflixActivity netflixActivity) {
        super(netflixActivity);
        this.mAutoPlayEnabled = true;
        this.onEverySecond = new PostPlayForEpisodes$1(this);
        this.init(netflixActivity);
    }
    
    PostPlayForEpisodes(final PlayerFragment playerFragment) {
        super(playerFragment);
        this.mAutoPlayEnabled = true;
        this.onEverySecond = new PostPlayForEpisodes$1(this);
        this.init();
    }
    
    private void init() {
        this.mTimerValue = this.mNetflixActivity.getResources().getInteger(2131492883);
        this.mAutoPlayEnabled = this.isAutoPlayEnabled();
        if (Log.isLoggable()) {
            Log.d("nf_postplay", "PostPlayForEpisodes:: timer max value " + this.mTimerValue);
        }
        if (!this.mAutoPlayEnabled && this.mTimerView != null) {
            ViewUtils.setVisibility(this.mAutoPlayView, ViewUtils$Visibility.INVISIBLE);
        }
        this.initInfoContainer(this.mNetflixActivity);
        this.initButtons();
    }
    
    private void init(final Activity activity) {
        this.mTimerValue = activity.getResources().getInteger(2131492883);
        this.mAutoPlayEnabled = this.isAutoPlayEnabled();
        if (Log.isLoggable()) {
            Log.d("nf_postplay", "PostPlayForEpisodes:: timer max value " + this.mTimerValue);
        }
        if (!this.mAutoPlayEnabled && this.mTimerView != null) {
            ViewUtils.setVisibility(this.mAutoPlayView, ViewUtils$Visibility.INVISIBLE);
        }
        this.initInfoContainer(activity);
        this.initButtons();
    }
    
    private void refreshTimerText() {
        if (this.mTimerView != null) {
            this.mTimerView.setText((CharSequence)String.valueOf(this.mTimer));
        }
    }
    
    private void updatePostPlayUI(final boolean b) {
        if (Coppola1Utils.isCoppolaExperience((Context)this.mNetflixActivity)) {
            final TextView mTitle = this.mTitle;
            int visibility;
            if (b) {
                visibility = 8;
            }
            else {
                visibility = 0;
            }
            mTitle.setVisibility(visibility);
            this.mSynopsis.setVisibility(8);
            this.mPlayButton.getLayoutParams().width = -2;
            ((ViewGroup$MarginLayoutParams)this.mPlayButton.getLayoutParams()).bottomMargin = (int)this.mNetflixActivity.getResources().getDimension(2131362269);
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
        if (this.mAutoPlayEnabled) {
            this.mNetflixActivity.getHandler().removeCallbacks(this.onEverySecond);
        }
    }
    
    @Override
    protected void doTransitionFromPostPlay() {
        if (this.mBackground.getVisibility() == 0) {
            this.mNetflixActivity.performUpAction();
        }
    }
    
    @Override
    protected void doTransitionToPostPlay() {
        if (this.mAutoPlayEnabled) {
            Log.d("nf_postplay", "Auto play is enabled");
            this.mTimer = this.mTimerValue;
            if (Coppola1Utils.isCoppolaContext((Context)this.mNetflixActivity)) {
                this.refreshTimerText();
            }
            this.mNetflixActivity.getHandler().postDelayed(this.onEverySecond, 1000L);
            return;
        }
        Log.d("nf_postplay", "Auto play is disabled");
    }
    
    @Override
    public void endOfPlay() {
        super.endOfPlay();
        this.setBackgroundImageVisible(true);
    }
    
    @Override
    protected void findViews() {
        this.mInfoTitleView = (TextView)this.mNetflixActivity.findViewById(2131690119);
        this.mAutoPlayView = this.mNetflixActivity.findViewById(2131690118);
        this.mTimerView = (TextView)this.mNetflixActivity.findViewById(2131690120);
        this.episodeBadge = (TextView)this.mNetflixActivity.findViewById(2131690126);
    }
    
    @Override
    protected int getLengthOfAutoPlayCountdow() {
        return this.mTimerValue;
    }
    
    @Override
    protected UserActionLogging$PostPlayExperience getPostPlayExpirience() {
        return UserActionLogging$PostPlayExperience.PostPlayNextEpisode;
    }
    
    @Override
    protected void handlePlayNow(final boolean b) {
        if (this.mNetflixActivity == null || this.mNetflixActivity.isFinishing()) {
            Log.d("nf_postplay", "Activity is alredy destroyed, ignore play now!");
            return;
        }
        Log.d("nf_postplay", "Play NEXT episode!");
        if (this.mPostPlayVideos == null || this.mPostPlayVideos.size() == 0) {
            Log.d("nf_postplay", "mPostPlayVideos size is zero");
            CharSequence text;
            if (this.mTitle.getText() != null) {
                text = this.mTitle.getText();
            }
            else {
                text = "null";
            }
            this.mNetflixActivity.getServiceManager().getClientLogging().getErrorLogging().logHandledException(String.format("SPY-7987 - PostPlayVideos empty for title  %s", text));
            this.reportNextPlayFailed(b);
            return;
        }
        if (this.mPostPlayContexts.size() == 0) {
            Log.d("nf_postplay", "mPostPlayContexts size is zero");
            CharSequence text2;
            if (this.mTitle.getText() != null) {
                text2 = this.mTitle.getText();
            }
            else {
                text2 = "null";
            }
            this.mNetflixActivity.getServiceManager().getClientLogging().getErrorLogging().logHandledException(String.format("SPY-7987 - PostPlayContexts empty for title  %s", text2));
        }
        final PostPlayVideo postPlayVideo = this.mPostPlayVideos.get(0);
        PlayContext playContext;
        if (this.mPostPlayContexts != null && this.mPostPlayContexts.size() > 1) {
            playContext = new PlayContextImp(this.mPostPlayContexts.get(0).getRequestId(), this.mPostPlayContexts.get(0).getTrackId(), 0, 0);
        }
        else {
            playContext = null;
        }
        if (postPlayVideo == null) {
            this.reportNextPlayFailed(b);
            return;
        }
        if (this.mPlayerFragment != null) {
            this.mPlayerFragment.playNextVideo(postPlayVideo.getPlayable(), playContext, b);
            this.reportNextPlay(postPlayVideo.getPlayable(), playContext, !b, null);
            return;
        }
        Log.e("nf_postplay", "handlePlayNow() - called with null PlayerFragment!");
    }
    
    protected void initButtons() {
        if (this.mPlayButton != null) {
            this.mPlayButton.setVisibility(0);
        }
        if (this.mStopButton != null) {
            this.mStopButton.setVisibility(8);
        }
        if (this.mMoreButton != null) {
            this.mMoreButton.setVisibility(8);
        }
        if (this.mPlayerFragment != null) {
            this.updatePostPlayUI(this.mPlayerFragment.isInPortrait());
        }
    }
    
    protected void initInfoContainer(final Activity activity) {
        if (this.mInfoTitleView != null) {
            this.mInfoTitleView.setText(activity.getResources().getText(2131231146));
        }
        if (this.mTimerView != null) {
            this.mTimerView.setVisibility(0);
        }
        if (this.mBackground != null) {
            this.mBackground.setVisibility(4);
        }
    }
    
    @Override
    protected void onConfigurationChanged(final Configuration configuration) {
        boolean b = true;
        super.onConfigurationChanged(configuration);
        if (configuration.orientation != 1) {
            b = false;
        }
        this.updatePostPlayUI(b);
    }
    
    @Override
    public void onPause() {
        if (this.mAutoPlayEnabled) {
            this.mNetflixActivity.getHandler().removeCallbacks(this.onEverySecond);
        }
        super.onPause();
    }
    
    @Override
    public void onResume() {
        if (!this.isInteractivePostPlay && this.mInPostPlay && this.mAutoPlayEnabled) {
            this.mNetflixActivity.getHandler().removeCallbacks(this.onEverySecond);
            this.mNetflixActivity.getHandler().post(this.onEverySecond);
        }
        super.onResume();
    }
    
    protected void onTimerEnd() {
        this.mPlayButton.setEnabled(false);
        this.handlePlayNow(true);
    }
    
    @Override
    public void postPlayDismissed() {
        super.postPlayDismissed();
        if (this.mAutoPlayEnabled) {
            this.mNetflixActivity.getHandler().removeCallbacks(this.onEverySecond);
        }
    }
    
    @Override
    protected void updateOnPostPlayVideosFetched() {
        Log.d("nf_postplay", "updateOnPostPlayVideosFetched start");
        if (this.mPostPlayVideos == null || this.mPostPlayVideos.size() < 1) {
            Log.e("nf_postplay", "We do not have any data! Do nothing!");
            return;
        }
        final PostPlayVideo postPlayVideo = this.mPostPlayVideos.get(0);
        if (postPlayVideo == null) {
            Log.e("nf_postplay", "We do not have any data! Do nothing!");
            return;
        }
        this.updateViews(postPlayVideo);
    }
    
    protected void updateViews(final PostPlayVideo postPlayVideo) {
        String title;
        if ((title = postPlayVideo.getTitle()) == null) {
            title = "";
        }
        final String string = this.mNetflixActivity.getResources().getString(2131231063, new Object[] { title });
        final String storyUrl = postPlayVideo.getStoryUrl();
        String s = postPlayVideo.getInterestingUrl();
        if (postPlayVideo.getType() != VideoType.EPISODE) {
            s = postPlayVideo.getStoryUrl();
        }
        final String string2 = this.mNetflixActivity.getResources().getString(2131230894, new Object[] { string });
        if (this.mBackground != null) {
            if (!StringUtils.isEmpty(storyUrl) && this.mNetflixActivity.isTablet()) {
                NetflixActivity.getImageLoader((Context)this.mNetflixActivity).showImg(this.mBackground, storyUrl, IClientLogging$AssetType.merchStill, string2, ImageLoader$StaticImgConfig.DARK, true, 1);
            }
            else if (!StringUtils.isEmpty(s) && !this.mNetflixActivity.isTablet()) {
                NetflixActivity.getImageLoader((Context)this.mNetflixActivity).showImg(this.mBackground, s, IClientLogging$AssetType.merchStill, string2, ImageLoader$StaticImgConfig.DARK, true, 1);
            }
        }
        String string3;
        if (postPlayVideo.isNSRE()) {
            LoMoUtils.toggleEpisodeBadge(postPlayVideo.getEpisodeBadges(), this.episodeBadge);
            string3 = string;
        }
        else {
            final String s2 = string3 = this.mNetflixActivity.getResources().getString(2131231151, new Object[] { postPlayVideo.getPlayable().getSeasonAbbrSeqLabel(), postPlayVideo.getPlayable().getEpisodeNumber(), string });
            if (this.episodeBadge != null) {
                this.episodeBadge.setVisibility(8);
                string3 = s2;
            }
        }
        if (Log.isLoggable()) {
            Log.d("nf_postplay", "Title: " + string3);
        }
        if (this.mTitle != null) {
            this.mTitle.setText((CharSequence)string3);
        }
        final String synopsis = postPlayVideo.getSynopsis();
        if (this.mSynopsis != null && StringUtils.isNotEmpty(synopsis)) {
            this.mSynopsis.setText((CharSequence)synopsis);
        }
        if (Log.isLoggable()) {
            Log.d("nf_postplay", "Synopsis: " + postPlayVideo.getSynopsis());
        }
    }
}
