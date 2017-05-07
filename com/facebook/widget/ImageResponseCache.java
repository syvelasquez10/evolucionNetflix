// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.net.URLConnection;
import com.facebook.internal.Utility;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import android.content.Context;
import com.facebook.internal.FileLruCache;

class ImageResponseCache
{
    static final String TAG;
    private static volatile FileLruCache imageCache;
    
    static {
        TAG = ImageResponseCache.class.getSimpleName();
    }
    
    static FileLruCache getCache(final Context context) throws IOException {
        synchronized (ImageResponseCache.class) {
            if (ImageResponseCache.imageCache == null) {
                ImageResponseCache.imageCache = new FileLruCache(context.getApplicationContext(), ImageResponseCache.TAG, new FileLruCache.Limits());
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
    
    static InputStream interceptAndCacheImageStream(final Context context, final HttpURLConnection httpURLConnection) throws IOException {
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
            inputStream = getCache(context).interceptAndPut(url.toString(), new BufferedHttpInputStream(inputStream2, httpURLConnection));
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
    
    private static class BufferedHttpInputStream extends BufferedInputStream
    {
        HttpURLConnection connection;
        
        BufferedHttpInputStream(final InputStream inputStream, final HttpURLConnection connection) {
            super(inputStream, 8192);
            this.connection = connection;
        }
        
        @Override
        public void close() throws IOException {
            super.close();
            Utility.disconnectQuietly(this.connection);
        }
    }
}
