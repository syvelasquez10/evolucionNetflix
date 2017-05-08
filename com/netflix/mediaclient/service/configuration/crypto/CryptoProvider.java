// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

public enum CryptoProvider
{
    LEGACY("", 0), 
    WIDEVINE_L1("", 1), 
    WIDEVINE_L3("L3-", 3);
    
    public final String ESN_VALUE;
    public final int NCCP_VALUE;
    
    private CryptoProvider(final String esn_VALUE, final int nccp_VALUE) {
        this.ESN_VALUE = esn_VALUE;
        this.NCCP_VALUE = nccp_VALUE;
    }
    
    public static CryptoProvider fromName(final String s) {
        if (CryptoProvider.LEGACY.name().equals(s)) {
            return CryptoProvider.LEGACY;
        }
        if (CryptoProvider.WIDEVINE_L1.name().equals(s)) {
            return CryptoProvider.WIDEVINE_L1;
        }
        if (CryptoProvider.WIDEVINE_L3.name().equals(s)) {
            return CryptoProvider.WIDEVINE_L3;
        }
        return null;
    }
}
