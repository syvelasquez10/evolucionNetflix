// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.client.model.Error;

public class NetflixStatus extends BaseStatus
{
    private static final long serialVersionUID = 5121797712426793309L;
    private String mDebugMessageForServerLogs;
    protected boolean mDisplayMessage;
    protected transient Error mError;
    protected String mMessage;
    private int mRequestId;
    
    public NetflixStatus(final StatusCode statusCode) {
        this(statusCode, Integer.MAX_VALUE);
    }
    
    public NetflixStatus(final StatusCode mStatusCode, final int mRequestId) {
        if (mStatusCode == null) {
            new IllegalArgumentException("Status code can not be null!");
        }
        this.mStatusCode = mStatusCode;
        this.mRequestId = mRequestId;
    }
    
    @Override
    public String getDebugMessageForServerLogs() {
        return this.mDebugMessageForServerLogs;
    }
    
    @Override
    public Error getError() {
        return this.mError;
    }
    
    @Override
    public String getMessage() {
        return this.mMessage;
    }
    
    @Override
    public int getRequestId() {
        return this.mRequestId;
    }
    
    public void setDebugMessageForServerLogs(final String mDebugMessageForServerLogs) {
        this.mDebugMessageForServerLogs = mDebugMessageForServerLogs;
    }
    
    public void setDisplayMessage(final boolean mDisplayMessage) {
        this.mDisplayMessage = mDisplayMessage;
    }
    
    public void setError(final Error mError) {
        this.mError = mError;
    }
    
    public void setMessage(final String mMessage) {
        this.mMessage = mMessage;
    }
    
    public void setRequestId(final int mRequestId) {
        this.mRequestId = mRequestId;
    }
    
    @Override
    public boolean shouldDisplayMessage() {
        return this.mDisplayMessage;
    }
    
    @Override
    public String toString() {
        return "NetflixStatus, " + this.mStatusCode;
    }
}
