// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import com.netflix.mediaclient.service.player.bladerunnerclient.volley.OfflineLicenseSyncRequest;
import java.util.List;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.RefreshOfflineManifestRequest;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchManifestsRequest;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchLicenseRequest;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchLicenseRequest$LicenseReqType;
import com.netflix.mediaclient.service.player.drm.BaseLicenseContext;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.OfflineLicenseDeactivate;
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchLinkRequest;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;

class BladeRunnerClient$2 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ BladeRunnerClient this$0;
    
    BladeRunnerClient$2(final BladeRunnerClient this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onLinkDone(final JSONObject jsonObject, final Status status) {
        super.onLinkDone(jsonObject, status);
        Log.d(BladeRunnerClient.TAG, "license activated : res: %s", status);
    }
}
