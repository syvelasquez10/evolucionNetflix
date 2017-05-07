// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View$AccessibilityDelegate;
import android.view.View;

class ViewCompatICS
{
    public static boolean canScrollHorizontally(final View view, final int n) {
        return view.canScrollHorizontally(n);
    }
    
    public static boolean canScrollVertically(final View view, final int n) {
        return view.canScrollVertically(n);
    }
    
    public static void setAccessibilityDelegate(final View view, final Object o) {
        view.setAccessibilityDelegate((View$AccessibilityDelegate)o);
    }
}
