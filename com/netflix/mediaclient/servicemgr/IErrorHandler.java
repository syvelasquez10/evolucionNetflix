// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.error.ErrorDescriptor;

public interface IErrorHandler
{
    boolean addError(final ErrorDescriptor p0);
    
    void clear();
    
    ErrorDescriptor getCurrentError();
    
    void setErrorAccepted(final ErrorDescriptor p0);
}
