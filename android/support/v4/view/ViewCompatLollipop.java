// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View$OnApplyWindowInsetsListener;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.view.WindowInsets;
import android.view.View;

class ViewCompatLollipop
{
    public static WindowInsetsCompat dispatchApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = windowInsetsCompat;
        if (windowInsetsCompat instanceof WindowInsetsCompatApi21) {
            final WindowInsets unwrap = ((WindowInsetsCompatApi21)windowInsetsCompat).unwrap();
            final WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(unwrap);
            windowInsetsCompat2 = windowInsetsCompat;
            if (dispatchApplyWindowInsets != unwrap) {
                windowInsetsCompat2 = new WindowInsetsCompatApi21(dispatchApplyWindowInsets);
            }
        }
        return windowInsetsCompat2;
    }
    
    public static float getElevation(final View view) {
        return view.getElevation();
    }
    
    public static float getTranslationZ(final View view) {
        return view.getTranslationZ();
    }
    
    public static float getZ(final View view) {
        return view.getZ();
    }
    
    public static WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = windowInsetsCompat;
        if (windowInsetsCompat instanceof WindowInsetsCompatApi21) {
            final WindowInsets unwrap = ((WindowInsetsCompatApi21)windowInsetsCompat).unwrap();
            final WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(unwrap);
            windowInsetsCompat2 = windowInsetsCompat;
            if (onApplyWindowInsets != unwrap) {
                windowInsetsCompat2 = new WindowInsetsCompatApi21(onApplyWindowInsets);
            }
        }
        return windowInsetsCompat2;
    }
    
    public static void requestApplyInsets(final View view) {
        view.requestApplyInsets();
    }
    
    static void setBackgroundTintList(final View view, final ColorStateList backgroundTintList) {
        view.setBackgroundTintList(backgroundTintList);
    }
    
    static void setBackgroundTintMode(final View view, final PorterDuff$Mode backgroundTintMode) {
        view.setBackgroundTintMode(backgroundTintMode);
    }
    
    public static void setElevation(final View view, final float elevation) {
        view.setElevation(elevation);
    }
    
    public static void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        view.setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)new ViewCompatLollipop$1(onApplyWindowInsetsListener));
    }
}
