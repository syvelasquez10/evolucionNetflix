// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;
import android.util.Log;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzx;
import android.os.Looper;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.common.internal.zzq;
import java.util.ArrayList;

public abstract class zzb<R extends Result> implements PendingResult<R>
{
    private boolean zzL;
    private final Object zzYD;
    protected final zzb$zza<R> zzYE;
    private final ArrayList<PendingResult$BatchCallback> zzYF;
    private ResultCallback<R> zzYG;
    private volatile R zzYH;
    private volatile boolean zzYI;
    private boolean zzYJ;
    private zzq zzYK;
    private final CountDownLatch zzoR;
    
    protected zzb(final Looper looper) {
        this.zzYD = new Object();
        this.zzoR = new CountDownLatch(1);
        this.zzYF = new ArrayList<PendingResult$BatchCallback>();
        this.zzYE = new zzb$zza<R>(looper);
    }
    
    private R get() {
        boolean b = true;
        synchronized (this.zzYD) {
            if (this.zzYI) {
                b = false;
            }
            zzx.zza(b, "Result has already been consumed.");
            zzx.zza(this.isReady(), "Result is not ready.");
            final Result zzYH = this.zzYH;
            this.zzYH = null;
            this.zzYG = null;
            this.zzYI = true;
            // monitorexit(this.zzYD)
            this.zzmZ();
            return (R)zzYH;
        }
    }
    
    private void zzb(final R zzYH) {
        this.zzYH = zzYH;
        this.zzYK = null;
        this.zzoR.countDown();
        final Status status = this.zzYH.getStatus();
        if (this.zzYG != null) {
            this.zzYE.zzna();
            if (!this.zzL) {
                this.zzYE.zza(this.zzYG, this.get());
            }
        }
        final Iterator<PendingResult$BatchCallback> iterator = this.zzYF.iterator();
        while (iterator.hasNext()) {
            iterator.next().onComplete(status);
        }
        this.zzYF.clear();
    }
    
    static void zzc(final Result result) {
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
    
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/common/api/zzb.zzYD:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/common/api/zzb.zzL:Z
        //    11: ifne            21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/common/api/zzb.zzYI:Z
        //    18: ifeq            24
        //    21: aload_1        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/common/api/zzb.zzYK:Lcom/google/android/gms/common/internal/zzq;
        //    28: astore_2       
        //    29: aload_2        
        //    30: ifnull          42
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/common/api/zzb.zzYK:Lcom/google/android/gms/common/internal/zzq;
        //    37: invokeinterface com/google/android/gms/common/internal/zzq.cancel:()V
        //    42: aload_0        
        //    43: getfield        com/google/android/gms/common/api/zzb.zzYH:Lcom/google/android/gms/common/api/Result;
        //    46: invokestatic    com/google/android/gms/common/api/zzb.zzc:(Lcom/google/android/gms/common/api/Result;)V
        //    49: aload_0        
        //    50: aconst_null    
        //    51: putfield        com/google/android/gms/common/api/zzb.zzYG:Lcom/google/android/gms/common/api/ResultCallback;
        //    54: aload_0        
        //    55: iconst_1       
        //    56: putfield        com/google/android/gms/common/api/zzb.zzL:Z
        //    59: aload_0        
        //    60: aload_0        
        //    61: getstatic       com/google/android/gms/common/api/Status.zzaaH:Lcom/google/android/gms/common/api/Status;
        //    64: invokevirtual   com/google/android/gms/common/api/zzb.zzb:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
        //    67: invokespecial   com/google/android/gms/common/api/zzb.zzb:(Lcom/google/android/gms/common/api/Result;)V
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
    
    public boolean isCanceled() {
        synchronized (this.zzYD) {
            return this.zzL;
        }
    }
    
    public final boolean isReady() {
        return this.zzoR.getCount() == 0L;
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<R> resultCallback) {
        while (true) {
            zzx.zza(!this.zzYI, "Result has already been consumed.");
            synchronized (this.zzYD) {
                if (this.isCanceled()) {
                    return;
                }
                if (this.isReady()) {
                    this.zzYE.zza(resultCallback, this.get());
                    return;
                }
            }
            final ResultCallback<R> zzYG;
            this.zzYG = zzYG;
        }
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<R> resultCallback, final long n, final TimeUnit timeUnit) {
        while (true) {
            zzx.zza(!this.zzYI, "Result has already been consumed.");
            synchronized (this.zzYD) {
                if (this.isCanceled()) {
                    return;
                }
                if (this.isReady()) {
                    this.zzYE.zza(resultCallback, this.get());
                    return;
                }
            }
            final ResultCallback<R> zzYG;
            this.zzYG = zzYG;
            this.zzYE.zza(this, timeUnit.toMillis(n));
        }
    }
    
    public final void zza(final R r) {
    Label_0057_Outer:
        while (true) {
            final boolean b = true;
            while (true) {
            Label_0083:
                while (true) {
                    synchronized (this.zzYD) {
                        if (this.zzYJ || this.zzL) {
                            zzc(r);
                            return;
                        }
                        if (!this.isReady()) {
                            final boolean b2 = true;
                            zzx.zza(b2, "Results have already been set");
                            if (!this.zzYI) {
                                final boolean b3 = b;
                                zzx.zza(b3, "Result has already been consumed");
                                this.zzb(r);
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
    
    protected abstract R zzb(final Status p0);
    
    protected void zzmZ() {
    }
    
    public final void zzw(final Status status) {
        synchronized (this.zzYD) {
            if (!this.isReady()) {
                this.zza(this.zzb(status));
                this.zzYJ = true;
            }
        }
    }
}
