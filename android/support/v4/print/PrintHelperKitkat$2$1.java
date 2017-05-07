// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

import android.print.PrintDocumentAdapter;
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
import android.os.CancellationSignal$OnCancelListener;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo$Builder;
import java.io.FileNotFoundException;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter$LayoutResultCallback;
import android.os.CancellationSignal;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;

class PrintHelperKitkat$2$1 extends AsyncTask<Uri, Boolean, Bitmap>
{
    final /* synthetic */ PrintHelperKitkat$2 this$1;
    final /* synthetic */ CancellationSignal val$cancellationSignal;
    final /* synthetic */ PrintDocumentAdapter$LayoutResultCallback val$layoutResultCallback;
    final /* synthetic */ PrintAttributes val$newPrintAttributes;
    final /* synthetic */ PrintAttributes val$oldPrintAttributes;
    
    PrintHelperKitkat$2$1(final PrintHelperKitkat$2 this$1, final CancellationSignal val$cancellationSignal, final PrintAttributes val$newPrintAttributes, final PrintAttributes val$oldPrintAttributes, final PrintDocumentAdapter$LayoutResultCallback val$layoutResultCallback) {
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
    }
    
    protected void onPostExecute(final Bitmap mBitmap) {
        boolean b = true;
        super.onPostExecute((Object)mBitmap);
        this.this$1.mBitmap = mBitmap;
        if (mBitmap != null) {
            final PrintDocumentInfo build = new PrintDocumentInfo$Builder(this.this$1.val$jobName).setContentType(1).setPageCount(1).build();
            if (this.val$newPrintAttributes.equals((Object)this.val$oldPrintAttributes)) {
                b = false;
            }
            this.val$layoutResultCallback.onLayoutFinished(build, b);
            return;
        }
        this.val$layoutResultCallback.onLayoutFailed((CharSequence)null);
    }
    
    protected void onPreExecute() {
        this.val$cancellationSignal.setOnCancelListener((CancellationSignal$OnCancelListener)new PrintHelperKitkat$2$1$1(this));
    }
}
