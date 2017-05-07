// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import java.util.List;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.PostPlayVideo;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

public final class PostPlayForEpisodes extends PostPlay
{
    private static final int ONE_SECOND_IN_MS = 1000;
    protected AdvancedImageView mPlayButtonImage;
    private int mTimer;
    private int mTimerValue;
    private TextView mTimerView;
    private final Runnable onEverySecond;
    
    public PostPlayForEpisodes(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.onEverySecond = new Runnable() {
            @Override
            public void run() {
                if (!PostPlayForEpisodes.this.mInPostPlay || PostPlayForEpisodes.this.mContext.destroyed()) {
                    Log.d("nf_postplay", "post play timer exit or activity is destroyed");
                    return;
                }
                PostPlayForEpisodes.this.mTimer--;
                if (PostPlayForEpisodes.this.mTimerView != null) {
                    PostPlayForEpisodes.this.mTimerView.setText((CharSequence)String.valueOf(PostPlayForEpisodes.this.mTimer));
                }
                if (PostPlayForEpisodes.this.mTimer <= 0) {
                    Log.d("nf_postplay", "Timer counter to 0, play next episode");
                    PostPlayForEpisodes.this.mPlayButton.setEnabled(false);
                    PostPlayForEpisodes.this.handlePlayNow(true);
                    return;
                }
                PostPlayForEpisodes.this.mContext.getHandler().postDelayed((Runnable)this, 1000L);
            }
        };
        this.init();
    }
    
    private void init() {
        this.mTimerValue = this.mContext.getResources().getInteger(2131361799);
        if (Log.isLoggable("nf_postplay", 3)) {
            Log.d("nf_postplay", "PostPlayForEpisodes:: timer max value " + this.mTimerValue);
        }
        this.mTimerView = (TextView)this.mContext.findViewById(2131231044);
        this.mPlayButtonImage = (AdvancedImageView)this.mContext.findViewById(2131231047);
    }
    
    @Override
    public void destroy() {
        super.destroy();
        this.mContext.getHandler().removeCallbacks(this.onEverySecond);
    }
    
    @Override
    protected void doTransitionToPostPlay() {
        this.mTimer = this.mTimerValue;
        this.mContext.getHandler().postDelayed(this.onEverySecond, 1000L);
    }
    
    @Override
    protected void handlePlayNow(final boolean b) {
        if (this.mContext.destroyed()) {
            Log.d("nf_postplay", "Activity is alredy destroyed, ignore play now!");
        }
        else {
            Log.d("nf_postplay", "Play NEXT episode!");
            final PostPlayVideo postPlayVideo = this.mPostPlayVideos.get(0);
            if (postPlayVideo != null) {
                this.mContext.playNextVideo(postPlayVideo, new PlayContextImp(postPlayVideo.getPostPlayRequestId(), postPlayVideo.getPostPlayTrackId(), 0, 0), b);
            }
        }
    }
    
    @Override
    public void onPause() {
        this.mContext.getHandler().removeCallbacks(this.onEverySecond);
    }
    
    @Override
    public void onResume() {
        if (this.mInPostPlay) {
            this.mContext.getHandler().removeCallbacks(this.onEverySecond);
            this.mContext.getHandler().post(this.onEverySecond);
        }
    }
    
    @Override
    public void postPlayDismissed() {
        super.postPlayDismissed();
        this.mContext.getHandler().removeCallbacks(this.onEverySecond);
    }
    
    @Override
    protected void updateOnPostPlayVideosFetched(final List<PostPlayVideo> list) {
        Log.d("nf_postplay", "updateOnPostPlayVideosFetched start");
        if (list == null || list.size() < 1) {
            Log.e("nf_postplay", "We do not have any data! Do nothing!");
        }
        else {
            final PostPlayVideo postPlayVideo = list.get(0);
            if (postPlayVideo == null) {
                Log.e("nf_postplay", "We do not have any data! Do nothing!");
                return;
            }
            String title;
            if ((title = postPlayVideo.getTitle()) == null) {
                title = "";
            }
            final String storyUrl = postPlayVideo.getStoryUrl();
            final String interestingUrl = postPlayVideo.getInterestingUrl();
            final String format = String.format(this.mContext.getResources().getString(2131296684), title);
            if (this.mBackground != null) {
                if (!StringUtils.isEmpty(storyUrl) && this.mContext.isTablet()) {
                    NetflixActivity.getImageLoader((Context)this.mContext).showImg(this.mBackground, storyUrl, IClientLogging.AssetType.merchStill, format, true, true, 1);
                }
                else if (!StringUtils.isEmpty(interestingUrl) && !this.mContext.isTablet()) {
                    NetflixActivity.getImageLoader((Context)this.mContext).showImg(this.mBackground, interestingUrl, IClientLogging.AssetType.merchStill, format, true, true, 1);
                }
            }
            if (!StringUtils.isEmpty(interestingUrl) && this.mPlayButtonImage != null) {
                NetflixActivity.getImageLoader((Context)this.mContext).showImg(this.mPlayButtonImage, interestingUrl, IClientLogging.AssetType.merchStill, format, true, true, 1);
            }
            final String format2 = String.format(this.mContext.getResources().getString(2131296687, new Object[] { postPlayVideo.getSeasonNumber(), postPlayVideo.getEpisodeNumber(), title }), new Object[0]);
            if (Log.isLoggable("nf_postplay", 3)) {
                Log.d("nf_postplay", "Title: " + format2);
            }
            if (this.mTitle != null) {
                this.mTitle.setText((CharSequence)format2);
            }
            if (this.mSynopsis != null && postPlayVideo.getSynopsis() != null) {
                this.mSynopsis.setText((CharSequence)postPlayVideo.getSynopsis());
            }
            if (Log.isLoggable("nf_postplay", 3)) {
                Log.d("nf_postplay", "Synopsis: " + postPlayVideo.getSynopsis());
            }
        }
    }
}
