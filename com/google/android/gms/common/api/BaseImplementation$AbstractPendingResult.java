// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.n;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.common.internal.i;
import java.util.ArrayList;

public abstract class BaseImplementation$AbstractPendingResult<R extends Result> implements BaseImplementation$b<R>, PendingResult<R>
{
    private final Object Im;
    private final ArrayList<PendingResult$a> In;
    private ResultCallback<R> Io;
    private volatile R Ip;
    private volatile boolean Iq;
    private boolean Ir;
    private boolean Is;
    private i It;
    protected BaseImplementation$CallbackHandler<R> mHandler;
    private final CountDownLatch mg;
    
    BaseImplementation$AbstractPendingResult() {
        this.Im = new Object();
        this.mg = new CountDownLatch(1);
        this.In = new ArrayList<PendingResult$a>();
    }
    
    private void c(final R ip) {
        this.Ip = ip;
        this.It = null;
        this.mg.countDown();
        final Status status = this.Ip.getStatus();
        if (this.Io != null) {
            this.mHandler.removeTimeoutMessages();
            if (!this.Ir) {
                this.mHandler.sendResultCallback(this.Io, this.gg());
            }
        }
        final Iterator<PendingResult$a> iterator = this.In.iterator();
        while (iterator.hasNext()) {
            iterator.next().n(status);
        }
        this.In.clear();
    }
    
    private R gg() {
        while (true) {
            while (true) {
                synchronized (this.Im) {
                    if (!this.Iq) {
                        final boolean b = true;
                        n.a(b, "Result has already been consumed.");
                        n.a(this.isReady(), "Result is not ready.");
                        final Result ip = this.Ip;
                        this.gh();
                        return (R)ip;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    private void gj() {
        synchronized (this.Im) {
            if (!this.isReady()) {
                this.b(this.c(Status.Jr));
                this.Is = true;
            }
        }
    }
    
    protected void a(final BaseImplementation$CallbackHandler<R> mHandler) {
        this.mHandler = mHandler;
    }
    
    @Override
    public final void b(final R r) {
    Label_0057_Outer:
        while (true) {
            final boolean b = true;
            while (true) {
            Label_0083:
                while (true) {
                    synchronized (this.Im) {
                        if (this.Is || this.Ir) {
                            BaseImplementation.a(r);
                            return;
                        }
                        if (!this.isReady()) {
                            final boolean b2 = true;
                            n.a(b2, "Results have already been set");
                            if (!this.Iq) {
                                final boolean b3 = b;
                                n.a(b3, "Result has already been consumed");
                                this.c(r);
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
    
    protected abstract R c(final Status p0);
    
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.Im:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.Ir:Z
        //    11: ifne            21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.Iq:Z
        //    18: ifeq            24
        //    21: aload_1        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.It:Lcom/google/android/gms/common/internal/i;
        //    28: astore_2       
        //    29: aload_2        
        //    30: ifnull          42
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.It:Lcom/google/android/gms/common/internal/i;
        //    37: invokeinterface com/google/android/gms/common/internal/i.cancel:()V
        //    42: aload_0        
        //    43: getfield        com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.Ip:Lcom/google/android/gms/common/api/Result;
        //    46: invokestatic    com/google/android/gms/common/api/BaseImplementation.a:(Lcom/google/android/gms/common/api/Result;)V
        //    49: aload_0        
        //    50: aconst_null    
        //    51: putfield        com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.Io:Lcom/google/android/gms/common/api/ResultCallback;
        //    54: aload_0        
        //    55: iconst_1       
        //    56: putfield        com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.Ir:Z
        //    59: aload_0        
        //    60: aload_0        
        //    61: getstatic       com/google/android/gms/common/api/Status.Js:Lcom/google/android/gms/common/api/Status;
        //    64: invokevirtual   com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.c:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
        //    67: invokespecial   com/google/android/gms/common/api/BaseImplementation$AbstractPendingResult.c:(Lcom/google/android/gms/common/api/Result;)V
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
    
    protected void gh() {
        this.Iq = true;
        this.Ip = null;
        this.Io = null;
    }
    
    public boolean isCanceled() {
        synchronized (this.Im) {
            return this.Ir;
        }
    }
    
    public final boolean isReady() {
        return this.mg.getCount() == 0L;
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<R> resultCallback) {
        while (true) {
            n.a(!this.Iq, "Result has already been consumed.");
            synchronized (this.Im) {
                if (this.isCanceled()) {
                    return;
                }
                if (this.isReady()) {
                    this.mHandler.sendResultCallback(resultCallback, this.gg());
                    return;
                }
            }
            final ResultCallback<R> io;
            this.Io = io;
        }
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<R> resultCallback, final long n, final TimeUnit timeUnit) {
        while (true) {
            final boolean b = true;
            n.a(!this.Iq, "Result has already been consumed.");
            n.a(this.mHandler != null && b, "CallbackHandler has not been set before calling setResultCallback.");
            synchronized (this.Im) {
                if (this.isCanceled()) {
                    return;
                }
                if (this.isReady()) {
                    this.mHandler.sendResultCallback(resultCallback, this.gg());
                    return;
                }
            }
            final ResultCallback<R> io;
            this.Io = io;
            this.mHandler.sendTimeoutResultCallback(this, timeUnit.toMillis(n));
        }
    }
}
