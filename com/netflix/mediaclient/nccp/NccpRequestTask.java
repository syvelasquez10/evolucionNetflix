// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.params.HttpConnectionParams;
import com.netflix.mediaclient.Log;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.HttpVersion;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.client.HttpClient;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;

public class NccpRequestTask extends AsyncTaskCompat<Void, Void, NccpResponse>
{
    public static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
    public static final int DEFAULT_SOCKET_TIMEOUT = 3000;
    private final String TAG;
    private int connectionTimeout;
    private int socketTimeout;
    private NccpTransaction transaction;
    
    public NccpRequestTask(final NccpTransaction transaction) {
        this.TAG = "nf_nccp";
        this.connectionTimeout = 5000;
        this.socketTimeout = 3000;
        this.transaction = transaction;
    }
    
    public NccpRequestTask(final NccpTransaction nccpTransaction, final int connectionTimeout, final int socketTimeout) {
        this(nccpTransaction);
        this.connectionTimeout = connectionTimeout;
        this.socketTimeout = socketTimeout;
    }
    
    private HttpClient getNewHttpClient() {
        try {
            final SSLSocketFactory sslSocketFactory = new SSLSocketFactory(NccpKeyStore.getInstance());
            sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            final BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion((HttpParams)basicHttpParams, (ProtocolVersion)HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset((HttpParams)basicHttpParams, "UTF-8");
            final SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", (SocketFactory)sslSocketFactory, 443));
            return (HttpClient)new DefaultHttpClient((ClientConnectionManager)new ThreadSafeClientConnManager((HttpParams)basicHttpParams, schemeRegistry), (HttpParams)basicHttpParams);
        }
        catch (Exception ex) {
            Log.e("nf_nccp", "Failed to initialize http client");
            return (HttpClient)new DefaultHttpClient();
        }
    }
    
    @Override
    protected NccpResponse doInBackground(final Void... array) {
        Log.d("nf_nccp", "Starting nccp call...");
        final HttpParams params = this.getNewHttpClient().getParams();
        HttpConnectionParams.setConnectionTimeout(params, this.connectionTimeout);
        HttpConnectionParams.setSoTimeout(params, this.socketTimeout);
        params.setParameter("http.useragent", (Object)this.transaction.getUserAgent());
        NccpResponse nccpResponse;
        try {
            final String requestBody = this.transaction.getRequestBody();
            final HttpPost httpPost = new HttpPost(this.transaction.getEndpoint());
            httpPost.setEntity((HttpEntity)new StringEntity(requestBody, "UTF-8"));
            if (Log.isLoggable()) {
                Log.d("nf_nccp", "PostRequest:" + httpPost.getRequestLine());
                Log.d("nf_nccp", "Endpoint " + this.transaction.getEndpoint());
                Log.d("nf_nccp", "Content " + requestBody);
            }
            final List<RequestParameter> requestHeaders = this.transaction.getRequestHeaders();
            if (requestHeaders != null) {
                for (final RequestParameter requestParameter : requestHeaders) {
                    httpPost.setHeader(requestParameter.name, requestParameter.value);
                }
                goto Label_0308;
            }
            goto Label_0308;
        }
        catch (ConnectTimeoutException ex) {
            Log.e("nf_nccp", "Connection timeout", (Throwable)ex);
            nccpResponse = this.transaction.processError((Throwable)ex);
        }
        catch (SocketTimeoutException ex2) {
            Log.e("nf_nccp", "Socket timeout", ex2);
            nccpResponse = this.transaction.processError(ex2);
            goto Label_0266;
        }
        catch (ClientProtocolException ex3) {
            Log.e("nf_nccp", "Request protocol failed", (Throwable)ex3);
            nccpResponse = this.transaction.processError((Throwable)ex3);
            goto Label_0266;
        }
        catch (IOException ex4) {
            Log.e("nf_nccp", "Request IO failed ", ex4);
            nccpResponse = this.transaction.processError(ex4);
            goto Label_0266;
        }
        Log.w("nf_nccp", "Response handler is null!");
        return nccpResponse;
    }
}
