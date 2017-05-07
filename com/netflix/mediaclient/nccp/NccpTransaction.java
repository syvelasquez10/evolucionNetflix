// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp;

import java.io.IOException;
import org.apache.http.HttpResponse;
import java.util.List;

public interface NccpTransaction
{
    String getContentType();
    
    String getEndpoint();
    
    String getName();
    
    String getNccpVersion();
    
    String getRequestBody();
    
    List<RequestParameter> getRequestHeaders();
    
    NccpResponseHandler getResponseHandler();
    
    String getUserAgent();
    
    NccpResponse processError(final Throwable p0);
    
    NccpResponse processResponse(final HttpResponse p0) throws IOException;
}
