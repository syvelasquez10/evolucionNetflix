// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoKitKatImpl extends AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanMr2Impl
{
    @Override
    public Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
        return AccessibilityNodeInfoCompatKitKat.obtainCollectionInfo(n, n2, b, n3);
    }
    
    @Override
    public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        return AccessibilityNodeInfoCompatKitKat.obtainCollectionItemInfo(n, n2, n3, n4, b);
    }
    
    @Override
    public void setCollectionInfo(final Object o, final Object o2) {
        AccessibilityNodeInfoCompatKitKat.setCollectionInfo(o, o2);
    }
    
    @Override
    public void setCollectionItemInfo(final Object o, final Object o2) {
        AccessibilityNodeInfoCompatKitKat.setCollectionItemInfo(o, o2);
    }
}
