// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social;

import android.os.Bundle;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Intent;
import android.app.Activity;
import com.facebook.Session;
import com.netflix.mediaclient.repository.SecurityRepository;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.facebook.Request$GraphUserCallback;
import com.facebook.Session$StatusCallback;
import com.netflix.mediaclient.ui.login.AccountActivity;

public class FacebookLoginActivity extends AccountActivity
{
    private static final String TAG = "FacebookLoginActivity";
    private final Session$StatusCallback facebookSdkStatusCallback;
    private final Request$GraphUserCallback graphUserCallback;
    private ServiceManager manager;
    
    public FacebookLoginActivity() {
        this.graphUserCallback = new FacebookLoginActivity$2(this);
        this.facebookSdkStatusCallback = new FacebookLoginActivity$3(this);
    }
    
    private void executeMeRequestIfDebug() {
    }
    
    private void handleConnectFailure() {
        Toast.makeText((Context)this, 2131165433, 1).show();
        this.finish();
    }
    
    private void handleConnectSuccess() {
        this.sendHomeRefreshBrodcast();
        Toast.makeText((Context)this, 2131165408, 1).show();
        this.finish();
    }
    
    private void initFacebookSession() {
        Session.openActiveSession(this, true, this.facebookSdkStatusCallback, SecurityRepository.getFacebookId());
    }
    
    private void printAppSignatureKeyIfDebug() {
    }
    
    public static void show(final Activity activity) {
        activity.startActivity(new Intent((Context)activity, (Class)FacebookLoginActivity.class));
        activity.overridePendingTransition(0, 0);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new FacebookLoginActivity$1(this);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.facebook;
    }
    
    @Override
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        final StringBuilder append = new StringBuilder().append("onActivityResult: ").append(n).append(", result: ");
        Serializable value;
        if (n2 == -1) {
            value = "ok";
        }
        else if (n2 == 0) {
            value = "cancelled";
        }
        else {
            value = n2;
        }
        Log.v("FacebookLoginActivity", append.append(value).toString());
        final Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            activeSession.onActivityResult(this, n, n2, intent);
        }
        Log.v("FacebookLoginActivity", "onActivityResult session: " + activeSession);
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.getWindow().requestFeature(1);
        this.getWindow().setBackgroundDrawableResource(2131558590);
        super.onCreate(bundle);
        this.printAppSignatureKeyIfDebug();
    }
    
    public void sendHomeRefreshBrodcast() {
        this.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
        Log.v("FacebookLoginActivity", "Intent REFRESH_HOME sent");
    }
}
