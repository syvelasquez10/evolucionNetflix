// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.animation.Interpolator;
import android.os.Build$VERSION;
import android.view.View;
import java.lang.ref.WeakReference;

public class ViewPropertyAnimatorCompat
{
    static final ViewPropertyAnimatorCompat$ViewPropertyAnimatorCompatImpl IMPL;
    private Runnable mEndAction;
    private int mOldLayerType;
    private Runnable mStartAction;
    private WeakReference<View> mView;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = new ViewPropertyAnimatorCompat$LollipopViewPropertyAnimatorCompatImpl();
            return;
        }
        if (sdk_INT >= 19) {
            IMPL = new ViewPropertyAnimatorCompat$KitKatViewPropertyAnimatorCompatImpl();
            return;
        }
        if (sdk_INT >= 18) {
            IMPL = new ViewPropertyAnimatorCompat$JBMr2ViewPropertyAnimatorCompatImpl();
            return;
        }
        if (sdk_INT >= 16) {
            IMPL = new ViewPropertyAnimatorCompat$JBViewPropertyAnimatorCompatImpl();
            return;
        }
        if (sdk_INT >= 14) {
            IMPL = new ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl();
            return;
        }
        IMPL = new ViewPropertyAnimatorCompat$BaseViewPropertyAnimatorCompatImpl();
    }
    
    ViewPropertyAnimatorCompat(final View view) {
        this.mStartAction = null;
        this.mEndAction = null;
        this.mOldLayerType = -1;
        this.mView = new WeakReference<View>(view);
    }
    
    public ViewPropertyAnimatorCompat alpha(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.alpha(this, view, n);
        }
        return this;
    }
    
    public void cancel() {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.cancel(this, view);
        }
    }
    
    public long getDuration() {
        final View view = this.mView.get();
        if (view != null) {
            return ViewPropertyAnimatorCompat.IMPL.getDuration(this, view);
        }
        return 0L;
    }
    
    public ViewPropertyAnimatorCompat setDuration(final long n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setDuration(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setInterpolator(final Interpolator interpolator) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setInterpolator(this, view, interpolator);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setListener(final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setListener(this, view, viewPropertyAnimatorListener);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setStartDelay(final long n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setStartDelay(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setUpdateListener(this, view, viewPropertyAnimatorUpdateListener);
        }
        return this;
    }
    
    public void start() {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.start(this, view);
        }
    }
    
    public ViewPropertyAnimatorCompat translationX(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationX(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationY(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationY(this, view, n);
        }
        return this;
    }
}
