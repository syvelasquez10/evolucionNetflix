// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import android.os.Message;
import android.os.Handler$Callback;

class ForwardingCookieHandler$CookieSaver$1 implements Handler$Callback
{
    final /* synthetic */ ForwardingCookieHandler$CookieSaver this$1;
    final /* synthetic */ ForwardingCookieHandler val$this$0;
    
    ForwardingCookieHandler$CookieSaver$1(final ForwardingCookieHandler$CookieSaver this$1, final ForwardingCookieHandler val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    public boolean handleMessage(final Message message) {
        if (message.what == 1) {
            this.this$1.persistCookies();
            return true;
        }
        return false;
    }
}
