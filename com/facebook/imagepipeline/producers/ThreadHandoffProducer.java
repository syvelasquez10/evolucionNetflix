// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;

public class ThreadHandoffProducer<T> implements Producer<T>
{
    private final Producer<T> mInputProducer;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;
    
    public ThreadHandoffProducer(final Producer<T> producer, final ThreadHandoffProducerQueue mThreadHandoffProducerQueue) {
        this.mInputProducer = Preconditions.checkNotNull(producer);
        this.mThreadHandoffProducerQueue = mThreadHandoffProducerQueue;
    }
    
    @Override
    public void produceResults(final Consumer<T> p0, final ProducerContext p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokeinterface com/facebook/imagepipeline/producers/ProducerContext.getListener:()Lcom/facebook/imagepipeline/producers/ProducerListener;
        //     6: astore_3       
        //     7: aload_2        
        //     8: invokeinterface com/facebook/imagepipeline/producers/ProducerContext.getId:()Ljava/lang/String;
        //    13: astore          4
        //    15: new             new            !!! ERROR
        //    18: dup            
        //    19: aload_0        
        //    20: aload_1        
        //    21: aload_3        
        //    22: ldc             "BackgroundThreadHandoffProducer"
        //    24: aload           4
        //    26: aload_3        
        //    27: aload           4
        //    29: aload_1        
        //    30: aload_2        
        //    31: invokespecial   invokespecial  !!! ERROR
        //    34: astore_1       
        //    35: aload_2        
        //    36: new             Lcom/facebook/imagepipeline/producers/ThreadHandoffProducer$2;
        //    39: dup            
        //    40: aload_0        
        //    41: aload_1        
        //    42: invokespecial   com/facebook/imagepipeline/producers/ThreadHandoffProducer$2.<init>:(Lcom/facebook/imagepipeline/producers/ThreadHandoffProducer;Lcom/facebook/imagepipeline/producers/StatefulProducerRunnable;)V
        //    45: invokeinterface com/facebook/imagepipeline/producers/ProducerContext.addCallbacks:(Lcom/facebook/imagepipeline/producers/ProducerContextCallbacks;)V
        //    50: aload_0        
        //    51: getfield        com/facebook/imagepipeline/producers/ThreadHandoffProducer.mThreadHandoffProducerQueue:Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;
        //    54: aload_1        
        //    55: invokevirtual   com/facebook/imagepipeline/producers/ThreadHandoffProducerQueue.addToQueueOrExecute:(Ljava/lang/Runnable;)V
        //    58: return         
        //    Signature:
        //  (Lcom/facebook/imagepipeline/producers/Consumer<TT;>;Lcom/facebook/imagepipeline/producers/ProducerContext;)V
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
        //     at com.strobel.assembler.metadata.MetadataHelper.isRawType(MetadataHelper.java:1540)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2295)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2256)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.accept(CoreMetadataFactory.java:550)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visit(MetadataHelper.java:2270)
        //     at com.strobel.assembler.metadata.MetadataHelper.isSameType(MetadataHelper.java:1370)
        //     at com.strobel.assembler.metadata.MetadataHelper.isSameType(MetadataHelper.java:1358)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2905)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
}
