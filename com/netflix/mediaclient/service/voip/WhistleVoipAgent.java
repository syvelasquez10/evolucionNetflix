// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.servicemgr.IVoip$CallState;
import com.netflix.mediaclient.service.webclient.model.leafs.VoipAuthorizationData;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.vailsys.whistleengine.WhistleEngineDelegate$ConnectivityState;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import java.util.Iterator;
import com.netflix.mediaclient.util.log.CustomerServiceLogUtils;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$CallQuality;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.servicemgr.IVoip$AuthorizationTokens;
import com.netflix.mediaclient.util.FileUtils;
import com.vailsys.whistleengine.WhistleEngineConfig$TransportMode;
import com.vailsys.whistleengine.WhistleEngineConfig;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.annotation.TargetApi;
import android.os.PowerManager;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixService;
import java.util.Collections;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.os.PowerManager$WakeLock;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import java.util.List;
import com.vailsys.whistleengine.WhistleEngine;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.servicemgr.IVoip$ConnectivityState;
import android.content.ServiceConnection;
import com.vailsys.whistleengine.WhistleEngineDelegate;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.service.ServiceAgent;

public class WhistleVoipAgent extends ServiceAgent implements VoipAuthorizationTokensUpdater, IVoip, WhistleEngineDelegate
{
    private static final long DELAYED_DIAL = 100L;
    private static final float MIN_PROXIMITY = 0.5f;
    private static final String TAG = "nf_voip";
    Runnable cancelAction;
    private AuthorizationTokensManager mAuthorizationTokensManager;
    private final ServiceConnection mConnection;
    private IVoip$ConnectivityState mConnectivityState;
    private WhistleVoipAgent$WhistleCall mCurrentCall;
    private AtomicBoolean mDialRequested;
    private WhistleEngine mEngine;
    private AtomicBoolean mEngineReady;
    private boolean mEngineStarted;
    private List<IVoip$OutboundCallListener> mListeners;
    private CallNotificationManager mNotificationManager;
    private PowerManager$WakeLock mProximityWakeLock;
    private AtomicBoolean mReady;
    private WhistleVoipAgent$ServiceState mServiceState;
    private long mStartTime;
    private final BroadcastReceiver mVoipReceiver;
    
    public WhistleVoipAgent(final Context context, final ServiceAgent$UserAgentInterface serviceAgent$UserAgentInterface) {
        this.mReady = new AtomicBoolean(false);
        this.mEngineReady = new AtomicBoolean(false);
        this.mConnectivityState = IVoip$ConnectivityState.NO_CONNECTION;
        this.mServiceState = WhistleVoipAgent$ServiceState.NOT_STARTED;
        this.mListeners = Collections.synchronizedList(new ArrayList<IVoip$OutboundCallListener>());
        this.mDialRequested = new AtomicBoolean(false);
        this.mConnection = (ServiceConnection)new WhistleVoipAgent$1(this);
        this.cancelAction = new WhistleVoipAgent$2(this);
        this.mVoipReceiver = new WhistleVoipAgent$5(this);
        this.mAuthorizationTokensManager = new AuthorizationTokensManager(context, serviceAgent$UserAgentInterface);
    }
    
    private void acquireScreenLock() {
        if (this.mProximityWakeLock == null) {
            return;
        }
        if (!this.mProximityWakeLock.isHeld()) {
            Log.d("nf_voip", "updateProximitySensorMode: acquiring...");
            this.mProximityWakeLock.acquire();
            return;
        }
        Log.w("nf_voip", "updateProximitySensorMode: lock already held.");
    }
    
    private void callCleanup() {
        this.mDialRequested.set(false);
        this.cancelNotification();
        this.releaseScreenLock();
        this.mStartTime = 0L;
        LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(new Intent("com.netflix.mediaclient.ui.cs.ACTION_CALL_ENDED"));
    }
    
    private void cancelNotification() {
        if (this.mNotificationManager != null) {
            this.mNotificationManager.cancelNotification();
        }
    }
    
