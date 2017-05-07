// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;
import android.util.Log;
import com.google.android.gms.common.api.Releasable;
import java.util.Iterator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzx;
import android.os.Looper;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.PendingResult$zza;
import java.util.ArrayList;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

public abstract class zzlc<R extends Result> extends PendingResult<R>
{
    private boolean zzL;
    private volatile R zzaaX;
    private final Object zzabh;
    protected final zzlc$zza<R> zzabi;
    private final ArrayList<PendingResult$zza> zzabj;
    private ResultCallback<? super R> zzabk;
    private volatile boolean zzabl;
    private boolean zzabm;
    private zzq zzabn;
    private Integer zzabo;
    private volatile zzlq<R> zzabp;
    private final CountDownLatch zzoS;
    
    protected zzlc(final Looper looper) {
        this.zzabh = new Object();
        this.zzoS = new CountDownLatch(1);
        this.zzabj = new ArrayList<PendingResult$zza>();
        this.zzabi = new zzlc$zza<R>(looper);
    }
    
    private R get() {
        boolean b = true;
        synchronized (this.zzabh) {
            if (this.zzabl) {
                b = false;
            }
            zzx.zza(b, "Result has already been consumed.");
            zzx.zza(this.isReady(), "Result is not ready.");
            final Result zzaaX = this.zzaaX;
            this.zzaaX = null;
            this.zzabk = null;
            this.zzabl = true;
            // monitorexit(this.zzabh)
            this.zznL();
            return (R)zzaaX;
        }
    }
    
    private void zzc(final R zzaaX) {
        this.zzaaX = zzaaX;
        this.zzabn = null;
        this.zzoS.countDown();
        final Status status = this.zzaaX.getStatus();
        if (this.zzabk != null) {
            this.zzabi.zznM();
            if (!this.zzL) {
                this.zzabi.zza(this.zzabk, this.get());
            }
        }
        final Iterator<PendingResult$zza> iterator = this.zzabj.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzt(status);
        }
        this.zzabj.clear();
    }
    
    public static void zzd(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            Log.w("BasePendingResult", "Unable to release " + result, (Throwable)ex);
        }
    }
    
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzlc.zzabh:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/zzlc.zzL:Z
        //    11: ifne            21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/internal/zzlc.zzabl:Z
        //    18: ifeq            24
        //    21: aload_1        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/internal/zzlc.zzabn:Lcom/google/android/gms/common/internal/zzq;
        //    28: astore_2       
        //    29: aload_2        
        //    30: ifnull          42
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/internal/zzlc.zzabn:Lcom/google/android/gms/common/internal/zzq;
        //    37: invokeinterface com/google/android/gms/common/internal/zzq.cancel:()V
        //    42: aload_0        
        //    43: getfield        com/google/android/gms/internal/zzlc.zzaaX:Lcom/google/android/gms/common/api/Result;
        //    46: invokestatic    com/google/android/gms/internal/zzlc.zzd:(Lcom/google/android/gms/common/api/Result;)V
        //    49: aload_0        
        //    50: aconst_null    
        //    51: putfield        com/google/android/gms/internal/zzlc.zzabk:Lcom/google/android/gms/common/api/ResultCallback;
        //    54: aload_0        
        //    55: iconst_1       
        //    56: putfield        com/google/android/gms/internal/zzlc.zzL:Z
        //    59: aload_0        
        //    60: aload_0        
        //    61: getstatic       com/google/android/gms/common/api/Status.zzabf:Lcom/google/android/gms/common/api/Status;
        //    64: invokevirtual   com/google/android/gms/internal/zzlc.zzb:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
        //    67: invokespecial   com/google/android/gms/internal/zzlc.zzc:(Lcom/google/android/gms/common/api/Result;)V
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
        synchronized (this.zzabh) {
            return this.zzL;
        }
    }
    
    public final boolean isReady() {
        return this.zzoS.getCount() == 0L;
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<? super R> zzabk) {
        while (true) {
            final boolean b = true;
            zzx.zza(!this.zzabl, "Result has already been consumed.");
            while (true) {
                Label_0094: {
                    synchronized (this.zzabh) {
                        if (this.zzabp != null) {
                            break Label_0094;
                        }
                        final boolean b2 = b;
                        zzx.zza(b2, "Cannot set callbacks if then() has been called.");
                        if (this.isCanceled()) {
                            return;
                        }
                        if (this.isReady()) {
                            this.zzabi.zza(zzabk, this.get());
                            return;
                        }
                    }
                    this.zzabk = zzabk;
                    return;
                }
                final boolean b2 = false;
                continue;
            }
        }
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<? super R> zzabk, final long n, final TimeUnit timeUnit) {
        while (true) {
            final boolean b = true;
            zzx.zza(!this.zzabl, "Result has already been consumed.");
            while (true) {
                Label_0115: {
                    synchronized (this.zzabh) {
                        if (this.zzabp != null) {
                            break Label_0115;
                        }
                        final boolean b2 = b;
                        zzx.zza(b2, "Cannot set callbacks if then() has been called.");
                        if (this.isCanceled()) {
                            return;
                        }
                        if (this.isReady()) {
                            this.zzabi.zza(zzabk, this.get());
                            return;
                        }
                    }
                    this.zzabk = zzabk;
                    this.zzabi.zza(this, timeUnit.toMillis(n));
                    return;
                }
                final boolean b2 = false;
                continue;
            }
        }
    }
    
    protected abstract R zzb(final Status p0);
    
    public final void zzb(final R r) {
    Label_0057_Outer:
        while (true) {
            final boolean b = true;
            while (true) {
            Label_0083:
                while (true) {
                    synchronized (this.zzabh) {
                        if (this.zzabm || this.zzL) {
                            zzd(r);
                            return;
                        }
                        if (!this.isReady()) {
                            final boolean b2 = true;
                            zzx.zza(b2, "Results have already been set");
                            if (!this.zzabl) {
                                final boolean b3 = b;
                                zzx.zza(b3, "Result has already been consumed");
                                this.zzc(r);
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
    public Integer zznF() {
        return this.zzabo;
    }
    
    protected void zznL() {
    }
    
    public final void zzw(final Status status) {
        synchronized (this.zzabh) {
            if (!this.isReady()) {
                this.zzb(this.zzb(status));
                this.zzabm = true;
            }
        }
    }
}
