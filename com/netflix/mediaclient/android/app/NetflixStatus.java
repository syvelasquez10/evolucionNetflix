// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import java.util.ArrayList;
import com.netflix.mediaclient.StatusCode;
import java.util.List;

public class NetflixStatus implements Status
{
    protected boolean mDisplayMessage;
    protected List<NetflixError> mErrors;
    protected String mMessage;
    private int mRequestId;
    protected StatusCode mStatusCode;
    
    public NetflixStatus(final StatusCode statusCode) {
        this(statusCode, Integer.MAX_VALUE);
    }
    
    public NetflixStatus(final StatusCode mStatusCode, final int mRequestId) {
        this.mErrors = new ArrayList<NetflixError>();
        if (mStatusCode == null) {
            new IllegalArgumentException("Status code can not be null!");
        }
        this.mStatusCode = mStatusCode;
        this.mRequestId = mRequestId;
    }
    
    public void addError(final NetflixError netflixError) {
        if (netflixError == null) {
            throw new IllegalArgumentException("AddError:: error can not be null!");
        }
        this.mErrors.add(netflixError);
    }
    
    @Override
    public NetflixError[] getErrors() {
        return this.mErrors.toArray(new NetflixError[this.mErrors.size()]);
    }
    
    @Override
    public String getMessage() {
        return this.mMessage;
    }
    
    @Override
    public int getRequestId() {
        return this.mRequestId;
    }
    
    @Override
    public StatusCode getStatusCode() {
        return this.mStatusCode;
    }
    
    @Override
    public boolean isError() {
        return this.mStatusCode.isError();
    }
    
    @Override
    public boolean isSucces() {
        return this.mStatusCode.isSucess();
    }
    
    public void setDisplayMessage(final boolean mDisplayMessage) {
        this.mDisplayMessage = mDisplayMessage;
    }
    
    public void setMessage(final String mMessage) {
        this.mMessage = mMessage;
    }
    
    @Override
    public boolean shouldDisplayMessage() {
        return this.mDisplayMessage;
    }
}
