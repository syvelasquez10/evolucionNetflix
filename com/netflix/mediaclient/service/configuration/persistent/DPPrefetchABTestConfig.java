// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class DPPrefetchABTestConfig extends PersistentConfigurable
{
    private static final String PERSISTENT_REDUCED_DP_TTI_CONFIG_PREFS_KEY = "persistent_reduced_dp_tti_experience_key";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getPrefetchDPConfig();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_reduced_dp_tti_experience_key";
    }
    
    @Override
    public String getTestId() {
        return "7722";
    }
}
