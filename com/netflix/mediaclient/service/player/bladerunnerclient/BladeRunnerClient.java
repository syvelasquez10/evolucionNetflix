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
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import java.util.Date;
import java.text.DateFormat;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class BladeRunnerClient implements IBladeRunnerClient
{
    private static String TAG;
    private ServiceAgent$ConfigurationAgentInterface config;
    private Context context;
    private ServiceAgent$UserAgentInterface mUser;
    private IMSLClient mslClient;
    
    static {
        BladeRunnerClient.TAG = "nf_bladerunnerClient";
    }
    
    public BladeRunnerClient(final Context context, final IMSLClient mslClient, final ServiceAgent$ConfigurationAgentInterface config, final ServiceAgent$UserAgentInterface mUser) {
        this.context = context;
        this.mslClient = mslClient;
        this.config = config;
        this.mUser = mUser;
    }
    
    private String buildLinkRequestParam(final String s) {
        Log.d(BladeRunnerClient.TAG, "building param for link %s", s);
        return new LinksParamBuilder().setLink(this.getLinkJson(s)).build();
    }
    
    private String buildYearlyWarningMessage(final Context context, final long n, final Locale locale) {
        final String string = context.getString(2131296882, new Object[] { 1, DateFormat.getDateInstance(1, locale).format(new Date(n)) });
        Log.d(BladeRunnerClient.TAG, "yearly warning msg: %s", string);
        return string;
    }
    
    private Status buildYearlyWarningStatusCode(final Context context, final long n, final Locale locale) {
        final String buildYearlyWarningMessage = this.buildYearlyWarningMessage(context, n, locale);
        final NetflixStatus netflixStatus = new NetflixStatus(StatusCode.DL_WARNING_DL_N_TIMES_BEFORE_DATE, 0);
        netflixStatus.setMessage(buildYearlyWarningMessage);
        return netflixStatus;
    }
    
    private JSONObject getLinkJson(final String s) {
        if (s == null) {
            return null;
        }
        try {
            return new JSONObject(s);
        }
        catch (JSONException ex) {
            Log.d(BladeRunnerClient.TAG, "error parsing link %s", s);
            return null;
        }
    }
    
    @Override
    public void activateOfflineLicense(final String s) {
        if (StringUtils.isEmpty(s)) {
            return;
        }
        Log.d(BladeRunnerClient.TAG, "activateOfflineLicense");
        final BladeRunnerClient$2 bladeRunnerClient$2 = new BladeRunnerClient$2(this);
        Log.d(BladeRunnerClient.TAG, "activating license");
        this.mslClient.addRequest(new FetchLinkRequest(this.buildLinkRequestParam(s), bladeRunnerClient$2));
    }
    
    @Override
    public void deactivateOfflineLicense(final String s, final String s2, final boolean b, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        this.mslClient.addRequest(new OfflineLicenseDeactivate(new DeactivateRequestParamBuilder(b).setLink(this.getLinkJson(s)).build(), bladeRunnerWebCallback));
    }
    
    @Override
    public void fetchLicense(final BaseLicenseContext baseLicenseContext, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        this.mslClient.addRequest(new FetchLicenseRequest(FetchLicenseRequest$LicenseReqType.STREAMING, new LicenseRequestParamBuilder(this.mUser).buildBaseParams(baseLicenseContext.getLicenseLink(), baseLicenseContext.getBase64Challenge()).build(), false, bladeRunnerWebCallback));
    }
    
    @Override
    public void fetchManifests(final String[] array, final IBladeRunnerClient$ManifestRequestFlavor bladeRunnerClient$ManifestRequestFlavor, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        this.mslClient.addRequest(new FetchManifestsRequest(new ManifestRequestParamBuilder(this.config, this.mUser).uiversion(DeviceUtils.getSoftwareVersion(this.context)).flavor(bladeRunnerClient$ManifestRequestFlavor).playableIds(array).build(), bladeRunnerWebCallback));
    }
    
    public void fetchOfflineLicense(String build, final String s, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        build = new LicenseRequestParamBuilder(this.mUser).buildBaseParams(this.getLinkJson(build), s).build();
        final BladeRunnerClient$1 bladeRunnerClient$1 = new BladeRunnerClient$1(this, bladeRunnerWebCallback);
        Log.d(BladeRunnerClient.TAG, "fetching offline license");
        this.mslClient.addRequest(new FetchLicenseRequest(FetchLicenseRequest$LicenseReqType.OFFLINE, build, false, bladeRunnerClient$1));
    }
    
    @Override
    public void fetchOfflineManifest(final String s, final String s2, final String s3, final DownloadVideoQuality downloadVideoQuality, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        this.mslClient.addRequest(new FetchManifestsRequest(new ManifestRequestParamBuilder(this.config, this.mUser).uiversion(DeviceUtils.getSoftwareVersion(this.context)).type(IBladeRunnerClient$ManifestType.OFFLINE).downaloadVideoQuality(downloadVideoQuality).playableIds(new String[] { s }).setOfflineIds(s2, s3).build(), bladeRunnerWebCallback));
    }
    
    @Override
    public void refreshOfflineLicense(final IBladeRunnerClient$OfflineRefreshInvoke invokeLocation, final String s, final String s2, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        final String build = new LicenseRequestParamBuilder(this.mUser).buildBaseParams(this.getLinkJson(s), s2).setInvokeLocation(invokeLocation).build();
        final BladeRunnerClient$3 bladeRunnerClient$3 = new BladeRunnerClient$3(this, bladeRunnerWebCallback);
        Log.d(BladeRunnerClient.TAG, "refreshing offline license");
        this.mslClient.addRequest(new FetchLicenseRequest(FetchLicenseRequest$LicenseReqType.OFFLINE, build, true, bladeRunnerClient$3));
    }
    
    @Override
    public void refreshOfflineManifest(final String s, final String s2, final String s3, final DownloadVideoQuality downloadVideoQuality, final NfManifest nfManifest, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        Log.d(BladeRunnerClient.TAG, "refreshOfflineManifest");
        if (nfManifest == null || nfManifest.getLinks() == null) {
            this.fetchOfflineManifest(s, s2, s3, downloadVideoQuality, bladeRunnerWebCallback);
            return;
        }
        this.mslClient.addRequest(new RefreshOfflineManifestRequest(new ManifestRequestParamBuilder(this.config, this.mUser).uiversion(DeviceUtils.getSoftwareVersion(this.context)).type(IBladeRunnerClient$ManifestType.OFFLINE).downaloadVideoQuality(downloadVideoQuality).playableIds(new String[] { s }).setOfflineIds(s2, s3).build(), nfManifest, bladeRunnerWebCallback));
    }
    
    @Override
    public void syncActiveLicensesToServer(final List<String> deactiveLinks, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        this.mslClient.addRequest(new OfflineLicenseSyncRequest(new SyncActiveLicensesParamBuilder().setDeactiveLinks(deactiveLinks).build(), bladeRunnerWebCallback));
    }
}
