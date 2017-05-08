// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.Bundle;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.app.FragmentTransaction;
import com.netflix.mediaclient.Log;
import android.app.FragmentManager;
import com.netflix.mediaclient.util.LoginUtils;
import com.netflix.mediaclient.android.app.Status;
import com.google.android.gms.auth.api.credentials.Credential;
import android.content.Intent;
import android.content.Context;
import android.app.Fragment;

public class LoginActivity extends AccountActivity implements LoginFragmentListener
{
    private static final String PREFERENCE_NON_MEMBER_PLAYBACK = "prefs_non_member_playback";
    public static final int RC_SAVE = 1;
    private static final String TAG = "LoginActivity";
    private EmailPasswordFragment mEmailPasswordFragment;
    private boolean mLaunchProfileGate;
    
    public static Intent createStartIntent(final Context context) {
        return createStartIntent(context, null, null);
    }
    
    public static Intent createStartIntent(final Context context, final Credential credential, final Status status) {
        final Intent intent = new Intent(context, (Class)LoginActivity.class);
        LoginUtils.addCredentialsToIntent(credential, status, intent);
        return intent;
    }
    
    private Fragment getActiveFragment() {
        return this.getActiveFragment(this.getFragmentManager());
    }
    
    private Fragment getActiveFragment(final FragmentManager fragmentManager) {
        final int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (Log.isLoggable()) {
            Log.i("LoginActivity", "getBackStackEntryCount " + backStackEntryCount);
        }
        if (backStackEntryCount == 0) {
            return null;
        }
        final String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        if (Log.isLoggable()) {
            Log.i("LoginActivity", "current fragment tag " + name);
        }
        return fragmentManager.findFragmentByTag(name);
    }
    
    private void showEmailPasswordFragment() {
        if (Log.isLoggable()) {
            Log.i("LoginActivity", "showEmailPasswordFragment");
        }
        final FragmentManager fragmentManager = this.getFragmentManager();
        final FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        this.mEmailPasswordFragment = EmailPasswordFragment.newInstance(this.getIntent().getExtras());
        beginTransaction.replace(2131689939, (Fragment)this.mEmailPasswordFragment, "EmailPasswordFragment").addToBackStack("EmailPasswordFragment");
        beginTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
        this.getActiveFragment(fragmentManager);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new LoginActivity$1(this);
    }
    
    @Override
    public void finish() {
        super.finish();
        PerformanceProfiler.getInstance().endSession(Sessions.LOG_IN, null);
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
        if (Log.isLoggable()) {
            Log.i("LoginActivity", "Account deactivated ...");
        }
    }
    
    @Override
    public void handleBackToRegularWorkflow() {
        if (this.mLaunchProfileGate) {
            if (Log.isLoggable()) {
                Log.d("LoginActivity", "New profile requested - starting profile selection activity...");
            }
            PreferenceUtils.putBooleanPref((Context)this, "prefs_non_member_playback", false);
            this.startActivity(ProfileSelectionActivity.createStartIntent((Context)this));
            AccountActivity.finishAllAccountActivities((Context)this);
            return;
        }
        if (Log.isLoggable()) {
            Log.d("LoginActivity", "Back to regular workflow for profile activated...");
        }
        this.finish();
    }
    
    @Override
    protected void handleProfileActivated() {
        if (LoginUtils.shouldUseAutoLogin((Context)this)) {
            if (Log.isLoggable()) {
                Log.d("LoginActivity", "SmartLogin save enabled, do save credentials for profile activated...");
            }
            this.mLaunchProfileGate = false;
            return;
        }
        if (Log.isLoggable()) {
            Log.d("LoginActivity", "SmartLogin save not enabled, regular workflow for profile activated...");
        }
        this.finish();
    }
    
    @Override
    protected void handleProfileReadyToSelect() {
        if (Log.isLoggable()) {
            Log.i("LoginActivity", "New profile requested - starting profile selection activity...");
        }
        if (LoginUtils.shouldUseAutoLogin((Context)this)) {
            if (Log.isLoggable()) {
                Log.d("LoginActivity", "SmartLogin save enabled, do save credentials...");
            }
            this.mLaunchProfileGate = true;
            return;
        }
        if (Log.isLoggable()) {
            Log.d("LoginActivity", "SmartLogin save not enabled, regular workflow...");
        }
        PreferenceUtils.putBooleanPref((Context)this, "prefs_non_member_playback", false);
        this.startActivity(ProfileSelectionActivity.createStartIntent((Context)this));
        AccountActivity.finishAllAccountActivities((Context)this);
    }
    
    @Override
    protected boolean hasUpAction() {
        return !SignupActivity.isSignupDisabledDevice();
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
            Log.e("LoginActivity", "onActivityResult: unknown request code" + n);
        }
        this.handleBackToRegularWorkflow();
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        AndroidUtils.setWindowSecureFlag(this);
        this.setContentView(2130903180);
        if (bundle != null) {
            this.mEmailPasswordFragment = (EmailPasswordFragment)this.getFragmentManager().findFragmentByTag("EmailPasswordFragment");
        }
        else {
            PerformanceProfiler.getInstance().startSession(Sessions.LOG_IN, null);
            this.showEmailPasswordFragment();
        }
        UserActionLogUtils.reportLoginActionEnded((Context)this, IClientLogging$CompletionReason.success, null);
    }
    
    @Override
    protected void onResume() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && serviceManager.isReady()) {
            serviceManager.setNonMemberPlayback(false);
        }
        super.onResume();
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }
    
    @Override
    public void performUpAction() {
        UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.upButton, this.getUiScreen(), this.getDataContext());
        this.startActivity(SignupActivity.createShowIntent((Context)this));
        this.finish();
    }
    
    @Override
    protected boolean showHelpInMenu() {
        return this.getServiceManager() != null && this.getServiceManager().getConfiguration() != null && this.getServiceManager().getConfiguration().showHelpForNonMemebers();
    }
}
