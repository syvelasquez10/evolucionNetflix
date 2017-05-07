// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class CacheDispatcher extends Thread
{
    private static final boolean DEBUG;
    private final Cache mCache;
    private final BlockingQueue<Request> mCacheQueue;
    private final ResponseDelivery mDelivery;
    private final BlockingQueue<Request> mNetworkQueue;
    private volatile boolean mQuit;
    
    static {
        DEBUG = VolleyLog.DEBUG;
    }
    
    public CacheDispatcher(final BlockingQueue<Request> mCacheQueue, final BlockingQueue<Request> mNetworkQueue, final Cache mCache, final ResponseDelivery mDelivery) {
        this.mQuit = false;
        this.mCacheQueue = mCacheQueue;
        this.mNetworkQueue = mNetworkQueue;
        this.mCache = mCache;
        this.mDelivery = mDelivery;
    }
    
    public void quit() {
        this.mQuit = true;
        this.interrupt();
    }
    
    @Override
    public void run() {
        if (CacheDispatcher.DEBUG) {
            VolleyLog.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.mCache.initialize();
    Label_0029_Outer:
        while (true) {
            while (true) {
                Request<?> request;
                try {
                    while (true) {
                        request = this.mCacheQueue.take();
                        request.addMarker("cache-queue-take");
                        if (!request.isCanceled()) {
                            break;
                        }
                        request.finish("cache-discard-canceled");
                    }
                }
                catch (InterruptedException ex) {
                    if (this.mQuit) {
                        return;
                    }
                    continue Label_0029_Outer;
                }
                final Cache.Entry value = this.mCache.get(request.getCacheKey());
                if (value == null) {
                    request.addMarker("cache-miss");
                    this.mNetworkQueue.put(request);
                    continue;
                }
                if (value.isExpired()) {
                    request.addMarker("cache-hit-expired");
                    request.setCacheEntry(value);
                    this.mNetworkQueue.put(request);
                    continue;
                }
                request.addMarker("cache-hit");
                final Response<?> networkResponse = request.parseNetworkResponse(new NetworkResponse(value.data, value.responseHeaders));
                request.addMarker("cache-hit-parsed");
                if (!value.refreshNeeded()) {
                    this.mDelivery.postResponse(request, networkResponse);
                    continue;
                }
                request.addMarker("cache-hit-refresh-needed");
                request.setCacheEntry(value);
                networkResponse.intermediate = true;
                this.mDelivery.postResponse(request, networkResponse, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            CacheDispatcher.this.mNetworkQueue.put(request);
                        }
                        catch (InterruptedException ex) {}
                    }
                });
                continue;
            }
        }
    }
}
