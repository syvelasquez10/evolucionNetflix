// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import java.util.Arrays;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData$LicenseData;
import com.netflix.mediaclient.Log;
import com.netflix.msl.util.Base64;
import org.json.JSONObject;

public class OfflineLicenseResponse
{
    private static String LINK_ACTIVATE;
    private static String LINK_ACTIVATE_AND_REFRESH;
    private static String LINK_COMPLETE_AND_DOWNLOAD;
    private static String LINK_DEACTIVATE;
    public static String LINK_REFRESH;
    private static String TAG;
    private String drmLicenseContextId;
    private byte[] licenseData;
    private long mExpirationTimeInMs;
    private byte[] mKeySetId;
    public String mLinkActivate;
    public String mLinkDeactivate;
    public String mLinkDownloadAndActivate;
    public String mLinkRefresh;
    private long mPlayWindowResetLimit;
    private long mPlayableWindowInHour;
    private boolean mPwResettable;
    private long mRefreshLicenseTimeStamp;
    private boolean mShouldRefresh;
    private boolean mShouldRefreshByTimestamp;
    private boolean mShouldUsePlayWindowLimits;
    private long mViewingWindow;
    private String providerSessionToken;
    
    static {
        OfflineLicenseResponse.TAG = "bladerunnerOfflineLicenseResponse";
        OfflineLicenseResponse.LINK_COMPLETE_AND_DOWNLOAD = "completeDownloadAndActivate";
        OfflineLicenseResponse.LINK_ACTIVATE_AND_REFRESH = "activateAndRefresh";
        OfflineLicenseResponse.LINK_ACTIVATE = "activate";
        OfflineLicenseResponse.LINK_DEACTIVATE = "deactivate";
        OfflineLicenseResponse.LINK_REFRESH = "refresh";
    }
    
    public OfflineLicenseResponse(final JSONObject jsonObject) {
        this.parseOfflineParams(jsonObject);
    }
    
    public static String getLink(JSONObject optJSONObject, final String s) {
        if (optJSONObject != null) {
            optJSONObject = optJSONObject.optJSONObject(s);
            if (optJSONObject != null) {
                return optJSONObject.toString();
            }
        }
        return null;
    }
    
    private void parseOfflineParams(JSONObject optJSONObject) {
        if (optJSONObject != null) {
            this.drmLicenseContextId = optJSONObject.optString("drmLicenseContextId");
            this.providerSessionToken = optJSONObject.optString("providerSessionToken");
            this.licenseData = Base64.decode(optJSONObject.optString("licenseResponseBase64"));
            Log.d(OfflineLicenseResponse.TAG, "parsing license response end.");
            this.mExpirationTimeInMs = optJSONObject.optLong("absoluteExpirationTimeMillis");
            this.mViewingWindow = optJSONObject.optLong("viewingWindow");
            if (this.mViewingWindow <= 0L) {
                this.mViewingWindow = Long.MAX_VALUE;
            }
            final JSONObject optJSONObject2 = optJSONObject.optJSONObject("playbackLimitations");
            if (optJSONObject2 != null) {
                this.mShouldUsePlayWindowLimits = optJSONObject2.optBoolean("APPLYPLAYWINDOW");
                this.mPlayableWindowInHour = optJSONObject2.optLong("PLAYWINDOWHOURS");
                this.mPwResettable = optJSONObject2.optBoolean("ALLOWPLAYWINDOWRESET");
                this.mPlayWindowResetLimit = optJSONObject2.optLong("PLAYWINDOWRESETLIMIT");
                this.mShouldRefresh = optJSONObject2.optBoolean("ALLOWAUTOLICENSEREFRESH");
                this.mShouldRefreshByTimestamp = optJSONObject2.optBoolean("APPLYLICENSEREFRESHLIMIT");
                this.mRefreshLicenseTimeStamp = optJSONObject2.optLong("REFRESHLICENSETIMESTAMP");
            }
            optJSONObject = optJSONObject.optJSONObject("links");
            if (optJSONObject != null) {
                this.mLinkActivate = getLink(optJSONObject, OfflineLicenseResponse.LINK_ACTIVATE);
                this.mLinkDeactivate = getLink(optJSONObject, OfflineLicenseResponse.LINK_DEACTIVATE);
                this.mLinkDownloadAndActivate = getLink(optJSONObject, OfflineLicenseResponse.LINK_COMPLETE_AND_DOWNLOAD);
                this.mLinkRefresh = getLink(optJSONObject, OfflineLicenseResponse.LINK_ACTIVATE_AND_REFRESH);
            }
        }
    }
    
