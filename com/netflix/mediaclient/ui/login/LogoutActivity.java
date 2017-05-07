// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.ui.RelaunchActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.Toast;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.android.app.Status;

public class LogoutActivity extends AccountActivity
{
    private static final String TAG = "LogoutActivity";
    
    public static Intent create(final Context context) {
        return new Intent(context, (Class)LogoutActivity.class);
    }
    
    private void handleLogoutComplete() {
        Log.i("LogoutActivity", "Handling logout completion...");
        UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging.CompletionReason.success, null);
        Toast.makeText(this.getApplicationContext(), 2131493222, 1).show();
        relaunchApp(this, "handleLogoutComplete()");
    }
    
    public static void relaunchApp(final NetflixActivity netflixActivity, final String s) {
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        if (serviceManager == null) {
            Log.w("LogoutActivity", "ServiceManager is null - can't flush caches!");
        }
        else {
            serviceManager.getBrowse().flushCaches();
        }
        NetflixActivity.finishAllActivities((Context)netflixActivity);
        netflixActivity.startActivity(RelaunchActivity.createStartIntent(netflixActivity, s));
    }
    
    private void reportError(final Status status, final String s) {
        UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging.CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, s, ActionOnUIError.displayedError));
    }
    
    public static void showLogoutDialog(final Activity activity) {
        new AlertDialog$Builder((Context)activity).setMessage(2131493220).setNegativeButton(2131493127, (DialogInterface$OnClickListener)null).setPositiveButton(2131493191, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                activity.startActivity(LogoutActivity.create((Context)activity));
                activity.overridePendingTransition(0, 0);
                activity.finish();
            }
        }).show();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                serviceManager.logoutUser(new LogoutHandler());
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
            }
        };
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.logout;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }
    
    @Override
    protected boolean shouldReportNavigationActionEndedOnStop() {
        return false;
    }
    
    private class LogoutHandler extends LoggingManagerCallback
    {
        public LogoutHandler() {
            super("LogoutActivity");
        }
        
        @Override
        public void onLogoutComplete(final Status status) {
            super.onLogoutComplete(status);
            if (status.isSucces()) {
                LogoutActivity.this.handleLogoutComplete();
                return;
            }
            if (Log.isLoggable("LogoutActivity", 6)) {
                Log.e("LogoutActivity", "Could not log user out - status code: " + status.getStatusCode());
            }
            LogoutActivity.this.reportError(status, LogoutActivity.this.getString(2131493221));
            Toast.makeText(LogoutActivity.this.getApplicationContext(), 2131493221, 1).show();
            LogoutActivity.this.finish();
        }
    }
}
