// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.os.Build$VERSION;

public class AccessibilityEventCompat
{
    private static final AccessibilityEventCompat$AccessibilityEventVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = new AccessibilityEventCompat$AccessibilityEventKitKatImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new AccessibilityEventCompat$AccessibilityEventIcsImpl();
            return;
        }
        IMPL = new AccessibilityEventCompat$AccessibilityEventStubImpl();
    }
    
    public static AccessibilityRecordCompat asRecord(final AccessibilityEvent accessibilityEvent) {
        return new AccessibilityRecordCompat(accessibilityEvent);
    }
    
    public static int getContentChangeTypes(final AccessibilityEvent accessibilityEvent) {
        return AccessibilityEventCompat.IMPL.getContentChangeTypes(accessibilityEvent);
    }
    
    public static void setContentChangeTypes(final AccessibilityEvent accessibilityEvent, final int n) {
        AccessibilityEventCompat.IMPL.setContentChangeTypes(accessibilityEvent, n);
    }
}
