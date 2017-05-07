// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction;

class AccessibilityNodeInfoCompatApi21$AccessibilityAction
{
    static int getId(final Object o) {
        return ((AccessibilityNodeInfo$AccessibilityAction)o).getId();
    }
    
    static CharSequence getLabel(final Object o) {
        return ((AccessibilityNodeInfo$AccessibilityAction)o).getLabel();
    }
}
