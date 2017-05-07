// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.view.ViewParent;
import android.view.View;
import java.util.WeakHashMap;

class ViewCompat$BaseViewCompatImpl implements ViewCompat$ViewCompatImpl
{
    WeakHashMap<View, ViewPropertyAnimatorCompat> mViewPropertyAnimatorCompatMap;
    
    ViewCompat$BaseViewCompatImpl() {
        this.mViewPropertyAnimatorCompatMap = null;
    }
    
    @Override
    public ViewPropertyAnimatorCompat animate(final View view) {
        return new ViewPropertyAnimatorCompat(view);
    }
    
    @Override
    public boolean canScrollHorizontally(final View view, final int n) {
        return false;
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
    public int getLayoutDirection(final View view) {
        return 0;
    }
    
    @Override
    public int getMeasuredState(final View view) {
        return 0;
    }
    
    @Override
    public int getMinimumHeight(final View view) {
        return 0;
    }
    
    @Override
    public int getOverScrollMode(final View view) {
        return 2;
    }
    
    @Override
    public ViewParent getParentForAccessibility(final View view) {
        return view.getParent();
    }
    
    @Override
    public float getTranslationY(final View view) {
        return 0.0f;
    }
    
    @Override
    public int getWindowSystemUiVisibility(final View view) {
        return 0;
    }
    
    @Override
    public void jumpDrawablesToCurrentState(final View view) {
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
    public void setAlpha(final View view, final float n) {
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
    public void setScaleY(final View view, final float n) {
    }
    
    @Override
    public void setTranslationX(final View view, final float n) {
    }
    
    @Override
    public void setTranslationY(final View view, final float n) {
    }
}
