// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.net.HttpURLConnection;
import java.io.IOException;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import java.io.InputStream;
import java.net.URL;
import com.facebook.internal.FileLruCache$Limits;
import android.content.Context;
import com.facebook.internal.FileLruCache;

class ImageResponseCache
{
    static final String TAG;
    private static volatile FileLruCache imageCache;
    
    static {
        TAG = ImageResponseCache.class.getSimpleName();
    }
    
    static FileLruCache getCache(final Context context) {
        synchronized (ImageResponseCache.class) {
            if (ImageResponseCache.imageCache == null) {
                ImageResponseCache.imageCache = new FileLruCache(context.getApplicationContext(), ImageResponseCache.TAG, new FileLruCache$Limits());
            }
            return ImageResponseCache.imageCache;
        }
    }
    
    static InputStream getCachedImageStream(final URL url, final Context context) {
        InputStream value = null;
        if (url == null) {
            return value;
        }
        value = value;
        if (!isCDNURL(url)) {
            return value;
        }
        try {
            value = getCache(context).get(url.toString());
            return value;
        }
        catch (IOException ex) {
            Logger.log(LoggingBehavior.CACHE, 5, ImageResponseCache.TAG, ex.toString());
            return null;
        }
    }
    
    static InputStream interceptAndCacheImageStream(final Context context, final HttpURLConnection httpURLConnection) {
        InputStream inputStream = null;
        if (httpURLConnection.getResponseCode() != 200) {
            return inputStream;
        }
        final URL url = httpURLConnection.getURL();
        final InputStream inputStream2 = inputStream = httpURLConnection.getInputStream();
        if (!isCDNURL(url)) {
            return inputStream;
        }
        try {
            inputStream = getCache(context).interceptAndPut(url.toString(), new ImageResponseCache$BufferedHttpInputStream(inputStream2, httpURLConnection));
            return inputStream;
        }
        catch (IOException ex) {
            return inputStream2;
        }
    }
    
    private static boolean isCDNURL(final URL url) {
        if (url != null) {
            final String host = url.getHost();
            if (host.endsWith("fbcdn.net") || (host.startsWith("fbcdn") && host.endsWith("akamaihd.net"))) {
                return true;
            }
        }
        return false;
    }
}
