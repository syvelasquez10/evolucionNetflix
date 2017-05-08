// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.os.Bundle;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoKitKatImpl extends AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanMr2Impl
{
    @Override
    public boolean canOpenPopup(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.canOpenPopup(o);
    }
    
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
    public Bundle getExtras(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.getExtras(o);
    }
    
    @Override
    public int getInputType(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.getInputType(o);
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
    public CharSequence getRoleDescription(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.getRoleDescription(o);
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
    public boolean isContentInvalid(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.isContentInvalid(o);
    }
    
    @Override
    public boolean isDismissable(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.isDismissable(o);
    }
    
    @Override
    public boolean isMultiLine(final Object o) {
        return AccessibilityNodeInfoCompatKitKat.isMultiLine(o);
    }
    
    @Override
    public Object obtainCollectionInfo(final int n, final int n2, final boolean b) {
        return AccessibilityNodeInfoCompatKitKat.obtainCollectionInfo(n, n2, b);
    }
    
    @Override
    public Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
        return AccessibilityNodeInfoCompatKitKat.obtainCollectionInfo(n, n2, b, n3);
    }
    
    @Override
    public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b) {
        return AccessibilityNodeInfoCompatKitKat.obtainCollectionItemInfo(n, n2, n3, n4, b);
    }
    
    @Override
    public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        return AccessibilityNodeInfoCompatKitKat.obtainCollectionItemInfo(n, n2, n3, n4, b);
    }
    
    @Override
    public Object obtainRangeInfo(final int n, final float n2, final float n3, final float n4) {
        return AccessibilityNodeInfoCompatKitKat.obtainRangeInfo(n, n2, n3, n4);
    }
    
    @Override
    public void setCanOpenPopup(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatKitKat.setCanOpenPopup(o, b);
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
    public void setContentInvalid(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatKitKat.setContentInvalid(o, b);
    }
    
    @Override
    public void setDismissable(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatKitKat.setDismissable(o, b);
    }
    
    @Override
    public void setInputType(final Object o, final int n) {
        AccessibilityNodeInfoCompatKitKat.setInputType(o, n);
    }
    
    @Override
    public void setLiveRegion(final Object o, final int n) {
        AccessibilityNodeInfoCompatKitKat.setLiveRegion(o, n);
    }
    
    @Override
    public void setMultiLine(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatKitKat.setMultiLine(o, b);
    }
    
    @Override
    public void setRangeInfo(final Object o, final Object o2) {
        AccessibilityNodeInfoCompatKitKat.setRangeInfo(o, o2);
    }
    
    @Override
    public void setRoleDescription(final Object o, final CharSequence charSequence) {
        AccessibilityNodeInfoCompatKitKat.setRoleDescription(o, charSequence);
    }
}
