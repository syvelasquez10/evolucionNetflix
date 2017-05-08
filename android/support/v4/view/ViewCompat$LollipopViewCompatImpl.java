// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.view.View;

class ViewCompat$LollipopViewCompatImpl extends ViewCompat$KitKatViewCompatImpl
{
    @Override
    public WindowInsetsCompat dispatchApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return WindowInsetsCompat.wrap(ViewCompatLollipop.dispatchApplyWindowInsets(view, WindowInsetsCompat.unwrap(windowInsetsCompat)));
    }
    
    @Override
    public boolean dispatchNestedFling(final View view, final float n, final float n2, final boolean b) {
        return ViewCompatLollipop.dispatchNestedFling(view, n, n2, b);
    }
    
    @Override
    public boolean dispatchNestedPreFling(final View view, final float n, final float n2) {
        return ViewCompatLollipop.dispatchNestedPreFling(view, n, n2);
    }
    
    @Override
    public boolean dispatchNestedPreScroll(final View view, final int n, final int n2, final int[] array, final int[] array2) {
        return ViewCompatLollipop.dispatchNestedPreScroll(view, n, n2, array, array2);
    }
    
    @Override
    public boolean dispatchNestedScroll(final View view, final int n, final int n2, final int n3, final int n4, final int[] array) {
        return ViewCompatLollipop.dispatchNestedScroll(view, n, n2, n3, n4, array);
    }
    
    @Override
    public ColorStateList getBackgroundTintList(final View view) {
        return ViewCompatLollipop.getBackgroundTintList(view);
    }
    
    @Override
    public PorterDuff$Mode getBackgroundTintMode(final View view) {
        return ViewCompatLollipop.getBackgroundTintMode(view);
    }
    
    @Override
    public float getElevation(final View view) {
        return ViewCompatLollipop.getElevation(view);
    }
    
    @Override
    public String getTransitionName(final View view) {
        return ViewCompatLollipop.getTransitionName(view);
    }
    
    @Override
    public float getTranslationZ(final View view) {
        return ViewCompatLollipop.getTranslationZ(view);
    }
    
    @Override
    public float getZ(final View view) {
        return ViewCompatLollipop.getZ(view);
    }
    
    @Override
    public boolean hasNestedScrollingParent(final View view) {
        return ViewCompatLollipop.hasNestedScrollingParent(view);
    }
    
    @Override
    public boolean isImportantForAccessibility(final View view) {
        return ViewCompatLollipop.isImportantForAccessibility(view);
    }
    
    @Override
    public boolean isNestedScrollingEnabled(final View view) {
        return ViewCompatLollipop.isNestedScrollingEnabled(view);
    }
    
    @Override
    public void offsetLeftAndRight(final View view, final int n) {
        ViewCompatLollipop.offsetLeftAndRight(view, n);
    }
    
    @Override
    public void offsetTopAndBottom(final View view, final int n) {
        ViewCompatLollipop.offsetTopAndBottom(view, n);
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return WindowInsetsCompat.wrap(ViewCompatLollipop.onApplyWindowInsets(view, WindowInsetsCompat.unwrap(windowInsetsCompat)));
    }
    
    @Override
    public void requestApplyInsets(final View view) {
        ViewCompatLollipop.requestApplyInsets(view);
    }
    
    @Override
    public void setBackgroundTintList(final View view, final ColorStateList list) {
        ViewCompatLollipop.setBackgroundTintList(view, list);
    }
    
    @Override
    public void setBackgroundTintMode(final View view, final PorterDuff$Mode porterDuff$Mode) {
        ViewCompatLollipop.setBackgroundTintMode(view, porterDuff$Mode);
    }
    
    @Override
    public void setElevation(final View view, final float n) {
        ViewCompatLollipop.setElevation(view, n);
    }
    
    @Override
    public void setNestedScrollingEnabled(final View view, final boolean b) {
        ViewCompatLollipop.setNestedScrollingEnabled(view, b);
    }
    
    @Override
    public void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        if (onApplyWindowInsetsListener == null) {
            ViewCompatLollipop.setOnApplyWindowInsetsListener(view, null);
            return;
        }
        ViewCompatLollipop.setOnApplyWindowInsetsListener(view, new ViewCompat$LollipopViewCompatImpl$1(this, onApplyWindowInsetsListener));
    }
    
    @Override
    public void setTransitionName(final View view, final String s) {
        ViewCompatLollipop.setTransitionName(view, s);
    }
    
    @Override
    public void setTranslationZ(final View view, final float n) {
        ViewCompatLollipop.setTranslationZ(view, n);
    }
    
    @Override
    public void setZ(final View view, final float n) {
        ViewCompatLollipop.setZ(view, n);
    }
    
    @Override
    public boolean startNestedScroll(final View view, final int n) {
        return ViewCompatLollipop.startNestedScroll(view, n);
    }
    
    @Override
    public void stopNestedScroll(final View view) {
        ViewCompatLollipop.stopNestedScroll(view);
    }
}
