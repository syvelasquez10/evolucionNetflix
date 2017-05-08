// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.advisory;

public enum Advisory$DisplayLocation
{
    END, 
    START, 
    UNKNOWN;
    
    public static Advisory$DisplayLocation fromString(final String s) {
        try {
            return valueOf(s.toUpperCase());
        }
        catch (IllegalArgumentException ex) {
            return Advisory$DisplayLocation.UNKNOWN;
        }
    }
}
