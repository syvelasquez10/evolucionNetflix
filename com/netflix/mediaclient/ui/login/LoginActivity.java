// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.view.View$OnClickListener;
import android.widget.TextView$OnEditorActionListener;
import com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.IntentSender$SendIntentException;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.app.Activity;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential$Builder;
import java.io.Serializable;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gms.auth.api.credentials.Credential;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import android.os.Handler;
import android.widget.TextView;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;

public class LoginActivity extends AccountActivity implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    public static final String EXTRA_EMAIL = "email";
    private static final String EXTRA_PASSWORD = "password";
    private static final String EXTRA_STATUS = "status";
    private static final int MIN_PASSWORD_CHARS = 4;
    private static final int RC_SAVE = 1;
    private static final String TAG = "LoginActivity";
    private static final String loginHelpUrl = "https://signup.netflix.com/loginhelp";
    private GoogleApiClient credentialsApiClient;
    private EditText emailView;
    private boolean launchProfileGate;
    private View loginButton;
    private View loginForm;
    private final SimpleManagerCallback loginQueryCallback;
    private EditText passwordView;
    private boolean saveCredentials;
    private Status status;
    private View statusGroup;
    private TextView statusMessageView;
    
    public LoginActivity() {
        this.loginQueryCallback = new LoginActivity$5(this);
    }
    
    private void attemptLogin() {
        Object o = null;
        this.emailView.setError((CharSequence)null);
        this.passwordView.setError((CharSequence)null);
        final String string = this.emailView.getText().toString();
        final String string2 = this.passwordView.getText().toString();
        boolean b = false;
        if (this.passwordIsInvalid(string2)) {
            final String string3 = this.getString(2131493198);
            this.reportCancel(string3);
            this.passwordView.setError((CharSequence)string3);
            o = this.passwordView;
            b = true;
        }
        if (this.emailIsInvalid(string)) {
            final String string4 = this.getString(2131493197);
            this.reportCancel(string4);
            this.emailView.setError((CharSequence)string4);
            o = this.emailView;
            b = true;
        }
        if (b) {
            Log.i("LoginActivity", "There was an error - skipping login and showing error msg");
            ((View)o).requestFocus();
            return;
        }
        final ServiceManager serviceManager = this.getServiceManager();
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this) || serviceManager == null || !serviceManager.isReady()) {
            this.noConnectivityWarning();
            return;
        }
        final int screenSensorOrientation = DeviceUtils.getScreenSensorOrientation((Context)this);
        Log.i("LoginActivity", "Locking orientation to: " + screenSensorOrientation);
        this.setRequestedOrientation(screenSensorOrientation);
        this.statusMessageView.setText(2131493196);
        this.showProgress(true);
        serviceManager.loginUser(string, string2, this.loginQueryCallback);
    }
    
    public static Intent createStartIntent(final Context context) {
        return createStartIntent(context, null, null);
    }
    
    public static Intent createStartIntent(final Context context, final Credential credential, final Status status) {
        final Intent intent = new Intent(context, (Class)LoginActivity.class);
        if (credential != null) {
            final String id = credential.getId();
            final String password = credential.getPassword();
            if (Log.isLoggable()) {
                Log.d("LoginActivity", "Credentials received " + credential);
                Log.d("LoginActivity", "Email in credentials: " + id);
                Log.d("LoginActivity", "Password in credentials: " + password);
            }
            if (StringUtils.isNotEmpty(id)) {
                intent.putExtra("email", id);
                if (StringUtils.isNotEmpty(password)) {
                    intent.putExtra("password", password);
                }
            }
            if (status != null) {
                intent.putExtra("status", (Serializable)status);
            }
        }
        return intent;
    }
    
    private void doSaveCredentials(final GoogleApiClient googleApiClient) {
        // monitorenter(this)
        Label_0018: {
            if (googleApiClient != null) {
                break Label_0018;
            }
            while (true) {
                Object o;
                String string;
                try {
                    Log.d("LoginActivity", "GPS client is null, unable to try to save credentials");
                    Label_0015: {
                        return;
                    }
                    // iftrue(Label_0015:, !this.saveCredentials)
                    Log.d("LoginActivity", "Trying to save credentials to GPS");
                    this.saveCredentials = false;
                    o = this.emailView.getText().toString();
                    string = this.passwordView.getText().toString();
                    // iftrue(Label_0092:, !StringUtils.isEmpty((String)o) && !StringUtils.isEmpty(string))
                    Log.w("LoginActivity", "Credential is empty, do not save it.");
                    return;
                }
                finally {
                }
                // monitorexit(this)
                Label_0092: {
                    o = new Credential$Builder((String)o).setPassword(string).build();
                }
                final GoogleApiClient googleApiClient2;
                Auth.CredentialsApi.save(googleApiClient2, (Credential)o).setResultCallback(new LoginActivity$4(this));
            }
        }
    }
    
    private boolean emailIsInvalid(final String s) {
        return StringUtils.isEmpty(s);
    }
    
    private void getCredentialAndState(final Intent intent) {
        Log.d("LoginActivity", intent);
        final String stringExtra = intent.getStringExtra("email");
        final String stringExtra2 = intent.getStringExtra("password");
        if (StringUtils.isNotEmpty(stringExtra)) {
            Log.w("LoginActivity", "We received credential");
            this.emailView.setText((CharSequence)stringExtra);
            if (StringUtils.isNotEmpty(stringExtra2)) {
                this.passwordView.setText((CharSequence)stringExtra2);
            }
        }
        this.status = (Status)intent.getSerializableExtra("status");
        if (this.status != null && Log.isLoggable()) {
            Log.w("LoginActivity", "We received error status: " + this.status);
        }
    }
    
    private void handleBackToRegularWorkflow() {
        if (this.launchProfileGate) {
            Log.d("LoginActivity", "New profile requested - starting profile selection activity...");
            this.startActivity(ProfileSelectionActivity.createStartIntent((Context)this));
            AccountActivity.finishAllAccountActivities((Context)this);
            return;
        }
        Log.d("LoginActivity", "Back to regular workflow for profile activated...");
        this.finish();
    }
    
    private void handleLoginComplete(final Status status) {
        if (Log.isLoggable()) {
            Log.d("LoginActivity", "Login Complete - Status: " + status);
        }
        this.setRequestedOrientation(-1);
        if (status.isSucces() || status.getStatusCode() == StatusCode.NRD_REGISTRATION_EXISTS) {
            this.showDebugToast(2131493203);
            this.saveCredentials();
            return;
        }
        this.handleUserAgentErrors(this, status);
        this.showProgress(false);
    }
    
    private void noConnectivityWarning() {
        this.runOnUiThread((Runnable)new LoginActivity$6(this));
    }
    
    private boolean passwordIsInvalid(final String s) {
        return StringUtils.isEmpty(s) || s.length() < 4;
    }
    
    private void reportCancel(final String s) {
        UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging$CompletionReason.canceled, new UIError(RootCause.clientFailure, ActionOnUIError.displayedError, s, null));
        UserActionLogUtils.reportNavigationActionStarted((Context)this, null, this.getUiScreen());
    }
    
    private void reportError(final Status status, final String s) {
        UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, s, ActionOnUIError.displayedError));
        UserActionLogUtils.reportNavigationActionStarted((Context)this, null, this.getUiScreen());
    }
    
    private void resolveResult(final com.google.android.gms.common.api.Status status) {
        boolean b = true;
        if (Log.isLoggable()) {
            Log.d("LoginActivity", "Google Play Services: Resolving: " + status);
        }
        while (true) {
            Label_0085: {
                if (status == null || !status.hasResolution()) {
                    break Label_0085;
                }
                Log.d("LoginActivity", "Google Play Services: STATUS: RESOLVING");
                try {
                    status.startResolutionForResult(this, 1);
                    b = false;
                    if (b) {
                        this.handleBackToRegularWorkflow();
                    }
                    return;
                }
                catch (IntentSender$SendIntentException ex) {
                    Log.e("LoginActivity", "Google Play Services: STATUS: Failed to send resolution.", (Throwable)ex);
                    continue;
                }
            }
            Log.e("LoginActivity", "Google Play Services: STATUS: FAIL");
            this.showDebugToast("Google Play Services: Could Not Resolve Error");
            continue;
        }
    }
    
    private void saveCredentials() {
        while (true) {
            Label_0047: {
                synchronized (this) {
                    if (!this.shouldUseAutoLogin()) {
                        Log.d("LoginActivity", "SmartLock is disabled or device does not support GPS");
                    }
                    else {
                        if (this.credentialsApiClient != null) {
                            break Label_0047;
                        }
                        Log.d("LoginActivity", "GPS client unavailable, unable to attempt to save credentials");
                    }
                    return;
                }
            }
            final GoogleApiClient googleApiClient;
            if (Log.isLoggable()) {
                Log.d("LoginActivity", "GPS client is connected " + googleApiClient.isConnected() + " or connecting " + googleApiClient.isConnecting());
            }
            this.saveCredentials = true;
            if (googleApiClient.isConnected()) {
                this.doSaveCredentials(googleApiClient);
            }
        }
    }
    
    private boolean shouldUseAutoLogin() {
        return this.isAutoLoginEnabled() && DeviceUtils.canUseGooglePlayServices((Context)this);
    }
    
    private void showDebugToast(final int n) {
        this.showDebugToast(this.getString(n));
    }
    
    private void showDebugToast(final String s) {
    }
    
    private void showProgress(final boolean b) {
        final int n = 8;
        boolean enabled = false;
        final View statusGroup = this.statusGroup;
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        statusGroup.setVisibility(visibility);
        final View loginForm = this.loginForm;
        int visibility2;
        if (b) {
            visibility2 = n;
        }
        else {
            visibility2 = 0;
        }
        loginForm.setVisibility(visibility2);
        final View loginButton = this.loginButton;
        if (!b) {
            enabled = true;
        }
        loginButton.setEnabled(enabled);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new LoginActivity$7(this);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.login;
    }
    
    @Override
    protected void handleAccountDeactivated() {
        Log.i("LoginActivity", "Account deactivated ...");
    }
    
    @Override
    protected void handleProfileActivated() {
        if (this.shouldUseAutoLogin()) {
            Log.d("LoginActivity", "SmartLogin save enabled, do save credentials for profile activated...");
            this.launchProfileGate = false;
            return;
        }
        Log.d("LoginActivity", "SmartLogin save not enabled, regular workflow for profile activated...");
        this.finish();
    }
    
    @Override
    protected void handleProfileReadyToSelect() {
        Log.i("LoginActivity", "New profile requested - starting profile selection activity...");
        if (this.shouldUseAutoLogin()) {
            Log.d("LoginActivity", "SmartLogin save enabled, do save credentials...");
            this.launchProfileGate = true;
            return;
        }
        Log.d("LoginActivity", "SmartLogin save not enabled, regular workflow...");
        this.startActivity(ProfileSelectionActivity.createStartIntent((Context)this));
        AccountActivity.finishAllAccountActivities((Context)this);
    }
    
    @Override
    protected String handleUserAgentErrors(final Activity activity, final Status status) {
        final StatusCode statusCode = status.getStatusCode();
        if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_4 || statusCode == StatusCode.NRD_LOGIN_ACTIONID_8) {
            final String string = this.getString(2131493199);
            this.passwordView.setError((CharSequence)string);
            this.reportError(status, string);
            return string;
        }
        if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_2) {
            final String string2 = this.getString(2131493256) + " (" + statusCode.getValue() + ")";
            this.displayUserAgentDialog(string2, null, false);
            this.reportError(status, string2);
            return string2;
        }
        if (statusCode == StatusCode.NETWORK_ERROR) {
            final String string3 = this.getString(2131493257) + " (" + statusCode.getValue() + ")";
            this.displayUserAgentDialog(string3, null, true);
            this.reportError(status, string3);
            return string3;
        }
        return super.handleUserAgentErrors(activity, status);
    }
    
    protected boolean isAutoLoginEnabled() {
        return true;
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (Log.isLoggable()) {
            Log.d("LoginActivity", "onActivityResult:" + n + ":" + n2 + ":" + intent);
        }
        if (n == 1) {
            this.showDebugToast("Account credentials saved!");
            this.handleBackToRegularWorkflow();
            return;
        }
        Log.e("LoginActivity", "onActivityResult: uknown request code" + n);
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        synchronized (this) {
            Log.d("LoginActivity", "onConnected");
            this.doSaveCredentials(this.credentialsApiClient);
        }
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        if (Log.isLoggable()) {
            Log.d("LoginActivity", "onConnectionFailed:" + connectionResult);
        }
        this.credentialsApiClient = null;
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        if (Log.isLoggable()) {
            Log.d("LoginActivity", "onConnectionSuspended:" + n);
        }
        if (this.credentialsApiClient != null) {
            this.credentialsApiClient.reconnect();
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903131);
        UserActionLogUtils.reportLoginActionEnded((Context)this, IClientLogging$CompletionReason.success, null);
        if (this.shouldUseAutoLogin()) {
            (this.credentialsApiClient = new GoogleApiClient$Builder((Context)this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Auth.CREDENTIALS_API).build()).connect();
        }
        (this.emailView = (EditText)this.findViewById(2131427621)).requestFocus();
        this.passwordView = (EditText)this.findViewById(2131427622);
        this.getCredentialAndState(this.getIntent());
        this.passwordView.setOnEditorActionListener((TextView$OnEditorActionListener)new LoginActivity$1(this));
        this.loginForm = this.findViewById(2131427620);
        this.loginButton = this.findViewById(2131427618);
        this.statusGroup = this.findViewById(2131427498);
        this.statusMessageView = (TextView)this.findViewById(2131427624);
        this.findViewById(2131427618).setOnClickListener((View$OnClickListener)new LoginActivity$2(this));
        this.findViewById(2131427619).setOnClickListener((View$OnClickListener)new LoginActivity$3(this));
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.credentialsApiClient != null) {
            this.credentialsApiClient.disconnect();
        }
    }
}
