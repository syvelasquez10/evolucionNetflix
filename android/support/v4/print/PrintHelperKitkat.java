// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

import android.os.CancellationSignal$OnCancelListener;
import android.os.AsyncTask;
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
import java.io.InputStream;
import java.io.IOException;
import android.util.Log;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import java.io.FileNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.BitmapFactory$Options;
import android.content.Context;

class PrintHelperKitkat
{
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelperKitkat";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode;
    final Context mContext;
    BitmapFactory$Options mDecodeOptions;
    private final Object mLock;
    int mOrientation;
    int mScaleMode;
    
    PrintHelperKitkat(final Context mContext) {
        this.mDecodeOptions = null;
        this.mLock = new Object();
        this.mScaleMode = 2;
        this.mColorMode = 2;
        this.mOrientation = 1;
        this.mContext = mContext;
    }
    
    private Matrix getMatrix(final int n, final int n2, final RectF rectF, final int n3) {
        final Matrix matrix = new Matrix();
        final float n4 = rectF.width() / n;
        float n5;
        if (n3 == 2) {
            n5 = Math.max(n4, rectF.height() / n2);
        }
        else {
            n5 = Math.min(n4, rectF.height() / n2);
        }
        matrix.postScale(n5, n5);
        matrix.postTranslate((rectF.width() - n * n5) / 2.0f, (rectF.height() - n2 * n5) / 2.0f);
        return matrix;
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
    
    private Bitmap loadConstrainedBitmap(Uri uri, final int n) throws FileNotFoundException {
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
                final Object mLock = this.mLock;
                final BitmapFactory$Options mDecodeOptions;
                synchronized (mLock) {
                    this.mDecodeOptions = new BitmapFactory$Options();
                    this.mDecodeOptions.inMutable = true;
                    this.mDecodeOptions.inSampleSize = inSampleSize;
                    mDecodeOptions = this.mDecodeOptions;
                    // monitorexit(mLock)
                    final PrintHelperKitkat printHelperKitkat = this;
                    final Uri uri2 = uri;
                    final BitmapFactory$Options bitmapFactory$Options2 = mDecodeOptions;
                    final Bitmap bitmap = printHelperKitkat.loadBitmap(uri2, bitmapFactory$Options2);
                    final PrintHelperKitkat printHelperKitkat2 = this;
                    final Object o = printHelperKitkat2.mLock;
                    final Object o2;
                    uri = (Uri)(o2 = o);
                    synchronized (o2) {
                        final PrintHelperKitkat printHelperKitkat3 = this;
                        final BitmapFactory$Options bitmapFactory$Options3 = null;
                        printHelperKitkat3.mDecodeOptions = bitmapFactory$Options3;
                        return bitmap;
                    }
                }
                try {
                    final PrintHelperKitkat printHelperKitkat = this;
                    final Uri uri2 = uri;
                    final BitmapFactory$Options bitmapFactory$Options2 = mDecodeOptions;
                    final Bitmap bitmap = printHelperKitkat.loadBitmap(uri2, bitmapFactory$Options2);
                    final PrintHelperKitkat printHelperKitkat2 = this;
                    final Object o = printHelperKitkat2.mLock;
                    final Object o2;
                    uri = (Uri)(o2 = o);
                    // monitorenter(o2)
                    final PrintHelperKitkat printHelperKitkat3 = this;
                    final BitmapFactory$Options bitmapFactory$Options3 = null;
                    printHelperKitkat3.mDecodeOptions = bitmapFactory$Options3;
                    return bitmap;
                }
                finally {
                    synchronized (this.mLock) {
                        this.mDecodeOptions = null;
                    }
                    // monitorexit(this.mLock)
                }
            }
        }
        return null;
    }
    
    public int getColorMode() {
        return this.mColorMode;
    }
    
    public int getOrientation() {
        return this.mOrientation;
    }
    
    public int getScaleMode() {
        return this.mScaleMode;
    }
    
    public void printBitmap(final String s, final Bitmap bitmap, final OnPrintFinishCallback onPrintFinishCallback) {
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
            
            public void onFinish() {
                if (onPrintFinishCallback != null) {
                    onPrintFinishCallback.onFinish();
                }
            }
            
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
                //    39: astore          5
                //    41: aload_0        
                //    42: getfield        android/support/v4/print/PrintHelperKitkat$1.this$0:Landroid/support/v4/print/PrintHelperKitkat;
                //    45: aload_0        
                //    46: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
                //    49: invokevirtual   android/graphics/Bitmap.getWidth:()I
                //    52: aload_0        
                //    53: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
                //    56: invokevirtual   android/graphics/Bitmap.getHeight:()I
                //    59: aload           5
                //    61: aload_0        
                //    62: getfield        android/support/v4/print/PrintHelperKitkat$1.val$fittingMode:I
                //    65: invokestatic    android/support/v4/print/PrintHelperKitkat.access$000:(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
                //    68: astore          5
                //    70: aload_3        
                //    71: invokevirtual   android/graphics/pdf/PdfDocument$Page.getCanvas:()Landroid/graphics/Canvas;
                //    74: aload_0        
                //    75: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
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
    
    public void printBitmap(final String s, final Uri uri, final OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
        final PrintDocumentAdapter printDocumentAdapter = new PrintDocumentAdapter() {
            AsyncTask<Uri, Boolean, Bitmap> loadBitmap;
            private PrintAttributes mAttributes;
            Bitmap mBitmap = null;
            final /* synthetic */ int val$fittingMode = PrintHelperKitkat.this.mScaleMode;
            
            private void cancelLoad() {
                synchronized (PrintHelperKitkat.this.mLock) {
                    if (PrintHelperKitkat.this.mDecodeOptions != null) {
                        PrintHelperKitkat.this.mDecodeOptions.requestCancelDecode();
                        PrintHelperKitkat.this.mDecodeOptions = null;
                    }
                }
            }
            
            public void onFinish() {
                super.onFinish();
                this.cancelLoad();
                this.loadBitmap.cancel(true);
                if (onPrintFinishCallback != null) {
                    onPrintFinishCallback.onFinish();
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
                    final PrintDocumentInfo build = new PrintDocumentInfo$Builder(s).setContentType(1).setPageCount(1).build();
                    if (printAttributes2.equals((Object)printAttributes)) {
                        b = false;
                    }
                    printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, b);
                    return;
                }
                (this.loadBitmap = new AsyncTask<Uri, Boolean, Bitmap>() {
                    protected Bitmap doInBackground(final Uri... array) {
                        try {
                            return PrintHelperKitkat.this.loadConstrainedBitmap(uri, 3500);
                        }
                        catch (FileNotFoundException ex) {
                            return null;
                        }
                    }
                    
                    protected void onCancelled(final Bitmap bitmap) {
                        printDocumentAdapter$LayoutResultCallback.onLayoutCancelled();
                    }
                    
                    protected void onPostExecute(final Bitmap mBitmap) {
                        boolean b = true;
                        super.onPostExecute((Object)mBitmap);
                        PrintDocumentAdapter.this.mBitmap = mBitmap;
                        if (mBitmap != null) {
                            final PrintDocumentInfo build = new PrintDocumentInfo$Builder(s).setContentType(1).setPageCount(1).build();
                            if (printAttributes2.equals((Object)printAttributes)) {
                                b = false;
                            }
                            printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, b);
                            return;
                        }
                        printDocumentAdapter$LayoutResultCallback.onLayoutFailed((CharSequence)null);
                    }
                    
                    protected void onPreExecute() {
                        cancellationSignal.setOnCancelListener((CancellationSignal$OnCancelListener)new CancellationSignal$OnCancelListener() {
                            public void onCancel() {
                                PrintDocumentAdapter.this.cancelLoad();
                                AsyncTask.this.cancel(false);
                            }
                        });
                    }
                }).execute((Object[])new Uri[0]);
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
        };
        final PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
        final PrintAttributes$Builder printAttributes$Builder = new PrintAttributes$Builder();
        printAttributes$Builder.setColorMode(this.mColorMode);
        if (this.mOrientation == 1) {
            printAttributes$Builder.setMediaSize(PrintAttributes$MediaSize.UNKNOWN_LANDSCAPE);
        }
        else if (this.mOrientation == 2) {
            printAttributes$Builder.setMediaSize(PrintAttributes$MediaSize.UNKNOWN_PORTRAIT);
        }
        printManager.print(s, (PrintDocumentAdapter)printDocumentAdapter, printAttributes$Builder.build());
    }
    
    public void setColorMode(final int mColorMode) {
        this.mColorMode = mColorMode;
    }
    
    public void setOrientation(final int mOrientation) {
        this.mOrientation = mOrientation;
    }
    
    public void setScaleMode(final int mScaleMode) {
        this.mScaleMode = mScaleMode;
    }
    
    public interface OnPrintFinishCallback
    {
        void onFinish();
    }
}
