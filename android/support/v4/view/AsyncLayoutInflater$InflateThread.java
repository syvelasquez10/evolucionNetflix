// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.support.v4.util.Pools$SynchronizedPool;
import java.util.concurrent.ArrayBlockingQueue;

class AsyncLayoutInflater$InflateThread extends Thread
{
    private static final AsyncLayoutInflater$InflateThread sInstance;
    private ArrayBlockingQueue<AsyncLayoutInflater$InflateRequest> mQueue;
    private Pools$SynchronizedPool<AsyncLayoutInflater$InflateRequest> mRequestPool;
    
    static {
        (sInstance = new AsyncLayoutInflater$InflateThread()).start();
    }
    
    private AsyncLayoutInflater$InflateThread() {
        this.mQueue = new ArrayBlockingQueue<AsyncLayoutInflater$InflateRequest>(10);
        this.mRequestPool = new Pools$SynchronizedPool<AsyncLayoutInflater$InflateRequest>(10);
    }
    
    public static AsyncLayoutInflater$InflateThread getInstance() {
        return AsyncLayoutInflater$InflateThread.sInstance;
    }
    
    public void enqueue(final AsyncLayoutInflater$InflateRequest asyncLayoutInflater$InflateRequest) {
        try {
            this.mQueue.put(asyncLayoutInflater$InflateRequest);
        }
        catch (InterruptedException ex) {
            throw new RuntimeException("Failed to enqueue async inflate request", ex);
        }
    }
    
    public AsyncLayoutInflater$InflateRequest obtainRequest() {
        AsyncLayoutInflater$InflateRequest asyncLayoutInflater$InflateRequest;
        if ((asyncLayoutInflater$InflateRequest = this.mRequestPool.acquire()) == null) {
            asyncLayoutInflater$InflateRequest = new AsyncLayoutInflater$InflateRequest();
        }
        return asyncLayoutInflater$InflateRequest;
    }
    
    public void releaseRequest(final AsyncLayoutInflater$InflateRequest asyncLayoutInflater$InflateRequest) {
        asyncLayoutInflater$InflateRequest.callback = null;
        asyncLayoutInflater$InflateRequest.inflater = null;
        asyncLayoutInflater$InflateRequest.parent = null;
        asyncLayoutInflater$InflateRequest.resid = 0;
        asyncLayoutInflater$InflateRequest.view = null;
        this.mRequestPool.release(asyncLayoutInflater$InflateRequest);
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        android/support/v4/view/AsyncLayoutInflater$InflateThread.mQueue:Ljava/util/concurrent/ArrayBlockingQueue;
        //     4: invokevirtual   java/util/concurrent/ArrayBlockingQueue.take:()Ljava/lang/Object;
        //     7: checkcast       Landroid/support/v4/view/AsyncLayoutInflater$InflateRequest;
        //    10: astore_1       
        //    11: aload_1        
        //    12: aload_1        
        //    13: getfield        android/support/v4/view/AsyncLayoutInflater$InflateRequest.inflater:Landroid/support/v4/view/AsyncLayoutInflater;
        //    16: getfield        android/support/v4/view/AsyncLayoutInflater.mInflater:Landroid/view/LayoutInflater;
        //    19: aload_1        
        //    20: getfield        android/support/v4/view/AsyncLayoutInflater$InflateRequest.resid:I
        //    23: aload_1        
        //    24: getfield        android/support/v4/view/AsyncLayoutInflater$InflateRequest.parent:Landroid/view/ViewGroup;
        //    27: iconst_0       
        //    28: invokevirtual   android/view/LayoutInflater.inflate:(ILandroid/view/ViewGroup;Z)Landroid/view/View;
        //    31: putfield        android/support/v4/view/AsyncLayoutInflater$InflateRequest.view:Landroid/view/View;
        //    34: aload_1        
        //    35: getfield        android/support/v4/view/AsyncLayoutInflater$InflateRequest.inflater:Landroid/support/v4/view/AsyncLayoutInflater;
        //    38: getfield        android/support/v4/view/AsyncLayoutInflater.mHandler:Landroid/os/Handler;
        //    41: iconst_0       
        //    42: aload_1        
        //    43: invokestatic    android/os/Message.obtain:(Landroid/os/Handler;ILjava/lang/Object;)Landroid/os/Message;
        //    46: invokevirtual   android/os/Message.sendToTarget:()V
        //    49: goto            0
        //    52: astore_1       
        //    53: ldc             "AsyncLayoutInflater"
        //    55: aload_1        
        //    56: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/Throwable;)I
        //    59: pop            
        //    60: goto            0
        //    63: astore_2       
        //    64: ldc             "AsyncLayoutInflater"
        //    66: ldc             "Failed to inflate resource in the background! Retrying on the UI thread"
        //    68: aload_2        
        //    69: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    72: pop            
        //    73: goto            34
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      11     52     63     Ljava/lang/InterruptedException;
        //  11     34     63     76     Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
}
