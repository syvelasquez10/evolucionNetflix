// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

import android.print.PrintAttributes$Builder;
import android.print.PrintAttributes$MediaSize;
import android.print.PrintManager;
import java.io.InputStream;
import java.io.IOException;
import android.util.Log;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.BitmapFactory$Options;
import android.content.Context;
import android.print.PrintDocumentAdapter$WriteResultCallback;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo$Builder;
import android.os.Bundle;
import android.print.PrintDocumentAdapter$LayoutResultCallback;
import android.os.CancellationSignal;
import android.print.PrintAttributes;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.print.PrintDocumentAdapter;

class PrintHelperKitkat$2 extends PrintDocumentAdapter
{
    AsyncTask<Uri, Boolean, Bitmap> loadBitmap;
    private PrintAttributes mAttributes;
    Bitmap mBitmap;
    final /* synthetic */ PrintHelperKitkat this$0;
    final /* synthetic */ PrintHelperKitkat$OnPrintFinishCallback val$callback;
    final /* synthetic */ int val$fittingMode;
    final /* synthetic */ Uri val$imageFile;
    final /* synthetic */ String val$jobName;
    
    PrintHelperKitkat$2(final PrintHelperKitkat this$0, final String val$jobName, final Uri val$imageFile, final PrintHelperKitkat$OnPrintFinishCallback val$callback, final int val$fittingMode) {
        this.this$0 = this$0;
        this.val$jobName = val$jobName;
        this.val$imageFile = val$imageFile;
        this.val$callback = val$callback;
        this.val$fittingMode = val$fittingMode;
        this.mBitmap = null;
    }
    
    private void cancelLoad() {
        synchronized (this.this$0.mLock) {
            if (this.this$0.mDecodeOptions != null) {
                this.this$0.mDecodeOptions.requestCancelDecode();
                this.this$0.mDecodeOptions = null;
            }
        }
    }
    
    public void onFinish() {
        super.onFinish();
        this.cancelLoad();
        this.loadBitmap.cancel(true);
        if (this.val$callback != null) {
            this.val$callback.onFinish();
        }
    }
    
    public void onLayout(final PrintAttributes printAttributes, final PrintAttributes printAttributes2, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$LayoutResultCallback printDocumentAdapter$LayoutResultCallback, final Bundle bundle) {
        boolean b = true;
        if (cancellationSignal.isCanceled()) {
            printDocumentAdapter$LayoutResultCallback.onLayoutCancelled();
            this.mAttributes = printAttributes2;
            return;
        }
        if (this.mBitmap != null) {
            final PrintDocumentInfo build = new PrintDocumentInfo$Builder(this.val$jobName).setContentType(1).setPageCount(1).build();
            if (printAttributes2.equals((Object)printAttributes)) {
                b = false;
            }
            printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, b);
            return;
        }
        (this.loadBitmap = new PrintHelperKitkat$2$1(this, cancellationSignal, printAttributes2, printAttributes, printDocumentAdapter$LayoutResultCallback)).execute((Object[])new Uri[0]);
        this.mAttributes = printAttributes2;
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
        //     5: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //     8: getfield        android/support/v4/print/PrintHelperKitkat.mContext:Landroid/content/Context;
        //    11: aload_0        
        //    12: getfield        android/support/v4/print/PrintHelperKitkat$2.mAttributes:Landroid/print/PrintAttributes;
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
        //    39: astore          5
        //    41: aload_0        
        //    42: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //    45: aload_0        
        //    46: getfield        android/support/v4/print/PrintHelperKitkat$2.mBitmap:Landroid/graphics/Bitmap;
        //    49: invokevirtual   android/graphics/Bitmap.getWidth:()I
        //    52: aload_0        
        //    53: getfield        android/support/v4/print/PrintHelperKitkat$2.mBitmap:Landroid/graphics/Bitmap;
        //    56: invokevirtual   android/graphics/Bitmap.getHeight:()I
        //    59: aload           5
        //    61: aload_0        
        //    62: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fittingMode:I
        //    65: invokestatic    android/support/v4/print/PrintHelperKitkat.access$000:(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
        //    68: astore          5
        //    70: aload_3        
        //    71: invokevirtual   android/graphics/pdf/PdfDocument$Page.getCanvas:()Landroid/graphics/Canvas;
        //    74: aload_0        
        //    75: getfield        android/support/v4/print/PrintHelperKitkat$2.mBitmap:Landroid/graphics/Bitmap;
        //    78: aload           5
        //    80: aconst_null    
        //    81: invokevirtual   android/graphics/Canvas.drawBitmap:(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //    84: aload_1        
        //    85: aload_3        
        //    86: invokevirtual   android/print/pdf/PrintedPdfDocument.finishPage:(Landroid/graphics/pdf/PdfDocument$Page;)V
        //    89: aload_1        
        //    90: new             Ljava/io/FileOutputStream;
        //    93: dup            
        //    94: aload_2        
        //    95: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //    98: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/FileDescriptor;)V
        //   101: invokevirtual   android/print/pdf/PrintedPdfDocument.writeTo:(Ljava/io/OutputStream;)V
        //   104: aload           4
        //   106: iconst_1       
        //   107: anewarray       Landroid/print/PageRange;
        //   110: dup            
        //   111: iconst_0       
        //   112: getstatic       android/print/PageRange.ALL_PAGES:Landroid/print/PageRange;
        //   115: aastore        
        //   116: invokevirtual   android/print/PrintDocumentAdapter$WriteResultCallback.onWriteFinished:([Landroid/print/PageRange;)V
        //   119: aload_1        
        //   120: ifnull          127
        //   123: aload_1        
        //   124: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   127: aload_2        
        //   128: ifnull          135
        //   131: aload_2        
        //   132: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   135: return         
        //   136: astore_3       
        //   137: ldc             "PrintHelperKitkat"
        //   139: ldc             "Error writing printed content"
        //   141: aload_3        
        //   142: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   145: pop            
        //   146: aload           4
        //   148: aconst_null    
        //   149: invokevirtual   android/print/PrintDocumentAdapter$WriteResultCallback.onWriteFailed:(Ljava/lang/CharSequence;)V
        //   152: goto            119
        //   155: astore_3       
        //   156: aload_1        
        //   157: ifnull          164
        //   160: aload_1        
        //   161: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   164: aload_2        
        //   165: ifnull          172
        //   168: aload_2        
        //   169: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   172: aload_3        
        //   173: athrow         
        //   174: astore_1       
        //   175: return         
        //   176: astore_1       
        //   177: goto            172
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  19     89     155    174    Any
        //  89     119    136    155    Ljava/io/IOException;
        //  89     119    155    174    Any
        //  131    135    174    176    Ljava/io/IOException;
        //  137    152    155    174    Any
        //  168    172    176    180    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 92, Size: 92
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
