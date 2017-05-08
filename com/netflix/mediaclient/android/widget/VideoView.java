// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.text.TextUtils;
import com.netflix.mediaclient.service.configuration.ABTestUtils.AimLowTextPlaceholderABTestUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.View;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;

public class VideoView extends AdvancedImageView implements VideoViewGroup$IVideoView<Video>
{
    protected VideoDetailsClickListener clicker;
    private boolean isHorizontal;
    private String mTitle;
    private TitleDrawable mTitleDrawable;
    protected PlayContext playContext;
    private boolean useCallbackAnimation;
    
    public VideoView(final Context context) {
        super(context);
        this.init();
    }
    
    public VideoView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public VideoView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.setFocusable(true);
        this.setBackgroundResource(2130838166);
        this.useCallbackAnimation = BrowseExperience.showKidsExperience();
        this.clicker = new VideoDetailsClickListener((NetflixActivity)this.getContext(), (PlayContextProvider)this, this.useCallbackAnimation);
        this.setPressedStateHandlerEnabled(this.useCallbackAnimation);
    }
    
    private void renderTextOnPlaceholder() {
        if (this.mTitleDrawable == null) {
            final int dimensionPixelSize = this.getContext().getResources().getDimensionPixelSize(2131427945);
            (this.mTitleDrawable = new TitleDrawable()).setMaxLines(this.getResources().getInteger(2131558437));
            this.mTitleDrawable.setTextColor(this.getContext().getResources().getColor(2131755286));
            this.mTitleDrawable.setTextPadding(0, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            this.mTitleDrawable.setTextSize(this.getContext().getResources().getDimensionPixelSize(2131427949));
            this.mTitleDrawable.setBackground(this.getContext(), BrowseExperience.getImageLoaderConfig().getPlaceholderResId());
        }
        this.mTitleDrawable.setVideoTitle(this.mTitle);
        this.setImageDrawable(this.mTitleDrawable);
    }
    
    public String getImageUrl(final Video video, final boolean b) {
        int n = 0;
        if (this.isHorizontal) {
            n = 1;
        }
        return BrowseExperience.getLomoVideoViewImageUrl(this.getContext(), video, VideoView.class, n);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).clear((AdvancedImageView)this);
        this.setVisibility(4);
        this.clicker.remove((View)this);
    }
    
    public void setClickListener(final VideoDetailsClickListener clicker) {
        this.clicker = clicker;
    }
    
    public void setIsHorizontal(final boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }
    
    public void update(final Video video, final Trackable trackable, int visibility, final boolean b, final boolean b2) {
        if (trackable != null) {
            this.playContext = new PlayContextImp(trackable, visibility);
        }
        final String imageUrl = this.getImageUrl(video, b2);
        if (StringUtils.isEmpty(imageUrl)) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        this.setVisibility(visibility);
        this.clicker.update((View)this, video, this.pressedHandler);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final IClientLogging$AssetType boxArt = IClientLogging$AssetType.boxArt;
        final String title = video.getTitle();
        final ImageLoader$StaticImgConfig imageLoaderConfig = BrowseExperience.getImageLoaderConfig();
        if (b) {
            visibility = 1;
        }
        else {
            visibility = 0;
        }
        imageLoader.showImg((AdvancedImageView)this, imageUrl, boxArt, title, imageLoaderConfig, true, visibility);
        if (AimLowTextPlaceholderABTestUtils.shouldShowTextOnPlaceholder(this.getContext()) && !this.isImageLoaded() && !TextUtils.equals((CharSequence)this.mTitle, (CharSequence)video.getTitle())) {
            this.mTitle = video.getTitle();
            this.renderTextOnPlaceholder();
        }
    }
}
