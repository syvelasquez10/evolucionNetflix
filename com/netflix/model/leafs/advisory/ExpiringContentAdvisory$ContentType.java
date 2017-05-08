// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.advisory;

import java.util.Locale;

public enum ExpiringContentAdvisory$ContentType
{
    MOVIE, 
    SEASON, 
    SERIES, 
    SHOW, 
    UNKNOWN;
    
    public static ExpiringContentAdvisory$ContentType fromString(final String s) {
        try {
            return valueOf(s.toUpperCase(Locale.ENGLISH));
        }
        catch (IllegalArgumentException ex) {
            return ExpiringContentAdvisory$ContentType.UNKNOWN;
        }
    }
}
