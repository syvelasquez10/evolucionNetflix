// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.graphics.Rect;
import java.util.Collections;
import java.util.List;
import android.view.View;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoStubImpl implements AccessibilityNodeInfoCompat$AccessibilityNodeInfoImpl
{
    @Override
    public void addAction(final Object o, final int n) {
    }
    
    @Override
    public void addAction(final Object o, final Object o2) {
    }
    
    @Override
    public void addChild(final Object o, final View view) {
    }
    
    @Override
    public void addChild(final Object o, final View view, final int n) {
    }
    
    @Override
    public boolean canOpenPopup(final Object o) {
        return false;
    }
    
    @Override
    public List<Object> findAccessibilityNodeInfosByText(final Object o, final String s) {
        return Collections.emptyList();
    }
    
    @Override
    public List<Object> findAccessibilityNodeInfosByViewId(final Object o, final String s) {
        return Collections.emptyList();
    }
    
    @Override
    public Object findFocus(final Object o, final int n) {
        return null;
    }
    
    @Override
    public Object focusSearch(final Object o, final int n) {
        return null;
    }
    
    @Override
    public int getAccessibilityActionId(final Object o) {
        return 0;
    }
    
    @Override
    public CharSequence getAccessibilityActionLabel(final Object o) {
        return null;
    }
    
    @Override
    public Object getActionContextClick() {
        return null;
    }
    
    @Override
    public List<Object> getActionList(final Object o) {
        return null;
    }
    
    @Override
    public Object getActionScrollDown() {
        return null;
    }
    
    @Override
    public Object getActionScrollLeft() {
        return null;
    }
    
    @Override
    public Object getActionScrollRight() {
        return null;
    }
    
    @Override
    public Object getActionScrollToPosition() {
        return null;
    }
    
    @Override
    public Object getActionScrollUp() {
        return null;
    }
    
    @Override
    public Object getActionSetProgress() {
        return null;
    }
    
    @Override
    public Object getActionShowOnScreen() {
        return null;
    }
    
    @Override
    public int getActions(final Object o) {
        return 0;
    }
    
    @Override
    public void getBoundsInParent(final Object o, final Rect rect) {
    }
    
    @Override
    public void getBoundsInScreen(final Object o, final Rect rect) {
    }
    
    @Override
    public Object getChild(final Object o, final int n) {
        return null;
    }
    
    @Override
    public int getChildCount(final Object o) {
        return 0;
    }
    
    @Override
    public CharSequence getClassName(final Object o) {
        return null;
    }
    
    @Override
    public Object getCollectionInfo(final Object o) {
        return null;
    }
    
    @Override
    public int getCollectionInfoColumnCount(final Object o) {
        return 0;
    }
    
    @Override
    public int getCollectionInfoRowCount(final Object o) {
        return 0;
    }
    
    @Override
    public int getCollectionInfoSelectionMode(final Object o) {
        return 0;
    }
    
    @Override
    public int getCollectionItemColumnIndex(final Object o) {
        return 0;
    }
    
    @Override
    public int getCollectionItemColumnSpan(final Object o) {
        return 0;
    }
    
    @Override
    public Object getCollectionItemInfo(final Object o) {
        return null;
    }
    
    @Override
    public int getCollectionItemRowIndex(final Object o) {
        return 0;
    }
    
    @Override
    public int getCollectionItemRowSpan(final Object o) {
        return 0;
    }
    
    @Override
    public CharSequence getContentDescription(final Object o) {
        return null;
    }
    
    @Override
    public int getDrawingOrder(final Object o) {
        return 0;
    }
    
    @Override
    public CharSequence getError(final Object o) {
        return null;
    }
    
    @Override
    public Bundle getExtras(final Object o) {
        return new Bundle();
    }
    
    @Override
    public int getInputType(final Object o) {
        return 0;
    }
    
    @Override
    public Object getLabelFor(final Object o) {
        return null;
    }
    
    @Override
    public Object getLabeledBy(final Object o) {
        return null;
    }
    
    @Override
    public int getLiveRegion(final Object o) {
        return 0;
    }
    
    @Override
    public int getMaxTextLength(final Object o) {
        return -1;
    }
    
    @Override
    public int getMovementGranularities(final Object o) {
        return 0;
    }
    
    @Override
    public CharSequence getPackageName(final Object o) {
        return null;
    }
    
    @Override
    public Object getParent(final Object o) {
        return null;
    }
    
    @Override
    public Object getRangeInfo(final Object o) {
        return null;
    }
    
    @Override
    public CharSequence getRoleDescription(final Object o) {
        return null;
    }
    
    @Override
    public CharSequence getText(final Object o) {
        return null;
    }
    
    @Override
    public int getTextSelectionEnd(final Object o) {
        return -1;
    }
    
    @Override
    public int getTextSelectionStart(final Object o) {
        return -1;
    }
    
    @Override
    public Object getTraversalAfter(final Object o) {
        return null;
    }
    
    @Override
    public Object getTraversalBefore(final Object o) {
        return null;
    }
    
    @Override
    public String getViewIdResourceName(final Object o) {
        return null;
    }
    
    @Override
    public Object getWindow(final Object o) {
        return null;
    }
    
    @Override
    public int getWindowId(final Object o) {
        return 0;
    }
    
    @Override
    public boolean isAccessibilityFocused(final Object o) {
        return false;
    }
    
    @Override
    public boolean isCheckable(final Object o) {
        return false;
    }
    
    @Override
    public boolean isChecked(final Object o) {
        return false;
    }
    
    @Override
    public boolean isClickable(final Object o) {
        return false;
    }
    
    @Override
    public boolean isCollectionInfoHierarchical(final Object o) {
        return false;
    }
    
    @Override
    public boolean isCollectionItemHeading(final Object o) {
        return false;
    }
    
    @Override
    public boolean isCollectionItemSelected(final Object o) {
        return false;
    }
    
    @Override
    public boolean isContentInvalid(final Object o) {
        return false;
    }
    
    @Override
    public boolean isContextClickable(final Object o) {
        return false;
    }
    
    @Override
    public boolean isDismissable(final Object o) {
        return false;
    }
    
    @Override
    public boolean isEditable(final Object o) {
        return false;
    }
    
    @Override
    public boolean isEnabled(final Object o) {
        return false;
    }
    
    @Override
    public boolean isFocusable(final Object o) {
        return false;
    }
    
    @Override
    public boolean isFocused(final Object o) {
        return false;
    }
    
    @Override
    public boolean isImportantForAccessibility(final Object o) {
        return true;
    }
    
    @Override
    public boolean isLongClickable(final Object o) {
        return false;
    }
    
    @Override
    public boolean isMultiLine(final Object o) {
        return false;
    }
    
    @Override
    public boolean isPassword(final Object o) {
        return false;
    }
    
    @Override
    public boolean isScrollable(final Object o) {
        return false;
    }
    
    @Override
    public boolean isSelected(final Object o) {
        return false;
    }
    
    @Override
    public boolean isVisibleToUser(final Object o) {
        return false;
    }
    
    @Override
    public Object newAccessibilityAction(final int n, final CharSequence charSequence) {
        return null;
    }
    
    @Override
    public Object obtain() {
        return null;
    }
    
    @Override
    public Object obtain(final View view) {
        return null;
    }
    
    @Override
    public Object obtain(final View view, final int n) {
        return null;
    }
    
    @Override
    public Object obtain(final Object o) {
        return null;
    }
    
    @Override
    public Object obtainCollectionInfo(final int n, final int n2, final boolean b) {
        return null;
    }
    
    @Override
    public Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
        return null;
    }
    
    @Override
    public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b) {
        return null;
    }
    
    @Override
    public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        return null;
    }
    
    @Override
    public Object obtainRangeInfo(final int n, final float n2, final float n3, final float n4) {
        return null;
    }
    
    @Override
    public boolean performAction(final Object o, final int n) {
        return false;
    }
    
    @Override
    public boolean performAction(final Object o, final int n, final Bundle bundle) {
        return false;
    }
    
    @Override
    public void recycle(final Object o) {
    }
    
    @Override
    public boolean refresh(final Object o) {
        return false;
    }
    
    @Override
    public boolean removeAction(final Object o, final Object o2) {
        return false;
    }
    
    @Override
    public boolean removeChild(final Object o, final View view) {
        return false;
    }
    
    @Override
    public boolean removeChild(final Object o, final View view, final int n) {
        return false;
    }
    
    @Override
    public void setAccessibilityFocused(final Object o, final boolean b) {
    }
    
    @Override
    public void setBoundsInParent(final Object o, final Rect rect) {
    }
    
    @Override
    public void setBoundsInScreen(final Object o, final Rect rect) {
    }
    
    @Override
    public void setCanOpenPopup(final Object o, final boolean b) {
    }
    
    @Override
    public void setCheckable(final Object o, final boolean b) {
    }
    
    @Override
    public void setChecked(final Object o, final boolean b) {
    }
    
    @Override
    public void setClassName(final Object o, final CharSequence charSequence) {
    }
    
    @Override
    public void setClickable(final Object o, final boolean b) {
    }
    
    @Override
    public void setCollectionInfo(final Object o, final Object o2) {
    }
    
    @Override
    public void setCollectionItemInfo(final Object o, final Object o2) {
    }
    
    @Override
    public void setContentDescription(final Object o, final CharSequence charSequence) {
    }
    
    @Override
    public void setContentInvalid(final Object o, final boolean b) {
    }
    
    @Override
    public void setContextClickable(final Object o, final boolean b) {
    }
    
    @Override
    public void setDismissable(final Object o, final boolean b) {
    }
    
    @Override
    public void setDrawingOrder(final Object o, final int n) {
    }
    
    @Override
    public void setEditable(final Object o, final boolean b) {
    }
    
    @Override
    public void setEnabled(final Object o, final boolean b) {
    }
    
    @Override
    public void setError(final Object o, final CharSequence charSequence) {
    }
    
    @Override
    public void setFocusable(final Object o, final boolean b) {
    }
    
    @Override
    public void setFocused(final Object o, final boolean b) {
    }
    
    @Override
    public void setImportantForAccessibility(final Object o, final boolean b) {
    }
    
    @Override
    public void setInputType(final Object o, final int n) {
    }
    
    @Override
    public void setLabelFor(final Object o, final View view) {
    }
    
    @Override
    public void setLabelFor(final Object o, final View view, final int n) {
    }
    
    @Override
    public void setLabeledBy(final Object o, final View view) {
    }
    
    @Override
    public void setLabeledBy(final Object o, final View view, final int n) {
    }
    
    @Override
    public void setLiveRegion(final Object o, final int n) {
    }
    
    @Override
    public void setLongClickable(final Object o, final boolean b) {
    }
    
    @Override
    public void setMaxTextLength(final Object o, final int n) {
    }
    
    @Override
    public void setMovementGranularities(final Object o, final int n) {
    }
    
    @Override
    public void setMultiLine(final Object o, final boolean b) {
    }
    
    @Override
    public void setPackageName(final Object o, final CharSequence charSequence) {
    }
    
    @Override
    public void setParent(final Object o, final View view) {
    }
    
    @Override
    public void setParent(final Object o, final View view, final int n) {
    }
    
    @Override
    public void setPassword(final Object o, final boolean b) {
    }
    
    @Override
    public void setRangeInfo(final Object o, final Object o2) {
    }
    
    @Override
    public void setRoleDescription(final Object o, final CharSequence charSequence) {
    }
    
    @Override
    public void setScrollable(final Object o, final boolean b) {
    }
    
    @Override
    public void setSelected(final Object o, final boolean b) {
    }
    
    @Override
    public void setSource(final Object o, final View view) {
    }
    
    @Override
    public void setSource(final Object o, final View view, final int n) {
    }
    
    @Override
    public void setText(final Object o, final CharSequence charSequence) {
    }
    
    @Override
    public void setTextSelection(final Object o, final int n, final int n2) {
    }
    
    @Override
    public void setTraversalAfter(final Object o, final View view) {
    }
    
    @Override
    public void setTraversalAfter(final Object o, final View view, final int n) {
    }
    
    @Override
    public void setTraversalBefore(final Object o, final View view) {
    }
    
    @Override
    public void setTraversalBefore(final Object o, final View view, final int n) {
    }
    
    @Override
    public void setViewIdResourceName(final Object o, final String s) {
    }
    
    @Override
    public void setVisibleToUser(final Object o, final boolean b) {
    }
}
