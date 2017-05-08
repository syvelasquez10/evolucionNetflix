// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import com.netflix.msl.util.Base64;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$LicenseRequestFlavor;

public abstract class BaseLicenseContext
{
    private static final String TAG = "nf_baseLicenseContext";
    private String drmLicenseContextId;
    private byte[] licenseData;
    private String mBase64Challenge;
    private byte[] mDrmHeader;
    private IBladeRunnerClient$LicenseRequestFlavor mFlavor;
    private JSONObject mLicenseLink;
    private NfDrmManagerInterface$LicenseType mLicenseType;
    private String providerSessionToken;
    
    public BaseLicenseContext(final NfDrmManagerInterface$LicenseType nfDrmManagerInterface$LicenseType, final byte[] mDrmHeader, final JSONObject mLicenseLink) {
        this.setmLicenseType(nfDrmManagerInterface$LicenseType);
        this.mDrmHeader = mDrmHeader;
        this.mLicenseLink = mLicenseLink;
    }
    
    public JSONObject addLicenseReponse(final JSONObject jsonObject) {
        Log.d("nf_baseLicenseContext", "parsing license response start.");
        this.drmLicenseContextId = jsonObject.optString("drmLicenseContextId");
        this.providerSessionToken = jsonObject.optString("providerSessionToken");
        this.licenseData = Base64.decode(jsonObject.optString("licenseResponseBase64"));
        Log.d("nf_baseLicenseContext", "parsing license response end.");
        return jsonObject;
    }
    
    public String getBase64Challenge() {
        return this.mBase64Challenge;
    }
    
    public byte[] getDrmHeader() {
        return this.mDrmHeader;
    }
    
    public byte[] getLicenseData() {
        return this.licenseData;
    }
    
    public JSONObject getLicenseLink() {
        return this.mLicenseLink;
    }
    
    public NfDrmManagerInterface$LicenseType getLicenseType() {
        return this.mLicenseType;
    }
    
    public NfDrmManagerInterface$LicenseType getmLicenseType() {
        return this.mLicenseType;
    }
    
    public void setChallenge(final byte[] array) {
        this.mBase64Challenge = Base64.encode(array);
    }
    
    public void setmLicenseType(final NfDrmManagerInterface$LicenseType mLicenseType) {
        this.mLicenseType = mLicenseType;
        if (mLicenseType == NfDrmManagerInterface$LicenseType.LICENSE_TYPE_STANDARD) {
            this.mFlavor = IBladeRunnerClient$LicenseRequestFlavor.STANDARD;
            return;
        }
        if (mLicenseType == NfDrmManagerInterface$LicenseType.LICENSE_TYPE_LDL) {
            this.mFlavor = IBladeRunnerClient$LicenseRequestFlavor.LIMITED;
            return;
        }
        if (mLicenseType == NfDrmManagerInterface$LicenseType.LICENSE_TYPE_OFFLINE) {
            this.mFlavor = IBladeRunnerClient$LicenseRequestFlavor.OFFLINE;
            return;
        }
        this.mFlavor = IBladeRunnerClient$LicenseRequestFlavor.UNKNOWN;
    }
}
