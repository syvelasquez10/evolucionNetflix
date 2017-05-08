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
import java.util.Iterator;
import java.util.List;
import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.webkit.CookieManager;
import com.facebook.react.bridge.ReactContext;
import java.net.CookieHandler;
import com.facebook.react.bridge.Callback;
import android.webkit.ValueCallback;

class ForwardingCookieHandler$2 implements ValueCallback<Boolean>
{
    final /* synthetic */ ForwardingCookieHandler this$0;
    final /* synthetic */ Callback val$callback;
    
    ForwardingCookieHandler$2(final ForwardingCookieHandler this$0, final Callback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
    }
    
    public void onReceiveValue(final Boolean b) {
        this.this$0.mCookieSaver.onCookiesModified();
        this.val$callback.invoke(b);
    }
}
