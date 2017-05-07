// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.VolleyLog;
import com.android.volley.Request$Priority;
import com.android.volley.VolleyError;
import com.android.volley.ParseError;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import com.android.volley.RetryPolicy;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import com.android.volley.Request;

public class ImageRequest extends Request<Bitmap>
{
    private static final float IMAGE_BACKOFF_MULT = 2.0f;
    private static final int IMAGE_MAX_RETRIES = 2;
    private static final int IMAGE_TIMEOUT_MS = 1000;
    private static final Object sDecodeLock;
    private final Bitmap$Config mDecodeConfig;
    private final Response$Listener<Bitmap> mListener;
    private final int mMaxHeight;
    private final int mMaxWidth;
    
    static {
        sDecodeLock = new Object();
    }
    
    public ImageRequest(final String s, final Response$Listener<Bitmap> mListener, final int mMaxWidth, final int mMaxHeight, final Bitmap$Config mDecodeConfig, final Response$ErrorListener response$ErrorListener) {
        super(0, s, response$ErrorListener);
        this.setRetryPolicy(new DefaultRetryPolicy(1000, 2, 2.0f));
        this.mListener = mListener;
        this.mDecodeConfig = mDecodeConfig;
        this.mMaxWidth = mMaxWidth;
        this.mMaxHeight = mMaxHeight;
    }
    
    private Response<Bitmap> doParse(final NetworkResponse networkResponse) {
        final byte[] data = networkResponse.data;
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        Bitmap bitmap;
        if (this.mMaxWidth == 0 && this.mMaxHeight == 0) {
            bitmapFactory$Options.inPreferredConfig = this.mDecodeConfig;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, bitmapFactory$Options);
        }
        else {
            bitmapFactory$Options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, bitmapFactory$Options);
            final int outWidth = bitmapFactory$Options.outWidth;
            final int outHeight = bitmapFactory$Options.outHeight;
            final int resizedDimension = getResizedDimension(this.mMaxWidth, this.mMaxHeight, outWidth, outHeight);
            final int resizedDimension2 = getResizedDimension(this.mMaxHeight, this.mMaxWidth, outHeight, outWidth);
            bitmapFactory$Options.inJustDecodeBounds = false;
            bitmapFactory$Options.inSampleSize = findBestSampleSize(outWidth, outHeight, resizedDimension, resizedDimension2);
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, bitmapFactory$Options);
            if (bitmap != null && (bitmap.getWidth() > resizedDimension || bitmap.getHeight() > resizedDimension2)) {
                final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, resizedDimension, resizedDimension2, true);
                bitmap.recycle();
                bitmap = scaledBitmap;
            }
        }
        if (bitmap == null) {
            return Response.error(new ParseError());
        }
        return Response.success(bitmap, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
    
    static int findBestSampleSize(final int n, final int n2, final int n3, final int n4) {
        double min;
        float n5;
        for (min = Math.min(n / n3, n2 / n4), n5 = 1.0f; n5 * 2.0f <= min; n5 *= 2.0f) {}
        return (int)n5;
    }
    
    private static int getResizedDimension(final int n, final int n2, final int n3, final int n4) {
        int n5;
        if (n == 0 && n2 == 0) {
            n5 = n3;
        }
        else {
            if (n == 0) {
                return n2 / n4 * n3;
            }
            n5 = n;
            if (n2 != 0) {
                final double n6 = n4 / n3;
                n5 = n;
                if (n * n6 > n2) {
                    return (int)(n2 / n6);
                }
            }
        }
        return n5;
    }
    
    @Override
    protected void deliverResponse(final Bitmap bitmap) {
        this.mListener.onResponse(bitmap);
    }
    
    @Override
    public Request$Priority getPriority() {
        return Request$Priority.LOW;
    }
    
    @Override
    protected Response<Bitmap> parseNetworkResponse(final NetworkResponse networkResponse) {
        synchronized (ImageRequest.sDecodeLock) {
            try {
                return this.doParse(networkResponse);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                VolleyLog.e("Caught OOM for %d byte image, url=%s", networkResponse.data.length, this.getUrl());
                return Response.error(new ParseError(outOfMemoryError));
            }
        }
    }
}
