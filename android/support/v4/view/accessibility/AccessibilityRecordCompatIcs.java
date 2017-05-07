// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityRecord;

class AccessibilityRecordCompatIcs
{
    public static void setFromIndex(final Object o, final int fromIndex) {
        ((AccessibilityRecord)o).setFromIndex(fromIndex);
    }
    
    public static void setItemCount(final Object o, final int itemCount) {
        ((AccessibilityRecord)o).setItemCount(itemCount);
    }
    
    public static void setScrollable(final Object o, final boolean scrollable) {
        ((AccessibilityRecord)o).setScrollable(scrollable);
    }
    
    public static void setToIndex(final Object o, final int toIndex) {
        ((AccessibilityRecord)o).setToIndex(toIndex);
    }
}
