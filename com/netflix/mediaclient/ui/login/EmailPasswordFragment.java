// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.view.View$OnClickListener;
import android.widget.TextView$OnEditorActionListener;
import android.view.View$OnFocusChangeListener;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import java.util.Locale;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.google.android.gms.common.ConnectionResult;
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
import android.net.Uri;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.StatusCode;
import android.os.Bundle;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInConfigData;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credential$Builder;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.netflix.mediaclient.util.LoginUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import com.google.android.gms.common.api.Status;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.view.View;
import android.os.Handler;
import android.widget.EditText;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;

public class EmailPasswordFragment extends LoginBaseFragment implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    public static final String FRAGMENT_TAG = "EmailPasswordFragment";
    private static final String LOGIN_HELP_URL = "https://signup.netflix.com/loginhelp";
    private static final int MIN_PASSWORD_CHARS = 4;
    private GoogleApiClient mCredentialsApiClient;
    private EditText mEmailEditText;
    private final Handler mHandler;
    private View mLoginButton;
    private View mLoginForm;
    private final SimpleManagerCallback mLoginQueryCallback;
    private EditText mPasswordEditText;
    private boolean mSaveCredentials;
    private View mStatusGroup;
    private TextView mStatusMessageView;
    
    public EmailPasswordFragment() {
        this.mHandler = new Handler();
        this.mLoginQueryCallback = new EmailPasswordFragment$7(this);
    }
    
    private void attemptLogin() {
        Object o = null;
        this.mEmailEditText.setError((CharSequence)null);
        this.mPasswordEditText.setError((CharSequence)null);
        final String string = this.mEmailEditText.getText().toString();
        final String string2 = this.mPasswordEditText.getText().toString();
        boolean b = false;
        if (this.passwordIsInvalid(this.getServiceManager(), string2)) {
            final String string3 = this.getString(2131230957);
            this.reportCancel(string3);
            this.mPasswordEditText.setError((CharSequence)string3);
            o = this.mPasswordEditText;
            b = true;
        }
        if (this.emailIsInvalid(this.getServiceManager(), string)) {
            final String string4 = this.getString(2131230956);
            this.reportCancel(string4);
            this.mEmailEditText.setError((CharSequence)string4);
            o = this.mEmailEditText;
            b = true;
        }
        if (b) {
            if (Log.isLoggable()) {
                Log.i("LoginBaseFragment", "There was an error - skipping login and showing error msg");
            }
            ((View)o).requestFocus();
            return;
        }
        final ServiceManager serviceManager = this.getServiceManager();
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this.getActivity()) || serviceManager == null || !serviceManager.isReady()) {
            this.noConnectivityWarning();
            return;
        }
        DeviceUtils.lockScreenToSensorOrientation(this.getActivity());
        this.mStatusMessageView.setText(2131231238);
        this.showProgress(true);
        SignInLogUtils.reportSignInRequestSessionStarted((Context)this.getNetflixActivity(), SignInLogging$SignInType.emailPassword);
        if (serviceManager.isUserLoggedIn()) {
            serviceManager.logoutUser(new EmailPasswordFragment$LogoutNonMemberHandler(this));
            return;
        }
        serviceManager.loginUser(string, string2, this.mLoginQueryCallback);
    }
    
    private void connectToSmartLock() {
        if (LoginUtils.shouldUseAutoLogin((Context)this.getNetflixActivity())) {
            (this.mCredentialsApiClient = new GoogleApiClient$Builder((Context)this.getNetflixActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Auth.CREDENTIALS_API).build()).connect();
        }
    }
    
    private void doSaveCredentials(final GoogleApiClient googleApiClient) {
        // monitorenter(this)
        Label_0023: {
            if (googleApiClient != null) {
                break Label_0023;
            }
            Object o;
            String string;
            final GoogleApiClient googleApiClient2;
            Block_5_Outer:Block_7_Outer:
            while (true) {
                try {
                    if (Log.isLoggable()) {
                        Log.d("LoginBaseFragment", "GPS client is null, unable to try to save credentials");
                    }
                    Label_0020: {
                        return;
                    }
                    // iftrue(Label_0020:, !this.mSaveCredentials)
                    // iftrue(Label_0119:, !StringUtils.isEmpty((String)o) && !StringUtils.isEmpty(string))
                    // iftrue(Label_0045:, !Log.isLoggable())
                    while (true) {
                        Label_0096: {
                            while (true) {
                                Block_4: {
                                    break Block_4;
                                    while (true) {
                                        SignInLogUtils.reportCredentialStoreSessionStarted((Context)this.getNetflixActivity(), SignInLogging$CredentialService.GooglePlayService);
                                        this.mSaveCredentials = false;
                                        o = this.mEmailEditText.getText().toString();
                                        string = this.mPasswordEditText.getText().toString();
                                        break Label_0096;
                                        Log.d("LoginBaseFragment", "Trying to save credentials to GPS");
                                        continue Block_5_Outer;
                                    }
                                    Log.w("LoginBaseFragment", "Credential is empty, do not save it.");
                                    return;
                                }
                                continue Block_7_Outer;
                            }
                        }
                        continue;
                    }
                }
                // iftrue(Label_0020:, !Log.isLoggable())
                finally {
                }
                // monitorexit(this)
                Label_0119: {
                    o = new Credential$Builder((String)o).setPassword(string).build();
                }
                Auth.CredentialsApi.save(googleApiClient2, (Credential)o).setResultCallback(new EmailPasswordFragment$6(this));
            }
        }
    }
    
    private boolean emailIsInvalid(final ServiceManager serviceManager, final String s) {
        boolean b = false;
        final boolean empty = StringUtils.isEmpty(s);
        while (true) {
            Label_0076: {
                if (serviceManager == null || serviceManager.getConfiguration() == null || !serviceManager.getConfiguration().isDynecomSignInEnabled()) {
                    break Label_0076;
                }
                final SignInConfigData signInConfigData = serviceManager.getConfiguration().getSignInConfigData();
                if (signInConfigData == null) {
                    break Label_0076;
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
    
    private void getCredentialAndState(final Bundle bundle) {
        final String string = bundle.getString("email");
        final String string2 = bundle.getString("password");
        if (StringUtils.isNotEmpty(string)) {
            if (Log.isLoggable()) {
                Log.w("LoginBaseFragment", "We received credential");
            }
            this.mEmailEditText.setText((CharSequence)string);
            if (StringUtils.isNotEmpty(string2)) {
                this.mPasswordEditText.setText((CharSequence)string2);
            }
        }
        final com.netflix.mediaclient.android.app.Status status = (com.netflix.mediaclient.android.app.Status)bundle.getSerializable("status");
        if (status != null && Log.isLoggable()) {
            Log.w("LoginBaseFragment", "We received error status: " + status);
        }
    }
    
    private void handleLoginComplete(final com.netflix.mediaclient.android.app.Status status) {
        if (Log.isLoggable()) {
            Log.d("LoginBaseFragment", "Login Complete - Status: " + status);
        }
        this.getNetflixActivity().setRequestedOrientation(-1);
        if (status.isSucces() || status.getStatusCode() == StatusCode.NRD_REGISTRATION_EXISTS) {
            SignInLogUtils.reportSignInRequestSessionEnded((Context)this.getNetflixActivity(), SignInLogging$SignInType.emailPassword, IClientLogging$CompletionReason.success, null);
            this.getNetflixActivity().showDebugToast(this.getString(2131231185));
            this.saveCredentials();
            return;
        }
        SignInLogUtils.reportSignInRequestSessionEnded((Context)this.getNetflixActivity(), SignInLogging$SignInType.emailPassword, IClientLogging$CompletionReason.failed, status.getError());
        this.handleUserAgentErrors(status);
        this.showProgress(false);
    }
    
    private String handleUserAgentErrors(final com.netflix.mediaclient.android.app.Status status) {
        if (Log.isLoggable()) {
            Log.e("LoginBaseFragment", "Handling user agent errors, res: " + status);
        }
        final StatusCode statusCode = status.getStatusCode();
        if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_4 || statusCode == StatusCode.NRD_LOGIN_ACTIONID_8 || statusCode == StatusCode.USER_SIGNIN_RETRY) {
            final String string = this.getString(2131231090);
            this.mPasswordEditText.setError((CharSequence)string);
            this.reportError(status, string);
            return string;
        }
        if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_2) {
            final String string2 = this.getString(2131231235) + " (" + statusCode.getValue() + ")";
            this.getNetflixActivity().displayServiceAgentDialog(string2, null, false);
            this.reportError(status, string2);
            return string2;
        }
        if (statusCode == StatusCode.NETWORK_ERROR) {
            final String string3 = this.getString(2131231237) + " (" + statusCode.getValue() + ")";
            this.getNetflixActivity().displayServiceAgentDialog(string3, null, true);
            this.reportError(status, string3);
            return string3;
        }
        return this.getNetflixActivity().handleUserAgentErrors(status);
    }
    
    public static EmailPasswordFragment newInstance(final Bundle arguments) {
        final EmailPasswordFragment emailPasswordFragment = new EmailPasswordFragment();
        emailPasswordFragment.setArguments(arguments);
        return emailPasswordFragment;
    }
    
    private void noConnectivityWarning() {
        this.getActivity().runOnUiThread((Runnable)new EmailPasswordFragment$5(this));
    }
    
    private void onForgotPassword() {
        final Intent setData = new Intent("android.intent.action.VIEW").setData(Uri.parse("https://signup.netflix.com/loginhelp"));
        if (setData.resolveActivity(this.getNetflixActivity().getPackageManager()) != null) {
            this.startActivityForResult(setData, 0);
            return;
        }
        this.getNetflixActivity().displayServiceAgentDialog(this.getString(2131231227, new Object[] { "https://signup.netflix.com/loginhelp" }), null, false);
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
            Label_0094: {
                if (serviceManager == null || serviceManager.getConfiguration() == null || !serviceManager.getConfiguration().isDynecomSignInEnabled()) {
                    break Label_0094;
                }
                final SignInConfigData signInConfigData = serviceManager.getConfiguration().getSignInConfigData();
                if (signInConfigData == null) {
                    break Label_0094;
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
        UserActionLogUtils.reportNavigationActionEnded((Context)this.getNetflixActivity(), this.getNetflixActivity().getUiScreen(), IClientLogging$CompletionReason.canceled, new UIError(RootCause.clientFailure, ActionOnUIError.displayedError, s, null));
        UserActionLogUtils.reportNavigationActionStarted((Context)this.getNetflixActivity(), null, this.getNetflixActivity().getUiScreen());
    }
    
    private void reportError(final com.netflix.mediaclient.android.app.Status status, final String s) {
        UserActionLogUtils.reportNavigationActionEnded((Context)this.getNetflixActivity(), this.getNetflixActivity().getUiScreen(), IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, s, ActionOnUIError.displayedError));
        UserActionLogUtils.reportNavigationActionStarted((Context)this.getNetflixActivity(), null, this.getNetflixActivity().getUiScreen());
    }
    
    private void resolveResult(final Status status) {
        if (Log.isLoggable()) {
            Log.d("LoginBaseFragment", "Google Play Services: Resolving: " + status);
        }
        while (true) {
            if (this.getNetflixActivity() == null) {
                Log.e("LoginBaseFragment", "NetflixActivity is null -  skipping startResolutionForResult");
                final int n = 1;
                break Label_0076;
            }
            Label_0130: {
                if (status == null || !status.hasResolution()) {
                    break Label_0130;
                }
                if (Log.isLoggable()) {
                    Log.d("LoginBaseFragment", "Google Play Services: STATUS: RESOLVING");
                }
                try {
                    status.startResolutionForResult(this.getNetflixActivity(), 1);
                    final int n = 0;
                    if (n != 0 && this.mLoginFragmentListener != null) {
                        this.mLoginFragmentListener.handleBackToRegularWorkflow();
                    }
                    return;
                }
                catch (IntentSender$SendIntentException ex) {
                    Log.e("LoginBaseFragment", "Google Play Services: STATUS: Failed to send resolution.", (Throwable)ex);
                    SignInLogUtils.reportCredentialStoreSessionEnded((Context)this.getNetflixActivity(), SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.failed, SignInLogUtils.credentialRequestResultToError(status));
                    final int n = 1;
                    continue;
                }
            }
            Log.e("LoginBaseFragment", "Google Play Services: STATUS: FAIL");
            this.getNetflixActivity().showDebugToast("Google Play Services: Could Not Resolve Error");
            SignInLogUtils.reportCredentialStoreSessionEnded((Context)this.getNetflixActivity(), SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.failed, SignInLogUtils.credentialRequestResultToError(status));
            final int n = 1;
            continue;
        }
    }
    
    private void saveCredentials() {
        while (true) {
            Label_0062: {
                synchronized (this) {
                    if (!LoginUtils.shouldUseAutoLogin((Context)this.getActivity())) {
                        if (Log.isLoggable()) {
                            Log.d("LoginBaseFragment", "SmartLock is disabled or device does not support GPS");
                        }
                    }
                    else {
                        if (this.mCredentialsApiClient != null) {
                            break Label_0062;
                        }
                        if (Log.isLoggable()) {
                            Log.d("LoginBaseFragment", "GPS client unavailable, unable to attempt to save credentials");
                        }
                    }
                    return;
                }
            }
            final GoogleApiClient googleApiClient;
            if (Log.isLoggable()) {
                Log.d("LoginBaseFragment", "GPS client is connected " + googleApiClient.isConnected() + " or connecting " + googleApiClient.isConnecting());
            }
            this.mSaveCredentials = true;
            if (googleApiClient.isConnected()) {
                this.doSaveCredentials(googleApiClient);
            }
        }
    }
    
    private void showProgress(final boolean b) {
        final int n = 8;
        boolean enabled = false;
        final View mStatusGroup = this.mStatusGroup;
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        mStatusGroup.setVisibility(visibility);
        final View mLoginForm = this.mLoginForm;
        int visibility2;
        if (b) {
            visibility2 = n;
        }
        else {
            visibility2 = 0;
        }
        mLoginForm.setVisibility(visibility2);
        final View mLoginButton = this.mLoginButton;
        if (!b) {
            enabled = true;
        }
        mLoginButton.setEnabled(enabled);
    }
    
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.getCredentialAndState(bundle);
        }
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("LoginBaseFragment", "onConnected");
            }
            this.doSaveCredentials(this.mCredentialsApiClient);
        }
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        if (Log.isLoggable()) {
            Log.d("LoginBaseFragment", "onConnectionFailed:" + connectionResult);
        }
        this.mCredentialsApiClient = null;
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        if (Log.isLoggable()) {
            Log.d("LoginBaseFragment", "onConnectionSuspended:" + n);
        }
        if (this.mCredentialsApiClient != null) {
            this.mCredentialsApiClient.reconnect();
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        if (Log.isLoggable()) {
            Log.i("LoginBaseFragment", "EmailPasswordFragment onCreateView");
        }
        final View inflate = layoutInflater.inflate(2130903171, viewGroup, false);
        this.setupViews(inflate);
        this.connectToSmartLock();
        return inflate;
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        this.mLoginFragmentListener = null;
        if (this.mCredentialsApiClient != null) {
            this.mCredentialsApiClient.disconnect();
        }
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final com.netflix.mediaclient.android.app.Status status) {
        super.onManagerReady(serviceManager, status);
        if (Log.isLoggable()) {
            Log.i("LoginBaseFragment", "EmailPasswordFragment onManagerReady");
        }
    }
    
    @Override
    protected void setupViews(final View view) {
        super.setupViews(view);
        (this.mEmailEditText = (EditText)view.findViewById(2131689917)).requestFocus();
        this.mPasswordEditText = (EditText)view.findViewById(2131689918);
        this.mLoginForm = view.findViewById(2131689915);
        this.mLoginButton = view.findViewById(2131689913);
        this.mStatusGroup = view.findViewById(2131689755);
        this.mStatusMessageView = (TextView)view.findViewById(2131689916);
        if (LocalizationUtils.isLocaleRTL(Locale.getDefault())) {
            this.mPasswordEditText.setGravity(5);
            this.mPasswordEditText.setOnFocusChangeListener((View$OnFocusChangeListener)new EmailPasswordFragment$1(this));
        }
        this.mPasswordEditText.setOnEditorActionListener((TextView$OnEditorActionListener)new EmailPasswordFragment$2(this));
        view.findViewById(2131689913).setOnClickListener((View$OnClickListener)new EmailPasswordFragment$3(this));
        view.findViewById(2131689914).setOnClickListener((View$OnClickListener)new EmailPasswordFragment$4(this));
    }
}
