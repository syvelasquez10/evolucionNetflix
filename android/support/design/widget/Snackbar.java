// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.widget.TextView;
import android.view.ViewGroup$LayoutParams;
import android.content.res.ColorStateList;
import android.widget.Button;
import android.text.TextUtils;
import android.view.View$OnClickListener;
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
import android.view.accessibility.AccessibilityManager;
import android.os.Handler;

public final class Snackbar
{
    static final int ANIMATION_DURATION = 250;
    static final int ANIMATION_FADE_DURATION = 180;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW = 0;
    static final Handler sHandler;
    private final AccessibilityManager mAccessibilityManager;
    private Snackbar$Callback mCallback;
    private final Context mContext;
    private int mDuration;
    final SnackbarManager$Callback mManagerCallback;
    private final ViewGroup mTargetParent;
    final Snackbar$SnackbarLayout mView;
    
    static {
        sHandler = new Handler(Looper.getMainLooper(), (Handler$Callback)new Snackbar$1());
    }
    
    private Snackbar(final ViewGroup mTargetParent) {
        this.mManagerCallback = new Snackbar$3(this);
        this.mTargetParent = mTargetParent;
        ThemeUtils.checkAppCompatTheme(this.mContext = mTargetParent.getContext());
        this.mView = (Snackbar$SnackbarLayout)LayoutInflater.from(this.mContext).inflate(R$layout.design_layout_snackbar, this.mTargetParent, false);
        this.mAccessibilityManager = (AccessibilityManager)this.mContext.getSystemService("accessibility");
    }
    
    private void animateViewOut(final int n) {
        if (Build$VERSION.SDK_INT >= 14) {
            ViewCompat.animate((View)this.mView).translationY(this.mView.getHeight()).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new Snackbar$9(this, n)).start();
            return;
        }
        final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_snackbar_out);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new Snackbar$10(this, n));
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
    
    public static Snackbar make(final View view, final int n, final int n2) {
        return make(view, view.getResources().getText(n), n2);
    }
    
    public static Snackbar make(final View view, final CharSequence text, final int duration) {
        final Snackbar snackbar = new Snackbar(findSuitableParent(view));
        snackbar.setText(text);
        snackbar.setDuration(duration);
        return snackbar;
    }
    
    void animateViewIn() {
        if (Build$VERSION.SDK_INT >= 14) {
            ViewCompat.setTranslationY((View)this.mView, this.mView.getHeight());
            ViewCompat.animate((View)this.mView).translationY(0.0f).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new Snackbar$7(this)).start();
            return;
        }
        final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_snackbar_in);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new Snackbar$8(this));
        this.mView.startAnimation(loadAnimation);
    }
    
    public void dismiss() {
        this.dispatchDismiss(3);
    }
    
    void dispatchDismiss(final int n) {
        SnackbarManager.getInstance().dismiss(this.mManagerCallback, n);
    }
    
    public int getDuration() {
        return this.mDuration;
    }
    
    public View getView() {
        return (View)this.mView;
    }
    
    final void hideView(final int n) {
        if (this.shouldAnimate() && this.mView.getVisibility() == 0) {
            this.animateViewOut(n);
            return;
        }
        this.onViewHidden(n);
    }
    
    public boolean isShown() {
        return SnackbarManager.getInstance().isCurrent(this.mManagerCallback);
    }
    
    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(this.mManagerCallback);
    }
    
    void onViewHidden(final int n) {
        SnackbarManager.getInstance().onDismissed(this.mManagerCallback);
        if (this.mCallback != null) {
            this.mCallback.onDismissed(this, n);
        }
        if (Build$VERSION.SDK_INT < 11) {
            this.mView.setVisibility(8);
        }
        final ViewParent parent = this.mView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView((View)this.mView);
        }
    }
    
    void onViewShown() {
        SnackbarManager.getInstance().onShown(this.mManagerCallback);
        if (this.mCallback != null) {
            this.mCallback.onShown(this);
        }
    }
    
    public Snackbar setAction(final int n, final View$OnClickListener view$OnClickListener) {
        return this.setAction(this.mContext.getText(n), view$OnClickListener);
    }
    
    public Snackbar setAction(final CharSequence text, final View$OnClickListener view$OnClickListener) {
        final Button actionView = this.mView.getActionView();
        if (TextUtils.isEmpty(text) || view$OnClickListener == null) {
            ((TextView)actionView).setVisibility(8);
            ((TextView)actionView).setOnClickListener((View$OnClickListener)null);
            return this;
        }
        ((TextView)actionView).setVisibility(0);
        ((TextView)actionView).setText(text);
        ((TextView)actionView).setOnClickListener((View$OnClickListener)new Snackbar$2(this, view$OnClickListener));
        return this;
    }
    
    public Snackbar setActionTextColor(final int textColor) {
        ((TextView)this.mView.getActionView()).setTextColor(textColor);
        return this;
    }
    
    public Snackbar setActionTextColor(final ColorStateList textColor) {
        ((TextView)this.mView.getActionView()).setTextColor(textColor);
        return this;
    }
    
    public Snackbar setCallback(final Snackbar$Callback mCallback) {
        this.mCallback = mCallback;
        return this;
    }
    
    public Snackbar setDuration(final int mDuration) {
        this.mDuration = mDuration;
        return this;
    }
    
    public Snackbar setText(final int n) {
        return this.setText(this.mContext.getText(n));
    }
    
    public Snackbar setText(final CharSequence text) {
        this.mView.getMessageView().setText(text);
        return this;
    }
    
    boolean shouldAnimate() {
        return !this.mAccessibilityManager.isEnabled();
    }
    
    public void show() {
        SnackbarManager.getInstance().show(this.mDuration, this.mManagerCallback);
    }
    
    final void showView() {
        if (this.mView.getParent() == null) {
            final ViewGroup$LayoutParams layoutParams = this.mView.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout$LayoutParams) {
                final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)layoutParams;
                final Snackbar$Behavior behavior = new Snackbar$Behavior(this);
                behavior.setStartAlphaSwipeDistance(0.1f);
                behavior.setEndAlphaSwipeDistance(0.6f);
                behavior.setSwipeDirection(0);
                behavior.setListener(new Snackbar$4(this));
                coordinatorLayout$LayoutParams.setBehavior(behavior);
                coordinatorLayout$LayoutParams.insetEdge = 80;
            }
            this.mTargetParent.addView((View)this.mView);
        }
        this.mView.setOnAttachStateChangeListener(new Snackbar$5(this));
        if (!ViewCompat.isLaidOut((View)this.mView)) {
            this.mView.setOnLayoutChangeListener(new Snackbar$6(this));
            return;
        }
        if (this.shouldAnimate()) {
            this.animateViewIn();
            return;
        }
        this.onViewShown();
    }
}
