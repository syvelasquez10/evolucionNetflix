// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Message;
import android.util.Pair;
import android.os.Handler;
import android.os.DeadObjectException;
import android.app.PendingIntent;
import android.os.RemoteException;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.internal.fq;
import android.util.Log;
import java.util.Iterator;
import android.os.Looper;
import com.google.android.gms.internal.fk;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class a
{
    public abstract static class a<R extends Result> implements PendingResult<R>, d<R>
    {
        private final Object AB;
        private c<R> AC;
        private final CountDownLatch AD;
        private final ArrayList<PendingResult.a> AE;
        private ResultCallback<R> AF;
        private volatile R AG;
        private volatile boolean AH;
        private boolean AI;
        private boolean AJ;
        private fk AK;
        
        a() {
            this.AB = new Object();
            this.AD = new CountDownLatch(1);
            this.AE = new ArrayList<PendingResult.a>();
        }
        
        public a(final Looper looper) {
            this.AB = new Object();
            this.AD = new CountDownLatch(1);
            this.AE = new ArrayList<PendingResult.a>();
            this.AC = (c<R>)new c(looper);
        }
        
        public a(final c<R> ac) {
            this.AB = new Object();
            this.AD = new CountDownLatch(1);
            this.AE = new ArrayList<PendingResult.a>();
            this.AC = ac;
        }
        
        private void b(final R ag) {
            this.AG = ag;
            this.AK = null;
            this.AD.countDown();
            final Status status = this.AG.getStatus();
            if (this.AF != null) {
                this.AC.eg();
                if (!this.AI) {
                    this.AC.a(this.AF, this.eb());
                }
            }
            final Iterator<PendingResult.a> iterator = this.AE.iterator();
            while (iterator.hasNext()) {
                iterator.next().l(status);
            }
            this.AE.clear();
        }
        
        private void c(final Result result) {
            if (!(result instanceof Releasable)) {
                return;
            }
            try {
                ((Releasable)result).release();
            }
            catch (Exception ex) {
                Log.w("AbstractPendingResult", "Unable to release " + this, (Throwable)ex);
            }
        }
        
        private R eb() {
            while (true) {
                while (true) {
                    synchronized (this.AB) {
                        if (!this.AH) {
                            final boolean b = true;
                            fq.a(b, (Object)"Result has already been consumed.");
                            fq.a(this.isReady(), (Object)"Result is not ready.");
                            final Result ag = this.AG;
                            this.ec();
                            return (R)ag;
                        }
                    }
                    final boolean b = false;
                    continue;
                }
            }
        }
        
        private void ed() {
            synchronized (this.AB) {
                if (!this.isReady()) {
                    this.a(this.d(Status.Bw));
                    this.AJ = true;
                }
            }
        }
        
        private void ee() {
            synchronized (this.AB) {
                if (!this.isReady()) {
                    this.a(this.d(Status.By));
                    this.AJ = true;
                }
            }
        }
        
        @Override
        public final void a(final PendingResult.a a) {
            while (true) {
                Label_0064: {
                    if (this.AH) {
                        break Label_0064;
                    }
                    final boolean b = true;
                    fq.a(b, (Object)"Result has already been consumed.");
                    synchronized (this.AB) {
                        if (this.isReady()) {
                            a.l(this.AG.getStatus());
                        }
                        else {
                            this.AE.add(a);
                        }
                        return;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
        
        public final void a(final R r) {
        Label_0058_Outer:
            while (true) {
                final boolean b = true;
                while (true) {
                Label_0084:
                    while (true) {
                        synchronized (this.AB) {
                            if (this.AJ || this.AI) {
                                this.c(r);
                                return;
                            }
                            if (!this.isReady()) {
                                final boolean b2 = true;
                                fq.a(b2, (Object)"Results have already been set");
                                if (!this.AH) {
                                    final boolean b3 = b;
                                    fq.a(b3, (Object)"Result has already been consumed");
                                    this.b((Object)r);
                                    return;
                                }
                                break Label_0084;
                            }
                        }
                        final boolean b2 = false;
                        continue Label_0058_Outer;
                    }
                    final boolean b3 = false;
                    continue;
                }
            }
        }
        
        protected void a(final c<R> ac) {
            this.AC = ac;
        }
        
        protected final void a(final fk ak) {
            synchronized (this.AB) {
                this.AK = ak;
            }
        }
        
        @Override
        public final R await() {
            final boolean b = false;
            Label_0064: {
                if (this.AH) {
                    break Label_0064;
                }
                boolean b2 = true;
            Label_0050_Outer:
                while (true) {
                    fq.a(b2, (Object)"Result has already been consumed");
                    boolean b3 = false;
                    Label_0037: {
                        if (!this.isReady()) {
                            b3 = b;
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                break Label_0037;
                            }
                        }
                        b3 = true;
                    }
                    fq.a(b3, (Object)"await must not be called on the UI thread");
                    while (true) {
                        try {
                            this.AD.await();
                            fq.a(this.isReady(), (Object)"Result is not ready.");
                            return this.eb();
                            b2 = false;
                            continue Label_0050_Outer;
                        }
                        catch (InterruptedException ex) {
                            this.ed();
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
        }
        
        @Override
        public final R await(final long n, final TimeUnit timeUnit) {
            final boolean b = false;
            Label_0080: {
                if (this.AH) {
                    break Label_0080;
                }
                boolean b2 = true;
            Label_0066_Outer:
                while (true) {
                    fq.a(b2, (Object)"Result has already been consumed.");
                    boolean b3 = false;
                    Label_0043: {
                        if (!this.isReady()) {
                            b3 = b;
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                break Label_0043;
                            }
                        }
                        b3 = true;
                    }
                    fq.a(b3, (Object)"await must not be called on the UI thread");
                    while (true) {
                        try {
                            if (!this.AD.await(n, timeUnit)) {
                                this.ee();
                            }
                            fq.a(this.isReady(), (Object)"Result is not ready.");
                            return this.eb();
                            b2 = false;
                            continue Label_0066_Outer;
                        }
                        catch (InterruptedException ex) {
                            this.ed();
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
        }
        
        @Override
        public void cancel() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/google/android/gms/common/api/a$a.AB:Ljava/lang/Object;
            //     4: astore_1       
            //     5: aload_1        
            //     6: monitorenter   
            //     7: aload_0        
            //     8: getfield        com/google/android/gms/common/api/a$a.AI:Z
            //    11: ifeq            17
            //    14: aload_1        
            //    15: monitorexit    
            //    16: return         
            //    17: aload_0        
            //    18: getfield        com/google/android/gms/common/api/a$a.AK:Lcom/google/android/gms/internal/fk;
            //    21: astore_2       
            //    22: aload_2        
            //    23: ifnull          35
            //    26: aload_0        
            //    27: getfield        com/google/android/gms/common/api/a$a.AK:Lcom/google/android/gms/internal/fk;
            //    30: invokeinterface com/google/android/gms/internal/fk.cancel:()V
            //    35: aload_0        
            //    36: aload_0        
            //    37: getfield        com/google/android/gms/common/api/a$a.AG:Lcom/google/android/gms/common/api/Result;
            //    40: invokespecial   com/google/android/gms/common/api/a$a.c:(Lcom/google/android/gms/common/api/Result;)V
            //    43: aload_0        
            //    44: aconst_null    
            //    45: putfield        com/google/android/gms/common/api/a$a.AF:Lcom/google/android/gms/common/api/ResultCallback;
            //    48: aload_0        
            //    49: iconst_1       
            //    50: putfield        com/google/android/gms/common/api/a$a.AI:Z
            //    53: aload_0        
            //    54: aload_0        
            //    55: getstatic       com/google/android/gms/common/api/Status.Bz:Lcom/google/android/gms/common/api/Status;
            //    58: invokevirtual   com/google/android/gms/common/api/a$a.d:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
            //    61: invokespecial   com/google/android/gms/common/api/a$a.b:(Lcom/google/android/gms/common/api/Result;)V
            //    64: aload_1        
            //    65: monitorexit    
            //    66: return         
            //    67: astore_2       
            //    68: aload_1        
            //    69: monitorexit    
            //    70: aload_2        
            //    71: athrow         
            //    72: astore_2       
            //    73: goto            35
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                        
            //  -----  -----  -----  -----  ----------------------------
            //  7      16     67     72     Any
            //  17     22     67     72     Any
            //  26     35     72     76     Landroid/os/RemoteException;
            //  26     35     67     72     Any
            //  35     66     67     72     Any
            //  68     70     67     72     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        protected abstract R d(final Status p0);
        
        protected void ec() {
            this.AH = true;
            this.AG = null;
            this.AF = null;
        }
        
        @Override
        public boolean isCanceled() {
            synchronized (this.AB) {
                return this.AI;
            }
        }
        
        public final boolean isReady() {
            return this.AD.getCount() == 0L;
        }
        
        @Override
        public final void setResultCallback(final ResultCallback<R> resultCallback) {
            while (true) {
                fq.a(!this.AH, (Object)"Result has already been consumed.");
                synchronized (this.AB) {
                    if (this.isCanceled()) {
                        return;
                    }
                    if (this.isReady()) {
                        this.AC.a(resultCallback, this.eb());
                        return;
                    }
                }
                final ResultCallback<R> af;
                this.AF = af;
            }
        }
        
        @Override
        public final void setResultCallback(final ResultCallback<R> resultCallback, final long n, final TimeUnit timeUnit) {
            while (true) {
                fq.a(!this.AH, (Object)"Result has already been consumed.");
                synchronized (this.AB) {
                    if (this.isCanceled()) {
                        return;
                    }
                    if (this.isReady()) {
                        this.AC.a(resultCallback, this.eb());
                        return;
                    }
                }
                final ResultCallback<R> af;
                this.AF = af;
                this.AC.a(this, timeUnit.toMillis(n));
            }
        }
    }
    
    public abstract static class b<R extends Result, A extends Api.a> extends a<R> implements com.google.android.gms.common.api.b.c<A>
    {
        private com.google.android.gms.common.api.b.a AL;
        private final Api.c<A> Az;
        
        protected b(final Api.c<A> c) {
            this.Az = (Api.c<A>)fq.f((Api.c)c);
        }
        
        private void a(final RemoteException ex) {
            this.k(new Status(8, ex.getLocalizedMessage(), null));
        }
        
        protected abstract void a(final A p0) throws RemoteException;
        
        @Override
        public void a(final com.google.android.gms.common.api.b.a al) {
            this.AL = al;
        }
        
        @Override
        public final void b(final A a) throws DeadObjectException {
            this.a((c<R>)new c(((Api.a)a).getLooper()));
            try {
                this.a(a);
            }
            catch (DeadObjectException ex) {
                this.a((RemoteException)ex);
                throw ex;
            }
            catch (RemoteException ex2) {
                this.a(ex2);
            }
        }
        
        @Override
        public final Api.c<A> ea() {
            return this.Az;
        }
        
        @Override
        protected void ec() {
            super.ec();
            if (this.AL != null) {
                this.AL.b(this);
                this.AL = null;
            }
        }
        
        @Override
        public int ef() {
            return 0;
        }
        
        @Override
        public final void k(final Status status) {
            fq.b(!status.isSuccess(), "Failed result must not be success");
            this.a(this.d(status));
        }
    }
    
    public static class c<R extends Result> extends Handler
    {
        public c() {
            this(Looper.getMainLooper());
        }
        
        public c(final Looper looper) {
            super(looper);
        }
        
        public void a(final ResultCallback<R> resultCallback, final R r) {
            this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)resultCallback, (Object)r)));
        }
        
        public void a(final a<R> a, final long n) {
            this.sendMessageDelayed(this.obtainMessage(2, (Object)a), n);
        }
        
        protected void b(final ResultCallback<R> resultCallback, final R r) {
            resultCallback.onResult(r);
        }
        
        public void eg() {
            this.removeMessages(2);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.wtf("GoogleApi", "Don't know how to handle this message.");
                }
                case 1: {
                    final Pair pair = (Pair)message.obj;
                    this.b((ResultCallback<Result>)pair.first, (Result)pair.second);
                }
                case 2: {
                    ((a<Result>)message.obj).ee();
                }
            }
        }
    }
    
    public interface d<R>
    {
        void b(final R p0);
    }
}
