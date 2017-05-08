// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.graphics.Paint;
import android.support.v4.os.BuildCompat;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation;
import android.view.View;
import android.view.animation.Animation$AnimationListener;

class FragmentManagerImpl$AnimateOnHWLayerIfNeededListener implements Animation$AnimationListener
{
    private Animation$AnimationListener mOriginalListener;
    private boolean mShouldRunOnHWLayer;
    View mView;
    
    public FragmentManagerImpl$AnimateOnHWLayerIfNeededListener(final View mView, final Animation animation) {
        if (mView == null || animation == null) {
            return;
        }
        this.mView = mView;
    }
    
    public FragmentManagerImpl$AnimateOnHWLayerIfNeededListener(final View mView, final Animation animation, final Animation$AnimationListener mOriginalListener) {
        if (mView == null || animation == null) {
            return;
        }
        this.mOriginalListener = mOriginalListener;
        this.mView = mView;
        this.mShouldRunOnHWLayer = true;
    }
    
    public void onAnimationEnd(final Animation animation) {
        if (this.mView != null && this.mShouldRunOnHWLayer) {
            if (ViewCompat.isAttachedToWindow(this.mView) || BuildCompat.isAtLeastN()) {
                this.mView.post((Runnable)new FragmentManagerImpl$AnimateOnHWLayerIfNeededListener$1(this));
            }
            else {
                ViewCompat.setLayerType(this.mView, 0, null);
            }
        }
        if (this.mOriginalListener != null) {
            this.mOriginalListener.onAnimationEnd(animation);
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
        if (this.mOriginalListener != null) {
            this.mOriginalListener.onAnimationRepeat(animation);
        }
    }
    
    public void onAnimationStart(final Animation animation) {
        if (this.mOriginalListener != null) {
            this.mOriginalListener.onAnimationStart(animation);
        }
    }
}
