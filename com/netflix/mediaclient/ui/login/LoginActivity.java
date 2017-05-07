// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.net.Uri;
import android.view.View$OnClickListener;
import android.view.KeyEvent;
import android.widget.TextView$OnEditorActionListener;
import android.os.Bundle;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
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
        this.loginQueryCallback = new SimpleManagerCallback() {
            @Override
            public void onLoginComplete(final Status status) {
                LoginActivity.this.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        LoginActivity.this.handleLoginComplete(status);
                    }
                });
            }
        };
    }
    
    private void attemptLogin() {
        this.emailView.setError((CharSequence)null);
        this.passwordView.setError((CharSequence)null);
        final String string = this.emailView.getText().toString();
        final String string2 = this.passwordView.getText().toString();
        boolean b = false;
        Object o = null;
        if (this.passwordIsInvalid(string2)) {
            final String string3 = this.getString(2131493218);
            this.reportCancel(string3);
            this.passwordView.setError((CharSequence)string3);
            o = this.passwordView;
            b = true;
        }
        if (this.emailIsInvalid(string)) {
            final String string4 = this.getString(2131493217);
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
        this.statusMessageView.setText(2131493216);
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
            this.showDebugToast(2131493223);
            return;
        }
        this.handleUserAgentErrors(this, status);
        this.showProgress(false);
    }
    
    private void noConnectivityWarning() {
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                LoginActivity.this.displayDialog(AlertDialogFactory.createDialog((Context)LoginActivity.this, LoginActivity.this.handler, new AlertDialogFactory.AlertDialogDescriptor(null, LoginActivity.this.getString(2131493276), LoginActivity.this.getString(17039370), null)));
            }
        });
    }
    
    private boolean passwordIsInvalid(final String s) {
        return StringUtils.isEmpty(s) || s.length() < 4;
    }
    
    private void reportCancel(final String s) {
        UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging.CompletionReason.canceled, new UIError(RootCause.clientFailure, ActionOnUIError.displayedError, s, null));
        UserActionLogUtils.reportNavigationActionStarted((Context)this, null, this.getUiScreen());
    }
    
    private void reportError(final Status status, final String s) {
        UserActionLogUtils.reportNavigationActionEnded((Context)this, this.getUiScreen(), IClientLogging.CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, s, ActionOnUIError.displayedError));
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
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.login;
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
        String error;
        if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_4 || statusCode == StatusCode.NRD_LOGIN_ACTIONID_8) {
            error = this.getString(2131493219);
            this.passwordView.setError((CharSequence)error);
            this.reportError(status, error);
        }
        else if (statusCode == StatusCode.NRD_LOGIN_ACTIONID_2) {
            error = this.getString(2131493278) + " (" + statusCode.getValue() + ")";
            this.displayUserAgentDialog(error, null, false);
            this.reportError(status, error);
        }
        else {
            if (statusCode != StatusCode.NETWORK_ERROR) {
                return super.handleUserAgentErrors(activity, status);
            }
            error = this.getString(2131493280) + " (" + statusCode.getValue() + ")";
            this.displayUserAgentDialog(error, null, true);
            this.reportError(status, error);
        }
        return error;
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        Log.d("LoginActivity", this.getIntent());
        this.setContentView(2130903120);
        UserActionLogUtils.reportLoginActionEnded((Context)this, IClientLogging.CompletionReason.success, null);
        (this.emailView = (EditText)this.findViewById(2131165451)).requestFocus();
        (this.passwordView = (EditText)this.findViewById(2131165452)).setOnEditorActionListener((TextView$OnEditorActionListener)new TextView$OnEditorActionListener() {
            private boolean isLoginId(final int n) {
                return n == 2131165453 || n == 0 || n == 6;
            }
            
            public boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
                if (Log.isLoggable("LoginActivity", 2)) {
                    Log.v("LoginActivity", "Editor action: " + n + ", keyevent: " + keyEvent);
                }
                if (this.isLoginId(n)) {
                    LoginActivity.this.attemptLogin();
                    return true;
                }
                return false;
            }
        });
        this.loginForm = this.findViewById(2131165450);
        this.loginButton = this.findViewById(2131165448);
        this.statusGroup = this.findViewById(2131165340);
        this.statusMessageView = (TextView)this.findViewById(2131165454);
        this.findViewById(2131165448).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                LoginActivity.this.attemptLogin();
            }
        });
        this.findViewById(2131165449).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final Intent setData = new Intent("android.intent.action.VIEW").setData(Uri.parse("https://signup.netflix.com/loginhelp"));
                if (setData.resolveActivity(LoginActivity.this.getPackageManager()) != null) {
                    LoginActivity.this.startActivityForResult(setData, 0);
                    return;
                }
                LoginActivity.this.displayUserAgentDialog(LoginActivity.this.getString(2131493363, new Object[] { "https://signup.netflix.com/loginhelp" }), null, false);
            }
        });
    }
}
