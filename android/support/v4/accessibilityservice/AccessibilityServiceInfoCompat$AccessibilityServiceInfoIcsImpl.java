// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.accessibilityservice;

import android.content.pm.ResolveInfo;
import android.accessibilityservice.AccessibilityServiceInfo;

class AccessibilityServiceInfoCompat$AccessibilityServiceInfoIcsImpl extends AccessibilityServiceInfoCompat$AccessibilityServiceInfoStubImpl
{
    @Override
    public boolean getCanRetrieveWindowContent(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompatIcs.getCanRetrieveWindowContent(accessibilityServiceInfo);
    }
    
    @Override
    public int getCapabilities(final AccessibilityServiceInfo accessibilityServiceInfo) {
        if (this.getCanRetrieveWindowContent(accessibilityServiceInfo)) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public String getDescription(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompatIcs.getDescription(accessibilityServiceInfo);
    }
    
    @Override
    public String getId(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompatIcs.getId(accessibilityServiceInfo);
    }
    
    @Override
    public ResolveInfo getResolveInfo(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompatIcs.getResolveInfo(accessibilityServiceInfo);
    }
    
    @Override
    public String getSettingsActivityName(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompatIcs.getSettingsActivityName(accessibilityServiceInfo);
    }
}
