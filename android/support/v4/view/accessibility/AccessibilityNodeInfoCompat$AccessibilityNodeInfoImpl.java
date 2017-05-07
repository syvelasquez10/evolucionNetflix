// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.graphics.Rect;
import java.util.List;
import android.view.View;

interface AccessibilityNodeInfoCompat$AccessibilityNodeInfoImpl
{
    void addAction(final Object p0, final int p1);
    
    void addAction(final Object p0, final int p1, final CharSequence p2);
    
    void addChild(final Object p0, final View p1);
    
    void addChild(final Object p0, final View p1, final int p2);
    
    List<Object> findAccessibilityNodeInfosByText(final Object p0, final String p1);
    
    Object findFocus(final Object p0, final int p1);
    
    Object focusSearch(final Object p0, final int p1);
    
    List<Object> getActionList(final Object p0);
    
    int getActions(final Object p0);
    
    void getBoundsInParent(final Object p0, final Rect p1);
    
    void getBoundsInScreen(final Object p0, final Rect p1);
    
    Object getChild(final Object p0, final int p1);
    
    int getChildCount(final Object p0);
    
    CharSequence getClassName(final Object p0);
    
    Object getCollectionInfo(final Object p0);
    
    int getCollectionInfoColumnCount(final Object p0);
    
    int getCollectionInfoRowCount(final Object p0);
    
    int getCollectionItemColumnIndex(final Object p0);
    
    int getCollectionItemColumnSpan(final Object p0);
    
    Object getCollectionItemInfo(final Object p0);
    
    int getCollectionItemRowIndex(final Object p0);
    
    int getCollectionItemRowSpan(final Object p0);
    
    CharSequence getContentDescription(final Object p0);
    
    int getLiveRegion(final Object p0);
    
    int getMovementGranularities(final Object p0);
    
    CharSequence getPackageName(final Object p0);
    
    Object getParent(final Object p0);
    
    Object getRangeInfo(final Object p0);
    
    CharSequence getText(final Object p0);
    
    String getViewIdResourceName(final Object p0);
    
    int getWindowId(final Object p0);
    
    boolean isAccessibilityFocused(final Object p0);
    
    boolean isCheckable(final Object p0);
    
    boolean isChecked(final Object p0);
    
    boolean isClickable(final Object p0);
    
    boolean isCollectionInfoHierarchical(final Object p0);
    
    boolean isCollectionItemHeading(final Object p0);
    
    boolean isCollectionItemSelected(final Object p0);
    
    boolean isEnabled(final Object p0);
    
    boolean isFocusable(final Object p0);
    
    boolean isFocused(final Object p0);
    
    boolean isLongClickable(final Object p0);
    
    boolean isPassword(final Object p0);
    
    boolean isScrollable(final Object p0);
    
    boolean isSelected(final Object p0);
    
    boolean isVisibleToUser(final Object p0);
    
    Object obtain();
    
    Object obtain(final View p0);
    
    Object obtain(final View p0, final int p1);
    
    Object obtain(final Object p0);
    
    Object obtainCollectionInfo(final int p0, final int p1, final boolean p2, final int p3);
    
    Object obtainCollectionItemInfo(final int p0, final int p1, final int p2, final int p3, final boolean p4, final boolean p5);
    
    boolean performAction(final Object p0, final int p1);
    
    boolean performAction(final Object p0, final int p1, final Bundle p2);
    
    void recycle(final Object p0);
    
    void setAccessibilityFocused(final Object p0, final boolean p1);
    
    void setBoundsInParent(final Object p0, final Rect p1);
    
    void setBoundsInScreen(final Object p0, final Rect p1);
    
    void setCheckable(final Object p0, final boolean p1);
    
    void setChecked(final Object p0, final boolean p1);
    
    void setClassName(final Object p0, final CharSequence p1);
    
    void setClickable(final Object p0, final boolean p1);
    
    void setCollectionInfo(final Object p0, final Object p1);
    
    void setCollectionItemInfo(final Object p0, final Object p1);
    
    void setContentDescription(final Object p0, final CharSequence p1);
    
    void setEnabled(final Object p0, final boolean p1);
    
    void setFocusable(final Object p0, final boolean p1);
    
    void setFocused(final Object p0, final boolean p1);
    
    void setLiveRegion(final Object p0, final int p1);
    
    void setLongClickable(final Object p0, final boolean p1);
    
    void setMovementGranularities(final Object p0, final int p1);
    
    void setPackageName(final Object p0, final CharSequence p1);
    
    void setParent(final Object p0, final View p1);
    
    void setParent(final Object p0, final View p1, final int p2);
    
    void setPassword(final Object p0, final boolean p1);
    
    void setScrollable(final Object p0, final boolean p1);
    
    void setSelected(final Object p0, final boolean p1);
    
    void setSource(final Object p0, final View p1);
    
    void setSource(final Object p0, final View p1, final int p2);
    
    void setText(final Object p0, final CharSequence p1);
    
    void setViewIdResourceName(final Object p0, final String p1);
    
    void setVisibleToUser(final Object p0, final boolean p1);
}
