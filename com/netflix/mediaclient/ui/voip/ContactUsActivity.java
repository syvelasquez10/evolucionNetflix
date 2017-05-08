// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.database.ContentObserver;
import android.provider.Settings$System;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.View$OnClickListener;
import android.support.design.widget.Snackbar;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.service.logging.apm.model.Orientation;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.log.CustomerServiceLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import com.netflix.mediaclient.util.PermissionUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$ReturnToDialScreenFrom;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import android.view.View;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import android.support.v4.app.ActivityCompat$OnRequestPermissionsResultCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class ContactUsActivity extends NetflixActivity implements ActivityCompat$OnRequestPermissionsResultCallback, IVoip$OutboundCallListener
{
    private static final String EXTRA_PARAM_AUTODIAL = "AUTODIAL";
    private static final String EXTRA_SHOULD_DIPLAY_VERIFICATION_DIALOG = "com.netflix.mediaclient.ui.voip.verification_dialog";
    private static String[] PERMISSIONS_AUDIO;
    private static final int REQUEST_AUDIO = 0;
    private static final String TAG = "VoipActivity";
    private ContactUsActivity$AudioObserver mAudioObserver;
    private boolean mAutoDial;
    private View mDialButton;
    private boolean mDialerOnTop;
    private DialerScreen mDialerScreen;
    private CustomerServiceLogging$EntryPoint mEntry;
    private ViewFlipper mFlipper;
    private CustomerServiceLogging$ReturnToDialScreenFrom mFrom;
    private LandingPageScreen mLandingPage;
    private ServiceManager mServiceManager;
    private IClientLogging$ModalView mSource;
    private boolean mVerificationDialogDisplayed;
    private IVoip mVoip;
    
    static {
        ContactUsActivity.PERMISSIONS_AUDIO = new String[] { "android.permission.RECORD_AUDIO", "android.permission.MODIFY_AUDIO_SETTINGS" };
    }
    
    private void checkForAutoDial(final Intent intent) {
        if (intent != null && intent.getBooleanExtra("AUTODIAL", false)) {
            Log.d("VoipActivity", "AutoDial requested");
            this.mAutoDial = true;
        }
        else {
            this.mAutoDial = false;
        }
        if (this.mAutoDial && this.mServiceManager != null) {
            Log.d("VoipActivity", "Start autodial, service manager exist");
            this.startDial();
        }
    }
    
    private void checkForLogData(final Intent intent) {
        if (intent != null) {
            Log.d("VoipActivity", intent);
            if (intent.getStringExtra("source") != null) {
                this.mSource = IClientLogging$ModalView.valueOf(intent.getStringExtra("source"));
                Log.d("VoipActivity", "Source found: " + this.mSource);
            }
            if (intent.getStringExtra("from") != null) {
                this.mFrom = CustomerServiceLogging$ReturnToDialScreenFrom.valueOf(intent.getStringExtra("from"));
                Log.d("VoipActivity", "From found: " + this.mFrom);
            }
            if (intent.getStringExtra("entry") != null) {
                this.mEntry = CustomerServiceLogging$EntryPoint.valueOf(intent.getStringExtra("entry"));
                Log.d("VoipActivity", "Entry point found: " + this.mEntry);
            }
        }
    }
    
    private void checkIntent(final Intent intent) {
        this.checkForLogData(intent);
        this.checkForAutoDial(intent);
    }
    
    private void clearWindowFlags() {
        this.getWindow().clearFlags(this.getFlags());
    }
    
    private CustomerServiceLogging$EntryPoint createEntryPoint() {
        if (this.mEntry != null) {
            Log.d("VoipActivity", "Entry field is known, use it");
            return this.mEntry;
        }
        Log.d("VoipActivity", "Return to help page from dial or from links");
        return null;
    }
    
    private CustomerServiceLogging$ReturnToDialScreenFrom createFrom() {
        if (this.mFrom != null) {
            Log.d("VoipActivity", "From field is known, use it");
            return this.mFrom;
        }
        Log.d("VoipActivity", "From field is not known, use entry point");
        if (this.mEntry != null) {
            if (CustomerServiceLogging$EntryPoint.login == this.mEntry) {
                Log.d("VoipActivity", "Use entry point login");
                return CustomerServiceLogging$ReturnToDialScreenFrom.login;
            }
            if (CustomerServiceLogging$EntryPoint.nonMemberLanding == this.mEntry) {
                Log.d("VoipActivity", "Use entry point nml");
                return CustomerServiceLogging$ReturnToDialScreenFrom.nml;
            }
        }
        Log.d("VoipActivity", "Entry point is not know, return null");
        return null;
    }
    
    public static Intent createStartIntent(final Context context) {
        return new Intent(context, (Class)ContactUsActivity.class);
    }
    
    public static Intent createStartIntentWithAutoDial(final Context context) {
        final Intent intent = new Intent(context, (Class)ContactUsActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("AUTODIAL", true);
        return intent;
    }
    
    private void displayConfirmationDialog() {
        this.mVerificationDialogDisplayed = true;
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$TwoButtonAlertDialogDescriptor(null, this.getString(2131231027), this.getString(2131231026), new ContactUsActivity$2(this), this.getString(2131231025), new ContactUsActivity$3(this)), null));
    }
    
    private void doStartDial() {
        if (PermissionUtils.shouldRequestPermissions((Context)this, ContactUsActivity.PERMISSIONS_AUDIO)) {
            Log.d("VoipActivity", "Record audio permission are not granted. Requested them.");
            this.requestAudioPermissions();
            return;
        }
        Log.d("VoipActivity", "Record audio permission has already been granted. Start dialing.");
        Log.d("VoipActivity", "startDial:: To dialer");
        this.setWindowFlags();
        this.mFlipper.showNext();
        this.mDialerOnTop = true;
        CustomerServiceLogUtils.reportHelpRequestSessionEnded((Context)this, CustomerServiceLogging$Action.dial, null, IClientLogging$CompletionReason.success, null);
        if (this.getVoip().isCallInProgress()) {
            Log.e("VoipActivity", "Call is already in progress, what to start?");
            return;
        }
        Log.d("VoipActivity", "startDial:: Start call");
        try {
            this.mDialerScreen.startCall();
        }
        catch (Exception ex) {
            Log.e("VoipActivity", "Failed to dial", ex);
            this.callFailed(null, null, -1);
        }
    }
    
    private int getFlags() {
        return 4718592;
    }
    
    private void goToLandingPage() {
        this.clearWindowFlags();
        if (!this.isTablet()) {
            Log.d("VoipActivity", "Phone, release lock on portrait for dial screen");
            this.setRequestedOrientation(4);
        }
        this.mFlipper.showPrevious();
        this.mDialerOnTop = false;
    }
    
    private void init(final ServiceManager mServiceManager, final Status status) {
        this.mServiceManager = mServiceManager;
        this.mVoip = this.mServiceManager.getVoip();
        this.initUI(status.isError());
        if (this.mVoip != null) {
            this.mVoip.addOutboundCallListener(this);
        }
        this.reportEvent();
        if (this.mVerificationDialogDisplayed) {
            Log.d("VoipActivity", "Verification dialog was previosly displayed, show it again");
            this.displayConfirmationDialog();
        }
    }
    
    private void initUI(final boolean b) {
        this.setContentView(2130903081);
        this.getSupportActionBar().hide();
        this.mFlipper = (ViewFlipper)this.findViewById(2131689692);
        this.mLandingPage = new LandingPageScreen(this);
        this.mDialerScreen = new DialerScreen(this);
        this.mDialButton = this.findViewById(2131689740);
        if (b || this.mVoip.isEnabled()) {
            if (Log.isLoggable()) {
                Log.d("VoipActivity", "VOIP is enabled, show dial button on landing page! This is force enabled: " + b);
            }
            this.mDialButton.setVisibility(0);
        }
        else {
            Log.d("VoipActivity", "VOIP is disabled, do not show dial button on landing page!");
            this.mDialButton.setVisibility(8);
        }
        this.mLandingPage.update();
        this.mDialerScreen.update(this.mServiceManager.getVoip().isConnected());
        this.mDialerScreen.initUi();
        if (this.mVoip.isCallInProgress()) {
            Log.d("VoipActivity", "Call is in progress, move to dialer");
            this.setWindowFlags();
            this.mFlipper.showNext();
            this.mDialerOnTop = true;
            return;
        }
        Log.d("VoipActivity", "Call is not in progress, leave on landing page");
    }
    
    private void reportEvent() {
        Log.d("VoipActivity", "Back to ContactUsActivity");
        if (this.mDialerOnTop) {
            Log.d("VoipActivity", "Dialer visible, report back to ");
            Orientation orientation;
            if (DeviceUtils.isPortrait((Context)this)) {
                orientation = Orientation.portrait;
            }
            else {
                orientation = Orientation.landscape;
            }
            CustomerServiceLogUtils.reportBackToDialScreen((Context)this, this.mSource, orientation, this.createFrom());
            return;
        }
        Log.d("VoipActivity", "Help section visible, report new help request session started");
        CustomerServiceLogUtils.reportHelpRequestSessionStarted((Context)this, this.createEntryPoint());
    }
    
    private void requestAudioPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECORD_AUDIO")) {
            Log.i("VoipActivity", "Displaying audio permission rationale to provide additional context.");
            Snackbar.make(this.mLandingPage.getFab(), 2131230908, -2).setAction(2131231168, (View$OnClickListener)new ContactUsActivity$4(this)).show();
            return;
        }
        ActivityCompat.requestPermissions(this, ContactUsActivity.PERMISSIONS_AUDIO, 0);
    }
    
    private void setWindowFlags() {
        this.getWindow().addFlags(this.getFlags());
    }
    
    private boolean shouldDisplayCallConfirmationDialog() {
        return true;
    }
    
    private void verifyBeforeDial() {
        if (this.isFinishing()) {
            return;
        }
        if (!this.isTablet()) {
            Log.d("VoipActivity", "Phone, force portrait for dial screen");
            this.setRequestedOrientation(7);
        }
        if (!this.mDialerOnTop) {
            this.doStartDial();
            return;
        }
        Log.d("VoipActivity", "startDial:: Already in dialer");
    }
    
    @Override
    public void callConnected(final IVoip$Call voip$Call) {
        if (this.isFinishing()) {
            return;
        }
        this.mDialerScreen.callConnected();
    }
    
    @Override
    public void callDisconnected(final IVoip$Call voip$Call) {
        if (this.isFinishing()) {
            return;
        }
        if (this.mDialerOnTop) {
            Log.d("VoipActivity", "callDisconnected:: Back to landing page contact us");
            this.goToLandingPage();
        }
        else {
            Log.d("VoipActivity", "callDisconnected:: Already back to landing page contact us");
        }
        this.mDialerScreen.stopTimer();
    }
    
    @Override
    public void callEnded(final IVoip$Call voip$Call) {
        if (this.isFinishing()) {
            return;
        }
        if (this.mDialerOnTop) {
            Log.d("VoipActivity", "callEnded:: Back to landing page contact us");
            this.goToLandingPage();
            return;
        }
        Log.d("VoipActivity", "callEnded:: Already back to landing page contact us");
    }
    
    @Override
    public void callFailed(final IVoip$Call voip$Call, final String s, final int n) {
        if (this.isFinishing()) {
            return;
        }
        if (this.mDialerOnTop) {
            Log.d("VoipActivity", "callFailed:: Back to landing page contact us");
            this.goToLandingPage();
        }
        else {
            Log.d("VoipActivity", "callFailed:: Already back to landing page contact us");
        }
        this.mDialerScreen.stopTimer();
    }
    
    @Override
    public void callRinging(final IVoip$Call voip$Call) {
        if (this.isFinishing()) {
            return;
        }
        this.mDialerScreen.callRinging();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ContactUsActivity$1(this);
    }
    
    @Override
    public void engineStatusChanged(final boolean enabled) {
        if (this.isFinishing()) {
            return;
        }
        this.mDialButton.setEnabled(enabled);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.contactUs;
    }
    
    IVoip getVoip() {
        return this.mVoip;
    }
    
    @Override
    protected boolean hasUpAction() {
        return true;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public void networkFailed(final IVoip$Call voip$Call) {
        if (this.isFinishing()) {
            return;
        }
        if (this.mDialerOnTop) {
            Log.d("VoipActivity", "networkFailed:: Back to landing page contact us");
            this.goToLandingPage();
        }
        else {
            Log.d("VoipActivity", "networkFailed:: Already back to landing page contact us");
        }
        this.mDialerScreen.stopTimer();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        Log.d("VoipActivity", "onCreate");
        this.checkIntent(this.getIntent());
        this.mAudioObserver = new ContactUsActivity$AudioObserver(this, (Context)this);
        this.setVolumeControlStream(0);
        this.getApplicationContext().getContentResolver().registerContentObserver(Settings$System.CONTENT_URI, true, (ContentObserver)this.mAudioObserver);
        if (bundle != null) {
            this.mVerificationDialogDisplayed = bundle.getBoolean("com.netflix.mediaclient.ui.voip.verification_dialog");
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mVoip != null) {
            this.mVoip.removeOutboundCallListener(this);
        }
        this.getApplicationContext().getContentResolver().unregisterContentObserver((ContentObserver)this.mAudioObserver);
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.checkIntent(this.getIntent());
        if (this.mServiceManager != null) {
            this.reportEvent();
        }
    }
    
    @Override
    public void onRequestPermissionsResult(int i, final String[] array, final int[] array2) {
        final int n = 0;
        if (i != 0) {
            super.onRequestPermissionsResult(i, array, array2);
            return;
        }
        Log.d("VoipActivity", "Received response for Audio permission request.");
        int length;
        for (length = array.length, i = 0; i < length; ++i) {
            Log.d("VoipActivity", array[i]);
        }
        int length2;
        for (length2 = array2.length, i = n; i < length2; ++i) {
            Log.d("VoipActivity", "" + array2[i]);
        }
        if (PermissionUtils.verifyPermissions(array2, 2)) {
            Log.d("VoipActivity", "Audio permission has now been granted. Go to dialer...");
            this.doStartDial();
            return;
        }
        Log.i("VoipActivity", "Audio permission was NOT granted.");
        Snackbar.make(this.mLandingPage.getFab(), 2131230907, -1).show();
    }
    
    @Override
    protected void onResume() {
        synchronized (this) {
            super.onResume();
            if (this.getServiceManager() != null && this.mVerificationDialogDisplayed) {
                this.displayConfirmationDialog();
            }
        }
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d("VoipActivity", "Saving dialog state...");
        bundle.putBoolean("com.netflix.mediaclient.ui.voip.verification_dialog", this.mVerificationDialogDisplayed);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        if (this.mServiceManager != null) {
            this.reportEvent();
        }
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        this.mEntry = null;
        this.mSource = null;
        CustomerServiceLogging$Action customerServiceLogging$Action;
        if (this.isFinishing()) {
            customerServiceLogging$Action = CustomerServiceLogging$Action.back;
        }
        else {
            customerServiceLogging$Action = CustomerServiceLogging$Action.home;
        }
        if (this.mDialerOnTop) {
            CustomerServiceLogUtils.reportExitFromDialScreen((Context)this, customerServiceLogging$Action);
            return;
        }
        CustomerServiceLogUtils.reportHelpRequestSessionEnded((Context)this, customerServiceLogging$Action, null, IClientLogging$CompletionReason.canceled, null);
    }
    
    public void performAction(final View view) {
        if (this.mLandingPage.performAction(view)) {
            Log.d("VoipActivity", "Handled by landing page");
            return;
        }
        if (this.mDialerScreen.performAction(view)) {
            Log.d("VoipActivity", "Handled by dialer page");
            return;
        }
        Log.w("VoipActivity", "Handled by nobody!");
    }
    
    @Override
    public void performUpAction() {
        try {
            if (this.mDialerOnTop) {
                CustomerServiceLogUtils.reportExitFromDialScreen((Context)this, CustomerServiceLogging$Action.up);
            }
            else {
                CustomerServiceLogUtils.reportHelpRequestSessionEnded((Context)this, CustomerServiceLogging$Action.up, null, IClientLogging$CompletionReason.canceled, null);
            }
            super.performUpAction();
        }
        catch (Throwable t) {
            Log.e("VoipActivity", "Running app in broken state, go for relaunch...");
            this.finish();
        }
    }
    
    @Override
    protected boolean shouldFinishOnManagerError() {
        return false;
    }
    
    @Override
    protected boolean shouldStartLaunchActivityIfVisibleOnManagerUnavailable() {
        return false;
    }
    
    public boolean showMdxInMenu() {
        return false;
    }
    
    @Override
    public boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    public boolean showSignOutInMenu() {
        return false;
    }
    
    void startDial() {
        if (this.shouldDisplayCallConfirmationDialog()) {
            Log.d("VoipActivity", "User is in test cell to display confirmation dialog");
            this.displayConfirmationDialog();
            return;
        }
        Log.d("VoipActivity", "Start call");
        this.verifyBeforeDial();
    }
}
