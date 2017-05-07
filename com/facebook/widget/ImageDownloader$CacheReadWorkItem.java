// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.io.InputStream;
import java.net.URL;
import java.io.Closeable;
import com.facebook.internal.Utility;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;
import android.content.Context;

class ImageDownloader$CacheReadWorkItem implements Runnable
{
    private boolean allowCachedRedirects;
    private Context context;
    private ImageDownloader$RequestKey key;
    
    ImageDownloader$CacheReadWorkItem(final Context context, final ImageDownloader$RequestKey key, final boolean allowCachedRedirects) {
        this.context = context;
        this.key = key;
        this.allowCachedRedirects = allowCachedRedirects;
    }
    
    @Override
    public void run() {
        readFromCache(this.key, this.context, this.allowCachedRedirects);
    }
}
