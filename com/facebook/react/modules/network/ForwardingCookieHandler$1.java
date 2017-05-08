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
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.webkit.CookieManager;
import java.net.CookieHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedResultAsyncTask;

class ForwardingCookieHandler$1 extends GuardedResultAsyncTask<Boolean>
{
    final /* synthetic */ ForwardingCookieHandler this$0;
    final /* synthetic */ Callback val$callback;
    
    ForwardingCookieHandler$1(final ForwardingCookieHandler this$0, final ReactContext reactContext, final Callback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        super(reactContext);
    }
    
    @Override
    protected Boolean doInBackgroundGuarded() {
        this.this$0.getCookieManager().removeAllCookie();
        this.this$0.mCookieSaver.onCookiesModified();
        return true;
    }
    
    @Override
    protected void onPostExecuteGuarded(final Boolean b) {
        this.val$callback.invoke(b);
    }
}
