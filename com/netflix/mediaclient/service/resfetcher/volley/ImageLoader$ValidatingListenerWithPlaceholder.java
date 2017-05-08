// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UriUtil;
import android.graphics.Bitmap$Config;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.service.logging.perf.InteractiveTimer$InteractiveListener;
import android.os.Looper;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.perf.InteractiveTimer$ATTITimer;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

class ImageLoader$ValidatingListenerWithPlaceholder extends ImageLoader$ValidatingListener
{
    final /* synthetic */ ImageLoader this$0;
    
    public ImageLoader$ValidatingListenerWithPlaceholder(final ImageLoader this$0, final AdvancedImageView advancedImageView, final String s, final ImageLoader$StaticImgConfig imageLoader$StaticImgConfig) {
        this.this$0 = this$0;
        super(this$0, advancedImageView, s, imageLoader$StaticImgConfig);
    }
    
    @Override
    protected void updateView(final ImageView imageView, final Bitmap bitmap) {
        if (bitmap == null) {
            this.this$0.setDrawableResource(imageView, this.staticImgConfig.getPlaceholderResId());
            return;
        }
        AnimationUtils.setImageBitmapWithPropertyFade(imageView, bitmap);
    }
}
