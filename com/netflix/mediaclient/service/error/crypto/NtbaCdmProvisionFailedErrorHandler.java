// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import android.content.Context;
import com.netflix.mediaclient.event.nrdp.registration.ActivateEvent;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

public class NtbaCdmProvisionFailedErrorHandler extends BaseNtbaCryptoErrorHandler
{
    private static final int ERROR_CODE = 15001;
    protected static final String TAG = "nf_user_error";
    protected static final String TRANSACTION = "cdmprovision";
    private AlertDialogFactory$AlertDialogDescriptor mMetadata;
    
    public static boolean canHandle(final StatusCode statusCode) {
        return statusCode == StatusCode.DRM_FAILURE_CDM;
    }
    
    public static boolean canHandle(final ActivateEvent activateEvent) {
        return activateEvent.getActionID() == 1 && activateEvent.getCode() == 15001 && "cdmprovision".equalsIgnoreCase(activateEvent.getTransaction());
    }
    
    @Override
    StatusCode getStatusCode() {
        return StatusCode.DRM_FAILURE_CDM;
    }
    
    @Override
    public ErrorDescriptor handle(final Context context, final Throwable t) {
        Log.d("nf_user_error", "NTBA CDM provisioning failed. Kill app process after error is displayed.");
        return this.handleKillApp(context, t);
    }
}
