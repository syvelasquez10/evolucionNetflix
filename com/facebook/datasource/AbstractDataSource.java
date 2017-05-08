// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.datasource;

import com.facebook.common.internal.Preconditions;
import java.util.Iterator;
import java.util.concurrent.Executor;
import android.util.Pair;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractDataSource<T> implements DataSource<T>
{
    private AbstractDataSource$DataSourceStatus mDataSourceStatus;
    private Throwable mFailureThrowable;
    private boolean mIsClosed;
    private float mProgress;
    private T mResult;
    private final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers;
    
    protected AbstractDataSource() {
        this.mResult = null;
        this.mFailureThrowable = null;
        this.mProgress = 0.0f;
        this.mIsClosed = false;
        this.mDataSourceStatus = AbstractDataSource$DataSourceStatus.IN_PROGRESS;
        this.mSubscribers = new ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>>();
    }
    
    private void notifyDataSubscriber(final DataSubscriber<T> dataSubscriber, final Executor executor, final boolean b, final boolean b2) {
        executor.execute(new AbstractDataSource$1(this, b, dataSubscriber, b2));
    }
    
    private void notifyDataSubscribers() {
        final boolean hasFailed = this.hasFailed();
        final boolean wasCancelled = this.wasCancelled();
        for (final Pair<DataSubscriber<T>, Executor> pair : this.mSubscribers) {
            this.notifyDataSubscriber((DataSubscriber<T>)pair.first, (Executor)pair.second, hasFailed, wasCancelled);
        }
    }
    
    private boolean setFailureInternal(final Throwable mFailureThrowable) {
        synchronized (this) {
            boolean b;
            if (this.mIsClosed || this.mDataSourceStatus != AbstractDataSource$DataSourceStatus.IN_PROGRESS) {
                b = false;
            }
            else {
                this.mDataSourceStatus = AbstractDataSource$DataSourceStatus.FAILURE;
                this.mFailureThrowable = mFailureThrowable;
                b = true;
            }
            return b;
        }
    }
    
    private boolean setProgressInternal(final float mProgress) {
        final boolean b = false;
        // monitorenter(this)
        boolean b2 = b;
        try {
            if (!this.mIsClosed) {
                if (this.mDataSourceStatus != AbstractDataSource$DataSourceStatus.IN_PROGRESS) {
                    b2 = b;
                }
                else {
                    b2 = b;
                    if (mProgress >= this.mProgress) {
                        this.mProgress = mProgress;
                        b2 = true;
                    }
                }
            }
            return b2;
        }
        finally {
        }
        // monitorexit(this)
    }
    
    private boolean setResultInternal(final T p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/facebook/datasource/AbstractDataSource.mIsClosed:Z
        //     6: ifne            27
        //     9: aload_0        
        //    10: getfield        com/facebook/datasource/AbstractDataSource.mDataSourceStatus:Lcom/facebook/datasource/AbstractDataSource$DataSourceStatus;
        //    13: astore          4
        //    15: getstatic       com/facebook/datasource/AbstractDataSource$DataSourceStatus.IN_PROGRESS:Lcom/facebook/datasource/AbstractDataSource$DataSourceStatus;
        //    18: astore          5
        //    20: aload           4
        //    22: aload           5
        //    24: if_acmpeq       49
        //    27: iconst_0       
        //    28: istore_3       
        //    29: aload_1        
        //    30: astore          4
        //    32: aload_0        
        //    33: monitorexit    
        //    34: iload_3        
        //    35: istore_2       
        //    36: aload_1        
        //    37: ifnull          47
        //    40: aload_0        
        //    41: aload_1        
        //    42: invokevirtual   com/facebook/datasource/AbstractDataSource.closeResult:(Ljava/lang/Object;)V
        //    45: iload_3        
        //    46: istore_2       
        //    47: iload_2        
        //    48: ireturn        
        //    49: iload_2        
        //    50: ifeq            65
        //    53: aload_0        
        //    54: getstatic       com/facebook/datasource/AbstractDataSource$DataSourceStatus.SUCCESS:Lcom/facebook/datasource/AbstractDataSource$DataSourceStatus;
        //    57: putfield        com/facebook/datasource/AbstractDataSource.mDataSourceStatus:Lcom/facebook/datasource/AbstractDataSource$DataSourceStatus;
        //    60: aload_0        
        //    61: fconst_1       
        //    62: putfield        com/facebook/datasource/AbstractDataSource.mProgress:F
        //    65: aload_0        
        //    66: getfield        com/facebook/datasource/AbstractDataSource.mResult:Ljava/lang/Object;
        //    69: aload_1        
        //    70: if_acmpeq       161
        //    73: aload_0        
        //    74: getfield        com/facebook/datasource/AbstractDataSource.mResult:Ljava/lang/Object;
        //    77: astore          4
        //    79: aload_0        
        //    80: aload_1        
        //    81: putfield        com/facebook/datasource/AbstractDataSource.mResult:Ljava/lang/Object;
        //    84: aload           4
        //    86: astore_1       
        //    87: iconst_1       
        //    88: istore_2       
        //    89: aload_1        
        //    90: astore          4
        //    92: aload_0        
        //    93: monitorexit    
        //    94: aload_1        
        //    95: ifnull          47
        //    98: aload_0        
        //    99: aload_1        
        //   100: invokevirtual   com/facebook/datasource/AbstractDataSource.closeResult:(Ljava/lang/Object;)V
        //   103: iconst_1       
        //   104: ireturn        
        //   105: astore          5
        //   107: aconst_null    
        //   108: astore_1       
        //   109: aload_1        
        //   110: astore          4
        //   112: aload_0        
        //   113: monitorexit    
        //   114: aload           5
        //   116: athrow         
        //   117: astore          5
        //   119: aload_1        
        //   120: astore          4
        //   122: aload           5
        //   124: astore_1       
        //   125: aload           4
        //   127: ifnull          136
        //   130: aload_0        
        //   131: aload           4
        //   133: invokevirtual   com/facebook/datasource/AbstractDataSource.closeResult:(Ljava/lang/Object;)V
        //   136: aload_1        
        //   137: athrow         
        //   138: astore_1       
        //   139: aconst_null    
        //   140: astore          4
        //   142: goto            125
        //   145: astore          5
        //   147: aload           4
        //   149: astore_1       
        //   150: goto            109
        //   153: astore          5
        //   155: aload           4
        //   157: astore_1       
        //   158: goto            109
        //   161: aconst_null    
        //   162: astore_1       
        //   163: goto            87
        //    Signature:
        //  (TT;Z)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  0      2      138    145    Any
        //  2      20     105    109    Any
        //  32     34     145    153    Any
        //  53     65     105    109    Any
        //  65     79     105    109    Any
        //  79     84     153    161    Any
        //  92     94     145    153    Any
        //  112    114    145    153    Any
        //  114    117    117    125    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 99, Size: 99
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    private boolean wasCancelled() {
        synchronized (this) {
            return this.isClosed() && !this.isFinished();
        }
    }
    
    @Override
    public boolean close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return false;
            }
            this.mIsClosed = true;
            final T mResult = this.mResult;
            this.mResult = null;
            // monitorexit(this)
            if (mResult != null) {
                this.closeResult(mResult);
            }
            if (!this.isFinished()) {
                this.notifyDataSubscribers();
            }
            synchronized (this) {
                this.mSubscribers.clear();
                return true;
            }
        }
    }
    
    protected void closeResult(final T t) {
    }
    
    @Override
    public Throwable getFailureCause() {
        synchronized (this) {
            return this.mFailureThrowable;
        }
    }
    
    @Override
    public float getProgress() {
        synchronized (this) {
            return this.mProgress;
        }
    }
    
    @Override
    public T getResult() {
        synchronized (this) {
            return this.mResult;
        }
    }
    
    public boolean hasFailed() {
        synchronized (this) {
            return this.mDataSourceStatus == AbstractDataSource$DataSourceStatus.FAILURE;
        }
    }
    
    @Override
    public boolean hasResult() {
        synchronized (this) {
            return this.mResult != null;
        }
    }
    
    public boolean isClosed() {
        synchronized (this) {
            return this.mIsClosed;
        }
    }
    
    @Override
    public boolean isFinished() {
        synchronized (this) {
            return this.mDataSourceStatus != AbstractDataSource$DataSourceStatus.IN_PROGRESS;
        }
    }
    
    protected void notifyProgressUpdate() {
        for (final Pair<DataSubscriber<T>, Executor> pair : this.mSubscribers) {
            ((Executor)pair.second).execute(new AbstractDataSource$2(this, (DataSubscriber)pair.first));
        }
    }
    
    protected boolean setFailure(final Throwable failureInternal) {
        final boolean setFailureInternal = this.setFailureInternal(failureInternal);
        if (setFailureInternal) {
            this.notifyDataSubscribers();
        }
        return setFailureInternal;
    }
    
    protected boolean setProgress(final float progressInternal) {
        final boolean setProgressInternal = this.setProgressInternal(progressInternal);
        if (setProgressInternal) {
            this.notifyProgressUpdate();
        }
        return setProgressInternal;
    }
    
    protected boolean setResult(final T t, final boolean b) {
        final boolean setResultInternal = this.setResultInternal(t, b);
        if (setResultInternal) {
            this.notifyDataSubscribers();
        }
        return setResultInternal;
    }
    
    @Override
    public void subscribe(final DataSubscriber<T> dataSubscriber, final Executor executor) {
        while (true) {
            Preconditions.checkNotNull(dataSubscriber);
            Preconditions.checkNotNull(executor);
            while (true) {
                Label_0101: {
                    synchronized (this) {
                        if (this.mIsClosed) {
                            return;
                        }
                        if (this.mDataSourceStatus == AbstractDataSource$DataSourceStatus.IN_PROGRESS) {
                            this.mSubscribers.add((Pair<DataSubscriber<T>, Executor>)Pair.create((Object)dataSubscriber, (Object)executor));
                        }
                        if (this.hasResult() || this.isFinished() || this.wasCancelled()) {
                            break Label_0101;
                        }
                        final int n = 0;
                        // monitorexit(this)
                        if (n != 0) {
                            this.notifyDataSubscriber(dataSubscriber, executor, this.hasFailed(), this.wasCancelled());
                            return;
                        }
                    }
                    break;
                }
                final int n = 1;
                continue;
            }
        }
    }
}
