// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.IOException;
import com.facebook.LoggingBehavior;
import android.content.Context;

class UrlRedirectCache
{
    private static final String REDIRECT_CONTENT_TAG;
    static final String TAG;
    private static volatile FileLruCache urlRedirectCache;
    
    static {
        TAG = UrlRedirectCache.class.getSimpleName();
        REDIRECT_CONTENT_TAG = UrlRedirectCache.TAG + "_Redirect";
    }
    
    static void clearCache(final Context context) {
        try {
            getCache(context).clearCache();
        }
        catch (IOException ex) {
            Logger.log(LoggingBehavior.CACHE, 5, UrlRedirectCache.TAG, "clearCache failed " + ex.getMessage());
        }
    }
    
    static FileLruCache getCache(final Context context) {
        synchronized (UrlRedirectCache.class) {
            if (UrlRedirectCache.urlRedirectCache == null) {
                UrlRedirectCache.urlRedirectCache = new FileLruCache(context.getApplicationContext(), UrlRedirectCache.TAG, new FileLruCache$Limits());
            }
            return UrlRedirectCache.urlRedirectCache;
        }
    }
}
