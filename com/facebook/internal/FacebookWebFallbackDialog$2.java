// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.os.Bundle;
import android.webkit.WebView;
import android.os.Handler;
import android.os.Looper;
import com.facebook.widget.WebDialog$OnCompleteListener;
import com.facebook.widget.FacebookDialog$Callback;
import com.facebook.widget.FacebookDialog$PendingCall;
import android.content.Context;
import com.facebook.widget.WebDialog;

class FacebookWebFallbackDialog$2 implements Runnable
{
    final /* synthetic */ FacebookWebFallbackDialog this$0;
    
    FacebookWebFallbackDialog$2(final FacebookWebFallbackDialog this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (!this.this$0.isListenerCalled()) {
            this.this$0.sendCancelToListener();
        }
    }
}
