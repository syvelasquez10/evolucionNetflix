// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

import android.util.Log;
import android.print.PageRange;
import android.print.PrintDocumentAdapter$WriteResultCallback;
import android.os.ParcelFileDescriptor;
import android.os.CancellationSignal;
import android.graphics.Bitmap;
import android.print.PrintAttributes;
import android.os.AsyncTask;

class PrintHelperKitkat$2 extends AsyncTask<Void, Void, Throwable>
{
    final /* synthetic */ PrintHelperKitkat this$0;
    final /* synthetic */ PrintAttributes val$attributes;
    final /* synthetic */ Bitmap val$bitmap;
    final /* synthetic */ CancellationSignal val$cancellationSignal;
    final /* synthetic */ ParcelFileDescriptor val$fileDescriptor;
    final /* synthetic */ int val$fittingMode;
    final /* synthetic */ PrintAttributes val$pdfAttributes;
    final /* synthetic */ PrintDocumentAdapter$WriteResultCallback val$writeResultCallback;
    
    PrintHelperKitkat$2(final PrintHelperKitkat this$0, final CancellationSignal val$cancellationSignal, final PrintAttributes val$pdfAttributes, final Bitmap val$bitmap, final PrintAttributes val$attributes, final int val$fittingMode, final ParcelFileDescriptor val$fileDescriptor, final PrintDocumentAdapter$WriteResultCallback val$writeResultCallback) {
        this.this$0 = this$0;
        this.val$cancellationSignal = val$cancellationSignal;
        this.val$pdfAttributes = val$pdfAttributes;
        this.val$bitmap = val$bitmap;
        this.val$attributes = val$attributes;
        this.val$fittingMode = val$fittingMode;
        this.val$fileDescriptor = val$fileDescriptor;
        this.val$writeResultCallback = val$writeResultCallback;
    }
    
