// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import java.util.LinkedList;
import android.text.TextUtils;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;
import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.StringUtils;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UriUtil;
import android.graphics.Bitmap$Config;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.android.volley.VolleyError;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.os.Looper;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;

public class ImageLoader implements com.netflix.mediaclient.util.gfx.ImageLoader
{
    private static final boolean LOG_VERBOSE = false;
    private static final String TAG = "ImageLoader";
    private final ApplicationPerformanceMetricsLogging mApmLogger;
    private final int mBatchResponseDelayMs;
    private final HashMap<String, ImageLoader$BatchedImageRequest> mBatchedResponses;
    private final ImageLoader$ImageCache mCache;
    private final Handler mHandler;
    private final HashMap<String, ImageLoader$BatchedImageRequest> mInFlightRequests;
    HashMap<String, InteractiveTracker> mInteractiveTrackers;
    private long mMinimumCacheTtl;
    private final RequestQueue mRequestQueue;
    private int mRequestSocketTimeout;
    private final Object mRequestTag;
    private Runnable mRunnable;
    
    public ImageLoader(final RequestQueue requestQueue, final ImageLoader$ImageCache imageLoader$ImageCache, final int n, final long mMinimumCacheTtl, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        this(requestQueue, imageLoader$ImageCache, n, applicationPerformanceMetricsLogging);
        this.mMinimumCacheTtl = mMinimumCacheTtl;
    }
    
    private ImageLoader(final RequestQueue requestQueue, final ImageLoader$ImageCache imageLoader$ImageCache, final int mRequestSocketTimeout, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        this(requestQueue, imageLoader$ImageCache, applicationPerformanceMetricsLogging);
        this.mRequestSocketTimeout = mRequestSocketTimeout;
    }
    
    private ImageLoader(final RequestQueue mRequestQueue, final ImageLoader$ImageCache mCache, final ApplicationPerformanceMetricsLogging mApmLogger) {
        this.mRequestTag = new Object();
        this.mRequestSocketTimeout = -1;
        this.mBatchResponseDelayMs = 100;
        this.mMinimumCacheTtl = -1L;
        this.mInFlightRequests = new HashMap<String, ImageLoader$BatchedImageRequest>();
        this.mBatchedResponses = new HashMap<String, ImageLoader$BatchedImageRequest>();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mInteractiveTrackers = new HashMap<String, InteractiveTracker>();
        this.mRequestQueue = mRequestQueue;
        this.mCache = mCache;
        this.mApmLogger = mApmLogger;
    }
    
    private void batchResponse(final String s, final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest) {
        this.mBatchedResponses.put(s, imageLoader$BatchedImageRequest);
        if (this.mRunnable == null) {
            this.mRunnable = new ImageLoader$4(this);
            this.mHandler.postDelayed(this.mRunnable, 100L);
        }
    }
    
    private ImageLoader$ImageContainer get(final String s, final IClientLogging$AssetType clientLogging$AssetType, final ImageLoader$ImageListener imageLoader$ImageListener, final int n, final int n2, final Request$Priority request$Priority, final Bitmap$Config bitmap$Config) {
        this.throwIfNotOnMainThread();
        if (!UriUtil.isValidUri(s) || this.mRequestQueue == null) {
            String string;
            if (this.mRequestQueue == null) {
                string = "Request queue is null - can't get bitmap";
            }
            else {
                string = "Request URL is NOT valid, unable to load " + s;
            }
            Log.v("ImageLoader", string);
            final ImageLoader$ImageContainer imageLoader$ImageContainer = new ImageLoader$ImageContainer(this, null, s, "ERROR", imageLoader$ImageListener);
            if (imageLoader$ImageListener != null) {
                imageLoader$ImageListener.onErrorResponse(new VolleyError(string));
                return imageLoader$ImageContainer;
            }
            Log.e("ImageLoader", "Unable to report an error, missing listener");
            return imageLoader$ImageContainer;
        }
        else {
            if (imageLoader$ImageListener instanceof ImageLoader$ImageInteractionTrackingListener) {
                ((ImageLoader$ImageInteractionTrackingListener)imageLoader$ImageListener).register(request$Priority);
            }
            final String startSession = PerformanceProfiler.getInstance().startSession(Sessions.IMAGE_FETCH);
            final String cacheKey = getCacheKey(s);
            final Bitmap bitmap = this.mCache.getBitmap(cacheKey);
            if (bitmap != null) {
                final ImageLoader$ImageContainer imageLoader$ImageContainer2 = new ImageLoader$ImageContainer(this, bitmap, s, null, null);
                imageLoader$ImageListener.onResponse(imageLoader$ImageContainer2, ImageLoader$Type.CACHE);
                ApmLogUtils.reportAssetRequest(s, clientLogging$AssetType, this.mApmLogger);
                ApmLogUtils.reportAssetRequestResult(s, StatusCode.OK, this.mApmLogger);
                return imageLoader$ImageContainer2;
            }
            final ImageLoader$ImageContainer imageLoader$ImageContainer3 = new ImageLoader$ImageContainer(this, null, s, cacheKey, imageLoader$ImageListener);
            imageLoader$ImageListener.onResponse(imageLoader$ImageContainer3, ImageLoader$Type.PLACEHOLDER);
            final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest = this.mInFlightRequests.get(cacheKey);
            if (imageLoader$BatchedImageRequest != null) {
                imageLoader$BatchedImageRequest.addContainer(imageLoader$ImageContainer3);
                return imageLoader$ImageContainer3;
            }
            final ImageRequest imageRequest = new ImageRequest(s, new ImageLoader$2(this, s, startSession, cacheKey), n, n2, bitmap$Config, new ImageLoader$3(this, s, startSession, cacheKey), request$Priority, this.mRequestSocketTimeout, this.mMinimumCacheTtl);
            imageRequest.setTag(this.mRequestTag);
            ApmLogUtils.reportAssetRequest(s, (IClientLogging$AssetType)null, this.mApmLogger);
            this.mRequestQueue.add(imageRequest);
            this.mInFlightRequests.put(cacheKey, new ImageLoader$BatchedImageRequest(imageRequest, imageLoader$ImageContainer3));
            return imageLoader$ImageContainer3;
        }
    }
    
