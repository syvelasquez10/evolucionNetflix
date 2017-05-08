// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class MotionBB extends PersistentConfigurable
{
    private static final String PERSISTENT_MOTION_BB_CONFIG_PREFS_KEY = "persistent_motion_bb_experience_key";
    
    @Override
    public ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.getMotionBBTestConfig();
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_motion_bb_experience_key";
    }
    
    @Override
    public String getTestId() {
        return "6930";
    }
}
