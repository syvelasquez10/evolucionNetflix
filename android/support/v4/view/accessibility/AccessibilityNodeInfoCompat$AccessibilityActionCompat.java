// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

public class AccessibilityNodeInfoCompat$AccessibilityActionCompat
{
    private final Object mAction;
    
    private AccessibilityNodeInfoCompat$AccessibilityActionCompat(final Object mAction) {
        this.mAction = mAction;
    }
    
    public int getId() {
        return AccessibilityNodeInfoCompatApi21$AccessibilityAction.getId(this.mAction);
    }
    
    public CharSequence getLabel() {
        return AccessibilityNodeInfoCompatApi21$AccessibilityAction.getLabel(this.mAction);
    }
}
