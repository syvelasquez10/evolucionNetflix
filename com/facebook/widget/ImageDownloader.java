// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.io.Serializable;
import android.graphics.Bitmap;
import java.io.InputStream;
import java.io.IOException;
import android.graphics.BitmapFactory;
import java.net.URL;
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
    Label_0132_Outer:
        while (true) {
            URLConnection urlConnection = null;
            URLConnection urlConnection2 = null;
            final InputStream inputStream = null;
            final Closeable closeable = null;
            final Closeable closeable2 = null;
            final IOException ex = null;
            final Bitmap bitmap = null;
            final Bitmap bitmap2 = null;
            boolean b = true;
            boolean b3;
            boolean b2 = b3 = true;
            Closeable closeable3 = closeable2;
            Closeable closeable4 = closeable;
        Label_0606_Outer:
            while (true) {
                Label_0624: {
                    try {
                        final HttpURLConnection httpURLConnection = (HttpURLConnection)(urlConnection2 = requestKey.url.openConnection());
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
                        while (true) {
                            urlConnection2 = httpURLConnection;
                            b3 = b2;
                            final InputStream inputStream2;
                            closeable3 = inputStream2;
                            urlConnection = httpURLConnection;
                            closeable4 = inputStream2;
                            final char[] array;
                            final int read = ((InputStreamReader)removePendingRequest).read(array, 0, array.length);
                            final Serializable s;
                            Block_3: {
                                break Block_3;
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
                            urlConnection2 = httpURLConnection;
                            b3 = b2;
                            closeable3 = inputStream2;
                            urlConnection = httpURLConnection;
                            closeable4 = inputStream2;
                            ((StringBuilder)s).append(array, 0, read);
                            continue Label_0132_Outer;
                        }
                    }
                    // iftrue(Label_0685:, read <= 0)
                    catch (IOException removePendingRequest) {
                        Utility.closeQuietly(closeable3);
                        Utility.disconnectQuietly(urlConnection2);
                        Bitmap decodeStream = bitmap2;
                    Block_6_Outer:
                        while (true) {
                            final HttpURLConnection httpURLConnection;
                            InputStream inputStream2;
                            Serializable s;
                            while (true) {
                                if (b3) {
                                    issueResponse(requestKey, removePendingRequest, decodeStream, false);
                                }
                                return;
                                Utility.closeQuietly(inputStream2);
                                Utility.disconnectQuietly(httpURLConnection);
                                removePendingRequest = (IOException)s;
                                b3 = b2;
                                continue Label_0606_Outer;
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
                            continue Block_6_Outer;
                            URL url = null;
                            boolean b4 = false;
                        Block_7:
                            while (true) {
                                urlConnection = httpURLConnection;
                                closeable4 = closeable;
                                final String headerField;
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
                                break Block_7;
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
                                continue;
                            }
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
                            continue Block_6_Outer;
                            urlConnection = httpURLConnection;
                            closeable4 = closeable;
                            inputStream2 = ImageResponseCache.interceptAndCacheImageStream((Context)removePendingRequest, httpURLConnection);
                            urlConnection = httpURLConnection;
                            closeable4 = inputStream2;
                            decodeStream = BitmapFactory.decodeStream(inputStream2);
                            s = ex;
                            b2 = b;
                            continue Block_6_Outer;
                        }
                    }
                    // iftrue(Label_0606:, removePendingRequest == null)
                    // iftrue(Label_0606:, Utility.isNullOrEmpty(headerField))
                    // iftrue(Label_0606:, removePendingRequest.isCancelled)
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
