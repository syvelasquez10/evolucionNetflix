// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.View;
import java.util.List;
import android.os.Parcelable;

class AccessibilityRecordCompat$AccessibilityRecordIcsImpl extends AccessibilityRecordCompat$AccessibilityRecordStubImpl
{
    @Override
    public int getAddedCount(final Object o) {
        return AccessibilityRecordCompatIcs.getAddedCount(o);
    }
    
    @Override
    public CharSequence getBeforeText(final Object o) {
        return AccessibilityRecordCompatIcs.getBeforeText(o);
    }
    
    @Override
    public CharSequence getClassName(final Object o) {
        return AccessibilityRecordCompatIcs.getClassName(o);
    }
    
    @Override
    public CharSequence getContentDescription(final Object o) {
        return AccessibilityRecordCompatIcs.getContentDescription(o);
    }
    
    @Override
    public int getCurrentItemIndex(final Object o) {
        return AccessibilityRecordCompatIcs.getCurrentItemIndex(o);
    }
    
    @Override
    public int getFromIndex(final Object o) {
        return AccessibilityRecordCompatIcs.getFromIndex(o);
    }
    
    @Override
    public int getItemCount(final Object o) {
        return AccessibilityRecordCompatIcs.getItemCount(o);
    }
    
    @Override
    public Parcelable getParcelableData(final Object o) {
        return AccessibilityRecordCompatIcs.getParcelableData(o);
    }
    
    @Override
    public int getRemovedCount(final Object o) {
        return AccessibilityRecordCompatIcs.getRemovedCount(o);
    }
    
    @Override
    public int getScrollX(final Object o) {
        return AccessibilityRecordCompatIcs.getScrollX(o);
    }
    
    @Override
    public int getScrollY(final Object o) {
        return AccessibilityRecordCompatIcs.getScrollY(o);
    }
    
    @Override
    public AccessibilityNodeInfoCompat getSource(final Object o) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityRecordCompatIcs.getSource(o));
    }
    
    @Override
    public List<CharSequence> getText(final Object o) {
        return AccessibilityRecordCompatIcs.getText(o);
    }
    
    @Override
    public int getToIndex(final Object o) {
        return AccessibilityRecordCompatIcs.getToIndex(o);
    }
    
    @Override
    public int getWindowId(final Object o) {
        return AccessibilityRecordCompatIcs.getWindowId(o);
    }
    
    @Override
    public boolean isChecked(final Object o) {
        return AccessibilityRecordCompatIcs.isChecked(o);
    }
    
    @Override
    public boolean isEnabled(final Object o) {
        return AccessibilityRecordCompatIcs.isEnabled(o);
    }
    
    @Override
    public boolean isFullScreen(final Object o) {
        return AccessibilityRecordCompatIcs.isFullScreen(o);
    }
    
    @Override
    public boolean isPassword(final Object o) {
        return AccessibilityRecordCompatIcs.isPassword(o);
    }
    
    @Override
    public boolean isScrollable(final Object o) {
        return AccessibilityRecordCompatIcs.isScrollable(o);
    }
    
    @Override
    public Object obtain() {
        return AccessibilityRecordCompatIcs.obtain();
    }
    
    @Override
    public Object obtain(final Object o) {
        return AccessibilityRecordCompatIcs.obtain(o);
    }
    
    @Override
    public void recycle(final Object o) {
        AccessibilityRecordCompatIcs.recycle(o);
    }
    
    @Override
    public void setAddedCount(final Object o, final int n) {
        AccessibilityRecordCompatIcs.setAddedCount(o, n);
    }
    
    @Override
    public void setBeforeText(final Object o, final CharSequence charSequence) {
        AccessibilityRecordCompatIcs.setBeforeText(o, charSequence);
    }
    
    @Override
    public void setChecked(final Object o, final boolean b) {
        AccessibilityRecordCompatIcs.setChecked(o, b);
    }
    
    @Override
    public void setClassName(final Object o, final CharSequence charSequence) {
        AccessibilityRecordCompatIcs.setClassName(o, charSequence);
    }
    
    @Override
    public void setContentDescription(final Object o, final CharSequence charSequence) {
        AccessibilityRecordCompatIcs.setContentDescription(o, charSequence);
    }
    
    @Override
    public void setCurrentItemIndex(final Object o, final int n) {
        AccessibilityRecordCompatIcs.setCurrentItemIndex(o, n);
    }
    
    @Override
    public void setEnabled(final Object o, final boolean b) {
        AccessibilityRecordCompatIcs.setEnabled(o, b);
    }
    
    @Override
    public void setFromIndex(final Object o, final int n) {
        AccessibilityRecordCompatIcs.setFromIndex(o, n);
    }
    
    @Override
    public void setFullScreen(final Object o, final boolean b) {
        AccessibilityRecordCompatIcs.setFullScreen(o, b);
    }
    
    @Override
    public void setItemCount(final Object o, final int n) {
        AccessibilityRecordCompatIcs.setItemCount(o, n);
    }
    
    @Override
    public void setParcelableData(final Object o, final Parcelable parcelable) {
        AccessibilityRecordCompatIcs.setParcelableData(o, parcelable);
    }
    
    @Override
    public void setPassword(final Object o, final boolean b) {
        AccessibilityRecordCompatIcs.setPassword(o, b);
    }
    
    @Override
    public void setRemovedCount(final Object o, final int n) {
        AccessibilityRecordCompatIcs.setRemovedCount(o, n);
    }
    
    @Override
    public void setScrollX(final Object o, final int n) {
        AccessibilityRecordCompatIcs.setScrollX(o, n);
    }
    
    @Override
    public void setScrollY(final Object o, final int n) {
        AccessibilityRecordCompatIcs.setScrollY(o, n);
    }
    
    @Override
    public void setScrollable(final Object o, final boolean b) {
        AccessibilityRecordCompatIcs.setScrollable(o, b);
    }
    
    @Override
    public void setSource(final Object o, final View view) {
        AccessibilityRecordCompatIcs.setSource(o, view);
    }
    
    @Override
    public void setToIndex(final Object o, final int n) {
        AccessibilityRecordCompatIcs.setToIndex(o, n);
    }
}
