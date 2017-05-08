// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;

class ViewPropertyAnimatorCompat$LollipopViewPropertyAnimatorCompatImpl extends ViewPropertyAnimatorCompat$KitKatViewPropertyAnimatorCompatImpl
{
    @Override
    public void translationZ(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatLollipop.translationZ(view, n);
    }
    
    @Override
    public void translationZBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatLollipop.translationZBy(view, n);
    }
    
    @Override
    public void z(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatLollipop.z(view, n);
    }
    
    @Override
    public void zBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatLollipop.zBy(view, n);
    }
}
