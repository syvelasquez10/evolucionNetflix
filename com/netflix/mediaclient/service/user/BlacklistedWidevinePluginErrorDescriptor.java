// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import android.app.Activity;
import com.netflix.mediaclient.event.nrdp.registration.ActivateEvent;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.service.error.ErrorDescriptor;

public class BlacklistedWidevinePluginErrorDescriptor implements ErrorDescriptor
{
    private static final int BLACKLISTED_WIDEVINE_PLUGIN = 15003;
    protected static final String TAG = "nf_user_error";
    private AlertDialogFactory$AlertDialogDescriptor mMetadata;
    
    public BlacklistedWidevinePluginErrorDescriptor(final Context context) {
        Log.d("nf_user_error", "actionID 1 15003, Widevine blacklisted...");
        this.mMetadata = new AlertDialogFactory$AlertDialogDescriptor("", context.getString(2131230879, new Object[] { 15003 }), null, new BlacklistedWidevinePluginErrorDescriptor$1(this, context));
    }
    
    static boolean canHandle(final ActivateEvent activateEvent) {
        return activateEvent.getActionID() == 1 && activateEvent.getCode() == 15003;
    }
    
    @Override
    public Runnable getBackgroundTask() {
        return null;
    }
    
    @Override
    public AlertDialogFactory$AlertDialogDescriptor getData() {
        return this.mMetadata;
    }
    
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public boolean shouldReportToUserAsDialog(final Activity activity) {
        return true;
    }
}
