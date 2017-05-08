// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl;

import com.netflix.mediaclient.service.configuration.crypto.CryptoManager$DrmReadyCallback;
import com.netflix.mediaclient.servicemgr.INetflixService;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import com.android.volley.Request;
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.servicemgr.IMSLClient$NetworkRequestInspector;
import com.netflix.msl.MslConstants$ResponseCode;
import com.netflix.msl.msg.ErrorHeader;
import com.netflix.mediaclient.service.msl.client.MslErrorException;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.android.volley.Network;
import com.android.volley.Cache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.BasicNetwork;
import com.netflix.mediaclient.service.msl.volley.MSLSimplelUrlStack;
import com.android.volley.toolbox.NoCache;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.android.org.json.JSONObject;
import com.netflix.mediaclient.android.app.Status;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.msl.client.AndroidMslClient;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;

class MSLAgent$2 implements Runnable
{
    final /* synthetic */ MSLAgent this$0;
    
    MSLAgent$2(final MSLAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        try {
            this.this$0.doExecuteAppBoot();
        }
        catch (Throwable t) {
            Log.e("nf_msl", t, "Failed to execute AppBoot asynchronously", new Object[0]);
        }
    }
}
