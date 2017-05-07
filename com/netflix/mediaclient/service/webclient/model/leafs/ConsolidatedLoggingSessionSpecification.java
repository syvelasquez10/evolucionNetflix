// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class ConsolidatedLoggingSessionSpecification
{
    @SerializedName("disableChancePercentagePerUserSession")
    private int disableChancePercentagePerUserSession;
    @SerializedName("session")
    private String session;
    @SerializedName("suppressPercentagePerEvent")
    private int suppressPercentagePerEvent;
    
    public int getDisableChancePercentagePerUserSession() {
        return this.disableChancePercentagePerUserSession;
    }
    
    public String getSession() {
        return this.session;
    }
    
    public int getSuppressPercentagePerEvent() {
        return this.suppressPercentagePerEvent;
    }
    
    @Override
    public String toString() {
        return "ConsolidatedLoggingSpecification [session=" + this.session + ", disableChancePercentagePerUserSession=" + this.disableChancePercentagePerUserSession + ", suppressPercentagePerEvent=" + this.suppressPercentagePerEvent + "]";
    }
}
