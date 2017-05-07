// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.app.Activity;
import com.netflix.mediaclient.ui.LaunchActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;

public class LogoutActivity extends AccountActivity
{
    private static final String TAG = "LogoutActivity";
    
    public static Intent create(final Context context) {
        return new Intent(context, (Class)LogoutActivity.class);
    }
    
    private void handleLogoutComplete() {
        Log.i("LogoutActivity", "Handling logout completion...");
        Toast.makeText(this.getApplicationContext(), 2131296589, 1).show();
        this.getServiceManager().flushCaches();
        NetflixActivity.finishAllActivities((Context)this);
        this.startActivity(LaunchActivity.createStartIntent(this, "handleLogoutComplete()"));
    }
    
    public static void showLogoutDialog(final Activity activity) {
        new AlertDialog$Builder((Context)activity).setMessage(2131296587).setNegativeButton(2131296494, (DialogInterface$OnClickListener)null).setPositiveButton(2131296559, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                activity.startActivity(LogoutActivity.create((Context)activity));
                activity.finish();
            }
        }).show();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                serviceManager.logoutUser(new LogoutHandler());
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
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
    
    private class LogoutHandler extends LoggingManagerCallback
    {
        public LogoutHandler() {
            super("LogoutActivity");
        }
        
        @Override
        public void onLogoutComplete(final int n) {
            super.onLogoutComplete(n);
            if (n == 0) {
                LogoutActivity.this.handleLogoutComplete();
                return;
            }
            Log.e("LogoutActivity", "Could not log user out - status code: " + n);
            Toast.makeText(LogoutActivity.this.getApplicationContext(), 2131296588, 1).show();
            LogoutActivity.this.finish();
        }
    }
}
