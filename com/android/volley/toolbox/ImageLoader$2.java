// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import android.graphics.Bitmap$Config;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import android.os.Looper;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import android.graphics.Bitmap;
import com.android.volley.Response$Listener;

class ImageLoader$2 implements Response$Listener<Bitmap>
{
    final /* synthetic */ ImageLoader this$0;
    final /* synthetic */ String val$cacheKey;
    
    ImageLoader$2(final ImageLoader this$0, final String val$cacheKey) {
        this.this$0 = this$0;
        this.val$cacheKey = val$cacheKey;
    }
    
    @Override
    public void onResponse(final Bitmap bitmap) {
        this.this$0.onGetImageSuccess(this.val$cacheKey, bitmap);
    }
}
