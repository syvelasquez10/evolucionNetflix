// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import android.content.Context;
import com.netflix.mediaclient.StatusCode;

public class NtbaWidevineGetKeyRequestFailedErrorHandler extends BaseNtbaCryptoErrorHandler
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
        Log.d(NtbaWidevineGetKeyRequestFailedErrorHandler.TAG, "NTBA get key request failed. Unregister device, logout user, and kill app process after error is displayed.");
        return this.handleKillApp(context, t);
    }
}
