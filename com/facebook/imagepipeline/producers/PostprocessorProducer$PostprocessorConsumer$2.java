// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

class PostprocessorProducer$PostprocessorConsumer$2 implements Runnable
{
    final /* synthetic */ PostprocessorProducer$PostprocessorConsumer this$1;
    
    PostprocessorProducer$PostprocessorConsumer$2(final PostprocessorProducer$PostprocessorConsumer this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer$2.this$1:Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer$2.this$1:Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;
        //    11: invokestatic    com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer.access$300:(Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;)Lcom/facebook/common/references/CloseableReference;
        //    14: astore_3       
        //    15: aload_0        
        //    16: getfield        com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer$2.this$1:Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;
        //    19: invokestatic    com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer.access$400:(Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;)Z
        //    22: istore_1       
        //    23: aload_0        
        //    24: getfield        com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer$2.this$1:Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;
        //    27: aconst_null    
        //    28: invokestatic    com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer.access$302:(Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;Lcom/facebook/common/references/CloseableReference;)Lcom/facebook/common/references/CloseableReference;
        //    31: pop            
        //    32: aload_0        
        //    33: getfield        com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer$2.this$1:Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;
        //    36: iconst_0       
        //    37: invokestatic    com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer.access$502:(Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;Z)Z
        //    40: pop            
        //    41: aload_2        
        //    42: monitorexit    
        //    43: aload_3        
        //    44: invokestatic    com/facebook/common/references/CloseableReference.isValid:(Lcom/facebook/common/references/CloseableReference;)Z
        //    47: ifeq            63
        //    50: aload_0        
        //    51: getfield        com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer$2.this$1:Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;
        //    54: aload_3        
        //    55: iload_1        
        //    56: invokestatic    com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer.access$600:(Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;Lcom/facebook/common/references/CloseableReference;Z)V
        //    59: aload_3        
        //    60: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //    63: aload_0        
        //    64: getfield        com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer$2.this$1:Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;
        //    67: invokestatic    com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer.access$700:(Lcom/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer;)V
        //    70: return         
        //    71: astore_3       
        //    72: aload_2        
        //    73: monitorexit    
        //    74: aload_3        
        //    75: athrow         
        //    76: astore_2       
        //    77: aload_3        
        //    78: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //    81: aload_2        
        //    82: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      43     71     76     Any
        //  50     59     76     83     Any
        //  72     74     71     76     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
}
