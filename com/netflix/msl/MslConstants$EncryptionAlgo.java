// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

public enum MslConstants$EncryptionAlgo
{
    AES;
    
    public static MslConstants$EncryptionAlgo fromString(final String s) {
        return Enum.valueOf(MslConstants$EncryptionAlgo.class, s);
    }
    
    @Override
    public String toString() {
        return this.name();
    }
}
