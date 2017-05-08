// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.StatusCode;

public abstract class BaseStatus implements Status
{
    public StatusCode mStatusCode;
    
    @Override
    public abstract Error getError();
    
    @Override
    public abstract String getMessage();
    
    @Override
    public abstract int getRequestId();
    
    @Override
    public StatusCode getStatusCode() {
        return this.mStatusCode;
    }
    
    @Override
    public boolean isError() {
        return this.mStatusCode.isError();
    }
    
    @Override
    public boolean isErrorOrWarning() {
        return this.isError() || this.isWarning();
    }
    
    @Override
    public boolean isSucces() {
        return this.mStatusCode.isSucess();
    }
    
    @Override
    public boolean isWarning() {
        return this.mStatusCode.isWarning();
    }
    
    @Override
    public abstract boolean shouldDisplayMessage();
}
