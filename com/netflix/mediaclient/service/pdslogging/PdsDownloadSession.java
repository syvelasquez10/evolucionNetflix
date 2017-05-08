// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import com.netflix.mediaclient.util.StringUtils;
import junit.framework.Assert;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.IPdsLogging;
import org.json.JSONObject;

public class PdsDownloadSession
{
    private static final int PROGRESS_PERCENT_INTERVAL = 30;
    private static final String TAG;
    private boolean isManifestFetchInProgress;
    private boolean isPaused;
    private int lastNotifiedProgressPercentage;
    private String mAppSessionId;
    private DownloadContext mDc;
    private String mDxId;
    private JSONObject mLinkCancelDownload;
    private JSONObject mLinkCompleteDownload;
    private JSONObject mLinkPauseDownload;
    private JSONObject mLinkProgressDownload;
    private JSONObject mLinkResumeDownload;
    private JSONObject mLinkStartDownload;
    private JSONObject mLinkStopDownloadOnError;
    private JSONObject mLinkStopDownloadOnExpiredManifest;
    private JSONObject mLinkStopDownloadOnLicenseError;
    private String mOxId;
    private IPdsLogging mPdsLogging;
    private String mPlayableId;
    private String mUserSessionId;
    
    static {
        TAG = PdsDownloadSession.class.getSimpleName();
    }
    
    public PdsDownloadSession(final String mPlayableId, final String mOxId, final String mDxId, final String mAppSessionId, final String mUserSessionId, final IPdsLogging mPdsLogging) {
        this.mPlayableId = mPlayableId;
        this.mOxId = mOxId;
        this.mDxId = mDxId;
        this.mAppSessionId = mAppSessionId;
        this.mUserSessionId = mUserSessionId;
        this.mPdsLogging = mPdsLogging;
    }
    
    private boolean areLinksPresent() {
        return this.mLinkStartDownload != null && this.mLinkCompleteDownload != null;
    }
    
    private PdsDownloadEventParamBuilder buildDownloadEvent(final JSONObject jsonObject) {
        if (Log.isLoggable()) {
            Assert.assertNotNull((Object)jsonObject);
        }
        return new PdsDownloadEventParamBuilder(jsonObject, this.mAppSessionId, this.mUserSessionId).setDownloadContext(this.mDc);
    }
    
    private void sendEventForLink(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        Log.d(PdsDownloadSession.TAG, "sending pds download event: %s", jsonObject.optString("rel"));
        this.sendPdsEventViaLogging(this.buildDownloadEvent(jsonObject).build());
    }
    
