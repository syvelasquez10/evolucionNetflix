// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import java.util.List;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.RefreshOfflineManifestRequest;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchManifestsRequest;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchLicenseRequest;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchLicenseRequest$LicenseReqType;
import com.netflix.mediaclient.service.player.drm.BaseLicenseContext;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchDownloadComplete;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.OfflineLicenseDeactivate;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchLinkRequest;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;

class BladeRunnerClient$1 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ BladeRunnerClient this$0;
    final /* synthetic */ BladeRunnerWebCallback val$callback;
    
    BladeRunnerClient$1(final BladeRunnerClient this$0, final BladeRunnerWebCallback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onOfflineLicenseFetched(final OfflineLicenseResponse offlineLicenseResponse, final Status status) {
        super.onOfflineLicenseFetched(offlineLicenseResponse, status);
        Log.d(BladeRunnerClient.TAG, " license fetched status: %s", status);
        this.val$callback.onOfflineLicenseFetched(offlineLicenseResponse, status);
        if (status.isSucces()) {
            this.this$0.activateLicense(offlineLicenseResponse);
        }
    }
}