    static String getCacheKey(final String s) {
        return StringUtils.getPathFromUri(s);
    }
    
    private void onGetImageError(final String s, final VolleyError error) {
        this.throwIfNotOnMainThread();
        final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest = this.mInFlightRequests.remove(s);
        imageLoader$BatchedImageRequest.setError(error);
        this.batchResponse(s, imageLoader$BatchedImageRequest);
    }
    
    private void onGetImageSuccess(final String s, final Bitmap bitmap) {
        this.throwIfNotOnMainThread();
        this.mCache.putBitmap(s, bitmap);
        final ImageLoader$BatchedImageRequest imageLoader$BatchedImageRequest = this.mInFlightRequests.remove(s);
        if (imageLoader$BatchedImageRequest != null) {
            imageLoader$BatchedImageRequest.mResponseBitmap = bitmap;
            this.batchResponse(s, imageLoader$BatchedImageRequest);
        }
    }
    
    private void setDrawableBitmap(final ImageView imageView, final Bitmap imageBitmap) {
        imageView.setImageBitmap(imageBitmap);
    }
    
    private void setDrawableResource(final ImageView imageView, final int imageResource) {
        imageView.setImageResource(imageResource);
    }
    
    private void setDrawableToNull(final ImageView imageView) {
        imageView.setImageDrawable((Drawable)null);
    }
    
    private void showImgInternal(final AdvancedImageView advancedImageView, final String s, final IClientLogging$AssetType clientLogging$AssetType, final ImageLoader$StaticImgConfig imageLoader$StaticImgConfig, final boolean b, final int n, final Bitmap$Config bitmap$Config) {
        ImageLoader$ValidatingListener imageLoader$ValidatingListener;
        if (imageLoader$StaticImgConfig != null && imageLoader$StaticImgConfig.shouldShowPlaceholder()) {
            imageLoader$ValidatingListener = new ImageLoader$ValidatingListenerWithPlaceholder(this, advancedImageView, s, imageLoader$StaticImgConfig, bitmap$Config);
        }
        else if (b) {
            imageLoader$ValidatingListener = new ImageLoader$ValidatingListenerWithAnimation(this, advancedImageView, s, imageLoader$StaticImgConfig, bitmap$Config);
        }
        else {
            imageLoader$ValidatingListener = new ImageLoader$ValidatingListener(this, advancedImageView, s, imageLoader$StaticImgConfig, bitmap$Config);
        }
        Request$Priority request$Priority;
        if (n > 0) {
            request$Priority = Request$Priority.NORMAL;
        }
        else {
            request$Priority = Request$Priority.LOW;
        }
        this.get(s, clientLogging$AssetType, imageLoader$ValidatingListener, 0, 0, request$Priority, bitmap$Config);
    }
    
