// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.util.SparseArray;

public enum SocialUtils$TinyTypes
{
    CLIP_TYPE, 
    DISC_TYPE, 
    MOVIE_TYPE, 
    NO_TYPE, 
    PERSON_TYPE, 
    PROGRAM_TYPE, 
    SEASON_TYPE, 
    SERIES_TYPE;
    
    private static final SparseArray<SocialUtils$TinyTypes> hashOrdToType;
    
    static {
        int i = 0;
        hashOrdToType = new SparseArray();
        for (SocialUtils$TinyTypes[] values = values(); i < values.length; ++i) {
            final SocialUtils$TinyTypes socialUtils$TinyTypes = values[i];
            SocialUtils$TinyTypes.hashOrdToType.put(socialUtils$TinyTypes.ordinal(), (Object)socialUtils$TinyTypes);
        }
    }
    
    public static SocialUtils$TinyTypes ordinalToType(final int n) {
        return (SocialUtils$TinyTypes)SocialUtils$TinyTypes.hashOrdToType.get(n);
    }
}
