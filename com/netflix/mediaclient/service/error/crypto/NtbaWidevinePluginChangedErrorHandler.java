// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.service.configuration.crypto.WidevineErrorDescriptor;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import android.content.Context;
import com.netflix.mediaclient.StatusCode;

class NtbaWidevinePluginChangedErrorHandler extends BaseNtbaCryptoErrorHandler
{
    static boolean canHandle(final StatusCode statusCode) {
        return statusCode == StatusCode.DRM_FAILURE_MEDIADRM_WIDEVINE_PLUGIN_CHANGED;
    }
    
    @Override
    StatusCode getStatusCode() {
        return StatusCode.DRM_FAILURE_MEDIADRM_WIDEVINE_PLUGIN_CHANGED;
    }
    
    @Override
    public ErrorDescriptor handle(final Context context, final Throwable t) {
        Log.d(NtbaWidevinePluginChangedErrorHandler.TAG, "MediaDrm Widevine plugin changed");
        this.logHandledException(CryptoErrorManager.createMediaDrmErrorMessage(this.getStatusCode(), t));
        return new WidevineErrorDescriptor(context, this.getStatusCode(), this.getForceStopTask(context), 2131231081);
    }
}
