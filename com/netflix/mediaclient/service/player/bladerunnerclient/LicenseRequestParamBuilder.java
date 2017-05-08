// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import com.netflix.mediaclient.Log;
import java.util.Collection;
import org.json.JSONArray;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import org.json.JSONObject;

public class LicenseRequestParamBuilder
{
    private static final String TAG = "nf_msl_volley_FetchLicenseRequest";
    String mBase64Challenge;
    boolean mForceDeviceActivate;
    JSONObject mLicenseLink;
    private ServiceAgent$UserAgentInterface mUser;
    
    public LicenseRequestParamBuilder(final ServiceAgent$UserAgentInterface mUser) {
        this.mUser = mUser;
    }
    
    final String build() {
        final JSONObject jsonObject = new JSONObject();
        if (this.mLicenseLink == null) {
            return jsonObject.toString();
        }
        try {
            jsonObject.put("version", 2);
            jsonObject.put("method", (Object)this.mLicenseLink.optString("rel"));
            jsonObject.put("url", (Object)this.mLicenseLink.optString("href"));
            final long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("clientTime", seconds);
            jsonObject2.put("forceDeviceActivate", this.mForceDeviceActivate);
            jsonObject2.putOpt("challengeBase64", (Object)this.mBase64Challenge);
            jsonObject.putOpt("params", (Object)jsonObject2);
            final JSONObject jsonObject3 = new JSONObject();
            final String[] languages = this.mUser.getCurrentProfile().getLanguages();
            final String raw = this.mUser.getCurrentAppLocale().getRaw();
            jsonObject3.put("appselectedlanguages", (Object)new JSONArray((Collection)Arrays.asList(languages)));
            jsonObject3.put("platformselectedlanguages", (Object)new JSONArray((Collection)Arrays.asList(raw)));
            jsonObject.put("preferredlanguages", (Object)jsonObject3);
            return jsonObject.toString();
        }
        catch (Exception ex) {
            Log.e("nf_msl_volley_FetchLicenseRequest", ex, "error creating manifest params", new Object[0]);
            return jsonObject.toString();
        }
    }
    
    LicenseRequestParamBuilder buildBaseParams(final JSONObject licenseLink, final String base64Challenge) {
        return this.setLicenseLink(licenseLink).setBase64Challenge(base64Challenge);
    }
    
    LicenseRequestParamBuilder setBase64Challenge(final String mBase64Challenge) {
        this.mBase64Challenge = mBase64Challenge;
        return this;
    }
    
    LicenseRequestParamBuilder setInvokeLocation(final IBladeRunnerClient$OfflineRefreshInvoke bladeRunnerClient$OfflineRefreshInvoke) {
        this.mForceDeviceActivate = (IBladeRunnerClient$OfflineRefreshInvoke.USER.getValue() == bladeRunnerClient$OfflineRefreshInvoke.getValue());
        return this;
    }
    
    LicenseRequestParamBuilder setLicenseLink(final JSONObject mLicenseLink) {
        this.mLicenseLink = mLicenseLink;
        return this;
    }
}
