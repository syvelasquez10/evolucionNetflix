// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.animation.Interpolator;
import android.os.Build$VERSION;
import android.view.View;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat
{
    static final ViewPropertyAnimatorCompat$ViewPropertyAnimatorCompatImpl IMPL;
    static final int LISTENER_TAG_ID = 2113929216;
    private static final String TAG = "ViewAnimatorCompat";
    Runnable mEndAction;
    int mOldLayerType;
    Runnable mStartAction;
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
    
    public ViewPropertyAnimatorCompat alphaBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.alphaBy(this, view, n);
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
    
    public Interpolator getInterpolator() {
        final View view = this.mView.get();
        if (view != null) {
            return ViewPropertyAnimatorCompat.IMPL.getInterpolator(this, view);
        }
        return null;
    }
    
    public long getStartDelay() {
        final View view = this.mView.get();
        if (view != null) {
            return ViewPropertyAnimatorCompat.IMPL.getStartDelay(this, view);
        }
        return 0L;
    }
    
    public ViewPropertyAnimatorCompat rotation(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotation(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationX(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationX(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationXBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationXBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationY(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationY(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationYBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationYBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleX(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.scaleX(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleXBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.scaleXBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleY(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.scaleY(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleYBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.scaleYBy(this, view, n);
        }
        return this;
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
    
    public ViewPropertyAnimatorCompat translationXBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationXBy(this, view, n);
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
    
    public ViewPropertyAnimatorCompat translationYBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationYBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationZ(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationZ(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationZBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationZBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat withEndAction(final Runnable runnable) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.withEndAction(this, view, runnable);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat withLayer() {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.withLayer(this, view);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat withStartAction(final Runnable runnable) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.withStartAction(this, view, runnable);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat x(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.x(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat xBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.xBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat y(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.y(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat yBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.yBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat z(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.z(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat zBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.zBy(this, view, n);
        }
        return this;
    }
}
