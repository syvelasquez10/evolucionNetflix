// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social;

import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.os.Bundle;
import java.io.Serializable;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.repository.SecurityRepository;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.facebook.SessionState;
import com.facebook.internal.SessionTracker;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.facebook.Session;
import com.netflix.mediaclient.ui.login.AccountActivity;

public class FacebookLoginActivity extends AccountActivity
{
    private static final String TAG = "FacebookLoginActivity";
    private final Session.StatusCallback facebookSdkStatusCallback;
    private ServiceManager manager;
    private SessionTracker tracker;
    
    public FacebookLoginActivity() {
        this.facebookSdkStatusCallback = new Session.StatusCallback() {
            @Override
            public void call(final Session session, final SessionState sessionState, final Exception ex) {
                Log.v("FacebookLoginActivity", "Callback - Session: " + session + ", State: " + sessionState + ", Exception: " + ex);
                if (sessionState.isOpened()) {
                    FacebookLoginActivity.this.executeMeRequestIfDebug();
                    FacebookLoginActivity.this.manager.connectWithFacebook(Session.getActiveSession().getAccessToken(), new ConnectedToFacebookCallback());
                }
                else if (sessionState.isClosed()) {
                    FacebookLoginActivity.this.handleConnectFailure();
                }
            }
        };
    }
    
    private void executeMeRequestIfDebug() {
    }
    
    private void handleConnectFailure() {
        Toast.makeText((Context)this, 2131296601, 1).show();
        this.finish();
    }
    
    private void handleConnectSuccess() {
        this.sendHomeRefreshBrodcast();
        Toast.makeText((Context)this, 2131296600, 1).show();
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
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                FacebookLoginActivity.this.manager = serviceManager;
                FacebookLoginActivity.this.initFacebookSession();
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
                FacebookLoginActivity.this.manager = null;
            }
        };
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.facebook;
    }
    
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
        this.getWindow().setBackgroundDrawableResource(2131165219);
        super.onCreate(bundle);
        this.printAppSignatureKeyIfDebug();
    }
    
    public void sendHomeRefreshBrodcast() {
        this.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
        Log.v("FacebookLoginActivity", "Intent REFRESH_HOME sent");
    }
    
    private class ConnectedToFacebookCallback extends LoggingManagerCallback
    {
        public ConnectedToFacebookCallback() {
            super("FacebookLoginActivity");
        }
        
        @Override
        public void onConnectWithFacebookComplete(final int n, final String s) {
            super.onConnectWithFacebookComplete(n, s);
            if (n == 0) {
                FacebookLoginActivity.this.handleConnectSuccess();
                return;
            }
            FacebookLoginActivity.this.handleConnectFailure();
        }
    }
}
