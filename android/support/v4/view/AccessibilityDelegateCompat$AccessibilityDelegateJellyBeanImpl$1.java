// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.os.Bundle;
import android.view.ViewGroup;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;

class AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1 implements AccessibilityDelegateCompatJellyBean$AccessibilityDelegateBridgeJellyBean
{
    final /* synthetic */ AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl this$0;
    final /* synthetic */ AccessibilityDelegateCompat val$compat;
    
    AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl$1(final AccessibilityDelegateCompat$AccessibilityDelegateJellyBeanImpl this$0, final AccessibilityDelegateCompat val$compat) {
        this.this$0 = this$0;
        this.val$compat = val$compat;
    }
    
    @Override
    public boolean dispatchPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        return this.val$compat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }
    
    @Override
    public Object getAccessibilityNodeProvider(final View view) {
        final AccessibilityNodeProviderCompat accessibilityNodeProvider = this.val$compat.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return accessibilityNodeProvider.getProvider();
        }
        return null;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        this.val$compat.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final Object o) {
        this.val$compat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(o));
    }
    
    @Override
    public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        this.val$compat.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }
    
    @Override
    public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
        return this.val$compat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        return this.val$compat.performAccessibilityAction(view, n, bundle);
    }
    
    @Override
    public void sendAccessibilityEvent(final View view, final int n) {
        this.val$compat.sendAccessibilityEvent(view, n);
    }
    
    @Override
    public void sendAccessibilityEventUnchecked(final View view, final AccessibilityEvent accessibilityEvent) {
        this.val$compat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
