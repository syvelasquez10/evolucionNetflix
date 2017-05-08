// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.os.Build$VERSION;
import java.lang.ref.WeakReference;
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
        Runnable runnable;
        if (this.mStarterMap != null) {
            runnable = this.mStarterMap.get(view);
        }
        else {
            runnable = null;
        }
        Runnable runnable2 = runnable;
        if (runnable == null) {
            runnable2 = new ViewPropertyAnimatorCompat$BaseViewPropertyAnimatorCompatImpl$Starter(this, viewPropertyAnimatorCompat, view, null);
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
    
    private void startAnimation(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        final Object tag = view.getTag(2113929216);
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        if (tag instanceof ViewPropertyAnimatorListener) {
            viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
        }
        else {
            viewPropertyAnimatorListener = null;
        }
        final Runnable access$100 = viewPropertyAnimatorCompat.mStartAction;
        final Runnable access$101 = viewPropertyAnimatorCompat.mEndAction;
        if (access$100 != null) {
            access$100.run();
        }
        if (viewPropertyAnimatorListener != null) {
            viewPropertyAnimatorListener.onAnimationStart(view);
            viewPropertyAnimatorListener.onAnimationEnd(view);
        }
        if (access$101 != null) {
            access$101.run();
        }
        if (this.mStarterMap != null) {
            this.mStarterMap.remove(view);
        }
    }
    
    @Override
    public void alpha(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
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
    public void scaleX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void scaleY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
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
    
    @Override
    public void translationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
    
    @Override
    public void translationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        this.postStartMessage(viewPropertyAnimatorCompat, view);
    }
}
