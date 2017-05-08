// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class ContinueWatchingProgBar extends PersistentConfigurable
{
    private static final String CW_PROG_BAR_CONFIG_PREFS_KEY = "persistent_cw_prog_bar";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getCWProgressBarConfig();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_cw_prog_bar";
    }
}
