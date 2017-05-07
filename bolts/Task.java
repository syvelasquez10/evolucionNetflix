// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Collection;
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
    public static final Executor UI_THREAD_EXECUTOR;
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations;
    private Exception error;
    private final Object lock;
    private TResult result;
    
    static {
        BACKGROUND_EXECUTOR = BoltsExecutors.background();
        IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
        UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
    }
    
    private Task() {
        this.lock = new Object();
        this.continuations = new ArrayList<Continuation<TResult, Void>>();
    }
    
    public static <TResult> Task<TResult> call(final Callable<TResult> callable) {
        return call(callable, Task.IMMEDIATE_EXECUTOR);
    }
    
    public static <TResult> Task<TResult> call(final Callable<TResult> callable, final Executor executor) {
        final Task$TaskCompletionSource create = create();
        executor.execute(new Task$2(create, callable));
        return (Task<TResult>)create.getTask();
    }
    
    public static <TResult> Task<TResult> callInBackground(final Callable<TResult> callable) {
        return call(callable, Task.BACKGROUND_EXECUTOR);
    }
    
    public static <TResult> Task<TResult> cancelled() {
        final Task$TaskCompletionSource create = create();
        create.setCancelled();
        return (Task<TResult>)create.getTask();
    }
    
    private static <TContinuationResult, TResult> void completeAfterTask(final Task$TaskCompletionSource task$TaskCompletionSource, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, final Executor executor) {
        executor.execute(new Task$10(continuation, task, task$TaskCompletionSource));
    }
    
    private static <TContinuationResult, TResult> void completeImmediately(final Task$TaskCompletionSource task$TaskCompletionSource, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, final Executor executor) {
        executor.execute(new Task$9(continuation, task, task$TaskCompletionSource));
    }
    
    public static <TResult> Task$TaskCompletionSource create() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lbolts/Task;
        //     3: dup            
        //     4: invokespecial   bolts/Task.<init>:()V
        //     7: astore_0       
        //     8: aload_0        
        //     9: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    12: pop            
        //    13: new             Lbolts/Task$TaskCompletionSource;
        //    16: dup            
        //    17: aload_0        
        //    18: aconst_null    
        //    19: invokespecial   bolts/Task$TaskCompletionSource.<init>:(Lbolts/Task;invokespecial  !!! ERROR
        //    22: areturn        
        //    Signature:
        //  <TResult:Ljava/lang/Object;>()Lbolts/Task$TaskCompletionSource; [from metadata: <TResult:Ljava/lang/Object;>()Lbolts/Task<TTResult;>.TaskCompletionSource;]
        //  
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
        //     at com.strobel.assembler.metadata.MetadataHelper.isRawType(MetadataHelper.java:1540)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2295)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2256)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.accept(CoreMetadataFactory.java:550)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visit(MetadataHelper.java:2270)
        //     at com.strobel.assembler.metadata.MetadataHelper.isSameType(MetadataHelper.java:1370)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.isCastRequired(AstMethodBodyBuilder.java:1354)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.adjustArgumentsForMethodCallCore(AstMethodBodyBuilder.java:1315)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.adjustArgumentsForMethodCall(AstMethodBodyBuilder.java:1283)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1179)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
    
    public static <TResult> Task<TResult> forError(final Exception error) {
        final Task$TaskCompletionSource create = create();
        create.setError(error);
        return (Task<TResult>)create.getTask();
    }
    
    public static <TResult> Task<TResult> forResult(final TResult result) {
        final Task$TaskCompletionSource create = create();
        create.setResult(result);
        return (Task<TResult>)create.getTask();
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
    
    public static Task<Void> whenAll(final Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return forResult((Void)null);
        }
        final Task$TaskCompletionSource create = create();
        final ArrayList list = new ArrayList();
        final Object o = new Object();
        final AtomicInteger atomicInteger = new AtomicInteger(collection.size());
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final Iterator<? extends Task<?>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ((Task)iterator.next()).continueWith(new Task$3(o, list, atomicBoolean, atomicInteger, create));
        }
        return (Task<Void>)create.getTask();
    }
    
    public <TOut> Task<TOut> cast() {
        return (Task<TOut>)this;
    }
    
    public Task<Void> continueWhile(final Callable<Boolean> callable, final Continuation<Void, Task<Void>> continuation) {
        return this.continueWhile(callable, continuation, Task.IMMEDIATE_EXECUTOR);
    }
    
    public Task<Void> continueWhile(final Callable<Boolean> callable, final Continuation<Void, Task<Void>> continuation, final Executor executor) {
        final Capture<Task$4> capture = new Capture<Task$4>();
        capture.set(new Task$4(this, callable, continuation, executor, capture));
        return this.makeVoid().continueWithTask((Continuation<Void, Task<Void>>)capture.get(), executor);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation) {
        return this.continueWith(continuation, Task.IMMEDIATE_EXECUTOR);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> p0, final Executor p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    bolts/Task.create:()Lbolts/Task$TaskCompletionSource;
        //     3: astore          5
        //     5: aload_0        
        //     6: getfield        bolts/Task.lock:Ljava/lang/Object;
        //     9: astore          4
        //    11: aload           4
        //    13: monitorenter   
        //    14: aload_0        
        //    15: invokevirtual   bolts/Task.isCompleted:()Z
        //    18: istore_3       
        //    19: iload_3        
        //    20: ifne            45
        //    23: aload_0        
        //    24: getfield        bolts/Task.continuations:Ljava/util/List;
        //    27: new             new            !!! ERROR
        //    30: dup            
        //    31: aload_0        
        //    32: aload           5
        //    34: aload_1        
        //    35: aload_2        
        //    36: invokespecial   invokespecial  !!! ERROR
        //    39: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    44: pop            
        //    45: aload           4
        //    47: monitorexit    
        //    48: iload_3        
        //    49: ifeq            60
        //    52: aload           5
        //    54: aload_1        
        //    55: aload_0        
        //    56: aload_2        
        //    57: invokestatic    bolts/Task.completeImmediately:(Lbolts/Task$TaskCompletionSource;Lbolts/Continuation;Lbolts/Task;Ljava/util/concurrent/Executor;)V
        //    60: aload           5
        //    62: invokevirtual   bolts/Task$TaskCompletionSource.getTask:()Lbolts/Task;
        //    65: areturn        
        //    66: astore_1       
        //    67: aload           4
        //    69: monitorexit    
        //    70: aload_1        
        //    71: athrow         
        //    Signature:
        //  <TContinuationResult:Ljava/lang/Object;>(Lbolts/Continuation<TTResult;TTContinuationResult;>;Ljava/util/concurrent/Executor;)Lbolts/Task<TTContinuationResult;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  14     19     66     72     Any
        //  23     45     66     72     Any
        //  45     48     66     72     Any
        //  67     70     66     72     Any
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
        return this.continueWithTask(continuation, Task.IMMEDIATE_EXECUTOR);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> p0, final Executor p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    bolts/Task.create:()Lbolts/Task$TaskCompletionSource;
        //     3: astore          5
        //     5: aload_0        
        //     6: getfield        bolts/Task.lock:Ljava/lang/Object;
        //     9: astore          4
        //    11: aload           4
        //    13: monitorenter   
        //    14: aload_0        
        //    15: invokevirtual   bolts/Task.isCompleted:()Z
        //    18: istore_3       
        //    19: iload_3        
        //    20: ifne            45
        //    23: aload_0        
        //    24: getfield        bolts/Task.continuations:Ljava/util/List;
        //    27: new             new            !!! ERROR
        //    30: dup            
        //    31: aload_0        
        //    32: aload           5
        //    34: aload_1        
        //    35: aload_2        
        //    36: invokespecial   invokespecial  !!! ERROR
        //    39: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    44: pop            
        //    45: aload           4
        //    47: monitorexit    
        //    48: iload_3        
        //    49: ifeq            60
        //    52: aload           5
        //    54: aload_1        
        //    55: aload_0        
        //    56: aload_2        
        //    57: invokestatic    bolts/Task.completeAfterTask:(Lbolts/Task$TaskCompletionSource;Lbolts/Continuation;Lbolts/Task;Ljava/util/concurrent/Executor;)V
        //    60: aload           5
        //    62: invokevirtual   bolts/Task$TaskCompletionSource.getTask:()Lbolts/Task;
        //    65: areturn        
        //    66: astore_1       
        //    67: aload           4
        //    69: monitorexit    
        //    70: aload_1        
        //    71: athrow         
        //    Signature:
        //  <TContinuationResult:Ljava/lang/Object;>(Lbolts/Continuation<TTResult;Lbolts/Task<TTContinuationResult;>;>;Ljava/util/concurrent/Executor;)Lbolts/Task<TTContinuationResult;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  14     19     66     72     Any
        //  23     45     66     72     Any
        //  45     48     66     72     Any
        //  67     70     66     72     Any
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
                if (this.error != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public Task<Void> makeVoid() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: new             new            !!! ERROR
        //     4: dup            
        //     5: aload_0        
        //     6: invokespecial   invokespecial  !!! ERROR
        //     9: invokevirtual   bolts/Task.continueWithTask:(Lbolts/Continuation;)Lbolts/Task;
        //    12: areturn        
        //    Signature:
        //  ()Lbolts/Task<Ljava/lang/Void;>;
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
    
    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation) {
        return this.onSuccess(continuation, Task.IMMEDIATE_EXECUTOR);
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> p0, final Executor p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: new             new            !!! ERROR
        //     4: dup            
        //     5: aload_0        
        //     6: aload_1        
        //     7: invokespecial   invokespecial  !!! ERROR
        //    10: aload_2        
        //    11: invokevirtual   bolts/Task.continueWithTask:(Lbolts/Continuation;Ljava/util/concurrent/Executor;)Lbolts/Task;
        //    14: areturn        
        //    Signature:
        //  <TContinuationResult:Ljava/lang/Object;>(Lbolts/Continuation<TTResult;TTContinuationResult;>;Ljava/util/concurrent/Executor;)Lbolts/Task<TTContinuationResult;>;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.signatures.Reifier.reifyTypeArguments(Reifier.java:53)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:123)
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
    
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation) {
        return this.onSuccessTask(continuation, Task.IMMEDIATE_EXECUTOR);
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> p0, final Executor p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: new             new            !!! ERROR
        //     4: dup            
        //     5: aload_0        
        //     6: aload_1        
        //     7: invokespecial   invokespecial  !!! ERROR
        //    10: aload_2        
        //    11: invokevirtual   bolts/Task.continueWithTask:(Lbolts/Continuation;Ljava/util/concurrent/Executor;)Lbolts/Task;
        //    14: areturn        
        //    Signature:
        //  <TContinuationResult:Ljava/lang/Object;>(Lbolts/Continuation<TTResult;Lbolts/Task<TTContinuationResult;>;>;Ljava/util/concurrent/Executor;)Lbolts/Task<TTContinuationResult;>;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.signatures.Reifier.reifyTypeArguments(Reifier.java:53)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:123)
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
    
    public void waitForCompletion() {
        synchronized (this.lock) {
            if (!this.isCompleted()) {
                this.lock.wait();
            }
        }
    }
}
