// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.widget.TextView;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.support.design.R$anim;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.os.Handler$Callback;
import android.os.Looper;
import android.view.ViewGroup;
import android.content.Context;
import android.os.Handler;

public class Snackbar
{
    private static final Handler sHandler;
    private final Context mContext;
    private int mDuration;
    private final SnackbarManager$Callback mManagerCallback;
    private final ViewGroup mParent;
    private final Snackbar$SnackbarLayout mView;
    
    static {
        sHandler = new Handler(Looper.getMainLooper(), (Handler$Callback)new Snackbar$1());
    }
    
    Snackbar(final ViewGroup mParent) {
        this.mManagerCallback = new Snackbar$3(this);
        this.mParent = mParent;
        this.mContext = mParent.getContext();
        this.mView = (Snackbar$SnackbarLayout)LayoutInflater.from(this.mContext).inflate(R$layout.layout_snackbar, this.mParent, false);
    }
    
    private void animateViewIn() {
        if (Build$VERSION.SDK_INT >= 14) {
            ViewCompat.setTranslationY((View)this.mView, this.mView.getHeight());
            ViewCompat.animate((View)this.mView).translationY(0.0f).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new Snackbar$6(this)).start();
            return;
        }
        final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.snackbar_in);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new Snackbar$7(this));
        this.mView.startAnimation(loadAnimation);
    }
    
    private void animateViewOut() {
        if (Build$VERSION.SDK_INT >= 14) {
            ViewCompat.animate((View)this.mView).translationY(this.mView.getHeight()).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new Snackbar$8(this)).start();
            return;
        }
        final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.snackbar_out);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new Snackbar$9(this));
        this.mView.startAnimation(loadAnimation);
    }
    
    private static ViewGroup findSuitableParent(View view) {
        ViewGroup viewGroup = null;
        View view2 = view;
        while (!(view2 instanceof CoordinatorLayout)) {
            ViewGroup viewGroup2 = viewGroup;
            if (view2 instanceof FrameLayout) {
                if (view2.getId() == 16908290) {
                    return (ViewGroup)view2;
                }
                viewGroup2 = (ViewGroup)view2;
            }
            if ((view = view2) != null) {
                final ViewParent parent = view2.getParent();
                if (parent instanceof View) {
                    view = (View)parent;
                }
                else {
                    view = null;
                }
            }
            view2 = view;
            viewGroup = viewGroup2;
            if (view == null) {
                return viewGroup2;
            }
        }
        return (ViewGroup)view2;
    }
    
    private boolean isBeingDragged() {
        final ViewGroup$LayoutParams layoutParams = this.mView.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout$LayoutParams) {
            final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)layoutParams).getBehavior();
            if (behavior instanceof SwipeDismissBehavior) {
                return ((SwipeDismissBehavior)behavior).getDragState() != 0;
            }
        }
        return false;
    }
    
    public static Snackbar make(final View view, final int n, final int n2) {
        return make(view, view.getResources().getText(n), n2);
    }
    
    public static Snackbar make(final View view, final CharSequence text, final int duration) {
        final Snackbar snackbar = new Snackbar(findSuitableParent(view));
        snackbar.setText(text);
        snackbar.setDuration(duration);
        return snackbar;
    }
    
    private void onViewHidden() {
        this.mParent.removeView((View)this.mView);
        SnackbarManager.getInstance().onDismissed(this.mManagerCallback);
    }
    
    public void dismiss() {
        SnackbarManager.getInstance().dismiss(this.mManagerCallback);
    }
    
    public View getView() {
        return (View)this.mView;
    }
    
    final void hideView() {
        if (this.mView.getVisibility() != 0 || this.isBeingDragged()) {
            this.onViewHidden();
            return;
        }
        this.animateViewOut();
    }
    
    public Snackbar setAction(final int n, final View$OnClickListener view$OnClickListener) {
        return this.setAction(this.mContext.getText(n), view$OnClickListener);
    }
    
    public Snackbar setAction(final CharSequence text, final View$OnClickListener view$OnClickListener) {
        final TextView actionView = this.mView.getActionView();
        if (TextUtils.isEmpty(text) || view$OnClickListener == null) {
            actionView.setVisibility(8);
            actionView.setOnClickListener((View$OnClickListener)null);
            return this;
        }
        actionView.setVisibility(0);
        actionView.setText(text);
        actionView.setOnClickListener((View$OnClickListener)new Snackbar$2(this, view$OnClickListener));
        return this;
    }
    
    public Snackbar setDuration(final int mDuration) {
        this.mDuration = mDuration;
        return this;
    }
    
    public Snackbar setText(final CharSequence text) {
        this.mView.getMessageView().setText(text);
        return this;
    }
    
    public void show() {
        SnackbarManager.getInstance().show(this.mDuration, this.mManagerCallback);
    }
    
    final void showView() {
        if (this.mView.getParent() == null) {
            final ViewGroup$LayoutParams layoutParams = this.mView.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout$LayoutParams) {
                final Snackbar$Behavior behavior = new Snackbar$Behavior(this);
                behavior.setStartAlphaSwipeDistance(0.1f);
                behavior.setEndAlphaSwipeDistance(0.6f);
                behavior.setSwipeDirection(0);
                behavior.setListener(new Snackbar$4(this));
                ((CoordinatorLayout$LayoutParams)layoutParams).setBehavior(behavior);
            }
            this.mParent.addView((View)this.mView);
        }
        if (ViewCompat.isLaidOut((View)this.mView)) {
            this.animateViewIn();
            return;
        }
        this.mView.setOnLayoutChangeListener(new Snackbar$5(this));
    }
}
