// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import com.facebook.react.R$style;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import android.widget.FrameLayout;
import android.view.View;
import android.content.Context;
import android.content.DialogInterface$OnShowListener;
import android.app.Dialog;
import com.facebook.react.bridge.LifecycleEventListener;
import android.view.ViewGroup;
import android.app.Activity;
import com.facebook.react.bridge.ReactContext;
import com.facebook.infer.annotation.Assertions;
import android.view.KeyEvent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnKeyListener;

class ReactModalHostView$1 implements DialogInterface$OnKeyListener
{
    final /* synthetic */ ReactModalHostView this$0;
    
    ReactModalHostView$1(final ReactModalHostView this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onKey(final DialogInterface dialogInterface, final int n, final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            if (n == 4) {
                Assertions.assertNotNull(this.this$0.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                this.this$0.mOnRequestCloseListener.onRequestClose(dialogInterface);
                return true;
            }
            final Activity currentActivity = ((ReactContext)this.this$0.getContext()).getCurrentActivity();
            if (currentActivity != null) {
                return currentActivity.onKeyUp(n, keyEvent);
            }
        }
        return false;
    }
}
