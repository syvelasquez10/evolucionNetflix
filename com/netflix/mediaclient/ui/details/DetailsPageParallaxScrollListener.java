// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.widget.Toolbar$LayoutParams;
import java.util.Date;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;
import android.support.v7.widget.RecyclerView$OnScrollListener;

public class DetailsPageParallaxScrollListener extends RecyclerView$OnScrollListener
{
    static final int ACTION_BAR_GRADIENT_RANGE = 76;
    static final int OPAQUE = 255;
    static final float PARALLAX_EFFECT_PERCENT = 40.0f;
    private static final String TAG = "DetailsPageParallaxScrollListener";
    private static final int TRACKER_VIEW_FADE_INTO_ACTIONBAR_DURATION_FASTSCROLL = 100;
    private static final int TRACKER_VIEW_FADE_INTO_ACTIONBAR_FADEIN_DURATION = 300;
    private static final int TRACKER_VIEW_FADE_INTO_ACTIONBAR_FADEOUT_DURATION = 300;
    static final int TRANSPARENT = 0;
    private float actionBarFadeRatio;
    private int actionBarPosition;
    private View anchorView;
    private boolean animating;
    private boolean applyToolBarGradientTransform;
    private long currentVelocity;
    private int fadeOutDuration;
    protected int initialBottomColor;
    protected int initialTopColor;
    private boolean islatched;
    float lastParallaxPosition;
    private long lastTime;
    private int latchPosition;
    private ViewGroup originalTrackingViewParent;
    private final View[] parallaxViews;
    protected final RecyclerView recyclerView;
    private DetailsPageParallaxScrollListener$IScrollStateChanged scrollStateChanged;
    private final SeasonsSpinner seasonsSpinner;
    protected final View trackingView;
    
    public DetailsPageParallaxScrollListener(final SeasonsSpinner seasonsSpinner, final RecyclerView recyclerView, final View[] parallaxViews, final View trackingView, final int initialTopColor, final int initialBottomColor, final View anchorView) {
        this.applyToolBarGradientTransform = true;
        this.seasonsSpinner = seasonsSpinner;
        this.parallaxViews = parallaxViews;
        this.trackingView = trackingView;
        this.anchorView = anchorView;
        this.recyclerView = recyclerView;
        this.initialTopColor = initialTopColor;
        this.initialBottomColor = initialBottomColor;
        if (trackingView != null) {
            this.originalTrackingViewParent = (ViewGroup)trackingView.getParent();
        }
        this.init();
    }
    
    private void adjustForParallax(final View view, final int n) {
        if (AndroidUtils.getAndroidVersion() >= 16) {
            final float n2 = -n + n * 0.4f;
            if (view != null && n2 != this.lastParallaxPosition) {
                view.setTranslationY(n2);
                this.lastParallaxPosition = n2;
            }
        }
    }
    
    private void animateTrackingView(final DetailsPageParallaxScrollListener$ItrackingViewAnimationActions detailsPageParallaxScrollListener$ItrackingViewAnimationActions) {
        this.fadeOutDuration = this.getTrackerViewLatchFadeoutDuration();
        if (this.currentVelocity == 0L) {
            this.fadeOutDuration = 100;
        }
        this.trackingView.animate().alpha(0.0f).setDuration((long)this.fadeOutDuration).setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator()).setListener((Animator$AnimatorListener)new DetailsPageParallaxScrollListener$3(this, detailsPageParallaxScrollListener$ItrackingViewAnimationActions));
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
    
