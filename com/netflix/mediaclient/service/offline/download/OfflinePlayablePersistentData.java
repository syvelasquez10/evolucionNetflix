// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.StringUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class OfflinePlayablePersistentData
{
    @SerializedName("audioTracks")
    public final List<DownloadablePersistentData> mAudioDownloadablePersistentList;
    private transient DownloadState mDlStateBeforeDelete;
    @SerializedName("dlStateBeforeDelete")
    private int mDlStateBeforeDeleteValue;
    @SerializedName("dcInitTimeMs")
    public final long mDownloadContextInitTimeMs;
    @SerializedName("pcListPos")
    public final int mDownloadContextListPos;
    @SerializedName("dcRequestId")
    public final String mDownloadContextRequestId;
    @SerializedName("pcTrackId")
    public final int mDownloadContextTrackId;
    @SerializedName("pcVideoPos")
    public final int mDownloadContextVideoPos;
    private transient DownloadState mDownloadState;
    @SerializedName("downloadStateValue")
    private int mDownloadStateValue;
    @SerializedName("videoQuality")
    private final String mDownloadVideoQuality;
    @SerializedName("dxId")
    public String mDxId;
    @SerializedName("errorCode")
    public int mErrorCode;
    @SerializedName("errorString")
    public String mErrorString;
    @SerializedName("geoBlocked")
    private boolean mGeoBlocked;
    @SerializedName("licenseData")
    public final OfflinePlayablePersistentData$LicenseData mLicenseData;
    @SerializedName("manifestNetworkType")
    private int mManifestNetworkType;
    @SerializedName("oxId")
    public final String mOxId;
    @SerializedName("playStartTime")
    public long mPlayStartTime;
    @SerializedName("playableId")
    public final String mPlayableId;
    @SerializedName("profileGuidList")
    private final List<String> mProfileGuidList;
    private transient StopReason mStopReason;
    @SerializedName("stopReasonValue")
    private int mStopReasonValue;
    @SerializedName("subtitleTracks")
    public final List<DownloadablePersistentData> mSubtitleDownloadablePersistentList;
    @SerializedName("trickPlays")
    public final List<DownloadablePersistentData> mTrickPlayDownloadablePersistentList;
    @SerializedName("videoTracks")
    public final List<DownloadablePersistentData> mVideoDownloadablePersistentList;
    @SerializedName("manifestNetworkName")
    private String mWiFiSsidOrNetworkOperatorName;
    
    private OfflinePlayablePersistentData(final String mPlayableId, final PlayContext playContext, final String mOxId, final String s, final String mDownloadVideoQuality) {
        this.mProfileGuidList = new ArrayList<String>();
        this.mLicenseData = new OfflinePlayablePersistentData$LicenseData(this);
        this.mPlayableId = mPlayableId;
        this.mDownloadContextTrackId = playContext.getTrackId();
        this.mDownloadContextVideoPos = playContext.getVideoPos();
        this.mDownloadContextListPos = playContext.getListPos();
        this.mDownloadContextRequestId = playContext.getRequestId();
        this.mDownloadContextInitTimeMs = System.currentTimeMillis();
        this.mOxId = mOxId;
        this.mProfileGuidList.add(s);
        this.mDownloadVideoQuality = mDownloadVideoQuality;
        this.mDownloadState = DownloadState.Creating;
        this.mAudioDownloadablePersistentList = new ArrayList<DownloadablePersistentData>();
        this.mVideoDownloadablePersistentList = new ArrayList<DownloadablePersistentData>();
        this.mSubtitleDownloadablePersistentList = new ArrayList<DownloadablePersistentData>();
        this.mTrickPlayDownloadablePersistentList = new ArrayList<DownloadablePersistentData>();
        this.mStopReason = StopReason.WaitingToBeStarted;
        this.mStopReasonValue = this.mStopReason.getIntValue();
    }
    
    public static OfflinePlayablePersistentData createOfflineContentPersistentData(final String s, final PlayContext playContext, final String s2, final String s3, final String s4) {
        return new OfflinePlayablePersistentData(s, playContext, s2, s3, s4);
    }
    
    private void setDownloadState(final DownloadState mDownloadState) {
        this.mDownloadState = mDownloadState;
        this.mDownloadStateValue = mDownloadState.getIntValue();
    }
    
    private void setStopReason(final StopReason mStopReason) {
        this.mStopReason = mStopReason;
        this.mStopReasonValue = mStopReason.getIntValue();
    }
    
    public DownloadState getDlStateBeforeDelete() {
        if (this.mDlStateBeforeDelete == null) {
            this.mDlStateBeforeDelete = DownloadState.getStateByValue(this.mDlStateBeforeDeleteValue);
        }
        return this.mDlStateBeforeDelete;
    }
    
    public DownloadState getDownloadState() {
        if (this.mDownloadState == null) {
            this.mDownloadState = DownloadState.getStateByValue(this.mDownloadStateValue);
            if (this.mDownloadState == DownloadState.Stopped && this.mStopReason == null) {
                this.mStopReason = StopReason.getStopReasonByValue(this.mStopReasonValue);
            }
        }
        if (this.mDownloadState == DownloadState.Deleted && StringUtils.isEmpty(this.mLicenseData.mKeySetId)) {
            this.mDownloadState = DownloadState.DeleteComplete;
        }
        return this.mDownloadState;
    }
    
    public String getDownloadVideoQuality() {
        return this.mDownloadVideoQuality;
    }
    
    public int getManifestNetworkType() {
        return this.mManifestNetworkType;
    }
    
    public List<String> getProfileGuidList() {
        return this.mProfileGuidList;
    }
    
    public StopReason getStopReason() {
        if (this.getDownloadState() == DownloadState.Stopped) {
            return this.mStopReason;
        }
        return StopReason.Unknown;
    }
    
    public String getWiFiSsidOrNetworkOperatorName() {
        return this.mWiFiSsidOrNetworkOperatorName;
    }
    
    public boolean isGeoBlocked() {
        return this.mGeoBlocked;
    }
    
    public void resetPersistentError() {
        this.mErrorCode = 0;
        this.mErrorString = null;
    }
    
    public void setCreateFailedState() {
        this.setDownloadState(DownloadState.CreateFailed);
    }
    
    public void setDownloadStateComplete() {
        this.setDownloadState(DownloadState.Complete);
        this.mStopReason = null;
    }
    
    public void setDownloadStateDeleteComplete() {
        this.setDownloadState(DownloadState.DeleteComplete);
        this.mStopReason = null;
    }
    
    public void setDownloadStateDeleted() {
        this.mDlStateBeforeDelete = this.mDownloadState;
        this.setDownloadState(DownloadState.Deleted);
        this.mStopReason = null;
    }
    
    public void setDownloadStateInProgress() {
        this.setDownloadState(DownloadState.InProgress);
        this.mStopReason = null;
    }
    
    public void setDownloadStateStopped(final StopReason stopReason) {
        this.setDownloadState(DownloadState.Stopped);
        this.setStopReason(stopReason);
    }
    
    public void setGeoBlocked(final boolean mGeoBlocked) {
        this.mGeoBlocked = mGeoBlocked;
    }
    
    public void setManifestNetworkType(final int mManifestNetworkType) {
        this.mManifestNetworkType = mManifestNetworkType;
    }
    
    public void setPersistentError(final Status status) {
        this.mErrorCode = status.getStatusCode().getValue();
        this.mErrorString = status.getMessage();
    }
    
    public void setWiFiSsidOrNetworkOperatorName(final String mWiFiSsidOrNetworkOperatorName) {
        this.mWiFiSsidOrNetworkOperatorName = mWiFiSsidOrNetworkOperatorName;
    }
}
