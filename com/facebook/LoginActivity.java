// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.util.Log;
import com.facebook.android.R$id;
import com.facebook.android.R$layout;
import android.content.Intent;
import java.io.Serializable;
import android.os.Bundle;
import android.app.Activity;

public class LoginActivity extends Activity
{
    private static final String EXTRA_REQUEST = "request";
    private static final String NULL_CALLING_PKG_ERROR_MSG = "Cannot call LoginActivity with a null calling package. This can occur if the launchMode of the caller is singleInstance.";
    static final String RESULT_KEY = "com.facebook.LoginActivity:Result";
    private static final String SAVED_AUTH_CLIENT = "authorizationClient";
    private static final String SAVED_CALLING_PKG_KEY = "callingPackage";
    private static final String TAG;
    private AuthorizationClient authorizationClient;
    private String callingPackage;
    private AuthorizationClient$AuthorizationRequest request;
    
    static {
        TAG = LoginActivity.class.getName();
    }
    
    private void onAuthClientCompleted(final AuthorizationClient$Result authorizationClient$Result) {
        this.request = null;
        int n;
        if (authorizationClient$Result.code == AuthorizationClient$Result$Code.CANCEL) {
            n = 0;
        }
        else {
            n = -1;
        }
        final Bundle bundle = new Bundle();
        bundle.putSerializable("com.facebook.LoginActivity:Result", (Serializable)authorizationClient$Result);
        final Intent intent = new Intent();
        intent.putExtras(bundle);
        this.setResult(n, intent);
        this.finish();
    }
    
    static Bundle populateIntentExtras(final AuthorizationClient$AuthorizationRequest authorizationClient$AuthorizationRequest) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable("request", (Serializable)authorizationClient$AuthorizationRequest);
        return bundle;
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        this.authorizationClient.onActivityResult(n, n2, intent);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R$layout.com_facebook_login_activity_layout);
        if (bundle != null) {
            this.callingPackage = bundle.getString("callingPackage");
            this.authorizationClient = (AuthorizationClient)bundle.getSerializable("authorizationClient");
        }
        else {
            this.callingPackage = this.getCallingPackage();
            this.authorizationClient = new AuthorizationClient();
            this.request = (AuthorizationClient$AuthorizationRequest)this.getIntent().getSerializableExtra("request");
        }
        this.authorizationClient.setContext(this);
        this.authorizationClient.setOnCompletedListener(new LoginActivity$1(this));
        this.authorizationClient.setBackgroundProcessingListener(new LoginActivity$2(this));
    }
    
    public void onPause() {
        super.onPause();
        this.authorizationClient.cancelCurrentHandler();
        this.findViewById(R$id.com_facebook_login_activity_progress_bar).setVisibility(8);
    }
    
    public void onResume() {
        super.onResume();
        if (this.callingPackage == null) {
            Log.e(LoginActivity.TAG, "Cannot call LoginActivity with a null calling package. This can occur if the launchMode of the caller is singleInstance.");
            this.finish();
            return;
        }
        this.authorizationClient.startOrContinueAuth(this.request);
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("callingPackage", this.callingPackage);
        bundle.putSerializable("authorizationClient", (Serializable)this.authorizationClient);
    }
}
