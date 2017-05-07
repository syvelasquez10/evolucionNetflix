// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.view.View$OnClickListener;
import android.widget.TextView$OnEditorActionListener;
import android.os.Bundle;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
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
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AccountActivity
{
    private static final int MIN_PASSWORD_CHARS = 4;
    private static final String TAG = "LoginActivity";
    private static final String loginHelpUrl = "https://signup.netflix.com/loginhelp";
    private EditText emailView;
    private View loginButton;
    private View loginForm;
    private final SimpleManagerCallback loginQueryCallback;
    private EditText passwordView;
    private View statusGroup;
    private TextView statusMessageView;
    
    public LoginActivity() {
        this.loginQueryCallback = new LoginActivity$4(this);
    }
    
    private void attemptLogin() {
        Object o = null;
        this.emailView.setError((CharSequence)null);
        this.passwordView.setError((CharSequence)null);
        final String string = this.emailView.getText().toString();
        final String string2 = this.passwordView.getText().toString();
        boolean b = false;
        if (this.passwordIsInvalid(string2)) {
            final String string3 = this.getString(2131493190);
            this.reportCancel(string3);
            this.passwordView.setError((CharSequence)string3);
            o = this.passwordView;
            b = true;
        }
        if (this.emailIsInvalid(string)) {
            final String string4 = this.getString(2131493189);
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
        this.statusMessageView.setText(2131493188);
        this.showProgress(true);
        serviceManager.loginUser(string, string2, this.loginQueryCallback);
    }
    
    public static Intent createStartIntent(final Context context) {
        return new Intent(context, (Class)LoginActivity.class);
    }
    
    private boolean emailIsInvalid(final String s) {
        return StringUtils.isEmpty(s);
    }
    
    private void handleLoginComplete(final Status status) {
        if (Log.isLoggable("LoginActivity", 3)) {
            Log.d("LoginActivity", "Login Complete - Status: " + status);
        }
        this.setRequestedOrientation(-1);
        if (status.isSucces() || status.getStatusCode() == StatusCode.NRD_REGISTRATION_EXISTS) {
            this.showDebugToast(2131493195);
            return;
        }
        this.handleUserAgentErrors(this, status);
        this.showProgress(false);
    }
    
    private void noConnectivityWarning() {
        this.runOnUiThread((Runnable)new LoginActivity$5(this));
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
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.login;
    }
    
    @Override
    protected void handleAccountDeactivated() {
        Log.i("LoginActivity", "Account deactivated ...");
    }
    
    @Override
    protected void handleProfileReadyToSelect() {
        Log.i("LoginActivity", "New profile requested - starting profile selection activity...");
        this.startActivity(ProfileSelectionActivity.createStartIntent((Context)this));
        AccountActivity.finishAllAccountActivities((Context)this);
    }
    
    @Override
    protected String handleUserAgentErrors(final Activity activity, final Status status) {
        final StatusCode statusCode = status.getStatusCode();
        if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_4 || statusCode == StatusCode.NRD_LOGIN_ACTIONID_8) {
            final String string = this.getString(2131493191);
            this.passwordView.setError((CharSequence)string);
            this.reportError(status, string);
            return string;
        }
        if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_2) {
            final String string2 = this.getString(2131493246) + " (" + statusCode.getValue() + ")";
            this.displayUserAgentDialog(string2, null, false);
            this.reportError(status, string2);
            return string2;
        }
        if (statusCode == StatusCode.NETWORK_ERROR) {
            final String string3 = this.getString(2131493247) + " (" + statusCode.getValue() + ")";
            this.displayUserAgentDialog(string3, null, true);
            this.reportError(status, string3);
            return string3;
        }
        return super.handleUserAgentErrors(activity, status);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        Log.d("LoginActivity", this.getIntent());
        this.setContentView(2130903134);
        UserActionLogUtils.reportLoginActionEnded((Context)this, IClientLogging$CompletionReason.success, null);
        (this.emailView = (EditText)this.findViewById(2131165485)).requestFocus();
        (this.passwordView = (EditText)this.findViewById(2131165486)).setOnEditorActionListener((TextView$OnEditorActionListener)new LoginActivity$1(this));
        this.loginForm = this.findViewById(2131165484);
        this.loginButton = this.findViewById(2131165482);
        this.statusGroup = this.findViewById(2131165347);
        this.statusMessageView = (TextView)this.findViewById(2131165488);
        this.findViewById(2131165482).setOnClickListener((View$OnClickListener)new LoginActivity$2(this));
        this.findViewById(2131165483).setOnClickListener((View$OnClickListener)new LoginActivity$3(this));
    }
}
