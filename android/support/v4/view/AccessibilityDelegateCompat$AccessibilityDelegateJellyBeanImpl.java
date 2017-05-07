// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;

class AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl extends AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl
{
    @Override
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object accessibilityNodeProvider, final View view) {
        accessibilityNodeProvider = AccessibilityDelegateCompatJellyBean.getAccessibilityNodeProvider(accessibilityNodeProvider, view);
        if (accessibilityNodeProvider != null) {
            return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
        }
        return null;
    }
    
    @Override
    public Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat accessibilityDelegateCompat) {
        return AccessibilityDelegateCompatJellyBean.newAccessibilityDelegateBridge(new AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1(this, accessibilityDelegateCompat));
    }
    
    @Override
    public boolean performAccessibilityAction(final Object o, final View view, final int n, final Bundle bundle) {
        return AccessibilityDelegateCompatJellyBean.performAccessibilityAction(o, view, n, bundle);
    }
}
