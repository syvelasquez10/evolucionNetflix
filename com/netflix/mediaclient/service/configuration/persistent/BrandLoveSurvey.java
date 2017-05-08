// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class BrandLoveSurvey extends PersistentConfigurable
{
    private static final String BRAND_LOVE_SURVEY_CONFIG_PREFS_KEY = "persistent_survey";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getBrandLoveSurveyConfig();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_survey";
    }
}
