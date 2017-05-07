// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import android.util.SparseArray;

public enum BaseNflxHandler$TinyTypes
{
    CLIP_TYPE, 
    DISC_TYPE, 
    MOVIE_TYPE, 
    NO_TYPE, 
    PERSON_TYPE, 
    PROGRAM_TYPE, 
    SEASON_TYPE, 
    SERIES_TYPE;
    
    private static final SparseArray<BaseNflxHandler$TinyTypes> hashOrdToType;
    
    static {
        int i = 0;
        hashOrdToType = new SparseArray();
        for (BaseNflxHandler$TinyTypes[] values = values(); i < values.length; ++i) {
            final BaseNflxHandler$TinyTypes baseNflxHandler$TinyTypes = values[i];
            BaseNflxHandler$TinyTypes.hashOrdToType.put(baseNflxHandler$TinyTypes.ordinal(), (Object)baseNflxHandler$TinyTypes);
        }
    }
    
    public static BaseNflxHandler$TinyTypes ordinalToType(final int n) {
        return (BaseNflxHandler$TinyTypes)BaseNflxHandler$TinyTypes.hashOrdToType.get(n);
    }
}
