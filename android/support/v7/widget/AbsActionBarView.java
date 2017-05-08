// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.content.res.Configuration;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.util.AttributeSet;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.content.Context;
import android.view.ViewGroup;

abstract class AbsActionBarView extends ViewGroup
{
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    protected ActionMenuView mMenuView;
    protected final Context mPopupContext;
    protected final AbsActionBarView$VisibilityAnimListener mVisAnimListener;
    protected ViewPropertyAnimatorCompat mVisibilityAnim;
    
    AbsActionBarView(final Context context) {
        this(context, null);
    }
    
    AbsActionBarView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    AbsActionBarView(final Context mPopupContext, final AttributeSet set, final int n) {
        super(mPopupContext, set, n);
        this.mVisAnimListener = new AbsActionBarView$VisibilityAnimListener(this);
        final TypedValue typedValue = new TypedValue();
        if (mPopupContext.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) && typedValue.resourceId != 0) {
            this.mPopupContext = (Context)new ContextThemeWrapper(mPopupContext, typedValue.resourceId);
            return;
        }
        this.mPopupContext = mPopupContext;
    }
    
    protected static int next(final int n, final int n2, final boolean b) {
        if (b) {
            return n - n2;
        }
        return n + n2;
    }
    
    public int getAnimatedVisibility() {
        if (this.mVisibilityAnim != null) {
            return this.mVisAnimListener.mFinalVisibility;
        }
        return this.getVisibility();
    }
    
    public int getContentHeight() {
        return this.mContentHeight;
    }
    
    protected int measureChildView(final View view, final int n, final int n2, final int n3) {
        view.measure(View$MeasureSpec.makeMeasureSpec(n, Integer.MIN_VALUE), n2);
        return Math.max(0, n - view.getMeasuredWidth() - n3);
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes((AttributeSet)null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        this.setContentHeight(obtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.onConfigurationChanged(configuration);
        }
    }
    
    public boolean onHoverEvent(final MotionEvent motionEvent) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            final boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            final boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.mEatingTouch = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }
    
    protected int positionChild(final View view, int n, int n2, final int n3, final boolean b) {
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        n2 += (n3 - measuredHeight) / 2;
        if (b) {
            view.layout(n - measuredWidth, n2, n, measuredHeight + n2);
        }
        else {
            view.layout(n, n2, n + measuredWidth, measuredHeight + n2);
        }
        n = measuredWidth;
        if (b) {
            n = -measuredWidth;
        }
        return n;
    }
    
    public void setContentHeight(final int mContentHeight) {
        this.mContentHeight = mContentHeight;
        this.requestLayout();
    }
    
    public void setVisibility(final int visibility) {
        if (visibility != this.getVisibility()) {
            if (this.mVisibilityAnim != null) {
                this.mVisibilityAnim.cancel();
            }
            super.setVisibility(visibility);
        }
    }
    
    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(final int n, final long n2) {
        if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.cancel();
        }
        if (n == 0) {
            if (this.getVisibility() != 0) {
                ViewCompat.setAlpha((View)this, 0.0f);
            }
            final ViewPropertyAnimatorCompat alpha = ViewCompat.animate((View)this).alpha(1.0f);
            alpha.setDuration(n2);
            alpha.setListener(this.mVisAnimListener.withFinalVisibility(alpha, n));
            return alpha;
        }
        final ViewPropertyAnimatorCompat alpha2 = ViewCompat.animate((View)this).alpha(0.0f);
        alpha2.setDuration(n2);
        alpha2.setListener(this.mVisAnimListener.withFinalVisibility(alpha2, n));
        return alpha2;
    }
    
    public boolean showOverflowMenu() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.showOverflowMenu();
    }
}
