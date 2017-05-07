// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;

class ViewCompat$Api21ViewCompatImpl extends ViewCompat$KitKatViewCompatImpl
{
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
}
