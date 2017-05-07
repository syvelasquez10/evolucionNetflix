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
    public boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat accessibilityManagerCompat$AccessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompatIcs.addAccessibilityStateChangeListener(accessibilityManager, accessibilityManagerCompat$AccessibilityStateChangeListenerCompat.mListener);
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
    public Object newAccessiblityStateChangeListener(final AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat accessibilityManagerCompat$AccessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompatIcs.newAccessibilityStateChangeListener(new AccessibilityManagerCompat$AccessibilityManagerIcsImpl$1(this, accessibilityManagerCompat$AccessibilityStateChangeListenerCompat));
    }
    
    @Override
    public boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat accessibilityManagerCompat$AccessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompatIcs.removeAccessibilityStateChangeListener(accessibilityManager, accessibilityManagerCompat$AccessibilityStateChangeListenerCompat.mListener);
    }
}
