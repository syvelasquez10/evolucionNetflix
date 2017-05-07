// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.view.ViewParent;
import android.view.View;

interface ViewCompat$ViewCompatImpl
{
    ViewPropertyAnimatorCompat animate(final View p0);
    
    boolean canScrollHorizontally(final View p0, final int p1);
    
    boolean canScrollVertically(final View p0, final int p1);
    
    float getAlpha(final View p0);
    
    boolean getFitsSystemWindows(final View p0);
    
    int getImportantForAccessibility(final View p0);
    
    int getLayoutDirection(final View p0);
    
    int getMeasuredState(final View p0);
    
    int getMinimumHeight(final View p0);
    
    int getMinimumWidth(final View p0);
    
    int getOverScrollMode(final View p0);
    
    int getPaddingEnd(final View p0);
    
    int getPaddingStart(final View p0);
    
    ViewParent getParentForAccessibility(final View p0);
    
    float getTranslationX(final View p0);
    
    float getTranslationY(final View p0);
    
    int getWindowSystemUiVisibility(final View p0);
    
    boolean hasAccessibilityDelegate(final View p0);
    
    boolean hasTransientState(final View p0);
    
    void jumpDrawablesToCurrentState(final View p0);
    
    void postInvalidateOnAnimation(final View p0);
    
    void postOnAnimation(final View p0, final Runnable p1);
    
    void postOnAnimationDelayed(final View p0, final Runnable p1, final long p2);
    
    void requestApplyInsets(final View p0);
    
    int resolveSizeAndState(final int p0, final int p1, final int p2);
    
    void setAccessibilityDelegate(final View p0, final AccessibilityDelegateCompat p1);
    
    void setAlpha(final View p0, final float p1);
    
    void setElevation(final View p0, final float p1);
    
    void setImportantForAccessibility(final View p0, final int p1);
    
    void setLayerType(final View p0, final int p1, final Paint p2);
    
    void setOnApplyWindowInsetsListener(final View p0, final OnApplyWindowInsetsListener p1);
    
    void setScaleY(final View p0, final float p1);
    
    void setTranslationX(final View p0, final float p1);
    
    void setTranslationY(final View p0, final float p1);
}
