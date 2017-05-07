// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.Context;
import android.support.v7.internal.view.SupportActionModeWrapper;
import android.support.v7.internal.view.SupportActionModeWrapper$CallbackWrapper;
import android.view.ActionMode;
import android.view.ActionMode$Callback;
import android.view.View;
import android.view.KeyEvent;
import android.support.v7.internal.widget.NativeActionModeAwareLayout;
import android.annotation.TargetApi;
import android.support.v7.internal.widget.NativeActionModeAwareLayout$OnActionModeForChildListener;

@TargetApi(11)
class ActionBarActivityDelegateHC extends ActionBarActivityDelegateBase implements NativeActionModeAwareLayout$OnActionModeForChildListener
{
    private NativeActionModeAwareLayout mNativeActionModeAwareLayout;
    
    ActionBarActivityDelegateHC(final ActionBarActivity actionBarActivity) {
        super(actionBarActivity);
    }
    
    @Override
    boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return false;
    }
    
    @Override
    void onSubDecorInstalled() {
        this.mNativeActionModeAwareLayout = (NativeActionModeAwareLayout)this.mActivity.findViewById(16908290);
        if (this.mNativeActionModeAwareLayout != null) {
            this.mNativeActionModeAwareLayout.setActionModeForChildListener(this);
        }
    }
    
    @Override
    public ActionMode startActionModeForChild(final View view, final ActionMode$Callback actionMode$Callback) {
        final android.support.v7.view.ActionMode startSupportActionMode = this.startSupportActionMode(new SupportActionModeWrapper$CallbackWrapper(view.getContext(), actionMode$Callback));
        if (startSupportActionMode != null) {
            return new SupportActionModeWrapper((Context)this.mActivity, startSupportActionMode);
        }
        return null;
    }
}
