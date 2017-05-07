// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import java.util.concurrent.BlockingQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import android.os.Handler;
import android.os.Looper;
import java.util.Queue;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

public class RequestQueue
{
    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;
    private final Cache mCache;
    private CacheDispatcher mCacheDispatcher;
    private final PriorityBlockingQueue<Request> mCacheQueue;
    private final Set<Request> mCurrentRequests;
    private final ResponseDelivery mDelivery;
    private NetworkDispatcher[] mDispatchers;
    private final Network mNetwork;
    private final PriorityBlockingQueue<Request> mNetworkQueue;
    private AtomicInteger mSequenceGenerator;
    private final Map<String, Queue<Request>> mWaitingRequests;
    
    public RequestQueue(final Cache cache, final Network network) {
        this(cache, network, 4);
    }
    
    public RequestQueue(final Cache cache, final Network network, final int n) {
        this(cache, network, n, new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }
    
    public RequestQueue(final Cache mCache, final Network mNetwork, final int n, final ResponseDelivery mDelivery) {
        this.mSequenceGenerator = new AtomicInteger();
        this.mWaitingRequests = new HashMap<String, Queue<Request>>();
        this.mCurrentRequests = new HashSet<Request>();
        this.mCacheQueue = new PriorityBlockingQueue<Request>();
        this.mNetworkQueue = new PriorityBlockingQueue<Request>();
        this.mCache = mCache;
        this.mNetwork = mNetwork;
        this.mDispatchers = new NetworkDispatcher[n];
        this.mDelivery = mDelivery;
    }
    
    public Request add(final Request request) {
        request.setRequestQueue(this);
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.add(request);
            // monitorexit(this.mCurrentRequests)
            request.setSequence(this.getSequenceNumber());
            request.addMarker("add-to-queue");
            if (!request.shouldCache()) {
                this.mNetworkQueue.add(request);
                return request;
            }
        }
        while (true) {
            final Request request2;
            final String cacheKey;
            synchronized (this.mWaitingRequests) {
                cacheKey = request2.getCacheKey();
                if (this.mWaitingRequests.containsKey(cacheKey)) {
                    Queue<Request> queue;
                    if ((queue = this.mWaitingRequests.get(cacheKey)) == null) {
                        queue = new LinkedList<Request>();
                    }
                    queue.add(request2);
                    this.mWaitingRequests.put(cacheKey, queue);
                    if (VolleyLog.DEBUG) {
                        VolleyLog.v("Request for cacheKey=%s is in flight, putting on hold.", cacheKey);
                    }
                    return request2;
                }
            }
            this.mWaitingRequests.put(cacheKey, null);
            this.mCacheQueue.add(request2);
            return request2;
        }
    }
    
    public void cancelAll(final RequestQueue$RequestFilter requestQueue$RequestFilter) {
        synchronized (this.mCurrentRequests) {
            for (final Request<?> request : this.mCurrentRequests) {
                if (requestQueue$RequestFilter.apply(request)) {
                    VolleyLog.v("Cancelling req %s", request.getUrl());
                    request.cancel();
                }
            }
        }
    }
    // monitorexit(set)
    
    public void cancelAll(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        this.cancelAll(new RequestQueue$1(this, o));
    }
    
    void finish(final Request p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/android/volley/RequestQueue.mCurrentRequests:Ljava/util/Set;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/android/volley/RequestQueue.mCurrentRequests:Ljava/util/Set;
        //    11: aload_1        
        //    12: invokeinterface java/util/Set.remove:(Ljava/lang/Object;)Z
        //    17: pop            
        //    18: aload_2        
        //    19: monitorexit    
        //    20: aload_1        
        //    21: invokevirtual   com/android/volley/Request.shouldCache:()Z
        //    24: ifeq            99
        //    27: aload_0        
        //    28: getfield        com/android/volley/RequestQueue.mWaitingRequests:Ljava/util/Map;
        //    31: astore_2       
        //    32: aload_2        
        //    33: monitorenter   
        //    34: aload_1        
        //    35: invokevirtual   com/android/volley/Request.getCacheKey:()Ljava/lang/String;
        //    38: astore_1       
        //    39: aload_0        
        //    40: getfield        com/android/volley/RequestQueue.mWaitingRequests:Ljava/util/Map;
        //    43: aload_1        
        //    44: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    49: checkcast       Ljava/util/Queue;
        //    52: astore_3       
        //    53: aload_3        
        //    54: ifnull          97
        //    57: getstatic       com/android/volley/VolleyLog.DEBUG:Z
        //    60: ifeq            88
        //    63: ldc             "Releasing %d waiting requests for cacheKey=%s."
        //    65: iconst_2       
        //    66: anewarray       Ljava/lang/Object;
        //    69: dup            
        //    70: iconst_0       
        //    71: aload_3        
        //    72: invokeinterface java/util/Queue.size:()I
        //    77: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    80: aastore        
        //    81: dup            
        //    82: iconst_1       
        //    83: aload_1        
        //    84: aastore        
        //    85: invokestatic    com/android/volley/VolleyLog.v:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    88: aload_0        
        //    89: getfield        com/android/volley/RequestQueue.mCacheQueue:Ljava/util/concurrent/PriorityBlockingQueue;
        //    92: aload_3        
        //    93: invokevirtual   java/util/concurrent/PriorityBlockingQueue.addAll:(Ljava/util/Collection;)Z
        //    96: pop            
        //    97: aload_2        
        //    98: monitorexit    
        //    99: return         
        //   100: astore_1       
        //   101: aload_2        
        //   102: monitorexit    
        //   103: aload_1        
        //   104: athrow         
        //   105: astore_1       
        //   106: aload_2        
        //   107: monitorexit    
        //   108: aload_1        
        //   109: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      20     100    105    Any
        //  34     53     105    110    Any
        //  57     88     105    110    Any
        //  88     97     105    110    Any
        //  97     99     105    110    Any
        //  101    103    100    105    Any
        //  106    108    105    110    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Cache getCache() {
        return this.mCache;
    }
    
    public int getSequenceNumber() {
        return this.mSequenceGenerator.incrementAndGet();
    }
    
    public void start() {
        this.stop();
        (this.mCacheDispatcher = new CacheDispatcher(this.mCacheQueue, this.mNetworkQueue, this.mCache, this.mDelivery)).start();
        for (int i = 0; i < this.mDispatchers.length; ++i) {
            (this.mDispatchers[i] = new NetworkDispatcher(this.mNetworkQueue, this.mNetwork, this.mCache, this.mDelivery)).start();
        }
    }
    
    public void stop() {
        if (this.mCacheDispatcher != null) {
            this.mCacheDispatcher.quit();
        }
        for (int i = 0; i < this.mDispatchers.length; ++i) {
            if (this.mDispatchers[i] != null) {
                this.mDispatchers[i].quit();
            }
        }
    }
}
