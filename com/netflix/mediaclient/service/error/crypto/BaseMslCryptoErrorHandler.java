// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

abstract class BaseMslCryptoErrorHandler extends BaseCryptoErrorHandler
{
    protected static String TAG;
    
    static {
        BaseMslCryptoErrorHandler.TAG = "nf_crypto_error_msl";
    }
    
    @Override
    ErrorSource getErrorSource() {
        return ErrorSource.msl;
    }
}
