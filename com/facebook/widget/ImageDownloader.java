// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.io.Serializable;
import android.graphics.Bitmap;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import android.graphics.BitmapFactory;
import com.facebook.FacebookException;
import java.net.URLConnection;
import java.io.Closeable;
import com.facebook.internal.Utility;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;

class ImageDownloader
{
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue;
    private static WorkQueue downloadQueue;
    private static final Handler handler;
    private static final Map<RequestKey, DownloaderContext> pendingRequests;
    
    static {
        handler = new Handler();
        ImageDownloader.downloadQueue = new WorkQueue(8);
        ImageDownloader.cacheReadQueue = new WorkQueue(2);
        pendingRequests = new HashMap<RequestKey, DownloaderContext>();
    }
    
    static boolean cancelRequest(final ImageRequest imageRequest) {
        boolean b = false;
        final RequestKey requestKey = new RequestKey(imageRequest.getImageUrl(), imageRequest.getCallerTag());
        synchronized (ImageDownloader.pendingRequests) {
            final DownloaderContext downloaderContext = ImageDownloader.pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                b = true;
                if (downloaderContext.workItem.cancel()) {
                    ImageDownloader.pendingRequests.remove(requestKey);
                }
                else {
                    downloaderContext.isCancelled = true;
                }
            }
            return b;
        }
    }
    
    private static void download(final RequestKey requestKey, Context removePendingRequest) {
        URLConnection urlConnection;
        URLConnection urlConnection2;
        InputStream inputStream;
        Closeable closeable;
        Closeable closeable2;
        IOException ex;
        Bitmap bitmap;
        Bitmap bitmap2;
        boolean b;
        boolean b3;
        boolean b2;
        Closeable closeable3;
        Closeable closeable4;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream2 = null;
        Serializable s = null;
        char[] array = null;
        int read = 0;
        Bitmap decodeStream;
        boolean b4 = false;
        String headerField = null;
        URL url;
        Label_0237_Outer:Label_0132_Outer:
        while (true) {
            urlConnection = null;
            urlConnection2 = null;
            inputStream = null;
            closeable = null;
            closeable2 = null;
            ex = null;
            bitmap = null;
            bitmap2 = null;
            b = true;
            b2 = (b3 = true);
            closeable3 = closeable2;
            closeable4 = closeable;
        Block_7_Outer:
            while (true) {
                Label_0624: {
                    try {
                        httpURLConnection = (HttpURLConnection)(urlConnection2 = requestKey.url.openConnection());
                        b3 = b2;
                        closeable3 = closeable2;
                        urlConnection = httpURLConnection;
                        closeable4 = closeable;
                        httpURLConnection.setInstanceFollowRedirects(false);
                        urlConnection2 = httpURLConnection;
                        b3 = b2;
                        closeable3 = closeable2;
                        urlConnection = httpURLConnection;
                        closeable4 = closeable;
                        switch (httpURLConnection.getResponseCode()) {
                            case 301:
                            case 302: {
                                break Label_0624;
                            }
                            case 200: {
                                break Label_0624;
                            }
                            default: {
                                break Label_0624;
                            }
                        }
                        // iftrue(Label_0685:, read <= 0)
                        while (true) {
                            while (true) {
                                urlConnection2 = httpURLConnection;
                                b3 = b2;
                                closeable3 = inputStream2;
                                urlConnection = httpURLConnection;
                                closeable4 = inputStream2;
                                ((StringBuilder)s).append(array, 0, read);
                                urlConnection2 = httpURLConnection;
                                b3 = b2;
                                closeable3 = inputStream2;
                                urlConnection = httpURLConnection;
                                closeable4 = inputStream2;
                                read = ((InputStreamReader)removePendingRequest).read(array, 0, array.length);
                                continue Label_0237_Outer;
                            }
                            urlConnection2 = httpURLConnection;
                            b3 = b2;
                            closeable3 = closeable2;
                            urlConnection = httpURLConnection;
                            closeable4 = closeable;
                            inputStream2 = httpURLConnection.getErrorStream();
                            urlConnection2 = httpURLConnection;
                            b3 = b2;
                            closeable3 = inputStream2;
                            urlConnection = httpURLConnection;
                            closeable4 = inputStream2;
                            removePendingRequest = (IOException)new InputStreamReader(inputStream2);
                            urlConnection2 = httpURLConnection;
                            b3 = b2;
                            closeable3 = inputStream2;
                            urlConnection = httpURLConnection;
                            closeable4 = inputStream2;
                            array = new char[128];
                            urlConnection2 = httpURLConnection;
                            b3 = b2;
                            closeable3 = inputStream2;
                            urlConnection = httpURLConnection;
                            closeable4 = inputStream2;
                            s = new StringBuilder();
                            continue Label_0132_Outer;
                        }
                    }
                    catch (IOException removePendingRequest) {
                        Utility.closeQuietly(closeable3);
                        Utility.disconnectQuietly(urlConnection2);
                        decodeStream = bitmap2;
                        // iftrue(Label_0606:, Utility.isNullOrEmpty(headerField))
                        // iftrue(Label_0606:, removePendingRequest.isCancelled)
                        while (true) {
                            Block_6: {
                                while (true) {
                                    while (true) {
                                        if (b3) {
                                            issueResponse(requestKey, removePendingRequest, decodeStream, false);
                                        }
                                        return;
                                        b4 = false;
                                        b = false;
                                        urlConnection = httpURLConnection;
                                        closeable4 = closeable;
                                        headerField = httpURLConnection.getHeaderField("location");
                                        decodeStream = bitmap;
                                        s = ex;
                                        b2 = b4;
                                        inputStream2 = inputStream;
                                        urlConnection = httpURLConnection;
                                        closeable4 = closeable;
                                        break Block_6;
                                        decodeStream = bitmap;
                                        s = ex;
                                        b2 = b4;
                                        inputStream2 = inputStream;
                                        urlConnection = httpURLConnection;
                                        closeable4 = closeable;
                                        urlConnection = httpURLConnection;
                                        closeable4 = closeable;
                                        enqueueCacheRead(((DownloaderContext)removePendingRequest).request, new RequestKey(url, requestKey.tag), false);
                                        inputStream2 = inputStream;
                                        b2 = b4;
                                        s = ex;
                                        decodeStream = bitmap;
                                        Utility.closeQuietly(inputStream2);
                                        Utility.disconnectQuietly(httpURLConnection);
                                        removePendingRequest = (IOException)s;
                                        b3 = b2;
                                        continue Block_7_Outer;
                                    }
                                    Label_0685: {
                                        urlConnection = httpURLConnection;
                                    }
                                    closeable4 = inputStream2;
                                    Utility.closeQuietly((Closeable)removePendingRequest);
                                    urlConnection = httpURLConnection;
                                    closeable4 = inputStream2;
                                    s = new FacebookException(((StringBuilder)s).toString());
                                    decodeStream = bitmap;
                                    b2 = b;
                                    continue;
                                    urlConnection = httpURLConnection;
                                    closeable4 = closeable;
                                    inputStream2 = ImageResponseCache.interceptAndCacheImageStream((Context)removePendingRequest, httpURLConnection);
                                    urlConnection = httpURLConnection;
                                    closeable4 = inputStream2;
                                    decodeStream = BitmapFactory.decodeStream(inputStream2);
                                    s = ex;
                                    b2 = b;
                                    continue;
                                }
                            }
                            urlConnection = httpURLConnection;
                            closeable4 = closeable;
                            url = new URL(headerField);
                            urlConnection = httpURLConnection;
                            closeable4 = closeable;
                            UrlRedirectCache.cacheUrlRedirect((Context)removePendingRequest, requestKey.url, url);
                            urlConnection = httpURLConnection;
                            closeable4 = closeable;
                            removePendingRequest = (IOException)removePendingRequest(requestKey);
                            decodeStream = bitmap;
                            s = ex;
                            b2 = b4;
                            inputStream2 = inputStream;
                            continue;
                        }
                    }
                    // iftrue(Label_0606:, removePendingRequest == null)
                    finally {
                        Utility.closeQuietly(closeable4);
                        Utility.disconnectQuietly(urlConnection);
                    }
                }
                continue;
            }
        }
    }
    
    static void downloadAsync(final ImageRequest request) {
        if (request == null) {
            return;
        }
        while (true) {
            final RequestKey requestKey = new RequestKey(request.getImageUrl(), request.getCallerTag());
            synchronized (ImageDownloader.pendingRequests) {
                final DownloaderContext downloaderContext = ImageDownloader.pendingRequests.get(requestKey);
                if (downloaderContext != null) {
                    downloaderContext.request = request;
                    downloaderContext.isCancelled = false;
                    downloaderContext.workItem.moveToFront();
                    return;
                }
            }
            final ImageRequest imageRequest;
            enqueueCacheRead(imageRequest, requestKey, imageRequest.isCachedRedirectAllowed());
        }
    }
    
    private static void enqueueCacheRead(final ImageRequest imageRequest, final RequestKey requestKey, final boolean b) {
        enqueueRequest(imageRequest, requestKey, ImageDownloader.cacheReadQueue, new CacheReadWorkItem(imageRequest.getContext(), requestKey, b));
    }
    
    private static void enqueueDownload(final ImageRequest imageRequest, final RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, ImageDownloader.downloadQueue, new DownloadImageWorkItem(imageRequest.getContext(), requestKey));
    }
    
    private static void enqueueRequest(final ImageRequest request, final RequestKey requestKey, final WorkQueue workQueue, final Runnable runnable) {
        synchronized (ImageDownloader.pendingRequests) {
            final DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = request;
            ImageDownloader.pendingRequests.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }
    
    private static void issueResponse(final RequestKey requestKey, final Exception ex, final Bitmap bitmap, final boolean b) {
        final DownloaderContext removePendingRequest = removePendingRequest(requestKey);
        if (removePendingRequest != null && !removePendingRequest.isCancelled) {
            final ImageRequest request = removePendingRequest.request;
            final ImageRequest.Callback callback = request.getCallback();
            if (callback != null) {
                ImageDownloader.handler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        callback.onCompleted(new ImageResponse(request, ex, b, bitmap));
                    }
                });
            }
        }
    }
    
    static void prioritizeRequest(final ImageRequest imageRequest) {
        final RequestKey requestKey = new RequestKey(imageRequest.getImageUrl(), imageRequest.getCallerTag());
        synchronized (ImageDownloader.pendingRequests) {
            final DownloaderContext downloaderContext = ImageDownloader.pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }
    
    private static void readFromCache(final RequestKey requestKey, final Context context, final boolean b) {
        final InputStream inputStream = null;
        final boolean b2 = false;
        InputStream inputStream2 = inputStream;
        boolean b3 = b2;
        if (b) {
            final URL redirectedUrl = UrlRedirectCache.getRedirectedUrl(context, requestKey.url);
            inputStream2 = inputStream;
            b3 = b2;
            if (redirectedUrl != null) {
                inputStream2 = ImageResponseCache.getCachedImageStream(redirectedUrl, context);
                b3 = (inputStream2 != null);
            }
        }
        if (!b3) {
            inputStream2 = ImageResponseCache.getCachedImageStream(requestKey.url, context);
        }
        if (inputStream2 != null) {
            final Bitmap decodeStream = BitmapFactory.decodeStream(inputStream2);
            Utility.closeQuietly(inputStream2);
            issueResponse(requestKey, null, decodeStream, b3);
        }
        else {
            final DownloaderContext removePendingRequest = removePendingRequest(requestKey);
            if (removePendingRequest != null && !removePendingRequest.isCancelled) {
                enqueueDownload(removePendingRequest.request, requestKey);
            }
        }
    }
    
    private static DownloaderContext removePendingRequest(final RequestKey requestKey) {
        synchronized (ImageDownloader.pendingRequests) {
            return ImageDownloader.pendingRequests.remove(requestKey);
        }
    }
    
    private static class CacheReadWorkItem implements Runnable
    {
        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;
        
        CacheReadWorkItem(final Context context, final RequestKey key, final boolean allowCachedRedirects) {
            this.context = context;
            this.key = key;
            this.allowCachedRedirects = allowCachedRedirects;
        }
        
        @Override
        public void run() {
            readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }
    
    private static class DownloadImageWorkItem implements Runnable
    {
        private Context context;
        private RequestKey key;
        
        DownloadImageWorkItem(final Context context, final RequestKey key) {
            this.context = context;
            this.key = key;
        }
        
        @Override
        public void run() {
            download(this.key, this.context);
        }
    }
    
    private static class DownloaderContext
    {
        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;
    }
    
    private static class RequestKey
    {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        URL url;
        
        RequestKey(final URL url, final Object tag) {
            this.url = url;
            this.tag = tag;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = false;
            if (o != null) {
                b = b;
                if (o instanceof RequestKey) {
                    final RequestKey requestKey = (RequestKey)o;
                    if (requestKey.url != this.url || requestKey.tag != this.tag) {
                        return false;
                    }
                    b = true;
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            return (this.url.hashCode() + 1073) * 37 + this.tag.hashCode();
        }
    }
}
