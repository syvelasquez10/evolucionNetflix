// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewParent;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;

class ViewCompat$JBViewCompatImpl extends ViewCompat$ICSMr1ViewCompatImpl
{
    @Override
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View view) {
        final Object accessibilityNodeProvider = ViewCompatJB.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
        }
        return null;
    }
    
    @Override
    public boolean getFitsSystemWindows(final View view) {
        return ViewCompatJB.getFitsSystemWindows(view);
    }
    
    @Override
    public int getImportantForAccessibility(final View view) {
        return ViewCompatJB.getImportantForAccessibility(view);
    }
    
    @Override
    public int getMinimumHeight(final View view) {
        return ViewCompatJB.getMinimumHeight(view);
    }
    
    @Override
    public int getMinimumWidth(final View view) {
        return ViewCompatJB.getMinimumWidth(view);
    }
    
    @Override
    public ViewParent getParentForAccessibility(final View view) {
        return ViewCompatJB.getParentForAccessibility(view);
    }
    
    @Override
    public boolean hasOverlappingRendering(final View view) {
        return ViewCompatJB.hasOverlappingRendering(view);
    }
    
    @Override
    public boolean hasTransientState(final View view) {
        return ViewCompatJB.hasTransientState(view);
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        return ViewCompatJB.performAccessibilityAction(view, n, bundle);
    }
    
    @Override
    public void postInvalidateOnAnimation(final View view) {
        ViewCompatJB.postInvalidateOnAnimation(view);
    }
    
    @Override
    public void postInvalidateOnAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        ViewCompatJB.postInvalidateOnAnimation(view, n, n2, n3, n4);
    }
    
    @Override
    public void postOnAnimation(final View view, final Runnable runnable) {
        ViewCompatJB.postOnAnimation(view, runnable);
    }
    
    @Override
    public void postOnAnimationDelayed(final View view, final Runnable runnable, final long n) {
        ViewCompatJB.postOnAnimationDelayed(view, runnable, n);
    }
    
    @Override
    public void requestApplyInsets(final View view) {
        ViewCompatJB.requestApplyInsets(view);
    }
    
    @Override
    public void setBackground(final View view, final Drawable drawable) {
        ViewCompatJB.setBackground(view, drawable);
    }
    
    @Override
    public void setHasTransientState(final View view, final boolean b) {
        ViewCompatJB.setHasTransientState(view, b);
    }
    
    @Override
    public void setImportantForAccessibility(final View view, final int n) {
        int n2 = n;
        if (n == 4) {
            n2 = 2;
        }
        ViewCompatJB.setImportantForAccessibility(view, n2);
    }
}
