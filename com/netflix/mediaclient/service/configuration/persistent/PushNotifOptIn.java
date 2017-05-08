// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class PushNotifOptIn extends PersistentConfigurable
{
    private static final String PERSISTENT_PUSH_OPT_IN_CONFIG_PREFS_KEY = "persistent_push_opt_in_key";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getPushNotifOptInConfig();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_push_opt_in_key";
    }
    
    @Override
    public String getTestId() {
        return "7035";
    }
}
