// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

abstract class BaseNtbaCryptoErrorHandler extends BaseCryptoErrorHandler
{
    protected static String TAG;
    
    static {
        BaseNtbaCryptoErrorHandler.TAG = "nf_crypto_error_ntba";
    }
    
    @Override
    ErrorSource getErrorSource() {
        return ErrorSource.ntba;
    }
}
