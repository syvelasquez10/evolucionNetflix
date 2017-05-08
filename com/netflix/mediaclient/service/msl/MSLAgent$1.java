// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl;

import com.netflix.mediaclient.servicemgr.INetflixService;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import com.android.volley.Request;
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.servicemgr.IMSLClient$NetworkRequestInspector;
import com.netflix.msl.MslConstants$ResponseCode;
import com.netflix.msl.msg.ErrorHeader;
import com.netflix.mediaclient.service.msl.client.MslErrorException;
import com.android.volley.Network;
import com.android.volley.Cache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.BasicNetwork;
import com.netflix.mediaclient.service.msl.volley.MSLSimplelUrlStack;
import com.android.volley.toolbox.NoCache;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.android.org.json.JSONObject;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.msl.client.AndroidMslClient;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManager$DrmReadyCallback;

class MSLAgent$1 implements CryptoManager$DrmReadyCallback
{
    final /* synthetic */ MSLAgent this$0;
    
    MSLAgent$1(final MSLAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void drmError(final Status status) {
        Log.e("nf_msl", "Failed to create MSL, disable it! Status" + status);
        ErrorLoggingManager.logHandledException("MSL_ERROR_1: Failed to create MSL, disabled. Status code" + status.getStatusCode().name());
        if (status != CommonStatus.MSL_LEGACY_CRYPTO) {}
        this.this$0.mEnabled = false;
        this.this$0.initCompleted(CommonStatus.OK);
        Log.d("nf_msl", "MSLAgent::doInit done.");
    }
    
    @Override
    public void drmReady() {
        Log.d("nf_msl", "Crypto is created, MSL is ready to proceed. Enable it!");
        this.this$0.mEnabled = true;
        this.this$0.initializeMsl();
    }
    
    @Override
    public void drmResoureReclaimed() {
    }
}
