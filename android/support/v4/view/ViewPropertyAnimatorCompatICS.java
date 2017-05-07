// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;
import android.view.View;

class ViewPropertyAnimatorCompatICS
{
    public static void alpha(final View view, final float n) {
        view.animate().alpha(n);
    }
    
    public static void cancel(final View view) {
        view.animate().cancel();
    }
    
    public static void scaleY(final View view, final float n) {
        view.animate().scaleY(n);
    }
    
    public static void setDuration(final View view, final long duration) {
        view.animate().setDuration(duration);
    }
    
    public static void setInterpolator(final View view, final Interpolator interpolator) {
        view.animate().setInterpolator((TimeInterpolator)interpolator);
    }
    
    public static void setListener(final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener((Animator$AnimatorListener)new ViewPropertyAnimatorCompatICS$1(viewPropertyAnimatorListener, view));
            return;
        }
        view.animate().setListener((Animator$AnimatorListener)null);
    }
    
    public static void start(final View view) {
        view.animate().start();
    }
    
    public static void translationX(final View view, final float n) {
        view.animate().translationX(n);
    }
    
    public static void translationY(final View view, final float n) {
        view.animate().translationY(n);
    }
}
