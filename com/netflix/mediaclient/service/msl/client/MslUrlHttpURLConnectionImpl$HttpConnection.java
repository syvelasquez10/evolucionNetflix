// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import java.net.URL;
import javax.net.ssl.SSLSocketFactory;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.msl.io.Url;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import com.netflix.mediaclient.util.net.HttpURLConnectionUtils;
import java.net.HttpURLConnection;
import com.netflix.msl.io.Url$Connection;

class MslUrlHttpURLConnectionImpl$HttpConnection implements Url$Connection
{
    private HttpURLConnection mURLConnection;
    final /* synthetic */ MslUrlHttpURLConnectionImpl this$0;
    
    public MslUrlHttpURLConnectionImpl$HttpConnection(final MslUrlHttpURLConnectionImpl this$0) {
        this.this$0 = this$0;
        this.mURLConnection = HttpURLConnectionUtils.createHttpURLConnection(this$0.mConfiguration, this$0.mUrl);
        if (this$0.mHeaders != null && this$0.mHeaders.size() > 0) {
            for (final String s : this$0.mHeaders.keySet()) {
                this.mURLConnection.addRequestProperty(s, (String)this$0.mHeaders.get(s));
            }
        }
        if (this$0.mTimeout >= 0) {
            this.mURLConnection.setConnectTimeout(this$0.mTimeout);
            this.mURLConnection.setReadTimeout(this$0.mTimeout);
        }
        this.mURLConnection.setUseCaches(false);
        this.mURLConnection.setDoInput(true);
        this.mURLConnection.setDoOutput(true);
        this.mURLConnection.setRequestMethod("POST");
        if ("https".equals(this$0.mUrl.getProtocol()) && this$0.mSslSocketFactory != null) {
            ((HttpsURLConnection)this.mURLConnection).setSSLSocketFactory(this$0.mSslSocketFactory);
        }
    }
    
    public InputStream getInputStream() {
        return this.mURLConnection.getInputStream();
    }
    
    public OutputStream getOutputStream() {
        return this.mURLConnection.getOutputStream();
    }
}
