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
import android.annotation.TargetApi;
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.webkit.CookieManager;
import com.facebook.react.bridge.ReactContext;
import java.net.CookieHandler;
import java.util.Iterator;
import java.util.List;

class ForwardingCookieHandler$3 implements Runnable
{
    final /* synthetic */ ForwardingCookieHandler this$0;
    final /* synthetic */ List val$cookies;
    final /* synthetic */ String val$url;
    
    ForwardingCookieHandler$3(final ForwardingCookieHandler this$0, final List val$cookies, final String val$url) {
        this.this$0 = this$0;
        this.val$cookies = val$cookies;
        this.val$url = val$url;
    }
    
    @Override
    public void run() {
        final Iterator<String> iterator = this.val$cookies.iterator();
        while (iterator.hasNext()) {
            this.this$0.getCookieManager().setCookie(this.val$url, (String)iterator.next());
        }
        this.this$0.mCookieSaver.onCookiesModified();
    }
}
