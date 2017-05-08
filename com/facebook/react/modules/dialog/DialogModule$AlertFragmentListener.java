// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.dialog;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import android.os.Bundle;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.common.logging.FLog;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import java.io.Serializable;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import android.content.DialogInterface;
import com.facebook.react.bridge.Callback;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnClickListener;

class DialogModule$AlertFragmentListener implements DialogInterface$OnClickListener, DialogInterface$OnDismissListener
{
    private final Callback mCallback;
    private boolean mCallbackConsumed;
    final /* synthetic */ DialogModule this$0;
    
    public DialogModule$AlertFragmentListener(final DialogModule this$0, final Callback mCallback) {
        this.this$0 = this$0;
        this.mCallbackConsumed = false;
        this.mCallback = mCallback;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (!this.mCallbackConsumed && this.this$0.getReactApplicationContext().hasActiveCatalystInstance()) {
            this.mCallback.invoke("buttonClicked", n);
            this.mCallbackConsumed = true;
        }
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        if (!this.mCallbackConsumed && this.this$0.getReactApplicationContext().hasActiveCatalystInstance()) {
            this.mCallback.invoke("dismissed");
            this.mCallbackConsumed = true;
        }
    }
}
