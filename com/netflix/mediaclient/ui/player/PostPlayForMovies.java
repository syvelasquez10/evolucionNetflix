// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.MotionEvent;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.servicemgr.PostPlayVideo;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import java.util.List;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.View;

public final class PostPlayForMovies extends PostPlay
{
    private final int LIST_SIZE;
    private View mBackgroundContainer;
    private ImageLoader.ImageLoaderListener mImageLoaderListener;
    private View mMetadata;
    private List<View> mPlayButtons;
    private NetflixRatingBar mRatingBar;
    private List<AdvancedImageView> mRecommendationBoxArts;
    private int mSelected;
    private TextView mVideoDetails;
    private AtomicBoolean mVideoFullScreen;
    private VideoWindowForPostplay mVideoWindow;
    
    public PostPlayForMovies(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.LIST_SIZE = 3;
        this.mRecommendationBoxArts = new ArrayList<AdvancedImageView>(3);
        this.mPlayButtons = new ArrayList<View>(3);
        this.mSelected = -1;
        this.mVideoFullScreen = new AtomicBoolean(true);
        this.mImageLoaderListener = new ImageLoader.ImageLoaderListener() {
            @Override
            public void onErrorResponse(final String s) {
                if (Log.isLoggable("nf_postplay", 5)) {
                    Log.w("nf_postplay", "Image failed to be retrieved " + s);
                }
            }
            
            @Override
            public void onResponse(final Bitmap bitmap, final String s) {
                if (Log.isLoggable("nf_postplay", 3)) {
                    Log.d("nf_postplay", "Image retrieved " + s);
                }
            }
        };
        this.init();
    }
    
    private void addBoxArt(final int n, final int n2) {
        final AdvancedImageView advancedImageView = (AdvancedImageView)this.mContext.findViewById(n);
        this.mRecommendationBoxArts.add(advancedImageView);
        if (advancedImageView == null) {
            Log.e("nf_postplay", "Image not found for index " + n2);
            return;
        }
        advancedImageView.setBackgroundResource(2130837603);
        advancedImageView.setOnTouchListener((View$OnTouchListener)new ChangeRecommendation(n2));
    }
    
