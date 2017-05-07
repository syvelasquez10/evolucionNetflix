// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

import android.print.PrintAttributes$Builder;
import android.print.PrintDocumentAdapter$WriteResultCallback;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo$Builder;
import android.os.Bundle;
import android.print.PrintDocumentAdapter$LayoutResultCallback;
import android.os.CancellationSignal;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintAttributes$MediaSize;
import android.print.PrintManager;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import android.util.Log;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.net.Uri;
import android.content.Context;

public class PrintHelperKitkat
{
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelperKitkat";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode;
    final Context mContext;
    int mScaleMode;
    
    PrintHelperKitkat(final Context mContext) {
        this.mScaleMode = 2;
        this.mColorMode = 2;
        this.mContext = mContext;
    }
    
    private Bitmap loadBitmap(final Uri uri, BitmapFactory$Options decodeStream) throws FileNotFoundException {
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        InputStream openInputStream = null;
        try {
            final InputStream inputStream = openInputStream = this.mContext.getContentResolver().openInputStream(uri);
            decodeStream = (BitmapFactory$Options)BitmapFactory.decodeStream(inputStream, (Rect)null, decodeStream);
            if (inputStream == null) {
                return (Bitmap)decodeStream;
            }
            try {
                inputStream.close();
                return (Bitmap)decodeStream;
            }
            catch (IOException ex) {
                Log.w("PrintHelperKitkat", "close fail ", (Throwable)ex);
                return (Bitmap)decodeStream;
            }
        }
        finally {
            Label_0075: {
                if (openInputStream == null) {
                    break Label_0075;
                }
                try {
                    openInputStream.close();
                }
                catch (IOException ex2) {
                    Log.w("PrintHelperKitkat", "close fail ", (Throwable)ex2);
                }
            }
        }
    }
    
