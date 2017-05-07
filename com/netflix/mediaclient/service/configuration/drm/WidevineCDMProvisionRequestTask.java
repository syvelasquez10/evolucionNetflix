// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.params.HttpParams;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpUriRequest;
import com.netflix.mediaclient.Log;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;

public final class WidevineCDMProvisionRequestTask extends AsyncTask<String, Void, byte[]>
{
    public static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
    public static final int DEFAULT_SOCKET_TIMEOUT = 3000;
    private final String TAG;
    private WidevineDrmManager$WidewineProvisiongCallback callback;
    private int connectionTimeout;
    private byte[] drmRequest;
    private int socketTimeout;
    
    public WidevineCDMProvisionRequestTask(final byte[] drmRequest, final WidevineDrmManager$WidewineProvisiongCallback callback) {
        this.TAG = "nf_net";
        this.connectionTimeout = 5000;
        this.socketTimeout = 3000;
        this.drmRequest = drmRequest;
        this.callback = callback;
    }
    
    public WidevineCDMProvisionRequestTask(final byte[] array, final WidevineDrmManager$WidewineProvisiongCallback widevineDrmManager$WidewineProvisiongCallback, final int connectionTimeout, final int socketTimeout) {
        this(array, widevineDrmManager$WidewineProvisiongCallback);
        this.connectionTimeout = connectionTimeout;
        this.socketTimeout = socketTimeout;
    }
    
    private byte[] postRequest(final String s, final byte[] array) {
        final DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        final HttpPost httpPost = new HttpPost(s + "&signedRequest=" + new String(array));
        final HttpParams params = ((HttpClient)defaultHttpClient).getParams();
        HttpConnectionParams.setConnectionTimeout(params, this.connectionTimeout);
        HttpConnectionParams.setSoTimeout(params, this.socketTimeout);
        if (Log.isLoggable()) {
            Log.d("nf_net", "PostRequest:" + httpPost.getRequestLine());
        }
        try {
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("User-Agent", "Widevine CDM v1.0");
            httpPost.setHeader("Content-Type", "application/json");
            final HttpResponse execute = ((HttpClient)defaultHttpClient).execute((HttpUriRequest)httpPost);
            final int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return EntityUtils.toByteArray(execute.getEntity());
            }
            if (Log.isLoggable()) {
                Log.d("nf_net", "Server returned HTTP error code " + statusCode);
                return null;
            }
        }
        catch (ConnectTimeoutException ex) {
            Log.e("nf_net", "Connection timeout", (Throwable)ex);
            return null;
        }
        catch (SocketTimeoutException ex2) {
            Log.e("nf_net", "Socket timeout", ex2);
            return null;
        }
        catch (ClientProtocolException ex3) {
            Log.e("nf_net", "Request protocol failed", (Throwable)ex3);
            return null;
        }
        catch (IOException ex4) {
            Log.e("nf_net", "Request IO failed ", ex4);
        }
        return null;
    }
    
    protected byte[] doInBackground(final String... array) {
        final byte[] postRequest = this.postRequest(array[0], this.drmRequest);
        if (postRequest != null) {
            Log.d("nf_net", "response length=" + postRequest.length);
        }
        else {
            Log.e("nf_net", "Response is null!");
        }
        if (this.callback != null) {
            this.callback.done(postRequest);
        }
        return postRequest;
    }
}
