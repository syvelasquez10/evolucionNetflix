// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

class BitmapMemoryCacheProducer$1 extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>
{
    final /* synthetic */ BitmapMemoryCacheProducer this$0;
    final /* synthetic */ CacheKey val$cacheKey;
    
    BitmapMemoryCacheProducer$1(final BitmapMemoryCacheProducer this$0, final Consumer consumer, final CacheKey val$cacheKey) {
        this.this$0 = this$0;
        this.val$cacheKey = val$cacheKey;
        super(consumer);
    }
    
    public void onNewResultImpl(final CloseableReference<CloseableImage> p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       20
        //     4: iload_2        
        //     5: ifeq            19
        //     8: aload_0        
        //     9: invokevirtual   com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer$1.getConsumer:()Lcom/facebook/imagepipeline/producers/Consumer;
        //    12: aconst_null    
        //    13: iconst_1       
        //    14: invokeinterface com/facebook/imagepipeline/producers/Consumer.onNewResult:(Ljava/lang/Object;Z)V
        //    19: return         
        //    20: aload_1        
        //    21: invokevirtual   com/facebook/common/references/CloseableReference.get:()Ljava/lang/Object;
        //    24: checkcast       Lcom/facebook/imagepipeline/image/CloseableImage;
        //    27: invokevirtual   com/facebook/imagepipeline/image/CloseableImage.isStateful:()Z
        //    30: ifeq            45
        //    33: aload_0        
        //    34: invokevirtual   com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer$1.getConsumer:()Lcom/facebook/imagepipeline/producers/Consumer;
        //    37: aload_1        
        //    38: iload_2        
        //    39: invokeinterface com/facebook/imagepipeline/producers/Consumer.onNewResult:(Ljava/lang/Object;Z)V
        //    44: return         
        //    45: iload_2        
        //    46: ifne            141
        //    49: aload_0        
        //    50: getfield        com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer$1.this$0:Lcom/facebook/imagepipeline/producers/BitmapMemoryCacheProducer;
        //    53: invokestatic    com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer.access$000:(Lcom/facebook/imagepipeline/producers/BitmapMemoryCacheProducer;)Lcom/facebook/imagepipeline/cache/MemoryCache;
        //    56: aload_0        
        //    57: getfield        com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer$1.val$cacheKey:Lcom/facebook/cache/common/CacheKey;
        //    60: invokeinterface com/facebook/imagepipeline/cache/MemoryCache.get:(Ljava/lang/Object;)Lcom/facebook/common/references/CloseableReference;
        //    65: astore_3       
        //    66: aload_3        
        //    67: ifnull          141
        //    70: aload_1        
        //    71: invokevirtual   com/facebook/common/references/CloseableReference.get:()Ljava/lang/Object;
        //    74: checkcast       Lcom/facebook/imagepipeline/image/CloseableImage;
        //    77: invokevirtual   com/facebook/imagepipeline/image/CloseableImage.getQualityInfo:()Lcom/facebook/imagepipeline/image/QualityInfo;
        //    80: astore          4
        //    82: aload_3        
        //    83: invokevirtual   com/facebook/common/references/CloseableReference.get:()Ljava/lang/Object;
        //    86: checkcast       Lcom/facebook/imagepipeline/image/CloseableImage;
        //    89: invokevirtual   com/facebook/imagepipeline/image/CloseableImage.getQualityInfo:()Lcom/facebook/imagepipeline/image/QualityInfo;
        //    92: astore          5
        //    94: aload           5
        //    96: invokeinterface com/facebook/imagepipeline/image/QualityInfo.isOfFullQuality:()Z
        //   101: ifne            121
        //   104: aload           5
        //   106: invokeinterface com/facebook/imagepipeline/image/QualityInfo.getQuality:()I
        //   111: aload           4
        //   113: invokeinterface com/facebook/imagepipeline/image/QualityInfo.getQuality:()I
        //   118: if_icmplt       137
        //   121: aload_0        
        //   122: invokevirtual   com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer$1.getConsumer:()Lcom/facebook/imagepipeline/producers/Consumer;
        //   125: aload_3        
        //   126: iconst_0       
        //   127: invokeinterface com/facebook/imagepipeline/producers/Consumer.onNewResult:(Ljava/lang/Object;Z)V
        //   132: aload_3        
        //   133: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //   136: return         
        //   137: aload_3        
        //   138: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //   141: aload_0        
        //   142: getfield        com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer$1.this$0:Lcom/facebook/imagepipeline/producers/BitmapMemoryCacheProducer;
        //   145: invokestatic    com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer.access$000:(Lcom/facebook/imagepipeline/producers/BitmapMemoryCacheProducer;)Lcom/facebook/imagepipeline/cache/MemoryCache;
        //   148: aload_0        
        //   149: getfield        com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer$1.val$cacheKey:Lcom/facebook/cache/common/CacheKey;
        //   152: aload_1        
        //   153: invokeinterface com/facebook/imagepipeline/cache/MemoryCache.cache:(Ljava/lang/Object;Lcom/facebook/common/references/CloseableReference;)Lcom/facebook/common/references/CloseableReference;
        //   158: astore_3       
        //   159: iload_2        
        //   160: ifeq            173
        //   163: aload_0        
        //   164: invokevirtual   com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer$1.getConsumer:()Lcom/facebook/imagepipeline/producers/Consumer;
        //   167: fconst_1       
        //   168: invokeinterface com/facebook/imagepipeline/producers/Consumer.onProgressUpdate:(F)V
        //   173: aload_0        
        //   174: invokevirtual   com/facebook/imagepipeline/producers/BitmapMemoryCacheProducer$1.getConsumer:()Lcom/facebook/imagepipeline/producers/Consumer;
        //   177: astore          4
        //   179: aload_3        
        //   180: ifnull          185
        //   183: aload_3        
        //   184: astore_1       
        //   185: aload           4
        //   187: aload_1        
        //   188: iload_2        
        //   189: invokeinterface com/facebook/imagepipeline/producers/Consumer.onNewResult:(Ljava/lang/Object;Z)V
        //   194: aload_3        
        //   195: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //   198: return         
        //   199: astore_1       
        //   200: aload_3        
        //   201: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //   204: aload_1        
        //   205: athrow         
        //   206: astore_1       
        //   207: aload_3        
        //   208: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //   211: aload_1        
        //   212: athrow         
        //    Signature:
        //  (Lcom/facebook/common/references/CloseableReference<Lcom/facebook/imagepipeline/image/CloseableImage;>;Z)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  70     121    199    206    Any
        //  121    132    199    206    Any
        //  163    173    206    213    Any
        //  173    179    206    213    Any
        //  185    194    206    213    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0173:
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
