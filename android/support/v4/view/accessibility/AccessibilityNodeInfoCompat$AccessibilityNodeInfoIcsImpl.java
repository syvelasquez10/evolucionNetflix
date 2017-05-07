// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import java.util.List;
import android.view.View;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoIcsImpl extends AccessibilityNodeInfoCompat$AccessibilityNodeInfoStubImpl
{
    @Override
    public void addAction(final Object o, final int n) {
        AccessibilityNodeInfoCompatIcs.addAction(o, n);
    }
    
    @Override
    public void addAction(final Object o, final int n, final CharSequence charSequence) {
        if (Integer.bitCount(n) == 1) {
            this.addAction(o, n);
        }
    }
    
    @Override
    public void addChild(final Object o, final View view) {
        AccessibilityNodeInfoCompatIcs.addChild(o, view);
    }
    
    @Override
    public List<Object> findAccessibilityNodeInfosByText(final Object o, final String s) {
        return AccessibilityNodeInfoCompatIcs.findAccessibilityNodeInfosByText(o, s);
    }
    
    @Override
    public int getActions(final Object o) {
        return AccessibilityNodeInfoCompatIcs.getActions(o);
    }
    
    @Override
    public void getBoundsInParent(final Object o, final Rect rect) {
        AccessibilityNodeInfoCompatIcs.getBoundsInParent(o, rect);
    }
    
    @Override
    public void getBoundsInScreen(final Object o, final Rect rect) {
        AccessibilityNodeInfoCompatIcs.getBoundsInScreen(o, rect);
    }
    
    @Override
    public Object getChild(final Object o, final int n) {
        return AccessibilityNodeInfoCompatIcs.getChild(o, n);
    }
    
    @Override
    public int getChildCount(final Object o) {
        return AccessibilityNodeInfoCompatIcs.getChildCount(o);
    }
    
    @Override
    public CharSequence getClassName(final Object o) {
        return AccessibilityNodeInfoCompatIcs.getClassName(o);
    }
    
    @Override
    public CharSequence getContentDescription(final Object o) {
        return AccessibilityNodeInfoCompatIcs.getContentDescription(o);
    }
    
    @Override
    public CharSequence getPackageName(final Object o) {
        return AccessibilityNodeInfoCompatIcs.getPackageName(o);
    }
    
    @Override
    public Object getParent(final Object o) {
        return AccessibilityNodeInfoCompatIcs.getParent(o);
    }
    
    @Override
    public CharSequence getText(final Object o) {
        return AccessibilityNodeInfoCompatIcs.getText(o);
    }
    
    @Override
    public int getWindowId(final Object o) {
        return AccessibilityNodeInfoCompatIcs.getWindowId(o);
    }
    
    @Override
    public boolean isCheckable(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isCheckable(o);
    }
    
    @Override
    public boolean isChecked(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isChecked(o);
    }
    
    @Override
    public boolean isClickable(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isClickable(o);
    }
    
    @Override
    public boolean isEnabled(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isEnabled(o);
    }
    
    @Override
    public boolean isFocusable(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isFocusable(o);
    }
    
    @Override
    public boolean isFocused(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isFocused(o);
    }
    
    @Override
    public boolean isLongClickable(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isLongClickable(o);
    }
    
    @Override
    public boolean isPassword(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isPassword(o);
    }
    
    @Override
    public boolean isScrollable(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isScrollable(o);
    }
    
    @Override
    public boolean isSelected(final Object o) {
        return AccessibilityNodeInfoCompatIcs.isSelected(o);
    }
    
    @Override
    public Object obtain() {
        return AccessibilityNodeInfoCompatIcs.obtain();
    }
    
    @Override
    public Object obtain(final View view) {
        return AccessibilityNodeInfoCompatIcs.obtain(view);
    }
    
    @Override
    public Object obtain(final Object o) {
        return AccessibilityNodeInfoCompatIcs.obtain(o);
    }
    
    @Override
    public boolean performAction(final Object o, final int n) {
        return AccessibilityNodeInfoCompatIcs.performAction(o, n);
    }
    
    @Override
    public void recycle(final Object o) {
        AccessibilityNodeInfoCompatIcs.recycle(o);
    }
    
    @Override
    public void setBoundsInParent(final Object o, final Rect rect) {
        AccessibilityNodeInfoCompatIcs.setBoundsInParent(o, rect);
    }
    
    @Override
    public void setBoundsInScreen(final Object o, final Rect rect) {
        AccessibilityNodeInfoCompatIcs.setBoundsInScreen(o, rect);
    }
    
    @Override
    public void setCheckable(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setCheckable(o, b);
    }
    
    @Override
    public void setChecked(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setChecked(o, b);
    }
    
    @Override
    public void setClassName(final Object o, final CharSequence charSequence) {
        AccessibilityNodeInfoCompatIcs.setClassName(o, charSequence);
    }
    
    @Override
    public void setClickable(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setClickable(o, b);
    }
    
    @Override
    public void setContentDescription(final Object o, final CharSequence charSequence) {
        AccessibilityNodeInfoCompatIcs.setContentDescription(o, charSequence);
    }
    
    @Override
    public void setEnabled(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setEnabled(o, b);
    }
    
    @Override
    public void setFocusable(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setFocusable(o, b);
    }
    
    @Override
    public void setFocused(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setFocused(o, b);
    }
    
    @Override
    public void setLongClickable(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setLongClickable(o, b);
    }
    
    @Override
    public void setPackageName(final Object o, final CharSequence charSequence) {
        AccessibilityNodeInfoCompatIcs.setPackageName(o, charSequence);
    }
    
    @Override
    public void setParent(final Object o, final View view) {
        AccessibilityNodeInfoCompatIcs.setParent(o, view);
    }
    
    @Override
    public void setPassword(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setPassword(o, b);
    }
    
    @Override
    public void setScrollable(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setScrollable(o, b);
    }
    
    @Override
    public void setSelected(final Object o, final boolean b) {
        AccessibilityNodeInfoCompatIcs.setSelected(o, b);
    }
    
    @Override
    public void setSource(final Object o, final View view) {
        AccessibilityNodeInfoCompatIcs.setSource(o, view);
    }
    
    @Override
    public void setText(final Object o, final CharSequence charSequence) {
        AccessibilityNodeInfoCompatIcs.setText(o, charSequence);
    }
}
