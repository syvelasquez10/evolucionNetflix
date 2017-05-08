// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.android.volley.Request;
import com.netflix.mediaclient.util.net.HttpURLConnectionUtils;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.SSLSocketFactory;
import com.android.volley.toolbox.HurlStack$UrlRewriter;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.android.volley.toolbox.HurlStack;

public class ResourceHttpStack extends HurlStack
{
    private static final String TAG = "nf_service_resourcefetcher";
    private ServiceAgent$ConfigurationAgentInterface mConfiguration;
    
    public ResourceHttpStack(final ServiceAgent$ConfigurationAgentInterface configuration) {
        this.setConfiguration(configuration);
    }
    
    public ResourceHttpStack(final ServiceAgent$ConfigurationAgentInterface configuration, final HurlStack$UrlRewriter hurlStack$UrlRewriter) {
        super(hurlStack$UrlRewriter);
        this.setConfiguration(configuration);
    }
    
    public ResourceHttpStack(final ServiceAgent$ConfigurationAgentInterface configuration, final HurlStack$UrlRewriter hurlStack$UrlRewriter, final SSLSocketFactory sslSocketFactory) {
        super(hurlStack$UrlRewriter, sslSocketFactory);
        this.setConfiguration(configuration);
    }
    
    private void setConfiguration(final ServiceAgent$ConfigurationAgentInterface mConfiguration) {
        if (mConfiguration == null) {
            throw new IllegalStateException("Config can not be null!");
        }
        this.mConfiguration = mConfiguration;
    }
    
    @Override
    protected HttpURLConnection createConnection(final URL url) {
        return HttpURLConnectionUtils.createHttpURLConnection(this.mConfiguration, url);
    }
    
    @Override
    protected HttpURLConnection openConnection(final URL url, final Request<?> request) {
        final HttpURLConnection openConnection = super.openConnection(url, request);
        request.setHttpURLConnection(openConnection);
        return openConnection;
    }
}
