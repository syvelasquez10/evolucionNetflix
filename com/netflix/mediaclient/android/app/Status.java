// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.client.model.Error;
import java.io.Serializable;

public interface Status extends Serializable
{
    public static final int REQUEST_ID_NOT_AVAILABLE = Integer.MAX_VALUE;
    
    Error getError();
    
    String getMessage();
    
    int getRequestId();
    
    StatusCode getStatusCode();
    
    boolean isError();
    
    boolean isErrorOrWarning();
    
    boolean isSucces();
    
    boolean isWarning();
    
    boolean shouldDisplayMessage();
}
