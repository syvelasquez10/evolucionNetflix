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
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.FetchLinkRequest;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;

class BladeRunnerClient$3 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ BladeRunnerClient this$0;
    final /* synthetic */ BladeRunnerWebCallback val$callback;
    
    BladeRunnerClient$3(final BladeRunnerClient this$0, final BladeRunnerWebCallback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onOfflineLicenseFetched(final OfflineLicenseResponse offlineLicenseResponse, final Status status) {
        super.onOfflineLicenseFetched(offlineLicenseResponse, status);
        Log.d(BladeRunnerClient.TAG, "refresh license fetched status: %s", status);
        Status access$300 = status;
        if (status.isSucces()) {
            access$300 = status;
            if (offlineLicenseResponse.willTiggerYearlyLimit()) {
                access$300 = this.this$0.buildYearlyWarningStatusCode(this.this$0.context, offlineLicenseResponse.mYearlyLimitExpiryDateMillis, this.this$0.mUser.getCurrentAppLocale().getLocale());
                Log.d(BladeRunnerClient.TAG, "refresh license fetched status: %s", access$300);
            }
        }
        this.val$callback.onOfflineLicenseFetched(offlineLicenseResponse, access$300);
    }
}
