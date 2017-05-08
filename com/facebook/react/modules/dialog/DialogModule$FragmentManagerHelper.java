// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.dialog;

import com.facebook.react.bridge.Callback;
import android.os.Bundle;
import android.app.FragmentManager;

class DialogModule$FragmentManagerHelper
{
    private final FragmentManager mFragmentManager;
    private Object mFragmentToShow;
    private final android.support.v4.app.FragmentManager mSupportFragmentManager;
    final /* synthetic */ DialogModule this$0;
    
    public DialogModule$FragmentManagerHelper(final DialogModule this$0, final FragmentManager mFragmentManager) {
        this.this$0 = this$0;
        this.mFragmentManager = mFragmentManager;
        this.mSupportFragmentManager = null;
    }
    
    public DialogModule$FragmentManagerHelper(final DialogModule this$0, final android.support.v4.app.FragmentManager mSupportFragmentManager) {
        this.this$0 = this$0;
        this.mFragmentManager = null;
        this.mSupportFragmentManager = mSupportFragmentManager;
    }
    
    private void dismissExisting() {
        if (this.isUsingSupportLibrary()) {
            final SupportAlertFragment supportAlertFragment = (SupportAlertFragment)this.mSupportFragmentManager.findFragmentByTag("com.facebook.catalyst.react.dialog.DialogModule");
            if (supportAlertFragment != null) {
                supportAlertFragment.dismiss();
            }
        }
        else {
            final AlertFragment alertFragment = (AlertFragment)this.mFragmentManager.findFragmentByTag("com.facebook.catalyst.react.dialog.DialogModule");
            if (alertFragment != null) {
                alertFragment.dismiss();
            }
        }
    }
    
    private boolean isUsingSupportLibrary() {
        return this.mSupportFragmentManager != null;
    }
    
    public void showNewAlert(final boolean b, final Bundle bundle, final Callback callback) {
        this.dismissExisting();
        DialogModule$AlertFragmentListener dialogModule$AlertFragmentListener;
        if (callback != null) {
            dialogModule$AlertFragmentListener = new DialogModule$AlertFragmentListener(this.this$0, callback);
        }
        else {
            dialogModule$AlertFragmentListener = null;
        }
        if (this.isUsingSupportLibrary()) {
            final SupportAlertFragment mFragmentToShow = new SupportAlertFragment(dialogModule$AlertFragmentListener, bundle);
            if (b) {
                if (bundle.containsKey("cancelable")) {
                    mFragmentToShow.setCancelable(bundle.getBoolean("cancelable"));
                }
                mFragmentToShow.show(this.mSupportFragmentManager, "com.facebook.catalyst.react.dialog.DialogModule");
                return;
            }
            this.mFragmentToShow = mFragmentToShow;
        }
        else {
            final AlertFragment mFragmentToShow2 = new AlertFragment(dialogModule$AlertFragmentListener, bundle);
            if (b) {
                if (bundle.containsKey("cancelable")) {
                    mFragmentToShow2.setCancelable(bundle.getBoolean("cancelable"));
                }
                mFragmentToShow2.show(this.mFragmentManager, "com.facebook.catalyst.react.dialog.DialogModule");
                return;
            }
            this.mFragmentToShow = mFragmentToShow2;
        }
    }
    
    public void showPendingAlert() {
        if (this.mFragmentToShow == null) {
            return;
        }
        if (this.isUsingSupportLibrary()) {
            ((SupportAlertFragment)this.mFragmentToShow).show(this.mSupportFragmentManager, "com.facebook.catalyst.react.dialog.DialogModule");
        }
        else {
            ((AlertFragment)this.mFragmentToShow).show(this.mFragmentManager, "com.facebook.catalyst.react.dialog.DialogModule");
        }
        this.mFragmentToShow = null;
    }
}
