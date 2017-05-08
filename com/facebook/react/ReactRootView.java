// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.bridge.UiThreadUtil;
import android.view.View$MeasureSpec;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.common.logging.FLog;
import android.view.MotionEvent;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.facebook.infer.annotation.Assertions;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.content.Context;
import android.os.Bundle;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;

public class ReactRootView extends SizeMonitoringFrameLayout implements RootView
{
    private ReactRootView$CustomGlobalLayoutListener mCustomGlobalLayoutListener;
    private boolean mIsAttachedToInstance;
    private String mJSModuleName;
    private final JSTouchDispatcher mJSTouchDispatcher;
    private Bundle mLaunchOptions;
    private ReactInstanceManager mReactInstanceManager;
    private ReactRootView$ReactRootViewEventListener mRootViewEventListener;
    private int mRootViewTag;
    private boolean mWasMeasured;
    
    public ReactRootView(final Context context) {
        super(context);
        this.mWasMeasured = false;
        this.mIsAttachedToInstance = false;
        this.mJSTouchDispatcher = new JSTouchDispatcher((ViewGroup)this);
    }
    
    public ReactRootView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mWasMeasured = false;
        this.mIsAttachedToInstance = false;
        this.mJSTouchDispatcher = new JSTouchDispatcher((ViewGroup)this);
    }
    
    public ReactRootView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mWasMeasured = false;
        this.mIsAttachedToInstance = false;
        this.mJSTouchDispatcher = new JSTouchDispatcher((ViewGroup)this);
    }
    
    private void attachToReactInstanceManager() {
        if (this.mIsAttachedToInstance) {
            return;
        }
        this.mIsAttachedToInstance = true;
        Assertions.assertNotNull(this.mReactInstanceManager).attachMeasuredRootView(this);
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this.getCustomGlobalLayoutListener());
    }
    
    private void dispatchJSTouchEvent(final MotionEvent motionEvent) {
        if (this.mReactInstanceManager == null || !this.mIsAttachedToInstance || this.mReactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("React", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        this.mJSTouchDispatcher.handleTouchEvent(motionEvent, this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class).getEventDispatcher());
    }
    
    private ReactRootView$CustomGlobalLayoutListener getCustomGlobalLayoutListener() {
        if (this.mCustomGlobalLayoutListener == null) {
            this.mCustomGlobalLayoutListener = new ReactRootView$CustomGlobalLayoutListener(this);
        }
        return this.mCustomGlobalLayoutListener;
    }
    
    protected void finalize() {
        super.finalize();
        Assertions.assertCondition(!this.mIsAttachedToInstance, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }
    
    String getJSModuleName() {
        return Assertions.assertNotNull(this.mJSModuleName);
    }
    
    Bundle getLaunchOptions() {
        return this.mLaunchOptions;
    }
    
    public int getRootViewTag() {
        return this.mRootViewTag;
    }
    
    public void onAttachedToReactInstance() {
        if (this.mRootViewEventListener != null) {
            this.mRootViewEventListener.onAttachedToReactInstance(this);
        }
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsAttachedToInstance) {
            this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this.getCustomGlobalLayoutListener());
        }
    }
    
    @Override
    public void onChildStartedNativeGesture(final MotionEvent motionEvent) {
        if (this.mReactInstanceManager == null || !this.mIsAttachedToInstance || this.mReactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("React", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class).getEventDispatcher());
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsAttachedToInstance) {
            this.getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this.getCustomGlobalLayoutListener());
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.dispatchJSTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.setMeasuredDimension(View$MeasureSpec.getSize(n), View$MeasureSpec.getSize(n2));
        this.mWasMeasured = true;
        if (this.mReactInstanceManager != null && !this.mIsAttachedToInstance) {
            UiThreadUtil.runOnUiThread(new ReactRootView$1(this));
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.dispatchJSTouchEvent(motionEvent);
        super.onTouchEvent(motionEvent);
        return true;
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        if (this.getParent() != null) {
            this.getParent().requestDisallowInterceptTouchEvent(b);
        }
    }
    
    public void setEventListener(final ReactRootView$ReactRootViewEventListener mRootViewEventListener) {
        this.mRootViewEventListener = mRootViewEventListener;
    }
    
    public void setRootViewTag(final int mRootViewTag) {
        this.mRootViewTag = mRootViewTag;
    }
    
    public void startReactApplication(final ReactInstanceManager mReactInstanceManager, final String mjsModuleName, final Bundle mLaunchOptions) {
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(this.mReactInstanceManager == null, "This root view has already been attached to a catalyst instance manager");
        this.mReactInstanceManager = mReactInstanceManager;
        this.mJSModuleName = mjsModuleName;
        this.mLaunchOptions = mLaunchOptions;
        if (!this.mReactInstanceManager.hasStartedCreatingInitialContext()) {
            this.mReactInstanceManager.createReactContextInBackground();
        }
        if (this.mWasMeasured) {
            this.attachToReactInstanceManager();
        }
    }
}
