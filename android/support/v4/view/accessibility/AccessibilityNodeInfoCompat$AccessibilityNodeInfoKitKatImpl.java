// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoKitKatImpl extends AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanMr2Impl
{
    @Override
    public Object getCollectionInfo(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.getCollectionInfo(o);
    }
    
    @Override
    public int getCollectionInfoColumnCount(final Object o) {
        return AccessibilityNodeInfoCompatKitKat$CollectionInfo.getColumnCount(o);
    }
    
    @Override
    public int getCollectionInfoRowCount(final Object o) {
        return AccessibilityNodeInfoCompatKitKat$CollectionInfo.getRowCount(o);
    }
    
    @Override
    public int getCollectionItemColumnIndex(final Object o) {
        return AccessibilityNodeInfoCompatKitKat$CollectionItemInfo.getColumnIndex(o);
    }
    
    @Override
    public int getCollectionItemColumnSpan(final Object o) {
        return AccessibilityNodeInfoCompatKitKat$CollectionItemInfo.getColumnSpan(o);
    }
    
    @Override
    public Object getCollectionItemInfo(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.getCollectionItemInfo(o);
    }
    
    @Override
    public int getCollectionItemRowIndex(final Object o) {
        return AccessibilityNodeInfoCompatKitKat$CollectionItemInfo.getRowIndex(o);
    }
    
    @Override
    public int getCollectionItemRowSpan(final Object o) {
        return AccessibilityNodeInfoCompatKitKat$CollectionItemInfo.getRowSpan(o);
    }
    
    @Override
    public int getLiveRegion(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.getLiveRegion(o);
    }
    
    @Override
    public Object getRangeInfo(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.getRangeInfo(o);
    }
    
    @Override
    public boolean isCollectionInfoHierarchical(final Object o) {
        return AccessibilityNodeInfoCompatKitKat$CollectionInfo.isHierarchical(o);
    }
    
    @Override
    public boolean isCollectionItemHeading(final Object o) {
        return AccessibilityNodeInfoCompatKitKat$CollectionItemInfo.isHeading(o);
    }
    
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
    
    @Override
    public void setLiveRegion(final Object o, final int n) {
        AccessibilityNodeInfoCompatKitKat.setLiveRegion(o, n);
    }
}
