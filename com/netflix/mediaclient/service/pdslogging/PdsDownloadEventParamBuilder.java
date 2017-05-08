// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.NetflixApplication;
import org.json.JSONObject;

public class PdsDownloadEventParamBuilder
{
    private static final String TAG;
    private final int INVALID_PROGRESS_PERCENTAGE;
    private String mAppSessionId;
    private DownloadContext mDc;
    private String mErrorCode;
    private String mErrorMessage;
    private JSONObject mEventLink;
    private int mProgressPercentage;
    private String mUserSessionId;
    
    static {
        TAG = PdsDownloadEventParamBuilder.class.getSimpleName();
    }
    
    public PdsDownloadEventParamBuilder(final JSONObject mEventLink, final String mAppSessionId, final String mUserSessionId) {
        this.INVALID_PROGRESS_PERCENTAGE = -1;
        this.mEventLink = mEventLink;
        this.mAppSessionId = mAppSessionId;
        this.mUserSessionId = mUserSessionId;
        this.mProgressPercentage = -1;
    }
    
    final String build() {
        final JSONObject jsonObject = new JSONObject();
        if (this.mEventLink == null) {
            return jsonObject.toString();
        }
        try {
            jsonObject.put("version", 2);
            jsonObject.put("method", (Object)this.mEventLink.optString("rel"));
            jsonObject.put("url", (Object)this.mEventLink.optString("href"));
            final long currentTimeMillis = System.currentTimeMillis();
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("clientTime", currentTimeMillis);
            jsonObject2.put("appSessionId", (Object)this.mAppSessionId);
            jsonObject2.put("userSessionId", (Object)this.mUserSessionId);
            jsonObject2.put("isInBackground", !NetflixApplication.isActivityVisible());
            jsonObject2.put("trackerId", this.mDc.getTrackId());
            if (this.mProgressPercentage != -1) {
                jsonObject2.put("progressPercentage", this.mProgressPercentage);
            }
            if (StringUtils.isNotEmpty(this.mErrorCode)) {
                jsonObject2.put("errorCode", (Object)this.mErrorCode);
            }
            if (StringUtils.isNotEmpty(this.mErrorMessage)) {
                jsonObject2.put("errorMessage", (Object)this.mErrorMessage);
            }
            jsonObject2.put("uiDownloadContext", (Object)this.mDc.getJsonObject().toString());
            jsonObject.putOpt("params", (Object)jsonObject2);
            return jsonObject.toString();
        }
        catch (Exception ex) {
            Log.e(PdsDownloadEventParamBuilder.TAG, ex, "error creating pds download event params", new Object[0]);
            return jsonObject.toString();
        }
    }
    
    public PdsDownloadEventParamBuilder setDownloadContext(final DownloadContext mDc) {
        this.mDc = mDc;
        return this;
    }
    
    public PdsDownloadEventParamBuilder setErrorInfo(final String mErrorCode, final String mErrorMessage) {
        this.mErrorCode = mErrorCode;
        this.mErrorMessage = mErrorMessage;
        return this;
    }
    
    public PdsDownloadEventParamBuilder setProgressPercentage(final int mProgressPercentage) {
        this.mProgressPercentage = mProgressPercentage;
        return this;
    }
}
