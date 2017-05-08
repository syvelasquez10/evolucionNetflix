// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import java.util.Set;

public enum MslConstants$CompressionAlgorithm
{
    GZIP, 
    LZW;
    
    public static MslConstants$CompressionAlgorithm getPreferredAlgorithm(final Set<MslConstants$CompressionAlgorithm> set) {
        final MslConstants$CompressionAlgorithm[] values = values();
        for (int n = 0; n < values.length && set.size() > 0; ++n) {
            final MslConstants$CompressionAlgorithm mslConstants$CompressionAlgorithm = values[n];
            if (set.contains(mslConstants$CompressionAlgorithm)) {
                return mslConstants$CompressionAlgorithm;
            }
        }
        return null;
    }
}
