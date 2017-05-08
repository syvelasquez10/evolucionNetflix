// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.widget.TextView;
import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$Behavior;
import android.support.design.widget.SwipeDismissBehavior$OnDismissListener;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.graphics.Typeface;
import android.content.res.ColorStateList;
import android.widget.Button;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.support.design.widget.CoordinatorLayout;
import android.content.res.TypedArray;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.AnimationUtils;
import com.netflix.android.widgetry.R$anim;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import com.netflix.android.widgetry.R$layout;
import android.view.LayoutInflater;
import android.os.Handler$Callback;
import android.os.Looper;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.appcompat.R$attr;
import android.view.ViewGroup;
import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import android.os.Handler;
import android.view.animation.Interpolator;
import android.annotation.SuppressLint;

@SuppressLint({ "PrivateResource" })
public final class BuffetBar
{
    static final int ANIMATION_DURATION = 250;
    static final int ANIMATION_FADE_DURATION = 180;
    private static final int[] APPCOMPAT_CHECK_ATTRS;
    static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW = 0;
    static final int MSG_SHOW_NO_ANIMATION = 2;
    static final Handler sHandler;
    private final AccessibilityManager mAccessibilityManager;
    private BuffetBar$Callback mCallback;
    private final Context mContext;
    private int mDuration;
    private int mLastBackgroundColor;
    final BuffetManager$Callback mManagerCallback;
    private final ViewGroup mTargetParent;
    final BuffetBar$BuffetLayout mView;
    
    static {
        APPCOMPAT_CHECK_ATTRS = new int[] { R$attr.colorPrimary };
        FAST_OUT_SLOW_IN_INTERPOLATOR = (Interpolator)new FastOutSlowInInterpolator();
        sHandler = new Handler(Looper.getMainLooper(), (Handler$Callback)new BuffetBar$1());
    }
    
    private BuffetBar(final ViewGroup mTargetParent) {
        this.mLastBackgroundColor = -1;
        this.mManagerCallback = new BuffetBar$3(this);
        this.mTargetParent = mTargetParent;
        checkAppCompatTheme(this.mContext = mTargetParent.getContext());
        this.mView = (BuffetBar$BuffetLayout)LayoutInflater.from(this.mContext).inflate(R$layout.design_layout_buffet, this.mTargetParent, false);
        this.mAccessibilityManager = (AccessibilityManager)this.mContext.getSystemService("accessibility");
    }
    
