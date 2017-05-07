// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.internal.view.menu.ActionMenuView;
import android.support.v7.internal.view.menu.ActionMenuPresenter;
import android.view.ViewGroup;

abstract class AbsActionBarView extends ViewGroup
{
    private static final int FADE_DURATION = 200;
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    protected ActionMenuView mMenuView;
    protected boolean mSplitActionBar;
    protected ActionBarContainer mSplitView;
    protected boolean mSplitWhenNarrow;
    
    AbsActionBarView(final Context context) {
        super(context);
    }
    
    AbsActionBarView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    AbsActionBarView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public void animateToVisibility(final int n) {
        this.clearAnimation();
        if (n != this.getVisibility()) {
            final Context context = this.getContext();
            int n2;
            if (n == 0) {
                n2 = R.anim.abc_fade_in;
            }
            else {
                n2 = R.anim.abc_fade_out;
            }
            final Animation loadAnimation = AnimationUtils.loadAnimation(context, n2);
            this.startAnimation(loadAnimation);
            this.setVisibility(n);
            if (this.mSplitView != null && this.mMenuView != null) {
                this.mMenuView.startAnimation(loadAnimation);
                this.mMenuView.setVisibility(n);
            }
        }
    }
    
    public void dismissPopupMenus() {
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
    }
    
    public int getAnimatedVisibility() {
        return this.getVisibility();
    }
    
    public int getContentHeight() {
        return this.mContentHeight;
    }
    
    public boolean hideOverflowMenu() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.hideOverflowMenu();
    }
    
    public boolean isOverflowMenuShowing() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowMenuShowing();
    }
    
    public boolean isOverflowReserved() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowReserved();
    }
    
    protected int measureChildView(final View view, final int n, final int n2, final int n3) {
        view.measure(View$MeasureSpec.makeMeasureSpec(n, Integer.MIN_VALUE), n2);
        return Math.max(0, n - view.getMeasuredWidth() - n3);
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        if (Build$VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes((AttributeSet)null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        this.setContentHeight(obtainStyledAttributes.getLayoutDimension(1, 0));
        obtainStyledAttributes.recycle();
        if (this.mSplitWhenNarrow) {
            this.setSplitActionBar(this.getContext().getResources().getBoolean(R.bool.abc_split_action_bar_is_narrow));
        }
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.onConfigurationChanged(configuration);
        }
    }
    
    protected int positionChild(final View view, final int n, int n2, final int n3) {
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        n2 += (n3 - measuredHeight) / 2;
        view.layout(n, n2, n + measuredWidth, n2 + measuredHeight);
        return measuredWidth;
    }
    
    protected int positionChildInverse(final View view, final int n, int n2, final int n3) {
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        n2 += (n3 - measuredHeight) / 2;
        view.layout(n - measuredWidth, n2, n, n2 + measuredHeight);
        return measuredWidth;
    }
    
    public void postShowOverflowMenu() {
        this.post((Runnable)new Runnable() {
            @Override
            public void run() {
                AbsActionBarView.this.showOverflowMenu();
            }
        });
    }
    
    public void setContentHeight(final int mContentHeight) {
        this.mContentHeight = mContentHeight;
        this.requestLayout();
    }
    
    public void setSplitActionBar(final boolean mSplitActionBar) {
        this.mSplitActionBar = mSplitActionBar;
    }
    
    public void setSplitView(final ActionBarContainer mSplitView) {
        this.mSplitView = mSplitView;
    }
    
    public void setSplitWhenNarrow(final boolean mSplitWhenNarrow) {
        this.mSplitWhenNarrow = mSplitWhenNarrow;
    }
    
    public void setVisibility(final int visibility) {
        if (visibility != this.getVisibility()) {
            super.setVisibility(visibility);
        }
    }
    
    public boolean showOverflowMenu() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.showOverflowMenu();
    }
}
