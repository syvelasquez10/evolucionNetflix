// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.view.View;

class ViewCompat$LollipopViewCompatImpl extends ViewCompat$KitKatViewCompatImpl
{
    @Override
    public WindowInsetsCompat dispatchApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return ViewCompatLollipop.dispatchApplyWindowInsets(view, windowInsetsCompat);
    }
    
    @Override
    public float getElevation(final View view) {
        return ViewCompatLollipop.getElevation(view);
    }
    
    @Override
    public float getTranslationZ(final View view) {
        return ViewCompatLollipop.getTranslationZ(view);
    }
    
    @Override
    public float getZ(final View view) {
        return ViewCompatLollipop.getZ(view);
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return ViewCompatLollipop.onApplyWindowInsets(view, windowInsetsCompat);
    }
    
    @Override
    public void requestApplyInsets(final View view) {
        ViewCompatLollipop.requestApplyInsets(view);
    }
    
    @Override
    public void setBackgroundTintList(final View view, final ColorStateList list) {
        ViewCompatLollipop.setBackgroundTintList(view, list);
    }
    
    @Override
    public void setBackgroundTintMode(final View view, final PorterDuff$Mode porterDuff$Mode) {
        ViewCompatLollipop.setBackgroundTintMode(view, porterDuff$Mode);
    }
    
    @Override
    public void setElevation(final View view, final float n) {
        ViewCompatLollipop.setElevation(view, n);
    }
    
    @Override
    public void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        ViewCompatLollipop.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
    }
    
    @Override
    public void stopNestedScroll(final View view) {
        ViewCompatLollipop.stopNestedScroll(view);
    }
}
