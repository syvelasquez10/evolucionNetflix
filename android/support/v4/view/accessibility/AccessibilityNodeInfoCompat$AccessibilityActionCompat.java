// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.graphics.Rect;
import android.view.View;
import android.os.Build$VERSION;

public class AccessibilityNodeInfoCompat$AccessibilityActionCompat
{
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_CLEAR_FOCUS;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_CLEAR_SELECTION;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_CLICK;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_COLLAPSE;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_COPY;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_CUT;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_DISMISS;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_EXPAND;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_FOCUS;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_LONG_CLICK;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_PASTE;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_SCROLL_BACKWARD;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_SCROLL_FORWARD;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_SELECT;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_SET_SELECTION;
    public static final AccessibilityNodeInfoCompat$AccessibilityActionCompat ACTION_SET_TEXT;
    private final Object mAction;
    
    static {
        ACTION_FOCUS = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(1, null);
        ACTION_CLEAR_FOCUS = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(2, null);
        ACTION_SELECT = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(4, null);
        ACTION_CLEAR_SELECTION = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(8, null);
        ACTION_CLICK = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(16, null);
        ACTION_LONG_CLICK = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(32, null);
        ACTION_ACCESSIBILITY_FOCUS = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(64, null);
        ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(128, null);
        ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(256, null);
        ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(512, null);
        ACTION_NEXT_HTML_ELEMENT = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(1024, null);
        ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(2048, null);
        ACTION_SCROLL_FORWARD = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(4096, null);
        ACTION_SCROLL_BACKWARD = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(8192, null);
        ACTION_COPY = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(16384, null);
        ACTION_PASTE = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(32768, null);
        ACTION_CUT = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(65536, null);
        ACTION_SET_SELECTION = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(131072, null);
        ACTION_EXPAND = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(262144, null);
        ACTION_COLLAPSE = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(524288, null);
        ACTION_DISMISS = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(1048576, null);
        ACTION_SET_TEXT = new AccessibilityNodeInfoCompat$AccessibilityActionCompat(2097152, null);
    }
    
    public AccessibilityNodeInfoCompat$AccessibilityActionCompat(final int n, final CharSequence charSequence) {
        this(AccessibilityNodeInfoCompat.IMPL.newAccessibilityAction(n, charSequence));
    }
    
    private AccessibilityNodeInfoCompat$AccessibilityActionCompat(final Object mAction) {
        this.mAction = mAction;
    }
}
