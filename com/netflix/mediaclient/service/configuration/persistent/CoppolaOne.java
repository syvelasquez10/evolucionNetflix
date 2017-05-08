// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class CoppolaOne extends PersistentConfigurable
{
    private static final String PERSISTENT_COPPOLA_1_CONFIG_PREFS_KEY = "persistent_coppola1_experience_key";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getCoppola1Experience();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_coppola1_experience_key";
    }
}
