// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.osp;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public abstract class AsyncTaskCompat<Params, Progress, Result>
{
    private static final int CORE_POOL_SIZE = 4;
    private static final int KEEP_ALIVE = 1;
    private static final String LOG_TAG = "AsyncTask";
    private static final int MAXIMUM_POOL_SIZE;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor SERIAL_EXECUTOR;
    public static final Executor THREAD_POOL_EXECUTOR;
    private static volatile Executor sDefaultExecutor;
    private static final AsyncTaskCompat$InternalHandler sHandler;
    private static final BlockingQueue<Runnable> sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;
    private final AtomicBoolean mCancelled;
    private final FutureTask<Result> mFuture;
    private volatile AsyncTaskCompat$Status mStatus;
    private final AtomicBoolean mTaskInvoked;
    private final AsyncTaskCompat$WorkerRunnable<Params, Result> mWorker;
    
    static {
        int maximum_POOL_SIZE;
        if (ConfigurationAgent.shouldUseLowMemConfig()) {
            maximum_POOL_SIZE = 8;
        }
        else {
            maximum_POOL_SIZE = 16;
        }
        MAXIMUM_POOL_SIZE = maximum_POOL_SIZE;
        sThreadFactory = new AsyncTaskCompat$1();
        sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(64);
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, AsyncTaskCompat.MAXIMUM_POOL_SIZE, 1L, TimeUnit.SECONDS, AsyncTaskCompat.sPoolWorkQueue, AsyncTaskCompat.sThreadFactory, new ThreadPoolExecutor.DiscardOldestPolicy());
        SERIAL_EXECUTOR = Executors.newSingleThreadExecutor();
        sHandler = new AsyncTaskCompat$InternalHandler(null);
        AsyncTaskCompat.sDefaultExecutor = AsyncTaskCompat.SERIAL_EXECUTOR;
    }
    
    public AsyncTaskCompat() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: getstatic       com/netflix/mediaclient/android/osp/AsyncTaskCompat$Status.PENDING:Lcom/netflix/mediaclient/android/osp/AsyncTaskCompat$Status;
        //     8: putfield        com/netflix/mediaclient/android/osp/AsyncTaskCompat.mStatus:Lcom/netflix/mediaclient/android/osp/AsyncTaskCompat$Status;
        //    11: aload_0        
        //    12: new             Ljava/util/concurrent/atomic/AtomicBoolean;
        //    15: dup            
        //    16: invokespecial   java/util/concurrent/atomic/AtomicBoolean.<init>:()V
        //    19: putfield        com/netflix/mediaclient/android/osp/AsyncTaskCompat.mCancelled:Ljava/util/concurrent/atomic/AtomicBoolean;
        //    22: aload_0        
        //    23: new             Ljava/util/concurrent/atomic/AtomicBoolean;
        //    26: dup            
        //    27: invokespecial   java/util/concurrent/atomic/AtomicBoolean.<init>:()V
        //    30: putfield        com/netflix/mediaclient/android/osp/AsyncTaskCompat.mTaskInvoked:Ljava/util/concurrent/atomic/AtomicBoolean;
        //    33: aload_0        
        //    34: new             new            !!! ERROR
        //    37: dup            
        //    38: aload_0        
        //    39: invokespecial   invokespecial  !!! ERROR
        //    42: putfield        com/netflix/mediaclient/android/osp/AsyncTaskCompat.mWorker:Lcom/netflix/mediaclient/android/osp/AsyncTaskCompat$WorkerRunnable;
        //    45: aload_0        
        //    46: new             new            !!! ERROR
        //    49: dup            
        //    50: aload_0        
        //    51: aload_0        
        //    52: getfield        com/netflix/mediaclient/android/osp/AsyncTaskCompat.mWorker:Lcom/netflix/mediaclient/android/osp/AsyncTaskCompat$WorkerRunnable;
        //    55: invokespecial   invokespecial  !!! ERROR
        //    58: putfield        com/netflix/mediaclient/android/osp/AsyncTaskCompat.mFuture:Ljava/util/concurrent/FutureTask;
        //    61: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:394)
        //     at com.strobel.assembler.metadata.ClassFileReader.populateBaseTypes(ClassFileReader.java:665)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:438)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:366)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:124)
        //     at com.strobel.decompiler.NoRetryMetadataSystem.resolveType(DecompilerDriver.java:463)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:76)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:589)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:599)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:172)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2428)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public static void execute(final Runnable runnable) {
        AsyncTaskCompat.sDefaultExecutor.execute(runnable);
    }
    
    private void finish(final Result result) {
        if (this.isCancelled()) {
            this.onCancelled(result);
        }
        else {
            this.onPostExecute(result);
        }
        this.mStatus = AsyncTaskCompat$Status.FINISHED;
    }
    
    public static void init() {
        AsyncTaskCompat.sHandler.getLooper();
    }
    
    private Result postResult(final Result result) {
        AsyncTaskCompat.sHandler.obtainMessage(1, (Object)new AsyncTaskCompat$AsyncTaskResult(this, new Object[] { result })).sendToTarget();
        return result;
    }
    
    private void postResultIfNotInvoked(final Result result) {
        if (!this.mTaskInvoked.get()) {
            this.postResult(result);
        }
    }
    
    public static void setDefaultExecutor(final Executor sDefaultExecutor) {
        AsyncTaskCompat.sDefaultExecutor = sDefaultExecutor;
    }
    
    public final boolean cancel(final boolean b) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(b);
    }
    
    protected abstract Result doInBackground(final Params... p0);
    
    public final AsyncTaskCompat<Params, Progress, Result> execute(final Params... array) {
        return this.executeOnExecutor(AsyncTaskCompat.sDefaultExecutor, array);
    }
    
    public final AsyncTaskCompat<Params, Progress, Result> executeOnExecutor(final Executor executor, final Params... mParams) {
        if (this.mStatus != AsyncTaskCompat$Status.PENDING) {
            switch (AsyncTaskCompat$4.$SwitchMap$com$netflix$mediaclient$android$osp$AsyncTaskCompat$Status[this.mStatus.ordinal()]) {
                case 1: {
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                }
                case 2: {
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                }
            }
        }
        this.mStatus = AsyncTaskCompat$Status.RUNNING;
        this.onPreExecute();
        this.mWorker.mParams = mParams;
        executor.execute(this.mFuture);
        return this;
    }
    
    public final Result get() {
        return this.mFuture.get();
    }
    
    public final Result get(final long n, final TimeUnit timeUnit) {
        return this.mFuture.get(n, timeUnit);
    }
    
    public final AsyncTaskCompat$Status getStatus() {
        return this.mStatus;
    }
    
    public final boolean isCancelled() {
        return this.mCancelled.get();
    }
    
    protected void onCancelled() {
    }
    
    protected void onCancelled(final Result result) {
        this.onCancelled();
    }
    
    protected void onPostExecute(final Result result) {
    }
    
    protected void onPreExecute() {
    }
    
    protected void onProgressUpdate(final Progress... array) {
    }
    
    protected final void publishProgress(final Progress... array) {
        if (!this.isCancelled()) {
            AsyncTaskCompat.sHandler.obtainMessage(2, (Object)new AsyncTaskCompat$AsyncTaskResult(this, (Object[])array)).sendToTarget();
        }
    }
}
