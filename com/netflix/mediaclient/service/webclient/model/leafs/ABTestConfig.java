// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class ABTestConfig
{
    private ABTestConfig$Cell cellEnum;
    @SerializedName("cell")
    private int cellInt;
    @SerializedName("isExplicit")
    private boolean isExplicit;
    @SerializedName("isFallback")
    private boolean isFallback;
    
    public ABTestConfig() {
        this.cellInt = 1;
    }
    
    public ABTestConfig$Cell getCell() {
        if (this.cellEnum == null) {
            this.cellEnum = ABTestConfig$Cell.fromInt(this.cellInt);
        }
        return this.cellEnum;
    }
    
    public boolean isExplicit() {
        return this.isExplicit;
    }
    
    public boolean isFallback() {
        return this.isFallback;
    }
}
