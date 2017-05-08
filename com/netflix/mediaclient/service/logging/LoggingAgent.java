// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.content.Intent;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.PresentationTracking;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging;
import com.netflix.mediaclient.service.logging.client.model.SessionKey;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.android.volley.NetworkDispatcher;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.servicemgr.ExceptionLoggingCl;
import com.netflix.mediaclient.servicemgr.OfflineLogging;
import com.netflix.mediaclient.servicemgr.IkoLogging;
import com.netflix.mediaclient.servicemgr.SignInLogging;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging;
import com.netflix.mediaclient.servicemgr.ISearchLogging;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import android.content.IntentFilter;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.logging.error.CrittercismErrorLoggingImpl;
import com.netflix.mediaclient.service.logging.breadcrumb.CrittercismBreadcrumbLoggingImpl;
import java.util.Random;
import com.android.volley.NetworkDispatcher$NetworkDispatcherListener;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.Log;
import android.content.BroadcastReceiver;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.CustomerEventLogging;
import com.netflix.mediaclient.servicemgr.CmpEventLogging;
import com.netflix.mediaclient.servicemgr.BreadcrumbLogging;
import com.netflix.mediaclient.service.logging.ads.AdvertiserIdLoggingManager;
import com.facebook.network.connectionclass.ConnectionClassManager$ConnectionClassStateChangeListener;
import java.util.concurrent.ThreadFactory;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent$ConfigAgentListener;
import com.netflix.mediaclient.javabridge.ui.Log$AppIdChangedListener;
import com.netflix.mediaclient.service.ServiceAgent;

public final class LoggingAgent extends ServiceAgent implements Log$AppIdChangedListener, ConfigurationAgent$ConfigAgentListener, IClientLogging
{
    private static final long EVENT_POST_TIMEOUT_MS = 60000L;
    static final String ICL_REPOSITORY_DIR = "iclevents";
    static final String LOGBLOB_REPOSITORY_DIR = "logblobs";
    private static final int NEXT_DELIVERY_ATTEMPT_TIMEOUT_IN_MS = 60000;
    static final String PDSEVENT_REPOSITORY_DIR = "pdsevents";
    static final String PT_REPOSITORY_DIR = "ptevents";
    private static final String TAG = "nf_log";
    public static final long gCritSessionId;
    private static final ThreadFactory sThreadFactory;
    private ConnectionClassManager$ConnectionClassStateChangeListener connectionClassManagerListener;
    private AdvertiserIdLoggingManager mAdvertiserIdLoggingManager;
    private BreadcrumbLogging mBreadcrumbLogging;
    private CmpEventLogging mCmpEventLogging;
    private CustomerEventLogging mCustomerEventLogging;
    private ErrorLogging mErrorLogging;
    private Runnable mEventPostCheck;
    private ScheduledExecutorService mExecutor;
    private AtomicInteger mFailureCounter;
    private IntegratedClientLoggingManager mIntegratedClientLoggingManager;
    private LogblobLoggingImpl mLogblobLogging;
    private final BroadcastReceiver mLoggerReceiver;
    private Log mNrdpLog;
    private PdsLoggingImpl mPdsLogging;
    private PresentationTrackingManager mPresentationTrackingManager;
    private long mStartedTime;
    private final Handler mWorkerHandler;
    private HandlerThread mWorkerThread;
    private NetworkDispatcher$NetworkDispatcherListener networkDispatcherListener;
    
    static {
        gCritSessionId = new Random().nextLong();
        sThreadFactory = new LoggingAgent$1();
    }
    
    public LoggingAgent() {
        this.mStartedTime = System.currentTimeMillis();
        this.mFailureCounter = new AtomicInteger();
        this.mEventPostCheck = new LoggingAgent$2(this);
        this.mLoggerReceiver = new LoggingAgent$3(this);
        com.netflix.mediaclient.Log.d("nf_log", "ClientLoggingAgent::");
        this.mBreadcrumbLogging = new CrittercismBreadcrumbLoggingImpl();
        this.mErrorLogging = new CrittercismErrorLoggingImpl();
        (this.mWorkerThread = new HandlerThread("ClientLoggingAgentWorker")).start();
        this.mWorkerHandler = new Handler(this.mWorkerThread.getLooper());
        this.mPdsLogging = new PdsLoggingImpl(this);
        com.netflix.mediaclient.Log.d("nf_log", "ClientLoggingAgent:: done");
    }
    
    private void addConfigurationChangeListener() {
        final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        if (configurationAgent instanceof ConfigurationAgent) {
            ((ConfigurationAgent)configurationAgent).addConfigAgentListener(this);
        }
    }
    
