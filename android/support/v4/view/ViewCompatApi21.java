// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View$OnApplyWindowInsetsListener;
import android.view.View;

class ViewCompatApi21
{
    public static float getElevation(final View view) {
        return view.getElevation();
    }
    
    public static String getTransitionName(final View view) {
        return view.getTransitionName();
    }
    
    public static float getTranslationZ(final View view) {
        return view.getTranslationZ();
    }
    
    public static void requestApplyInsets(final View view) {
        view.requestApplyInsets();
    }
    
    public static void setElevation(final View view, final float elevation) {
        view.setElevation(elevation);
    }
    
    public static void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        view.setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)new ViewCompatApi21$1(onApplyWindowInsetsListener));
    }
    
    public static void setTransitionName(final View view, final String transitionName) {
        view.setTransitionName(transitionName);
    }
    
    public static void setTranslationZ(final View view, final float translationZ) {
        view.setTranslationZ(translationZ);
    }
}
