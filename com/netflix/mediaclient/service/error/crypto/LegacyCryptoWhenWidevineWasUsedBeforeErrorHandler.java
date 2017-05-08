// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import android.content.Context;
import com.netflix.mediaclient.StatusCode;

class LegacyCryptoWhenWidevineWasUsedBeforeErrorHandler extends BaseMslCryptoErrorHandler
{
    static boolean canHandle(final StatusCode statusCode) {
        return statusCode == StatusCode.MSL_LEGACY_CRYPTO_BUT_USED_WIDEVINE_BEFORE;
    }
    
    @Override
    StatusCode getStatusCode() {
        return StatusCode.MSL_LEGACY_CRYPTO_BUT_USED_WIDEVINE_BEFORE;
    }
    
    @Override
    public ErrorDescriptor handle(final Context context, final Throwable t) {
        Log.d(LegacyCryptoWhenWidevineWasUsedBeforeErrorHandler.TAG, "Device is unable to used Widevine, but we detected that Widevine was used in past.");
        this.logHandledException(CryptoErrorManager.createMediaDrmErrorMessage(this.getStatusCode(), t));
        return this.handleKillApp(context, t);
    }
}