    public byte[] getLicenseData() {
        return this.licenseData;
    }
    
    public void populateLicenseData(final OfflinePlayablePersistentData$LicenseData offlinePlayablePersistentData$LicenseData) {
        offlinePlayablePersistentData$LicenseData.mExpirationTimeInMs = this.mExpirationTimeInMs;
        offlinePlayablePersistentData$LicenseData.mViewingWindow = this.mViewingWindow;
        offlinePlayablePersistentData$LicenseData.mKeySetId = OfflineUtils.keySetIdToString(this.mKeySetId);
        offlinePlayablePersistentData$LicenseData.mPlayableWindowInHour = this.mPlayableWindowInHour;
        offlinePlayablePersistentData$LicenseData.mPwResettable = this.mPwResettable;
        offlinePlayablePersistentData$LicenseData.mShouldRefresh = this.mShouldRefresh;
        offlinePlayablePersistentData$LicenseData.mPlayWindowResetLimit = this.mPlayWindowResetLimit;
        offlinePlayablePersistentData$LicenseData.mRefreshLicenseTimestamp = this.mRefreshLicenseTimeStamp;
        offlinePlayablePersistentData$LicenseData.mShouldUsePlayWindowLimits = this.mShouldUsePlayWindowLimits;
        offlinePlayablePersistentData$LicenseData.mShouldRefreshByTimestamp = this.mShouldRefreshByTimestamp;
        offlinePlayablePersistentData$LicenseData.mLinkDeactivate = this.mLinkDeactivate;
        offlinePlayablePersistentData$LicenseData.mLinkDownloadAndActivate = this.mLinkDownloadAndActivate;
        offlinePlayablePersistentData$LicenseData.mLinkRefresh = this.mLinkRefresh;
    }
    
    public void setKeySetId(final byte[] mKeySetId) {
        this.mKeySetId = mKeySetId;
    }
    
    @Override
    public String toString() {
        return "OfflineLicenseResponse{mExpirationTimeInMs=" + this.mExpirationTimeInMs + ", mPlayableWindowInHour=" + this.mPlayableWindowInHour + ", mPlayWindowResetLimit=" + this.mPlayWindowResetLimit + ", mRefreshLicenseTimeStamp=" + this.mRefreshLicenseTimeStamp + ", mShouldUsePlayWindowLimits=" + this.mShouldUsePlayWindowLimits + ", mPwResettable=" + this.mPwResettable + ", mShouldRefresh=" + this.mShouldRefresh + ", mShouldRefreshByTimestamp=" + this.mShouldRefreshByTimestamp + ", mViewingWindow=" + this.mViewingWindow + ", mKeySetId=" + Arrays.toString(this.mKeySetId) + ", mLinkActivate='" + this.mLinkActivate + '\'' + ", mLinkDownloadAndActivate='" + this.mLinkDownloadAndActivate + '\'' + ", mLinkDeactivate='" + this.mLinkDeactivate + '\'' + ", mLinkRefresh='" + this.mLinkRefresh + '\'' + ", drmLicenseContextId='" + this.drmLicenseContextId + '\'' + ", providerSessionToken='" + this.providerSessionToken + '\'' + '}';
    }
}
