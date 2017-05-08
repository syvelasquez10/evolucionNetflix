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

class NtbaWidevineDefaultErrorHandler extends BaseNtbaCryptoErrorHandler
{
    private StatusCode mFailureType;
    
    NtbaWidevineDefaultErrorHandler(final StatusCode mFailureType) {
        this.mFailureType = mFailureType;
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
        this.logHandledException(sb.toString());
        com.netflix.mediaclient.Log.d(NtbaWidevineDefaultErrorHandler.TAG, "MediaDrm defaul failure for NTBA, kill app and let user restart...");
        return new WidevineErrorDescriptor(context, this.mFailureType, this.getForceStopTask(context), 2131296637);
    }
}
