// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.ImageView$ScaleType;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.util.AttributeSet;
import android.content.Context;

public class WPWordWallyCardLayout extends WPCardLayout
{
    private int preferredHeight;
    
    public WPWordWallyCardLayout(final Context context) {
        super(context);
    }
    
    public WPWordWallyCardLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    @Override
    protected void createVideoMask() {
    }
    
    public int getCardHeight() {
        return this.preferredHeight;
    }
    
    @Override
    protected void onHideVideo() {
        this.imageView.setVisibility(0);
    }
    
    @Override
    protected void onVideoPlaybackStarted() {
        this.imageView.setVisibility(8);
    }
    
    @Override
    protected void storeViews(final Context context) {
        final int screenWidthInPixels = DeviceUtils.getScreenWidthInPixels(this.getContext());
        float n = 0.36f;
        if (DeviceUtils.getScreenAspectRatio(this.getContext()) <= 1.5f) {
            n = 0.4f;
        }
        final int n2 = (int)(n * screenWidthInPixels);
        this.preferredHeight = (int)(n2 * 0.5625f);
        final WPWordWallyCardImageView imageView = new WPWordWallyCardImageView(this.getContext());
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(n2, -2);
        imageView.setScaleType(ImageView$ScaleType.CENTER_CROP);
        imageView.setAdjustViewBounds(true);
        this.addView((View)imageView, (ViewGroup$LayoutParams)layoutParams);
        this.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.imageView = imageView;
    }
    
    @Override
    public String toString() {
        return "WPWordWallyCardLayout{preferredHeight=" + this.preferredHeight + "} " + super.toString();
    }
}
