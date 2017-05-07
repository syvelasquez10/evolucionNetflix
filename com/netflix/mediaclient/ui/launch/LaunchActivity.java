// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.launch;

import android.support.v7.app.ActionBar;
import android.view.View;
import com.netflix.mediaclient.android.fragment.LoadingView;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.IntentUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.ProgressBar;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.ImageView;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.protocol.nflx.NflxHandlerFactory;
import com.netflix.mediaclient.protocol.netflixcom.NetflixComHandlerFactory;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class LaunchActivity extends NetflixActivity
{
    private static final boolean HANG_ON_LOADING_SCREEN = false;
    private static final int PROFILE_GATE_SHOULD_BE_SHOWN_TIMES = 2;
    private static final boolean START_DETAILS_ACTIVITY_ON_LAUNCH = false;
    private static final String TAG = "LaunchActivity";
    private static final Video sampleVideo;
    private boolean isLoading;
    private long mSplashScreenStarted;
    private long mStarted;
    private final BroadcastReceiver nflxBroadcastReceiver;
    
    static {
        sampleVideo = new LaunchActivity$1();
    }
    
    public LaunchActivity() {
        this.isLoading = true;
        this.nflxBroadcastReceiver = new LaunchActivity$4(this);
    }
    
    private NflxHandler$Response canHandleIntent() {
        final Intent intent = this.getIntent();
        if (NetflixComHandlerFactory.canHandle(intent)) {
            return NetflixComHandlerFactory.handle(this, intent.getData());
        }
        try {
            final NflxHandler$Response handle = NflxHandlerFactory.getHandlerForIntent(this, intent, this.mStarted).handle();
            NflxHandlerFactory.endCommandSessions((Context)this, intent);
            return handle;
        }
        catch (Throwable t) {
            Log.e("LaunchActivity", "Failed to parse nflx url ", t);
            return NflxHandler$Response.NOT_HANDLING;
        }
    }
    
    private void createContentView() {
        this.setContentView(2130903202);
        final ImageView imageView = (ImageView)this.findViewById(2131165693);
        int imageResource;
        if (DeviceUtils.isTabletByContext((Context)this)) {
            imageResource = 2130837891;
        }
        else {
            imageResource = 2130837890;
        }
        imageView.setImageResource(imageResource);
        if (DeviceUtils.getScreenResolutionDpi((Context)this) >= 320 && DeviceUtils.getScreenSizeCategory((Context)this) == 4) {
            this.manipulateSplashBackground();
        }
    }
    
    private void handleManagerReady(final ServiceManager serviceManager) {
        if (this.isFinishing()) {
            return;
        }
        final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging = this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging();
        final boolean userLoggedIn = serviceManager.isUserLoggedIn();
        final boolean signUpEnabled = serviceManager.getSignUpParams().isSignUpEnabled();
        if (this.mSplashScreenStarted > 0L) {
            Log.d("LaunchActivity", "Splash screen was displayed, reporting");
            applicationPerformanceMetricsLogging.uiViewChanged(DeviceUtils.isPortrait((Context)this), IClientLogging$ModalView.appLoading, this.mSplashScreenStarted);
        }
        final Display display = ConsolidatedLoggingUtils.getDisplay((Context)this);
        if (!userLoggedIn) {
            if (signUpEnabled && !this.getNetflixApplication().hasSignedUpOnce()) {
                Log.d("LaunchActivity", "User has not signed up, redirect to Signup screen");
                if (this.shouldCreateUiSessions()) {
                    applicationPerformanceMetricsLogging.startUiStartupSession(ApplicationPerformanceMetricsLogging$UiStartupTrigger.touchGesture, IClientLogging$ModalView.signupPrompt, this.mStarted, display);
                }
                this.startNextActivity(SignupActivity.createStartIntent((Context)this, this.getIntent()));
            }
            else {
                Log.d("LaunchActivity", "User is NOT logged in, redirect to Login screen");
                if (this.shouldCreateUiSessions()) {
                    applicationPerformanceMetricsLogging.startUiStartupSession(ApplicationPerformanceMetricsLogging$UiStartupTrigger.touchGesture, IClientLogging$ModalView.login, this.mStarted, display);
                }
                this.startNextActivity(LoginActivity.createStartIntent((Context)this));
            }
            if (this.shouldCreateUiSessions()) {
                applicationPerformanceMetricsLogging.startUiBrowseStartupSession(this.mStarted);
            }
            this.finish();
            return;
        }
        NflxHandler$Response canHandleIntent = null;
        if (serviceManager.getCurrentProfile() != null) {
            canHandleIntent = this.canHandleIntent();
        }
        if (canHandleIntent != null && canHandleIntent == NflxHandler$Response.HANDLING) {
            Log.d("LaunchActivity", "Handled by nflx workflow");
            this.finish();
            return;
        }
        if (canHandleIntent != null && canHandleIntent == NflxHandler$Response.HANDLING_WITH_DELAY) {
            Log.d("LaunchActivity", "Handled by nflx workflow with delay. Stay on splash screen...");
            return;
        }
        if (serviceManager.getCurrentProfile() == null || this.shouldProfileGateBeShown(serviceManager)) {
            if (this.shouldCreateUiSessions()) {
                applicationPerformanceMetricsLogging.startUiStartupSession(ApplicationPerformanceMetricsLogging$UiStartupTrigger.touchGesture, IClientLogging$ModalView.profilesGate, this.mStarted, display);
                applicationPerformanceMetricsLogging.startUiBrowseStartupSession(this.mStarted);
            }
            this.startNextActivity(ProfileSelectionActivity.createStartIntentForAppRestart((Context)this));
            this.finish();
            return;
        }
        Log.d("LaunchActivity", String.format("Redirect to home with profile %s, %s", serviceManager.getCurrentProfile().getProfileName(), serviceManager.getCurrentProfile().getProfileGuid()));
        this.startNextActivity(HomeActivity.createStartIntent(this));
        if (this.shouldCreateUiSessions()) {
            applicationPerformanceMetricsLogging.startUiStartupSession(ApplicationPerformanceMetricsLogging$UiStartupTrigger.touchGesture, IClientLogging$ModalView.homeScreen, this.mStarted, display);
            applicationPerformanceMetricsLogging.startUiBrowseStartupSession(this.mStarted);
        }
        this.finish();
    }
    
    private void manipulateSplashBackground() {
        final ImageView imageView = (ImageView)this.findViewById(2131165693);
        imageView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new LaunchActivity$2(this, imageView, (ImageView)this.findViewById(2131165408), (ProgressBar)this.findViewById(2131165409)));
    }
    
    private void registerNflxReceiver() {
        Log.d("LaunchActivity", "Register receiver");
        IntentUtils.registerSafelyLocalBroadcastReceiver((Context)this, this.nflxBroadcastReceiver, "LocalIntentNflxUi", "com.netflix.mediaclient.intent.action.HANDLER_RESULT");
    }
    
    private boolean shouldProfileGateBeShown(final ServiceManager serviceManager) {
        boolean b = false;
        boolean b2 = false;
        if (serviceManager == null) {
            Log.e("LaunchActivity", "shouldProfileGateBeShown was called with null manager");
        }
        else {
            int n;
            if (serviceManager.getAllProfiles().size() == 1 && !(this instanceof RelaunchActivity)) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                final int intPref = PreferenceUtils.getIntPref((Context)this, "user_saw_profile_gate", 0);
                if (intPref < 2) {
                    b = true;
                }
                b2 = b;
                if (b) {
                    PreferenceUtils.putIntPref((Context)this, "user_saw_profile_gate", intPref + 1);
                    return b;
                }
            }
        }
        return b2;
    }
    
    private void startNextActivity(final Intent intent) {
        this.startActivity(intent);
        this.overridePendingTransition(0, 0);
    }
    
    private void unregisterNflxReceiver() {
        Log.d("LaunchActivity", "Unregistering Nflx receiver");
        IntentUtils.unregisterSafelyLocalBroadcastReceiver((Context)this, this.nflxBroadcastReceiver);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new LaunchActivity$3(this);
    }
    
    @Override
    public void finish() {
        super.finish();
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.appLoading;
    }
    
    @Override
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.mStarted = System.currentTimeMillis();
        super.onCreate(bundle);
        if (Log.isLoggable()) {
            Log.d("LaunchActivity", this.getIntent());
            Log.d("LaunchActivity", "Time: " + System.nanoTime());
        }
        this.registerNflxReceiver();
        if (this.getNetflixApplication().isReady()) {
            Log.d("LaunchActivity", "Service is ready, just use loading view...");
            this.setContentView((View)new LoadingView((Context)this));
            return;
        }
        Log.d("LaunchActivity", "Service is NOT ready, use splash screen...");
        this.mSplashScreenStarted = System.currentTimeMillis();
        this.createContentView();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterNflxReceiver();
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        Log.d("LaunchActivity", "Received new intent:", intent);
        super.onNewIntent(intent);
    }
    
    @Override
    protected void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }
    
    protected boolean shouldCreateUiSessions() {
        return true;
    }
    
    @Override
    protected boolean shouldFinishOnManagerError() {
        return false;
    }
    
    @Override
    protected boolean showMdxInMenu() {
        return false;
    }
}
