// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.util;

public enum TriState
{
    NO, 
    UNSET, 
    YES;
    
    public static TriState valueOf(final boolean b) {
        if (b) {
            return TriState.YES;
        }
        return TriState.NO;
    }
}
