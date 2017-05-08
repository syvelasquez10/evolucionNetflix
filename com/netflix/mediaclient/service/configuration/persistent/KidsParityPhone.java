// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

public class KidsParityPhone extends PersistentConfigurable
{
    @Override
    public String getPrefKey() {
        return "persistent_kids_parity_phone_key";
    }
    
    @Override
    public String getTestId() {
        return "8132";
    }
    
    @Override
    public boolean isMobileOnly() {
        return true;
    }
}
