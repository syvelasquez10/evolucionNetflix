// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.toast;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.HashMap;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import android.content.Context;
import android.widget.Toast;

class ToastModule$1 implements Runnable
{
    final /* synthetic */ ToastModule this$0;
    final /* synthetic */ int val$duration;
    final /* synthetic */ String val$message;
    
    ToastModule$1(final ToastModule this$0, final String val$message, final int val$duration) {
        this.this$0 = this$0;
        this.val$message = val$message;
        this.val$duration = val$duration;
    }
    
    @Override
    public void run() {
        Toast.makeText((Context)this.this$0.getReactApplicationContext(), (CharSequence)this.val$message, this.val$duration).show();
    }
}
