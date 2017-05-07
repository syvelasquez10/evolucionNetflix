// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.view.ViewGroup;
import java.util.Date;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.RecyclerView$OnScrollListener;

public final class DetailsPageParallaxScrollListener extends RecyclerView$OnScrollListener
{
    static final int ACTION_BAR_GRADIENT_RANGE = 76;
    static final int OPAQUE = 255;
    static final float PARALLAX_EFFECT_PERCENT = 40.0f;
    private static final String TAG = "detailsScroller";
    private static final int TRACKER_VIEW_FADE_INTO_ACTIONBAR_DURATION = 300;
    private static final int TRACKER_VIEW_FADE_INTO_ACTIONBAR_DURATION_FASTSCROLL = 100;
    static final int TRANSPARENT = 0;
    private float actionBarFadeRatio;
    private View anchorView;
    private boolean animating;
    private int currentScrollPos;
    private long currentVelocity;
    private int fadeOutDuration;
    private int initialBottomColor;
    private int initialTopColor;
    private long lastTime;
    private int latchPosition;
    private final View parallaxView;
    private final RecyclerView recyclerView;
    private DetailsPageParallaxScrollListener$IScrollStateChanged scrollStateChanged;
    private final SeasonsSpinner seasonsSpinner;
    private int trackerViewfinalXPosition;
    private final View trackingView;
    
    public DetailsPageParallaxScrollListener(final SeasonsSpinner seasonsSpinner, final RecyclerView recyclerView, final View parallaxView, final View trackingView, final int initialTopColor, final int initialBottomColor, final View anchorView) {
        this.seasonsSpinner = seasonsSpinner;
        this.parallaxView = parallaxView;
        this.trackingView = trackingView;
        this.anchorView = anchorView;
        this.recyclerView = recyclerView;
        this.initialTopColor = initialTopColor;
        this.initialBottomColor = initialBottomColor;
        this.init();
    }
    
    private void adjustForParallax(final View view, final int n) {
        if (AndroidUtils.getAndroidVersion() >= 16 && view != null) {
            view.setTranslationY(-n + n * 0.4f);
        }
    }
    
    private void calculateActionBarFadeRatio() {
        float n = ((NetflixActivity)this.recyclerView.getContext()).getActionBarHeight() * 2;
        if (DeviceUtils.isTabletByContext(this.recyclerView.getContext())) {
            n *= 2.0f;
        }
        this.actionBarFadeRatio = 76.0f / n;
    }
    
    private void calculateScrollVelocity(final int n) {
        final long time = new Date().getTime();
        this.currentVelocity = Math.abs((time - this.lastTime) / n);
        this.lastTime = time;
    }
    
