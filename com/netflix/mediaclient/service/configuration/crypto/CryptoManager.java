// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import com.netflix.msl.keyx.WidevineKeyRequestData;

public interface CryptoManager
{
    boolean closeCryptoSession(final CryptoManager$CryptoSession p0);
    
    byte[] decrypt(final CryptoManager$CryptoSession p0, final CryptoManager$KeyId p1, final byte[] p2, final byte[] p3);
    
    void destroy();
    
    byte[] encrypt(final CryptoManager$CryptoSession p0, final CryptoManager$KeyId p1, final byte[] p2, final byte[] p3);
    
    CryptoProvider getCryptoProvider();
    
    WidevineKeyRequestData getKeyRequestData();
    
    void resetCryptoFactory();
    
    CryptoManager$CryptoSession restoreCryptoSession(final CryptoManager$KeyId p0);
    
    byte[] sign(final CryptoManager$CryptoSession p0, final CryptoManager$KeyId p1, final byte[] p2);
    
    CryptoManager$CryptoSession updateKeyResponse(final WidevineKeyRequestData p0, final byte[] p1, final CryptoManager$KeyId p2, final CryptoManager$KeyId p3);
    
    boolean verify(final CryptoManager$CryptoSession p0, final CryptoManager$KeyId p1, final byte[] p2, final byte[] p3);
}
