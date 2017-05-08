// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import java.util.List;
import android.view.accessibility.AccessibilityManager;

class AccessibilityManagerCompat$AccessibilityManagerIcsImpl extends AccessibilityManagerCompat$AccessibilityManagerStubImpl
{
    @Override
    public boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$AccessibilityStateChangeListener accessibilityManagerCompat$AccessibilityStateChangeListener) {
        return AccessibilityManagerCompatIcs.addAccessibilityStateChangeListener(accessibilityManager, this.newAccessibilityStateChangeListener(accessibilityManagerCompat$AccessibilityStateChangeListener));
    }
    
    @Override
    public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
        return AccessibilityManagerCompatIcs.getEnabledAccessibilityServiceList(accessibilityManager, n);
    }
    
    @Override
    public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompatIcs.getInstalledAccessibilityServiceList(accessibilityManager);
    }
    
    @Override
    public boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompatIcs.isTouchExplorationEnabled(accessibilityManager);
    }
    
    @Override
    public AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper newAccessibilityStateChangeListener(final AccessibilityManagerCompat$AccessibilityStateChangeListener accessibilityManagerCompat$AccessibilityStateChangeListener) {
        return new AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper(accessibilityManagerCompat$AccessibilityStateChangeListener, new AccessibilityManagerCompat$AccessibilityManagerIcsImpl$1(this, accessibilityManagerCompat$AccessibilityStateChangeListener));
    }
    
    @Override
    public boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$AccessibilityStateChangeListener accessibilityManagerCompat$AccessibilityStateChangeListener) {
        return AccessibilityManagerCompatIcs.removeAccessibilityStateChangeListener(accessibilityManager, this.newAccessibilityStateChangeListener(accessibilityManagerCompat$AccessibilityStateChangeListener));
    }
}
