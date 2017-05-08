// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewGroup$LayoutParams;
import android.view.ViewParent;
import java.util.ArrayList;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.support.design.R$anim;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.os.Build$VERSION;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Handler$Callback;
import android.os.Looper;
import android.view.ViewGroup;
import android.content.Context;
import java.util.List;
import android.view.accessibility.AccessibilityManager;
import android.os.Handler;

public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>>
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
    private List<BaseTransientBottomBar$BaseCallback<B>> mCallbacks;
    private final BaseTransientBottomBar$ContentViewCallback mContentViewCallback;
    private final Context mContext;
    private int mDuration;
    final SnackbarManager$Callback mManagerCallback;
    private final ViewGroup mTargetParent;
    final BaseTransientBottomBar$SnackbarBaseLayout mView;
    
    static {
        sHandler = new Handler(Looper.getMainLooper(), (Handler$Callback)new BaseTransientBottomBar$1());
    }
    
    protected BaseTransientBottomBar(final ViewGroup mTargetParent, final View view, final BaseTransientBottomBar$ContentViewCallback mContentViewCallback) {
        this.mManagerCallback = new BaseTransientBottomBar$3(this);
        if (mTargetParent == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if (mContentViewCallback == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
        this.mTargetParent = mTargetParent;
        this.mContentViewCallback = mContentViewCallback;
        ThemeUtils.checkAppCompatTheme(this.mContext = mTargetParent.getContext());
        (this.mView = (BaseTransientBottomBar$SnackbarBaseLayout)LayoutInflater.from(this.mContext).inflate(R$layout.design_layout_snackbar, this.mTargetParent, false)).addView(view);
        ViewCompat.setAccessibilityLiveRegion((View)this.mView, 1);
        ViewCompat.setImportantForAccessibility((View)this.mView, 1);
        ViewCompat.setFitsSystemWindows((View)this.mView, true);
        ViewCompat.setOnApplyWindowInsetsListener((View)this.mView, new BaseTransientBottomBar$2(this));
        this.mAccessibilityManager = (AccessibilityManager)this.mContext.getSystemService("accessibility");
    }
    
    private void animateViewOut(final int n) {
        if (Build$VERSION.SDK_INT >= 14) {
            ViewCompat.animate((View)this.mView).translationY(this.mView.getHeight()).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new BaseTransientBottomBar$9(this, n)).start();
            return;
        }
        final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_snackbar_out);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new BaseTransientBottomBar$10(this, n));
        this.mView.startAnimation(loadAnimation);
    }
    
    public B addCallback(final BaseTransientBottomBar$BaseCallback<B> baseTransientBottomBar$BaseCallback) {
        if (baseTransientBottomBar$BaseCallback == null) {
            return (B)this;
        }
        if (this.mCallbacks == null) {
            this.mCallbacks = new ArrayList<BaseTransientBottomBar$BaseCallback<B>>();
        }
        this.mCallbacks.add(baseTransientBottomBar$BaseCallback);
        return (B)this;
    }
    
    void animateViewIn() {
        if (Build$VERSION.SDK_INT >= 14) {
            ViewCompat.setTranslationY((View)this.mView, this.mView.getHeight());
            ViewCompat.animate((View)this.mView).translationY(0.0f).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new BaseTransientBottomBar$7(this)).start();
            return;
        }
        final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_snackbar_in);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new BaseTransientBottomBar$8(this));
        this.mView.startAnimation(loadAnimation);
    }
    
    public void dismiss() {
        this.dispatchDismiss(3);
    }
    
    void dispatchDismiss(final int n) {
        SnackbarManager.getInstance().dismiss(this.mManagerCallback, n);
    }
    
    public Context getContext() {
        return this.mContext;
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
        if (this.mCallbacks != null) {
            for (int i = this.mCallbacks.size() - 1; i >= 0; --i) {
                this.mCallbacks.get(i).onDismissed((B)this, n);
            }
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
        if (this.mCallbacks != null) {
            for (int i = this.mCallbacks.size() - 1; i >= 0; --i) {
                this.mCallbacks.get(i).onShown((B)this);
            }
        }
    }
    
    public B removeCallback(final BaseTransientBottomBar$BaseCallback<B> baseTransientBottomBar$BaseCallback) {
        if (baseTransientBottomBar$BaseCallback != null && this.mCallbacks != null) {
            this.mCallbacks.remove(baseTransientBottomBar$BaseCallback);
            return (B)this;
        }
        return (B)this;
    }
    
    public B setDuration(final int mDuration) {
        this.mDuration = mDuration;
        return (B)this;
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
                final BaseTransientBottomBar$Behavior behavior = new BaseTransientBottomBar$Behavior(this);
                behavior.setStartAlphaSwipeDistance(0.1f);
                behavior.setEndAlphaSwipeDistance(0.6f);
                behavior.setSwipeDirection(0);
                behavior.setListener(new BaseTransientBottomBar$4(this));
                coordinatorLayout$LayoutParams.setBehavior(behavior);
                coordinatorLayout$LayoutParams.insetEdge = 80;
            }
            this.mTargetParent.addView((View)this.mView);
        }
        this.mView.setOnAttachStateChangeListener(new BaseTransientBottomBar$5(this));
        if (!ViewCompat.isLaidOut((View)this.mView)) {
            this.mView.setOnLayoutChangeListener(new BaseTransientBottomBar$6(this));
            return;
        }
        if (this.shouldAnimate()) {
            this.animateViewIn();
            return;
        }
        this.onViewShown();
    }
}
