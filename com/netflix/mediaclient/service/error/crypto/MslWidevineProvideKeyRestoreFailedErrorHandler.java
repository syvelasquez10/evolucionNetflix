// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import android.content.Context;
import com.netflix.mediaclient.StatusCode;

class MslWidevineProvideKeyRestoreFailedErrorHandler extends BaseMslCryptoErrorHandler
{
    static boolean canHandle(final StatusCode statusCode) {
        return statusCode == StatusCode.DRM_FAILURE_MEDIADRM_KEYS_RESTORE_FAILED;
    }
    
    @Override
    StatusCode getStatusCode() {
        return StatusCode.DRM_FAILURE_MEDIADRM_KEYS_RESTORE_FAILED;
    }
    
    @Override
    public ErrorDescriptor handle(final Context context, final Throwable t) {
        Log.d(MslWidevineProvideKeyRestoreFailedErrorHandler.TAG, "MediaDrm provide key restore failed. Kill app...");
        return this.handleKillApp(context, t);
    }
}
