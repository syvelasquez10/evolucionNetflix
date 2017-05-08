// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.msl.client.ApiHttpWrapper;

public interface IMSLClient$NetworkRequestInspector
{
    String inspectRequestAfterApiWrap(final String p0);
    
    IMSLClient$MSLApiUnwrappedParams inspectRequestBeforeApiWrap(final IMSLClient$MSLApiUnwrappedParams p0);
    
    ApiHttpWrapper inspectResponseAfterApiUnwrap(final ApiHttpWrapper p0);
    
    byte[] inspectResponseBeforeApiUnwrap(final byte[] p0);
}
