// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

public interface PServiceAgent$AgentContext
{
    PServiceAgent$PServiceFetchAgentInterface getFetchAgent();
    
    PServiceAgent$PServicePartnerFetchInterface getPartnerFetch();
    
    PService getService();
    
    PServiceAgent$PServiceWidgetAgentInterface getWidgetAgent();
}
