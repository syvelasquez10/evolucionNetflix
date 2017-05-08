// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import java.util.Collections;
import android.text.TextUtils;
import java.util.Map;
import java.net.URI;
import android.webkit.CookieSyncManager;
import android.content.Context;
import com.facebook.react.bridge.Callback;
import java.util.Iterator;
import java.util.List;
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.webkit.CookieManager;
import com.facebook.react.bridge.ReactContext;
import java.net.CookieHandler;
import android.annotation.TargetApi;
import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Handler;

class ForwardingCookieHandler$CookieSaver
{
    private final Handler mHandler;
    final /* synthetic */ ForwardingCookieHandler this$0;
    
    public ForwardingCookieHandler$CookieSaver(final ForwardingCookieHandler this$0) {
        this.this$0 = this$0;
        this.mHandler = new Handler(Looper.getMainLooper(), (Handler$Callback)new ForwardingCookieHandler$CookieSaver$1(this, this$0));
    }
    
    @TargetApi(21)
    private void flush() {
        this.this$0.getCookieManager().flush();
    }
    
    public void onCookiesModified() {
        if (ForwardingCookieHandler.USES_LEGACY_STORE) {
            this.mHandler.sendEmptyMessageDelayed(1, 30000L);
        }
    }
    
    public void persistCookies() {
        this.mHandler.removeMessages(1);
        this.this$0.runInBackground(new ForwardingCookieHandler$CookieSaver$2(this));
    }
}
