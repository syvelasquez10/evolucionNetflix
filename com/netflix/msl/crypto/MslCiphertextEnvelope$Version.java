// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.MslInternalException;

public enum MslCiphertextEnvelope$Version
{
    V1, 
    V2;
    
    public static MslCiphertextEnvelope$Version valueOf(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown ciphertext envelope version " + n + ".");
            }
            case 1: {
                return MslCiphertextEnvelope$Version.V1;
            }
            case 2: {
                return MslCiphertextEnvelope$Version.V2;
            }
        }
    }
    
    public int intValue() {
        switch (MslCiphertextEnvelope$1.$SwitchMap$com$netflix$msl$crypto$MslCiphertextEnvelope$Version[this.ordinal()]) {
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