    @TargetApi(21)
    private void createProximityLock() {
        if (AndroidUtils.getAndroidVersion() < 21) {
            Log.d("nf_voip", "Proximity screen wake off is not supported pre L");
            return;
        }
        final PowerManager powerManager = (PowerManager)this.getContext().getSystemService("power");
        if (powerManager == null) {
            Log.w("nf_voip", "Power manager is not available!");
            return;
        }
        if (!powerManager.isWakeLockLevelSupported(32)) {
            Log.d("nf_voip", "Proximity screen wake off is not supported on this device");
            return;
        }
        Log.d("nf_voip", "Proximity screen wake off is supported on this device");
        try {
            this.mProximityWakeLock = powerManager.newWakeLock(32, "nf_voip");
        }
        catch (Throwable t) {
            Log.e("nf_voip", "Failed to created new wake lock for promixity!");
        }
    }
    
    private void doDial() {
        new BackgroundTask().execute(new WhistleVoipAgent$3(this));
    }
    
    private void doDialWithEngineCheck() {
        if (!this.mDialRequested.get()) {
            Log.d("nf_voip", "No dial request, no need to start engine");
            return;
        }
        if (!this.startEngine()) {
            Log.d("nf_voip", "Wait to start dial when callback that VOIP engine is started returns!");
            return;
        }
        this.doDial();
    }
    
    private void doTerminate() {
        new BackgroundTask().execute(new WhistleVoipAgent$4(this));
    }
    
