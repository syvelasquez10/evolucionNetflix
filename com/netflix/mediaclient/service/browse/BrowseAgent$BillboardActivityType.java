// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

public enum BrowseAgent$BillboardActivityType
{
    ACTION("action"), 
    IMPRESSION("impression");
    
    private final String name;
    
    private BrowseAgent$BillboardActivityType(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
