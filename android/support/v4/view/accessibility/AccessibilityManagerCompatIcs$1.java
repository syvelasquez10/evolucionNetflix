// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;

final class AccessibilityManagerCompatIcs$1 implements AccessibilityManager$AccessibilityStateChangeListener
{
    final /* synthetic */ AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge val$bridge;
    
    AccessibilityManagerCompatIcs$1(final AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge val$bridge) {
        this.val$bridge = val$bridge;
    }
    
    public void onAccessibilityStateChanged(final boolean b) {
        this.val$bridge.onAccessibilityStateChanged(b);
    }
}
