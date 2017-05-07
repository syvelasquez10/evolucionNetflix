// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public enum SessionDefaultAudience
{
    EVERYONE("everyone"), 
    FRIENDS("friends"), 
    NONE((String)null), 
    ONLY_ME("only_me");
    
    private final String nativeProtocolAudience;
    
    private SessionDefaultAudience(final String nativeProtocolAudience) {
        this.nativeProtocolAudience = nativeProtocolAudience;
    }
    
    public String getNativeProtocolAudience() {
        return this.nativeProtocolAudience;
    }
}
