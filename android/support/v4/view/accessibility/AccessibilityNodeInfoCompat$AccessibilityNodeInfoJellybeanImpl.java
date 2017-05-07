// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanImpl extends AccessibilityNodeInfoCompat$AccessibilityNodeInfoIcsImpl
{
    @Override
    public boolean isAccessibilityFocused(final Object o) {
        return AccessibilityNodeInfoCompatJellyBean.isAccessibilityFocused(o);
    }
    
    @Override
    public boolean isVisibleToUser(final Object o) {
        return AccessibilityNodeInfoCompatJellyBean.isVisibleToUser(o);
    }
    
    @Override
    public void setAccessibilityFocused(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatJellyBean.setAccesibilityFocused(o, b);
    }
    
    @Override
    public void setVisibleToUser(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatJellyBean.setVisibleToUser(o, b);
    }
}
