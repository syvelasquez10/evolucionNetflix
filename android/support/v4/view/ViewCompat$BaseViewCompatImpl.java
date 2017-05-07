// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.view.ViewParent;
import android.view.View;
import java.util.WeakHashMap;

class ViewCompat$BaseViewCompatImpl implements ViewCompat$ViewCompatImpl
{
    WeakHashMap<View, ViewPropertyAnimatorCompat> mViewPropertyAnimatorCompatMap;
    
    ViewCompat$BaseViewCompatImpl() {
        this.mViewPropertyAnimatorCompatMap = null;
    }
    
    private boolean canScrollingViewScrollHorizontally(final ScrollingView scrollingView, final int n) {
        boolean b = true;
        final int computeHorizontalScrollOffset = scrollingView.computeHorizontalScrollOffset();
        final int n2 = scrollingView.computeHorizontalScrollRange() - scrollingView.computeHorizontalScrollExtent();
        if (n2 == 0) {
            b = false;
        }
        else if (n < 0) {
            if (computeHorizontalScrollOffset <= 0) {
                return false;
            }
        }
        else if (computeHorizontalScrollOffset >= n2 - 1) {
            return false;
        }
        return b;
    }
    
    private boolean canScrollingViewScrollVertically(final ScrollingView scrollingView, final int n) {
        boolean b = true;
        final int computeVerticalScrollOffset = scrollingView.computeVerticalScrollOffset();
        final int n2 = scrollingView.computeVerticalScrollRange() - scrollingView.computeVerticalScrollExtent();
        if (n2 == 0) {
            b = false;
        }
        else if (n < 0) {
            if (computeVerticalScrollOffset <= 0) {
                return false;
            }
        }
        else if (computeVerticalScrollOffset >= n2 - 1) {
            return false;
        }
        return b;
    }
    
    @Override
    public ViewPropertyAnimatorCompat animate(final View view) {
        return new ViewPropertyAnimatorCompat(view);
    }
    
    @Override
    public boolean canScrollHorizontally(final View view, final int n) {
        return view instanceof ScrollingView && this.canScrollingViewScrollHorizontally((ScrollingView)view, n);
    }
    
    @Override
    public boolean canScrollVertically(final View view, final int n) {
        return view instanceof ScrollingView && this.canScrollingViewScrollVertically((ScrollingView)view, n);
    }
    
    @Override
    public int combineMeasuredStates(final int n, final int n2) {
        return n | n2;
    }
    
    @Override
    public WindowInsetsCompat dispatchApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return windowInsetsCompat;
    }
    
    @Override
    public float getAlpha(final View view) {
        return 1.0f;
    }
    
    @Override
    public float getElevation(final View view) {
        return 0.0f;
    }
    
    @Override
    public boolean getFitsSystemWindows(final View view) {
        return false;
    }
    
    long getFrameTime() {
        return 10L;
    }
    
    @Override
    public int getImportantForAccessibility(final View view) {
        return 0;
    }
    
    @Override
    public int getLayerType(final View view) {
        return 0;
    }
    
    @Override
    public int getLayoutDirection(final View view) {
        return 0;
    }
    
    @Override
    public int getMeasuredState(final View view) {
        return 0;
    }
    
    @Override
    public int getMinimumHeight(final View view) {
        return ViewCompatBase.getMinimumHeight(view);
    }
    
    @Override
    public int getMinimumWidth(final View view) {
        return ViewCompatBase.getMinimumWidth(view);
    }
    
    @Override
    public int getOverScrollMode(final View view) {
        return 2;
    }
    
    @Override
    public int getPaddingEnd(final View view) {
        return view.getPaddingRight();
    }
    
    @Override
    public int getPaddingStart(final View view) {
        return view.getPaddingLeft();
    }
    
    @Override
    public ViewParent getParentForAccessibility(final View view) {
        return view.getParent();
    }
    
    @Override
    public float getTranslationX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getTranslationY(final View view) {
        return 0.0f;
    }
    
    public float getTranslationZ(final View view) {
        return 0.0f;
    }
    
    @Override
    public int getWindowSystemUiVisibility(final View view) {
        return 0;
    }
    
    @Override
    public float getZ(final View view) {
        return this.getTranslationZ(view) + this.getElevation(view);
    }
    
    @Override
    public boolean hasAccessibilityDelegate(final View view) {
        return false;
    }
    
    @Override
    public boolean hasOverlappingRendering(final View view) {
        return true;
    }
    
    @Override
    public boolean hasTransientState(final View view) {
        return false;
    }
    
    @Override
    public boolean isAttachedToWindow(final View view) {
        return ViewCompatBase.isAttachedToWindow(view);
    }
    
    @Override
    public boolean isLaidOut(final View view) {
        return ViewCompatBase.isLaidOut(view);
    }
    
    @Override
    public boolean isPaddingRelative(final View view) {
        return false;
    }
    
    @Override
    public void jumpDrawablesToCurrentState(final View view) {
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return windowInsetsCompat;
    }
    
    @Override
    public void postInvalidateOnAnimation(final View view) {
        view.invalidate();
    }
    
    @Override
    public void postOnAnimation(final View view, final Runnable runnable) {
        view.postDelayed(runnable, this.getFrameTime());
    }
    
    @Override
    public void postOnAnimationDelayed(final View view, final Runnable runnable, final long n) {
        view.postDelayed(runnable, this.getFrameTime() + n);
    }
    
    @Override
    public void requestApplyInsets(final View view) {
    }
    
    @Override
    public int resolveSizeAndState(final int n, final int n2, final int n3) {
        return View.resolveSize(n, n2);
    }
    
    @Override
    public void setAccessibilityDelegate(final View view, final AccessibilityDelegateCompat accessibilityDelegateCompat) {
    }
    
    @Override
    public void setActivated(final View view, final boolean b) {
    }
    
    @Override
    public void setAlpha(final View view, final float n) {
    }
    
    @Override
    public void setBackgroundTintList(final View view, final ColorStateList list) {
        ViewCompatBase.setBackgroundTintList(view, list);
    }
    
    @Override
    public void setBackgroundTintMode(final View view, final PorterDuff$Mode porterDuff$Mode) {
        ViewCompatBase.setBackgroundTintMode(view, porterDuff$Mode);
    }
    
    @Override
    public void setElevation(final View view, final float n) {
    }
    
    @Override
    public void setImportantForAccessibility(final View view, final int n) {
    }
    
    @Override
    public void setLayerType(final View view, final int n, final Paint paint) {
    }
    
    @Override
    public void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
    }
    
    @Override
    public void setPaddingRelative(final View view, final int n, final int n2, final int n3, final int n4) {
        view.setPadding(n, n2, n3, n4);
    }
    
    @Override
    public void setSaveFromParentEnabled(final View view, final boolean b) {
    }
    
    @Override
    public void setTranslationX(final View view, final float n) {
    }
    
    @Override
    public void setTranslationY(final View view, final float n) {
    }
}
