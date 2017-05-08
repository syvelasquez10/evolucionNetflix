// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

public class KidsParityTablet extends PersistentConfigurable
{
    @Override
    public String getPrefKey() {
        return "persistent_kids_parity_tablet_key";
    }
    
    @Override
    public String getTestId() {
        return "7872";
    }
    
    @Override
    public boolean isTabletOnly() {
        return true;
    }
}
