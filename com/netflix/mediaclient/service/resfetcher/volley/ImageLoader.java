// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
import java.util.LinkedList;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.StringUtils;
import android.graphics.Bitmap$Config;
import com.android.volley.Response;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.UriUtil;
import com.android.volley.Request;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import java.util.Iterator;
import com.android.volley.VolleyError;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.os.Looper;
import com.netflix.mediaclient.service.ServiceAgent;
import com.android.volley.RequestQueue;
import android.os.Handler;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;

public class ImageLoader implements com.netflix.mediaclient.util.gfx.ImageLoader
{
    private static final boolean LOG_VERBOSE = false;
    private static final String TAG = "ImageLoader";
    private ApplicationPerformanceMetricsLogging mApmLogger;
    private final int mBatchResponseDelayMs;
    private final HashMap<String, BatchedImageRequest> mBatchedResponses;
    private final ImageCache mCache;
    private final Handler mHandler;
    private final HashMap<String, BatchedImageRequest> mInFlightRequests;
    private long mMinimumCacheTtl;
    private final RequestQueue mRequestQueue;
    private int mRequestSocketTimeout;
    private final Object mRequestTag;
    private Runnable mRunnable;
    
    public ImageLoader(final RequestQueue requestQueue, final ImageCache imageCache, final int n, final long mMinimumCacheTtl, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface) {
        this(requestQueue, imageCache, n, applicationPerformanceMetricsLogging, configurationAgentInterface);
        this.mMinimumCacheTtl = mMinimumCacheTtl;
    }
    
    private ImageLoader(final RequestQueue requestQueue, final ImageCache imageCache, final int mRequestSocketTimeout, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface) {
        this(requestQueue, imageCache, applicationPerformanceMetricsLogging, configurationAgentInterface);
        this.mRequestSocketTimeout = mRequestSocketTimeout;
    }
    
    private ImageLoader(final RequestQueue mRequestQueue, final ImageCache mCache, final ApplicationPerformanceMetricsLogging mApmLogger, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface) {
        this.mRequestTag = new Object();
        this.mRequestSocketTimeout = -1;
        this.mBatchResponseDelayMs = 100;
        this.mMinimumCacheTtl = -1L;
        this.mInFlightRequests = new HashMap<String, BatchedImageRequest>();
        this.mBatchedResponses = new HashMap<String, BatchedImageRequest>();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mRequestQueue = mRequestQueue;
        this.mCache = mCache;
        this.mApmLogger = mApmLogger;
    }
    
    private void batchResponse(final String s, final BatchedImageRequest batchedImageRequest) {
        this.mBatchedResponses.put(s, batchedImageRequest);
        if (this.mRunnable == null) {
            this.mRunnable = new Runnable() {
                @Override
                public void run() {
                    for (final BatchedImageRequest batchedImageRequest : ImageLoader.this.mBatchedResponses.values()) {
                        for (final ImageContainer imageContainer : batchedImageRequest.mContainers) {
                            if (imageContainer.mListener != null) {
                                if (batchedImageRequest.getError() == null) {
                                    imageContainer.mBitmap = batchedImageRequest.mResponseBitmap;
                                    imageContainer.mListener.onResponse(imageContainer, false);
                                }
                                else {
                                    ((Response.ErrorListener)imageContainer.mListener).onErrorResponse(batchedImageRequest.getError());
                                }
                            }
                        }
                    }
                    ImageLoader.this.mBatchedResponses.clear();
                    ImageLoader.this.mRunnable = null;
                }
            };
            this.mHandler.postDelayed(this.mRunnable, 100L);
        }
    }
    