    private void registerReceiver() {
        final int n = 0;
        com.netflix.mediaclient.Log.d("nf_log", "Register receiver");
        final IntentFilter intentFilter = new IntentFilter();
        final String[] actions = ApplicationPerformanceMetricsLogging.ACTIONS;
        for (int length = actions.length, i = 0; i < length; ++i) {
            intentFilter.addAction(actions[i]);
        }
        final String[] actions2 = UserActionLogging.ACTIONS;
        for (int length2 = actions2.length, j = 0; j < length2; ++j) {
            intentFilter.addAction(actions2[j]);
        }
        final String[] actions3 = UIViewLogging.ACTIONS;
        for (int length3 = actions3.length, k = 0; k < length3; ++k) {
            intentFilter.addAction(actions3[k]);
        }
        final String[] actions4 = ISearchLogging.ACTIONS;
        for (int length4 = actions4.length, l = 0; l < length4; ++l) {
            intentFilter.addAction(actions4[l]);
        }
        final String[] actions5 = CustomerServiceLogging.ACTIONS;
        for (int length5 = actions5.length, n2 = 0; n2 < length5; ++n2) {
            intentFilter.addAction(actions5[n2]);
        }
        final String[] actions6 = SignInLogging.ACTIONS;
        for (int length6 = actions6.length, n3 = 0; n3 < length6; ++n3) {
            intentFilter.addAction(actions6[n3]);
        }
        final String[] actions7 = IkoLogging.ACTIONS;
        for (int length7 = actions7.length, n4 = 0; n4 < length7; ++n4) {
            intentFilter.addAction(actions7[n4]);
        }
        final String[] actions8 = OfflineLogging.ACTIONS;
        for (int length8 = actions8.length, n5 = 0; n5 < length8; ++n5) {
            intentFilter.addAction(actions8[n5]);
        }
        final String[] actions9 = ExceptionLoggingCl.ACTIONS;
        for (int length9 = actions9.length, n6 = n; n6 < length9; ++n6) {
            intentFilter.addAction(actions9[n6]);
        }
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intentFilter.setPriority(999);
        try {
            LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mLoggerReceiver, intentFilter);
        }
        catch (Throwable t) {
            com.netflix.mediaclient.Log.e("nf_log", "Failed to register ", t);
        }
    }
    
    @Override
    public void NrdpLog(final LogArguments logArguments) {
        if (this.mNrdpLog != null) {
            this.mNrdpLog.log(logArguments);
        }
    }
    
    @Override
    public void changed(final String s, final String s2) {
        if (com.netflix.mediaclient.Log.isLoggable()) {
            com.netflix.mediaclient.Log.d("nf_log", "App ID is changed to " + s);
            com.netflix.mediaclient.Log.d("nf_log", "Session ID is changed to " + s2);
        }
        this.mIntegratedClientLoggingManager.recreateSessions(s, s2);
    }
    
    void clearFailureCounter() {
        this.mFailureCounter.set(0);
    }
    
    @Override
    public void destroy() {
        com.netflix.mediaclient.Log.d("nf_log", "PNA:: destroy and unregister receiver");
        IntentUtils.unregisterSafelyLocalBroadcastReceiver(this.getContext(), this.mLoggerReceiver);
        NetworkDispatcher.removeNetworkDispatcherListener(this.networkDispatcherListener);
        ConnectionClassManager.getInstance().remove(this.connectionClassManagerListener);
        if (this.mAdvertiserIdLoggingManager != null) {
            this.mAdvertiserIdLoggingManager.destroy();
        }
        if (this.mIntegratedClientLoggingManager != null) {
            this.mIntegratedClientLoggingManager.destroy();
        }
        if (this.mNrdpLog != null) {
            this.mNrdpLog.setAppIdChangedListener(null);
            this.mNrdpLog = null;
        }
        if (this.mLogblobLogging != null) {
            this.mLogblobLogging.destroy();
        }
        super.destroy();
    }
    
    @Override
    protected void doInit() {
        com.netflix.mediaclient.Log.d("nf_log", "ClientLoggingAgent::init start ");
        this.connectionClassManagerListener = ConnectivityLogger.getConnectionClassManagerListener();
        ConnectionClassManager.getInstance().register(this.connectionClassManagerListener);
        NetworkDispatcher.addNetworkDispatcherListener(this.networkDispatcherListener = ConnectivityLogger.getNetworkDispatcherListener());
        this.mCustomerEventLogging = new LegacyCustomerEventLoggingImpl(this, this.getContext(), this.mWorkerHandler);
        this.mCmpEventLogging = new LegacyCmpEventLoggingImpl(this, this.getContext());
        this.mIntegratedClientLoggingManager = new IntegratedClientLoggingManager(this.getContext(), this, this.getUser(), this.getService());
        this.mPresentationTrackingManager = new PresentationTrackingManager(this.getContext(), this, this.getUser());
        this.mAdvertiserIdLoggingManager = new AdvertiserIdLoggingManager(this.getContext(), this);
        this.mLogblobLogging = new LogblobLoggingImpl(this);
        com.netflix.mediaclient.Log.d("nf_log", "ClientLoggingAgent::init create executor thread start ");
        this.mExecutor = Executors.newSingleThreadScheduledExecutor(LoggingAgent.sThreadFactory);
        com.netflix.mediaclient.Log.d("nf_log", "ClientLoggingAgent::init create executor thread done ");
        this.mExecutor.scheduleAtFixedRate(this.mEventPostCheck, 60000L, 60000L, TimeUnit.MILLISECONDS);
        this.mIntegratedClientLoggingManager.init(this.mExecutor);
        this.mPresentationTrackingManager.init(this.mExecutor);
        this.mAdvertiserIdLoggingManager.init();
        this.mLogblobLogging.init(this.mExecutor);
        this.mPdsLogging.init(this.mExecutor);
        this.registerReceiver();
        ErrorLoggingManager.onConfigurationChanged(this.getContext(), LoggingAgent.gCritSessionId, this.getConfigurationAgent().getErrorLoggingSpecification(), this.getConfigurationAgent().getBreadcrumbLoggingSpecification());
        this.addConfigurationChangeListener();
        (this.mNrdpLog = this.getNrdController().getNrdp().getLog()).setAppIdChangedListener(this);
        this.initCompleted(CommonStatus.OK);
        com.netflix.mediaclient.Log.d("nf_log", "ClientLoggingAgent::init done ");
    }
    
    @Override
    public void flushLoggingEvents() {
        com.netflix.mediaclient.Log.d("nf_log", "Flush events");
        this.mIntegratedClientLoggingManager.flush(true);
        this.mPresentationTrackingManager.flush(true);
    }
    
    public String getAccountOwnerToken() {
        if (this.getService() != null) {
            return this.getService().getAccountOwnerToken();
        }
        return null;
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
    public String getApplicationId() {
        return this.getNrdController().getNrdp().getLog().getAppId();
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
    public LogblobLogging getLogblobLogging() {
        return this.mLogblobLogging;
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
    public IPdsLogging getPdsLogging() {
        return this.mPdsLogging;
    }
    
    @Override
    public PresentationTracking getPresentationTracking() {
        return this.mPresentationTrackingManager;
    }
    
    long getUptime() {
        return System.currentTimeMillis() - this.mStartedTime;
    }
    
    public ServiceAgent$UserAgentInterface getUser() {
        return this.getUserAgent();
    }
    
    @Override
    public String getUserSessionId() {
        return this.getNrdController().getNrdp().getLog().getSessionId();
    }
    
    public boolean handleCommand(final Intent intent) {
        if (intent == null) {
            com.netflix.mediaclient.Log.w("nf_log", "Intent is null");
        }
        else if (com.netflix.mediaclient.Log.isLoggable()) {
            com.netflix.mediaclient.Log.d("nf_log", "Received command " + intent.getAction());
            return false;
        }
        return false;
    }
    
    public void handleConnectivityChange(final Intent intent) {
        if (this.mIntegratedClientLoggingManager != null) {
            this.mIntegratedClientLoggingManager.handleConnectivityChange(intent);
        }
        if (this.mLogblobLogging != null) {
            this.mLogblobLogging.handleConnectivityChange(intent);
        }
        if (this.mPresentationTrackingManager != null) {
            this.mPresentationTrackingManager.handleConnectivityChange(intent);
        }
        if (this.mPdsLogging != null) {
            this.mPdsLogging.handleConnectivityChange(intent);
        }
    }
    
    @Override
    public boolean isReady() {
        return true;
    }
    
    @Override
    public void onConfigRefreshed(final Status status) {
        if (com.netflix.mediaclient.Log.isLoggable()) {
            com.netflix.mediaclient.Log.v("nf_log", "Configuration is refreshed with status code " + status.getStatusCode());
        }
        if (status.isSucces()) {
            com.netflix.mediaclient.Log.v("nf_log", "Refresh configuration for error and breadcrumb logging");
            ErrorLoggingManager.onConfigurationChanged(this.getContext(), LoggingAgent.gCritSessionId, this.getConfigurationAgent().getErrorLoggingSpecification(), this.getConfigurationAgent().getBreadcrumbLoggingSpecification());
        }
    }
    
    @Override
    public void onPlayEnd() {
        com.netflix.mediaclient.Log.d("nf_log", "Flush events");
        this.mPresentationTrackingManager.flush(true);
    }
    
    @Override
    public void onProfileSwitch() {
        com.netflix.mediaclient.Log.d("nf_log", "Flush events");
        this.mIntegratedClientLoggingManager.flush(true);
        this.mPresentationTrackingManager.flush(true);
    }
    
    @Override
    public void onUserLogout() {
        com.netflix.mediaclient.Log.d("nf_log", "onUserLogout");
        this.mIntegratedClientLoggingManager.endAllActiveSessions();
        this.mIntegratedClientLoggingManager.flush(false);
        this.getNrdController().getNrdp().getLog().resetAppID();
        this.mPresentationTrackingManager.flush(false);
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
