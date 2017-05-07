// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

import com.netflix.mediaclient.service.resfetcher.volley.PrefetchResourceRequest;
import com.netflix.mediaclient.service.webclient.WebClient;
import com.netflix.mediaclient.service.resfetcher.volley.FileDownloadRequest;
import com.android.volley.Request$Priority;
import com.android.volley.Request;
import com.android.volley.Response$ErrorListener;
import com.netflix.mediaclient.service.resfetcher.volley.HttpRangeRequest;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.WebClientInitParameters;
import com.netflix.mediaclient.service.webclient.NetflixWebClientInitParameters;
import com.android.volley.Network;
import com.android.volley.Cache;
import com.android.volley.toolbox.BasicNetwork;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.gfx.BitmapLruCache;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$ImageCache;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.content.Context;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.HttpStack;
import com.netflix.mediaclient.Log;
import com.android.volley.toolbox.DiskBasedCache;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader;
import java.io.File;
import com.netflix.mediaclient.service.ServiceAgent;

public class ResourceFetcher extends ServiceAgent
{
    private static final String DOWNLOADS_CACHE_DIR = "downloads";
    private static final long MINIMUM_IMAGE_CACHE_TTL = 1209600000L;
    private static final int RESOURCE_REQUEST_TIMEOUT_MS = 1000;
    private static final String SELECTED_WEBCLIENT = "volley";
    private static final String TAG = "nf_service_resourcefetcher";
    private static final String VOLLEY_CACHE_DIR = "volley";
    private static final String VOLLEY_WEBCLIENT_NAME = "volley";
    private File mDownloadsDir;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;
    private final FalkorVolleyWebClient mWebClient;
    
    public ResourceFetcher() {
        this.mWebClient = createWebClient();
    }
    
    private DiskBasedCache createDiskCache() {
        final File file = new File(this.getContext().getCacheDir(), "volley");
        final int diskCacheSizeBytes = this.getConfigurationAgent().getDiskCacheSizeBytes();
        if (Log.isLoggable()) {
            Log.i("nf_service_resourcefetcher", String.format("Creating new Volley DiskBasedCache, location: %s,  max size: %d bytes", file.getAbsolutePath(), diskCacheSizeBytes));
        }
        return new DiskBasedCache(file, diskCacheSizeBytes);
    }
    
    private HttpStack createHttpStack() {
        Log.d("nf_service_resourcefetcher", "Using HttpURLConnection for Volley");
        return new HurlStack();
    }
    
    private ImageLoader createImageLoader(final Context context) {
        final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging = null;
        Log.d("nf_service_resourcefetcher", "ResourceFetcher creating ImageLoader");
        if (this.mRequestQueue == null) {
            Log.w("nf_service_resourcefetcher", "Attempting to create an ImageLoader with a null RequestQueue");
            ErrorLoggingManager.logHandledException("Attempting to create an ImageLoader with a null RequestQueue");
            return null;
        }
        final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        long imageCacheMinimumTtl = 1209600000L;
        int resourceRequestTimeout = 1000;
        if (configurationAgent != null) {
            resourceRequestTimeout = configurationAgent.getResourceRequestTimeout();
            imageCacheMinimumTtl = configurationAgent.getImageCacheMinimumTtl();
        }
        if (Log.isLoggable()) {
            Log.d("nf_service_resourcefetcher", "Received request to create new ImageLoader with socketTimeout = " + resourceRequestTimeout + " and minimumTtl = " + imageCacheMinimumTtl + "ms");
        }
        ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging2 = applicationPerformanceMetricsLogging;
        if (this.getService() != null) {
            applicationPerformanceMetricsLogging2 = applicationPerformanceMetricsLogging;
            if (this.getService().getClientLogging() != null) {
                applicationPerformanceMetricsLogging2 = this.getService().getClientLogging().getApplicationPerformanceMetricsLogging();
            }
        }
        return new ImageLoader(this.mRequestQueue, this.getImageCache(context), resourceRequestTimeout, imageCacheMinimumTtl, applicationPerformanceMetricsLogging2, configurationAgent);
    }
    
    private static FalkorVolleyWebClient createWebClient() {
        Log.i("nf_service_resourcefetcher", "WebClient of type volley");
        if ("volley".equals("volley")) {
            return new FalkorVolleyWebClient();
        }
        throw new IllegalStateException("Webclient not implemented");
    }
    
    private ImageLoader$ImageCache getImageCache(final Context context) {
        synchronized (this) {
            final NetflixApplication netflixApplication = (NetflixApplication)context.getApplicationContext();
            final BitmapLruCache imageCache = netflixApplication.getImageCache();
            ImageLoader$ImageCache imageCache2;
            if (imageCache != null && imageCache instanceof ImageLoader$ImageCache) {
                imageCache2 = (ImageLoader$ImageCache)imageCache;
            }
            else {
                final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
                if (configurationAgent == null) {
                    Log.w("nf_service_resourcefetcher", "Config interface is null - using default img cache size");
                }
                int n;
                if (configurationAgent == null) {
                    n = ConfigurationAgent.DEFAULT_IMAGE_CACHE_SIZE_BYTES;
                }
                else {
                    n = configurationAgent.getImageCacheSizeBytes();
                }
                Log.i("nf_service_resourcefetcher", "Creating new BitmapLruCache of size " + n + " bytes");
                imageCache2 = new ResourceFetcher$VolleyImageCache(n);
                netflixApplication.setImageCache((BitmapLruCache)imageCache2);
            }
            return imageCache2;
        }
    }
    
