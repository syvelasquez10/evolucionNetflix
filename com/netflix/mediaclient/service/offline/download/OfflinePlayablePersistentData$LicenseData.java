// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.google.gson.annotations.SerializedName;

public class OfflinePlayablePersistentData$LicenseData
{
    @SerializedName("expirationTime")
    public long mExpirationTimeInMs;
    @SerializedName("keySetId")
    public String mKeySetId;
    @SerializedName("licenseDate")
    public long mLicenseDate;
    @SerializedName("linkDeactivate")
    public String mLinkDeactivate;
    @SerializedName("linkDownloadAndActivate")
    public String mLinkDownloadAndActivate;
    @SerializedName("linkRefresh")
    public String mLinkRefresh;
    @SerializedName("playWindowResetLimit")
    public long mPlayWindowResetLimit;
    @SerializedName("playableWindow")
    public long mPlayableWindowInHour;
    @SerializedName("resettable")
    public boolean mPwResettable;
    @SerializedName("refreshLicenseTimestamp")
    public long mRefreshLicenseTimestamp;
    @SerializedName("shouldRefresh")
    public boolean mShouldRefresh;
    @SerializedName("mShouldRefreshByTimestamp")
    public boolean mShouldRefreshByTimestamp;
    @SerializedName("shouldUsePlayWindowLimits")
    public boolean mShouldUsePlayWindowLimits;
    @SerializedName("viewingWindow")
    public long mViewingWindow;
    final /* synthetic */ OfflinePlayablePersistentData this$0;
    
    public OfflinePlayablePersistentData$LicenseData(final OfflinePlayablePersistentData this$0) {
        this.this$0 = this$0;
    }
}
