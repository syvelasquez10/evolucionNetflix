// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.content.res.Resources;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.util.AttributeSet;
import android.content.Context;

public class WPStandardCardLayout extends WPCardLayout
{
    public WPStandardCardLayout(final Context context) {
        super(context);
    }
    
    public WPStandardCardLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    @Override
    protected void createVideoMask() {
        final ImageView videoMaskImageView = new ImageView(this.getContext());
        videoMaskImageView.setImageDrawable((Drawable)this.videoMaskDrawable);
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(this.cardWidth, this.cardHeight);
        frameLayout$LayoutParams.gravity = 48;
        videoMaskImageView.setVisibility(8);
        this.addView((View)videoMaskImageView, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.videoMaskImageView = videoMaskImageView;
    }
    
    @Override
    protected void onHideVideo() {
        ((WPStandardCardImageView)this.imageView).showShadowOnly(false);
    }
    
    @Override
    protected void onVideoPlaybackStarted() {
        ((WPStandardCardImageView)this.imageView).showShadowOnly(true);
    }
    
    @Override
    protected void storeViews(final Context context) {
        final WPStandardCardImageView imageView = new WPStandardCardImageView(this.getContext());
        final Resources resources = context.getResources();
        this.cardWidth = resources.getDimensionPixelSize(2131361945);
        this.cardHeight = resources.getDimensionPixelSize(2131361943);
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(this.cardWidth + 2, resources.getDimensionPixelSize(2131361944) + 2);
        frameLayout$LayoutParams.gravity = 48;
        frameLayout$LayoutParams.setMargins(-1, -1, 0, 0);
        this.addView((View)imageView, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.imageView = imageView;
    }
    
    @Override
    public String toString() {
        return "WPStandardCardLayout{} " + super.toString();
    }
}
