// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;

class ViewCompat$KitKatViewCompatImpl extends ViewCompat$JbMr2ViewCompatImpl
{
    @Override
    public int getAccessibilityLiveRegion(final View view) {
        return ViewCompatKitKat.getAccessibilityLiveRegion(view);
    }
    
    @Override
    public boolean isAttachedToWindow(final View view) {
        return ViewCompatKitKat.isAttachedToWindow(view);
    }
    
    @Override
    public boolean isLaidOut(final View view) {
        return ViewCompatKitKat.isLaidOut(view);
    }
    
    @Override
    public boolean isLayoutDirectionResolved(final View view) {
        return ViewCompatKitKat.isLayoutDirectionResolved(view);
    }
    
    @Override
    public void setAccessibilityLiveRegion(final View view, final int n) {
        ViewCompatKitKat.setAccessibilityLiveRegion(view, n);
    }
    
    @Override
    public void setImportantForAccessibility(final View view, final int n) {
        ViewCompatJB.setImportantForAccessibility(view, n);
    }
}
