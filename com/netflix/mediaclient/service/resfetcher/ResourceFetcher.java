// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

import com.netflix.mediaclient.service.resfetcher.volley.PrefetchResourceRequest;
import com.netflix.mediaclient.service.webclient.WebClient;
import com.android.volley.Request;
import com.netflix.mediaclient.service.resfetcher.volley.FileDownloadRequest;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.webclient.WebClientInitParameters;
import com.netflix.mediaclient.service.webclient.NetflixWebClientInitParameters;
import com.android.volley.Network;
import com.android.volley.Cache;
import com.android.volley.toolbox.BasicNetwork;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.util.gfx.BitmapLruCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.HttpStack;
import com.netflix.mediaclient.Log;
import com.android.volley.toolbox.DiskBasedCache;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader;
import java.io.File;
import com.netflix.mediaclient.service.ServiceAgent;

public class ResourceFetcher extends ServiceAgent
{
    private static final String DOWNLOADS_CACHE_DIR = "downloads";
    private static final String SELECTED_WEBCLIENT = "volley";
    private static final String TAG = "nf_service_resourcefetcher";
    private static final String VOLLEY_CACHE_DIR = "volley";
    private static final String VOLLEY_WEBCLIENT_NAME = "volley";
    private File mDownloadsDir;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;
    private final FalcorVolleyWebClient mWebClient;
    
    public ResourceFetcher() {
        this.mWebClient = createWebClient();
    }
    
    private DiskBasedCache createDiskCache() {
        final File file = new File(this.getContext().getCacheDir(), "volley");
        final int diskCacheSizeBytes = this.getConfigurationAgent().getDiskCacheSizeBytes();
        if (Log.isLoggable("nf_service_resourcefetcher", 4)) {
            Log.i("nf_service_resourcefetcher", String.format("Creating new Volley DiskBasedCache, location: %s,  max size: %d bytes", file.getAbsolutePath(), diskCacheSizeBytes));
        }
        return new DiskBasedCache(file, diskCacheSizeBytes);
    }
    
    private HttpStack createHttpStack() {
        Log.d("nf_service_resourcefetcher", "Using HttpURLConnection for Volley");
        return new HurlStack();
    }
    
    private ImageLoader createImageLoader() {
        Log.d("nf_service_resourcefetcher", "ResourceFetcher creating ImageLoader");
        final int resourceRequestTimeout = this.getConfigurationAgent().getResourceRequestTimeout();
        final long imageCacheMinimumTtl = this.getConfigurationAgent().getImageCacheMinimumTtl();
        if (Log.isLoggable("nf_service_resourcefetcher", 3)) {
            Log.d("nf_service_resourcefetcher", "Received request to create new ImageLoader with socketTimeout = " + resourceRequestTimeout + " and minimumTtl = " + imageCacheMinimumTtl + "ms");
        }
        return new ImageLoader(this.mRequestQueue, this.getImageCache(), resourceRequestTimeout, imageCacheMinimumTtl, this.getService().getClientLogging().getApplicationPerformanceMetricsLogging(), this.getConfigurationAgent());
    }
    
    private static FalcorVolleyWebClient createWebClient() {
        Log.i("nf_service_resourcefetcher", "WebClient of type volley");
        if ("volley".equals("volley")) {
            return new FalcorVolleyWebClient();
        }
        throw new IllegalStateException("Webclient not implemented");
    }
    
    private ImageLoader.ImageCache getImageCache() {
        synchronized (this) {
            final NetflixApplication application = this.getApplication();
            final BitmapLruCache imageCache = application.getImageCache();
            ImageLoader.ImageCache imageCache2;
            if (imageCache != null && imageCache instanceof ImageLoader.ImageCache) {
                imageCache2 = (ImageLoader.ImageCache)imageCache;
            }
            else {
                final int imageCacheSizeBytes = this.getConfigurationAgent().getImageCacheSizeBytes();
                Log.i("nf_service_resourcefetcher", "Creating new BitmapLruCache of size " + imageCacheSizeBytes + " bytes");
                imageCache2 = new VolleyImageCache(imageCacheSizeBytes);
                application.setImageCache((BitmapLruCache)imageCache2);
            }
            return imageCache2;
        }
    }
    
    private ResourceFetcherCallback getResourceFetcherCallback(final ResourceFetcherCallback resourceFetcherCallback) {
        if (resourceFetcherCallback == null) {
            Log.w("nf_service_resourcefetcher", "Resource fetcher callback is null!");
            return null;
        }
        return new ResourceFetcherCallbackWrapper(resourceFetcherCallback);
    }
    
    @Override
    public void destroy() {
        super.destroy();
        if (this.mRequestQueue != null) {
            Log.i("nf_service_resourcefetcher", "Stopping Volley RequestQueue");
            this.mRequestQueue.stop();
            this.mRequestQueue = null;
        }
    }
    
