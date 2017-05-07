// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.Response$Listener;
import android.graphics.Bitmap$Config;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.os.Looper;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.android.volley.VolleyError;
import com.android.volley.Response$ErrorListener;

class ImageLoader$3 implements Response$ErrorListener
{
    final /* synthetic */ ImageLoader this$0;
    final /* synthetic */ String val$cacheKey;
    
    ImageLoader$3(final ImageLoader this$0, final String val$cacheKey) {
        this.this$0 = this$0;
        this.val$cacheKey = val$cacheKey;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        this.this$0.onGetImageError(this.val$cacheKey, volleyError);
    }
}
