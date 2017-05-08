// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Task<TResult>
{
    public static final ExecutorService BACKGROUND_EXECUTOR;
    private static final Executor IMMEDIATE_EXECUTOR;
    private static Task<?> TASK_CANCELLED;
    private static Task<Boolean> TASK_FALSE;
    private static Task<?> TASK_NULL;
    private static Task<Boolean> TASK_TRUE;
    public static final Executor UI_THREAD_EXECUTOR;
    private static volatile Task$UnobservedExceptionHandler unobservedExceptionHandler;
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations;
    private Exception error;
    private boolean errorHasBeenObserved;
    private final Object lock;
    private TResult result;
    private UnobservedErrorNotifier unobservedErrorNotifier;
    
    static {
        BACKGROUND_EXECUTOR = BoltsExecutors.background();
        IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
        UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
        Task.TASK_NULL = new Task<Object>(null);
        Task.TASK_TRUE = new Task<Boolean>(Boolean.valueOf(true));
        Task.TASK_FALSE = new Task<Boolean>(Boolean.valueOf(false));
        Task.TASK_CANCELLED = new Task<Object>(true);
    }
    
    Task() {
        this.lock = new Object();
        this.continuations = new ArrayList<Continuation<TResult, Void>>();
    }
    
    private Task(final TResult tResult) {
        this.lock = new Object();
        this.continuations = new ArrayList<Continuation<TResult, Void>>();
        this.trySetResult(tResult);
    }
    
    private Task(final boolean b) {
        this.lock = new Object();
        this.continuations = new ArrayList<Continuation<TResult, Void>>();
        if (b) {
            this.trySetCancelled();
            return;
        }
        this.trySetResult(null);
    }
    
    public static <TResult> Task<TResult> call(final Callable<TResult> callable, final Executor executor) {
        return call(callable, executor, null);
    }
    
    public static <TResult> Task<TResult> call(final Callable<TResult> callable, final Executor executor, final CancellationToken cancellationToken) {
        final TaskCompletionSource<TResult> taskCompletionSource = new TaskCompletionSource<TResult>();
        try {
            executor.execute(new Task$4(cancellationToken, taskCompletionSource, callable));
            return taskCompletionSource.getTask();
        }
        catch (Exception ex) {
            taskCompletionSource.setError(new ExecutorException(ex));
            return taskCompletionSource.getTask();
        }
    }
    
    private static <TContinuationResult, TResult> void completeAfterTask(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, final Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Task$15(cancellationToken, taskCompletionSource, continuation, task));
        }
        catch (Exception ex) {
            taskCompletionSource.setError(new ExecutorException(ex));
        }
    }
    
    private static <TContinuationResult, TResult> void completeImmediately(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, final Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Task$14(cancellationToken, taskCompletionSource, continuation, task));
        }
        catch (Exception ex) {
            taskCompletionSource.setError(new ExecutorException(ex));
        }
    }
    
    public static <TResult> Task<TResult> forError(final Exception error) {
        final TaskCompletionSource<TResult> taskCompletionSource = new TaskCompletionSource<TResult>();
        taskCompletionSource.setError(error);
        return taskCompletionSource.getTask();
    }
    
    public static <TResult> Task<TResult> forResult(final TResult result) {
        if (result == null) {
            return (Task<TResult>)Task.TASK_NULL;
        }
        if (!(result instanceof Boolean)) {
            final TaskCompletionSource<TResult> taskCompletionSource = new TaskCompletionSource<TResult>();
            taskCompletionSource.setResult(result);
            return taskCompletionSource.getTask();
        }
        if (result) {
            return (Task<TResult>)Task.TASK_TRUE;
        }
        return (Task<TResult>)Task.TASK_FALSE;
    }
    
    public static Task$UnobservedExceptionHandler getUnobservedExceptionHandler() {
        return Task.unobservedExceptionHandler;
    }
    
    private void runContinuations() {
        final Object lock = this.lock;
        // monitorenter(lock)
        try {
            for (final Continuation<TResult, Void> continuation : this.continuations) {
                try {
                    continuation.then(this);
                }
                catch (RuntimeException ex) {
                    throw ex;
                }
                catch (Exception ex2) {
                    throw new RuntimeException(ex2);
                }
            }
        }
        finally {}
        this.continuations = null;
    }
    // monitorexit(lock)
    
    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation) {
        return this.continueWith(continuation, Task.IMMEDIATE_EXECUTOR, null);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> p0, final Executor p1, final CancellationToken p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lbolts/TaskCompletionSource;
        //     3: dup            
        //     4: invokespecial   bolts/TaskCompletionSource.<init>:()V
        //     7: astore          6
        //     9: aload_0        
        //    10: getfield        bolts/Task.lock:Ljava/lang/Object;
        //    13: astore          5
        //    15: aload           5
        //    17: monitorenter   
        //    18: aload_0        
        //    19: invokevirtual   bolts/Task.isCompleted:()Z
        //    22: istore          4
        //    24: iload           4
        //    26: ifne            52
        //    29: aload_0        
        //    30: getfield        bolts/Task.continuations:Ljava/util/List;
        //    33: new             new            !!! ERROR
        //    36: dup            
        //    37: aload_0        
        //    38: aload           6
        //    40: aload_1        
        //    41: aload_2        
        //    42: aload_3        
        //    43: invokespecial   invokespecial  !!! ERROR
        //    46: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    51: pop            
        //    52: aload           5
        //    54: monitorexit    
        //    55: iload           4
        //    57: ifeq            69
        //    60: aload           6
        //    62: aload_1        
        //    63: aload_0        
        //    64: aload_2        
        //    65: aload_3        
        //    66: invokestatic    bolts/Task.completeImmediately:(Lbolts/TaskCompletionSource;Lbolts/Continuation;Lbolts/Task;Ljava/util/concurrent/Executor;Lbolts/CancellationToken;)V
        //    69: aload           6
        //    71: invokevirtual   bolts/TaskCompletionSource.getTask:()Lbolts/Task;
        //    74: areturn        
        //    75: astore_1       
        //    76: aload           5
        //    78: monitorexit    
        //    79: aload_1        
        //    80: athrow         
        //    Signature:
        //  <TContinuationResult:Ljava/lang/Object;>(Lbolts/Continuation<TTResult;TTContinuationResult;>;Ljava/util/concurrent/Executor;Lbolts/CancellationToken;)Lbolts/Task<TTContinuationResult;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  18     24     75     81     Any
        //  29     52     75     81     Any
        //  52     55     75     81     Any
        //  76     79     75     81     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:404)
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
    
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation) {
        return this.continueWithTask(continuation, Task.IMMEDIATE_EXECUTOR, null);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> p0, final Executor p1, final CancellationToken p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lbolts/TaskCompletionSource;
        //     3: dup            
        //     4: invokespecial   bolts/TaskCompletionSource.<init>:()V
        //     7: astore          6
        //     9: aload_0        
        //    10: getfield        bolts/Task.lock:Ljava/lang/Object;
        //    13: astore          5
        //    15: aload           5
        //    17: monitorenter   
        //    18: aload_0        
        //    19: invokevirtual   bolts/Task.isCompleted:()Z
        //    22: istore          4
        //    24: iload           4
        //    26: ifne            52
        //    29: aload_0        
        //    30: getfield        bolts/Task.continuations:Ljava/util/List;
        //    33: new             new            !!! ERROR
        //    36: dup            
        //    37: aload_0        
        //    38: aload           6
        //    40: aload_1        
        //    41: aload_2        
        //    42: aload_3        
        //    43: invokespecial   invokespecial  !!! ERROR
        //    46: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    51: pop            
        //    52: aload           5
        //    54: monitorexit    
        //    55: iload           4
        //    57: ifeq            69
        //    60: aload           6
        //    62: aload_1        
        //    63: aload_0        
        //    64: aload_2        
        //    65: aload_3        
        //    66: invokestatic    bolts/Task.completeAfterTask:(Lbolts/TaskCompletionSource;Lbolts/Continuation;Lbolts/Task;Ljava/util/concurrent/Executor;Lbolts/CancellationToken;)V
        //    69: aload           6
        //    71: invokevirtual   bolts/TaskCompletionSource.getTask:()Lbolts/Task;
        //    74: areturn        
        //    75: astore_1       
        //    76: aload           5
        //    78: monitorexit    
        //    79: aload_1        
        //    80: athrow         
        //    Signature:
        //  <TContinuationResult:Ljava/lang/Object;>(Lbolts/Continuation<TTResult;Lbolts/Task<TTContinuationResult;>;>;Ljava/util/concurrent/Executor;Lbolts/CancellationToken;)Lbolts/Task<TTContinuationResult;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  18     24     75     81     Any
        //  29     52     75     81     Any
        //  52     55     75     81     Any
        //  76     79     75     81     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:404)
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
    
    public Exception getError() {
        synchronized (this.lock) {
            if (this.error != null) {
                this.errorHasBeenObserved = true;
                if (this.unobservedErrorNotifier != null) {
                    this.unobservedErrorNotifier.setObserved();
                    this.unobservedErrorNotifier = null;
                }
            }
            return this.error;
        }
    }
    
    public TResult getResult() {
        synchronized (this.lock) {
            return this.result;
        }
    }
    
    public boolean isCancelled() {
        synchronized (this.lock) {
            return this.cancelled;
        }
    }
    
    public boolean isCompleted() {
        synchronized (this.lock) {
            return this.complete;
        }
    }
    
    public boolean isFaulted() {
        while (true) {
            synchronized (this.lock) {
                if (this.getError() != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    boolean trySetCancelled() {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.cancelled = true;
            this.lock.notifyAll();
            this.runContinuations();
            return true;
        }
    }
    
    boolean trySetError(final Exception error) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.error = error;
            this.errorHasBeenObserved = false;
            this.lock.notifyAll();
            this.runContinuations();
            if (!this.errorHasBeenObserved && getUnobservedExceptionHandler() != null) {
                this.unobservedErrorNotifier = new UnobservedErrorNotifier(this);
            }
            return true;
        }
    }
    
    boolean trySetResult(final TResult result) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.result = result;
            this.lock.notifyAll();
            this.runContinuations();
            return true;
        }
    }
}
