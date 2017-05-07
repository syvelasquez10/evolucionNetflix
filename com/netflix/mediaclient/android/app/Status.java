// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.StatusCode;

public interface Status
{
    public static final int REQUEST_ID_NOT_AVAILABLE = Integer.MAX_VALUE;
    
    NetflixError[] getErrors();
    
    String getMessage();
    
    int getRequestId();
    
    StatusCode getStatusCode();
    
    boolean isError();
    
    boolean isSucces();
    
    boolean shouldDisplayMessage();
}
