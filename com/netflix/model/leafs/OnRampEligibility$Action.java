// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

public enum OnRampEligibility$Action
{
    FETCH, 
    RECORD, 
    UNKNOWN;
    
    public static OnRampEligibility$Action fromString(final String s) {
        try {
            return valueOf(s.toUpperCase());
        }
        catch (IllegalArgumentException ex) {
            return OnRampEligibility$Action.UNKNOWN;
        }
    }
}