    private void detachTrackingViewFromOriginalParent() {
        if (this.trackingView != null) {
            final Toolbar toolbar = ((NetflixActivity)this.recyclerView.getContext()).getNetflixActionBar().getToolbar();
            final ViewGroup viewGroup = (ViewGroup)this.trackingView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.trackingView);
                toolbar.addView(this.trackingView, (ViewGroup$LayoutParams)new Toolbar$LayoutParams(-2, -2, 48));
                this.islatched = true;
                this.trackingView.setTranslationX((float)this.getTrackerViewFinalXPosition());
            }
        }
    }
    
    private void getActionBarPosition() {
        if (this.recyclerView == null) {
            return;
        }
        final NetflixActionBar netflixActionBar = ((NetflixActivity)this.recyclerView.getContext()).getNetflixActionBar();
        final int[] array = new int[2];
        netflixActionBar.getToolbar().getLocationOnScreen(array);
        this.actionBarPosition = array[1];
    }
    
    private int getScrollY() {
        int max = 0;
        if (this.recyclerView != null) {
            int n;
            if (this.recyclerView.getChildCount() > 0) {
                final View child = this.recyclerView.getChildAt(0);
                if (!((RecyclerViewHeaderAdapter)this.recyclerView.getAdapter()).isViewHeader(child, this.recyclerView)) {
                    if (this.seasonsSpinner != null && child.getTag(2131623951) != null) {
                        this.postSetSpinnerSelectionRunnable((int)child.getTag(2131623951));
                    }
                    this.onItemsShown();
                    n = 76;
                }
                else {
                    final int n2 = (int)(-child.getTop() * this.actionBarFadeRatio);
                    final View[] parallaxViews = this.parallaxViews;
                    for (int length = parallaxViews.length, i = 0; i < length; ++i) {
                        final View view = parallaxViews[i];
                        if (view != null) {
                            this.adjustForParallax(view, child.getTop());
                        }
                    }
                    this.onHeaderShown();
                    n = n2;
                }
            }
            else {
                n = 0;
            }
            final int n3 = max = Math.max(0, Math.min(n, 76));
            if (Log.isLoggable()) {
                Log.v("DetailsPageParallaxScrollListener", "scrollY: " + n3);
                return n3;
            }
        }
        return max;
    }
    
    private void init() {
        this.setLatchPosition();
        this.getActionBarPosition();
        this.calculateActionBarFadeRatio();
        this.setInitialToolbarColor();
    }
    
    private void postSetSpinnerSelectionRunnable(final int n) {
        if (this.seasonsSpinner == null) {
            return;
        }
        this.seasonsSpinner.post((Runnable)new DetailsPageParallaxScrollListener$4(this, n));
    }
    
    private void reAttachTrackingViewOriginalParent() {
        if (this.trackingView != null && this.originalTrackingViewParent != null) {
            final ViewGroup viewGroup = (ViewGroup)this.trackingView.getParent();
            if (viewGroup != null && viewGroup != this.originalTrackingViewParent) {
                viewGroup.removeView(this.trackingView);
                this.originalTrackingViewParent.addView(this.trackingView, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 16));
                this.trackingView.setTranslationY(0.0f);
                this.trackingView.setTranslationX(0.0f);
                this.islatched = false;
            }
        }
    }
    
    private void setLatchPosition() {
        this.latchPosition = this.getLatchPosition();
    }
    
    private void setTrackerViewPos() {
        if (this.trackingView != null && this.anchorView != null) {
            final int[] array = new int[2];
            this.anchorView.getLocationOnScreen(array);
            if (array[1] < this.latchPosition) {
                this.setTrackingViewLatchedPosition();
                this.onTrackingViewLatched();
                return;
            }
            this.setTrackingViewUnlatchedPosition(array[1]);
            this.onTrackingViewUnlatched();
        }
    }
    
    protected int getLatchPosition() {
        if (this.trackingView != null) {
            return ((NetflixActivity)this.trackingView.getContext()).getActionBarHeight() + this.actionBarPosition;
        }
        return 0;
    }
    
    protected int getTrackerViewFinalXPosition() {
        return 0;
    }
    
    protected int getTrackerViewFinalYPosition() {
        if (this.trackingView == null) {
            return 0;
        }
        return this.actionBarPosition + ((NetflixActivity)this.trackingView.getContext()).getActionBarHeight() / 2 - ((NetflixActivity)this.trackingView.getContext()).getActionBarHeight() / 2;
    }
    
    protected int getTrackerViewLatchFadeinDuration() {
        return 300;
    }
    
    protected int getTrackerViewLatchFadeoutDuration() {
        return 300;
    }
    
    protected void onHeaderShown() {
    }
    
    protected void onItemsShown() {
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
        this.calculateScrollVelocity(n2);
        if (this.applyToolBarGradientTransform) {
            this.setToolbarColor();
        }
        this.setListenerScrollState();
        this.setTrackerViewPos();
    }
    
    protected void onTrackingViewLatched() {
    }
    
    protected void onTrackingViewUnlatched() {
    }
    
    public void resetDynamicViewsYPosition() {
        int i = 0;
        if (this.recyclerView.getChildCount() < 1 || this.parallaxViews.length < 1) {
            return;
        }
        Log.v("DetailsPageParallaxScrollListener", "Resetting dynamic views' Y-position");
        final View child = this.recyclerView.getChildAt(0);
        for (View[] parallaxViews = this.parallaxViews; i < parallaxViews.length; ++i) {
            final View view = parallaxViews[i];
            if (view != null) {
                this.adjustForParallax(view, child.getTop());
            }
        }
        this.setTrackerViewPos();
    }
    
    public void setAnchor(final View anchorView) {
        this.anchorView = anchorView;
    }
    
    public void setApplyToolBarGradientTransform(final boolean applyToolBarGradientTransform) {
        this.applyToolBarGradientTransform = applyToolBarGradientTransform;
    }
    
    public void setInitialBottomColor(final int initialBottomColor) {
        this.initialBottomColor = initialBottomColor;
        this.setToolbarColor();
    }
    
    protected void setInitialToolbarColor() {
        this.setToolbarColor();
        this.setListenerScrollState();
    }
    
    public void setInitialTopColor(final int initialTopColor) {
        this.initialTopColor = initialTopColor;
        this.setToolbarColor();
    }
    
    protected void setListenerScrollState() {
        if (this.getScrollY() != 0) {
            if (this.scrollStateChanged != null) {
                this.scrollStateChanged.onScrollStart();
            }
        }
        else if (this.scrollStateChanged != null) {
            this.scrollStateChanged.onScrollMinReached();
        }
    }
    
    public void setOnScrollStateChangedListener(final DetailsPageParallaxScrollListener$IScrollStateChanged scrollStateChanged) {
        this.scrollStateChanged = scrollStateChanged;
    }
    
    protected void setToolbarColor() {
        final int initialTopColor = this.initialTopColor;
        final int initialBottomColor = this.initialBottomColor;
        final int[] array = { initialTopColor, initialBottomColor };
        final GradientDrawable backgroundDrawable = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, array);
        final int scrollY = this.getScrollY();
        array[0] = initialTopColor + (scrollY << 24);
        array[1] = initialBottomColor + (scrollY << 24);
        ((NetflixActivity)this.recyclerView.getContext()).getSupportActionBar().setBackgroundDrawable((Drawable)backgroundDrawable);
    }
    
    protected void setTrackingViewLatchedPosition() {
        if (this.trackingView != null && !this.islatched && !this.animating) {
            this.animateTrackingView(new DetailsPageParallaxScrollListener$1(this));
        }
    }
    
    protected void setTrackingViewUnlatchedPosition(final int n) {
        if (this.trackingView != null && this.islatched && !this.animating) {
            this.animateTrackingView(new DetailsPageParallaxScrollListener$2(this));
        }
    }
}
