// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public enum ExpiringContentType
{
    MOVIE("movie"), 
    SEASON("season"), 
    SERIES("series"), 
    SHOW("show");
    
    private final String name;
    
    private ExpiringContentType(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
