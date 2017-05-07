// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.animation.Animation;
import android.view.View;
import android.view.animation.Animation$AnimationListener;

class FragmentManagerImpl$AnimateOnHWLayerIfNeededListener implements Animation$AnimationListener
{
    private boolean mShouldRunOnHWLayer;
    private View mView;
    
    public FragmentManagerImpl$AnimateOnHWLayerIfNeededListener(final View mView, final Animation animation) {
        this.mShouldRunOnHWLayer = false;
        if (mView == null || animation == null) {
            return;
        }
        this.mView = mView;
    }
    
    public void onAnimationEnd(final Animation animation) {
        if (this.mShouldRunOnHWLayer) {
            this.mView.post((Runnable)new FragmentManagerImpl$AnimateOnHWLayerIfNeededListener$2(this));
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
        this.mShouldRunOnHWLayer = FragmentManagerImpl.shouldRunOnHWLayer(this.mView, animation);
        if (this.mShouldRunOnHWLayer) {
            this.mView.post((Runnable)new FragmentManagerImpl$AnimateOnHWLayerIfNeededListener$1(this));
        }
    }
}
