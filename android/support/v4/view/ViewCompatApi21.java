// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View$OnApplyWindowInsetsListener;
import android.view.View;

class ViewCompatApi21
{
    public static void requestApplyInsets(final View view) {
        view.requestApplyInsets();
    }
    
    public static void setElevation(final View view, final float elevation) {
        view.setElevation(elevation);
    }
    
    public static void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        view.setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)new ViewCompatApi21$1(onApplyWindowInsetsListener));
    }
}
