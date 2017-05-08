// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;

public interface IMSLClient
{
    void addNetworkRequestInspector(final IMSLClient$NetworkRequestInspector p0, final Class[] p1);
    
    boolean addRequest(final MSLVolleyRequest p0);
    
    boolean enabled();
    
    boolean isUserKnown(final String p0);
    
    void logout();
}
