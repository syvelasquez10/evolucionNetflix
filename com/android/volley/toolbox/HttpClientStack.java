// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpConnectionParams;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import java.util.List;
import com.android.volley.AuthFailureError;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.client.methods.HttpPost;
import com.android.volley.Request;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.HttpClient;

public class HttpClientStack implements HttpStack
{
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected final HttpClient mClient;
    
    public HttpClientStack(final HttpClient mClient) {
        this.mClient = mClient;
    }
    
    private static void addHeaders(final HttpUriRequest httpUriRequest, final Map<String, String> map) {
        for (final String s : map.keySet()) {
            httpUriRequest.setHeader(s, (String)map.get(s));
        }
    }
    
    static HttpUriRequest createHttpRequest(final Request<?> request, final Map<String, String> map) throws AuthFailureError {
        switch (request.getMethod()) {
            default: {
                throw new IllegalStateException("Unknown request method.");
            }
            case -1: {
                final byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    final HttpPost httpPost = new HttpPost(request.getUrl());
                    httpPost.addHeader("Content-Type", request.getPostBodyContentType());
                    httpPost.setEntity((HttpEntity)new ByteArrayEntity(postBody));
                    return (HttpUriRequest)httpPost;
                }
                return (HttpUriRequest)new HttpGet(request.getUrl());
            }
            case 0: {
                return (HttpUriRequest)new HttpGet(request.getUrl());
            }
            case 3: {
                return (HttpUriRequest)new HttpDelete(request.getUrl());
            }
            case 1: {
                final HttpPost httpPost2 = new HttpPost(request.getUrl());
                httpPost2.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody((HttpEntityEnclosingRequestBase)httpPost2, request);
                return (HttpUriRequest)httpPost2;
            }
            case 2: {
                final HttpPut httpPut = new HttpPut(request.getUrl());
                httpPut.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody((HttpEntityEnclosingRequestBase)httpPut, request);
                return (HttpUriRequest)httpPut;
            }
        }
    }
    
    private static List<NameValuePair> getPostParameterPairs(final Map<String, String> map) {
        final ArrayList list = new ArrayList(map.size());
        for (final String s : map.keySet()) {
            list.add(new BasicNameValuePair(s, (String)map.get(s)));
        }
        return (List<NameValuePair>)list;
    }
    
    private void modifySetCookies(final HttpResponse httpResponse) {
        final StringBuilder sb = new StringBuilder();
        final Header[] allHeaders = httpResponse.getAllHeaders();
        for (int length = allHeaders.length, i = 0; i < length; ++i) {
            final Header header = allHeaders[i];
            if (header.getName() != null && header.getName().equalsIgnoreCase("Set-Cookie")) {
                if (sb.length() > 0) {
                    sb.append("; ");
                }
                sb.append(header.getValue());
            }
        }
        if (sb.length() > 0) {
            httpResponse.removeHeaders("Set-Cookie");
            httpResponse.addHeader("Set-Cookie", sb.toString());
        }
    }
    
    private static void setEntityIfNonEmptyBody(final HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, final Request<?> request) throws AuthFailureError {
        final byte[] body = request.getBody();
        if (body != null) {
            httpEntityEnclosingRequestBase.setEntity((HttpEntity)new ByteArrayEntity(body));
        }
    }
    
    protected void onPrepareRequest(final HttpUriRequest httpUriRequest) throws IOException {
    }
    
    @Override
    public HttpResponse performRequest(final Request<?> request, final Map<String, String> map) throws IOException, AuthFailureError {
        final HttpUriRequest httpRequest = createHttpRequest(request, map);
        addHeaders(httpRequest, map);
        addHeaders(httpRequest, request.getHeaders());
        this.onPrepareRequest(httpRequest);
        final HttpParams params = httpRequest.getParams();
        final int timeoutMs = request.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, timeoutMs);
        final HttpResponse execute = this.mClient.execute(httpRequest);
        this.modifySetCookies(execute);
        return execute;
    }
}