    private int findLine() {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Number of lines: " + this.mEngine.getMaxLines());
        }
        if (this.mEngine.getMaxLines() > 0) {
            return 1;
        }
        Log.e("nf_voip", "All lines in use?!");
        return -1;
    }
    
    private WhistleEngineConfig getConfiguration() {
        final IVoip$AuthorizationTokens authorizationTokens = this.mAuthorizationTokensManager.getAuthorizationTokens();
        if (authorizationTokens == null) {
            Log.e("nf_voip", "Unable to create configuration, tokens not found!");
            return null;
        }
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Connect to VOIP proxy: netflixvoip.vailsys.com");
        }
        final WhistleEngineConfig whistleEngineConfig = new WhistleEngineConfig("netflixvoip.vailsys.com", authorizationTokens.getUserToken());
        whistleEngineConfig.setApplicationIdentifier("samurai");
        whistleEngineConfig.setPassword(authorizationTokens.getEncToken());
        whistleEngineConfig.setTransportMode(WhistleEngineConfig$TransportMode.TLS);
        Log.d("nf_voip", "SSL proxy server validation is enabled, set root certificate(s)...");
        try {
            final String rawString = FileUtils.readRawString(this.getContext(), 2131099650);
            if (Log.isLoggable()) {
                Log.d("nf_voip", "PEM\n" + rawString);
            }
            whistleEngineConfig.setRootCertificate(rawString);
            return whistleEngineConfig;
        }
        catch (Throwable t) {
            Log.e("nf_voip", "We failed to load VOIP trust store from res/raw. All attempt to connect will fail!", t);
            return null;
        }
    }
    
    private Intent getServiceIntent() {
        return new Intent(this.getContext(), (Class)WhistleEngine.class);
    }
    
    private boolean isServiceStarted() {
        return this.mServiceState == WhistleVoipAgent$ServiceState.STARTED;
    }
    
    private boolean isServiceStartedOrStarting() {
        return this.mServiceState == WhistleVoipAgent$ServiceState.STARTED || this.mServiceState == WhistleVoipAgent$ServiceState.STARTING;
    }
    
    private boolean isServiceStoppedOrStopping() {
        return this.mServiceState == WhistleVoipAgent$ServiceState.NOT_STARTED || this.mServiceState == WhistleVoipAgent$ServiceState.STOPPED || this.mServiceState == WhistleVoipAgent$ServiceState.STOPPING;
    }
    
    private void registerReceiver() {
        Log.d("nf_voip", "Registering VOIP receiver...");
        this.getContext().registerReceiver(this.mVoipReceiver, CallNotificationManager.getNotificationIntentFilter());
        Log.d("nf_voip", "Registered VOIP receiver");
    }
    
    private void releaseScreenLock() {
        if (this.mProximityWakeLock == null) {
            return;
        }
        if (this.mProximityWakeLock.isHeld()) {
            Log.d("nf_voip", "updateProximitySensorMode: releasing...");
            this.mProximityWakeLock.release();
            return;
        }
        Log.w("nf_voip", "updateProximitySensorMode: lock already released!");
    }
    
    private boolean startEngine() {
        if (this.mEngine != null) {
            if (!this.mEngineStarted) {
                final WhistleEngineConfig configuration = this.getConfiguration();
                if (configuration == null) {
                    Log.e("nf_voip", "Failed to get configuration, unable to start engine!");
                    return false;
                }
                Log.d("nf_voip", "Start VOIP engine");
                try {
                    this.mEngine.start(this, configuration, this.getContext());
                    this.mEngineStarted = true;
                    return false;
                }
                catch (Throwable t) {
                    Log.e("nf_voip", "Whistle engine was unable to start, report failure", t);
                    this.getService().getErrorHandler().addError(VoipErrorDialogDescriptorFactory.getHandlerForEngineFailed(this.getContext(), this.cancelAction));
                    return false;
                }
            }
            Log.w("nf_voip", "Engine already started!");
            return true;
        }
        return false;
    }
    
    private void stopEngine() {
        synchronized (this) {
            if (this.mEngine != null) {
                if (this.mEngineStarted) {
                    Log.d("nf_voip", "Stop VOIP engine");
                    this.mEngine.stop();
                    this.mEngineReady.set(false);
                }
                else {
                    Log.w("nf_voip", "Engine already stopped!");
                }
            }
            this.mEngineStarted = false;
        }
    }
    
    private void unregisterReceiver() {
        final Context context = this.getContext();
        if (context == null) {
            Log.d("nf_voip", "Context is null, nothing to unregister.");
            return;
        }
        try {
            Log.d("nf_voip", "Unregistering VOIP receiver...");
            context.unregisterReceiver(this.mVoipReceiver);
            Log.d("nf_voip", "Unregistered VOIP receiver");
        }
        catch (Exception ex) {
            Log.d("nf_voip", "unregister VOIP receiver  " + ex);
        }
    }
    
    @Override
    public void addOutboundCallListener(final IVoip$OutboundCallListener voip$OutboundCallListener) {
        synchronized (this) {
            if (this.mListeners.contains(voip$OutboundCallListener)) {
                Log.w("nf_voip", "Listener is already added!");
            }
            else {
                this.mListeners.add(voip$OutboundCallListener);
            }
        }
    }
    
    @Override
    public void authenticationNeeded(final boolean b) {
        if (Log.isLoggable()) {
            Log.e("nf_voip", "authenticationNeeded " + b);
        }
    }
    
    @Override
    public void callConnected(final int n) {
        while (true) {
            while (true) {
                Label_0218: {
                    Label_0173: {
                        synchronized (this) {
                            if (Log.isLoggable()) {
                                Log.d("nf_voip", "Outbound call connected on line " + n);
                            }
                            if (this.mEngine != null) {
                                if (this.mCurrentCall == null) {
                                    Log.w("nf_voip", "Call was NOT in progress and we received connected on line " + n);
                                }
                                else {
                                    if (this.mCurrentCall.line != n) {
                                        break Label_0173;
                                    }
                                    final Iterator<IVoip$OutboundCallListener> iterator = this.mListeners.iterator();
                                    while (iterator.hasNext()) {
                                        iterator.next().callConnected(this.mCurrentCall);
                                    }
                                }
                                this.mConnectivityState = IVoip$ConnectivityState.GREEN;
                                this.mNotificationManager.updateConnectedNotification();
                                CustomerServiceLogUtils.reportCallConnected(this.getContext(), CustomerServiceLogging$CallQuality.green);
                                Log.d("nf_voip", "Sets start time...");
                                this.mStartTime = System.currentTimeMillis();
                                return;
                            }
                            break Label_0218;
                        }
                    }
                    Log.e("nf_voip", "Call is in progress on line " + this.mCurrentCall.line + " but we received connect on line " + n);
                    return;
                }
                Log.e("nf_voip", "Engine is null and we received call connected! Should not happen!");
                continue;
            }
        }
    }
    
    @Override
    public void callDisconnected(final int n) {
        while (true) {
            while (true) {
                Label_0200: {
                    Label_0155: {
                        synchronized (this) {
                            if (Log.isLoggable()) {
                                Log.d("nf_voip", "Outbound call disconnected on line " + n);
                            }
                            if (this.mEngine != null) {
                                if (this.mCurrentCall == null) {
                                    Log.w("nf_voip", "Call was NOT in progress and we received disconnect on line " + n);
                                }
                                else {
                                    if (this.mCurrentCall.line != n) {
                                        break Label_0155;
                                    }
                                    final Iterator<IVoip$OutboundCallListener> iterator = this.mListeners.iterator();
                                    while (iterator.hasNext()) {
                                        iterator.next().callDisconnected(this.mCurrentCall);
                                    }
                                }
                                this.mConnectivityState = IVoip$ConnectivityState.NO_CONNECTION;
                                CustomerServiceLogUtils.reportCallSessionEnded(this.getContext(), IClientLogging$CompletionReason.canceled, null);
                                this.callCleanup();
                                return;
                            }
                            break Label_0200;
                        }
                    }
                    Log.e("nf_voip", "Call is in progress on line " + this.mCurrentCall.line + " but we received disconnect on line " + n);
                    return;
                }
                Log.e("nf_voip", "Engine is null and we received call disconnect! Should not happen!");
                continue;
            }
        }
    }
    
    @Override
    public void callEnded(final int n) {
        while (true) {
            Label_0175: {
                while (true) {
                    synchronized (this) {
                        if (Log.isLoggable()) {
                            Log.d("nf_voip", "Outbound call ended on line " + n);
                        }
                        if (this.mEngine == null) {
                            break Label_0175;
                        }
                        if (this.mCurrentCall == null) {
                            Log.w("nf_voip", "Call was NOT in progress and we received call ended on line " + n);
                            final Iterator<IVoip$OutboundCallListener> iterator = this.mListeners.iterator();
                            while (iterator.hasNext()) {
                                iterator.next().callEnded(this.mCurrentCall);
                            }
                            break;
                        }
                    }
                    if (this.mCurrentCall.line != n) {
                        Log.e("nf_voip", "Call is in progress on line " + this.mCurrentCall.line + " but we received call ended on line " + n);
                        continue;
                    }
                    continue;
                }
            }
            Log.e("nf_voip", "Engine is null and we received call ended! Should not happen!");
            break;
        }
        this.mCurrentCall = null;
        this.mConnectivityState = IVoip$ConnectivityState.NO_CONNECTION;
        this.stopEngine();
        CustomerServiceLogUtils.reportCallSessionEnded(this.getContext(), IClientLogging$CompletionReason.success, null);
        this.callCleanup();
    }
    // monitorexit(this)
    
    @Override
    public void callFailed(final int n, final String s, final int n2) {
        while (true) {
            while (true) {
                Label_0252: {
                    Label_0207: {
                        synchronized (this) {
                            if (Log.isLoggable()) {
                                Log.d("nf_voip", "Outbound call failed on line " + n + ", error " + s + ", sipCode " + n2);
                            }
                            this.getService().getErrorHandler().addError(VoipErrorDialogDescriptorFactory.getHandlerForCallFailed(this.getContext(), s, n2));
                            if (this.mEngine != null) {
                                if (this.mCurrentCall == null) {
                                    Log.w("nf_voip", "Call was NOT in progress and we received call failed on line " + n);
                                }
                                else {
                                    if (this.mCurrentCall.line != n) {
                                        break Label_0207;
                                    }
                                    final Iterator<IVoip$OutboundCallListener> iterator = this.mListeners.iterator();
                                    while (iterator.hasNext()) {
                                        iterator.next().callFailed(this.mCurrentCall, s, n2);
                                    }
                                }
                                this.mCurrentCall = null;
                                this.mConnectivityState = IVoip$ConnectivityState.NO_CONNECTION;
                                CustomerServiceLogUtils.reportCallSessionEnded(this.getContext(), IClientLogging$CompletionReason.failed, null);
                                this.callCleanup();
                                return;
                            }
                            break Label_0252;
                        }
                    }
                    Log.e("nf_voip", "Call is in progress on line " + this.mCurrentCall.line + " but we received call failed on line " + n);
                    return;
                }
                Log.e("nf_voip", "Engine is null and we received call failed! Should not happen!");
                continue;
            }
        }
    }
    
    @Override
    public void callRinging(final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Outbound call ringing on line " + n);
        }
        if (this.mEngine != null) {
            if (this.mCurrentCall == null) {
                Log.w("nf_voip", "Call was NOT in progress and we received call ringing on line " + n);
            }
            else {
                if (this.mCurrentCall.line != n) {
                    Log.e("nf_voip", "Call is in progress on line " + this.mCurrentCall.line + " but we received call ringing on line " + n);
                    return;
                }
                final Iterator<IVoip$OutboundCallListener> iterator = this.mListeners.iterator();
                while (iterator.hasNext()) {
                    iterator.next().callRinging(this.mCurrentCall);
                }
            }
            return;
        }
        Log.e("nf_voip", "Engine is null and we received call ringing! Should not happen!");
    }
    
    @Override
    public void callSecured(final int n, final boolean b) {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Not supported:::callSecured for line " + n + ", ? " + b);
        }
    }
    
    @Override
    public void connectivityUpdate(final int n, final WhistleEngineDelegate$ConnectivityState whistleEngineDelegate$ConnectivityState) {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "connectivityUpdate for line " + n + ", state " + whistleEngineDelegate$ConnectivityState);
        }
        final CustomerServiceLogging$CallQuality customerServiceLogging$CallQuality = null;
        CustomerServiceLogging$CallQuality customerServiceLogging$CallQuality2;
        if (whistleEngineDelegate$ConnectivityState == WhistleEngineDelegate$ConnectivityState.GREEN) {
            this.mConnectivityState = IVoip$ConnectivityState.GREEN;
            customerServiceLogging$CallQuality2 = CustomerServiceLogging$CallQuality.green;
        }
        else if (whistleEngineDelegate$ConnectivityState == WhistleEngineDelegate$ConnectivityState.RED) {
            this.mConnectivityState = IVoip$ConnectivityState.RED;
            customerServiceLogging$CallQuality2 = CustomerServiceLogging$CallQuality.red;
        }
        else if (whistleEngineDelegate$ConnectivityState == WhistleEngineDelegate$ConnectivityState.YELLOW) {
            this.mConnectivityState = IVoip$ConnectivityState.YELLOW;
            customerServiceLogging$CallQuality2 = CustomerServiceLogging$CallQuality.yellow;
        }
        else {
            Log.e("nf_voip", "Uknown state!");
            customerServiceLogging$CallQuality2 = customerServiceLogging$CallQuality;
        }
        if (customerServiceLogging$CallQuality2 != null) {
            CustomerServiceLogUtils.reportCallQualityChanged(this.getContext(), customerServiceLogging$CallQuality2);
        }
    }
    
    @Override
    public void destroy() {
        this.unregisterReceiver();
        this.stop();
    }
    
    @Override
    public boolean dial() {
        while (true) {
            Label_0072: {
                synchronized (this) {
                    if (this.mDialRequested.get()) {
                        Log.d("nf_voip", "Request for dial is already in progress!");
                    }
                    else {
                        CustomerServiceLogUtils.reportCallSessionStarted(this.getContext());
                        this.mDialRequested.set(true);
                        this.start();
                        if (this.mReady.get()) {
                            break Label_0072;
                        }
                        Log.d("nf_voip", "Wait to start dial when callback that VOIP service is started returns!");
                    }
                    return true;
                }
            }
            this.doDialWithEngineCheck();
            return true;
        }
    }
    
    @Override
    protected void doInit() {
        this.mNotificationManager = new CallNotificationManager(this.getContext());
        this.registerReceiver();
        this.createProximityLock();
        this.initCompleted(CommonStatus.OK);
    }
    
    @Override
    public void engineNotReady() {
        Log.w("nf_voip", "Engine is NOT ready!");
        this.mEngineReady.set(false);
    }
    
    @Override
    public void engineReady() {
        Log.d("nf_voip", "Engine is ready");
        this.mEngineReady.set(true);
        final Iterator<IVoip$OutboundCallListener> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().engineStatusChanged(true);
        }
        this.doDial();
    }
    
    @Override
    public long getCallStartTimeInMs() {
        return this.mStartTime;
    }
    
    @Override
    public IVoip$ConnectivityState getConnectivityState() {
        return this.mConnectivityState;
    }
    
    @Override
    public IVoip$Call getCurrentCall() {
        return this.mCurrentCall;
    }
    
    @Override
    public float getMicrophoneInputLevel() {
        if (this.mEngine != null) {
            return this.mEngine.getInputLevel();
        }
        return 0.0f;
    }
    
    @Override
    public void incomingCall(final int n, final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Incomming call on line " + n + " from " + s + ", name " + s2);
        }
        if (this.mEngine != null) {
            Log.d("nf_voip", "We do not support incomming calls, terminate");
            this.mEngine.hangup(n);
            return;
        }
        Log.e("nf_voip", "Engine is null and we received callback! Should not happen!");
    }
    
    @Override
    public boolean isCallInProgress() {
        return this.mDialRequested.get();
    }
    
    @Override
    public boolean isConnected() {
        return this.mConnectivityState != IVoip$ConnectivityState.NO_CONNECTION && !this.getConfigurationAgent().shouldDisableVoip();
    }
    
    @Override
    public boolean isEnabled() {
        boolean b = false;
        if (this.getConfigurationAgent() != null) {
            b = b;
            if (!this.getConfigurationAgent().shouldDisableVoip()) {
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public boolean isReady() {
        synchronized (this) {
            return this.mReady.get() && this.mEngine != null;
        }
    }
    
    @Override
    public boolean refreshAuthorizationTokens() {
        return this.mAuthorizationTokensManager.refreshAuthorizationTokens();
    }
    
    @Override
    public void registrationSuccessful() {
        Log.w("nf_voip", "RegistrationSuccessful? This should not happen!");
    }
    
    @Override
    public boolean removeOutboundCallListener(final IVoip$OutboundCallListener voip$OutboundCallListener) {
        synchronized (this) {
            return this.mListeners.remove(voip$OutboundCallListener);
        }
    }
    
    @Override
    public void removeUserAuthorizationTokens() {
        this.mAuthorizationTokensManager.removeUserTokens();
    }
    
    @Override
    public void selectedCodec(final int n, final String s, final int n2) {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Selected coded for line " + n + ", codec " + s + ", sample rate " + n2);
        }
    }
    
    @Override
    public void setMicrophoneMute(final boolean mute) {
        if (this.mEngine != null) {
            this.mEngine.setMute(mute);
        }
    }
    
    @Override
    public void setOutputVolume(final float outputVolume) {
        if (this.mEngine != null) {
            Log.d("nf_voip", "Set volume...");
            this.mEngine.setOutputVolume(outputVolume);
        }
    }
    
    @Override
    public boolean start() {
        final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        boolean b;
        if (configurationAgent != null && !configurationAgent.shouldDisableVoip()) {
            b = true;
        }
        else {
            b = false;
        }
        if (b && !this.isServiceStartedOrStarting()) {
            Log.d("nf_voip", "VOIP service is enabled and it is not ready, start it.");
            this.mServiceState = WhistleVoipAgent$ServiceState.STARTING;
            this.getApplication().bindService(this.getServiceIntent(), this.mConnection, 1);
        }
        else if (b) {
            Log.d("nf_voip", "VOIP service is NOT enabled or it started or starting, no need to start it.");
            return true;
        }
        return false;
    }
    
    @Override
    public void startDTMF(final char c) {
        if (this.mEngine != null) {
            this.mEngine.startDTMF(c);
        }
    }
    
    @Override
    public void stop() {
        final boolean b = true;
        if (this.mReady.get() && this.isServiceStoppedOrStopping() && this.mConnection != null) {
            Log.d("nf_voip", "Stop VOIP service");
            this.mServiceState = WhistleVoipAgent$ServiceState.STOPPING;
            this.getService().unbindService(this.mConnection);
        }
        else {
            final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
            if (Log.isLoggable() && configurationAgent != null) {
                Log.w("nf_voip", "VOIP service is enabled " + !configurationAgent.shouldDisableVoip() + ", ready " + this.mReady.get() + ", connection is null " + (this.mConnection == null && b) + ", skip stop!");
            }
        }
    }
    
    @Override
    public void stopDTMF() {
        if (this.mEngine != null) {
            this.mEngine.stopDTMF();
        }
    }
    
    @Override
    public boolean terminate() {
        synchronized (this) {
            CustomerServiceLogUtils.reportCallSessionEnded(this.getContext(), IClientLogging$CompletionReason.canceled, null);
            this.callCleanup();
            boolean b;
            if (this.mEngine == null) {
                Log.e("nf_voip", "Engine is null, unable to terminate call!");
                b = false;
            }
            else {
                if (this.mCurrentCall == null) {
                    Log.e("nf_voip", "Current call is null, unable to terminate call!");
                }
                else {
                    this.doTerminate();
                }
                this.mCurrentCall = null;
                b = true;
            }
            return b;
        }
    }
    
    @Override
    public void updateAuthorizationData(final VoipAuthorizationData voipAuthorizationData) {
        this.mAuthorizationTokensManager.updateAuthorizationData(voipAuthorizationData);
    }
}