    private void detachTrackingViewFromParent() {
        if (this.trackingView != null) {
            final ViewGroup viewGroup = (ViewGroup)this.trackingView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.trackingView);
                ((ViewGroup)this.recyclerView.getRootView()).addView(this.trackingView);
            }
        }
    }
    
    private int getScrollY() {
        if (this.recyclerView == null) {
            return 0;
        }
        int n;
        if (this.recyclerView.getChildCount() > 0) {
            final View child = this.recyclerView.getChildAt(0);
            if (!((RecyclerViewHeaderAdapter)this.recyclerView.getAdapter()).isViewHeader(child, this.recyclerView)) {
                if (this.seasonsSpinner != null && child.getTag(2131427349) != null) {
                    this.postSetSpinnerSelectionRunnable((int)child.getTag(2131427349));
                }
                if (this.scrollStateChanged != null) {
                    this.scrollStateChanged.onItemsShown();
                    n = 76;
                }
                else {
                    n = 76;
                }
            }
            else {
                final int n2 = (int)(-child.getTop() * this.actionBarFadeRatio);
                this.adjustForParallax(this.parallaxView, child.getTop());
                n = n2;
                if (this.scrollStateChanged != null) {
                    this.scrollStateChanged.onHeaderShown();
                    n = n2;
                }
            }
        }
        else {
            n = 0;
        }
        return Math.max(0, Math.min(n, 76));
    }
    
    private void init() {
        this.setLatchPosition();
        this.setTrackerViewFinalPositions();
        this.calculateActionBarFadeRatio();
        this.setToolbarColor();
    }
    
    private void postSetSpinnerSelectionRunnable(final int n) {
        if (this.seasonsSpinner == null) {
            return;
        }
        this.seasonsSpinner.post((Runnable)new DetailsPageParallaxScrollListener$2(this, n));
    }
    
    private void saveScrollPosition(final int n) {
        this.currentScrollPos += n;
    }
    
    private void setLatchPosition() {
        if (this.trackingView != null) {
            this.latchPosition = ((NetflixActivity)this.trackingView.getContext()).getActionBarHeight() + ViewUtils.getStatusBarHeight(this.trackingView.getContext());
        }
    }
    
    private void setToolbarColor() {
        final int initialTopColor = this.initialTopColor;
        final int initialBottomColor = this.initialBottomColor;
        final int[] array = { initialTopColor, initialBottomColor };
        final GradientDrawable backgroundDrawable = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, array);
        final int scrollY = this.getScrollY();
        this.setScrollState(scrollY);
        final int n = initialTopColor + (scrollY << 24);
        final int n2 = initialBottomColor + (scrollY << 24);
        if (Log.isLoggable("detailsScroller", 3)) {
            Log.d("detailsScroller", "actionBar top colour: " + Integer.toHexString(n));
            Log.d("detailsScroller", "actionBar bottom colour: " + Integer.toHexString(n2));
        }
        array[0] = n;
        array[1] = n2;
        ((NetflixActivity)this.recyclerView.getContext()).getSupportActionBar().setBackgroundDrawable((Drawable)backgroundDrawable);
    }
    
    private void setTrackerViewFinalPositions() {
        this.trackerViewfinalXPosition = (int)this.recyclerView.getResources().getDimension(2131296470);
    }
    
    private void setTrackerViewPos() {
        if (this.trackingView != null && this.anchorView != null) {
            this.detachTrackingViewFromParent();
            final int[] array = new int[2];
            this.anchorView.getLocationOnScreen(array);
            if (array[1] < this.latchPosition) {
                this.setTrackingViewLatchedPosition();
                return;
            }
            this.setTrackingViewUnlatchedPosition(array[1]);
        }
    }
    
    private void setTrackingViewLatchedPosition() {
        if (this.trackingView != null && this.trackingView.getTranslationX() != this.trackerViewfinalXPosition && !this.animating) {
            this.fadeOutDuration = 300;
            if (this.currentVelocity <= 10L) {
                this.fadeOutDuration = 100;
            }
            this.trackingView.animate().alpha(0.0f).setDuration((long)this.fadeOutDuration).setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator()).setListener((Animator$AnimatorListener)new DetailsPageParallaxScrollListener$1(this));
        }
    }
    
    private void setTrackingViewUnlatchedPosition(final int n) {
        if (this.trackingView == null) {
            return;
        }
        this.trackingView.clearAnimation();
        this.trackingView.setTranslationX(0.0f);
        this.trackingView.setTranslationY((float)n);
    }
    
    @Override
    public void onScrollStateChanged(final RecyclerView recyclerView, final int n) {
        super.onScrollStateChanged(recyclerView, n);
        if (this.scrollStateChanged != null) {
            switch (n) {
                case 0: {
                    this.scrollStateChanged.onScrollStop();
                }
            }
        }
    }
    
    @Override
    public void onScrolled(final RecyclerView recyclerView, final int n, final int n2) {
        super.onScrolled(recyclerView, n, n2);
        this.saveScrollPosition(n2);
        this.calculateScrollVelocity(n2);
        this.setToolbarColor();
        this.setTrackerViewPos();
    }
    
    public void setAnchor(final View anchorView) {
        this.anchorView = anchorView;
    }
    
    public void setInitialBottomColor(final int initialBottomColor) {
        this.initialBottomColor = initialBottomColor;
        this.setToolbarColor();
    }
    
    public void setInitialTopColor(final int initialTopColor) {
        this.initialTopColor = initialTopColor;
        this.setToolbarColor();
    }
    
    public void setOnScrollStateChangedListener(final DetailsPageParallaxScrollListener$IScrollStateChanged scrollStateChanged) {
        this.scrollStateChanged = scrollStateChanged;
    }
    
    protected void setScrollState(final int n) {
        if (this.scrollStateChanged != null) {
            if (n == 0) {
                this.scrollStateChanged.onScrollMinReached();
                return;
            }
            this.scrollStateChanged.onScrollStart();
        }
    }
}
