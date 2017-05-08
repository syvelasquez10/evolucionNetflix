// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import java.util.Collections;
import android.accessibilityservice.AccessibilityServiceInfo;
import java.util.List;
import android.view.accessibility.AccessibilityManager;

class AccessibilityManagerCompat$AccessibilityManagerStubImpl implements AccessibilityManagerCompat$AccessibilityManagerVersionImpl
{
    @Override
    public boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$AccessibilityStateChangeListener accessibilityManagerCompat$AccessibilityStateChangeListener) {
        return false;
    }
    
    @Override
    public boolean addTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$TouchExplorationStateChangeListener accessibilityManagerCompat$TouchExplorationStateChangeListener) {
        return false;
    }
    
    @Override
    public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
        return Collections.emptyList();
    }
    
    @Override
    public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
        return Collections.emptyList();
    }
    
    @Override
    public boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
        return false;
    }
    
    @Override
    public AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper newAccessibilityStateChangeListener(final AccessibilityManagerCompat$AccessibilityStateChangeListener accessibilityManagerCompat$AccessibilityStateChangeListener) {
        return null;
    }
    
    @Override
    public AccessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerWrapper newTouchExplorationStateChangeListener(final AccessibilityManagerCompat$TouchExplorationStateChangeListener accessibilityManagerCompat$TouchExplorationStateChangeListener) {
        return null;
    }
    
    @Override
    public boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$AccessibilityStateChangeListener accessibilityManagerCompat$AccessibilityStateChangeListener) {
        return false;
    }
    
    @Override
    public boolean removeTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityManagerCompat$TouchExplorationStateChangeListener accessibilityManagerCompat$TouchExplorationStateChangeListener) {
        return false;
    }
}
