// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.view.View;

interface AccessibilityNodeInfoCompat$AccessibilityNodeInfoImpl
{
    void addAction(final Object p0, final int p1);
    
    void addChild(final Object p0, final View p1);
    
    int getActions(final Object p0);
    
    void getBoundsInParent(final Object p0, final Rect p1);
    
    void getBoundsInScreen(final Object p0, final Rect p1);
    
    CharSequence getClassName(final Object p0);
    
    CharSequence getContentDescription(final Object p0);
    
    CharSequence getPackageName(final Object p0);
    
    CharSequence getText(final Object p0);
    
    String getViewIdResourceName(final Object p0);
    
    boolean isAccessibilityFocused(final Object p0);
    
    boolean isCheckable(final Object p0);
    
    boolean isChecked(final Object p0);
    
    boolean isClickable(final Object p0);
    
    boolean isEnabled(final Object p0);
    
    boolean isFocusable(final Object p0);
    
    boolean isFocused(final Object p0);
    
    boolean isLongClickable(final Object p0);
    
    boolean isPassword(final Object p0);
    
    boolean isScrollable(final Object p0);
    
    boolean isSelected(final Object p0);
    
    boolean isVisibleToUser(final Object p0);
    
    Object obtain(final Object p0);
    
    Object obtainCollectionInfo(final int p0, final int p1, final boolean p2, final int p3);
    
    Object obtainCollectionItemInfo(final int p0, final int p1, final int p2, final int p3, final boolean p4, final boolean p5);
    
    void recycle(final Object p0);
    
    void setAccessibilityFocused(final Object p0, final boolean p1);
    
    void setBoundsInParent(final Object p0, final Rect p1);
    
    void setBoundsInScreen(final Object p0, final Rect p1);
    
    void setClassName(final Object p0, final CharSequence p1);
    
    void setClickable(final Object p0, final boolean p1);
    
    void setCollectionInfo(final Object p0, final Object p1);
    
    void setCollectionItemInfo(final Object p0, final Object p1);
    
    void setContentDescription(final Object p0, final CharSequence p1);
    
    void setEnabled(final Object p0, final boolean p1);
    
    void setFocusable(final Object p0, final boolean p1);
    
    void setFocused(final Object p0, final boolean p1);
    
    void setLongClickable(final Object p0, final boolean p1);
    
    void setPackageName(final Object p0, final CharSequence p1);
    
    void setParent(final Object p0, final View p1);
    
    void setScrollable(final Object p0, final boolean p1);
    
    void setSelected(final Object p0, final boolean p1);
    
    void setSource(final Object p0, final View p1);
    
    void setVisibleToUser(final Object p0, final boolean p1);
}
