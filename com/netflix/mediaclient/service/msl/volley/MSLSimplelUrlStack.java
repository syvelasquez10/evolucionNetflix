// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.volley;

import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.ProtocolVersion;
import org.apache.http.HttpResponse;
import java.util.Map;
import java.net.HttpURLConnection;
import com.android.volley.Request;
import java.net.URL;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.HttpEntity;
import javax.net.ssl.SSLSocketFactory;
import com.android.volley.toolbox.HurlStack$UrlRewriter;
import com.netflix.mediaclient.service.msl.client.AndroidMslClient;
import com.android.volley.toolbox.HurlStack;

public class MSLSimplelUrlStack extends HurlStack
{
    protected static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected static final String TAG = "nf_msl_volley";
    protected AndroidMslClient mClient;
    protected int mTimeoutInMs;
    
    public MSLSimplelUrlStack(final AndroidMslClient androidMslClient, final int n) {
        this(androidMslClient, null, n);
    }
    
    public MSLSimplelUrlStack(final AndroidMslClient androidMslClient, final HurlStack$UrlRewriter hurlStack$UrlRewriter, final int n) {
        this(androidMslClient, hurlStack$UrlRewriter, null, n);
    }
    
    public MSLSimplelUrlStack(final AndroidMslClient mClient, final HurlStack$UrlRewriter hurlStack$UrlRewriter, final SSLSocketFactory sslSocketFactory, final int mTimeoutInMs) {
        super(hurlStack$UrlRewriter, sslSocketFactory);
        if (mClient == null) {
            throw new IllegalStateException("If client is null!");
        }
        this.mClient = mClient;
        this.mTimeoutInMs = mTimeoutInMs;
    }
    
    private static HttpEntity entityFromResponse(final byte[] array) {
        final ByteArrayEntity byteArrayEntity = new ByteArrayEntity(array);
        byteArrayEntity.setContentEncoding("identity");
        byteArrayEntity.setContentType("text/plain");
        return (HttpEntity)byteArrayEntity;
    }
    
    @Override
    protected HttpURLConnection openConnection(final URL url, final Request<?> request) {
        throw new IllegalStateException("MSL does not open connection. We should not be here. This is done through MSL core library and Volley is just used as queue manager!");
    }
    
    protected HttpResponse performPostRequest(final MSLVolleyRequest<?> mslVolleyRequest, final Map<String, String> map) {
        final byte[] execute = mslVolleyRequest.execute(map);
        final BasicHttpResponse basicHttpResponse = new BasicHttpResponse((StatusLine)new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
        basicHttpResponse.setEntity(entityFromResponse(execute));
        return (HttpResponse)basicHttpResponse;
    }
    
    @Override
    public HttpResponse performRequest(final Request<?> request, final Map<String, String> map) {
        if (request instanceof MSLVolleyRequest) {
            return this.performPostRequest((MSLVolleyRequest<?>)request, map);
        }
        return super.performRequest(request, map);
    }
}
