// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import java.util.List;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoApi21Impl extends AccessibilityNodeInfoCompat$AccessibilityNodeInfoKitKatImpl
{
    @Override
    public void addAction(final Object o, final int n, final CharSequence charSequence) {
        AccessibilityNodeInfoCompatApi21.addAction(o, n, charSequence);
    }
    
    @Override
    public List<Object> getActionList(final Object o) {
        return AccessibilityNodeInfoCompatApi21.getActionList(o);
    }
    
    @Override
    public boolean isCollectionItemSelected(final Object o) {
        return AccessibilityNodeInfoCompatApi21$CollectionItemInfo.isSelected(o);
    }
    
    @Override
    public Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
        return AccessibilityNodeInfoCompatApi21.obtainCollectionInfo(n, n2, b, n3);
    }
    
    @Override
    public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        return AccessibilityNodeInfoCompatApi21.obtainCollectionItemInfo(n, n2, n3, n4, b, b2);
    }
}
