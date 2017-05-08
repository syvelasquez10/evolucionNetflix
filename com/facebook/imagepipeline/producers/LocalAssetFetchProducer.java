// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import android.content.res.AssetManager;

public class LocalAssetFetchProducer extends LocalFetchProducer
{
    private final AssetManager mAssetManager;
    
    public LocalAssetFetchProducer(final Executor executor, final PooledByteBufferFactory pooledByteBufferFactory, final AssetManager mAssetManager, final boolean b) {
        super(executor, pooledByteBufferFactory, b);
        this.mAssetManager = mAssetManager;
    }
    
    private static String getAssetName(final ImageRequest imageRequest) {
        return imageRequest.getSourceUri().getPath().substring(1);
    }
    
    private int getLength(final ImageRequest p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/imagepipeline/producers/LocalAssetFetchProducer.mAssetManager:Landroid/content/res/AssetManager;
        //     4: aload_1        
        //     5: invokestatic    com/facebook/imagepipeline/producers/LocalAssetFetchProducer.getAssetName:(Lcom/facebook/imagepipeline/request/ImageRequest;)Ljava/lang/String;
        //     8: invokevirtual   android/content/res/AssetManager.openFd:(Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
        //    11: astore_1       
        //    12: aload_1        
        //    13: invokevirtual   android/content/res/AssetFileDescriptor.getLength:()J
        //    16: lstore          4
        //    18: lload           4
        //    20: l2i            
        //    21: istore_3       
        //    22: iload_3        
        //    23: istore_2       
        //    24: aload_1        
        //    25: ifnull          34
        //    28: aload_1        
        //    29: invokevirtual   android/content/res/AssetFileDescriptor.close:()V
        //    32: iload_3        
        //    33: istore_2       
        //    34: iload_2        
        //    35: ireturn        
        //    36: astore_1       
        //    37: aconst_null    
        //    38: astore_1       
        //    39: iconst_m1      
        //    40: istore_2       
        //    41: aload_1        
        //    42: ifnull          34
        //    45: aload_1        
        //    46: invokevirtual   android/content/res/AssetFileDescriptor.close:()V
        //    49: iconst_m1      
        //    50: ireturn        
        //    51: astore_1       
        //    52: iconst_m1      
        //    53: ireturn        
        //    54: astore          6
        //    56: aconst_null    
        //    57: astore_1       
        //    58: aload_1        
        //    59: ifnull          66
        //    62: aload_1        
        //    63: invokevirtual   android/content/res/AssetFileDescriptor.close:()V
        //    66: aload           6
        //    68: athrow         
        //    69: astore_1       
        //    70: iload_3        
        //    71: ireturn        
        //    72: astore_1       
        //    73: goto            66
        //    76: astore          6
        //    78: goto            58
        //    81: astore          6
        //    83: goto            39
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      12     36     39     Ljava/io/IOException;
        //  0      12     54     58     Any
        //  12     18     81     86     Ljava/io/IOException;
        //  12     18     76     81     Any
        //  28     32     69     72     Ljava/io/IOException;
        //  45     49     51     54     Ljava/io/IOException;
        //  62     66     72     76     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
    
    @Override
    protected EncodedImage getEncodedImage(final ImageRequest imageRequest) {
        return this.getEncodedImage(this.mAssetManager.open(getAssetName(imageRequest), 2), this.getLength(imageRequest));
    }
    
    @Override
    protected String getProducerName() {
        return "LocalAssetFetchProducer";
    }
}
