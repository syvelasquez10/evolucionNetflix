// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import android.graphics.drawable.Drawable;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.util.UriUtil;
import android.graphics.Bitmap$Config;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.logging.perf.InteractiveTimer$InteractiveListener;
import android.os.Looper;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.perf.InteractiveTimer$ATTITimer;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.netflix.mediaclient.Log;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;

class ImageLoader$ValidatingListener implements ImageLoader$ImageListener
{
    protected final String imgUrl;
    protected final ImageLoader$StaticImgConfig staticImgConfig;
    final /* synthetic */ ImageLoader this$0;
    protected final AdvancedImageView view;
    
    public ImageLoader$ValidatingListener(final ImageLoader this$0, final AdvancedImageView view, final String imgUrl, final ImageLoader$StaticImgConfig staticImgConfig) {
        this.this$0 = this$0;
        this.view = view;
        this.imgUrl = imgUrl;
        this.staticImgConfig = staticImgConfig;
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
    
    public AdvancedImageView getImageView() {
        return this.view;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (this.responseIsOutdated()) {
            return;
        }
        Log.w("ImageLoader", "Error loading bitmap for url: " + this.imgUrl);
        this.this$0.setDrawableResource(this.view, this.staticImgConfig.getOnFailResId());
    }
    
    @Override
    public void onResponse(final ImageLoader$ImageContainer imageLoader$ImageContainer, final boolean b) {
        if (this.responseIsOutdated()) {
            return;
        }
        final Bitmap bitmap = imageLoader$ImageContainer.getBitmap();
        if (bitmap != null && b) {
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
