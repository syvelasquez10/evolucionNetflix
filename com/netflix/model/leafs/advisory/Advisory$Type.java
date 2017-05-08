// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.advisory;

import java.util.Locale;

public enum Advisory$Type
{
    CONTENT_ADVISORY, 
    EXPIRY_NOTICE, 
    PRODUCT_PLACEMENT_ADVISORY, 
    UNKNOWN;
    
    public static Advisory$Type fromString(final String s) {
        try {
            return valueOf(s.toUpperCase(Locale.ENGLISH));
        }
        catch (IllegalArgumentException ex) {
            return Advisory$Type.UNKNOWN;
        }
    }
}
