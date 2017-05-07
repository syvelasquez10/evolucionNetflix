// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoApi21Impl extends AccessibilityNodeInfoCompat$AccessibilityNodeInfoKitKatImpl
{
    @Override
    public Object newAccessibilityAction(final int n, final CharSequence charSequence) {
        return AccessibilityNodeInfoCompatApi21.newAccessibilityAction(n, charSequence);
    }
    
    @Override
    public Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
        return AccessibilityNodeInfoCompatApi21.obtainCollectionInfo(n, n2, b, n3);
    }
    
    @Override
    public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        return AccessibilityNodeInfoCompatApi21.obtainCollectionItemInfo(n, n2, n3, n4, b, b2);
    }
    
    @Override
    public boolean removeAction(final Object o, final Object o2) {
        return AccessibilityNodeInfoCompatApi21.removeAction(o, o2);
    }
}
