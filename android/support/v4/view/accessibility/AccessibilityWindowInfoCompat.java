// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build$VERSION;

public class AccessibilityWindowInfoCompat
{
    private static final AccessibilityWindowInfoCompat$AccessibilityWindowInfoImpl IMPL;
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private static final int UNDEFINED = -1;
    private Object mInfo;
    
    static {
        if (Build$VERSION.SDK_INT >= 24) {
            IMPL = new AccessibilityWindowInfoCompat$AccessibilityWindowInfoApi24Impl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = new AccessibilityWindowInfoCompat$AccessibilityWindowInfoApi21Impl();
            return;
        }
        IMPL = new AccessibilityWindowInfoCompat$AccessibilityWindowInfoStubImpl();
    }
    
    private AccessibilityWindowInfoCompat(final Object mInfo) {
        this.mInfo = mInfo;
    }
    
    public static AccessibilityWindowInfoCompat obtain() {
        return wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.obtain());
    }
    
    public static AccessibilityWindowInfoCompat obtain(final AccessibilityWindowInfoCompat accessibilityWindowInfoCompat) {
        if (accessibilityWindowInfoCompat == null) {
            return null;
        }
        return wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.obtain(accessibilityWindowInfoCompat.mInfo));
    }
    
    private static String typeToString(final int n) {
        switch (n) {
            default: {
                return "<UNKNOWN>";
            }
            case 1: {
                return "TYPE_APPLICATION";
            }
            case 2: {
                return "TYPE_INPUT_METHOD";
            }
            case 3: {
                return "TYPE_SYSTEM";
            }
            case 4: {
                return "TYPE_ACCESSIBILITY_OVERLAY";
            }
        }
    }
    
    static AccessibilityWindowInfoCompat wrapNonNullInstance(final Object o) {
        if (o != null) {
            return new AccessibilityWindowInfoCompat(o);
        }
        return null;
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
            final AccessibilityWindowInfoCompat accessibilityWindowInfoCompat = (AccessibilityWindowInfoCompat)o;
            if (this.mInfo == null) {
                if (accessibilityWindowInfoCompat.mInfo != null) {
                    return false;
                }
            }
            else if (!this.mInfo.equals(accessibilityWindowInfoCompat.mInfo)) {
                return false;
            }
        }
        return true;
    }
    
    public AccessibilityNodeInfoCompat getAnchor() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.getAnchor(this.mInfo));
    }
    
    public void getBoundsInScreen(final Rect rect) {
        AccessibilityWindowInfoCompat.IMPL.getBoundsInScreen(this.mInfo, rect);
    }
    
    public AccessibilityWindowInfoCompat getChild(final int n) {
        return wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.getChild(this.mInfo, n));
    }
    
    public int getChildCount() {
        return AccessibilityWindowInfoCompat.IMPL.getChildCount(this.mInfo);
    }
    
    public int getId() {
        return AccessibilityWindowInfoCompat.IMPL.getId(this.mInfo);
    }
    
    public int getLayer() {
        return AccessibilityWindowInfoCompat.IMPL.getLayer(this.mInfo);
    }
    
    public AccessibilityWindowInfoCompat getParent() {
        return wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.getParent(this.mInfo));
    }
    
    public AccessibilityNodeInfoCompat getRoot() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.getRoot(this.mInfo));
    }
    
    public CharSequence getTitle() {
        return AccessibilityWindowInfoCompat.IMPL.getTitle(this.mInfo);
    }
    
    public int getType() {
        return AccessibilityWindowInfoCompat.IMPL.getType(this.mInfo);
    }
    
    @Override
    public int hashCode() {
        if (this.mInfo == null) {
            return 0;
        }
        return this.mInfo.hashCode();
    }
    
    public boolean isAccessibilityFocused() {
        return AccessibilityWindowInfoCompat.IMPL.isAccessibilityFocused(this.mInfo);
    }
    
    public boolean isActive() {
        return AccessibilityWindowInfoCompat.IMPL.isActive(this.mInfo);
    }
    
    public boolean isFocused() {
        return AccessibilityWindowInfoCompat.IMPL.isFocused(this.mInfo);
    }
    
    public void recycle() {
        AccessibilityWindowInfoCompat.IMPL.recycle(this.mInfo);
    }
    
    @Override
    public String toString() {
        final boolean b = true;
        final StringBuilder sb = new StringBuilder();
        final Rect rect = new Rect();
        this.getBoundsInScreen(rect);
        sb.append("AccessibilityWindowInfo[");
        sb.append("id=").append(this.getId());
        sb.append(", type=").append(typeToString(this.getType()));
        sb.append(", layer=").append(this.getLayer());
        sb.append(", bounds=").append(rect);
        sb.append(", focused=").append(this.isFocused());
        sb.append(", active=").append(this.isActive());
        sb.append(", hasParent=").append(this.getParent() != null);
        sb.append(", hasChildren=").append(this.getChildCount() > 0 && b);
        sb.append(']');
        return sb.toString();
    }
}
