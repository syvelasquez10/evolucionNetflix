// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.app.ActionBar;
import android.view.View;
import com.netflix.mediaclient.android.fragment.LoadingView;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.protocol.nflx.NflxHandler;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class LaunchActivity extends NetflixActivity
{
    private static final String EXTRA_SOURCE = "extra_source";
    private static final boolean START_DETAILS_ACTIVITY_ON_LAUNCH = false;
    private static final String TAG = "LaunchActivity";
    private static final Video sampleVideo;
    private boolean isLoading;
    private long mSplashScreenStarted;
    private long mStarted;
    private final BroadcastReceiver nflxBroadcastReceiver;
    private final NflxHandler nflxHandler;
    
    static {
        sampleVideo = new Video() {
            @Override
            public String getBoxshotURL() {
                return null;
            }
            
            @Override
            public VideoType getErrorType() {
                return null;
            }
            
            @Override
            public String getId() {
                return "70178217";
            }
            
            @Override
            public String getTitle() {
                return null;
            }
            
            @Override
            public VideoType getType() {
                return VideoType.SHOW;
            }
        };
    }
    
    public LaunchActivity() {
        this.nflxHandler = new NflxHandler();
        this.isLoading = true;
        this.nflxBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (Log.isLoggable("LaunchActivity", 2)) {
                    Log.v("LaunchActivity", "Received intent " + intent);
                }
                final String action = intent.getAction();
                if ("com.netflix.mediaclient.intent.action.NFLX_HANDLER_RESULT".equals(action)) {
                    Log.d("LaunchActivity", "Delayed nflx action completed, finish activity");
                    LaunchActivity.this.finish();
                }
                else if (Log.isLoggable("LaunchActivity", 3)) {
                    Log.d("LaunchActivity", "We do not support action " + action);
                }
            }
        };
    }
    
    private NflxHandler.Response canHandleIntent() {
        try {
            return this.nflxHandler.handleNflxIntent(this, this.getServiceManager(), this.getIntent(), this.mStarted);
        }
        catch (Throwable t) {
            Log.e("LaunchActivity", "Failed to parse nflx url ", t);
            return NflxHandler.Response.NOT_HANDLING;
        }
    }
    
    public static Intent createStartIntent(final Activity activity, final String s) {
        return new Intent((Context)activity, (Class)LaunchActivity.class).putExtra("extra_source", s);
    }
    
    private void handleManagerReady(final ServiceManager serviceManager) {
        final boolean userLoggedIn = serviceManager.isUserLoggedIn();
        final boolean signUpEnabled = serviceManager.getSignUpParams().isSignUpEnabled();
        if (this.mSplashScreenStarted > 0L) {
            Log.d("LaunchActivity", "Splash screen was displayed, reporting");
            this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().uiViewChanged(DeviceUtils.isPortrait((Context)this), IClientLogging.ModalView.appLoading, this.mSplashScreenStarted);
        }
        if (!userLoggedIn) {
            if (signUpEnabled && !this.getNetflixApplication().hasSignedUpOnce()) {
                Log.d("LaunchActivity", "User has not signed up, redirect to Signup screen");
                this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.touchGesture, IClientLogging.ModalView.signupPrompt, this.mStarted);
                this.startNextActivity(SignupActivity.createStartIntent((Context)this, this.getIntent()));
            }
            else {
                Log.d("LaunchActivity", "User is NOT logged in, redirect to Login screen");
                this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.touchGesture, IClientLogging.ModalView.login, this.mStarted);
                this.startNextActivity(LoginActivity.createStartIntent((Context)this));
            }
            this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().startUiBrowseStartupSession(this.mStarted);
            this.finish();
            return;
        }
        Enum<NflxHandler.Response> canHandleIntent = null;
        if (serviceManager.getCurrentProfile() != null) {
            canHandleIntent = this.canHandleIntent();
        }
        if (canHandleIntent != null && canHandleIntent == NflxHandler.Response.HANDLING) {
            Log.d("LaunchActivity", "Handled by nflx workflow");
            this.finish();
            return;
        }
        if (canHandleIntent != null && canHandleIntent == NflxHandler.Response.HANDLING_WITH_DELAY) {
            Log.d("LaunchActivity", "Handled by nflx workflow with delay. Stay on splash screen...");
            return;
        }
        if (serviceManager.getCurrentProfile() == null) {
            this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.touchGesture, IClientLogging.ModalView.profilesGate, this.mStarted);
            this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().startUiBrowseStartupSession(this.mStarted);
            this.startNextActivity(ProfileSelectionActivity.createStartIntent(this));
            this.finish();
            return;
        }
        Log.d("LaunchActivity", String.format("Redirect to home with profile %s, %s", serviceManager.getCurrentProfile().getProfileName(), serviceManager.getCurrentProfile().getProfileId()));
        this.startNextActivity(HomeActivity.createStartIntent((Context)this));
        this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.touchGesture, IClientLogging.ModalView.homeScreen, this.mStarted);
        this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging().startUiBrowseStartupSession(this.mStarted);
        this.finish();
    }
    
    private void registerNflxReceiver() {
        Log.d("LaunchActivity", "Register receiver");
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.NFLX_HANDLER_RESULT");
        intentFilter.addCategory("LocalIntentNflxUi");
        intentFilter.setPriority(999);
        try {
            LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.nflxBroadcastReceiver, intentFilter);
        }
        catch (Throwable t) {
            Log.e("LaunchActivity", "Failed to register ", t);
        }
    }
    
    private void startNextActivity(final Intent intent) {
        this.startActivity(intent);
        this.overridePendingTransition(0, 0);
    }
    
    private void unregisterNflxReceiver() {
        Log.d("LaunchActivity", "Unregistering Nflx receiver");
        try {
            LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.nflxBroadcastReceiver);
        }
        catch (Throwable t) {
            Log.e("LaunchActivity", "Failed to unregister ", t);
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                LaunchActivity.this.isLoading = false;
                if (ServiceErrorsHandler.handleManagerResponse(LaunchActivity.this, n)) {
                    return;
                }
                LaunchActivity.this.handleManagerReady(serviceManager);
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
                LaunchActivity.this.isLoading = false;
                ServiceErrorsHandler.handleManagerResponse(LaunchActivity.this, n);
            }
        };
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return null;
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.mStarted = System.currentTimeMillis();
        super.onCreate(bundle);
        if (Log.isLoggable("LaunchActivity", 3)) {
            AndroidUtils.logIntent("LaunchActivity", this.getIntent());
            Log.d("LaunchActivity", "Time: " + System.nanoTime());
        }
        final ActionBar actionBar = this.getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        this.registerNflxReceiver();
        if (this.getNetflixApplication().isReady()) {
            Log.d("LaunchActivity", "Service is ready, just use simple progress bar...");
            this.setContentView((View)new LoadingView((Context)this));
            return;
        }
        Log.d("LaunchActivity", "Service is NOT ready, use splash screen...");
        this.mSplashScreenStarted = System.currentTimeMillis();
        if (DeviceUtils.isTabletByContext((Context)this)) {
            this.setContentView(2130903155);
            return;
        }
        this.setContentView(2130903154);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterNflxReceiver();
    }
    
    protected void onNewIntent(final Intent intent) {
        Log.d("LaunchActivity", "Received new intent:");
        AndroidUtils.logIntent("LaunchActivity", intent);
        super.onNewIntent(intent);
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
