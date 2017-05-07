// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import java.util.List;
import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;
import android.view.accessibility.AccessibilityManager;

class AccessibilityManagerCompatIcs
{
    public static boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final Object o) {
        return accessibilityManager.addAccessibilityStateChangeListener((AccessibilityManager$AccessibilityStateChangeListener)o);
    }
    
    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
        return (List<AccessibilityServiceInfo>)accessibilityManager.getEnabledAccessibilityServiceList(n);
    }
    
    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
        return (List<AccessibilityServiceInfo>)accessibilityManager.getInstalledAccessibilityServiceList();
    }
    
    public static boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
        return accessibilityManager.isTouchExplorationEnabled();
    }
    
    public static Object newAccessibilityStateChangeListener(final AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge accessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge) {
        return new AccessibilityManagerCompatIcs$1(accessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge);
    }
    
    public static boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final Object o) {
        return accessibilityManager.removeAccessibilityStateChangeListener((AccessibilityManager$AccessibilityStateChangeListener)o);
    }
}
