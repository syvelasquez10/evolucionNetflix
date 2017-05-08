// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import android.database.Cursor;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import com.facebook.common.logging.FLog;
import com.facebook.imageutils.JfifUtil;
import android.media.ExifInterface;
import java.io.File;
import com.facebook.imagepipeline.common.ResizeOptions;
import android.net.Uri;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import android.content.ContentResolver;
import android.graphics.Rect;
import com.facebook.imagepipeline.image.EncodedImage;

public class LocalContentUriThumbnailFetchProducer extends LocalFetchProducer implements ThumbnailProducer<EncodedImage>
{
    private static final Rect MICRO_THUMBNAIL_DIMENSIONS;
    private static final Rect MINI_THUMBNAIL_DIMENSIONS;
    private static final String[] PROJECTION;
    private static final Class<?> TAG;
    private static final String[] THUMBNAIL_PROJECTION;
    private final ContentResolver mContentResolver;
    
    static {
        TAG = LocalContentUriThumbnailFetchProducer.class;
        PROJECTION = new String[] { "_id", "_data" };
        THUMBNAIL_PROJECTION = new String[] { "_data" };
        MINI_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 512, 384);
        MICRO_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 96, 96);
    }
    
    public LocalContentUriThumbnailFetchProducer(final Executor executor, final PooledByteBufferFactory pooledByteBufferFactory, final ContentResolver mContentResolver, final boolean b) {
        super(executor, pooledByteBufferFactory, b);
        this.mContentResolver = mContentResolver;
    }
    
    private EncodedImage getCameraImage(Uri query, final ResizeOptions resizeOptions) {
        query = (Uri)this.mContentResolver.query(query, LocalContentUriThumbnailFetchProducer.PROJECTION, (String)null, (String[])null, (String)null);
        if (query == null) {
            return null;
        }
        try {
            if (((Cursor)query).getCount() == 0) {
                return null;
            }
            ((Cursor)query).moveToFirst();
            final String string = ((Cursor)query).getString(((Cursor)query).getColumnIndex("_data"));
            if (resizeOptions != null) {
                final EncodedImage thumbnail = this.getThumbnail(resizeOptions, ((Cursor)query).getInt(((Cursor)query).getColumnIndex("_id")));
                if (thumbnail != null) {
                    thumbnail.setRotationAngle(getRotationAngle(string));
                    return thumbnail;
                }
            }
            return null;
        }
        finally {
            ((Cursor)query).close();
        }
    }
    
    private static int getLength(final String s) {
        if (s == null) {
            return -1;
        }
        return (int)new File(s).length();
    }
    
    private static int getRotationAngle(final String s) {
        int autoRotateAngleFromOrientation = 0;
        if (s == null) {
            return autoRotateAngleFromOrientation;
        }
        try {
            autoRotateAngleFromOrientation = JfifUtil.getAutoRotateAngleFromOrientation(new ExifInterface(s).getAttributeInt("Orientation", 1));
            return autoRotateAngleFromOrientation;
        }
        catch (IOException ex) {
            FLog.e(LocalContentUriThumbnailFetchProducer.TAG, ex, "Unable to retrieve thumbnail rotation for %s", s);
            return 0;
        }
    }
    
    private EncodedImage getThumbnail(final ResizeOptions p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: aload_1        
        //     4: invokestatic    com/facebook/imagepipeline/producers/LocalContentUriThumbnailFetchProducer.getThumbnailKind:(Lcom/facebook/imagepipeline/common/ResizeOptions;)I
        //     7: istore_3       
        //     8: iload_3        
        //     9: ifne            15
        //    12: aload           4
        //    14: areturn        
        //    15: aload_0        
        //    16: getfield        com/facebook/imagepipeline/producers/LocalContentUriThumbnailFetchProducer.mContentResolver:Landroid/content/ContentResolver;
        //    19: iload_2        
        //    20: i2l            
        //    21: iload_3        
        //    22: getstatic       com/facebook/imagepipeline/producers/LocalContentUriThumbnailFetchProducer.THUMBNAIL_PROJECTION:[Ljava/lang/String;
        //    25: invokestatic    android/provider/MediaStore$Images$Thumbnails.queryMiniThumbnail:(Landroid/content/ContentResolver;JI[Ljava/lang/String;)Landroid/database/Cursor;
        //    28: astore_1       
        //    29: aload_1        
        //    30: ifnonnull       45
        //    33: aload_1        
        //    34: ifnull          12
        //    37: aload_1        
        //    38: invokeinterface android/database/Cursor.close:()V
        //    43: aconst_null    
        //    44: areturn        
        //    45: aload_1        
        //    46: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    51: pop            
        //    52: aload_1        
        //    53: invokeinterface android/database/Cursor.getCount:()I
        //    58: ifle            129
        //    61: aload_1        
        //    62: aload_1        
        //    63: ldc             "_data"
        //    65: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //    70: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    75: astore          5
        //    77: new             Ljava/io/File;
        //    80: dup            
        //    81: aload           5
        //    83: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    86: invokevirtual   java/io/File.exists:()Z
        //    89: ifeq            129
        //    92: aload_0        
        //    93: new             Ljava/io/FileInputStream;
        //    96: dup            
        //    97: aload           5
        //    99: invokespecial   java/io/FileInputStream.<init>:(Ljava/lang/String;)V
        //   102: aload           5
        //   104: invokestatic    com/facebook/imagepipeline/producers/LocalContentUriThumbnailFetchProducer.getLength:(Ljava/lang/String;)I
        //   107: invokevirtual   com/facebook/imagepipeline/producers/LocalContentUriThumbnailFetchProducer.getEncodedImage:(Ljava/io/InputStream;I)Lcom/facebook/imagepipeline/image/EncodedImage;
        //   110: astore          5
        //   112: aload           5
        //   114: astore          4
        //   116: aload_1        
        //   117: ifnull          12
        //   120: aload_1        
        //   121: invokeinterface android/database/Cursor.close:()V
        //   126: aload           5
        //   128: areturn        
        //   129: aload_1        
        //   130: ifnull          12
        //   133: aload_1        
        //   134: invokeinterface android/database/Cursor.close:()V
        //   139: aconst_null    
        //   140: areturn        
        //   141: astore_1       
        //   142: aconst_null    
        //   143: astore          5
        //   145: aload_1        
        //   146: astore          4
        //   148: aload           5
        //   150: ifnull          160
        //   153: aload           5
        //   155: invokeinterface android/database/Cursor.close:()V
        //   160: aload           4
        //   162: athrow         
        //   163: astore          4
        //   165: aload_1        
        //   166: astore          5
        //   168: goto            148
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  15     29     141    148    Any
        //  45     112    163    171    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
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
    
    private static int getThumbnailKind(final ResizeOptions resizeOptions) {
        if (ThumbnailSizeChecker.isImageBigEnough(LocalContentUriThumbnailFetchProducer.MICRO_THUMBNAIL_DIMENSIONS.width(), LocalContentUriThumbnailFetchProducer.MICRO_THUMBNAIL_DIMENSIONS.height(), resizeOptions)) {
            return 3;
        }
        if (ThumbnailSizeChecker.isImageBigEnough(LocalContentUriThumbnailFetchProducer.MINI_THUMBNAIL_DIMENSIONS.width(), LocalContentUriThumbnailFetchProducer.MINI_THUMBNAIL_DIMENSIONS.height(), resizeOptions)) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public boolean canProvideImageForSize(final ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(LocalContentUriThumbnailFetchProducer.MINI_THUMBNAIL_DIMENSIONS.width(), LocalContentUriThumbnailFetchProducer.MINI_THUMBNAIL_DIMENSIONS.height(), resizeOptions);
    }
    
    @Override
    protected EncodedImage getEncodedImage(final ImageRequest imageRequest) {
        final Uri sourceUri = imageRequest.getSourceUri();
        if (UriUtil.isLocalCameraUri(sourceUri)) {
            final EncodedImage cameraImage = this.getCameraImage(sourceUri, imageRequest.getResizeOptions());
            if (cameraImage != null) {
                return cameraImage;
            }
        }
        return null;
    }
    
    @Override
    protected String getProducerName() {
        return "LocalContentUriThumbnailFetchProducer";
    }
}
