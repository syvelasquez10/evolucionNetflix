// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.view.ViewParent;

class ViewParentCompat$ViewParentCompatStubImpl implements ViewParentCompat$ViewParentCompatImpl
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
