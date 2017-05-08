// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.concurrent.Future;
import com.facebook.imagepipeline.image.EncodedImage;
import java.net.URL;
import java.util.Locale;
import java.io.IOException;
import java.net.HttpURLConnection;
import android.net.Uri;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher<FetchState>
{
    private final ExecutorService mExecutorService;
    
    public HttpUrlConnectionNetworkFetcher() {
        this(Executors.newFixedThreadPool(3));
    }
    
    HttpUrlConnectionNetworkFetcher(final ExecutorService mExecutorService) {
        this.mExecutorService = mExecutorService;
    }
    
    private HttpURLConnection downloadFrom(final Uri uri, final int n) {
        final HttpURLConnection openConnectionTo = openConnectionTo(uri);
        final int responseCode = openConnectionTo.getResponseCode();
        if (isHttpSuccess(responseCode)) {
            return openConnectionTo;
        }
        if (!isHttpRedirect(responseCode)) {
            openConnectionTo.disconnect();
            throw new IOException(String.format("Image URL %s returned HTTP code %d", uri.toString(), responseCode));
        }
        final String headerField = openConnectionTo.getHeaderField("Location");
        openConnectionTo.disconnect();
        Uri parse;
        if (headerField == null) {
            parse = null;
        }
        else {
            parse = Uri.parse(headerField);
        }
        final String scheme = uri.getScheme();
        if (n > 0 && parse != null && !parse.getScheme().equals(scheme)) {
            return this.downloadFrom(parse, n - 1);
        }
        String s;
        if (n == 0) {
            s = error("URL %s follows too many redirects", uri.toString());
        }
        else {
            s = error("URL %s returned %d without a valid redirect", uri.toString(), responseCode);
        }
        throw new IOException(s);
    }
    
    private static String error(final String s, final Object... array) {
        return String.format(Locale.getDefault(), s, array);
    }
    
    private static boolean isHttpRedirect(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 300:
            case 301:
            case 302:
            case 303:
            case 307:
            case 308: {
                return true;
            }
        }
    }
    
    private static boolean isHttpSuccess(final int n) {
        return n >= 200 && n < 300;
    }
    
    static HttpURLConnection openConnectionTo(final Uri uri) {
        return (HttpURLConnection)new URL(uri.toString()).openConnection();
    }
    
    @Override
    public FetchState createFetchState(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        return new FetchState(consumer, producerContext);
    }
    
    @Override
    public void fetch(final FetchState fetchState, final NetworkFetcher$Callback networkFetcher$Callback) {
        fetchState.getContext().addCallbacks(new HttpUrlConnectionNetworkFetcher$2(this, this.mExecutorService.submit(new HttpUrlConnectionNetworkFetcher$1(this, fetchState, networkFetcher$Callback)), networkFetcher$Callback));
    }
    
    void fetchSync(final FetchState fetchState, final NetworkFetcher$Callback networkFetcher$Callback) {
        HttpURLConnection httpURLConnection = null;
        HttpURLConnection httpURLConnection2 = null;
        try {
            final HttpURLConnection download = this.downloadFrom(fetchState.getUri(), 5);
            if (download != null) {
                httpURLConnection2 = download;
                httpURLConnection = download;
                networkFetcher$Callback.onResponse(download.getInputStream(), -1);
            }
        }
        catch (IOException ex) {
            httpURLConnection = httpURLConnection2;
            networkFetcher$Callback.onFailure(ex);
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
