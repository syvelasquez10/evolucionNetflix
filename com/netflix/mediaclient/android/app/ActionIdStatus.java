// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.StatusCode;

public class ActionIdStatus extends NetflixStatus
{
    private int mActionId;
    private String mBcp47;
    private String mOrigin;
    private int mReasonCode;
    private String mType;
    
    public ActionIdStatus(final StatusCode statusCode, final int mActionId) {
        super(statusCode);
        this.mActionId = mActionId;
    }
    
    public int getActionId() {
        return this.mActionId;
    }
    
    public String getBcp47() {
        return this.mBcp47;
    }
    
    public String getOrigin() {
        return this.mOrigin;
    }
    
    public int getReasonCode() {
        return this.mReasonCode;
    }
    
    public String getType() {
        return this.mType;
    }
    
    public void setBcp47(final String mBcp47) {
        this.mBcp47 = mBcp47;
    }
    
    public void setOrigin(final String mOrigin) {
        this.mOrigin = mOrigin;
    }
    
    public void setReasonCode(final int mReasonCode) {
        this.mReasonCode = mReasonCode;
    }
    
    public void setType(final String mType) {
        this.mType = mType;
    }
    
    @Override
    public boolean shouldDisplayMessage() {
        return this.mActionId == 3 && StringUtils.isEmpty(this.mMessage);
    }
}
