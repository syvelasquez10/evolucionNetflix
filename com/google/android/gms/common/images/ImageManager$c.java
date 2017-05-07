// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.ParcelFileDescriptor;

final class ImageManager$c implements Runnable
{
    final /* synthetic */ ImageManager Kw;
    private final ParcelFileDescriptor Kx;
    private final Uri mUri;
    
    public ImageManager$c(final ImageManager kw, final Uri mUri, final ParcelFileDescriptor kx) {
        this.Kw = kw;
        this.mUri = mUri;
        this.Kx = kx;
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "LoadBitmapFromDiskRunnable can't be executed in the main thread"
        //     2: invokestatic    com/google/android/gms/common/internal/a.aU:(Ljava/lang/String;)V
        //     5: iconst_0       
        //     6: istore_1       
        //     7: iconst_0       
        //     8: istore_2       
        //     9: aconst_null    
        //    10: astore_3       
        //    11: aconst_null    
        //    12: astore          4
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/common/images/ImageManager$c.Kx:Landroid/os/ParcelFileDescriptor;
        //    18: ifnull          41
        //    21: aload_0        
        //    22: getfield        com/google/android/gms/common/images/ImageManager$c.Kx:Landroid/os/ParcelFileDescriptor;
        //    25: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //    28: invokestatic    android/graphics/BitmapFactory.decodeFileDescriptor:(Ljava/io/FileDescriptor;)Landroid/graphics/Bitmap;
        //    31: astore_3       
        //    32: iload_2        
        //    33: istore_1       
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/common/images/ImageManager$c.Kx:Landroid/os/ParcelFileDescriptor;
        //    38: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //    41: new             Ljava/util/concurrent/CountDownLatch;
        //    44: dup            
        //    45: iconst_1       
        //    46: invokespecial   java/util/concurrent/CountDownLatch.<init>:(I)V
        //    49: astore          4
        //    51: aload_0        
        //    52: getfield        com/google/android/gms/common/images/ImageManager$c.Kw:Lcom/google/android/gms/common/images/ImageManager;
        //    55: invokestatic    com/google/android/gms/common/images/ImageManager.g:(Lcom/google/android/gms/common/images/ImageManager;)Landroid/os/Handler;
        //    58: new             Lcom/google/android/gms/common/images/ImageManager$f;
        //    61: dup            
        //    62: aload_0        
        //    63: getfield        com/google/android/gms/common/images/ImageManager$c.Kw:Lcom/google/android/gms/common/images/ImageManager;
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/common/images/ImageManager$c.mUri:Landroid/net/Uri;
        //    70: aload_3        
        //    71: iload_1        
        //    72: aload           4
        //    74: invokespecial   com/google/android/gms/common/images/ImageManager$f.<init>:(Lcom/google/android/gms/common/images/ImageManager;Landroid/net/Uri;Landroid/graphics/Bitmap;ZLjava/util/concurrent/CountDownLatch;)V
        //    77: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //    80: pop            
        //    81: aload           4
        //    83: invokevirtual   java/util/concurrent/CountDownLatch.await:()V
        //    86: return         
        //    87: astore_3       
        //    88: ldc             "ImageManager"
        //    90: new             Ljava/lang/StringBuilder;
        //    93: dup            
        //    94: invokespecial   java/lang/StringBuilder.<init>:()V
        //    97: ldc             "OOM while loading bitmap for uri: "
        //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   102: aload_0        
        //   103: getfield        com/google/android/gms/common/images/ImageManager$c.mUri:Landroid/net/Uri;
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   109: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   112: aload_3        
        //   113: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   116: pop            
        //   117: iconst_1       
        //   118: istore_1       
        //   119: aload           4
        //   121: astore_3       
        //   122: goto            34
        //   125: astore          4
        //   127: ldc             "ImageManager"
        //   129: ldc             "closed failed"
        //   131: aload           4
        //   133: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   136: pop            
        //   137: goto            41
        //   140: astore_3       
        //   141: ldc             "ImageManager"
        //   143: new             Ljava/lang/StringBuilder;
        //   146: dup            
        //   147: invokespecial   java/lang/StringBuilder.<init>:()V
        //   150: ldc             "Latch interrupted while posting "
        //   152: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   155: aload_0        
        //   156: getfield        com/google/android/gms/common/images/ImageManager$c.mUri:Landroid/net/Uri;
        //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   162: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   165: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   168: pop            
        //   169: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  21     32     87     125    Ljava/lang/OutOfMemoryError;
        //  34     41     125    140    Ljava/io/IOException;
        //  81     86     140    170    Ljava/lang/InterruptedException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 86, Size: 86
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