    @Override
    protected void doInit() {
        Log.d("nf_service_resourcefetcher", "ResourceFetcher starting doInit.");
        final int resFetcherThreadPoolSize = this.getConfigurationAgent().getResFetcherThreadPoolSize();
        if (Log.isLoggable("nf_service_resourcefetcher", 3)) {
            Log.d("nf_service_resourcefetcher", String.format("Creating Volley RequestQueue with threadPoolsize of %d", resFetcherThreadPoolSize));
        }
        (this.mRequestQueue = new RequestQueue(this.createDiskCache(), new BasicNetwork(this.createHttpStack()), resFetcherThreadPoolSize)).start();
        this.mDownloadsDir = new File(this.getContext().getCacheDir(), "downloads");
        if (!this.mDownloadsDir.isDirectory()) {
            this.mDownloadsDir.mkdirs();
        }
        this.mWebClient.init(new NetflixWebClientInitParameters(this.getConfigurationAgent().getApiEndpointRegistry(), this.getUserAgent().getUserCredentialRegistry(), this.getService().getClientLogging().getErrorLogging(), this.mRequestQueue));
        final int dataRequestTimeout = this.getConfigurationAgent().getDataRequestTimeout();
        if (Log.isLoggable("nf_service_resourcefetcher", 4)) {
            Log.i("nf_service_resourcefetcher", "Setting default timeout value for data request to " + dataRequestTimeout + "ms");
        }
        this.mWebClient.setTimeout(dataRequestTimeout);
        this.initCompleted(0);
    }
    
    public void fetchResource(final String s, final IClientLogging.AssetType assetType, final ResourceFetcherCallback resourceFetcherCallback) {
        if (Log.isLoggable("nf_service_resourcefetcher", 4)) {
            Log.i("nf_service_resourcefetcher", "Received request to fetch resource at " + s);
        }
        LogUtils.reportAssetRequest(s, assetType, this.getService().getClientLogging().getApplicationPerformanceMetricsLogging());
        final ResourceFetcherCallback resourceFetcherCallback2 = this.getResourceFetcherCallback(resourceFetcherCallback);
        this.mRequestQueue.add(new FileDownloadRequest(s, resourceFetcherCallback2, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("nf_service_resourcefetcher", "FileDownloadRequest failed: ", volleyError);
                if (resourceFetcherCallback2 != null) {
                    resourceFetcherCallback2.onResourceFetched(s, null, -3);
                }
            }
        }, this.getConfigurationAgent().getResourceRequestTimeout(), this.mDownloadsDir));
    }
    
    public WebClient getApiNextWebClient() {
        return this.mWebClient;
    }
    
    public ImageLoader getImageLoader() {
        Label_0026: {
            if (this.mImageLoader != null) {
                break Label_0026;
            }
            synchronized (this) {
                if (this.mImageLoader == null) {
                    this.mImageLoader = this.createImageLoader();
                }
                // monitorexit(this)
                return this.mImageLoader;
            }
        }
    }
    
    public void prefetchResource(final String s, final IClientLogging.AssetType assetType, final ResourceFetcherCallback resourceFetcherCallback) {
        if (s == null) {
            Log.w("nf_service_resourcefetcher", String.format("Request to prefetch resource with null URL", new Object[0]));
            this.getMainHandler().post((Runnable)new Runnable() {
                @Override
                public void run() {
                    resourceFetcherCallback.onResourcePrefetched(s, 0, -2);
                }
            });
            return;
        }
        if (Log.isLoggable("nf_service_resourcefetcher", 3)) {
            Log.d("nf_service_resourcefetcher", "Request to prefetch resource at URL " + s);
        }
        LogUtils.reportAssetRequest(s, assetType, this.getService().getClientLogging().getApplicationPerformanceMetricsLogging());
        final ResourceFetcherCallback resourceFetcherCallback2 = this.getResourceFetcherCallback(resourceFetcherCallback);
        this.mRequestQueue.add(new PrefetchResourceRequest(s, resourceFetcherCallback2, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("nf_service_resourcefetcher", "PrefetchRequest failed: ", volleyError);
                if (resourceFetcherCallback2 != null) {
                    resourceFetcherCallback2.onResourcePrefetched(s, 0, -3);
                }
            }
        }, this.getConfigurationAgent().getResourceRequestTimeout()));
    }
    
    private class ResourceFetcherCallbackWrapper implements ResourceFetcherCallback
    {
        private ResourceFetcherCallback mCallback;
        
        private ResourceFetcherCallbackWrapper(final ResourceFetcherCallback mCallback) {
            if (mCallback == null) {
                throw new IllegalArgumentException("Callback can not be null");
            }
            this.mCallback = mCallback;
        }
        
        @Override
        public void onResourceFetched(final String s, final String s2, final int n) {
            LogUtils.reportAssetRequestResult(s, n, ResourceFetcher.this.getService().getClientLogging().getApplicationPerformanceMetricsLogging());
            this.mCallback.onResourceFetched(s, s2, n);
        }
        
        @Override
        public void onResourcePrefetched(final String s, final int n, final int n2) {
            LogUtils.reportAssetRequestResult(s, n2, ResourceFetcher.this.getService().getClientLogging().getApplicationPerformanceMetricsLogging());
            this.mCallback.onResourcePrefetched(s, n, n2);
        }
    }
    
    private static class VolleyImageCache extends BitmapLruCache implements ImageCache
    {
        public VolleyImageCache(final int n) {
            super(n);
        }
    }
}