    protected Throwable doInBackground(final Void... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        android/support/v4/print/PrintHelperKitkat$2.val$cancellationSignal:Landroid/os/CancellationSignal;
        //     4: invokevirtual   android/os/CancellationSignal.isCanceled:()Z
        //     7: ifeq            12
        //    10: aconst_null    
        //    11: areturn        
        //    12: new             Landroid/print/pdf/PrintedPdfDocument;
        //    15: dup            
        //    16: aload_0        
        //    17: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //    20: getfield        android/support/v4/print/PrintHelperKitkat.mContext:Landroid/content/Context;
        //    23: aload_0        
        //    24: getfield        android/support/v4/print/PrintHelperKitkat$2.val$pdfAttributes:Landroid/print/PrintAttributes;
        //    27: invokespecial   android/print/pdf/PrintedPdfDocument.<init>:(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //    30: astore          4
        //    32: aload_0        
        //    33: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //    36: aload_0        
        //    37: getfield        android/support/v4/print/PrintHelperKitkat$2.val$bitmap:Landroid/graphics/Bitmap;
        //    40: aload_0        
        //    41: getfield        android/support/v4/print/PrintHelperKitkat$2.val$pdfAttributes:Landroid/print/PrintAttributes;
        //    44: invokevirtual   android/print/PrintAttributes.getColorMode:()I
        //    47: invokestatic    android/support/v4/print/PrintHelperKitkat.access$100:(Landroid/support/v4/print/PrintHelperKitkat;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
        //    50: astore_3       
        //    51: aload_0        
        //    52: getfield        android/support/v4/print/PrintHelperKitkat$2.val$cancellationSignal:Landroid/os/CancellationSignal;
        //    55: invokevirtual   android/os/CancellationSignal.isCanceled:()Z
        //    58: istore_2       
        //    59: iload_2        
        //    60: ifne            389
        //    63: aload           4
        //    65: iconst_1       
        //    66: invokevirtual   android/print/pdf/PrintedPdfDocument.startPage:(I)Landroid/graphics/pdf/PdfDocument$Page;
        //    69: astore          5
        //    71: aload_0        
        //    72: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //    75: getfield        android/support/v4/print/PrintHelperKitkat.mIsMinMarginsHandlingCorrect:Z
        //    78: ifeq            198
        //    81: new             Landroid/graphics/RectF;
        //    84: dup            
        //    85: aload           5
        //    87: invokevirtual   android/graphics/pdf/PdfDocument$Page.getInfo:()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //    90: invokevirtual   android/graphics/pdf/PdfDocument$PageInfo.getContentRect:()Landroid/graphics/Rect;
        //    93: invokespecial   android/graphics/RectF.<init>:(Landroid/graphics/Rect;)V
        //    96: astore_1       
        //    97: aload_0        
        //    98: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //   101: aload_3        
        //   102: invokevirtual   android/graphics/Bitmap.getWidth:()I
        //   105: aload_3        
        //   106: invokevirtual   android/graphics/Bitmap.getHeight:()I
        //   109: aload_1        
        //   110: aload_0        
        //   111: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fittingMode:I
        //   114: invokestatic    android/support/v4/print/PrintHelperKitkat.access$200:(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
        //   117: astore          6
        //   119: aload_0        
        //   120: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //   123: getfield        android/support/v4/print/PrintHelperKitkat.mIsMinMarginsHandlingCorrect:Z
        //   126: ifeq            295
        //   129: aload           5
        //   131: invokevirtual   android/graphics/pdf/PdfDocument$Page.getCanvas:()Landroid/graphics/Canvas;
        //   134: aload_3        
        //   135: aload           6
        //   137: aconst_null    
        //   138: invokevirtual   android/graphics/Canvas.drawBitmap:(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //   141: aload           4
        //   143: aload           5
        //   145: invokevirtual   android/print/pdf/PrintedPdfDocument.finishPage:(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   148: aload_0        
        //   149: getfield        android/support/v4/print/PrintHelperKitkat$2.val$cancellationSignal:Landroid/os/CancellationSignal;
        //   152: invokevirtual   android/os/CancellationSignal.isCanceled:()Z
        //   155: istore_2       
        //   156: iload_2        
        //   157: ifeq            322
        //   160: aload           4
        //   162: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   165: aload_0        
        //   166: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
        //   169: astore_1       
        //   170: aload_1        
        //   171: ifnull          181
        //   174: aload_0        
        //   175: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
        //   178: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   181: aload_3        
        //   182: aload_0        
        //   183: getfield        android/support/v4/print/PrintHelperKitkat$2.val$bitmap:Landroid/graphics/Bitmap;
        //   186: if_acmpeq       389
        //   189: aload_3        
        //   190: invokevirtual   android/graphics/Bitmap.recycle:()V
        //   193: aconst_null    
        //   194: areturn        
        //   195: astore_1       
        //   196: aload_1        
        //   197: areturn        
        //   198: new             Landroid/print/pdf/PrintedPdfDocument;
        //   201: dup            
        //   202: aload_0        
        //   203: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //   206: getfield        android/support/v4/print/PrintHelperKitkat.mContext:Landroid/content/Context;
        //   209: aload_0        
        //   210: getfield        android/support/v4/print/PrintHelperKitkat$2.val$attributes:Landroid/print/PrintAttributes;
        //   213: invokespecial   android/print/pdf/PrintedPdfDocument.<init>:(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //   216: astore          6
        //   218: aload           6
        //   220: iconst_1       
        //   221: invokevirtual   android/print/pdf/PrintedPdfDocument.startPage:(I)Landroid/graphics/pdf/PdfDocument$Page;
        //   224: astore          7
        //   226: new             Landroid/graphics/RectF;
        //   229: dup            
        //   230: aload           7
        //   232: invokevirtual   android/graphics/pdf/PdfDocument$Page.getInfo:()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //   235: invokevirtual   android/graphics/pdf/PdfDocument$PageInfo.getContentRect:()Landroid/graphics/Rect;
        //   238: invokespecial   android/graphics/RectF.<init>:(Landroid/graphics/Rect;)V
        //   241: astore_1       
        //   242: aload           6
        //   244: aload           7
        //   246: invokevirtual   android/print/pdf/PrintedPdfDocument.finishPage:(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   249: aload           6
        //   251: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   254: goto            97
        //   257: astore_1       
        //   258: aload           4
        //   260: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   263: aload_0        
        //   264: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
        //   267: astore          4
        //   269: aload           4
        //   271: ifnull          281
        //   274: aload_0        
        //   275: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
        //   278: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   281: aload_3        
        //   282: aload_0        
        //   283: getfield        android/support/v4/print/PrintHelperKitkat$2.val$bitmap:Landroid/graphics/Bitmap;
        //   286: if_acmpeq       293
        //   289: aload_3        
        //   290: invokevirtual   android/graphics/Bitmap.recycle:()V
        //   293: aload_1        
        //   294: athrow         
        //   295: aload           6
        //   297: aload_1        
        //   298: getfield        android/graphics/RectF.left:F
        //   301: aload_1        
        //   302: getfield        android/graphics/RectF.top:F
        //   305: invokevirtual   android/graphics/Matrix.postTranslate:(FF)Z
        //   308: pop            
        //   309: aload           5
        //   311: invokevirtual   android/graphics/pdf/PdfDocument$Page.getCanvas:()Landroid/graphics/Canvas;
        //   314: aload_1        
        //   315: invokevirtual   android/graphics/Canvas.clipRect:(Landroid/graphics/RectF;)Z
        //   318: pop            
        //   319: goto            129
        //   322: aload           4
        //   324: new             Ljava/io/FileOutputStream;
        //   327: dup            
        //   328: aload_0        
        //   329: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
        //   332: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //   335: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/FileDescriptor;)V
        //   338: invokevirtual   android/print/pdf/PrintedPdfDocument.writeTo:(Ljava/io/OutputStream;)V
        //   341: aload           4
        //   343: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   346: aload_0        
        //   347: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
        //   350: astore_1       
        //   351: aload_1        
        //   352: ifnull          362
        //   355: aload_0        
        //   356: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
        //   359: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   362: aload_3        
        //   363: aload_0        
        //   364: getfield        android/support/v4/print/PrintHelperKitkat$2.val$bitmap:Landroid/graphics/Bitmap;
        //   367: if_acmpeq       389
        //   370: aload_3        
        //   371: invokevirtual   android/graphics/Bitmap.recycle:()V
        //   374: aconst_null    
        //   375: areturn        
        //   376: astore          4
        //   378: goto            281
        //   381: astore_1       
        //   382: goto            362
        //   385: astore_1       
        //   386: goto            181
        //   389: aconst_null    
        //   390: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      10     195    198    Ljava/lang/Throwable;
        //  12     59     195    198    Ljava/lang/Throwable;
        //  63     97     257    295    Any
        //  97     129    257    295    Any
        //  129    156    257    295    Any
        //  160    170    195    198    Ljava/lang/Throwable;
        //  174    181    385    389    Ljava/io/IOException;
        //  174    181    195    198    Ljava/lang/Throwable;
        //  181    193    195    198    Ljava/lang/Throwable;
        //  198    254    257    295    Any
        //  258    269    195    198    Ljava/lang/Throwable;
        //  274    281    376    381    Ljava/io/IOException;
        //  274    281    195    198    Ljava/lang/Throwable;
        //  281    293    195    198    Ljava/lang/Throwable;
        //  293    295    195    198    Ljava/lang/Throwable;
        //  295    319    257    295    Any
        //  322    341    257    295    Any
        //  341    351    195    198    Ljava/lang/Throwable;
        //  355    362    381    385    Ljava/io/IOException;
        //  355    362    195    198    Ljava/lang/Throwable;
        //  362    374    195    198    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 188, Size: 188
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
    
    protected void onPostExecute(final Throwable t) {
        if (this.val$cancellationSignal.isCanceled()) {
            this.val$writeResultCallback.onWriteCancelled();
            return;
        }
        if (t == null) {
            this.val$writeResultCallback.onWriteFinished(new PageRange[] { PageRange.ALL_PAGES });
            return;
        }
        Log.e("PrintHelperKitkat", "Error writing printed content", t);
        this.val$writeResultCallback.onWriteFailed((CharSequence)null);
    }
}
