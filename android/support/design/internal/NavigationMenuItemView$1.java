// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.support.v4.view.AccessibilityDelegateCompat;

class NavigationMenuItemView$1 extends AccessibilityDelegateCompat
{
    final /* synthetic */ NavigationMenuItemView this$0;
    
    NavigationMenuItemView$1(final NavigationMenuItemView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setCheckable(this.this$0.mCheckable);
    }
}
