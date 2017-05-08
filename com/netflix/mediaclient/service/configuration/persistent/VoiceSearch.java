// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class VoiceSearch extends PersistentConfigurable
{
    private static final String PERSISTENT_VOICE_SEARCH_CONFIG_PREFS_KEY = "persistent_voice_search_experience_key";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getVoiceSearchABTestConfig();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_voice_search_experience_key";
    }
    
    @Override
    public String getTestId() {
        return "6786";
    }
}
