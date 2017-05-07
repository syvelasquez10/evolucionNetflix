// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class KubrickKidsConfigData
{
    private KubrickKidsConfigData$KubrickKidsCell cellEnum;
    @SerializedName("cell")
    private int cellInt;
    
    public KubrickKidsConfigData() {
        this.cellInt = 1;
    }
    
    public KubrickKidsConfigData$KubrickKidsCell getCell() {
        if (this.cellEnum == null) {
            this.cellEnum = fromInt(this.cellInt);
        }
        return this.cellEnum;
    }
}
