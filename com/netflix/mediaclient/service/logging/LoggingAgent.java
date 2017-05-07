// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.PresentationTracking;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging;
import com.netflix.mediaclient.service.logging.client.model.SessionKey;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.servicemgr.ISearchLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import android.content.IntentFilter;
import com.netflix.mediaclient.service.logging.error.CrittercismErrorLoggingImpl;
import com.netflix.mediaclient.service.logging.breadcrumb.CrittercismBreadcrumbLoggingImpl;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.os.HandlerThread;
import android.os.Handler;
import android.content.BroadcastReceiver;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.CustomerEventLogging;
import com.netflix.mediaclient.servicemgr.CmpEventLogging;
import com.netflix.mediaclient.servicemgr.BreadcrumbLogging;
import com.netflix.mediaclient.service.logging.ads.AdvertiserIdLoggingManager;
import java.util.concurrent.ThreadFactory;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.ServiceAgent;

public final class LoggingAgent extends ServiceAgent implements IClientLogging, ConfigAgentListener
{
    private static final long EVENT_POST_TIMEOUT_MS = 60000L;
    static final String ICL_REPOSITORY_DIR = "iclevents";
    static final int NEXT_DELIVERY_ATTEMPT_TIMEOUT_IN_MS = 60000;
    static final String PT_REPOSITORY_DIR = "ptevents";
    private static final String TAG = "nf_log";
    private static final ThreadFactory sThreadFactory;
    private AdvertiserIdLoggingManager mAdvertiserIdLoggingManager;
    private BreadcrumbLogging mBreadcrumbLogging;
    private CmpEventLogging mCmpEventLogging;
    private CustomerEventLogging mCustomerEventLogging;
    private ErrorLogging mErrorLogging;
    private Runnable mEventPostCheck;
    private ScheduledExecutorService mExecutor;
    private AtomicInteger mFailureCounter;
    private IntegratedClientLoggingManager mIntegratedClientLoggingManager;
    private final BroadcastReceiver mLoggerReceiver;
    private PresentationTrackingManager mPresentationTrackingManager;
    private long mStartedTime;
    private final Handler mWorkerHandler;
    private HandlerThread mWorkerThread;
    
