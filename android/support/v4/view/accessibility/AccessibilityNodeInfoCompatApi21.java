// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction;

class AccessibilityNodeInfoCompatApi21
{
    static Object newAccessibilityAction(final int n, final CharSequence charSequence) {
        return new AccessibilityNodeInfo$AccessibilityAction(n, charSequence);
    }
    
    public static Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
        return AccessibilityNodeInfo$CollectionInfo.obtain(n, n2, b, n3);
    }
    
    public static Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        return AccessibilityNodeInfo$CollectionItemInfo.obtain(n, n2, n3, n4, b, b2);
    }
    
    public static boolean removeAction(final Object o, final Object o2) {
        return ((AccessibilityNodeInfo)o).removeAction((AccessibilityNodeInfo$AccessibilityAction)o2);
    }
}
