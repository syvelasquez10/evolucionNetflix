// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.httpstack;

import java.net.URISyntaxException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpGet;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;
import java.util.List;
import java.io.IOException;
import org.apache.http.params.HttpParams;
import org.apache.http.HttpVersion;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.impl.client.DefaultHttpClient;
import com.netflix.mediaclient.Log;

public class HttpApacheStack implements HttpStack
{
    private static final String TAG = "HttpApacheStack";
    
    @Override
    public void disconnect(final HttpStackConnection httpStackConnection) {
        if (!(httpStackConnection instanceof HttpApacheStackConnection)) {
            Log.e("HttpApacheStack", "wrong HttpStackConnection object?");
        }
        else {
            final DefaultHttpClient defaultHttpClient = ((HttpApacheStackConnection)httpStackConnection).getDefaultHttpClient();
            if (defaultHttpClient != null) {
                defaultHttpClient.getConnectionManager().shutdown();
            }
        }
    }
    
    @Override
    public HttpStackConnection getConnection(final String s) throws IOException {
        final BasicHttpParams basicHttpParams = new BasicHttpParams();
        ((HttpParams)basicHttpParams).setParameter("http.protocol.version", (Object)HttpVersion.HTTP_1_1);
        ((HttpParams)basicHttpParams).setParameter("http.connection.timeout", (Object)15000);
        ((HttpParams)basicHttpParams).setParameter("http.socket.timeout", (Object)10000);
        return new HttpApacheStackConnection((HttpParams)basicHttpParams, s);
    }
    
    @Override
    public List<Cookie> getResponseCookies(final HttpStackConnection httpStackConnection) {
        if (!(httpStackConnection instanceof HttpApacheStackConnection)) {
            Log.e("HttpApacheStack", "wrong HttpStackConnection object?");
            return null;
        }
        return (List<Cookie>)((HttpApacheStackConnection)httpStackConnection).getDefaultHttpClient().getCookieStore().getCookies();
    }
    
    @Override
    public HttpResponse performGet(final HttpStackConnection httpStackConnection) throws IOException {
        if (!(httpStackConnection instanceof HttpApacheStackConnection)) {
            throw new IOException("defaultConnection null - wrong HttpStackConnection object?");
        }
        final HttpApacheStackConnection httpApacheStackConnection = (HttpApacheStackConnection)httpStackConnection;
        final DefaultHttpClient defaultHttpClient = new DefaultHttpClient(httpApacheStackConnection.getHttpParams());
        httpApacheStackConnection.setDefaultHttpClient(defaultHttpClient);
        try {
            return defaultHttpClient.execute((HttpUriRequest)new HttpGet(new URI(((HttpApacheStackConnection)httpStackConnection).getUriString())), httpApacheStackConnection.getLocalHttpContext());
        }
        catch (URISyntaxException ex) {
            Log.e("HttpApacheStack", "Error in building uri");
            return null;
        }
    }
}
