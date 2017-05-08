// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Handler;
import java.util.Collections;
import android.text.TextUtils;
import java.util.Map;
import java.net.URI;
import android.content.Context;
import com.facebook.react.bridge.Callback;
import java.util.Iterator;
import java.util.List;
import android.annotation.TargetApi;
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.webkit.CookieManager;
import com.facebook.react.bridge.ReactContext;
import java.net.CookieHandler;
import android.webkit.CookieSyncManager;

class ForwardingCookieHandler$CookieSaver$2 implements Runnable
{
    final /* synthetic */ ForwardingCookieHandler$CookieSaver this$1;
    
    ForwardingCookieHandler$CookieSaver$2(final ForwardingCookieHandler$CookieSaver this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        if (ForwardingCookieHandler.USES_LEGACY_STORE) {
            CookieSyncManager.getInstance().sync();
            return;
        }
        this.this$1.flush();
    }
}
