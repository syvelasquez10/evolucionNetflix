// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.advisory;

import java.util.Locale;

public enum ExpiringContentAdvisory$ContentAction
{
    LOG_WHEN_SHOWN, 
    NEVER_SHOW_AGAIN, 
    UNKNOWN;
    
    public static ExpiringContentAdvisory$ContentAction fromString(final String s) {
        try {
            return valueOf(s.toUpperCase(Locale.ENGLISH));
        }
        catch (IllegalArgumentException ex) {
            return ExpiringContentAdvisory$ContentAction.UNKNOWN;
        }
    }
}
