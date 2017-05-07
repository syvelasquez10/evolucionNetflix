// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;

class ViewCompat$Api21ViewCompatImpl extends ViewCompat$KitKatViewCompatImpl
{
    @Override
    public float getElevation(final View view) {
        return ViewCompatApi21.getElevation(view);
    }
    
    @Override
    public String getTransitionName(final View view) {
        return ViewCompatApi21.getTransitionName(view);
    }
    
    @Override
    public float getTranslationZ(final View view) {
        return ViewCompatApi21.getTranslationZ(view);
    }
    
    @Override
    public void requestApplyInsets(final View view) {
        ViewCompatApi21.requestApplyInsets(view);
    }
    
    @Override
    public void setElevation(final View view, final float n) {
        ViewCompatApi21.setElevation(view, n);
    }
    
    @Override
    public void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        ViewCompatApi21.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
    }
    
    @Override
    public void setTransitionName(final View view, final String s) {
        ViewCompatApi21.setTransitionName(view, s);
    }
    
    @Override
    public void setTranslationZ(final View view, final float n) {
        ViewCompatApi21.setTranslationZ(view, n);
    }
}
