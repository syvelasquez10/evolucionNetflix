// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.service.configuration.crypto.WidevineErrorDescriptor;
import android.util.Log;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import android.content.Context;
import com.netflix.mediaclient.StatusCode;

class NtbaWidevineOperationErrorHandler extends BaseNtbaCryptoErrorHandler
{
    private StatusCode mFailureType;
    
    NtbaWidevineOperationErrorHandler(final StatusCode mFailureType) {
        this.mFailureType = mFailureType;
    }
    
    static boolean canHandle(final StatusCode statusCode) {
        return statusCode == StatusCode.DRM_FAILURE_MEDIADRM_DECRYPT || statusCode == StatusCode.DRM_FAILURE_MEDIADRM_ENCRYPT || statusCode == StatusCode.DRM_FAILURE_MEDIADRM_SIGN || statusCode == StatusCode.DRM_FAILURE_MEDIADRM_VERIFY;
    }
    
    @Override
    StatusCode getStatusCode() {
        return this.mFailureType;
    }
    
    @Override
    public ErrorDescriptor handle(final Context context, final Throwable t) {
        final StringBuilder sb = new StringBuilder();
        sb.append(CryptoManagerRegistry.getCryptoManager().getCryptoProvider().name()).append(": ");
        sb.append(this.mFailureType.toString());
        if (t != null) {
            sb.append(Log.getStackTraceString(t));
        }
        final String string = sb.toString();
        com.netflix.mediaclient.Log.dumpVerbose(NtbaWidevineOperationErrorHandler.TAG, string);
        CryptoErrorManager.INSTANCE.getErrorLogger().logHandledException(string);
        return new WidevineErrorDescriptor(context, this.mFailureType, this.getForceStopTask(context), this.getErrorMessageForFatalError());
    }
}
