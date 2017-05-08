// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.client.model.Error;

public interface ExceptionLoggingCl
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_EXCEPTION_CL" };
    public static final String EXTRA_ERROR = "error";
    public static final String LOG_EXCEPTION_CL = "com.netflix.mediaclient.intent.action.LOG_EXCEPTION_CL";
    
    void reportExceptionToCL(final Error p0);
    
    void setDataContext(final DataContext p0);
}
