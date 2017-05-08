// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.view.View$OnClickListener;
import android.widget.TextView$OnEditorActionListener;
import android.view.View$OnFocusChangeListener;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import java.util.Locale;
import com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.netflix.mediaclient.util.AndroidUtils;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.widget.Toast;
import com.netflix.mediaclient.NetflixApplication;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInConfigData;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential$Builder;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import java.io.Serializable;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gms.auth.api.credentials.Credential;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
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
    private static final int FLAG_TEXT_PASSWORD = 129;
    private static final int FLAG_TEXT_VISIBLE_PASSWORD = 145;
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
        this.loginQueryCallback = new LoginActivity$6(this);
    }
    
    private void attemptLogin() {
        Object o = null;
        this.emailView.setError((CharSequence)null);
        this.passwordView.setError((CharSequence)null);
        final String string = this.emailView.getText().toString();
        final String string2 = this.passwordView.getText().toString();
        boolean b = false;
        if (this.passwordIsInvalid(this.getServiceManager(), string2)) {
            final String string3 = this.getString(2131165409);
            this.reportCancel(string3);
            this.passwordView.setError((CharSequence)string3);
            o = this.passwordView;
            b = true;
        }
        if (this.emailIsInvalid(this.getServiceManager(), string)) {
            final String string4 = this.getString(2131165408);
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
        if (Log.isLoggable()) {
            Log.i("LoginActivity", "Locking orientation to: " + screenSensorOrientation);
        }
        this.setRequestedOrientation(screenSensorOrientation);
        this.statusMessageView.setText(2131165692);
        this.showProgress(true);
        SignInLogUtils.reportSignInRequestSessionStarted((Context)this, SignInLogging$SignInType.emailPassword);
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
                    while (true) {
                        Log.d("LoginActivity", "Trying to save credentials to GPS");
                        SignInLogUtils.reportCredentialStoreSessionStarted((Context)this, SignInLogging$CredentialService.GooglePlayService);
                        this.saveCredentials = false;
                        o = this.emailView.getText().toString();
                        string = this.passwordView.getText().toString();
                        Log.w("LoginActivity", "Credential is empty, do not save it.");
                        return;
                        continue;
                    }
                }
                // iftrue(Label_0099:, !StringUtils.isEmpty((String)o) && !StringUtils.isEmpty(string))
                // iftrue(Label_0015:, !this.saveCredentials)
                finally {
                }
                // monitorexit(this)
                Label_0099: {
                    o = new Credential$Builder((String)o).setPassword(string).build();
                }
                final GoogleApiClient googleApiClient2;
                Auth.CredentialsApi.save(googleApiClient2, (Credential)o).setResultCallback(new LoginActivity$5(this));
            }
        }
    }
    
    private boolean emailIsInvalid(final ServiceManager serviceManager, final String s) {
        boolean b = false;
        final boolean empty = StringUtils.isEmpty(s);
        while (true) {
            Label_0069: {
                if (serviceManager == null || !serviceManager.getConfiguration().isDynecomSignInEnabled()) {
                    break Label_0069;
                }
                final SignInConfigData signInConfigData = serviceManager.getConfiguration().getSignInConfigData();
                if (signInConfigData == null) {
                    break Label_0069;
                }
                int n;
                if (!signInConfigData.isEmailValid(s)) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (empty || n != 0) {
                    b = true;
                }
                return b;
            }
            int n = 0;
            continue;
        }
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
            SignInLogUtils.reportSignInRequestSessionEnded((Context)this, SignInLogging$SignInType.emailPassword, IClientLogging$CompletionReason.success, null);
            this.showDebugToast(2131165639);
            this.saveCredentials();
            return;
        }
        SignInLogUtils.reportSignInRequestSessionEnded((Context)this, SignInLogging$SignInType.emailPassword, IClientLogging$CompletionReason.failed, status.getError());
        this.handleUserAgentErrors(status);
        this.showProgress(false);
    }
    
    private void noConnectivityWarning() {
        this.runOnUiThread((Runnable)new LoginActivity$7(this));
    }
    
    private boolean passwordIsInvalid(final ServiceManager serviceManager, final String s) {
        boolean b = false;
        boolean b2;
        if (StringUtils.isEmpty(s) || s.length() < 4) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        while (true) {
            Label_0087: {
                if (serviceManager == null || !serviceManager.getConfiguration().isDynecomSignInEnabled()) {
                    break Label_0087;
                }
                final SignInConfigData signInConfigData = serviceManager.getConfiguration().getSignInConfigData();
                if (signInConfigData == null) {
                    break Label_0087;
                }
                int n;
                if (!signInConfigData.isPasswordValid(s)) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (b2 || n != 0) {
                    b = true;
                }
                return b;
            }
            int n = 0;
            continue;
        }
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
            Label_0099: {
                if (status == null || !status.hasResolution()) {
                    break Label_0099;
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
                    SignInLogUtils.reportCredentialStoreSessionEnded((Context)this, SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.failed, SignInLogUtils.credentialRequestResultToError(status));
                    continue;
                }
            }
            Log.e("LoginActivity", "Google Play Services: STATUS: FAIL");
            this.showDebugToast("Google Play Services: Could Not Resolve Error");
            SignInLogUtils.reportCredentialStoreSessionEnded((Context)this, SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.failed, SignInLogUtils.credentialRequestResultToError(status));
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
    
    private void showDebugToast(String string) {
        if (NetflixApplication.isDebugToastEnabled()) {
            string = "DEBUG: " + string;
            Log.v("LoginActivity", "Showing toast: " + string);
            Toast.makeText((Context)this, (CharSequence)string, 1).show();
        }
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
        return new LoginActivity$8(this);
    }
    
    @Override
    protected CustomerServiceLogging$EntryPoint getEntryPoint() {
        return CustomerServiceLogging$EntryPoint.login;
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
    protected String handleUserAgentErrors(final Status status) {
        final StatusCode statusCode = status.getStatusCode();
        if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_4 || statusCode == StatusCode.NRD_LOGIN_ACTIONID_8 || statusCode == StatusCode.USER_SIGNIN_RETRY) {
            final String string = this.getString(2131165552);
            this.passwordView.setError((CharSequence)string);
            this.reportError(status, string);
            return string;
        }
        if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_2) {
            final String string2 = this.getString(2131165689) + " (" + statusCode.getValue() + ")";
            this.displayServiceAgentDialog(string2, null, false);
            this.reportError(status, string2);
            return string2;
        }
        if (statusCode == StatusCode.NETWORK_ERROR) {
            final String string3 = this.getString(2131165691) + " (" + statusCode.getValue() + ")";
            this.displayServiceAgentDialog(string3, null, true);
            this.reportError(status, string3);
            return string3;
        }
        return super.handleUserAgentErrors(status);
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
            if (n2 == -1) {
                this.showDebugToast("Account credentials saved!");
                SignInLogUtils.reportCredentialStoreSessionEnded((Context)this, SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.success, null);
            }
            else {
                this.showDebugToast("Failed to save account credentials!");
                SignInLogUtils.reportCredentialStoreSessionEnded((Context)this, SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.failed, SignInLogUtils.credentialRequestResultToError(n2));
            }
        }
        else {
            Log.e("LoginActivity", "onActivityResult: uknown request code" + n);
        }
        this.handleBackToRegularWorkflow();
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
        AndroidUtils.setWindowSecureFlag(this);
        this.setContentView(2130903163);
        UserActionLogUtils.reportLoginActionEnded((Context)this, IClientLogging$CompletionReason.success, null);
        if (this.shouldUseAutoLogin()) {
            (this.credentialsApiClient = new GoogleApiClient$Builder((Context)this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Auth.CREDENTIALS_API).build()).connect();
        }
        (this.emailView = (EditText)this.findViewById(2131624357)).requestFocus();
        this.passwordView = (EditText)this.findViewById(2131624358);
        this.getCredentialAndState(this.getIntent());
        if (LocalizationUtils.isLocaleRTL(Locale.getDefault())) {
            this.passwordView.setGravity(5);
            this.passwordView.setOnFocusChangeListener((View$OnFocusChangeListener)new LoginActivity$1(this));
        }
        this.passwordView.setOnEditorActionListener((TextView$OnEditorActionListener)new LoginActivity$2(this));
        this.loginForm = this.findViewById(2131624355);
        this.loginButton = this.findViewById(2131624353);
        this.statusGroup = this.findViewById(2131624177);
        this.statusMessageView = (TextView)this.findViewById(2131624356);
        this.findViewById(2131624353).setOnClickListener((View$OnClickListener)new LoginActivity$3(this));
        this.findViewById(2131624354).setOnClickListener((View$OnClickListener)new LoginActivity$4(this));
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.credentialsApiClient != null) {
            this.credentialsApiClient.disconnect();
        }
    }
    
    @Override
    protected boolean showHelpInMenu() {
        return true;
    }
}
