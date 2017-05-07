// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.view.animation.Animation$AnimationListener;
import android.view.animation.AlphaAnimation;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.Log;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.view.View;
import android.widget.AbsListView;
import android.view.animation.Animation;
import android.widget.AbsListView$OnScrollListener;

public final class DetailsPageParallaxScrollListener implements AbsListView$OnScrollListener
{
    static final int ACTION_BAR_GRADIENT_RANGE = 76;
    static final int OPAQUE = 255;
    static final float PARALLAX_FACTOR = 2.8f;
    private static final String TAG = "detailsScroller";
    private static final int TRACKER_VIEW_FADE_DURATION = 300;
    static final int TRANSPARENT = 0;
    private Animation animationFadeIn;
    private final AbsListView gridview;
    private float parallaxRatio;
    private final View parallaxView;
    private final SeasonsSpinner seasonsSpinner;
    private int trackerViewfinalXPosition;
    private int trackerViewlatchYPosition;
    private final View trackingView;
    
    public DetailsPageParallaxScrollListener(final SeasonsSpinner seasonsSpinner, final AbsListView absListView, final View parallaxView, final View trackingView, final Drawable drawable) {
        this.seasonsSpinner = seasonsSpinner;
        this.parallaxView = parallaxView;
        this.trackingView = trackingView;
        this.gridview = absListView;
        this.setupTrackerViewAnimation();
        this.setTrackerViewPositions(absListView);
        this.calculateParallaxRatio();
    }
    
    private void adjustForParallax(final View view, final int n) {
        if (AndroidUtils.getAndroidVersion() >= 16 && view != null) {
            view.setTranslationY(n * 2.8f);
        }
    }
    
    private void calculateParallaxRatio() {
        float n;
        if (DeviceUtils.isPortrait(this.gridview.getContext())) {
            n = DeviceUtils.getScreenWidthInDPs(this.gridview.getContext());
        }
        else {
            n = DeviceUtils.getScreenWidthInDPs(this.gridview.getContext()) / 2.0f;
        }
        this.parallaxRatio = 76.0f / n;
    }
    
    private int getScrollY() {
        if (this.gridview == null) {
            return 0;
        }
        int n;
        if (this.gridview.getChildCount() > 0) {
            final View child = this.gridview.getChildAt(0);
            if (child.getTag(2131165256) != null) {
                if (this.seasonsSpinner != null && child.getTag(2131165257) != null) {
                    this.postSetSpinnerSelectionRunnable((int)child.getTag(2131165257));
                    n = 76;
                }
                else {
                    n = 76;
                }
            }
            else {
                n = (int)(-child.getTop() * this.parallaxRatio);
                this.adjustForParallax(this.parallaxView, n);
            }
        }
        else {
            n = 0;
        }
        return Math.max(0, Math.min(n, 76));
    }
    
    private void postSetSpinnerSelectionRunnable(final int n) {
        if (this.seasonsSpinner == null) {
            return;
        }
        this.seasonsSpinner.post((Runnable)new DetailsPageParallaxScrollListener$2(this, n));
    }
    
    private void setToolbarColor() {
        final int color = this.gridview.getResources().getColor(2131296363);
        final int[] array = { color, 0 };
        final GradientDrawable backgroundDrawable = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, array);
        final int scrollY = this.getScrollY();
        final int n = color + (scrollY << 24);
        final int n2 = (scrollY << 24) + 0;
        if (Log.isLoggable("detailsScroller", 3)) {
            Log.d("detailsScroller", "actionBar top colour: " + Integer.toHexString(n));
            Log.d("detailsScroller", "actionBar bottom colour: " + Integer.toHexString(n2));
        }
        array[0] = n;
        array[1] = n2;
        ((NetflixActivity)this.gridview.getContext()).getSupportActionBar().setBackgroundDrawable((Drawable)backgroundDrawable);
    }
    
    private void setTrackerViewPos() {
        int n2;
        final int n = n2 = 0;
        if (this.gridview.getChildCount() > 0) {
            final View child = this.gridview.getChildAt(0);
            n2 = n;
            if (child.getTag(2131165256) == null) {
                n2 = n;
                if (child.getBottom() >= this.trackerViewlatchYPosition) {
                    n2 = child.getBottom() - this.trackingView.getMeasuredHeight();
                }
            }
        }
        this.trackingView.setTranslationY((float)n2);
        if (n2 != this.trackerViewlatchYPosition) {
            this.animationFadeIn.cancel();
            this.trackingView.clearAnimation();
            this.trackingView.setTranslationX(0.0f);
            ((NetflixActivity)this.gridview.getContext()).getNetflixActionBar().bringToFront();
            return;
        }
        if (this.trackingView.getTranslationX() == this.trackerViewfinalXPosition) {
            return;
        }
        this.trackingView.startAnimation(this.animationFadeIn);
    }
    
    private void setTrackerViewPositions(final AbsListView absListView) {
        this.trackerViewlatchYPosition = 0;
        this.trackerViewfinalXPosition = (int)absListView.getResources().getDimension(2131361998);
    }
    
    private void setupTrackerViewAnimation() {
        (this.animationFadeIn = (Animation)new AlphaAnimation(0.0f, 1.0f)).setFillAfter(true);
        this.animationFadeIn.setDuration(300L);
        this.animationFadeIn.setAnimationListener((Animation$AnimationListener)new DetailsPageParallaxScrollListener$1(this));
    }
    
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        this.setToolbarColor();
        if (this.trackingView != null) {
            this.setTrackerViewPos();
        }
    }
    
    public void onScrollStateChanged(final AbsListView absListView, final int n) {
    }
}