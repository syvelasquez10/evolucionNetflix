// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionInfo;
import java.util.List;
import android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatApi21
{
    static void addAction(final Object o, final int n, final CharSequence charSequence) {
        ((AccessibilityNodeInfo)o).addAction(new AccessibilityNodeInfo$AccessibilityAction(n, charSequence));
    }
    
    static List<Object> getActionList(final Object o) {
        return (List<Object>)((AccessibilityNodeInfo)o).getActionList();
    }
    
    public static Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
        return AccessibilityNodeInfo$CollectionInfo.obtain(n, n2, b, n3);
    }
    
    public static Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        return AccessibilityNodeInfo$CollectionItemInfo.obtain(n, n2, n3, n4, b, b2);
    }
}
