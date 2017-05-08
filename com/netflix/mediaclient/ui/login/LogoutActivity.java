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
import android.widget.Toast;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
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
        PerformanceProfiler.getInstance().flushApmEvents(this.getApmSafely());
        UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging$CompletionReason.success, null);
        Toast.makeText(this.getApplicationContext(), 2131231179, 1).show();
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
        UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, s, ActionOnUIError.displayedError));
    }
    
    public static void showLogoutDialog(final Activity activity) {
        new AlertDialog$Builder((Context)activity).setMessage(2131231093).setNegativeButton(2131230991, (DialogInterface$OnClickListener)null).setPositiveButton(2131231178, (DialogInterface$OnClickListener)new LogoutActivity$1(activity)).show();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new LogoutActivity$2(this);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.logout;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }
    
    @Override
    protected boolean shouldReportNavigationActionEndedOnStop() {
        return false;
    }
}
