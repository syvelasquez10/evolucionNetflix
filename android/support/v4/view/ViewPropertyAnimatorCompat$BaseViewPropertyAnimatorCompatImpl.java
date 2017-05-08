// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.animation.Interpolator;
import android.view.View;
import java.util.WeakHashMap;

class ViewPropertyAnimatorCompat$BaseViewPropertyAnimatorCompatImpl implements ViewPropertyAnimatorCompat$ViewPropertyAnimatorCompatImpl
{
    WeakHashMap<View, Runnable> mStarterMap;
    
    ViewPropertyAnimatorCompat$BaseViewPropertyAnimatorCompatImpl() {
        this.mStarterMap = null;
    }
    
    private void postStartMessage(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        Runnable runnable = null;
        if (this.mStarterMap != null) {
            runnable = this.mStarterMap.get(view);
        }
        Runnable runnable2;
        if ((runnable2 = runnable) == null) {
            runnable2 = new ViewPropertyAnimatorCompat$BaseViewPropertyAnimatorCompatImpl$Starter(this, viewPropertyAnimatorCompat, view);
            if (this.mStarterMap == null) {
                this.mStarterMap = new WeakHashMap<View, Runnable>();
            }
            this.mStarterMap.put(view, runnable2);
        }
        view.removeCallbacks(runnable2);
        view.post(runnable2);
    }
    
    private void removeStartMessage(final View view) {
        if (this.mStarterMap != null) {
            final Runnable runnable = this.mStarterMap.get(view);
            if (runnable != null) {
                view.removeCallbacks(runnable);
            }
        }
    }
    
    @Override
    public void alpha(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void alphaBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void cancel(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public long getDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return 0L;
    }
    
    @Override
    public Interpolator getInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return null;
    }
    
    @Override
    public long getStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return 0L;
    }
    
    @Override
    public void rotation(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void rotationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void scaleX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void scaleXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void scaleY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void scaleYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void setDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
    }
    
    @Override
    public void setInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Interpolator interpolator) {
    }
    
    @Override
    public void setListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setTag(2113929216, (Object)viewPropertyAnimatorListener);
    }
    
    @Override
    public void setStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
    }
    
    @Override
    public void setUpdateListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
    }
    
    @Override
    public void start(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        this.removeStartMessage(view);
        this.startAnimation(viewPropertyAnimatorCompat, view);
    }
    
    void startAnimation(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        final Object tag = view.getTag(2113929216);
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        if (tag instanceof ViewPropertyAnimatorListener) {
            viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
        }
        else {
            viewPropertyAnimatorListener = null;
        }
        final Runnable mStartAction = viewPropertyAnimatorCompat.mStartAction;
        final Runnable mEndAction = viewPropertyAnimatorCompat.mEndAction;
        viewPropertyAnimatorCompat.mStartAction = null;
        viewPropertyAnimatorCompat.mEndAction = null;
        if (mStartAction != null) {
            mStartAction.run();
        }
        if (viewPropertyAnimatorListener != null) {
            viewPropertyAnimatorListener.onAnimationStart(view);
            viewPropertyAnimatorListener.onAnimationEnd(view);
        }
        if (mEndAction != null) {
            mEndAction.run();
        }
        if (this.mStarterMap != null) {
            this.mStarterMap.remove(view);
        }
    }
    
    @Override
    public void translationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationZ(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
    }
    
    @Override
    public void translationZBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
    }
    
    @Override
    public void withEndAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable mEndAction) {
        viewPropertyAnimatorCompat.mEndAction = mEndAction;
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void withLayer(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
    }
    
    @Override
    public void withStartAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable mStartAction) {
        viewPropertyAnimatorCompat.mStartAction = mStartAction;
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void x(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void xBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void y(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void yBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void z(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
    }
    
    @Override
    public void zBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
    }
}
