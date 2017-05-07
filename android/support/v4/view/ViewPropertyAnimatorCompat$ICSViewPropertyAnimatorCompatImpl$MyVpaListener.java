// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.animation.Interpolator;
import android.os.Build$VERSION;
import java.lang.ref.WeakReference;
import android.graphics.Paint;
import android.view.View;

class ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl$MyVpaListener implements ViewPropertyAnimatorListener
{
    ViewPropertyAnimatorCompat mVpa;
    
    ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl$MyVpaListener(final ViewPropertyAnimatorCompat mVpa) {
        this.mVpa = mVpa;
    }
    
    @Override
    public void onAnimationCancel(final View view) {
        final Object tag = view.getTag(2113929216);
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        if (tag instanceof ViewPropertyAnimatorListener) {
            viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
        }
        else {
            viewPropertyAnimatorListener = null;
        }
        if (viewPropertyAnimatorListener != null) {
            viewPropertyAnimatorListener.onAnimationCancel(view);
        }
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        if (this.mVpa.mOldLayerType >= 0) {
            ViewCompat.setLayerType(view, this.mVpa.mOldLayerType, null);
            this.mVpa.mOldLayerType = -1;
        }
        if (this.mVpa.mEndAction != null) {
            this.mVpa.mEndAction.run();
        }
        final Object tag = view.getTag(2113929216);
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        if (tag instanceof ViewPropertyAnimatorListener) {
            viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
        }
        else {
            viewPropertyAnimatorListener = null;
        }
        if (viewPropertyAnimatorListener != null) {
            viewPropertyAnimatorListener.onAnimationEnd(view);
        }
    }
    
    @Override
    public void onAnimationStart(final View view) {
        if (this.mVpa.mOldLayerType >= 0) {
            ViewCompat.setLayerType(view, 2, null);
        }
        if (this.mVpa.mStartAction != null) {
            this.mVpa.mStartAction.run();
        }
        final Object tag = view.getTag(2113929216);
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        if (tag instanceof ViewPropertyAnimatorListener) {
            viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
        }
        else {
            viewPropertyAnimatorListener = null;
        }
        if (viewPropertyAnimatorListener != null) {
            viewPropertyAnimatorListener.onAnimationStart(view);
        }
    }
}
