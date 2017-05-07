// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.app.ActionBar;
import com.netflix.mediaclient.android.fragment.LoadingView;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ImageView$ScaleType;
import android.widget.RelativeLayout$LayoutParams;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.ProgressBar;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.ImageView;
import com.netflix.mediaclient.protocol.nflx.NflxHandlerFactory;
import com.netflix.mediaclient.protocol.nflx.NflxHandler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class LaunchActivity extends NetflixActivity
{
    private static final boolean HANG_ON_LOADING_SCREEN = false;
    private static final boolean START_DETAILS_ACTIVITY_ON_LAUNCH = false;
    private static final String TAG = "LaunchActivity";
    private static final Video sampleVideo;
    private boolean isLoading;
    private long mSplashScreenStarted;
    private long mStarted;
    private final BroadcastReceiver nflxBroadcastReceiver;
    
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
            public String getHorzDispUrl() {
                return null;
            }
            
            @Override
            public String getId() {
                return "70140457";
            }
            
            @Override
            public String getSquareUrl() {
                return null;
            }
            
            @Override
            public String getTitle() {
                return "Dummy Title";
            }
            
            @Override
            public String getTvCardUrl() {
                return null;
            }
            
            @Override
            public VideoType getType() {
                return VideoType.SHOW;
            }
        };
    }
    
    public LaunchActivity() {
        this.isLoading = true;
        this.nflxBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (Log.isLoggable("LaunchActivity", 2)) {
                    Log.v("LaunchActivity", "Received intent " + intent);
                }
                final String action = intent.getAction();
                if ("com.netflix.mediaclient.intent.action.HANDLER_RESULT".equals(action)) {
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
            return NflxHandlerFactory.getHandlerForIntent(this, this.getIntent(), this.mStarted).handle();
        }
        catch (Throwable t) {
            Log.e("LaunchActivity", "Failed to parse nflx url ", t);
            return NflxHandler.Response.NOT_HANDLING;
        }
    }
    
    private void createContentView() {
        this.setContentView(2130903182);
        final ImageView imageView = (ImageView)this.findViewById(2131165635);
        int imageResource;
        if (DeviceUtils.isTabletByContext((Context)this)) {
            imageResource = 2130837891;
        }
        else {
            imageResource = 2130837890;
        }
        imageView.setImageResource(imageResource);
        if (DeviceUtils.getScreenResolutionDpi(this) >= 320 && DeviceUtils.getScreenSizeCategory((Context)this) == 4) {
            this.manipulateSplashBackground();
        }
    }
    
    private void handleManagerReady(final ServiceManager serviceManager) {
        final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging = this.getServiceManager().getClientLogging().getApplicationPerformanceMetricsLogging();
        final boolean userLoggedIn = serviceManager.isUserLoggedIn();
        final boolean signUpEnabled = serviceManager.getSignUpParams().isSignUpEnabled();
        if (this.mSplashScreenStarted > 0L) {
            Log.d("LaunchActivity", "Splash screen was displayed, reporting");
            applicationPerformanceMetricsLogging.uiViewChanged(DeviceUtils.isPortrait((Context)this), IClientLogging.ModalView.appLoading, this.mSplashScreenStarted);
        }
        final Display display = LogUtils.getDisplay((Context)this);
        if (!userLoggedIn) {
            if (signUpEnabled && !this.getNetflixApplication().hasSignedUpOnce()) {
                Log.d("LaunchActivity", "User has not signed up, redirect to Signup screen");
                if (this.shouldCreateUiSessions()) {
                    applicationPerformanceMetricsLogging.startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.touchGesture, IClientLogging.ModalView.signupPrompt, this.mStarted, display);
                }
                this.startNextActivity(SignupActivity.createStartIntent((Context)this, this.getIntent()));
            }
            else {
                Log.d("LaunchActivity", "User is NOT logged in, redirect to Login screen");
                if (this.shouldCreateUiSessions()) {
                    applicationPerformanceMetricsLogging.startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.touchGesture, IClientLogging.ModalView.login, this.mStarted, display);
                }
                this.startNextActivity(LoginActivity.createStartIntent((Context)this));
            }
            if (this.shouldCreateUiSessions()) {
                applicationPerformanceMetricsLogging.startUiBrowseStartupSession(this.mStarted);
            }
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
            if (this.shouldCreateUiSessions()) {
                applicationPerformanceMetricsLogging.startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.touchGesture, IClientLogging.ModalView.profilesGate, this.mStarted, display);
                applicationPerformanceMetricsLogging.startUiBrowseStartupSession(this.mStarted);
            }
            this.startNextActivity(ProfileSelectionActivity.createStartIntent((Context)this));
            this.finish();
            return;
        }
        Log.d("LaunchActivity", String.format("Redirect to home with profile %s, %s", serviceManager.getCurrentProfile().getProfileName(), serviceManager.getCurrentProfile().getProfileId()));
        this.startNextActivity(HomeActivity.createStartIntent(this));
        if (this.shouldCreateUiSessions()) {
            applicationPerformanceMetricsLogging.startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.touchGesture, IClientLogging.ModalView.homeScreen, this.mStarted, display);
            applicationPerformanceMetricsLogging.startUiBrowseStartupSession(this.mStarted);
        }
        this.finish();
    }
    
    private void manipulateSplashBackground() {
        final ImageView imageView = (ImageView)this.findViewById(2131165635);
        imageView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            final /* synthetic */ ImageView val$logo = (ImageView)LaunchActivity.this.findViewById(2131165417);
            final /* synthetic */ ProgressBar val$progress = (ProgressBar)LaunchActivity.this.findViewById(2131165418);
            
            public void onGlobalLayout() {
                if (imageView.getWidth() <= 0) {
                    return;
                }
                Log.v("LaunchActivity", "Manipulating splash bg, scale factor: " + 2);
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)imageView.getLayoutParams();
                relativeLayout$LayoutParams.width = imageView.getWidth() * 2;
                relativeLayout$LayoutParams.height = imageView.getHeight() * 2;
                imageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = (RelativeLayout$LayoutParams)this.val$logo.getLayoutParams();
                relativeLayout$LayoutParams2.topMargin *= 2;
                if (DeviceUtils.isLandscape((Context)LaunchActivity.this)) {
                    final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = (RelativeLayout$LayoutParams)this.val$progress.getLayoutParams();
                    relativeLayout$LayoutParams3.topMargin *= 2;
                }
                ViewUtils.removeGlobalLayoutListener((View)imageView, (ViewTreeObserver$OnGlobalLayoutListener)this);
            }
        });
    }
    
    private void registerNflxReceiver() {
        Log.d("LaunchActivity", "Register receiver");
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.HANDLER_RESULT");
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
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                LaunchActivity.this.isLoading = false;
                if (ServiceErrorsHandler.handleManagerResponse(LaunchActivity.this, status)) {
                    return;
                }
                LaunchActivity.this.handleManagerReady(serviceManager);
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
                LaunchActivity.this.isLoading = false;
                ServiceErrorsHandler.handleManagerResponse(LaunchActivity.this, status);
            }
        };
    }
    
    @Override
    public void finish() {
        super.finish();
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
    
    protected void onNewIntent(final Intent intent) {
        Log.d("LaunchActivity", "Received new intent:");
        AndroidUtils.logIntent("LaunchActivity", intent);
        super.onNewIntent(intent);
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
