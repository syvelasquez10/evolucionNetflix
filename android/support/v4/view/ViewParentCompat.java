// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.view.ViewParent;
import android.os.Build$VERSION;

public class ViewParentCompat
{
    static final ViewParentCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (ViewParentCompatImpl)new ViewParentCompatICSImpl();
            return;
        }
        IMPL = (ViewParentCompatImpl)new ViewParentCompatStubImpl();
    }
    
    public static boolean requestSendAccessibilityEvent(final ViewParent viewParent, final View view, final AccessibilityEvent accessibilityEvent) {
        return ViewParentCompat.IMPL.requestSendAccessibilityEvent(viewParent, view, accessibilityEvent);
    }
    
    static class ViewParentCompatICSImpl extends ViewParentCompatStubImpl
    {
        @Override
        public boolean requestSendAccessibilityEvent(final ViewParent viewParent, final View view, final AccessibilityEvent accessibilityEvent) {
            return ViewParentCompatICS.requestSendAccessibilityEvent(viewParent, view, accessibilityEvent);
        }
    }
    
    interface ViewParentCompatImpl
    {
        boolean requestSendAccessibilityEvent(final ViewParent p0, final View p1, final AccessibilityEvent p2);
    }
    
    static class ViewParentCompatStubImpl implements ViewParentCompatImpl
    {
        @Override
        public boolean requestSendAccessibilityEvent(final ViewParent viewParent, final View view, final AccessibilityEvent accessibilityEvent) {
            if (view == null) {
                return false;
            }
            ((AccessibilityManager)view.getContext().getSystemService("accessibility")).sendAccessibilityEvent(accessibilityEvent);
            return true;
        }
    }
}
