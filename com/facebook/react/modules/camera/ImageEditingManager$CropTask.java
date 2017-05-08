// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import java.util.Collections;
import java.util.Map;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import android.text.TextUtils;
import android.graphics.Bitmap$CompressFormat;
import android.media.ExifInterface;
import com.facebook.common.logging.FLog;
import android.os.AsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Arrays;
import java.util.List;
import android.annotation.SuppressLint;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import java.io.File;
import java.net.URL;
import android.net.Uri;
import android.graphics.Matrix;
import com.facebook.infer.annotation.Assertions;
import java.io.InputStream;
import java.io.IOException;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import android.content.Context;
import com.facebook.react.bridge.GuardedAsyncTask;

class ImageEditingManager$CropTask extends GuardedAsyncTask<Void, Void>
{
    final Context mContext;
    final Callback mError;
    final int mHeight;
    final Callback mSuccess;
    int mTargetHeight;
    int mTargetWidth;
    final String mUri;
    final int mWidth;
    final int mX;
    final int mY;
    
    private ImageEditingManager$CropTask(final ReactContext mContext, final String mUri, final int mx, final int my, final int mWidth, final int mHeight, final Callback mSuccess, final Callback mError) {
        super(mContext);
        this.mTargetWidth = 0;
        this.mTargetHeight = 0;
        if (mx < 0 || my < 0 || mWidth <= 0 || mHeight <= 0) {
            throw new JSApplicationIllegalArgumentException(String.format("Invalid crop rectangle: [%d, %d, %d, %d]", mx, my, mWidth, mHeight));
        }
        this.mContext = (Context)mContext;
        this.mUri = mUri;
        this.mX = mx;
        this.mY = my;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mSuccess = mSuccess;
        this.mError = mError;
    }
    
    private Bitmap crop(final BitmapFactory$Options bitmapFactory$Options) {
        final InputStream openBitmapInputStream = this.openBitmapInputStream();
        try {
            if (BitmapFactory.decodeStream(openBitmapInputStream, (Rect)null, bitmapFactory$Options) == null) {
                throw new IOException("Cannot decode bitmap: " + this.mUri);
            }
        }
        finally {
            if (openBitmapInputStream != null) {
                openBitmapInputStream.close();
            }
        }
        final Bitmap bitmap2;
        final Bitmap bitmap = Bitmap.createBitmap(bitmap2, this.mX, this.mY, this.mWidth, this.mHeight);
        if (openBitmapInputStream != null) {
            openBitmapInputStream.close();
        }
        return bitmap;
    }
    
    private Bitmap cropAndResize(int n, int n2, final BitmapFactory$Options bitmapFactory$Options) {
        Object decodeStream;
        InputStream inputStream;
        float n3;
        float n4;
        float n5;
        float n6;
        float n8;
        while (true) {
            Assertions.assertNotNull(bitmapFactory$Options);
            decodeStream = new BitmapFactory$Options();
            ((BitmapFactory$Options)decodeStream).inJustDecodeBounds = true;
            inputStream = this.openBitmapInputStream();
            while (true) {
                try {
                    BitmapFactory.decodeStream(inputStream, (Rect)null, (BitmapFactory$Options)decodeStream);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    n3 = this.mWidth / this.mHeight;
                    n4 = n / n2;
                    if (n3 > n4) {
                        n5 = this.mHeight * n4;
                        n6 = this.mHeight;
                        n3 = this.mX;
                        final float n7 = (this.mWidth - n5) / 2.0f;
                        n4 = this.mY;
                        n8 = n2 / this.mHeight;
                        final float n9 = n3 + n7;
                        n3 = n4;
                        n4 = n9;
                        bitmapFactory$Options.inSampleSize = getDecodeSampleSize(this.mWidth, this.mHeight, n, n2);
                        ((BitmapFactory$Options)decodeStream).inJustDecodeBounds = false;
                        inputStream = this.openBitmapInputStream();
                        try {
                            decodeStream = BitmapFactory.decodeStream(inputStream, (Rect)null, bitmapFactory$Options);
                            if (decodeStream == null) {
                                throw new IOException("Cannot decode bitmap: " + this.mUri);
                            }
                            break;
                        }
                        finally {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }
                    }
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                n5 = this.mWidth;
                n6 = this.mWidth / n4;
                n4 = this.mX;
                final float n10 = this.mY;
                final float n11 = (this.mHeight - n6) / 2.0f;
                n8 = n / this.mWidth;
                n3 = n11 + n10;
                continue;
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        n = (int)Math.floor(n4 / bitmapFactory$Options.inSampleSize);
        n2 = (int)Math.floor(n3 / bitmapFactory$Options.inSampleSize);
        final int n12 = (int)Math.floor(n5 / bitmapFactory$Options.inSampleSize);
        final int n13 = (int)Math.floor(n6 / bitmapFactory$Options.inSampleSize);
        final float n14 = bitmapFactory$Options.inSampleSize * n8;
        final Matrix matrix = new Matrix();
        matrix.setScale(n14, n14);
        return Bitmap.createBitmap((Bitmap)decodeStream, n, n2, n12, n13, matrix, true);
    }
    
    private InputStream openBitmapInputStream() {
        InputStream inputStream;
        if (isLocalUri(this.mUri)) {
            inputStream = this.mContext.getContentResolver().openInputStream(Uri.parse(this.mUri));
        }
        else {
            inputStream = new URL(this.mUri).openConnection().getInputStream();
        }
        if (inputStream == null) {
            throw new IOException("Cannot open bitmap: " + this.mUri);
        }
        return inputStream;
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... array) {
        Bitmap bitmap = null;
        String outMimeType = null;
    Label_0042_Outer:
        while (true) {
            while (true) {
                BitmapFactory$Options bitmapFactory$Options = null;
            Label_0095:
                while (true) {
                    try {
                        bitmapFactory$Options = new BitmapFactory$Options();
                        if (this.mTargetWidth > 0 && this.mTargetHeight > 0) {
                            final int n = 1;
                            if (n == 0) {
                                break Label_0095;
                            }
                            bitmap = this.cropAndResize(this.mTargetWidth, this.mTargetHeight, bitmapFactory$Options);
                            outMimeType = bitmapFactory$Options.outMimeType;
                            if (outMimeType == null || outMimeType.isEmpty()) {
                                throw new IOException("Could not determine MIME type");
                            }
                            break;
                        }
                    }
                    catch (Exception ex) {
                        this.mError.invoke(ex.getMessage());
                        return;
                    }
                    final int n = 0;
                    continue Label_0042_Outer;
                }
                bitmap = this.crop(bitmapFactory$Options);
                continue;
            }
        }
        final File access$300 = createTempFile(this.mContext, outMimeType);
        writeCompressedBitmapToFile(bitmap, outMimeType, access$300);
        if (outMimeType.equals("image/jpeg")) {
            copyExif(this.mContext, Uri.parse(this.mUri), access$300);
        }
        this.mSuccess.invoke(Uri.fromFile(access$300).toString());
    }
    
    public void setTargetSize(final int mTargetWidth, final int mTargetHeight) {
        if (mTargetWidth <= 0 || mTargetHeight <= 0) {
            throw new JSApplicationIllegalArgumentException(String.format("Invalid target size: [%d, %d]", mTargetWidth, mTargetHeight));
        }
        this.mTargetWidth = mTargetWidth;
        this.mTargetHeight = mTargetHeight;
    }
}
