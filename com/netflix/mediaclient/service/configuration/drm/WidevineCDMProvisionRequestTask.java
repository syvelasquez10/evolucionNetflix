// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.client.ClientProtocolException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
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
    
    protected byte[] doInBackground(final String... array) {
        Object o = null;
        final DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        final HttpPost httpPost = new HttpPost(array[0] + "&signedRequest=" + new String(this.drmRequest));
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
            Object o2 = ((HttpClient)defaultHttpClient).execute((HttpUriRequest)httpPost);
            final int statusCode = ((HttpResponse)o2).getStatusLine().getStatusCode();
            Label_0254: {
                if (statusCode != 200) {
                    break Label_0254;
                }
                try {
                    o2 = EntityUtils.toByteArray(((HttpResponse)o2).getEntity());
                    if (o2 != null) {
                        Log.d("nf_net", "response length=" + ((HttpResponse)o2).length);
                        o = o2;
                        if (this.callback != null) {
                            this.callback.done((byte[])o2);
                            o = o2;
                        }
                        Label_0251: {
                            return (byte[])o;
                        }
                    }
                    goto Label_0468;
                    Label_0292: {
                        ErrorLoggingManager.logHandledException("15002. Provisiong failed with status code 400 " + array[0]);
                    }
                    // iftrue(Label_0251:, this.callback == null)
                    // iftrue(Label_0355:, statusCode != 400)
                    Block_7: {
                        Block_9: {
                            break Block_9;
                            break Block_7;
                        }
                        this.callback.abort();
                        return null;
                    }
                    // iftrue(Label_0292:, !Log.isLoggable())
                    Log.d("nf_net", "Server returned HTTP error code 400 (BAD REQUEST), assume Widevine plugun is NOT recognized: " + statusCode);
                }
                catch (ConnectTimeoutException ex) {}
                catch (SocketTimeoutException ex2) {}
                catch (IOException ex3) {}
                catch (ClientProtocolException ex4) {}
            }
        }
        catch (ClientProtocolException ex5) {}
        catch (IOException ex6) {}
        catch (SocketTimeoutException o2) {
            final int statusCode = 0;
            goto Label_0403;
        }
        catch (ConnectTimeoutException o2) {
            final int statusCode = 0;
            goto Label_0336;
        }
    }
}
