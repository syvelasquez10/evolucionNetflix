// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import android.text.TextUtils;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;
import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UriUtil;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.android.volley.VolleyError;
import android.os.Looper;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$TTRTracker;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.graphics.Bitmap$Config;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

class ImageLoader$ValidatingListenerWithAnimation extends ImageLoader$ValidatingListener
{
    final /* synthetic */ ImageLoader this$0;
    
    public ImageLoader$ValidatingListenerWithAnimation(final ImageLoader this$0, final AdvancedImageView advancedImageView, final String s, final ImageLoader$StaticImgConfig imageLoader$StaticImgConfig, final Bitmap$Config bitmap$Config) {
        this.this$0 = this$0;
        super(this$0, advancedImageView, s, imageLoader$StaticImgConfig, bitmap$Config);
    }
    
    @Override
    protected void updateView(final ImageView imageView, final Bitmap bitmap) {
        if (bitmap == null) {
            this.this$0.setDrawableToNull(imageView);
            return;
        }
        AnimationUtils.setImageBitmapWithPropertyFade(imageView, bitmap);
    }
}
