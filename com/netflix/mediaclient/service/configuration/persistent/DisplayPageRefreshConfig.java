// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class DisplayPageRefreshConfig extends PersistentConfigurable
{
    private static final String DISPLAY_PAGE_CONFIG_PREFS_KEY = "display_page_refresh_key";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getDisplayPageRefreshConfig();
    }
    
    @Override
    public String getPrefKey() {
        return "display_page_refresh_key";
    }
    
    @Override
    public String getTestId() {
        return "7842";
    }
}
