// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

public enum MslConstants$CipherSpec
{
    AESWrap;
    
    private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";
    
    AES_CBC_PKCS5Padding;
    
    private static final String RSA_ECB_PKCS1PADDING = "RSA/ECB/PKCS1Padding";
    
    RSA_ECB_PKCS1Padding;
    
    public static MslConstants$CipherSpec fromString(final String s) {
        if ("AES/CBC/PKCS5Padding".equals(s)) {
            return MslConstants$CipherSpec.AES_CBC_PKCS5Padding;
        }
        if ("RSA/ECB/PKCS1Padding".equals(s)) {
            return MslConstants$CipherSpec.RSA_ECB_PKCS1Padding;
        }
        return Enum.valueOf(MslConstants$CipherSpec.class, s);
    }
    
    @Override
    public String toString() {
        switch (MslConstants$1.$SwitchMap$com$netflix$msl$MslConstants$CipherSpec[this.ordinal()]) {
            default: {
                return this.name();
            }
            case 1: {
                return "AES/CBC/PKCS5Padding";
            }
            case 2: {
                return "RSA/ECB/PKCS1Padding";
            }
        }
    }
}