    static {
        sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, "LoggingAgent #" + this.mCount.getAndIncrement());
            }
        };
    }
    
    public LoggingAgent() {
        this.mStartedTime = System.currentTimeMillis();
        this.mFailureCounter = new AtomicInteger();
        this.mEventPostCheck = new Runnable() {
            @Override
            public void run() {
                Log.d("nf_log", "Running state check...");
                LoggingAgent.this.mIntegratedClientLoggingManager.checkState();
                LoggingAgent.this.mPresentationTrackingManager.checkState();
                LoggingAgent.this.getApplication().getUserInput().checkState();
                Log.d("nf_log", "Running state check done.");
            }
        };
        this.mLoggerReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (Log.isLoggable("nf_log", 2)) {
                    Log.v("nf_log", "Received intent " + intent);
                }
                LoggingAgent.this.mIntegratedClientLoggingManager.handleIntent(intent);
            }
        };
        Log.d("nf_log", "ClientLoggingAgent::");
        this.mBreadcrumbLogging = new CrittercismBreadcrumbLoggingImpl();
        this.mErrorLogging = new CrittercismErrorLoggingImpl();
        (this.mWorkerThread = new HandlerThread("ClientLoggingAgentWorker")).start();
        this.mWorkerHandler = new Handler(this.mWorkerThread.getLooper());
        Log.d("nf_log", "ClientLoggingAgent:: done");
    }
    
    private void addConfigurationChangeListener() {
        final ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        if (configurationAgent instanceof ConfigurationAgent) {
            ((ConfigurationAgent)configurationAgent).addConfigAgentListener((ConfigurationAgent.ConfigAgentListener)this);
        }
    }
    
    private void registerReceiver() {
        Log.d("nf_log", "Register receiver");
        final IntentFilter intentFilter = new IntentFilter();
        final String[] actions = ApplicationPerformanceMetricsLogging.ACTIONS;
        for (int length = actions.length, i = 0; i < length; ++i) {
            intentFilter.addAction(actions[i]);
        }
        final String[] actions2 = UserActionLogging.ACTIONS;
        for (int length2 = actions2.length, j = 0; j < length2; ++j) {
            intentFilter.addAction(actions2[j]);
        }
        final String[] actions3 = UIViewLoggingImpl.ACTIONS;
        for (int length3 = actions3.length, k = 0; k < length3; ++k) {
            intentFilter.addAction(actions3[k]);
        }
        final String[] actions4 = ISearchLogging.ACTIONS;
        for (int length4 = actions4.length, l = 0; l < length4; ++l) {
            intentFilter.addAction(actions4[l]);
        }
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intentFilter.setPriority(999);
        try {
            LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mLoggerReceiver, intentFilter);
        }
        catch (Throwable t) {
            Log.e("nf_log", "Failed to register ", t);
        }
    }
    
    private void unregisterReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.mLoggerReceiver);
        }
        catch (Throwable t) {
            Log.e("nf_log", "Failed to unregister ", t);
        }
    }
    
    void clearFailureCounter() {
        this.mFailureCounter.set(0);
    }
    
    @Override
    public void destroy() {
        Log.d("nf_log", "PNA:: destroy and unregister receiver");
        this.unregisterReceiver();
        if (this.mAdvertiserIdLoggingManager != null) {
            this.mAdvertiserIdLoggingManager.destroy();
        }
        if (this.mIntegratedClientLoggingManager != null) {
            this.mIntegratedClientLoggingManager.destroy();
        }
        super.destroy();
    }
    
    @Override
    protected void doInit() {
        Log.d("nf_log", "ClientLoggingAgent::init start ");
        this.mCustomerEventLogging = new LegacyCustomerEventLoggingImpl(this, this.getContext(), this.mWorkerHandler);
        this.mCmpEventLogging = new LegacyCmpEventLoggingImpl(this, this.getContext());
        this.mIntegratedClientLoggingManager = new IntegratedClientLoggingManager(this.getContext(), this, this.getUser(), this.getService());
        this.mPresentationTrackingManager = new PresentationTrackingManager(this.getContext(), this, this.getUser());
        this.mAdvertiserIdLoggingManager = new AdvertiserIdLoggingManager(this.getContext(), this);
        Log.d("nf_log", "ClientLoggingAgent::init create executor thread start ");
        this.mExecutor = Executors.newSingleThreadScheduledExecutor(LoggingAgent.sThreadFactory);
        Log.d("nf_log", "ClientLoggingAgent::init create executor thread done ");
        this.mExecutor.scheduleAtFixedRate(this.mEventPostCheck, 60000L, 60000L, TimeUnit.MILLISECONDS);
        this.mIntegratedClientLoggingManager.init(this.mExecutor);
        this.mPresentationTrackingManager.init(this.mExecutor);
        this.mAdvertiserIdLoggingManager.init();
        this.registerReceiver();
        ErrorLoggingManager.onConfigurationChanged(this.getConfigurationAgent().getErrorLoggingSpecification(), this.getConfigurationAgent().getBreadcrumbLoggingSpecification());
        this.addConfigurationChangeListener();
        this.initCompleted(CommonStatus.OK);
        Log.d("nf_log", "ClientLoggingAgent::init done ");
    }
    
    @Override
    public void flush() {
        Log.d("nf_log", "Flush events");
        this.mIntegratedClientLoggingManager.flush();
        this.mPresentationTrackingManager.flush();
    }
    
    @Override
    public List<SessionKey> getActiveLoggingSessions() {
        if (this.mIntegratedClientLoggingManager == null) {
            return null;
        }
        return this.mIntegratedClientLoggingManager.getActiveSessions();
    }
    
    @Override
    public AdvertiserIdLogging getAdvertiserIdLogging() {
        return this.mAdvertiserIdLoggingManager;
    }
    
    @Override
    public ApplicationPerformanceMetricsLogging getApplicationPerformanceMetricsLogging() {
        final IntegratedClientLoggingManager mIntegratedClientLoggingManager = this.mIntegratedClientLoggingManager;
        if (mIntegratedClientLoggingManager == null) {
            return null;
        }
        return mIntegratedClientLoggingManager.getApmLogging();
    }
    
    @Override
    public BreadcrumbLogging getBreadcrumbLogging() {
        return this.mBreadcrumbLogging;
    }
    
    @Override
    public CmpEventLogging getCmpEventLogging() {
        return this.mCmpEventLogging;
    }
    
    @Override
    public CustomerEventLogging getCustomerEventLogging() {
        return this.mCustomerEventLogging;
    }
    
    @Override
    public ErrorLogging getErrorLogging() {
        return this.mErrorLogging;
    }
    
    @Override
    public long getNextSequence() {
        if (this.mIntegratedClientLoggingManager == null) {
            return 0L;
        }
        return this.mIntegratedClientLoggingManager.getNextSequence();
    }
    
    long getNextTimeToDeliverAfterFailure() {
        return this.mFailureCounter.incrementAndGet() * 60000;
    }
    
    @Override
    public PresentationTracking getPresentationTracking() {
        return this.mPresentationTrackingManager;
    }
    
    long getUptime() {
        return System.currentTimeMillis() - this.mStartedTime;
    }
    
    public UserAgentInterface getUser() {
        return this.getUserAgent();
    }
    
    public String getUserId() {
        if (this.getService() != null) {
            return this.getService().getUserId();
        }
        return null;
    }
    
    public boolean handleCommand(final Intent intent) {
        if (intent == null) {
            Log.w("nf_log", "Intent is null");
        }
        else if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "Received command " + intent.getAction());
            return false;
        }
        return false;
    }
    
    public void handleConnectivityChange(final Intent intent) {
        if (this.mIntegratedClientLoggingManager != null) {
            this.mIntegratedClientLoggingManager.handleConnectivityChange(intent);
        }
    }
    
    @Override
    public boolean isReady() {
        return true;
    }
    
    @Override
    public void onConfigRefreshed(final Status status) {
        if (Log.isLoggable("nf_log", 2)) {
            Log.v("nf_log", "Configuration is refreshed with status code " + status.getStatusCode());
        }
        if (status.isSucces()) {
            Log.v("nf_log", "Refresh configuration for error and breadcrumb logging");
            ErrorLoggingManager.onConfigurationChanged(this.getConfigurationAgent().getErrorLoggingSpecification(), this.getConfigurationAgent().getBreadcrumbLoggingSpecification());
        }
    }
    
    @Override
    public void pauseDelivery() {
        this.mIntegratedClientLoggingManager.pauseDelivery();
        this.mPresentationTrackingManager.pauseDelivery();
    }
    
    @Override
    public void resumeDelivery(final boolean b) {
        this.mIntegratedClientLoggingManager.resumeDelivery(b);
        this.mPresentationTrackingManager.resumeDelivery(b);
    }
    
    @Override
    public void setDataContext(final DataContext dataContext) {
        this.mIntegratedClientLoggingManager.setDataContext(dataContext);
    }
}