    private ResourceFetcherCallback getResourceFetcherCallback(final ResourceFetcherCallback resourceFetcherCallback) {
        if (resourceFetcherCallback == null) {
            Log.w("nf_service_resourcefetcher", "Resource fetcher callback is null!");
            return null;
        }
        return new ResourceFetcher$ResourceFetcherCallbackWrapper(this, resourceFetcherCallback, null);
    }
    
    public boolean deleteLocalResource(final String s) {
        boolean delete = false;
        if (this.mDownloadsDir.isDirectory()) {
            delete = delete;
            if (StringUtils.isNotEmpty(s)) {
                delete = new File(this.mDownloadsDir, s).delete();
            }
        }
        return delete;
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
        if (Log.isLoggable()) {
            Log.d("nf_service_resourcefetcher", String.format("Creating Volley RequestQueue with threadPoolsize of %d", resFetcherThreadPoolSize));
        }
        (this.mRequestQueue = new RequestQueue(this.createDiskCache(), new BasicNetwork(this.createHttpStack()), resFetcherThreadPoolSize)).start();
        this.mDownloadsDir = new File(this.getContext().getCacheDir(), "downloads");
        if (!this.mDownloadsDir.isDirectory()) {
            this.mDownloadsDir.mkdirs();
        }
        this.mWebClient.init(new NetflixWebClientInitParameters(this.getConfigurationAgent().getApiEndpointRegistry(), this.getUserAgent().getUserCredentialRegistry(), this.getService().getClientLogging().getErrorLogging(), this.mRequestQueue));
        final int dataRequestTimeout = this.getConfigurationAgent().getDataRequestTimeout();
        if (Log.isLoggable()) {
            Log.i("nf_service_resourcefetcher", "Setting default timeout value for data request to " + dataRequestTimeout + "ms");
        }
        this.mWebClient.setTimeout(dataRequestTimeout);
        this.initCompleted(CommonStatus.OK);
    }
    
    public void fetchResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final long n, final long n2, final ResourceFetcherCallback resourceFetcherCallback) {
        if (Log.isLoggable()) {
            Log.i("nf_service_resourcefetcher", "Received request to fetch resource at " + s);
        }
        ApmLogUtils.reportAssetRequest(s, clientLogging$AssetType, this.getService().getClientLogging().getApplicationPerformanceMetricsLogging());
        final ResourceFetcherCallback resourceFetcherCallback2 = this.getResourceFetcherCallback(resourceFetcherCallback);
        this.mRequestQueue.add(new HttpRangeRequest(s, n, n2, resourceFetcherCallback2, new ResourceFetcher$2(this, resourceFetcherCallback2, s), this.getConfigurationAgent().getResourceRequestTimeout()));
    }
    
    public void fetchResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final Request$Priority request$Priority, final ResourceFetcherCallback resourceFetcherCallback) {
        if (Log.isLoggable()) {
            Log.i("nf_service_resourcefetcher", "Received request to fetch resource at " + s);
        }
        ApmLogUtils.reportAssetRequest(s, clientLogging$AssetType, this.getService().getClientLogging().getApplicationPerformanceMetricsLogging());
        final ResourceFetcherCallback resourceFetcherCallback2 = this.getResourceFetcherCallback(resourceFetcherCallback);
        this.mRequestQueue.add(new FileDownloadRequest(s, resourceFetcherCallback2, new ResourceFetcher$1(this, resourceFetcherCallback2, s), this.getConfigurationAgent().getResourceRequestTimeout(), request$Priority, this.mDownloadsDir));
    }
    
    public void fetchResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final ResourceFetcherCallback resourceFetcherCallback) {
        if (Log.isLoggable()) {
            Log.i("nf_service_resourcefetcher", "Received request to fetch resource at " + s);
        }
        this.fetchResource(s, clientLogging$AssetType, Request$Priority.NORMAL, resourceFetcherCallback);
    }
    
    public WebClient getApiNextWebClient() {
        return this.mWebClient;
    }
    
    public ImageLoader getImageLoader(final Context context) {
        Label_0027: {
            if (this.mImageLoader != null) {
                break Label_0027;
            }
            synchronized (this) {
                if (this.mImageLoader == null) {
                    this.mImageLoader = this.createImageLoader(context);
                }
                // monitorexit(this)
                return this.mImageLoader;
            }
        }
    }
    
    public void prefetchResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final ResourceFetcherCallback resourceFetcherCallback) {
        if (s == null) {
            Log.w("nf_service_resourcefetcher", String.format("Request to prefetch resource with null URL", new Object[0]));
            this.getMainHandler().post((Runnable)new ResourceFetcher$3(this, resourceFetcherCallback, s));
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_service_resourcefetcher", "Request to prefetch resource at URL " + s);
        }
        ApmLogUtils.reportAssetRequest(s, clientLogging$AssetType, this.getService().getClientLogging().getApplicationPerformanceMetricsLogging());
        final ResourceFetcherCallback resourceFetcherCallback2 = this.getResourceFetcherCallback(resourceFetcherCallback);
        this.mRequestQueue.add(new PrefetchResourceRequest(s, resourceFetcherCallback2, new ResourceFetcher$4(this, resourceFetcherCallback2, s), this.getConfigurationAgent().getResourceRequestTimeout()));
    }
}
