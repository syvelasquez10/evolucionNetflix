// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.view.View;
import android.database.ContentObserver;
import android.provider.Settings$System;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.View$OnClickListener;
import android.support.design.widget.Snackbar;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.util.PermissionUtils;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ViewFlipper;
import android.support.design.widget.FloatingActionButton;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import android.support.v4.app.ActivityCompat$OnRequestPermissionsResultCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class ContactUsActivity extends NetflixActivity implements ActivityCompat$OnRequestPermissionsResultCallback, IVoip$OutboundCallListener
{
    private static final String EXTRA_PARAM_AUTODIAL = "AUTODIAL";
    private static String[] PERMISSIONS_AUDIO;
    private static final int REQUEST_AUDIO = 0;
    private static final String TAG = "VoipActivity";
    private ContactUsActivity$AudioObserver mAudioObserver;
    private boolean mAutoDial;
    private FloatingActionButton mDialButton;
    private boolean mDialerOnTop;
    private DialerScreen mDialerScreen;
    private ViewFlipper mFlipper;
    private LandingPageScreen mLandingPage;
    private ServiceManager mServiceManager;
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
    
    public static Intent createStartIntent(final Context context) {
        return new Intent(context, (Class)ContactUsActivity.class);
    }
    
    public static Intent createStartIntentWithAutoDial(final Context context) {
        final Intent intent = new Intent(context, (Class)ContactUsActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("AUTODIAL", true);
        return intent;
    }
    
    private void doStartDial() {
        if (PermissionUtils.shouldRequestPermissions((Context)this, ContactUsActivity.PERMISSIONS_AUDIO)) {
            Log.d("VoipActivity", "Record audio permission are not granted. Requested them.");
            this.requestAudioPermissions();
            return;
        }
        Log.d("VoipActivity", "Record audio permission has already been granted. Start dialing.");
        Log.d("VoipActivity", "startDial:: To dialer");
        this.mDialerOnTop = true;
        this.mFlipper.showNext();
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
    
    private void initUI() {
        this.setContentView(2130903087);
        this.getSupportActionBar().hide();
        this.mFlipper = (ViewFlipper)this.findViewById(2131624114);
        this.mLandingPage = new LandingPageScreen(this);
        this.mDialerScreen = new DialerScreen(this);
        this.mDialButton = (FloatingActionButton)this.findViewById(2131624162);
        if (this.mVoip.isEnabled()) {
            Log.d("VoipActivity", "VOIP is enabled, show dial button on landing page!");
            this.mDialButton.setVisibility(0);
        }
        else {
            Log.d("VoipActivity", "VOIP is disabled, do not show dial button on landing page!");
            this.mDialButton.setVisibility(8);
        }
        this.mLandingPage.update();
        this.mDialerScreen.update(this.mServiceManager.getVoip().isConnected());
        if (this.mVoip.isCallInProgress()) {
            Log.d("VoipActivity", "Call is in progress, move to dialer");
            this.mFlipper.showNext();
            this.mDialerOnTop = true;
            return;
        }
        Log.d("VoipActivity", "Call is not in progress, leave on landing page");
    }
    
    private void requestAudioPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECORD_AUDIO")) {
            Log.i("VoipActivity", "Displaying audio permission rationale to provide additional context.");
            Snackbar.make(this.mLandingPage.getFab(), 2131165674, -2).setAction(2131165485, (View$OnClickListener)new ContactUsActivity$2(this)).show();
            return;
        }
        ActivityCompat.requestPermissions(this, ContactUsActivity.PERMISSIONS_AUDIO, 0);
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
            this.mDialerOnTop = false;
            this.mFlipper.showPrevious();
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
            this.mFlipper.showPrevious();
            this.mDialerOnTop = false;
        }
        else {
            Log.d("VoipActivity", "callEnded:: Already back to landing page contact us");
        }
        this.mDialerScreen.stopTimer();
    }
    
    @Override
    public void callFailed(final IVoip$Call voip$Call, final String s, final int n) {
        if (this.isFinishing()) {
            return;
        }
        if (this.mDialerOnTop) {
            Log.d("VoipActivity", "callFailed:: Back to landing page contact us");
            this.mDialerOnTop = false;
            this.mFlipper.showPrevious();
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
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.checkForAutoDial(this.getIntent());
        this.mAudioObserver = new ContactUsActivity$AudioObserver(this, (Context)this);
        this.setVolumeControlStream(0);
        this.getApplicationContext().getContentResolver().registerContentObserver(Settings$System.CONTENT_URI, true, (ContentObserver)this.mAudioObserver);
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
        this.checkForAutoDial(intent);
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
        Snackbar.make(this.mLandingPage.getFab(), 2131165673, -1).show();
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
        if (this.isFinishing()) {
            return;
        }
        if (!this.mDialerOnTop) {
            this.doStartDial();
            return;
        }
        Log.d("VoipActivity", "startDial:: Already in dialer");
    }
}
