// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.os.Build$VERSION;

public class AccessibilityEventCompat
{
    private static final AccessibilityEventCompat$AccessibilityEventVersionImpl IMPL;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_GESTURE_DETECTION_END = 524288;
    public static final int TYPE_GESTURE_DETECTION_START = 262144;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 2097152;
    public static final int TYPE_TOUCH_INTERACTION_START = 1048576;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 65536;
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    public static final int TYPE_VIEW_SCROLLED = 4096;
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 131072;
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new AccessibilityEventCompat$AccessibilityEventIcsImpl();
            return;
        }
        IMPL = new AccessibilityEventCompat$AccessibilityEventStubImpl();
    }
    
    public static void appendRecord(final AccessibilityEvent accessibilityEvent, final AccessibilityRecordCompat accessibilityRecordCompat) {
        AccessibilityEventCompat.IMPL.appendRecord(accessibilityEvent, accessibilityRecordCompat.getImpl());
    }
    
    public static AccessibilityRecordCompat asRecord(final AccessibilityEvent accessibilityEvent) {
        return new AccessibilityRecordCompat(accessibilityEvent);
    }
    
    public static AccessibilityRecordCompat getRecord(final AccessibilityEvent accessibilityEvent, final int n) {
        return new AccessibilityRecordCompat(AccessibilityEventCompat.IMPL.getRecord(accessibilityEvent, n));
    }
    
    public static int getRecordCount(final AccessibilityEvent accessibilityEvent) {
        return AccessibilityEventCompat.IMPL.getRecordCount(accessibilityEvent);
    }
}
