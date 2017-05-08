// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

import android.print.PrintDocumentAdapter;
import android.print.PrintAttributes$MediaSize;
import android.print.PrintManager;
import android.print.PrintAttributes$Builder;
import android.print.PrintAttributes$Margins;
import java.io.InputStream;
import java.io.IOException;
import android.util.Log;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.net.Uri;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.print.PrintDocumentAdapter$WriteResultCallback;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.graphics.Bitmap;
import android.print.PrintAttributes;
import android.graphics.BitmapFactory$Options;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(19)
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
    protected boolean mIsMinMarginsHandlingCorrect;
    private final Object mLock;
    int mOrientation;
    protected boolean mPrintActivityRespectsOrientation;
    int mScaleMode;
    
    PrintHelperKitkat(final Context mContext) {
        this.mDecodeOptions = null;
        this.mLock = new Object();
        this.mScaleMode = 2;
        this.mColorMode = 2;
        this.mPrintActivityRespectsOrientation = true;
        this.mIsMinMarginsHandlingCorrect = true;
        this.mContext = mContext;
    }
    
    private Bitmap convertBitmapForColorMode(final Bitmap bitmap, final int n) {
        if (n != 1) {
            return bitmap;
        }
        final Bitmap bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap2);
        final Paint paint = new Paint();
        final ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter((ColorFilter)new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.setBitmap((Bitmap)null);
        return bitmap2;
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
        matrix.postTranslate((rectF.width() - n * n5) / 2.0f, (rectF.height() - n5 * n2) / 2.0f);
        return matrix;
    }
    
    private static boolean isPortrait(final Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }
    
    private Bitmap loadBitmap(final Uri uri, BitmapFactory$Options decodeStream) {
        InputStream openInputStream = null;
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
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
    
    private Bitmap loadConstrainedBitmap(Uri uri, final int n) {
        int inSampleSize = 1;
        if (n <= 0 || uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        this.loadBitmap(uri, bitmapFactory$Options);
        final int outWidth = bitmapFactory$Options.outWidth;
        final int outHeight = bitmapFactory$Options.outHeight;
        if (outWidth > 0 && outHeight > 0) {
            for (int i = Math.max(outWidth, outHeight); i > n; i >>>= 1, inSampleSize <<= 1) {}
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
    
    private void writeBitmap(final PrintAttributes printAttributes, final int n, final Bitmap bitmap, final ParcelFileDescriptor parcelFileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$WriteResultCallback printDocumentAdapter$WriteResultCallback) {
        PrintAttributes build;
        if (this.mIsMinMarginsHandlingCorrect) {
            build = printAttributes;
        }
        else {
            build = this.copyAttributes(printAttributes).setMinMargins(new PrintAttributes$Margins(0, 0, 0, 0)).build();
        }
        new PrintHelperKitkat$2(this, cancellationSignal, build, bitmap, printAttributes, n, parcelFileDescriptor, printDocumentAdapter$WriteResultCallback).execute((Object[])new Void[0]);
    }
    
    protected PrintAttributes$Builder copyAttributes(final PrintAttributes printAttributes) {
        final PrintAttributes$Builder setMinMargins = new PrintAttributes$Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
        if (printAttributes.getColorMode() != 0) {
            setMinMargins.setColorMode(printAttributes.getColorMode());
        }
        return setMinMargins;
    }
    
    public int getColorMode() {
        return this.mColorMode;
    }
    
    public int getOrientation() {
        if (this.mOrientation == 0) {
            return 1;
        }
        return this.mOrientation;
    }
    
    public int getScaleMode() {
        return this.mScaleMode;
    }
    
    public void printBitmap(final String s, final Bitmap bitmap, final PrintHelperKitkat$OnPrintFinishCallback printHelperKitkat$OnPrintFinishCallback) {
        if (bitmap == null) {
            return;
        }
        final int mScaleMode = this.mScaleMode;
        final PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
        PrintAttributes$MediaSize mediaSize;
        if (isPortrait(bitmap)) {
            mediaSize = PrintAttributes$MediaSize.UNKNOWN_PORTRAIT;
        }
        else {
            mediaSize = PrintAttributes$MediaSize.UNKNOWN_LANDSCAPE;
        }
        printManager.print(s, (PrintDocumentAdapter)new PrintHelperKitkat$1(this, s, mScaleMode, bitmap, printHelperKitkat$OnPrintFinishCallback), new PrintAttributes$Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
    }
    
    public void printBitmap(final String s, final Uri uri, final PrintHelperKitkat$OnPrintFinishCallback printHelperKitkat$OnPrintFinishCallback) {
        final PrintHelperKitkat$3 printHelperKitkat$3 = new PrintHelperKitkat$3(this, s, uri, printHelperKitkat$OnPrintFinishCallback, this.mScaleMode);
        final PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
        final PrintAttributes$Builder printAttributes$Builder = new PrintAttributes$Builder();
        printAttributes$Builder.setColorMode(this.mColorMode);
        if (this.mOrientation == 1 || this.mOrientation == 0) {
            printAttributes$Builder.setMediaSize(PrintAttributes$MediaSize.UNKNOWN_LANDSCAPE);
        }
        else if (this.mOrientation == 2) {
            printAttributes$Builder.setMediaSize(PrintAttributes$MediaSize.UNKNOWN_PORTRAIT);
        }
        printManager.print(s, (PrintDocumentAdapter)printHelperKitkat$3, printAttributes$Builder.build());
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
}
