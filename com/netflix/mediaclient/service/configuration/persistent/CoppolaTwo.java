// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class CoppolaTwo extends PersistentConfigurable
{
    private static final String PERSISTENT_COPPOLA_2_CONFIG_PREFS_KEY = "persistent_coppola2_experience_key";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getCoppola2Experience();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_coppola2_experience_key";
    }
}
