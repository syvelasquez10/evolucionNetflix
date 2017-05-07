// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.httpstack;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import java.util.Map;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.ProtocolVersion;
import org.apache.http.HttpResponse;
import java.util.Iterator;
import org.apache.http.impl.cookie.BasicClientCookie;
import java.util.ArrayList;
import org.apache.http.cookie.Cookie;
import java.util.List;
import java.net.URL;
import com.netflix.mediaclient.Log;
import java.io.InputStream;
import java.io.IOException;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.HttpEntity;
import java.net.HttpURLConnection;

public class HttpUrlStack implements HttpStack
{
    private static final boolean LOG_VERBOSE = false;
    private static final String TAG = "HttpUrlStack";
    
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
    
    @Override
    public void disconnect(final HttpStackConnection httpStackConnection) {
        if (!(httpStackConnection instanceof HttpUrlStackConnection)) {
            Log.e("HttpUrlStack", "urlConnection null - wrong HttpStackConnection object?");
        }
        else {
            final HttpURLConnection urlConnection = ((HttpUrlStackConnection)httpStackConnection).getUrlConnection();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
    
    @Override
    public HttpStackConnection getConnection(final String s) {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
        if (!(httpURLConnection instanceof HttpURLConnection)) {
            throw new IOException("Not an HTTP connection");
        }
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.setDoOutput(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(10000);
        return new HttpUrlStackConnection(httpURLConnection, s);
    }
    
    @Override
    public List<Cookie> getResponseCookies(final HttpStackConnection httpStackConnection) {
        if (!(httpStackConnection instanceof HttpUrlStackConnection)) {
            Log.e("HttpUrlStack", "urlConnection null - wrong HttpStackConnection object?");
            return null;
        }
        final HttpURLConnection urlConnection = ((HttpUrlStackConnection)httpStackConnection).getUrlConnection();
        final ArrayList<BasicClientCookie> list = (ArrayList<BasicClientCookie>)new ArrayList<Cookie>();
        for (final String s : urlConnection.getHeaderFields().get("Set-Cookie")) {
            list.add((Cookie)new BasicClientCookie(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=") + 1, s.length())));
        }
        return (List<Cookie>)list;
    }
    
    @Override
    public HttpResponse performGet(final HttpStackConnection httpStackConnection) {
        if (!(httpStackConnection instanceof HttpUrlStackConnection)) {
            throw new IOException("urlConnection null - wrong HttpStackConnection object?");
        }
        final HttpURLConnection urlConnection = ((HttpUrlStackConnection)httpStackConnection).getUrlConnection();
        urlConnection.setRequestProperty("Cookie", ((HttpUrlStackConnection)httpStackConnection).getCookies());
        final ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (urlConnection.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        final BasicHttpResponse basicHttpResponse = new BasicHttpResponse((StatusLine)new BasicStatusLine(protocolVersion, urlConnection.getResponseCode(), urlConnection.getResponseMessage()));
        basicHttpResponse.setEntity(entityFromConnection(urlConnection));
        for (final Map.Entry<String, List<String>> entry : urlConnection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader((Header)new BasicHeader((String)entry.getKey(), (String)entry.getValue().get(0)));
            }
        }
        return (HttpResponse)basicHttpResponse;
    }
}
