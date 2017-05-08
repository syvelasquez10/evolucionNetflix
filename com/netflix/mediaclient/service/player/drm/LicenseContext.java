// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import org.json.JSONObject;

public class LicenseContext extends BaseLicenseContext
{
    private static final String TAG = "NfPlayerDrmManager";
    private String mAppId;
    private JSONObject mSessionParams;
    private int mStartPosition;
    private String mTrackId;
    private String mXid;
    
    public LicenseContext(final String mTrackId, final String mXid, final int mStartPosition, final NfDrmManagerInterface$LicenseType nfDrmManagerInterface$LicenseType, final byte[] array, final JSONObject mSessionParams, final JSONObject jsonObject) {
        super(nfDrmManagerInterface$LicenseType, array, jsonObject);
        this.mTrackId = mTrackId;
        this.mAppId = "samurai";
        this.mXid = mXid;
        this.mStartPosition = mStartPosition;
        this.mSessionParams = mSessionParams;
    }
    
    public String getAppId() {
        return this.mAppId;
    }
    
    public JSONObject getSessionParams() {
        return this.mSessionParams;
    }
    
    public int getStartPosition() {
        return this.mStartPosition;
    }
    
    public String getTrackId() {
        return this.mTrackId;
    }
    
    public String getXid() {
        return this.mXid;
    }
}
