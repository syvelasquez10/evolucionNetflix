// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.net;

import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public final class HttpURLConnectionUtils
{
    private static final String TAG = "nf_net_cronet";
    
    public static HttpURLConnection createHttpURLConnection(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final URL url) {
        if (serviceAgent$ConfigurationAgentInterface.shouldUseAndroidHttpStack()) {}
        Log.d("nf_net_cronet", "Create HttpURLConnection using Cronet");
        return CronetHttpURLConnectionFactory.getInstance(((ServiceAgent)serviceAgent$ConfigurationAgentInterface).getContext()).openConnection(url);
    }
}
