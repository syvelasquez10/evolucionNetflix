// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import java.util.Map;
import java.util.HashMap;
import com.android.volley.toolbox.VolleyFileUtils;
import android.net.TrafficStats;
import android.os.Build$VERSION;
import android.os.Process;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.WeakHashMap;

public class NetworkDispatcher extends Thread
{
    private static WeakHashMap<NetworkDispatcher$NetworkDispatcherListener, NetworkDispatcher$NetworkDispatcherListener> sListener;
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request> mQueue;
    private volatile boolean mQuit;
    
    static {
        NetworkDispatcher.sListener = new WeakHashMap<NetworkDispatcher$NetworkDispatcherListener, NetworkDispatcher$NetworkDispatcherListener>();
    }
    
    public NetworkDispatcher(final BlockingQueue<Request> mQueue, final Network mNetwork, final Cache mCache, final ResponseDelivery mDelivery) {
        super("NetworkDispatcher");
        this.mQuit = false;
        this.mQueue = mQueue;
        this.mNetwork = mNetwork;
        this.mCache = mCache;
        this.mDelivery = mDelivery;
    }
    
    public static void addNetworkDispatcherListener(final NetworkDispatcher$NetworkDispatcherListener networkDispatcher$NetworkDispatcherListener) {
        NetworkDispatcher.sListener.put(networkDispatcher$NetworkDispatcherListener, networkDispatcher$NetworkDispatcherListener);
    }
    
    private void notifyOnFinished(final Request request) {
        request.markInFlight(false);
        for (final NetworkDispatcher$NetworkDispatcherListener networkDispatcher$NetworkDispatcherListener : NetworkDispatcher.sListener.keySet()) {
            if (networkDispatcher$NetworkDispatcherListener != null) {
                networkDispatcher$NetworkDispatcherListener.onCompleted(request);
            }
        }
    }
    
    private void notifyOnStarted(final Request request) {
        request.markInFlight(true);
        for (final NetworkDispatcher$NetworkDispatcherListener networkDispatcher$NetworkDispatcherListener : NetworkDispatcher.sListener.keySet()) {
            if (networkDispatcher$NetworkDispatcherListener != null) {
                networkDispatcher$NetworkDispatcherListener.onStarted(request);
            }
        }
    }
    
    private void parseAndDeliverNetworkError(final Request<?> request, VolleyError networkError) {
        networkError = request.parseNetworkError(networkError);
        this.mDelivery.postError(request, networkError);
    }
    
    public static void removeNetworkDispatcherListener(final NetworkDispatcher$NetworkDispatcherListener networkDispatcher$NetworkDispatcherListener) {
        NetworkDispatcher.sListener.remove(networkDispatcher$NetworkDispatcherListener);
    }
    
    public void quit() {
        this.mQuit = true;
        this.interrupt();
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            Label_0054: {
                Request<?> request;
                try {
                    while (true) {
                        final Request<?> request2;
                        request = (request2 = this.mQueue.take());
                        final String s = "network-queue-take";
                        request2.addMarker(s);
                        final Request<?> request3 = request;
                        final boolean b = request3.isCanceled();
                        if (!b) {
                            break Label_0054;
                        }
                        final Request<?> request4 = request;
                        final String s2 = "network-discard-cancelled";
                        request4.finish(s2);
                    }
                }
                catch (InterruptedException ex2) {
                    if (this.mQuit) {
                        return;
                    }
                    continue;
                }
                try {
                    final Request<?> request2 = request;
                    final String s = "network-queue-take";
                    request2.addMarker(s);
                    final Request<?> request3 = request;
                    final boolean b = request3.isCanceled();
                    if (b) {
                        final Request<?> request4 = request;
                        final String s2 = "network-discard-cancelled";
                        request4.finish(s2);
                        continue;
                    }
                    this.notifyOnStarted(request);
                    if (Build$VERSION.SDK_INT >= 14) {
                        TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
                    }
                    NetworkResponse performRequest;
                    if (request.getUrl().startsWith("file://")) {
                        performRequest = new NetworkResponse(200, VolleyFileUtils.readFileUrlToByteArray(request.getUrl()), new HashMap<String, String>(), false);
                    }
                    else {
                        performRequest = this.mNetwork.performRequest(request);
                    }
                    request.addMarker("network-http-complete");
                    if (performRequest.notModified && request.hasHadResponseDelivered()) {
                        request.finish("not-modified");
                        continue;
                    }
                    final Response<?> networkResponse = request.parseNetworkResponse(performRequest);
                    request.addMarker("network-parse-complete");
                    if (request.shouldCache() && networkResponse.cacheEntry != null) {
                        this.mCache.put(request.getCacheKey(), networkResponse.cacheEntry);
                        request.addMarker("network-cache-written");
                    }
                    request.markDelivered();
                    this.mDelivery.postResponse(request, networkResponse);
                    continue;
                }
                catch (VolleyError volleyError) {
                    this.parseAndDeliverNetworkError(request, volleyError);
                }
                catch (Exception ex) {
                    VolleyLog.e(ex, "Unhandled exception %s", ex.toString());
                    this.mDelivery.postError(request, new VolleyError(ex));
                }
                finally {
                    this.notifyOnFinished(request);
                }
            }
            break;
        }
    }
}
