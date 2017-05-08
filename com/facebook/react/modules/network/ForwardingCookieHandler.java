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
import android.annotation.TargetApi;
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.webkit.CookieManager;
import com.facebook.react.bridge.ReactContext;
import java.net.CookieHandler;

public class ForwardingCookieHandler extends CookieHandler
{
    private static final boolean USES_LEGACY_STORE;
    private final ReactContext mContext;
    private CookieManager mCookieManager;
    private final ForwardingCookieHandler$CookieSaver mCookieSaver;
    
    static {
        USES_LEGACY_STORE = (Build$VERSION.SDK_INT < 21);
    }
    
    public ForwardingCookieHandler(final ReactContext mContext) {
        this.mContext = mContext;
        this.mCookieSaver = new ForwardingCookieHandler$CookieSaver(this);
    }
    
    @TargetApi(21)
    private void addCookieAsync(final String s, final String s2) {
        this.getCookieManager().setCookie(s, s2, (ValueCallback)null);
    }
    
    private void addCookies(final String s, final List<String> list) {
        if (ForwardingCookieHandler.USES_LEGACY_STORE) {
            this.runInBackground(new ForwardingCookieHandler$3(this, list, s));
            return;
        }
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.addCookieAsync(s, iterator.next());
        }
        this.mCookieSaver.onCookiesModified();
    }
    
    private void clearCookiesAsync(final Callback callback) {
        this.getCookieManager().removeAllCookies((ValueCallback)new ForwardingCookieHandler$2(this, callback));
    }
    
    private CookieManager getCookieManager() {
        if (this.mCookieManager == null) {
            possiblyWorkaroundSyncManager((Context)this.mContext);
            this.mCookieManager = CookieManager.getInstance();
            if (ForwardingCookieHandler.USES_LEGACY_STORE) {
                this.mCookieManager.removeExpiredCookie();
            }
        }
        return this.mCookieManager;
    }
    
    private static boolean isCookieHeader(final String s) {
        return s.equalsIgnoreCase("Set-cookie") || s.equalsIgnoreCase("Set-cookie2");
    }
    
    private static void possiblyWorkaroundSyncManager(final Context context) {
        if (ForwardingCookieHandler.USES_LEGACY_STORE) {
            CookieSyncManager.createInstance(context).sync();
        }
    }
    
    private void runInBackground(final Runnable runnable) {
        new ForwardingCookieHandler$4(this, this.mContext, runnable).execute((Object[])new Void[0]);
    }
    
    public void clearCookies(final Callback callback) {
        if (ForwardingCookieHandler.USES_LEGACY_STORE) {
            new ForwardingCookieHandler$1(this, this.mContext, callback).execute((Object[])new Void[0]);
            return;
        }
        this.clearCookiesAsync(callback);
    }
    
    public void destroy() {
        if (ForwardingCookieHandler.USES_LEGACY_STORE) {
            this.getCookieManager().removeExpiredCookie();
            this.mCookieSaver.persistCookies();
        }
    }
    
    @Override
    public Map<String, List<String>> get(final URI uri, final Map<String, List<String>> map) {
        final String cookie = this.getCookieManager().getCookie(uri.toString());
        if (TextUtils.isEmpty((CharSequence)cookie)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(cookie));
    }
    
    @Override
    public void put(final URI uri, final Map<String, List<String>> map) {
        final String string = uri.toString();
        for (final Map.Entry<String, List<String>> entry : map.entrySet()) {
            final String s = entry.getKey();
            if (s != null && isCookieHeader(s)) {
                this.addCookies(string, entry.getValue());
            }
        }
    }
}
