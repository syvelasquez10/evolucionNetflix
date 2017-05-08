// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import java.io.OutputStream;
import java.io.InputStream;
import javax.net.ssl.HttpsURLConnection;
import com.netflix.mediaclient.util.net.HttpURLConnectionUtils;
import java.net.HttpURLConnection;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.msl.io.Url$Connection;
import java.util.ArrayList;
import java.net.URL;
import javax.net.ssl.SSLSocketFactory;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.msl.io.Url;

public class MslUrlHttpURLConnectionImpl implements Url
{
    private static final String TAG = "nf_msl_net";
    private ServiceAgent$ConfigurationAgentInterface mConfiguration;
    private List<MslUrlHttpURLConnectionImpl$HttpConnection> mConnections;
    private Map<String, String> mHeaders;
    private SSLSocketFactory mSslSocketFactory;
    private int mTimeout;
    private URL mUrl;
    
    public MslUrlHttpURLConnectionImpl(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final String s) {
        this(serviceAgent$ConfigurationAgentInterface, new URL(s));
    }
    
    public MslUrlHttpURLConnectionImpl(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final URL url) {
        this(serviceAgent$ConfigurationAgentInterface, url, null);
    }
    
    public MslUrlHttpURLConnectionImpl(final ServiceAgent$ConfigurationAgentInterface mConfiguration, final URL mUrl, final Map<String, String> mHeaders) {
        this.mTimeout = -1;
        this.mConnections = new ArrayList<MslUrlHttpURLConnectionImpl$HttpConnection>();
        if (mConfiguration == null) {
            throw new IllegalArgumentException("ConfigurationAgent is null!");
        }
        if (mUrl == null) {
            throw new IllegalArgumentException("URL is null!");
        }
        this.mUrl = mUrl;
        this.mConfiguration = mConfiguration;
        this.mHeaders = mHeaders;
    }
    
    public Url$Connection openConnection() {
        synchronized (this.mConnections) {
            final MslUrlHttpURLConnectionImpl$HttpConnection mslUrlHttpURLConnectionImpl$HttpConnection = new MslUrlHttpURLConnectionImpl$HttpConnection(this);
            Log.d("nf_msl_net", "MSL opened connection, total count on MSL connections on this URL %d", this.mConnections.size());
            this.mConnections.add(mslUrlHttpURLConnectionImpl$HttpConnection);
            return (Url$Connection)mslUrlHttpURLConnectionImpl$HttpConnection;
        }
    }
    
    public void release() {
        while (true) {
            while (true) {
                MslUrlHttpURLConnectionImpl$HttpConnection mslUrlHttpURLConnectionImpl$HttpConnection = null;
                Label_0086: {
                    synchronized (this.mConnections) {
                        Log.d("nf_msl_net", "Releasing %d MSL opened connection(s)...", this.mConnections.size());
                        final Iterator<MslUrlHttpURLConnectionImpl$HttpConnection> iterator = this.mConnections.iterator();
                        while (iterator.hasNext()) {
                            mslUrlHttpURLConnectionImpl$HttpConnection = iterator.next();
                            if (mslUrlHttpURLConnectionImpl$HttpConnection.mURLConnection != null) {
                                break Label_0086;
                            }
                            Log.e("nf_msl_net", "Missing HTTP connection! Unable to disconnect!");
                        }
                        break;
                    }
                }
                mslUrlHttpURLConnectionImpl$HttpConnection.mURLConnection.disconnect();
                continue;
            }
        }
        this.mConnections.clear();
        Log.d("nf_msl_net", "Releasing MSL opened connection(s) done.");
    }
    // monitorexit(list)
    
    public void setSslSocketFactory(final SSLSocketFactory mSslSocketFactory) {
        this.mSslSocketFactory = mSslSocketFactory;
    }
    
    public void setTimeout(final int mTimeout) {
        this.mTimeout = mTimeout;
    }
}