    private void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }
    
    private ImageLoader$ImageListener wrapPrivateListener(final ImageLoader$ImageLoaderListener imageLoader$ImageLoaderListener) {
        return new ImageLoader$1(this, imageLoader$ImageLoaderListener);
    }
    
    public void cancelAllRequests() {
        Log.i("ImageLoader", "Cancelling all ImageLoader requests!");
        if (this.mRequestQueue != null) {
            this.mRequestQueue.cancelAll(this.mRequestTag);
        }
        if (this.mInFlightRequests != null) {
            this.mInFlightRequests.clear();
        }
    }
    
    public void clear(final AdvancedImageView advancedImageView) {
        advancedImageView.setContentDescription((CharSequence)null);
        advancedImageView.setImageLoaderInfo(null);
    }
    
    public void getImg(final String s, final IClientLogging$AssetType clientLogging$AssetType, final int n, final int n2, final ImageLoader$ImageLoaderListener imageLoader$ImageLoaderListener) {
        this.get(s, clientLogging$AssetType, this.wrapPrivateListener(imageLoader$ImageLoaderListener), n, n2, Request$Priority.LOW, Bitmap$Config.RGB_565);
    }
    
    public InteractiveTracker getInteractiveTracker(final String s) {
        return this.mInteractiveTrackers.get(s);
    }
    
    public boolean isCached(String cacheKey) {
        this.throwIfNotOnMainThread();
        cacheKey = getCacheKey(cacheKey);
        return this.mCache.getBitmap(cacheKey) != null;
    }
    
    public void refreshImgIfNecessary(final AdvancedImageView advancedImageView, final IClientLogging$AssetType clientLogging$AssetType) {
        Log.v("ImageLoader", "refreshImgIfNecessary: " + advancedImageView);
        if (advancedImageView == null) {
            Log.v("ImageLoader", "refreshImgIfNecessary: null imageView");
            return;
        }
        final AdvancedImageView$ImageLoaderInfo imageLoaderInfo = advancedImageView.getImageLoaderInfo();
        if (imageLoaderInfo == null) {
            Log.v("ImageLoader", "refreshImgIfNecessary: null image loader info object");
            return;
        }
        final String imageUrl = imageLoaderInfo.imageUrl;
        if (StringUtils.isEmpty(imageUrl)) {
            Log.v("ImageLoader", "refreshImgIfNecessary: empty url");
            return;
        }
        ImageLoader$StaticImgConfig imageLoader$StaticImgConfig;
        if ((imageLoader$StaticImgConfig = imageLoaderInfo.imgViewConfig) == null) {
            Log.w("ImageLoader", "Couldn't get StaticImgConfig from image view - falling back to default");
            imageLoader$StaticImgConfig = ImageLoader$StaticImgConfig.DARK;
        }
        this.showImgInternal(advancedImageView, imageUrl, clientLogging$AssetType, imageLoader$StaticImgConfig, false, 1, imageLoaderInfo.bitmapConfig);
    }
    
    public void setInteractiveTracker(final InteractiveTracker interactiveTracker) {
        Log.v("InteractiveTracker", "setInteractiveTracker -> " + interactiveTracker.getId());
        if (this.mInteractiveTrackers.containsKey(interactiveTracker.getId())) {
            this.mInteractiveTrackers.get(interactiveTracker.getId()).setListener(null);
        }
        this.mInteractiveTrackers.put(interactiveTracker.getId(), interactiveTracker);
    }
    
    public void showImg(final AdvancedImageView advancedImageView, final String s, final IClientLogging$AssetType clientLogging$AssetType, final String s2, final ImageLoader$StaticImgConfig imageLoader$StaticImgConfig, final boolean b) {
        this.showImg(advancedImageView, s, clientLogging$AssetType, s2, imageLoader$StaticImgConfig, b, 0, Bitmap$Config.RGB_565);
    }
    
    public void showImg(final AdvancedImageView advancedImageView, final String s, final IClientLogging$AssetType clientLogging$AssetType, final String s2, final ImageLoader$StaticImgConfig imageLoader$StaticImgConfig, final boolean b, final int n) {
        this.showImg(advancedImageView, s, clientLogging$AssetType, s2, imageLoader$StaticImgConfig, b, n, Bitmap$Config.RGB_565);
    }
    
    public void showImg(final AdvancedImageView drawableToNull, final String s, final IClientLogging$AssetType clientLogging$AssetType, String imageUrl, final ImageLoader$StaticImgConfig imageLoader$StaticImgConfig, final boolean b, final int n, final Bitmap$Config bitmap$Config) {
        if (imageUrl != null) {
            drawableToNull.setContentDescription((CharSequence)imageUrl);
        }
        final AdvancedImageView$ImageLoaderInfo imageLoaderInfo = drawableToNull.getImageLoaderInfo();
        if (imageLoaderInfo == null) {
            imageUrl = null;
        }
        else {
            imageUrl = imageLoaderInfo.imageUrl;
        }
        drawableToNull.setImageLoaderInfo(new AdvancedImageView$ImageLoaderInfo(s, imageLoader$StaticImgConfig, bitmap$Config));
        if (imageLoaderInfo != null && imageLoaderInfo.loaded && TextUtils.equals((CharSequence)imageLoaderInfo.imageUrl, (CharSequence)s)) {
            drawableToNull.getImageLoaderInfo().setLoaded(true);
        }
        if (s == null) {
            this.setDrawableToNull(drawableToNull);
        }
        else if (!s.equals(imageUrl)) {
            this.showImgInternal(drawableToNull, s, clientLogging$AssetType, imageLoader$StaticImgConfig, b, n, bitmap$Config);
        }
    }
}
