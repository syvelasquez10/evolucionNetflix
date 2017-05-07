// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public enum BillboardInteractionType
{
    ACTION("action"), 
    IMPRESSION("impression");
    
    private final String name;
    
    private BillboardInteractionType(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
