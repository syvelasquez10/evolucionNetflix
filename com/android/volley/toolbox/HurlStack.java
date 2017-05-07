// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import java.util.List;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.ProtocolVersion;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.IOException;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.HttpEntity;
import java.io.DataOutputStream;
import com.android.volley.Request;
import java.net.HttpURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class HurlStack implements HttpStack
{
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private final SSLSocketFactory mSslSocketFactory;
    private final HurlStack$UrlRewriter mUrlRewriter;
    
    public HurlStack() {
        this(null);
    }
    
    public HurlStack(final HurlStack$UrlRewriter hurlStack$UrlRewriter) {
        this(hurlStack$UrlRewriter, null);
    }
    
    public HurlStack(final HurlStack$UrlRewriter mUrlRewriter, final SSLSocketFactory mSslSocketFactory) {
        this.mUrlRewriter = mUrlRewriter;
        this.mSslSocketFactory = mSslSocketFactory;
    }
    
    private static void addBodyIfExists(final HttpURLConnection httpURLConnection, final Request<?> request) {
        final byte[] body = request.getBody();
        if (body != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", request.getBodyContentType());
            final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(body);
            dataOutputStream.close();
        }
    }
    
    private static HttpEntity entityFromConnection(final HttpURLConnection httpURLConnection) {
        final BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        while (true) {
            try {
                final InputStream content = httpURLConnection.getInputStream();
                basicHttpEntity.setContent(content);
                basicHttpEntity.setContentLength((long)httpURLConnection.getContentLength());
                basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
                basicHttpEntity.setContentType(httpURLConnection.getContentType());
                return (HttpEntity)basicHttpEntity;
            }
            catch (IOException ex) {
                final InputStream content = httpURLConnection.getErrorStream();
                continue;
            }
            break;
        }
    }
    
    private HttpURLConnection openConnection(final URL url, final Request<?> request) {
        final HttpURLConnection connection = this.createConnection(url);
        final int timeoutMs = request.getTimeoutMs();
        connection.setConnectTimeout(timeoutMs);
        connection.setReadTimeout(timeoutMs);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.mSslSocketFactory != null) {
            ((HttpsURLConnection)connection).setSSLSocketFactory(this.mSslSocketFactory);
        }
        return connection;
    }
    
    static void setConnectionParametersForRequest(final HttpURLConnection httpURLConnection, final Request<?> request) {
        switch (request.getMethod()) {
            default: {
                throw new IllegalStateException("Unknown method type.");
            }
            case -1: {
                final byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", request.getPostBodyContentType());
                    final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(postBody);
                    dataOutputStream.close();
                }
            }
            case 0: {
                httpURLConnection.setRequestMethod("GET");
            }
            case 3: {
                httpURLConnection.setRequestMethod("DELETE");
            }
            case 1: {
                httpURLConnection.setRequestMethod("POST");
                addBodyIfExists(httpURLConnection, request);
            }
            case 2: {
                httpURLConnection.setRequestMethod("PUT");
                addBodyIfExists(httpURLConnection, request);
            }
        }
    }
    
    protected HttpURLConnection createConnection(final URL url) {
        return (HttpURLConnection)url.openConnection();
    }
    
    @Override
    public HttpResponse performRequest(final Request<?> request, final Map<String, String> map) {
        final String url = request.getUrl();
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.putAll(request.getHeaders());
        hashMap.putAll(map);
        String rewriteUrl;
        if (this.mUrlRewriter != null) {
            if ((rewriteUrl = this.mUrlRewriter.rewriteUrl(url)) == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }
        }
        else {
            rewriteUrl = url;
        }
        final HttpURLConnection openConnection = this.openConnection(new URL(rewriteUrl), request);
        for (final String s : hashMap.keySet()) {
            openConnection.addRequestProperty(s, hashMap.get(s));
        }
        setConnectionParametersForRequest(openConnection, request);
        final ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (openConnection.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        final BasicHttpResponse basicHttpResponse = new BasicHttpResponse((StatusLine)new BasicStatusLine(protocolVersion, openConnection.getResponseCode(), openConnection.getResponseMessage()));
        basicHttpResponse.setEntity(entityFromConnection(openConnection));
        for (final Map.Entry<String, List<String>> entry : openConnection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                if (entry.getKey().equalsIgnoreCase("Set-Cookie")) {
                    final List<String> list = entry.getValue();
                    final StringBuilder sb = new StringBuilder();
                    final Iterator<String> iterator3 = list.iterator();
                    int n = 0;
                    while (iterator3.hasNext()) {
                        final String s2 = iterator3.next();
                        if (n > 0) {
                            sb.append("; ");
                        }
                        sb.append(s2);
                        ++n;
                    }
                    basicHttpResponse.addHeader((Header)new BasicHeader((String)entry.getKey(), sb.toString()));
                }
                else {
                    basicHttpResponse.addHeader((Header)new BasicHeader((String)entry.getKey(), (String)entry.getValue().get(0)));
                }
            }
        }
        return (HttpResponse)basicHttpResponse;
    }
}
