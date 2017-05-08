// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.StatusCode;

final class CryptoErrorHandlerFactory
{
    private static String TAG;
    
    static {
        CryptoErrorHandlerFactory.TAG = "nf_crypto_error";
    }
    
    static CryptoErrorHandler getCryptoErrorHandler(final ErrorSource errorSource, final StatusCode statusCode) {
        if (errorSource == ErrorSource.msl) {
            return getMslErrorHandler(statusCode);
        }
        if (errorSource == ErrorSource.ntba) {
            return getNtbaErrorHandler(statusCode);
        }
        throw new IllegalStateException("Playback error sources not supported at this moment!");
    }
    
    private static CryptoErrorHandler getMslErrorHandler(final StatusCode statusCode) {
        if (MslWidevineNonFatalErrorHandler.canHandle(statusCode)) {
            return new MslWidevineNonFatalErrorHandler(statusCode);
        }
        if (MslWidevinePluginChangedErrorHandler.canHandle(statusCode)) {
            return new MslWidevinePluginChangedErrorHandler();
        }
        if (MslWidevineGetKeyRequestFailedErrorHandler.canHandle(statusCode)) {
            return new MslWidevineGetKeyRequestFailedErrorHandler();
        }
        if (MslWidevineProvideKeyResponseFailedErrorHandler.canHandle(statusCode)) {
            return new MslWidevineProvideKeyResponseFailedErrorHandler();
        }
        if (MslWidevineProvideKeyRestoreFailedErrorHandler.canHandle(statusCode)) {
            return new MslWidevineProvideKeyRestoreFailedErrorHandler();
        }
        if (LegacyCryptoWhenWidevineWasUsedBeforeErrorHandler.canHandle(statusCode)) {
            return new LegacyCryptoWhenWidevineWasUsedBeforeErrorHandler();
        }
        return null;
    }
    
    private static CryptoErrorHandler getNtbaErrorHandler(final StatusCode statusCode) {
        if (NtbaWidevineGetKeyRequestFailedErrorHandler.canHandle(statusCode)) {
            return new NtbaWidevineGetKeyRequestFailedErrorHandler();
        }
        if (NtbaWidevinePluginChangedErrorHandler.canHandle(statusCode)) {
            return new NtbaWidevinePluginChangedErrorHandler();
        }
        if (NtbaWidevineProvideKeyRestoreFailedErrorHandler.canHandle(statusCode)) {
            return new NtbaWidevineProvideKeyRestoreFailedErrorHandler();
        }
        if (NtbaWidevineProvideKeyResponseFailedErrorHandler.canHandle(statusCode)) {
            return new NtbaWidevineProvideKeyResponseFailedErrorHandler();
        }
        if (NtbaCdmProvisionFailedErrorHandler.canHandle(statusCode)) {
            return new NtbaCdmProvisionFailedErrorHandler();
        }
        if (NtbaWidevineOperationErrorHandler.canHandle(statusCode)) {
            return new NtbaWidevineOperationErrorHandler(statusCode);
        }
        return new NtbaWidevineDefaultErrorHandler(statusCode);
    }
}
