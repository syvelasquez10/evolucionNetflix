// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import java.util.WeakHashMap;
import android.view.View;

class ViewCompat$ICSViewCompatImpl extends ViewCompat$HCViewCompatImpl
{
    static boolean accessibilityDelegateCheckFailed;
    
    static {
        ViewCompat$ICSViewCompatImpl.accessibilityDelegateCheckFailed = false;
    }
    
    @Override
    public ViewPropertyAnimatorCompat animate(final View view) {
        if (this.mViewPropertyAnimatorCompatMap == null) {
            this.mViewPropertyAnimatorCompatMap = new WeakHashMap<View, ViewPropertyAnimatorCompat>();
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
        if ((viewPropertyAnimatorCompat = this.mViewPropertyAnimatorCompatMap.get(view)) == null) {
            viewPropertyAnimatorCompat = new ViewPropertyAnimatorCompat(view);
            this.mViewPropertyAnimatorCompatMap.put(view, viewPropertyAnimatorCompat);
        }
        return viewPropertyAnimatorCompat;
    }
    
    @Override
    public boolean canScrollHorizontally(final View view, final int n) {
        return ViewCompatICS.canScrollHorizontally(view, n);
    }
    
    @Override
    public void setAccessibilityDelegate(final View view, final AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompatICS.setAccessibilityDelegate(view, accessibilityDelegateCompat.getBridge());
    }
}
