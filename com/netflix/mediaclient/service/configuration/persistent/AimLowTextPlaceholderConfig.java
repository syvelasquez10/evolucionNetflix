// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class AimLowTextPlaceholderConfig extends PersistentConfigurable
{
    private static final String PERSISTENT_TEXT_PLACEHOLDER_CONFIG_KEY = "persistent_text_placeholder_key";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getAimLowTextPlaceholderConfig();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_text_placeholder_key";
    }
    
    @Override
    public String getTestId() {
        return "7858";
    }
}
