// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;
import com.facebook.common.internal.Preconditions;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.facebook.common.webp.WebpSupportStatus;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import java.io.FileDescriptor;
import com.facebook.common.internal.Throwables;
import android.os.MemoryFile;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import java.lang.reflect.Method;

public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder
{
    private static Method sGetFileDescriptorMethod;
    private final boolean mWebpSupportEnabled;
    
    public GingerbreadPurgeableDecoder(final boolean mWebpSupportEnabled) {
        this.mWebpSupportEnabled = mWebpSupportEnabled;
    }
    
    private static MemoryFile copyToMemoryFile(final CloseableReference<PooledByteBuffer> p0, final int p1, final byte[] p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          6
        //     3: aload_2        
        //     4: ifnonnull       109
        //     7: iconst_0       
        //     8: istore_3       
        //     9: new             Landroid/os/MemoryFile;
        //    12: dup            
        //    13: aconst_null    
        //    14: iload_3        
        //    15: iload_1        
        //    16: iadd           
        //    17: invokespecial   android/os/MemoryFile.<init>:(Ljava/lang/String;I)V
        //    20: astore          7
        //    22: aload           7
        //    24: iconst_0       
        //    25: invokevirtual   android/os/MemoryFile.allowPurging:(Z)Z
        //    28: pop            
        //    29: new             Lcom/facebook/imagepipeline/memory/PooledByteBufferInputStream;
        //    32: dup            
        //    33: aload_0        
        //    34: invokevirtual   com/facebook/common/references/CloseableReference.get:()Ljava/lang/Object;
        //    37: checkcast       Lcom/facebook/imagepipeline/memory/PooledByteBuffer;
        //    40: invokespecial   com/facebook/imagepipeline/memory/PooledByteBufferInputStream.<init>:(Lcom/facebook/imagepipeline/memory/PooledByteBuffer;)V
        //    43: astore          4
        //    45: new             Lcom/facebook/common/streams/LimitedInputStream;
        //    48: dup            
        //    49: aload           4
        //    51: iload_1        
        //    52: invokespecial   com/facebook/common/streams/LimitedInputStream.<init>:(Ljava/io/InputStream;I)V
        //    55: astore          5
        //    57: aload           7
        //    59: invokevirtual   android/os/MemoryFile.getOutputStream:()Ljava/io/OutputStream;
        //    62: astore          6
        //    64: aload           5
        //    66: aload           6
        //    68: invokestatic    com/facebook/common/internal/ByteStreams.copy:(Ljava/io/InputStream;Ljava/io/OutputStream;)J
        //    71: pop2           
        //    72: aload_2        
        //    73: ifnull          86
        //    76: aload           7
        //    78: aload_2        
        //    79: iconst_0       
        //    80: iload_1        
        //    81: aload_2        
        //    82: arraylength    
        //    83: invokevirtual   android/os/MemoryFile.writeBytes:([BIII)V
        //    86: aload_0        
        //    87: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //    90: aload           4
        //    92: invokestatic    com/facebook/common/internal/Closeables.closeQuietly:(Ljava/io/InputStream;)V
        //    95: aload           5
        //    97: invokestatic    com/facebook/common/internal/Closeables.closeQuietly:(Ljava/io/InputStream;)V
        //   100: aload           6
        //   102: iconst_1       
        //   103: invokestatic    com/facebook/common/internal/Closeables.close:(Ljava/io/Closeable;Z)V
        //   106: aload           7
        //   108: areturn        
        //   109: aload_2        
        //   110: arraylength    
        //   111: istore_3       
        //   112: goto            9
        //   115: astore_2       
        //   116: aconst_null    
        //   117: astore          4
        //   119: aconst_null    
        //   120: astore          5
        //   122: aload_0        
        //   123: invokestatic    com/facebook/common/references/CloseableReference.closeSafely:(Lcom/facebook/common/references/CloseableReference;)V
        //   126: aload           5
        //   128: invokestatic    com/facebook/common/internal/Closeables.closeQuietly:(Ljava/io/InputStream;)V
        //   131: aload           6
        //   133: invokestatic    com/facebook/common/internal/Closeables.closeQuietly:(Ljava/io/InputStream;)V
        //   136: aload           4
        //   138: iconst_1       
        //   139: invokestatic    com/facebook/common/internal/Closeables.close:(Ljava/io/Closeable;Z)V
        //   142: aload_2        
        //   143: athrow         
        //   144: astore_2       
        //   145: aconst_null    
        //   146: astore          7
        //   148: aload           4
        //   150: astore          5
        //   152: aload           7
        //   154: astore          4
        //   156: goto            122
        //   159: astore_2       
        //   160: aconst_null    
        //   161: astore          6
        //   163: aload           4
        //   165: astore          7
        //   167: aload           6
        //   169: astore          4
        //   171: aload           5
        //   173: astore          6
        //   175: aload           7
        //   177: astore          5
        //   179: goto            122
        //   182: astore_2       
        //   183: aload           4
        //   185: astore          7
        //   187: aload           6
        //   189: astore          4
        //   191: aload           5
        //   193: astore          6
        //   195: aload           7
        //   197: astore          5
        //   199: goto            122
        //    Signature:
        //  (Lcom/facebook/common/references/CloseableReference<Lcom/facebook/imagepipeline/memory/PooledByteBuffer;>;I[B)Landroid/os/MemoryFile;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  29     45     115    122    Any
        //  45     57     144    159    Any
        //  57     64     159    182    Any
        //  64     72     182    202    Any
        //  76     86     182    202    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 108, Size: 108
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
    
    private Method getFileDescriptorMethod() {
        synchronized (this) {
            Label_0024: {
                if (GingerbreadPurgeableDecoder.sGetFileDescriptorMethod != null) {
                    break Label_0024;
                }
                try {
                    GingerbreadPurgeableDecoder.sGetFileDescriptorMethod = MemoryFile.class.getDeclaredMethod("getFileDescriptor", (Class<?>[])new Class[0]);
                    return GingerbreadPurgeableDecoder.sGetFileDescriptorMethod;
                }
                catch (Exception ex) {
                    throw Throwables.propagate(ex);
                }
            }
        }
    }
    
    private FileDescriptor getMemoryFileDescriptor(final MemoryFile memoryFile) {
        try {
            return (FileDescriptor)this.getFileDescriptorMethod().invoke(memoryFile, new Object[0]);
        }
        catch (Exception ex) {
            throw Throwables.propagate(ex);
        }
    }
    
    protected Bitmap decodeByteArrayAsPurgeable(final CloseableReference<PooledByteBuffer> closeableReference, final BitmapFactory$Options bitmapFactory$Options) {
        return this.decodeFileDescriptorAsPurgeable(closeableReference, closeableReference.get().size(), null, bitmapFactory$Options);
    }
    
    protected Bitmap decodeFileDescriptorAsPurgeable(final CloseableReference<PooledByteBuffer> closeableReference, final int n, final byte[] array, final BitmapFactory$Options bitmapFactory$Options) {
        MemoryFile memoryFile = null;
        MemoryFile copyToMemoryFile = null;
        try {
            final MemoryFile memoryFile2 = memoryFile = (copyToMemoryFile = copyToMemoryFile(closeableReference, n, array));
            final FileDescriptor memoryFileDescriptor = this.getMemoryFileDescriptor(memoryFile2);
            copyToMemoryFile = memoryFile2;
            memoryFile = memoryFile2;
            Bitmap bitmap;
            if (this.mWebpSupportEnabled) {
                copyToMemoryFile = memoryFile2;
                memoryFile = memoryFile2;
                bitmap = WebpSupportStatus.sWebpBitmapFactory.decodeFileDescriptor(memoryFileDescriptor, null, bitmapFactory$Options);
            }
            else {
                copyToMemoryFile = memoryFile2;
                memoryFile = memoryFile2;
                bitmap = BitmapFactory.decodeFileDescriptor(memoryFileDescriptor, (Rect)null, bitmapFactory$Options);
            }
            copyToMemoryFile = memoryFile2;
            memoryFile = memoryFile2;
            return Preconditions.checkNotNull(bitmap, "BitmapFactory returned null");
        }
        catch (IOException ex) {
            memoryFile = copyToMemoryFile;
            throw Throwables.propagate(ex);
        }
        finally {
            if (memoryFile != null) {
                memoryFile.close();
            }
        }
    }
    
    protected Bitmap decodeJPEGByteArrayAsPurgeable(final CloseableReference<PooledByteBuffer> closeableReference, final int n, final BitmapFactory$Options bitmapFactory$Options) {
        byte[] eoi;
        if (DalvikPurgeableDecoder.endsWithEOI(closeableReference, n)) {
            eoi = null;
        }
        else {
            eoi = GingerbreadPurgeableDecoder.EOI;
        }
        return this.decodeFileDescriptorAsPurgeable(closeableReference, n, eoi, bitmapFactory$Options);
    }
}
