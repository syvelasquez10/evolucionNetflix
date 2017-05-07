// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo$RangeInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatKitKat
{
    static Object getCollectionInfo(final Object o) {
        return ((AccessibilityNodeInfo)o).getCollectionInfo();
    }
    
    static Object getCollectionItemInfo(final Object o) {
        return ((AccessibilityNodeInfo)o).getCollectionItemInfo();
    }
    
    static int getLiveRegion(final Object o) {
        return ((AccessibilityNodeInfo)o).getLiveRegion();
    }
    
    static Object getRangeInfo(final Object o) {
        return ((AccessibilityNodeInfo)o).getRangeInfo();
    }
    
    public static Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
        return AccessibilityNodeInfo$CollectionInfo.obtain(n, n2, b);
    }
    
    public static Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b) {
        return AccessibilityNodeInfo$CollectionItemInfo.obtain(n, n2, n3, n4, b);
    }
    
    public static void setCollectionInfo(final Object o, final Object o2) {
        ((AccessibilityNodeInfo)o).setCollectionInfo((AccessibilityNodeInfo$CollectionInfo)o2);
    }
    
    public static void setCollectionItemInfo(final Object o, final Object o2) {
        ((AccessibilityNodeInfo)o).setCollectionItemInfo((AccessibilityNodeInfo$CollectionItemInfo)o2);
    }
    
    static void setLiveRegion(final Object o, final int liveRegion) {
        ((AccessibilityNodeInfo)o).setLiveRegion(liveRegion);
    }
    
    static class CollectionInfo
    {
        static int getColumnCount(final Object o) {
            return ((AccessibilityNodeInfo$CollectionInfo)o).getColumnCount();
        }
        
        static int getRowCount(final Object o) {
            return ((AccessibilityNodeInfo$CollectionInfo)o).getRowCount();
        }
        
        static boolean isHierarchical(final Object o) {
            return ((AccessibilityNodeInfo$CollectionInfo)o).isHierarchical();
        }
    }
    
    static class CollectionItemInfo
    {
        static int getColumnIndex(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).getColumnIndex();
        }
        
        static int getColumnSpan(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).getColumnSpan();
        }
        
        static int getRowIndex(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).getRowIndex();
        }
        
        static int getRowSpan(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).getRowSpan();
        }
        
        static boolean isHeading(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).isHeading();
        }
    }
    
    static class RangeInfo
    {
        static float getCurrent(final Object o) {
            return ((AccessibilityNodeInfo$RangeInfo)o).getCurrent();
        }
        
        static float getMax(final Object o) {
            return ((AccessibilityNodeInfo$RangeInfo)o).getMax();
        }
        
        static float getMin(final Object o) {
            return ((AccessibilityNodeInfo$RangeInfo)o).getMin();
        }
        
        static int getType(final Object o) {
            return ((AccessibilityNodeInfo$RangeInfo)o).getType();
        }
    }
}
