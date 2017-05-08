// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.io.File;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imageutils.JfifUtil;
import android.net.Uri;
import android.util.Pair;
import com.facebook.imageformat.ImageFormat;
import com.facebook.common.references.CloseableReference;
import java.io.InputStream;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import android.media.ExifInterface;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import android.content.ContentResolver;
import com.facebook.imagepipeline.image.EncodedImage;

public class LocalExifThumbnailProducer implements ThumbnailProducer<EncodedImage>
{
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    
    public LocalExifThumbnailProducer(final Executor mExecutor, final PooledByteBufferFactory mPooledByteBufferFactory, final ContentResolver mContentResolver) {
        this.mExecutor = mExecutor;
        this.mPooledByteBufferFactory = mPooledByteBufferFactory;
        this.mContentResolver = mContentResolver;
    }
    
    private EncodedImage buildEncodedImage(PooledByteBuffer of, final ExifInterface exifInterface) {
        final Pair<Integer, Integer> decodeDimensions = BitmapUtil.decodeDimensions(new PooledByteBufferInputStream(of));
        final int rotationAngle = this.getRotationAngle(exifInterface);
        while (true) {
            while (true) {
                Label_0037: {
                    if (decodeDimensions != null) {
                        final int intValue = (int)decodeDimensions.first;
                        break Label_0037;
                    }
                    Label_0099: {
                        break Label_0099;
                        while (true) {
                            of = (PooledByteBuffer)CloseableReference.of(of);
                            try {
                                final EncodedImage encodedImage = new EncodedImage((CloseableReference<PooledByteBuffer>)of);
                                CloseableReference.closeSafely((CloseableReference<?>)of);
                                encodedImage.setImageFormat(ImageFormat.JPEG);
                                encodedImage.setRotationAngle(rotationAngle);
                                final int intValue;
                                encodedImage.setWidth(intValue);
                                final int intValue2;
                                encodedImage.setHeight(intValue2);
                                return encodedImage;
                                intValue = -1;
                                break;
                                intValue2 = -1;
                            }
                            finally {
                                CloseableReference.closeSafely((CloseableReference<?>)of);
                            }
                        }
                    }
                }
                if (decodeDimensions != null) {
                    final int intValue2 = (int)decodeDimensions.second;
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    private String getRealPathFromUri(final Uri p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_1        
        //     3: invokestatic    com/facebook/common/util/UriUtil.isLocalContentUri:(Landroid/net/Uri;)Z
        //     6: ifeq            75
        //     9: aload_0        
        //    10: getfield        com/facebook/imagepipeline/producers/LocalExifThumbnailProducer.mContentResolver:Landroid/content/ContentResolver;
        //    13: aload_1        
        //    14: aconst_null    
        //    15: aconst_null    
        //    16: aconst_null    
        //    17: aconst_null    
        //    18: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    21: astore_2       
        //    22: aload_2        
        //    23: ifnull          93
        //    26: aload_2        
        //    27: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    32: pop            
        //    33: aload_2        
        //    34: aload_2        
        //    35: ldc             "_data"
        //    37: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //    42: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    47: astore_1       
        //    48: aload_2        
        //    49: ifnull          58
        //    52: aload_2        
        //    53: invokeinterface android/database/Cursor.close:()V
        //    58: aload_1        
        //    59: areturn        
        //    60: astore_1       
        //    61: aload_3        
        //    62: astore_2       
        //    63: aload_2        
        //    64: ifnull          73
        //    67: aload_2        
        //    68: invokeinterface android/database/Cursor.close:()V
        //    73: aload_1        
        //    74: athrow         
        //    75: aload_1        
        //    76: invokestatic    com/facebook/common/util/UriUtil.isLocalFileUri:(Landroid/net/Uri;)Z
        //    79: ifeq            91
        //    82: aload_1        
        //    83: invokevirtual   android/net/Uri.getPath:()Ljava/lang/String;
        //    86: areturn        
        //    87: astore_1       
        //    88: goto            63
        //    91: aconst_null    
        //    92: areturn        
        //    93: aconst_null    
        //    94: astore_1       
        //    95: goto            48
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      22     60     63     Any
        //  26     48     87     91     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    private int getRotationAngle(final ExifInterface exifInterface) {
        return JfifUtil.getAutoRotateAngleFromOrientation(Integer.parseInt(exifInterface.getAttribute("Orientation")));
    }
    
    @Override
    public boolean canProvideImageForSize(final ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(512, 512, resizeOptions);
    }
    
    boolean canReadAsFile(final String s) {
        if (s != null) {
            final File file = new File(s);
            if (file.exists() && file.canRead()) {
                return true;
            }
        }
        return false;
    }
    
    ExifInterface getExifInterface(final Uri uri) {
        final String realPathFromUri = this.getRealPathFromUri(uri);
        if (this.canReadAsFile(realPathFromUri)) {
            return new ExifInterface(realPathFromUri);
        }
        return null;
    }
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        final LocalExifThumbnailProducer$1 localExifThumbnailProducer$1 = new LocalExifThumbnailProducer$1(this, consumer, producerContext.getListener(), "LocalExifThumbnailProducer", producerContext.getId(), producerContext.getImageRequest());
        producerContext.addCallbacks(new LocalExifThumbnailProducer$2(this, localExifThumbnailProducer$1));
        this.mExecutor.execute(localExifThumbnailProducer$1);
    }
}
