// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import android.util.Log;
import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher extends Thread
{
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request> mQueue;
    private volatile boolean mQuit;
    
    public NetworkDispatcher(final BlockingQueue<Request> mQueue, final Network mNetwork, final Cache mCache, final ResponseDelivery mDelivery) {
        super("NetworkDispatcher");
        this.mQuit = false;
        this.mQueue = mQueue;
        this.mNetwork = mNetwork;
        this.mCache = mCache;
        this.mDelivery = mDelivery;
    }
    
    private void parseAndDeliverNetworkError(final Request<?> request, VolleyError networkError) {
        networkError = request.parseNetworkError(networkError);
        this.mDelivery.postError(request, networkError);
    }
    
    public void quit() {
        this.mQuit = true;
        this.interrupt();
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            while (true) {
                Request<?> request = null;
                try {
                    while (true) {
                        request = this.mQueue.take();
                        if (Log.isLoggable("RequestQueue_Blocked", 4) && this.mQueue.size() > 0) {
                            Log.i("RequestQueue_Blocked", "Http request is blocked - queue size: " + this.mQueue.size());
                        }
                        try {
                            request.addMarker("network-queue-take");
                            if (!request.isCanceled()) {
                                goto Label_0113;
                            }
                            request.finish("network-discard-cancelled");
                        }
                        catch (VolleyError volleyError) {
                            this.parseAndDeliverNetworkError(request, volleyError);
                        }
                        catch (Exception ex) {
                            VolleyLog.e(ex, "Unhandled exception %s", ex.toString());
                            this.mDelivery.postError(request, new VolleyError(ex));
                        }
                    }
                }
                catch (InterruptedException ex2) {}
                final NetworkResponse networkResponse2;
                final Response<?> networkResponse = request.parseNetworkResponse(networkResponse2);
                request.addMarker("network-parse-complete");
                if (request.shouldCache() && networkResponse.cacheEntry != null) {
                    this.mCache.put(request.getCacheKey(), networkResponse.cacheEntry);
                    request.addMarker("network-cache-written");
                }
                request.markDelivered();
                this.mDelivery.postResponse(request, networkResponse);
                continue;
            }
        }
    }
}
