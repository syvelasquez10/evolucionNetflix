// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.LayoutInflater;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import com.netflix.model.leafs.PostPlayItem;
import com.netflix.model.leafs.PostPlayAction;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.Iterator;
import android.animation.TimeInterpolator;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import android.view.animation.DecelerateInterpolator;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import android.widget.TextView;

public final class PostPlayForPlayer extends PostPlay
{
    private TextView experienceTitle;
    private final ImageLoader$ImageLoaderListener mImageLoaderListener;
    private final DecelerateInterpolator mPanAnimationInterpolator;
    private int mSelected;
    private final AtomicBoolean mVideoFullScreen;
    private VideoWindowForPostplay mVideoWindow;
    List<PostPlayBackground> postPlayBackgrounds;
    List<PostPlayItemView> postPlayItems;
    
    public PostPlayForPlayer(final PlayerFragment playerFragment) {
        super(playerFragment);
        this.mSelected = 0;
        this.mPanAnimationInterpolator = new DecelerateInterpolator();
        this.mVideoFullScreen = new AtomicBoolean(true);
        this.postPlayItems = new ArrayList<PostPlayItemView>(5);
        this.postPlayBackgrounds = new ArrayList<PostPlayBackground>(5);
        this.mImageLoaderListener = new PostPlayForPlayer$1(this);
        this.init();
    }
    
    private void executeTransitionIn() {
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
        if (this.mPlayerFragment == null) {
            Log.e("nf_postplay", "init() - called with null PlayerFragment!");
            return;
        }
        this.mVideoWindow = VideoWindowForPostplayFactory.createVideoWindow(this.mPlayerFragment);
    }
    
    private void scrollToItem(final int mSelected) {
        if (this.mNetflixActivity.isFinishing()) {
            return;
        }
        this.mBackgroundContainer.animate().setDuration(250L).x((float)(DeviceUtils.getScreenWidthInPixels((Context)this.mNetflixActivity) * mSelected * -1)).setInterpolator((TimeInterpolator)this.mPanAnimationInterpolator);
        if (this.mPostPlayExperience.getItems().get(mSelected) != null) {
            this.mSelected = mSelected;
            return;
        }
        Log.e("nf_postplay", "PostPlay not found for index " + mSelected);
    }
    
