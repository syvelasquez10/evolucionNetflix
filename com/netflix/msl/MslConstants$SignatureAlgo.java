// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

public enum MslConstants$SignatureAlgo
{
    AESCmac, 
    HmacSHA256, 
    SHA256withRSA;
    
    public static MslConstants$SignatureAlgo fromString(final String s) {
        return Enum.valueOf(MslConstants$SignatureAlgo.class, s);
    }
    
    @Override
    public String toString() {
        return this.name();
    }
}
