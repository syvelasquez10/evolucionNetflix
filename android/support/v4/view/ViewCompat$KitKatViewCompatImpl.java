// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;

class ViewCompat$KitKatViewCompatImpl extends ViewCompat$JbMr2ViewCompatImpl
{
    @Override
    public boolean isAttachedToWindow(final View view) {
        return ViewCompatKitKat.isAttachedToWindow(view);
    }
    
    @Override
    public boolean isLaidOut(final View view) {
        return ViewCompatKitKat.isLaidOut(view);
    }
    
    @Override
    public void setImportantForAccessibility(final View view, final int n) {
        ViewCompatJB.setImportantForAccessibility(view, n);
    }
}
