// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

public class PrefetchLolomoConfig extends PersistentConfigurable
{
    @Override
    public String getPrefKey() {
        return "persistent_prefetch_lolomo_experience_key";
    }
    
    @Override
    public String getTestId() {
        return "8074";
    }
    
    @Override
    protected boolean shouldForceUpdateMemory() {
        return true;
    }
}
