// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

public class CoppolaTwo extends PersistentConfigurable
{
    @Override
    public String getPrefKey() {
        return "persistent_coppola2_experience_key";
    }
    
    @Override
    public String getTestId() {
        return "6941";
    }
    
    @Override
    public boolean isMobileOnly() {
        return true;
    }
}
