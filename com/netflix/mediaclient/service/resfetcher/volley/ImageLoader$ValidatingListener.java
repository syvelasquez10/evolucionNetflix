// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import android.text.TextUtils;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import android.graphics.drawable.Drawable;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.util.UriUtil;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.os.Looper;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.netflix.mediaclient.Log;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import android.graphics.Bitmap$Config;

class ImageLoader$ValidatingListener extends ImageLoader$ImageInteractionTrackingListener
{
    protected final Bitmap$Config bitmapConfig;
    protected final ImageLoader$StaticImgConfig staticImgConfig;
    final /* synthetic */ ImageLoader this$0;
    
    public ImageLoader$ValidatingListener(final ImageLoader this$0, final AdvancedImageView advancedImageView, final String s, final ImageLoader$StaticImgConfig staticImgConfig, final Bitmap$Config bitmapConfig) {
        this.this$0 = this$0;
        super(this$0, advancedImageView, s);
        this.staticImgConfig = staticImgConfig;
        this.bitmapConfig = bitmapConfig;
    }
    
    private boolean responseIsOutdated() {
        final AdvancedImageView$ImageLoaderInfo imageLoaderInfo = this.view.getImageLoaderInfo();
        String imageUrl;
        if (imageLoaderInfo == null) {
            imageUrl = null;
        }
        else {
            imageUrl = imageLoaderInfo.imageUrl;
        }
        final boolean b = !StringUtils.safeEquals(imageUrl, this.imgUrl);
        if (b) {}
        return b;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        if (this.responseIsOutdated()) {
            return;
        }
        Log.w("ImageLoader", "Error loading bitmap for url: " + this.imgUrl);
        this.this$0.setDrawableResource(this.view, this.staticImgConfig.getOnFailResId());
    }
    
    @Override
    public void onResponse(final ImageLoader$ImageContainer imageLoader$ImageContainer, final ImageLoader$Type imageLoader$Type) {
        super.onResponse(imageLoader$ImageContainer, imageLoader$Type);
        if (this.responseIsOutdated()) {
            return;
        }
        final Bitmap bitmap = imageLoader$ImageContainer.getBitmap();
        if (bitmap == null) {
            this.updateView(this.view, bitmap);
            return;
        }
        if (this.view.getImageLoaderInfo() != null) {
            this.view.getImageLoaderInfo().setLoaded(true);
        }
        if (imageLoader$Type.isImmediate()) {
            this.this$0.setDrawableBitmap(this.view, bitmap);
            return;
        }
        this.updateView(this.view, bitmap);
    }
    
    protected void updateView(final ImageView imageView, final Bitmap bitmap) {
        if (bitmap == null) {
            this.this$0.setDrawableToNull(imageView);
            return;
        }
        this.this$0.setDrawableBitmap(imageView, bitmap);
    }
}
