// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import java.util.List;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanMr2Impl extends AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanMr1Impl
{
    @Override
    public List<Object> findAccessibilityNodeInfosByViewId(final Object o, final String s) {
        return AccessibilityNodeInfoCompatJellybeanMr2.findAccessibilityNodeInfosByViewId(o, s);
    }
    
    @Override
    public int getTextSelectionEnd(final Object o) {
        return AccessibilityNodeInfoCompatJellybeanMr2.getTextSelectionEnd(o);
    }
    
    @Override
    public int getTextSelectionStart(final Object o) {
        return AccessibilityNodeInfoCompatJellybeanMr2.getTextSelectionStart(o);
    }
    
    @Override
    public String getViewIdResourceName(final Object o) {
        return AccessibilityNodeInfoCompatJellybeanMr2.getViewIdResourceName(o);
    }
    
    @Override
    public boolean isEditable(final Object o) {
        return AccessibilityNodeInfoCompatJellybeanMr2.isEditable(o);
    }
    
    @Override
    public boolean refresh(final Object o) {
        return AccessibilityNodeInfoCompatJellybeanMr2.refresh(o);
    }
    
    @Override
    public void setEditable(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatJellybeanMr2.setEditable(o, b);
    }
    
    @Override
    public void setTextSelection(final Object o, final int n, final int n2) {
        AccessibilityNodeInfoCompatJellybeanMr2.setTextSelection(o, n, n2);
    }
    
    @Override
    public void setViewIdResourceName(final Object o, final String s) {
        AccessibilityNodeInfoCompatJellybeanMr2.setViewIdResourceName(o, s);
    }
}