    private void addPlayButton(final int n, final int n2) {
        final View viewById = this.mContext.findViewById(n);
        this.mPlayButtons.add(viewById);
        if (viewById == null) {
            Log.e("nf_postplay", "Play button not found for index " + n2);
            return;
        }
        viewById.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (PostPlayForMovies.this.mSelected != n2) {
                    Log.e("nf_postplay", "This should NOT happen. This movie was NOT selected before");
                }
                PostPlayForMovies.this.handlePlayNow(false);
            }
        });
    }
    
    private void executeTransitionIn() {
        ViewUtils.setVisibility(this.mMetadata, true);
        ViewUtils.setVisibility(this.mPlayButton, true);
        if (this.mVideoWindow.canVideoVindowResize()) {
            this.setBackgroundImageVisible(true);
        }
        this.mVideoWindow.animateIn();
    }
    
    private void executeTransitionOut() {
        this.mVideoWindow.animateOut(null);
        this.setBackgroundImageVisible(false);
        this.mVideoFullScreen.set(false);
    }
    
    private void init() {
        this.mVideoWindow = VideoWindowForPostplayFactory.createVideoWindow(this.mContext);
        this.addBoxArt(2131165567, 0);
        this.addBoxArt(2131165569, 1);
        this.addBoxArt(2131165571, 2);
        this.addPlayButton(2131165568, 0);
        this.addPlayButton(2131165570, 1);
        this.addPlayButton(2131165572, 2);
    }
    
    private void updateUi(final PostPlayVideo details, final int n) {
        if (details != null) {
            String title;
            if ((title = details.getTitle()) == null) {
                title = "";
            }
            final String storyUrl = details.getStoryUrl();
            final String format = String.format(this.mContext.getResources().getString(2131493316), title);
            if (!StringUtils.isEmpty(storyUrl)) {
                NetflixActivity.getImageLoader((Context)this.mContext).showImg(this.mBackground, storyUrl, IClientLogging.AssetType.merchStill, format, true, true, 1);
            }
            for (int i = 0; i < 3; ++i) {
                final View view = this.mPlayButtons.get(i);
                if (i == n) {
                    ViewUtils.setVisibility(view, ViewUtils.Visibility.VISIBLE);
                }
                else {
                    ViewUtils.setVisibility(view, ViewUtils.Visibility.GONE);
                }
            }
            if (Log.isLoggable("nf_postplay", 3)) {
                Log.d("nf_postplay", "Title: " + title);
            }
            if (this.mTitle != null) {
                this.mTitle.setText((CharSequence)title);
            }
            if (this.mSynopsis != null) {
                if (details.getSynopsis() != null) {
                    this.mSynopsis.setText((CharSequence)details.getSynopsis());
                }
                else {
                    this.mSynopsis.setText((CharSequence)"");
                }
            }
            if (Log.isLoggable("nf_postplay", 3)) {
                Log.d("nf_postplay", "Synopsis: " + details.getSynopsis());
            }
            if (this.mRatingBar != null) {
                this.mRatingBar.setDetails(details);
            }
            if (this.mVideoDetails != null) {
                this.mVideoDetails.setText(StringUtils.getBasicInfoString((Context)this.mContext, details));
            }
        }
    }
    
    @Override
    protected void doTransitionToPostPlay() {
        if (this.mPostPlayDismissed) {
            Log.d("nf_postplay", "Second time postplay");
            this.executeTransitionIn();
            this.mVideoWindow.setVisible(false);
            return;
        }
        Log.d("nf_postplay", "First time postplay");
    }
    
    @Override
    public void endOfPlay() {
        super.endOfPlay();
        this.mVideoWindow.setVisible(false);
        if (this.mSelected < 0) {
            this.mSelected = 0;
        }
        this.updateUi(this.mPostPlayVideos.get(this.mSelected), this.mSelected);
        this.setBackgroundImageVisible(true);
        ViewUtils.setVisibility(this.mMetadata, true);
        ViewUtils.setVisibility(this.mPlayButton, true);
    }
    
    @Override
    void findViews() {
        this.mRatingBar = (NetflixRatingBar)this.mContext.findViewById(2131165566);
        this.mVideoDetails = (TextView)this.mContext.findViewById(2131165565);
        this.mBackgroundContainer = this.mContext.findViewById(2131165573);
        this.mMetadata = this.mContext.findViewById(2131165564);
    }
    
    @Override
    protected void handlePlayNow(final boolean b) {
        Log.d("nf_postplay", "Play recommendation");
        if (this.mSelected < 0) {
            Log.e("nf_postplay", "Error state, movie was not selected");
        }
        else {
            final PostPlayVideo postPlayVideo = this.mPostPlayVideos.get(this.mSelected);
            if (postPlayVideo != null) {
                this.mContext.playNextVideo(postPlayVideo, new PlayContextImp(postPlayVideo.getPostPlayRequestId(), postPlayVideo.getPostPlayTrackId(), 0, 0), b);
            }
        }
    }
    
    @Override
    public void init(final String s) {
        if (this.mContext.isTablet()) {
            Log.d("nf_postplay", "Fetch data for tablet only");
            super.init(s);
            return;
        }
        Log.d("nf_postplay", "Fetch data for tablet only, skip for phone");
    }
    
    @Override
    protected boolean isPostPlayEnabled() {
        return super.isPostPlayEnabled() && this.mContext.isTablet();
    }
    
    @Override
    public void postPlayDismissed() {
        super.postPlayDismissed();
        this.executeTransitionOut();
    }
    
    @Override
    public void setBackgroundImageVisible(final boolean b) {
        if (this.mBackgroundContainer != null) {
            if (!b) {
                this.mBackgroundContainer.setVisibility(4);
                return;
            }
            this.mBackgroundContainer.setVisibility(0);
        }
    }
    
    @Override
    protected void updateOnPostPlayVideosFetched(final List<PostPlayVideo> list) {
        if (list == null || list.size() < 1) {
            Log.e("nf_postplay", "We do not have any data! Do nothing!");
        }
        else {
            for (int i = 0; i < list.size(); ++i) {
                final PostPlayVideo postPlayVideo = list.get(i);
                if (postPlayVideo != null) {
                    final AdvancedImageView advancedImageView = this.mRecommendationBoxArts.get(i);
                    if (advancedImageView != null) {
                        advancedImageView.setVisibility(0);
                        String title;
                        if ((title = postPlayVideo.getTitle()) == null) {
                            title = "";
                        }
                        if (postPlayVideo.getStoryUrl() != null) {
                            NetflixActivity.getImageLoader((Context)this.mContext).getImg(postPlayVideo.getStoryUrl(), IClientLogging.AssetType.merchStill, 1920, 1080, this.mImageLoaderListener);
                        }
                        final String format = String.format(this.mContext.getResources().getString(2131493316), title);
                        if (postPlayVideo.getHighResolutionLandscapeBoxArtUrl() != null) {
                            NetflixActivity.getImageLoader((Context)this.mContext).showImg(this.mRecommendationBoxArts.get(i), postPlayVideo.getHighResolutionLandscapeBoxArtUrl(), IClientLogging.AssetType.merchStill, format, true, true, 1);
                        }
                    }
                }
                else {
                    this.mRecommendationBoxArts.get(i).setVisibility(8);
                }
            }
        }
    }
    
    private class ChangeRecommendation implements View$OnTouchListener
    {
        private int selection;
        
        private ChangeRecommendation(final int selection) {
            this.selection = selection;
        }
        
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (Log.isLoggable("nf_postplay", 3)) {
                Log.d("nf_postplay", "BoxArt touched: " + this.selection);
            }
            if (PostPlayForMovies.this.mVideoFullScreen.getAndSet(false)) {
                Log.d("nf_postplay", "Video was full size, scale down");
                PostPlayForMovies.this.executeTransitionIn();
            }
            if (PostPlayForMovies.this.mPostPlayVideos.size() > this.selection) {
                PostPlayForMovies.this.mSelected = this.selection;
                PostPlayForMovies.this.updateUi(PostPlayForMovies.this.mPostPlayVideos.get(this.selection), this.selection);
                return true;
            }
            Log.e("nf_postplay", "PostPlay not found for index " + this.selection);
            return false;
        }
    }
}