    private Bitmap loadConstrainedBitmap(final Uri uri, final int n) throws FileNotFoundException {
        if (n <= 0 || uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        this.loadBitmap(uri, bitmapFactory$Options);
        final int outWidth = bitmapFactory$Options.outWidth;
        final int outHeight = bitmapFactory$Options.outHeight;
        if (outWidth > 0 && outHeight > 0) {
            int i;
            int inSampleSize;
            for (i = Math.max(outWidth, outHeight), inSampleSize = 1; i > n; i >>>= 1, inSampleSize <<= 1) {}
            if (inSampleSize > 0 && Math.min(outWidth, outHeight) / inSampleSize > 0) {
                final BitmapFactory$Options bitmapFactory$Options2 = new BitmapFactory$Options();
                bitmapFactory$Options2.inMutable = true;
                bitmapFactory$Options2.inSampleSize = inSampleSize;
                return this.loadBitmap(uri, bitmapFactory$Options2);
            }
        }
        return null;
    }
    
    public int getColorMode() {
        return this.mColorMode;
    }
    
    public int getScaleMode() {
        return this.mScaleMode;
    }
    
    public void printBitmap(final String s, final Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        final int mScaleMode = this.mScaleMode;
        final PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
        PrintAttributes$MediaSize mediaSize = PrintAttributes$MediaSize.UNKNOWN_PORTRAIT;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            mediaSize = PrintAttributes$MediaSize.UNKNOWN_LANDSCAPE;
        }
        printManager.print(s, (PrintDocumentAdapter)new PrintDocumentAdapter() {
            private PrintAttributes mAttributes;
            
            public void onLayout(final PrintAttributes printAttributes, final PrintAttributes mAttributes, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$LayoutResultCallback printDocumentAdapter$LayoutResultCallback, final Bundle bundle) {
                boolean b = true;
                this.mAttributes = mAttributes;
                final PrintDocumentInfo build = new PrintDocumentInfo$Builder(s).setContentType(1).setPageCount(1).build();
                if (mAttributes.equals((Object)printAttributes)) {
                    b = false;
                }
                printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, b);
            }
            
            public void onWrite(final PageRange[] p0, final ParcelFileDescriptor p1, final CancellationSignal p2, final PrintDocumentAdapter$WriteResultCallback p3) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: new             Landroid/print/pdf/PrintedPdfDocument;
                //     3: dup            
                //     4: aload_0        
                //     5: getfield        android/support/v4/print/PrintHelperKitkat$1.this$0:Landroid/support/v4/print/PrintHelperKitkat;
                //     8: getfield        android/support/v4/print/PrintHelperKitkat.mContext:Landroid/content/Context;
                //    11: aload_0        
                //    12: getfield        android/support/v4/print/PrintHelperKitkat$1.mAttributes:Landroid/print/PrintAttributes;
                //    15: invokespecial   android/print/pdf/PrintedPdfDocument.<init>:(Landroid/content/Context;Landroid/print/PrintAttributes;)V
                //    18: astore_1       
                //    19: aload_1        
                //    20: iconst_1       
                //    21: invokevirtual   android/print/pdf/PrintedPdfDocument.startPage:(I)Landroid/graphics/pdf/PdfDocument$Page;
                //    24: astore_3       
                //    25: new             Landroid/graphics/RectF;
                //    28: dup            
                //    29: aload_3        
                //    30: invokevirtual   android/graphics/pdf/PdfDocument$Page.getInfo:()Landroid/graphics/pdf/PdfDocument$PageInfo;
                //    33: invokevirtual   android/graphics/pdf/PdfDocument$PageInfo.getContentRect:()Landroid/graphics/Rect;
                //    36: invokespecial   android/graphics/RectF.<init>:(Landroid/graphics/Rect;)V
                //    39: astore          6
                //    41: new             Landroid/graphics/Matrix;
                //    44: dup            
                //    45: invokespecial   android/graphics/Matrix.<init>:()V
                //    48: astore          7
                //    50: aload           6
                //    52: invokevirtual   android/graphics/RectF.width:()F
                //    55: aload_0        
                //    56: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
                //    59: invokevirtual   android/graphics/Bitmap.getWidth:()I
                //    62: i2f            
                //    63: fdiv           
                //    64: fstore          5
                //    66: aload_0        
                //    67: getfield        android/support/v4/print/PrintHelperKitkat$1.val$fittingMode:I
                //    70: iconst_2       
                //    71: if_icmpne       215
                //    74: fload           5
                //    76: aload           6
                //    78: invokevirtual   android/graphics/RectF.height:()F
                //    81: aload_0        
                //    82: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
                //    85: invokevirtual   android/graphics/Bitmap.getHeight:()I
                //    88: i2f            
                //    89: fdiv           
                //    90: invokestatic    java/lang/Math.max:(FF)F
                //    93: fstore          5
                //    95: aload           7
                //    97: fload           5
                //    99: fload           5
                //   101: invokevirtual   android/graphics/Matrix.postScale:(FF)Z
                //   104: pop            
                //   105: aload           7
                //   107: aload           6
                //   109: invokevirtual   android/graphics/RectF.width:()F
                //   112: aload_0        
                //   113: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
                //   116: invokevirtual   android/graphics/Bitmap.getWidth:()I
                //   119: i2f            
                //   120: fload           5
                //   122: fmul           
                //   123: fsub           
                //   124: fconst_2       
                //   125: fdiv           
                //   126: aload           6
                //   128: invokevirtual   android/graphics/RectF.height:()F
                //   131: aload_0        
                //   132: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
                //   135: invokevirtual   android/graphics/Bitmap.getHeight:()I
                //   138: i2f            
                //   139: fload           5
                //   141: fmul           
                //   142: fsub           
                //   143: fconst_2       
                //   144: fdiv           
                //   145: invokevirtual   android/graphics/Matrix.postTranslate:(FF)Z
                //   148: pop            
                //   149: aload_3        
                //   150: invokevirtual   android/graphics/pdf/PdfDocument$Page.getCanvas:()Landroid/graphics/Canvas;
                //   153: aload_0        
                //   154: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
                //   157: aload           7
                //   159: aconst_null    
                //   160: invokevirtual   android/graphics/Canvas.drawBitmap:(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
                //   163: aload_1        
                //   164: aload_3        
                //   165: invokevirtual   android/print/pdf/PrintedPdfDocument.finishPage:(Landroid/graphics/pdf/PdfDocument$Page;)V
                //   168: aload_1        
                //   169: new             Ljava/io/FileOutputStream;
                //   172: dup            
                //   173: aload_2        
                //   174: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
                //   177: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/FileDescriptor;)V
                //   180: invokevirtual   android/print/pdf/PrintedPdfDocument.writeTo:(Ljava/io/OutputStream;)V
                //   183: aload           4
                //   185: iconst_1       
                //   186: anewarray       Landroid/print/PageRange;
                //   189: dup            
                //   190: iconst_0       
                //   191: getstatic       android/print/PageRange.ALL_PAGES:Landroid/print/PageRange;
                //   194: aastore        
                //   195: invokevirtual   android/print/PrintDocumentAdapter$WriteResultCallback.onWriteFinished:([Landroid/print/PageRange;)V
                //   198: aload_1        
                //   199: ifnull          206
                //   202: aload_1        
                //   203: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
                //   206: aload_2        
                //   207: ifnull          214
                //   210: aload_2        
                //   211: invokevirtual   android/os/ParcelFileDescriptor.close:()V
                //   214: return         
                //   215: fload           5
                //   217: aload           6
                //   219: invokevirtual   android/graphics/RectF.height:()F
                //   222: aload_0        
                //   223: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
                //   226: invokevirtual   android/graphics/Bitmap.getHeight:()I
                //   229: i2f            
                //   230: fdiv           
                //   231: invokestatic    java/lang/Math.min:(FF)F
                //   234: fstore          5
                //   236: goto            95
                //   239: astore_3       
                //   240: ldc             "PrintHelperKitkat"
                //   242: ldc             "Error writing printed content"
                //   244: aload_3        
                //   245: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //   248: pop            
                //   249: aload           4
                //   251: aconst_null    
                //   252: invokevirtual   android/print/PrintDocumentAdapter$WriteResultCallback.onWriteFailed:(Ljava/lang/CharSequence;)V
                //   255: goto            198
                //   258: astore_3       
                //   259: aload_1        
                //   260: ifnull          267
                //   263: aload_1        
                //   264: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
                //   267: aload_2        
                //   268: ifnull          275
                //   271: aload_2        
                //   272: invokevirtual   android/os/ParcelFileDescriptor.close:()V
                //   275: aload_3        
                //   276: athrow         
                //   277: astore_1       
                //   278: return         
                //   279: astore_1       
                //   280: goto            275
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  19     95     258    277    Any
                //  95     168    258    277    Any
                //  168    198    239    258    Ljava/io/IOException;
                //  168    198    258    277    Any
                //  210    214    277    279    Ljava/io/IOException;
                //  215    236    258    277    Any
                //  240    255    258    277    Any
                //  271    275    279    283    Ljava/io/IOException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 146, Size: 146
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
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
        }, new PrintAttributes$Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
    }
    
    public void printBitmap(final String s, final Uri uri) throws FileNotFoundException {
        this.printBitmap(s, this.loadConstrainedBitmap(uri, 3500));
    }
    
    public void setColorMode(final int mColorMode) {
        this.mColorMode = mColorMode;
    }
    
    public void setScaleMode(final int mScaleMode) {
        this.mScaleMode = mScaleMode;
    }
}