    @Override
    protected void doTransitionFromPostPlay() {
        if (this.autoplayTimer != null && this.mPostPlayExperience.getAutoplay()) {
            this.autoplayTimer.stopTimer();
            final Iterator<PostPlayItemView> iterator = this.postPlayItems.iterator();
            while (iterator.hasNext()) {
                iterator.next().stopTimer();
            }
            final Iterator<PostPlayBackground> iterator2 = this.postPlayBackgrounds.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().stopTimer();
            }
        }
    }
    
    @Override
    protected void doTransitionToPostPlay() {
        if (this.autoplayTimer != null && this.mPostPlayExperience.getAutoplay()) {
            this.autoplayTimer.startTimer();
            final Iterator<PostPlayItemView> iterator = this.postPlayItems.iterator();
            while (iterator.hasNext()) {
                iterator.next().startTimer();
            }
            final Iterator<PostPlayBackground> iterator2 = this.postPlayBackgrounds.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().startTimer();
            }
        }
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
        if (this.isInteractivePostPlay) {
            if (Log.isLoggable()) {
                Log.d("nf_postplay", "End of play post play for interactive content will be handled in super class");
            }
        }
        else {
            this.mVideoWindow.setVisible(false);
            if (this.mPostPlayExperience != null) {
                this.setBackgroundImageVisible(true);
                return;
            }
            if (this.mPlayerFragment != null) {
                this.mPlayerFragment.cleanupAndExit();
            }
        }
    }
    
    @Override
    public void fetchPostPlayVideosIfNeeded(final String s, final VideoType videoType, final PostPlayRequestContext postPlayRequestContext) {
        Log.d("nf_postplay", "Fetch PostPlay data");
        super.fetchPostPlayVideosIfNeeded(s, videoType, postPlayRequestContext);
    }
    
    @Override
    protected void findViews() {
        this.experienceTitle = (TextView)this.mNetflixActivity.findViewById(2131690105);
    }
    
    @Override
    protected int getLengthOfAutoPlayCountdow() {
        return 0;
    }
    
    @Override
    protected UserActionLogging$PostPlayExperience getPostPlayExpirience() {
        return UserActionLogging$PostPlayExperience.PostPlaySuggestions;
    }
    
    @Override
    protected boolean isAutoPlayUsed() {
        return this.mPostPlayExperience.getAutoplay();
    }
    
    @Override
    protected boolean isPostPlayEnabled() {
        if (this.isInteractivePostPlay && this.interactivePostPlayManager != null && this.interactivePostPlayManager.waitUntilEndOfPlay()) {
            this.postPlayDismissed();
            return false;
        }
        return super.isPostPlayEnabled();
    }
    
    @Override
    public void postPlayDismissed() {
        super.postPlayDismissed();
        this.executeTransitionOut();
    }
    
    @Override
    public void setBackgroundImageVisible(final boolean backgroundImageVisible) {
        super.setBackgroundImageVisible(backgroundImageVisible);
        if (backgroundImageVisible) {
            final Iterator<PostPlayBackground> iterator = this.postPlayBackgrounds.iterator();
            while (iterator.hasNext()) {
                iterator.next().startBackgroundAutoPan();
            }
        }
    }
    
    @Override
    protected void updateOnPostPlayVideosFetched() {
        if (this.mPostPlayExperience == null || this.mPostPlayExperience.getItems().size() == 0) {
            Log.e("nf_postplay", "We do not have any data! Do nothing!");
        }
        else {
            if (this.mNetflixActivity.isFinishing()) {
                Log.e("nf_postplay", "Activity for playback is already not valid! Do nothing!");
                return;
            }
            final LayoutInflater layoutInflater = this.mNetflixActivity.getLayoutInflater();
            final boolean nonMemberPlayback = this.mNetflixActivity.getServiceManager().isNonMemberPlayback();
            this.mBackgroundContainer.removeAllViews();
            this.mItemsContainer.removeAllViews();
            if (this.experienceTitle != null) {
                String text;
                if (this.mPostPlayExperience.getExperienceTitle().size() != 0 && this.mPostPlayExperience.getExperienceTitle().get(0).getDisplayText() != null) {
                    text = this.mPostPlayExperience.getExperienceTitle().get(0).getDisplayText();
                }
                else if ("recommendations".equals(this.mPostPlayExperience.getType())) {
                    text = this.mNetflixActivity.getResources().getString(2131231152);
                }
                else {
                    text = "";
                }
                this.experienceTitle.setText((CharSequence)text);
                final TextView experienceTitle = this.experienceTitle;
                int visibility;
                if (text.isEmpty()) {
                    visibility = 8;
                }
                else {
                    visibility = 0;
                }
                experienceTitle.setVisibility(visibility);
            }
            boolean b;
            if (this.mPostPlayExperience.getItems().size() > 1) {
                b = true;
            }
            else {
                b = false;
            }
            this.mBackgroundContainer.getLayoutParams().width = DeviceUtils.getScreenWidthInPixels((Context)this.mNetflixActivity) * this.mPostPlayExperience.getItems().size();
            int n;
            if (b) {
                n = 2130903230;
            }
            else {
                n = 2130903228;
            }
            int n2;
            if (nonMemberPlayback) {
                n2 = 2130903238;
            }
            else if (b) {
                n2 = 2130903237;
            }
            else {
                n2 = 2130903236;
            }
            final Iterator<PostPlayItem> iterator = this.mPostPlayExperience.getItems().iterator();
            int n3 = 0;
            while (iterator.hasNext()) {
                final PostPlayItem postPlayItem = iterator.next();
                if (this.hasValidPlayAction(postPlayItem)) {
                    final PostPlayBackground postPlayBackground = (PostPlayBackground)layoutInflater.inflate(n, (ViewGroup)this.mBackgroundContainer, false);
                    this.mBackgroundContainer.addView((View)postPlayBackground);
                    postPlayBackground.updateViews(postPlayItem, this.mNetflixActivity, this.mPlayerFragment, PostPlayRequestContext.POST_PLAY);
                    postPlayBackground.getLayoutParams().width = DeviceUtils.getScreenWidthInPixels((Context)this.mNetflixActivity);
                    this.postPlayBackgrounds.add(postPlayBackground);
                    final PostPlayCallToAction postPlayCallToAction = new PostPlayCallToAction(this.mNetflixActivity, this.mPlayerFragment, postPlayItem.getPlayAction(), PostPlayRequestContext.POST_PLAY);
                    final PostPlayItemView postPlayItemView = (PostPlayItemView)layoutInflater.inflate(n2, (ViewGroup)this.mItemsContainer, false);
                    this.mItemsContainer.addView((View)postPlayItemView);
                    this.postPlayItems.add(postPlayItemView);
                    if (nonMemberPlayback) {
                        postPlayItemView.updateViews(postPlayItem, this.mNetflixActivity, this.mPlayerFragment, PostPlayRequestContext.POST_PLAY, (View$OnClickListener)new PostPlayForPlayer$SignupListener(this, this.mNetflixActivity));
                    }
                    else if (b) {
                        postPlayItemView.updateViews(postPlayItem, this.mNetflixActivity, this.mPlayerFragment, PostPlayRequestContext.POST_PLAY, (View$OnClickListener)new PostPlayForPlayer$SelectListener(this, n3, postPlayBackground));
                    }
                    else if (postPlayItem.getPlayAction().getName().equals("playTrailer")) {
                        postPlayItemView.updateViews(postPlayItem, this.mNetflixActivity, this.mPlayerFragment, PostPlayRequestContext.POST_PLAY, null);
                    }
                    else {
                        postPlayItemView.updateViews(postPlayItem, this.mNetflixActivity, this.mPlayerFragment, PostPlayRequestContext.POST_PLAY, postPlayCallToAction.generatePlayHandler());
                    }
                    ++n3;
                }
            }
            if (this.mPostPlayExperience.getAutoplay() && this.mPostPlayExperience.getAutoplaySeconds() > 0 && !nonMemberPlayback) {
                this.setupAutoPlay(PostPlayRequestContext.POST_PLAY);
            }
        }
    }
}
