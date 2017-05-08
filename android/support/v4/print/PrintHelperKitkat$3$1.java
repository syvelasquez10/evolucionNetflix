// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

import android.print.PageRange;
import android.os.Bundle;
import android.print.PrintDocumentAdapter;
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
import android.graphics.RectF;
import android.print.PrintDocumentAdapter$WriteResultCallback;
import android.os.ParcelFileDescriptor;
import android.graphics.BitmapFactory$Options;
import android.content.Context;
import android.os.CancellationSignal$OnCancelListener;
import android.print.PrintDocumentInfo;
import android.print.PrintAttributes$MediaSize;
import android.print.PrintDocumentInfo$Builder;
import android.graphics.Matrix;
import java.io.FileNotFoundException;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter$LayoutResultCallback;
import android.os.CancellationSignal;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;

class PrintHelperKitkat$3$1 extends AsyncTask<Uri, Boolean, Bitmap>
{
    final /* synthetic */ PrintHelperKitkat$3 this$1;
    final /* synthetic */ CancellationSignal val$cancellationSignal;
    final /* synthetic */ PrintDocumentAdapter$LayoutResultCallback val$layoutResultCallback;
    final /* synthetic */ PrintAttributes val$newPrintAttributes;
    final /* synthetic */ PrintAttributes val$oldPrintAttributes;
    
    PrintHelperKitkat$3$1(final PrintHelperKitkat$3 this$1, final CancellationSignal val$cancellationSignal, final PrintAttributes val$newPrintAttributes, final PrintAttributes val$oldPrintAttributes, final PrintDocumentAdapter$LayoutResultCallback val$layoutResultCallback) {
        this.this$1 = this$1;
        this.val$cancellationSignal = val$cancellationSignal;
        this.val$newPrintAttributes = val$newPrintAttributes;
        this.val$oldPrintAttributes = val$oldPrintAttributes;
        this.val$layoutResultCallback = val$layoutResultCallback;
    }
    
    protected Bitmap doInBackground(final Uri... array) {
        try {
            return this.this$1.this$0.loadConstrainedBitmap(this.this$1.val$imageFile, 3500);
        }
        catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    protected void onCancelled(final Bitmap bitmap) {
        this.val$layoutResultCallback.onLayoutCancelled();
        this.this$1.mLoadBitmap = null;
    }
    
    protected void onPostExecute(final Bitmap bitmap) {
        boolean b = true;
        super.onPostExecute((Object)bitmap);
        Bitmap bitmap2 = bitmap;
        Label_0110: {
            if (bitmap == null) {
                break Label_0110;
            }
            if (this.this$1.this$0.mPrintActivityRespectsOrientation) {
                bitmap2 = bitmap;
                if (this.this$1.this$0.mOrientation != 0) {
                    break Label_0110;
                }
            }
        Label_0171_Outer:
            while (true) {
                while (true) {
                    Label_0190: {
                        while (true) {
                            synchronized (this) {
                                final PrintAttributes$MediaSize mediaSize = this.this$1.mAttributes.getMediaSize();
                                // monitorexit(this)
                                bitmap2 = bitmap;
                                if (mediaSize != null) {
                                    bitmap2 = bitmap;
                                    if (mediaSize.isPortrait() != isPortrait(bitmap)) {
                                        final Matrix matrix = new Matrix();
                                        matrix.postRotate(90.0f);
                                        bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                                    }
                                }
                                if ((this.this$1.mBitmap = bitmap2) == null) {
                                    break Label_0190;
                                }
                                final PrintDocumentInfo build = new PrintDocumentInfo$Builder(this.this$1.val$jobName).setContentType(1).setPageCount(1).build();
                                if (!this.val$newPrintAttributes.equals((Object)this.val$oldPrintAttributes)) {
                                    this.val$layoutResultCallback.onLayoutFinished(build, b);
                                    this.this$1.mLoadBitmap = null;
                                    return;
                                }
                            }
                            b = false;
                            continue Label_0171_Outer;
                        }
                    }
                    this.val$layoutResultCallback.onLayoutFailed((CharSequence)null);
                    continue;
                }
            }
        }
    }
    
    protected void onPreExecute() {
        this.val$cancellationSignal.setOnCancelListener((CancellationSignal$OnCancelListener)new PrintHelperKitkat$3$1$1(this));
    }
}