    private void animateViewOut(final int n) {
        if (Build$VERSION.SDK_INT >= 14) {
            ViewCompat.animate((View)this.mView).translationY(this.mView.getHeight()).setInterpolator(BuffetBar.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new BuffetBar$9(this, n)).start();
            return;
        }
        final Animation loadAnimation = AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_snackbar_out);
        loadAnimation.setInterpolator(BuffetBar.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new BuffetBar$10(this, n));
        this.mView.startAnimation(loadAnimation);
    }
    
    static void checkAppCompatTheme(final Context context) {
        boolean b = false;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(BuffetBar.APPCOMPAT_CHECK_ATTRS);
        if (!obtainStyledAttributes.hasValue(0)) {
            b = true;
        }
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
        if (b) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
        }
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
    
    public static BuffetBar make(final View view, final int n, final int n2, final int n3) {
        return make(view, view.getResources().getText(n), n2, n3);
    }
    
    public static BuffetBar make(final View view, final CharSequence text, final int backgroundColor, final int duration) {
        final BuffetBar buffetBar = new BuffetBar(findSuitableParent(view));
        buffetBar.setText(text);
        buffetBar.setBackgroundColor(backgroundColor);
        buffetBar.setDuration(duration);
        return buffetBar;
    }
    
    void animateViewIn() {
        if (Build$VERSION.SDK_INT >= 14) {
            ViewCompat.setTranslationY((View)this.mView, this.mView.getHeight());
            ViewCompat.animate((View)this.mView).translationY(0.0f).setInterpolator(BuffetBar.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new BuffetBar$7(this)).start();
            return;
        }
        final Animation loadAnimation = AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_snackbar_in);
        loadAnimation.setInterpolator(BuffetBar.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new BuffetBar$8(this));
        this.mView.startAnimation(loadAnimation);
    }
    
    public void dismiss() {
        this.dispatchDismiss(3);
    }
    
    void dispatchDismiss(final int n) {
        BuffetManager.getInstance().dismiss(this.mManagerCallback, n);
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
        return BuffetManager.getInstance().isCurrent(this.mManagerCallback);
    }
    
    public boolean isShownOrQueued() {
        return BuffetManager.getInstance().isCurrentOrNext(this.mManagerCallback);
    }
    
    void onViewHidden(final int n) {
        BuffetManager.getInstance().onDismissed(this.mManagerCallback);
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
        BuffetManager.getInstance().onShown(this.mManagerCallback);
        if (this.mCallback != null) {
            this.mCallback.onShown(this);
        }
    }
    
    public BuffetBar setAction(final int n, final View$OnClickListener view$OnClickListener) {
        return this.setAction(this.mContext.getText(n), view$OnClickListener);
    }
    
    public BuffetBar setAction(final CharSequence text, final View$OnClickListener view$OnClickListener) {
        final Button actionView = this.mView.getActionView();
        if (TextUtils.isEmpty(text) || view$OnClickListener == null) {
            ((TextView)actionView).setVisibility(8);
            ((TextView)actionView).setOnClickListener((View$OnClickListener)null);
            return this;
        }
        ((TextView)actionView).setVisibility(0);
        ((TextView)actionView).setText(text);
        ((TextView)actionView).setOnClickListener((View$OnClickListener)new BuffetBar$2(this, view$OnClickListener));
        return this;
    }
    
    public BuffetBar setActionTextColor(final int textColor) {
        ((TextView)this.mView.getActionView()).setTextColor(textColor);
        return this;
    }
    
    public BuffetBar setActionTextColor(final ColorStateList textColor) {
        ((TextView)this.mView.getActionView()).setTextColor(textColor);
        return this;
    }
    
    public BuffetBar setActionTypeface(final Typeface typeface) {
        ((TextView)this.mView.getActionView()).setTypeface(typeface);
        return this;
    }
    
    public BuffetBar setBackgroundColor(final int n) {
        if (this.mLastBackgroundColor != n) {
            this.mView.setBackgroundColor(n);
            this.mLastBackgroundColor = n;
        }
        return this;
    }
    
    public BuffetBar setCallback(final BuffetBar$Callback mCallback) {
        this.mCallback = mCallback;
        return this;
    }
    
    public BuffetBar setDuration(final int mDuration) {
        this.mDuration = mDuration;
        return this;
    }
    
    public BuffetBar setText(final int n) {
        return this.setText(this.mContext.getText(n));
    }
    
    public BuffetBar setText(final CharSequence text) {
        this.mView.getMessageView().setText(text);
        return this;
    }
    
    boolean shouldAnimate() {
        return !this.mAccessibilityManager.isEnabled();
    }
    
    public void show() {
        this.show(true);
    }
    
    public void show(final boolean b) {
        BuffetManager.getInstance().show(this.mDuration, this.mManagerCallback, b);
    }
    
    final void showView(final boolean b) {
        if (this.mView.getParent() == null) {
            final ViewGroup$LayoutParams layoutParams = this.mView.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout$LayoutParams) {
                final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)layoutParams;
                final BuffetBar$Behavior behavior = new BuffetBar$Behavior(this);
                behavior.setStartAlphaSwipeDistance(0.1f);
                behavior.setEndAlphaSwipeDistance(0.6f);
                behavior.setSwipeDirection(2);
                behavior.setListener(new BuffetBar$4(this));
                coordinatorLayout$LayoutParams.setBehavior(behavior);
                coordinatorLayout$LayoutParams.insetEdge = 80;
            }
            this.mTargetParent.addView((View)this.mView);
        }
        this.mView.setOnAttachStateChangeListener(new BuffetBar$5(this));
        if (!ViewCompat.isLaidOut((View)this.mView)) {
            this.mView.setOnLayoutChangeListener(new BuffetBar$6(this, b));
            return;
        }
        if (b && this.shouldAnimate()) {
            this.animateViewIn();
            return;
        }
        this.onViewShown();
    }
}
