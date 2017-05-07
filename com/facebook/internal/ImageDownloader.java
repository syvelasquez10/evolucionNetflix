// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader
{
    private static WorkQueue cacheReadQueue;
    private static WorkQueue downloadQueue;
    private static final Map<Object, Object> pendingRequests;
    
    static {
        ImageDownloader.downloadQueue = new WorkQueue(8);
        ImageDownloader.cacheReadQueue = new WorkQueue(2);
        pendingRequests = new HashMap<Object, Object>();
    }
    
    public static void clearCache(final Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache(context);
    }
}
