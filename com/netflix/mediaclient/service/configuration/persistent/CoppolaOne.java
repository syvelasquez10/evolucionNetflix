// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

public class CoppolaOne extends PersistentConfigurable
{
    @Override
    public String getPrefKey() {
        return "persistent_coppola1_experience_key";
    }
    
    @Override
    public String getTestId() {
        return "6729";
    }
    
    @Override
    public boolean isMobileOnly() {
        return true;
    }
}
