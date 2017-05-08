// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.animation.Interpolator;
import android.view.View;
import java.util.WeakHashMap;

class ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl extends ViewPropertyAnimatorCompat$BaseViewPropertyAnimatorCompatImpl
{
    WeakHashMap<View, Integer> mLayerMap;
    
    ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl() {
        this.mLayerMap = null;
    }
    
    @Override
    public void alpha(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.alpha(view, n);
    }
    
    @Override
    public void cancel(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        ViewPropertyAnimatorCompatICS.cancel(view);
    }
    
    @Override
    public long getDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return ViewPropertyAnimatorCompatICS.getDuration(view);
    }
    
    @Override
    public void scaleX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.scaleX(view, n);
    }
    
    @Override
    public void scaleY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.scaleY(view, n);
    }
    
    @Override
    public void setDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
        ViewPropertyAnimatorCompatICS.setDuration(view, n);
    }
    
    @Override
    public void setInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Interpolator interpolator) {
        ViewPropertyAnimatorCompatICS.setInterpolator(view, interpolator);
    }
    
    @Override
    public void setListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setTag(2113929216, (Object)viewPropertyAnimatorListener);
        ViewPropertyAnimatorCompatICS.setListener(view, new ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl$MyVpaListener(viewPropertyAnimatorCompat));
    }
    
    @Override
    public void setStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
        ViewPropertyAnimatorCompatICS.setStartDelay(view, n);
    }
    
    @Override
    public void start(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        ViewPropertyAnimatorCompatICS.start(view);
    }
    
    @Override
    public void translationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.translationX(view, n);
    }
    
    @Override
    public void translationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.translationY(view, n);
    }
}
