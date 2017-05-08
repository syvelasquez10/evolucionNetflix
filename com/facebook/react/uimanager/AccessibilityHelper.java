// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.View;
import android.view.View$AccessibilityDelegate;

class AccessibilityHelper
{
    private static final View$AccessibilityDelegate BUTTON_DELEGATE;
    private static final View$AccessibilityDelegate RADIOBUTTON_CHECKED_DELEGATE;
    private static final View$AccessibilityDelegate RADIOBUTTON_UNCHECKED_DELEGATE;
    
    static {
        BUTTON_DELEGATE = new AccessibilityHelper$1();
        RADIOBUTTON_CHECKED_DELEGATE = new AccessibilityHelper$2();
        RADIOBUTTON_UNCHECKED_DELEGATE = new AccessibilityHelper$3();
    }
    
    public static void sendAccessibilityEvent(final View view, final int n) {
        view.sendAccessibilityEvent(n);
    }
    
    public static void updateAccessibilityComponentType(final View view, final String s) {
        if (s == null) {
            view.setAccessibilityDelegate((View$AccessibilityDelegate)null);
            return;
        }
        switch (s) {
            default: {
                view.setAccessibilityDelegate((View$AccessibilityDelegate)null);
            }
            case "button": {
                view.setAccessibilityDelegate(AccessibilityHelper.BUTTON_DELEGATE);
            }
            case "radiobutton_checked": {
                view.setAccessibilityDelegate(AccessibilityHelper.RADIOBUTTON_CHECKED_DELEGATE);
            }
            case "radiobutton_unchecked": {
                view.setAccessibilityDelegate(AccessibilityHelper.RADIOBUTTON_UNCHECKED_DELEGATE);
            }
        }
    }
}
