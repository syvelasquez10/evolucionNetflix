// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import android.content.Context;
import com.netflix.mediaclient.StatusCode;

class MslWidevineGetKeyRequestFailedErrorHandler extends BaseMslCryptoErrorHandler
{
    static boolean canHandle(final StatusCode statusCode) {
        return statusCode == StatusCode.DRM_FAILURE_MEDIADRM_GET_KEY_REQUEST;
    }
    
    @Override
    StatusCode getStatusCode() {
        return StatusCode.DRM_FAILURE_MEDIADRM_GET_KEY_REQUEST;
    }
    
    @Override
    public ErrorDescriptor handle(final Context context, final Throwable t) {
        Log.d(MslWidevineGetKeyRequestFailedErrorHandler.TAG, "MSL get key request failed. Device may recover on its own");
        return this.handleKillApp(context, t);
    }
}
