// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.MslInternalException;

public enum MslSignatureEnvelope$Version
{
    V1, 
    V2;
    
    public static MslSignatureEnvelope$Version valueOf(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown signature envelope version.");
            }
            case 1: {
                return MslSignatureEnvelope$Version.V1;
            }
            case 2: {
                return MslSignatureEnvelope$Version.V2;
            }
        }
    }
    
    public int intValue() {
        switch (MslSignatureEnvelope$1.$SwitchMap$com$netflix$msl$crypto$MslSignatureEnvelope$Version[this.ordinal()]) {
            default: {
                throw new MslInternalException("No integer value defined for version " + this + ".");
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
        }
    }
}
