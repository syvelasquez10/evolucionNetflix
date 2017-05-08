// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import android.content.DialogInterface$OnKeyListener;
import com.facebook.react.R$style;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import com.facebook.infer.annotation.Assertions;
import android.widget.FrameLayout;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import android.content.Context;
import android.content.DialogInterface$OnShowListener;
import android.app.Dialog;
import com.facebook.react.bridge.LifecycleEventListener;
import android.view.ViewGroup;

public class ReactModalHostView extends ViewGroup implements LifecycleEventListener
{
    private String mAnimationType;
    private Dialog mDialog;
    private ReactModalHostView$DialogRootViewGroup mHostView;
    private ReactModalHostView$OnRequestCloseListener mOnRequestCloseListener;
    private DialogInterface$OnShowListener mOnShowListener;
    private boolean mPropertyRequiresNewDialog;
    private boolean mTransparent;
    
    public ReactModalHostView(final Context context) {
        super(context);
        ((ReactContext)context).addLifecycleEventListener(this);
        this.mHostView = new ReactModalHostView$DialogRootViewGroup(context);
    }
    
    private void dismiss() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
            this.mDialog = null;
            ((ViewGroup)this.mHostView.getParent()).removeViewAt(0);
        }
    }
    
    private View getContentView() {
        final FrameLayout frameLayout = new FrameLayout(this.getContext());
        frameLayout.addView((View)this.mHostView);
        frameLayout.setFitsSystemWindows(true);
        return (View)frameLayout;
    }
    
    private void updateProperties() {
        Assertions.assertNotNull(this.mDialog, "mDialog must exist when we call updateProperties");
        if (this.mTransparent) {
            this.mDialog.getWindow().clearFlags(2);
            return;
        }
        this.mDialog.getWindow().setDimAmount(0.5f);
        this.mDialog.getWindow().setFlags(2, 2);
    }
    
    public void addChildrenForAccessibility(final ArrayList<View> list) {
    }
    
    public void addView(final View view, final int n) {
        this.mHostView.addView(view, n);
    }
    
    public boolean dispatchPopulateAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        return false;
    }
    
    public View getChildAt(final int n) {
        return this.mHostView.getChildAt(n);
    }
    
    public int getChildCount() {
        return this.mHostView.getChildCount();
    }
    
    public Dialog getDialog() {
        return this.mDialog;
    }
    
    public void onDropInstance() {
        ((ReactContext)this.getContext()).removeLifecycleEventListener(this);
        this.dismiss();
    }
    
    public void onHostPause() {
        this.dismiss();
    }
    
    public void onHostResume() {
        this.showOrUpdate();
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
    }
    
    public void removeView(final View view) {
        this.mHostView.removeView(view);
    }
    
    public void removeViewAt(final int n) {
        this.mHostView.removeView(this.getChildAt(n));
    }
    
    protected void setAnimationType(final String mAnimationType) {
        this.mAnimationType = mAnimationType;
        this.mPropertyRequiresNewDialog = true;
    }
    
    protected void setOnRequestCloseListener(final ReactModalHostView$OnRequestCloseListener mOnRequestCloseListener) {
        this.mOnRequestCloseListener = mOnRequestCloseListener;
    }
    
    protected void setOnShowListener(final DialogInterface$OnShowListener mOnShowListener) {
        this.mOnShowListener = mOnShowListener;
    }
    
    protected void setTransparent(final boolean mTransparent) {
        this.mTransparent = mTransparent;
    }
    
    protected void showOrUpdate() {
        if (this.mDialog != null) {
            if (!this.mPropertyRequiresNewDialog) {
                this.updateProperties();
                return;
            }
            this.dismiss();
        }
        this.mPropertyRequiresNewDialog = false;
        int n = R$style.Theme_FullScreenDialog;
        if (this.mAnimationType.equals("fade")) {
            n = R$style.Theme_FullScreenDialogAnimatedFade;
        }
        else if (this.mAnimationType.equals("slide")) {
            n = R$style.Theme_FullScreenDialogAnimatedSlide;
        }
        (this.mDialog = new Dialog(this.getContext(), n)).setContentView(this.getContentView());
        this.updateProperties();
        this.mDialog.setOnShowListener(this.mOnShowListener);
        this.mDialog.setOnKeyListener((DialogInterface$OnKeyListener)new ReactModalHostView$1(this));
        this.mDialog.getWindow().setSoftInputMode(16);
        this.mDialog.show();
    }
}
