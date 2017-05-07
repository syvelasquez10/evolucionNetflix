// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

class AccessibilityNodeProviderCompat$AccessibilityNodeProviderKitKatImpl extends AccessibilityNodeProviderCompat$AccessibilityNodeProviderStubImpl
{
    @Override
    public Object newAccessibilityNodeProviderBridge(final AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        return AccessibilityNodeProviderCompatKitKat.newAccessibilityNodeProviderBridge(new AccessibilityNodeProviderCompat$AccessibilityNodeProviderKitKatImpl$1(this, accessibilityNodeProviderCompat));
    }
}
