// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads;

public enum AdRequest$ErrorCode
{
    INTERNAL_ERROR("There was an internal error."), 
    INVALID_REQUEST("Invalid Ad request."), 
    NETWORK_ERROR("A network error occurred."), 
    NO_FILL("Ad request successful, but no ad returned due to lack of ad inventory.");
    
    private final String description;
    
    private AdRequest$ErrorCode(final String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return this.description;
    }
}
