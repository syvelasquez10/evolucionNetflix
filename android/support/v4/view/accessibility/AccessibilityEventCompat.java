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
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new AccessibilityEventCompat$AccessibilityEventIcsImpl();
            return;
        }
        IMPL = new AccessibilityEventCompat$AccessibilityEventStubImpl();
    }
    
    public static AccessibilityRecordCompat asRecord(final AccessibilityEvent accessibilityEvent) {
        return new AccessibilityRecordCompat(accessibilityEvent);
    }
}
