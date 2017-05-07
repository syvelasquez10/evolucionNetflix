// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.service.configuration.KubrickConfiguration;

public class KubrickConfigData implements KubrickConfiguration
{
    private KubrickConfigData$KubrickCell cellEnum;
    @SerializedName("cell")
    private int cellInt;
    
    public KubrickConfigData() {
        this.cellInt = 1;
    }
    
    @Override
    public KubrickConfigData$KubrickCell getCell() {
        if (this.cellEnum == null) {
            this.cellEnum = fromInt(this.cellInt);
        }
        return this.cellEnum;
    }
}
