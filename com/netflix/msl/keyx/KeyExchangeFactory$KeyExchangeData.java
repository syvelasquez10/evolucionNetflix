// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.crypto.ICryptoContext;

public class KeyExchangeFactory$KeyExchangeData
{
    public final ICryptoContext cryptoContext;
    public final KeyResponseData keyResponseData;
    
    public KeyExchangeFactory$KeyExchangeData(final KeyResponseData keyResponseData, final ICryptoContext cryptoContext) {
        this.keyResponseData = keyResponseData;
        this.cryptoContext = cryptoContext;
    }
}
