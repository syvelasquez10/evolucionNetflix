// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import android.graphics.Bitmap$Config;
import android.widget.ImageView;
import android.os.Looper;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.LinkedList;
import android.graphics.Bitmap;
import java.util.Iterator;

class ImageLoader$4 implements Runnable
{
    final /* synthetic */ ImageLoader this$0;
    
    ImageLoader$4(final ImageLoader this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        for (final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest : this.this$0.mBatchedResponses.values()) {
            for (final ImageLoader$ImageContainer imageLoader$ImageContainer : imageLoader$BatchedImageRequest.mContainers) {
                if (imageLoader$ImageContainer.mListener != null) {
                    if (imageLoader$BatchedImageRequest.getError() == null) {
                        imageLoader$ImageContainer.mBitmap = imageLoader$BatchedImageRequest.mResponseBitmap;
                        imageLoader$ImageContainer.mListener.onResponse(imageLoader$ImageContainer, false);
                    }
                    else {
                        imageLoader$ImageContainer.mListener.onErrorResponse(imageLoader$BatchedImageRequest.getError());
                    }
                }
            }
        }
        this.this$0.mBatchedResponses.clear();
        this.this$0.mRunnable = null;
    }
}
