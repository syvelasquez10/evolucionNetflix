// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.zzu;
import android.util.Log;
import java.util.Iterator;
import android.os.Looper;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.common.internal.ICancelToken;
import java.util.ArrayList;

public abstract class AbstractPendingResult<R extends Result> implements PendingResult<R>
{
    protected final AbstractPendingResult$CallbackHandler<R> mHandler;
    private boolean zzL;
    private final Object zzWa;
    private final ArrayList<PendingResult$BatchCallback> zzWb;
    private ResultCallback<R> zzWc;
    private volatile R zzWd;
    private volatile boolean zzWe;
    private boolean zzWf;
    private ICancelToken zzWg;
    private final CountDownLatch zzoD;
    
    protected AbstractPendingResult(final Looper looper) {
        this.zzWa = new Object();
        this.zzoD = new CountDownLatch(1);
        this.zzWb = new ArrayList<PendingResult$BatchCallback>();
        this.mHandler = new AbstractPendingResult$CallbackHandler<R>(looper);
    }
    
    private void zza(final R zzWd) {
        this.zzWd = zzWd;
        this.zzWg = null;
        this.zzoD.countDown();
        final Status status = this.zzWd.getStatus();
        if (this.zzWc != null) {
            this.mHandler.removeTimeoutMessages();
            if (!this.zzL) {
                this.mHandler.sendResultCallback(this.zzWc, this.zzmm());
            }
        }
        final Iterator<PendingResult$BatchCallback> iterator = this.zzWb.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzs(status);
        }
        this.zzWb.clear();
    }
    
    static void zzb(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            Log.w("AbstractPendingResult", "Unable to release " + result, (Throwable)ex);
        }
    }
    
    private R zzmm() {
        boolean b = true;
        synchronized (this.zzWa) {
            if (this.zzWe) {
                b = false;
            }
            zzu.zza(b, "Result has already been consumed.");
            zzu.zza(this.isReady(), "Result is not ready.");
            final Result zzWd = this.zzWd;
            this.zzWd = null;
            this.zzWc = null;
            this.zzWe = true;
            // monitorexit(this.zzWa)
            this.onResultConsumed();
            return (R)zzWd;
        }
    }
    
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/common/api/AbstractPendingResult.zzWa:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/common/api/AbstractPendingResult.zzL:Z
        //    11: ifne            21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/common/api/AbstractPendingResult.zzWe:Z
        //    18: ifeq            24
        //    21: aload_1        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/common/api/AbstractPendingResult.zzWg:Lcom/google/android/gms/common/internal/ICancelToken;
        //    28: astore_2       
        //    29: aload_2        
        //    30: ifnull          42
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/common/api/AbstractPendingResult.zzWg:Lcom/google/android/gms/common/internal/ICancelToken;
        //    37: invokeinterface com/google/android/gms/common/internal/ICancelToken.cancel:()V
        //    42: aload_0        
        //    43: getfield        com/google/android/gms/common/api/AbstractPendingResult.zzWd:Lcom/google/android/gms/common/api/Result;
        //    46: invokestatic    com/google/android/gms/common/api/AbstractPendingResult.zzb:(Lcom/google/android/gms/common/api/Result;)V
        //    49: aload_0        
        //    50: aconst_null    
        //    51: putfield        com/google/android/gms/common/api/AbstractPendingResult.zzWc:Lcom/google/android/gms/common/api/ResultCallback;
        //    54: aload_0        
        //    55: iconst_1       
        //    56: putfield        com/google/android/gms/common/api/AbstractPendingResult.zzL:Z
        //    59: aload_0        
        //    60: aload_0        
        //    61: getstatic       com/google/android/gms/common/api/Status.zzXS:Lcom/google/android/gms/common/api/Status;
        //    64: invokevirtual   com/google/android/gms/common/api/AbstractPendingResult.createFailedResult:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
        //    67: invokespecial   com/google/android/gms/common/api/AbstractPendingResult.zza:(Lcom/google/android/gms/common/api/Result;)V
        //    70: aload_1        
        //    71: monitorexit    
        //    72: return         
        //    73: astore_2       
        //    74: aload_1        
        //    75: monitorexit    
        //    76: aload_2        
        //    77: athrow         
        //    78: astore_2       
        //    79: goto            42
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      21     73     78     Any
        //  21     23     73     78     Any
        //  24     29     73     78     Any
        //  33     42     78     82     Landroid/os/RemoteException;
        //  33     42     73     78     Any
        //  42     72     73     78     Any
        //  74     76     73     78     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
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
    
    protected abstract R createFailedResult(final Status p0);
    
    public final void forceFailureUnlessReady(final Status status) {
        synchronized (this.zzWa) {
            if (!this.isReady()) {
                this.setResult(this.createFailedResult(status));
                this.zzWf = true;
            }
        }
    }
    
    public boolean isCanceled() {
        synchronized (this.zzWa) {
            return this.zzL;
        }
    }
    
    public final boolean isReady() {
        return this.zzoD.getCount() == 0L;
    }
    
    protected void onResultConsumed() {
    }
    
    public final void setResult(final R r) {
    Label_0057_Outer:
        while (true) {
            final boolean b = true;
            while (true) {
            Label_0083:
                while (true) {
                    synchronized (this.zzWa) {
                        if (this.zzWf || this.zzL) {
                            zzb(r);
                            return;
                        }
                        if (!this.isReady()) {
                            final boolean b2 = true;
                            zzu.zza(b2, "Results have already been set");
                            if (!this.zzWe) {
                                final boolean b3 = b;
                                zzu.zza(b3, "Result has already been consumed");
                                this.zza(r);
                                return;
                            }
                            break Label_0083;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0057_Outer;
                }
                final boolean b3 = false;
                continue;
            }
        }
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<R> resultCallback) {
        while (true) {
            zzu.zza(!this.zzWe, "Result has already been consumed.");
            synchronized (this.zzWa) {
                if (this.isCanceled()) {
                    return;
                }
                if (this.isReady()) {
                    this.mHandler.sendResultCallback(resultCallback, this.zzmm());
                    return;
                }
            }
            final ResultCallback<R> zzWc;
            this.zzWc = zzWc;
        }
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<R> resultCallback, final long n, final TimeUnit timeUnit) {
        while (true) {
            zzu.zza(!this.zzWe, "Result has already been consumed.");
            synchronized (this.zzWa) {
                if (this.isCanceled()) {
                    return;
                }
                if (this.isReady()) {
                    this.mHandler.sendResultCallback(resultCallback, this.zzmm());
                    return;
                }
            }
            final ResultCallback<R> zzWc;
            this.zzWc = zzWc;
            this.mHandler.sendTimeoutResultCallback(this, timeUnit.toMillis(n));
        }
    }
}
