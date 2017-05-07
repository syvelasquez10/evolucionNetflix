// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.mdxcontroller;

public interface PairingController
{
    public static final String PAIRING_VERSION = "1.0";
    public static final String REG_PAIRING_VERSION = "2.0";
    
    void deletePairing(final String p0);
    
    void pairingRequest(final String p0);
    
    void registrationPairingRequest(final String p0);
    
    void registrationPairingRequest(final String p0, final String p1);
}
