// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class Memento2 extends PersistentConfigurable
{
    private static final String PERSISTENT_MEMENTO2_CONFIG_PREFS_KEY = "persistent_memento2_key";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getMemento2Config();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_memento2_key";
    }
    
    @Override
    public String getTestId() {
        return "7827";
    }
}
