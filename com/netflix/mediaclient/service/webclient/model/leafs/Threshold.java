// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class Threshold
{
    @SerializedName("red")
    private int red;
    @SerializedName("yellow")
    private int yellow;
    
    public int getRed() {
        return this.red;
    }
    
    public int getYellow() {
        return this.yellow;
    }
    
    @Override
    public String toString() {
        return "Threshold{red=" + this.red + ", yellow=" + this.yellow + '}';
    }
}
