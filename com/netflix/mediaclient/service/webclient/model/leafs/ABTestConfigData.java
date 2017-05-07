// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class ABTestConfigData
{
    private ABTestConfigData$Cell cellEnum;
    @SerializedName("cell")
    private int cellInt;
    
    public ABTestConfigData() {
        this.cellInt = 1;
    }
    
    public ABTestConfigData$Cell getCell() {
        if (this.cellEnum == null) {
            this.cellEnum = fromInt(this.cellInt);
        }
        return this.cellEnum;
    }
}
