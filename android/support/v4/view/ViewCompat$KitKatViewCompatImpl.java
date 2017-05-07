// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;

class ViewCompat$KitKatViewCompatImpl extends ViewCompat$JbMr1ViewCompatImpl
{
    @Override
    public int getAccessibilityLiveRegion(final View view) {
        return ViewCompatKitKat.getAccessibilityLiveRegion(view);
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
