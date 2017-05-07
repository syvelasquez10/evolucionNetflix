// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public enum SessionDefaultAudience
{
    EVERYONE("EVERYONE"), 
    FRIENDS("ALL_FRIENDS"), 
    NONE((String)null), 
    ONLY_ME("SELF");
    
    private final String nativeProtocolAudience;
    
    private SessionDefaultAudience(final String nativeProtocolAudience) {
        this.nativeProtocolAudience = nativeProtocolAudience;
    }
    
    String getNativeProtocolAudience() {
        return this.nativeProtocolAudience;
    }
}
