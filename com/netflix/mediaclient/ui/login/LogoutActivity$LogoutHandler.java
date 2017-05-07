// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.ui.launch.RelaunchActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class LogoutActivity$LogoutHandler extends LoggingManagerCallback
{
    final /* synthetic */ LogoutActivity this$0;
    
    public LogoutActivity$LogoutHandler(final LogoutActivity this$0) {
        this.this$0 = this$0;
        super("LogoutActivity");
    }
    
    @Override
    public void onLogoutComplete(final Status status) {
        super.onLogoutComplete(status);
        if (status.isSucces()) {
            this.this$0.handleLogoutComplete();
            return;
        }
        if (Log.isLoggable()) {
            Log.e("LogoutActivity", "Could not log user out - status code: " + status.getStatusCode());
        }
        this.this$0.reportError(status, this.this$0.getString(2131165438));
        Toast.makeText(this.this$0.getApplicationContext(), 2131165438, 1).show();
        this.this$0.finish();
    }
}
