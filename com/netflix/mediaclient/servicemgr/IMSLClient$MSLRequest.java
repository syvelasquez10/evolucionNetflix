// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.Map;

public interface IMSLClient$MSLRequest
{
    Map<String, String> getMSLHeaders();
    
    String getMSLPayload();
    
    String getMSLQuery();
    
    String getMSLUri();
    
    IMSLClient$MSLUserCredentialRegistry getMSLUserCredentialRegistry();
}
