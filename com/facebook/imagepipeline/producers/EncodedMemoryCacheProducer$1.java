// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.EncodedImage;

class EncodedMemoryCacheProducer$1 extends DelegatingConsumer<EncodedImage, EncodedImage>
{
    final /* synthetic */ EncodedMemoryCacheProducer this$0;
    final /* synthetic */ CacheKey val$cacheKey;
    
    EncodedMemoryCacheProducer$1(final EncodedMemoryCacheProducer this$0, final Consumer consumer, final CacheKey val$cacheKey) {
        this.this$0 = this$0;
        this.val$cacheKey = val$cacheKey;
        super(consumer);
    }
    
    public void onNewResultImpl(final EncodedImage p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2        
        //     1: ifeq            8
        //     4: aload_1        
        //     5: ifnonnull       20
        //     8: aload_0        
        //     9: invokevirtual   com/facebook/imagepipeline/producers/EncodedMemoryCacheProducer$1.getConsumer:()Lcom/facebook/imagepipeline/producers/Consumer;
        //    12: aload_1        
        //    13: iload_2        
        //    14: invokeinterface com/facebook/imagepipeline/producers/Consumer.onNewResult:(Ljava/lang/Object;Z)V
        //    19: return         
        //    20: aload_1        
        //    21: invokevirtual   com/facebook/imagepipeline/image/EncodedImage.getByteBufferRef:()Lcom/facebook/common/references/CloseableReference;
        //    24: astore          4
        //    26: aload           4
        //    28: ifnull          130
        //    31: aload_0        
        //    32: getfield        com/facebook/imagepipeline/producers/EncodedMemoryCacheProducer$1.this$0:Lcom/facebook/imagepipeline/producers/EncodedMemoryCacheProducer;
        //    35: invokestatic    com/facebook/imagepipeline/producers/EncodedMemoryCacheProducer.access$000:(Lcom/facebook/imagepipeline/producers/EncodedMemoryCacheProducer;)Lcom/facebook/imagepipeline/cache/MemoryCache;
        //    38: aload_0        
        //    39: getfield        com/facebook/imagepipeline/producers/EncodedMemoryCacheProducer$1.val$cacheKey:Lcom/facebook/cache/common/CacheKey;
        //    42: aload           4
        //    44: invokeinterface com/facebook/imagepipeline/cache/MemoryCache.cache:(Ljava/lang/Object;Lcom/facebook/common/references/CloseableReference;)Lcom/facebook/common/references/CloseableReference;
        //    49: astore_3       
        //    50: aload           4
        //    52: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //    55: aload_3        
        //    56: ifnull          130
        //    59: new             Lcom/facebook/imagepipeline/image/EncodedImage;
        //    62: dup            
        //    63: aload_3        
        //    64: invokespecial   com/facebook/imagepipeline/image/EncodedImage.<init>:(Lcom/facebook/common/references/CloseableReference;)V
        //    67: astore          4
        //    69: aload           4
        //    71: aload_1        
        //    72: invokevirtual   com/facebook/imagepipeline/image/EncodedImage.copyMetaDataFrom:(Lcom/facebook/imagepipeline/image/EncodedImage;)V
        //    75: aload_3        
        //    76: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //    79: aload_0        
        //    80: invokevirtual   com/facebook/imagepipeline/producers/EncodedMemoryCacheProducer$1.getConsumer:()Lcom/facebook/imagepipeline/producers/Consumer;
        //    83: fconst_1       
        //    84: invokeinterface com/facebook/imagepipeline/producers/Consumer.onProgressUpdate:(F)V
        //    89: aload_0        
        //    90: invokevirtual   com/facebook/imagepipeline/producers/EncodedMemoryCacheProducer$1.getConsumer:()Lcom/facebook/imagepipeline/producers/Consumer;
        //    93: aload           4
        //    95: iconst_1       
        //    96: invokeinterface com/facebook/imagepipeline/producers/Consumer.onNewResult:(Ljava/lang/Object;Z)V
        //   101: aload           4
        //   103: invokestatic    com/facebook/imagepipeline/image/EncodedImage.closeSafely:(Lcom/facebook/imagepipeline/image/EncodedImage;)V
        //   106: return         
        //   107: astore_1       
        //   108: aload           4
        //   110: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //   113: aload_1        
        //   114: athrow         
        //   115: astore_1       
        //   116: aload_3        
        //   117: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //   120: aload_1        
        //   121: athrow         
        //   122: astore_1       
        //   123: aload           4
        //   125: invokestatic    com/facebook/imagepipeline/image/EncodedImage.closeSafely:(Lcom/facebook/imagepipeline/image/EncodedImage;)V
        //   128: aload_1        
        //   129: athrow         
        //   130: aload_0        
        //   131: invokevirtual   com/facebook/imagepipeline/producers/EncodedMemoryCacheProducer$1.getConsumer:()Lcom/facebook/imagepipeline/producers/Consumer;
        //   134: aload_1        
        //   135: iconst_1       
        //   136: invokeinterface com/facebook/imagepipeline/producers/Consumer.onNewResult:(Ljava/lang/Object;Z)V
        //   141: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  31     50     107    115    Any
        //  59     75     115    122    Any
        //  79     101    122    130    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 70, Size: 70
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
}
