// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.service.configuration.KubrickConfiguration;

public class KubrickConfigData implements KubrickConfiguration
{
    @SerializedName("isEnabled")
    private boolean isEnabled;
    
    @Override
    public boolean isKubrickEnabled() {
        return this.isEnabled;
    }
}