    private ImageContainer get(final String s, final IClientLogging.AssetType assetType, final ImageListener imageListener, final int n, final int n2, final Request.Priority priority) {
        this.throwIfNotOnMainThread();
        if (!UriUtil.isValidUri(s)) {
            final String string = "Request URL is NOT valid, unable to load " + s;
            Log.v("ImageLoader", string);
            final ImageContainer imageContainer = new ImageContainer(null, s, "ERROR", imageListener);
            if (imageListener != null) {
                ((Response.ErrorListener)imageListener).onErrorResponse(new VolleyError(string));
                return imageContainer;
            }
            Log.e("ImageLoader", "Unable to report an error, missing listener");
            return imageContainer;
        }
        else {
            final String cacheKey = getCacheKey(s);
            final Bitmap bitmap = this.mCache.getBitmap(cacheKey);
            if (bitmap != null) {
                final ImageContainer imageContainer2 = new ImageContainer(bitmap, s, null, null);
                imageListener.onResponse(imageContainer2, true);
                LogUtils.reportAssetRequest(s, assetType, this.mApmLogger);
                LogUtils.reportAssetRequestResult(s, 0, this.mApmLogger);
                return imageContainer2;
            }
            final ImageContainer imageContainer3 = new ImageContainer(null, s, cacheKey, imageListener);
            imageListener.onResponse(imageContainer3, true);
            final BatchedImageRequest batchedImageRequest = this.mInFlightRequests.get(cacheKey);
            if (batchedImageRequest != null) {
                batchedImageRequest.addContainer(imageContainer3);
                return imageContainer3;
            }
            final ImageRequest imageRequest = new ImageRequest(s, new Response.Listener<Bitmap>() {
                public void onResponse(final Bitmap bitmap) {
                    LogUtils.reportAssetRequestResult(s, 0, ImageLoader.this.mApmLogger);
                    ImageLoader.this.onGetImageSuccess(cacheKey, bitmap);
                }
            }, n, n2, Bitmap$Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    LogUtils.reportAssetRequestFailure(s, volleyError, ImageLoader.this.mApmLogger);
                    ImageLoader.this.onGetImageError(cacheKey, volleyError);
                }
            }, priority, this.mRequestSocketTimeout, this.mMinimumCacheTtl);
            imageRequest.setTag(this.mRequestTag);
            LogUtils.reportAssetRequest(s, null, this.mApmLogger);
            this.mRequestQueue.add(imageRequest);
            this.mInFlightRequests.put(cacheKey, new BatchedImageRequest(imageRequest, imageContainer3));
            return imageContainer3;
        }
    }
    
    static String getCacheKey(final String s) {
        return StringUtils.getPathFromUri(s);
    }
    
    private void onGetImageError(final String s, final VolleyError error) {
        this.throwIfNotOnMainThread();
        final BatchedImageRequest batchedImageRequest = this.mInFlightRequests.remove(s);
        batchedImageRequest.setError(error);
        if (batchedImageRequest != null) {
            this.batchResponse(s, batchedImageRequest);
        }
    }
    
    private void onGetImageSuccess(final String s, final Bitmap bitmap) {
        this.throwIfNotOnMainThread();
        this.mCache.putBitmap(s, bitmap);
        final BatchedImageRequest batchedImageRequest = this.mInFlightRequests.remove(s);
        if (batchedImageRequest != null) {
            batchedImageRequest.mResponseBitmap = bitmap;
            this.batchResponse(s, batchedImageRequest);
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
    
    private void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }
    
    private ImageListener wrapPrivateListener(final ImageLoaderListener imageLoaderListener) {
        return (ImageListener)new ImageListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                final ImageLoaderListener val$listener = imageLoaderListener;
                String message;
                if (volleyError == null) {
                    message = null;
                }
                else {
                    message = volleyError.getMessage();
                }
                val$listener.onErrorResponse(message);
            }
            
            @Override
            public void onResponse(final ImageContainer imageContainer, final boolean b) {
                if (imageContainer == null) {
                    imageLoaderListener.onResponse(null, null);
                    return;
                }
                imageLoaderListener.onResponse(imageContainer.getBitmap(), imageContainer.getRequestUrl());
            }
        };
    }
    
    @Override
    public void cancelAllRequests() {
        this.mRequestQueue.cancelAll(this.mRequestTag);
        this.mInFlightRequests.clear();
    }
    
    @Override
    public void getImg(final String s, final IClientLogging.AssetType assetType, final int n, final int n2, final ImageLoaderListener imageLoaderListener) {
        this.get(s, assetType, this.wrapPrivateListener(imageLoaderListener), n, n2, Request.Priority.LOW);
    }
    
    public boolean isCached(String cacheKey) {
        this.throwIfNotOnMainThread();
        cacheKey = getCacheKey(cacheKey);
        return this.mCache.getBitmap(cacheKey) != null;
    }
    
    @Override
    public void refreshImgIfNecessary(final AdvancedImageView advancedImageView, final IClientLogging.AssetType assetType) {
        Log.v("ImageLoader", "refreshImgIfNecessary: " + advancedImageView);
        if (advancedImageView == null) {
            Log.v("ImageLoader", "refreshImgIfNecessary: null imageView");
            return;
        }
        final String urlTag = advancedImageView.getUrlTag();
        if (StringUtils.isEmpty(urlTag)) {
            Log.v("ImageLoader", "refreshImgIfNecessary: empty url");
            return;
        }
        this.showImgInternal(advancedImageView, urlTag, assetType, false, false, 1);
    }
    
    @Override
    public void showImg(final AdvancedImageView advancedImageView, final String s, final IClientLogging.AssetType assetType, final String s2, final boolean b, final boolean b2) {
        this.showImg(advancedImageView, s, assetType, s2, b, b2, 0);
    }
    
    @Override
    public void showImg(final AdvancedImageView drawableToNull, final String urlTag, final IClientLogging.AssetType assetType, String urlTag2, final boolean b, final boolean b2, final int n) {
        drawableToNull.setContentDescription((CharSequence)urlTag2);
        urlTag2 = drawableToNull.getUrlTag();
        drawableToNull.setUrlTag(urlTag);
        if (urlTag == null) {
            this.setDrawableToNull(drawableToNull);
        }
        else if (!urlTag.equals(urlTag2)) {
            this.showImgInternal(drawableToNull, urlTag, assetType, b, b2, n);
        }
    }
    
    public void showImgInternal(final AdvancedImageView advancedImageView, final String s, final IClientLogging.AssetType assetType, final boolean b, final boolean b2, final int n) {
        ImageListener imageListener;
        if (b) {
            imageListener = new ValidatingListenerWithPlaceholder(advancedImageView, s);
        }
        else if (b2) {
            imageListener = new ValidatingListenerWithAnimation(advancedImageView, s);
        }
        else {
            imageListener = new ValidatingListener(advancedImageView, s);
        }
        Request.Priority priority;
        if (n > 0) {
            priority = Request.Priority.NORMAL;
        }
        else {
            priority = Request.Priority.LOW;
        }
        this.get(s, assetType, imageListener, 0, 0, priority);
    }
    
    private class BatchedImageRequest
    {
        private final LinkedList<ImageContainer> mContainers;
        private VolleyError mError;
        private final Request<?> mRequest;
        private Bitmap mResponseBitmap;
        
        public BatchedImageRequest(final Request<?> mRequest, final ImageContainer imageContainer) {
            this.mContainers = new LinkedList<ImageContainer>();
            this.mRequest = mRequest;
            this.mContainers.add(imageContainer);
        }
        
        public void addContainer(final ImageContainer imageContainer) {
            this.mContainers.add(imageContainer);
        }
        
        public VolleyError getError() {
            return this.mError;
        }
        
        public boolean removeContainerAndCancelIfNecessary(final ImageContainer imageContainer) {
            this.mContainers.remove(imageContainer);
            if (this.mContainers.size() == 0) {
                this.mRequest.cancel();
                return true;
            }
            return false;
        }
        
        public void setError(final VolleyError mError) {
            this.mError = mError;
        }
    }
    
    public interface ImageCache
    {
        Bitmap getBitmap(final String p0);
        
        void putBitmap(final String p0, final Bitmap p1);
    }
    
    public class ImageContainer
    {
        private Bitmap mBitmap;
        private final String mCacheKey;
        private final ImageListener mListener;
        private final String mRequestUrl;
        
        public ImageContainer(final Bitmap mBitmap, final String mRequestUrl, final String mCacheKey, final ImageListener mListener) {
            this.mBitmap = mBitmap;
            this.mRequestUrl = mRequestUrl;
            this.mCacheKey = mCacheKey;
            this.mListener = mListener;
        }
        
        public void cancelRequest() {
            if (this.mListener != null) {
                final BatchedImageRequest batchedImageRequest = ImageLoader.this.mInFlightRequests.get(this.mCacheKey);
                if (batchedImageRequest != null) {
                    if (batchedImageRequest.removeContainerAndCancelIfNecessary(this)) {
                        ImageLoader.this.mInFlightRequests.remove(this.mCacheKey);
                    }
                }
                else {
                    final BatchedImageRequest batchedImageRequest2 = ImageLoader.this.mBatchedResponses.get(this.mCacheKey);
                    if (batchedImageRequest2 != null) {
                        batchedImageRequest2.removeContainerAndCancelIfNecessary(this);
                        if (batchedImageRequest2.mContainers.size() == 0) {
                            ImageLoader.this.mBatchedResponses.remove(this.mCacheKey);
                        }
                    }
                }
            }
        }
        
        public Bitmap getBitmap() {
            return this.mBitmap;
        }
        
        public String getRequestUrl() {
            return this.mRequestUrl;
        }
    }
    
    public interface ImageListener extends ErrorListener
    {
        void onResponse(final ImageContainer p0, final boolean p1);
    }
    
    private class ValidatingListener implements ImageListener
    {
        protected final String imgUrl;
        protected final AdvancedImageView view;
        
        public ValidatingListener(final AdvancedImageView view, final String imgUrl) {
            this.view = view;
            this.imgUrl = imgUrl;
        }
        
        private boolean responseIsOutdated() {
            final boolean b = !StringUtils.safeEquals(this.view.getUrlTag(), this.imgUrl);
            if (b) {}
            return b;
        }
        
        @Override
        public void onErrorResponse(final VolleyError volleyError) {
            if (this.responseIsOutdated()) {
                return;
            }
            Log.w("ImageLoader", "Error loading bitmap for url: " + this.imgUrl);
            ImageLoader.this.setDrawableResource(this.view, 2130837516);
        }
        
        @Override
        public void onResponse(final ImageContainer imageContainer, final boolean b) {
            if (this.responseIsOutdated()) {
                return;
            }
            final Bitmap bitmap = imageContainer.getBitmap();
            if (bitmap != null && b) {
                ImageLoader.this.setDrawableBitmap(this.view, bitmap);
                return;
            }
            this.updateView(this.view, bitmap);
        }
        
        protected void updateView(final ImageView imageView, final Bitmap bitmap) {
            if (bitmap == null) {
                ImageLoader.this.setDrawableToNull(imageView);
                return;
            }
            ImageLoader.this.setDrawableBitmap(imageView, bitmap);
        }
    }
    
    private class ValidatingListenerWithAnimation extends ValidatingListener
    {
        public ValidatingListenerWithAnimation(final AdvancedImageView advancedImageView, final String s) {
            super(advancedImageView, s);
        }
        
        @Override
        protected void updateView(final ImageView imageView, final Bitmap bitmap) {
            if (bitmap == null) {
                ImageLoader.this.setDrawableToNull(imageView);
                return;
            }
            AnimationUtils.setImageBitmapWithPropertyFade(imageView, bitmap);
        }
    }
    
    private class ValidatingListenerWithPlaceholder extends ValidatingListener
    {
        public ValidatingListenerWithPlaceholder(final AdvancedImageView advancedImageView, final String s) {
            super(advancedImageView, s);
        }
        
        @Override
        protected void updateView(final ImageView imageView, final Bitmap bitmap) {
            if (bitmap == null) {
                ImageLoader.this.setDrawableResource(imageView, 2130837516);
                return;
            }
            AnimationUtils.setImageBitmapWithTransitionFade(imageView, bitmap);
        }
    }
}
