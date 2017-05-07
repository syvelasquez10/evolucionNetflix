// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatKitKat
{
    public static int getLiveRegion(final Object o) {
        return ((AccessibilityNodeInfo)o).getLiveRegion();
    }
    
    public static void setLiveRegion(final Object o, final int liveRegion) {
        ((AccessibilityNodeInfo)o).setLiveRegion(liveRegion);
    }
}
