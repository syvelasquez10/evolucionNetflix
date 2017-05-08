// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

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
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.BitmapFactory$Options;
import android.content.Context;
import android.annotation.TargetApi;
import android.print.PrintDocumentAdapter$WriteResultCallback;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo$Builder;
import android.os.Bundle;
import android.print.PrintDocumentAdapter$LayoutResultCallback;
import android.os.CancellationSignal;
import android.net.Uri;
import android.os.AsyncTask;
import android.graphics.Bitmap;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;

class PrintHelperKitkat$3 extends PrintDocumentAdapter
{
    private PrintAttributes mAttributes;
    Bitmap mBitmap;
    AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
    final /* synthetic */ PrintHelperKitkat this$0;
    final /* synthetic */ PrintHelperKitkat$OnPrintFinishCallback val$callback;
    final /* synthetic */ int val$fittingMode;
    final /* synthetic */ Uri val$imageFile;
    final /* synthetic */ String val$jobName;
    
    PrintHelperKitkat$3(final PrintHelperKitkat this$0, final String val$jobName, final Uri val$imageFile, final PrintHelperKitkat$OnPrintFinishCallback val$callback, final int val$fittingMode) {
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
        if (this.mLoadBitmap != null) {
            this.mLoadBitmap.cancel(true);
        }
        if (this.val$callback != null) {
            this.val$callback.onFinish();
        }
        if (this.mBitmap != null) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
    }
    
    public void onLayout(final PrintAttributes printAttributes, final PrintAttributes mAttributes, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$LayoutResultCallback printDocumentAdapter$LayoutResultCallback, final Bundle bundle) {
        boolean b = true;
        synchronized (this) {
            this.mAttributes = mAttributes;
            // monitorexit(this)
            if (cancellationSignal.isCanceled()) {
                printDocumentAdapter$LayoutResultCallback.onLayoutCancelled();
                return;
            }
        }
        final PrintAttributes printAttributes2;
        if (this.mBitmap != null) {
            final PrintDocumentInfo build = new PrintDocumentInfo$Builder(this.val$jobName).setContentType(1).setPageCount(1).build();
            if (mAttributes.equals((Object)printAttributes2)) {
                b = false;
            }
            printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, b);
            return;
        }
        this.mLoadBitmap = (AsyncTask<Uri, Boolean, Bitmap>)new PrintHelperKitkat$3$1(this, cancellationSignal, mAttributes, printAttributes2, printDocumentAdapter$LayoutResultCallback).execute((Object[])new Uri[0]);
    }
    
    public void onWrite(final PageRange[] array, final ParcelFileDescriptor parcelFileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$WriteResultCallback printDocumentAdapter$WriteResultCallback) {
        this.this$0.writeBitmap(this.mAttributes, this.val$fittingMode, this.mBitmap, parcelFileDescriptor, cancellationSignal, printDocumentAdapter$WriteResultCallback);
    }
}
