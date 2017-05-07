// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.view.View;
import android.os.Build$VERSION;

public class AccessibilityNodeInfoCompat
{
    private static final AccessibilityNodeInfoCompat$AccessibilityNodeInfoImpl IMPL;
    private final Object mInfo;
    
    static {
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoApi21Impl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoKitKatImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 18) {
            IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanMr2Impl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoJellybeanImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoIcsImpl();
            return;
        }
        IMPL = new AccessibilityNodeInfoCompat$AccessibilityNodeInfoStubImpl();
    }
    
    public AccessibilityNodeInfoCompat(final Object mInfo) {
        this.mInfo = mInfo;
    }
    
    private static String getActionSymbolicName(final int n) {
        switch (n) {
            default: {
                return "ACTION_UNKNOWN";
            }
            case 1: {
                return "ACTION_FOCUS";
            }
            case 2: {
                return "ACTION_CLEAR_FOCUS";
            }
            case 4: {
                return "ACTION_SELECT";
            }
            case 8: {
                return "ACTION_CLEAR_SELECTION";
            }
            case 16: {
                return "ACTION_CLICK";
            }
            case 32: {
                return "ACTION_LONG_CLICK";
            }
            case 64: {
                return "ACTION_ACCESSIBILITY_FOCUS";
            }
            case 128: {
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            }
            case 256: {
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            }
            case 512: {
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            }
            case 1024: {
                return "ACTION_NEXT_HTML_ELEMENT";
            }
            case 2048: {
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            }
            case 4096: {
                return "ACTION_SCROLL_FORWARD";
            }
            case 8192: {
                return "ACTION_SCROLL_BACKWARD";
            }
            case 65536: {
                return "ACTION_CUT";
            }
            case 16384: {
                return "ACTION_COPY";
            }
            case 32768: {
                return "ACTION_PASTE";
            }
            case 131072: {
                return "ACTION_SET_SELECTION";
            }
        }
    }
    
    public static AccessibilityNodeInfoCompat obtain(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.obtain(accessibilityNodeInfoCompat.mInfo));
    }
    
    static AccessibilityNodeInfoCompat wrapNonNullInstance(final Object o) {
        if (o != null) {
            return new AccessibilityNodeInfoCompat(o);
        }
        return null;
    }
    
    public void addAction(final int n) {
        AccessibilityNodeInfoCompat.IMPL.addAction(this.mInfo, n);
    }
    
    public void addChild(final View view) {
        AccessibilityNodeInfoCompat.IMPL.addChild(this.mInfo, view);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat)o;
            if (this.mInfo == null) {
                if (accessibilityNodeInfoCompat.mInfo != null) {
                    return false;
                }
            }
            else if (!this.mInfo.equals(accessibilityNodeInfoCompat.mInfo)) {
                return false;
            }
        }
        return true;
    }
    
    public int getActions() {
        return AccessibilityNodeInfoCompat.IMPL.getActions(this.mInfo);
    }
    
    public void getBoundsInParent(final Rect rect) {
        AccessibilityNodeInfoCompat.IMPL.getBoundsInParent(this.mInfo, rect);
    }
    
    public void getBoundsInScreen(final Rect rect) {
        AccessibilityNodeInfoCompat.IMPL.getBoundsInScreen(this.mInfo, rect);
    }
    
    public CharSequence getClassName() {
        return AccessibilityNodeInfoCompat.IMPL.getClassName(this.mInfo);
    }
    
    public CharSequence getContentDescription() {
        return AccessibilityNodeInfoCompat.IMPL.getContentDescription(this.mInfo);
    }
    
    public Object getInfo() {
        return this.mInfo;
    }
    
    public CharSequence getPackageName() {
        return AccessibilityNodeInfoCompat.IMPL.getPackageName(this.mInfo);
    }
    
    public CharSequence getText() {
        return AccessibilityNodeInfoCompat.IMPL.getText(this.mInfo);
    }
    
    public String getViewIdResourceName() {
        return AccessibilityNodeInfoCompat.IMPL.getViewIdResourceName(this.mInfo);
    }
    
    @Override
    public int hashCode() {
        if (this.mInfo == null) {
            return 0;
        }
        return this.mInfo.hashCode();
    }
    
    public boolean isAccessibilityFocused() {
        return AccessibilityNodeInfoCompat.IMPL.isAccessibilityFocused(this.mInfo);
    }
    
    public boolean isCheckable() {
        return AccessibilityNodeInfoCompat.IMPL.isCheckable(this.mInfo);
    }
    
    public boolean isChecked() {
        return AccessibilityNodeInfoCompat.IMPL.isChecked(this.mInfo);
    }
    
    public boolean isClickable() {
        return AccessibilityNodeInfoCompat.IMPL.isClickable(this.mInfo);
    }
    
    public boolean isEnabled() {
        return AccessibilityNodeInfoCompat.IMPL.isEnabled(this.mInfo);
    }
    
    public boolean isFocusable() {
        return AccessibilityNodeInfoCompat.IMPL.isFocusable(this.mInfo);
    }
    
    public boolean isFocused() {
        return AccessibilityNodeInfoCompat.IMPL.isFocused(this.mInfo);
    }
    
    public boolean isLongClickable() {
        return AccessibilityNodeInfoCompat.IMPL.isLongClickable(this.mInfo);
    }
    
    public boolean isPassword() {
        return AccessibilityNodeInfoCompat.IMPL.isPassword(this.mInfo);
    }
    
    public boolean isScrollable() {
        return AccessibilityNodeInfoCompat.IMPL.isScrollable(this.mInfo);
    }
    
    public boolean isSelected() {
        return AccessibilityNodeInfoCompat.IMPL.isSelected(this.mInfo);
    }
    
    public boolean isVisibleToUser() {
        return AccessibilityNodeInfoCompat.IMPL.isVisibleToUser(this.mInfo);
    }
    
    public void recycle() {
        AccessibilityNodeInfoCompat.IMPL.recycle(this.mInfo);
    }
    
    public void setAccessibilityFocused(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setAccessibilityFocused(this.mInfo, b);
    }
    
    public void setBoundsInParent(final Rect rect) {
        AccessibilityNodeInfoCompat.IMPL.setBoundsInParent(this.mInfo, rect);
    }
    
    public void setBoundsInScreen(final Rect rect) {
        AccessibilityNodeInfoCompat.IMPL.setBoundsInScreen(this.mInfo, rect);
    }
    
    public void setClassName(final CharSequence charSequence) {
        AccessibilityNodeInfoCompat.IMPL.setClassName(this.mInfo, charSequence);
    }
    
    public void setClickable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setClickable(this.mInfo, b);
    }
    
    public void setContentDescription(final CharSequence charSequence) {
        AccessibilityNodeInfoCompat.IMPL.setContentDescription(this.mInfo, charSequence);
    }
    
    public void setEnabled(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setEnabled(this.mInfo, b);
    }
    
    public void setFocusable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setFocusable(this.mInfo, b);
    }
    
    public void setFocused(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setFocused(this.mInfo, b);
    }
    
    public void setLongClickable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setLongClickable(this.mInfo, b);
    }
    
    public void setPackageName(final CharSequence charSequence) {
        AccessibilityNodeInfoCompat.IMPL.setPackageName(this.mInfo, charSequence);
    }
    
    public void setParent(final View view) {
        AccessibilityNodeInfoCompat.IMPL.setParent(this.mInfo, view);
    }
    
    public void setScrollable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setScrollable(this.mInfo, b);
    }
    
    public void setSelected(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setSelected(this.mInfo, b);
    }
    
    public void setSource(final View view) {
        AccessibilityNodeInfoCompat.IMPL.setSource(this.mInfo, view);
    }
    
    public void setVisibleToUser(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setVisibleToUser(this.mInfo, b);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        final Rect rect = new Rect();
        this.getBoundsInParent(rect);
        sb.append("; boundsInParent: " + rect);
        this.getBoundsInScreen(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ").append(this.getPackageName());
        sb.append("; className: ").append(this.getClassName());
        sb.append("; text: ").append(this.getText());
        sb.append("; contentDescription: ").append(this.getContentDescription());
        sb.append("; viewId: ").append(this.getViewIdResourceName());
        sb.append("; checkable: ").append(this.isCheckable());
        sb.append("; checked: ").append(this.isChecked());
        sb.append("; focusable: ").append(this.isFocusable());
        sb.append("; focused: ").append(this.isFocused());
        sb.append("; selected: ").append(this.isSelected());
        sb.append("; clickable: ").append(this.isClickable());
        sb.append("; longClickable: ").append(this.isLongClickable());
        sb.append("; enabled: ").append(this.isEnabled());
        sb.append("; password: ").append(this.isPassword());
        sb.append("; scrollable: " + this.isScrollable());
        sb.append("; [");
        int n2;
        for (int i = this.getActions(); i != 0; i = n2) {
            final int n = 1 << Integer.numberOfTrailingZeros(i);
            n2 = (i & ~n);
            sb.append(getActionSymbolicName(n));
            if ((i = n2) != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
