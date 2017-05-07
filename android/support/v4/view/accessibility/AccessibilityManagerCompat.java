// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import java.util.List;
import android.view.accessibility.AccessibilityManager;
import android.os.Build$VERSION;

public class AccessibilityManagerCompat
{
    private static final AccessibilityManagerCompat$AccessibilityManagerVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new AccessibilityManagerCompat$AccessibilityManagerIcsImpl();
            return;
        }
        IMPL = new AccessibilityManagerCompat$AccessibilityManagerStubImpl();
    }
    
    public static boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat accessibilityManagerCompat$AccessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompat.IMPL.addAccessibilityStateChangeListener(accessibilityManager, accessibilityManagerCompat$AccessibilityStateChangeListenerCompat);
    }
    
    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
        return AccessibilityManagerCompat.IMPL.getEnabledAccessibilityServiceList(accessibilityManager, n);
    }
    
    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompat.IMPL.getInstalledAccessibilityServiceList(accessibilityManager);
    }
    
    public static boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompat.IMPL.isTouchExplorationEnabled(accessibilityManager);
    }
    
    public static boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat accessibilityManagerCompat$AccessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompat.IMPL.removeAccessibilityStateChangeListener(accessibilityManager, accessibilityManagerCompat$AccessibilityStateChangeListenerCompat);
    }
}