    private void sendPdsEventViaLogging(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            this.mPdsLogging.sendPdsEventViaLogging(s);
        }
    }
    
    private void sendStopDownloadEventForLink(final JSONObject jsonObject, final String s, final String s2) {
        if (jsonObject == null) {
            return;
        }
        this.setPaused(true);
        this.sendPdsEventViaLogging(this.buildDownloadEvent(jsonObject).setErrorInfo(s, s2).build());
        this.mPdsLogging.flushEventsInLogging();
    }
    
    private boolean shouldRateLimitProgressMessage(final int lastNotifiedProgressPercentage) {
        if (lastNotifiedProgressPercentage == 0 || lastNotifiedProgressPercentage >= this.lastNotifiedProgressPercentage + 30) {
            this.lastNotifiedProgressPercentage = lastNotifiedProgressPercentage;
            return false;
        }
        Log.d(PdsDownloadSession.TAG, "rate limited progress message percentage: %d, lastNotifiedProgressPercentage: %d, interval: %d", lastNotifiedProgressPercentage, this.lastNotifiedProgressPercentage, 30);
        return true;
    }
    
    public String getDxId() {
        return this.mDxId;
    }
    
    public String getOxId() {
        return this.mOxId;
    }
    
    public String getPlayableId() {
        return this.mPlayableId;
    }
    
    public boolean isManifestFetchInProgress() {
        return this.isManifestFetchInProgress;
    }
    
    public boolean isPaused() {
        return this.isPaused;
    }
    
    public boolean needToFetchManifest() {
        return !this.areLinksPresent() && !this.isManifestFetchInProgress();
    }
    
    public void sendDownloadCompleteMessage() {
        this.sendEventForLink(this.mLinkCompleteDownload);
        this.mPdsLogging.flushEventsInLogging();
    }
    
    public void sendDownloadPauseMessage() {
        this.sendEventForLink(this.mLinkPauseDownload);
    }
    
    public void sendDownloadProgressMessage(final int progressPercentage) {
        if (this.mLinkProgressDownload != null && !this.shouldRateLimitProgressMessage(progressPercentage)) {
            this.sendPdsEventViaLogging(this.buildDownloadEvent(this.mLinkProgressDownload).setProgressPercentage(progressPercentage).build());
        }
    }
    
    public void sendDownloadResumeMessage() {
        this.sendEventForLink(this.mLinkResumeDownload);
    }
    
    public void sendStartDownloadMessage() {
        this.sendEventForLink(this.mLinkStartDownload);
    }
    
    public void sendStopDownloadOnError(final String s, final String s2) {
        if (this.mLinkStopDownloadOnError == null) {
            return;
        }
        this.sendStopDownloadEventForLink(this.mLinkStopDownloadOnError, s, s2);
    }
    
    public void sendStopDownloadOnExpiredManifest(final String s, final String s2) {
        if (this.mLinkStopDownloadOnExpiredManifest == null) {
            return;
        }
        this.sendStopDownloadEventForLink(this.mLinkStopDownloadOnExpiredManifest, s, s2);
    }
    
    public void sendStopDownloadOnLicenseError(final String s, final String s2) {
        if (this.mLinkStopDownloadOnLicenseError == null) {
            return;
        }
        this.sendStopDownloadEventForLink(this.mLinkStopDownloadOnLicenseError, s, s2);
    }
    
    public PdsDownloadSession setDownloadContext(final DownloadContext mDc) {
        this.mDc = mDc;
        return this;
    }
    
    public PdsDownloadSession setLinks(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return this;
        }
        this.mLinkStartDownload = jsonObject.optJSONObject("startDownload");
        this.mLinkPauseDownload = jsonObject.optJSONObject("pauseDownload");
        this.mLinkResumeDownload = jsonObject.optJSONObject("resumeDownload");
        this.mLinkCompleteDownload = jsonObject.optJSONObject("completeDownload");
        this.mLinkCancelDownload = jsonObject.optJSONObject("cancelDownload");
        this.mLinkProgressDownload = jsonObject.optJSONObject("reportProgress");
        this.mLinkStopDownloadOnLicenseError = jsonObject.optJSONObject("stopDownloadDueToRejectedLicense");
        this.mLinkStopDownloadOnError = jsonObject.optJSONObject("stopDownloadDueToError");
        this.mLinkStopDownloadOnExpiredManifest = jsonObject.optJSONObject("stopDownloadDueToExpiredManifest");
        return this;
    }
    
    public void setManifestFetchInProgress(final boolean isManifestFetchInProgress) {
        this.isManifestFetchInProgress = isManifestFetchInProgress;
    }
    
    public void setPaused(final boolean isPaused) {
        this.isPaused = isPaused;
    }
    
    @Override
    public String toString() {
        return "PdsDownloadSession{mPlayableId='" + this.mPlayableId + '\'' + ", mOxId='" + this.mOxId + '\'' + ", mDxId='" + this.mDxId + '\'' + ", mLinkStartDownload=" + this.mLinkStartDownload + ", mLinkPauseDownload=" + this.mLinkPauseDownload + ", mLinkResumeDownload=" + this.mLinkResumeDownload + ", mLinkCompleteDownload=" + this.mLinkCompleteDownload + ", mLinkCancelDownload=" + this.mLinkCancelDownload + ", mLinkProgressDownload=" + this.mLinkProgressDownload + ", mLinkStopDownloadOnLicenseError=" + this.mLinkStopDownloadOnLicenseError + ", mLinkStopDownloadOnError=" + this.mLinkStopDownloadOnError + '}';
    }
}
