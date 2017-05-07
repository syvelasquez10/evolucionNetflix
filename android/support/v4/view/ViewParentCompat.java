// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.view.ViewParent;
import android.os.Build$VERSION;

public class ViewParentCompat
{
    static final ViewParentCompat$ViewParentCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new ViewParentCompat$ViewParentCompatICSImpl();
            return;
        }
        IMPL = new ViewParentCompat$ViewParentCompatStubImpl();
    }
    
    public static boolean requestSendAccessibilityEvent(final ViewParent viewParent, final View view, final AccessibilityEvent accessibilityEvent) {
        return ViewParentCompat.IMPL.requestSendAccessibilityEvent(viewParent, view, accessibilityEvent);
    }
}
