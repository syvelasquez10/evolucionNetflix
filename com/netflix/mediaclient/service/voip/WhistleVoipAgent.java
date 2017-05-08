// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.servicemgr.IVoip$CallState;
import com.netflix.mediaclient.service.webclient.model.leafs.VoipAuthorizationData;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement$Debug;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.vailsys.whistleengine.WhistleEngineDelegate$ConnectivityState;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$TerminationReason;
import com.netflix.mediaclient.util.log.CustomerServiceLogUtils;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$CallQuality;
import android.os.Process;
import android.media.AudioManager;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.service.webclient.model.leafs.VoipConfiguration;
import com.vailsys.whistleengine.WhistleEngineThresholds;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.IVoip$AuthorizationTokens;
import com.netflix.mediaclient.util.FileUtils;
import com.vailsys.whistleengine.WhistleEngineConfig$TransportMode;
import com.vailsys.whistleengine.WhistleEngineConfig;
import com.netflix.mediaclient.service.user.UserLocaleRepository;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.util.l10n.UserLocale;
import com.netflix.mediaclient.service.NetflixService;
import java.util.Collections;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import java.util.List;
import com.vailsys.whistleengine.WhistleEngine;
import com.netflix.mediaclient.servicemgr.IVoip$ConnectivityState;
import android.content.ServiceConnection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ThreadFactory;
import com.vailsys.whistleengine.WhistleEngineDelegate;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.service.ServiceAgent;

public class WhistleVoipAgent extends ServiceAgent implements VoipAuthorizationTokensUpdater, IVoip, WhistleEngineDelegate
{
    private static final long DELAYED_DIAL = 100L;
    private static final float MIN_PROXIMITY = 0.5f;
    private static final String TAG = "nf_voip";
    private static final ThreadFactory sThreadFactory;
    Runnable cancelAction;
    private AtomicBoolean mAudioFocusRequested;
    private AuthorizationTokensManager mAuthorizationTokensManager;
    private final ServiceConnection mConnection;
    private IVoip$ConnectivityState mConnectivityState;
    private WhistleVoipAgent$WhistleCall mCurrentCall;
    private AtomicBoolean mDialRequested;
    private WhistleEngine mEngine;
    private AtomicBoolean mEngineReady;
    private boolean mEngineStarted;
    private List<IVoip$OutboundCallListener> mListeners;
    private PowerLockManager mLockManager;
    private CallNotificationManager mNotificationManager;
    AudioManager$OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    private AtomicBoolean mReady;
    private WhistleVoipAgent$ServiceState mServiceState;
    private String mSharedSessionId;
    private long mStartTime;
    private final BroadcastReceiver mVoipReceiver;
    
    static {
        sThreadFactory = new WhistleVoipAgent$6();
    }
    
    public WhistleVoipAgent(final Context context, final ServiceAgent$UserAgentInterface serviceAgent$UserAgentInterface) {
        this.mReady = new AtomicBoolean(false);
        this.mEngineReady = new AtomicBoolean(false);
        this.mConnectivityState = IVoip$ConnectivityState.NO_CONNECTION;
        this.mServiceState = WhistleVoipAgent$ServiceState.NOT_STARTED;
        this.mListeners = Collections.synchronizedList(new ArrayList<IVoip$OutboundCallListener>());
        this.mDialRequested = new AtomicBoolean(false);
        this.mAudioFocusRequested = new AtomicBoolean(false);
        this.mConnection = (ServiceConnection)new WhistleVoipAgent$1(this);
        this.cancelAction = new WhistleVoipAgent$2(this);
        this.mVoipReceiver = new WhistleVoipAgent$5(this);
        this.mOnAudioFocusChangeListener = (AudioManager$OnAudioFocusChangeListener)new WhistleVoipAgent$7(this);
        this.mAuthorizationTokensManager = new AuthorizationTokensManager(context, serviceAgent$UserAgentInterface);
        this.mLockManager = new PowerLockManager(context, this);
        this.mNotificationManager = new CallNotificationManager(context);
    }
    
    private void callCleanup() {
        this.mDialRequested.set(false);
        this.cancelNotification();
        this.mLockManager.callEnded();
        this.releaseAudioFocus();
        this.mStartTime = 0L;
        this.mCurrentCall = null;
        this.mConnectivityState = IVoip$ConnectivityState.NO_CONNECTION;
        LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(new Intent("com.netflix.mediaclient.ui.cs.ACTION_CALL_ENDED"));
    }
    
    private void cancelNotification() {
        if (this.mNotificationManager != null) {
            this.mNotificationManager.cancelNotification(this.getService(), this.getMainHandler());
        }
    }
    
    private void doDial() {
        execute(new WhistleVoipAgent$3(this));
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
    
    private void doTerminate(final int n) {
        execute(new WhistleVoipAgent$4(this, n));
    }
    
    private static void execute(final Runnable runnable) {
        WhistleVoipAgent.sThreadFactory.newThread(runnable).start();
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
    
    private UserLocale getAppLocale() {
        if (this.getUserAgent() != null && ((ServiceAgent)this.getUserAgent()).isReady()) {
            return this.getUserAgent().getCurrentAppLocale();
        }
        return UserLocaleRepository.getDeviceLocale();
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
        whistleEngineConfig.setEchoCanceler(true);
        whistleEngineConfig.setConnectivityThresholds(this.getWhistleEngineThresholds());
        this.getConfigurationAgent();
        final int voipSampleRateInHz = this.getVoipSampleRateInHz();
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Sets sample rate of " + voipSampleRateInHz + " Hz...");
        }
        whistleEngineConfig.setSamplerate(voipSampleRateInHz);
        Log.d("nf_voip", "SSL proxy server validation is enabled, set root certificate(s)...");
        try {
            final String rawString = FileUtils.readRawString(this.getContext(), 2131230722);
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
    
    private int getVoipSampleRateInHz() {
        final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        if (configurationAgent == null || configurationAgent.getVoipConfiguration() == null) {
            return 8000;
        }
        return configurationAgent.getVoipConfiguration().getSampleRateInHz();
    }
    
    private WhistleEngineThresholds getWhistleEngineThresholds() {
        WhistleEngineThresholds whistleEngineThresholds = null;
        final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        if (configurationAgent == null || configurationAgent.getVoipConfiguration() == null) {
            Log.w("nf_voip", "ConfigAgent null or VOIP config is null. Not expected!");
        }
        else {
            final VoipConfiguration voipConfiguration = configurationAgent.getVoipConfiguration();
            if (voipConfiguration.getJitterThresholdInMs() == null && voipConfiguration.getPacketLosThresholdInPercent() == null && voipConfiguration.getRttThresholdInMs() == null && voipConfiguration.getSipThresholdInMs() == null) {
                Log.d("nf_voip", "Thresholds not found, do not set them!");
                return null;
            }
            final WhistleEngineThresholds whistleEngineThresholds2 = new WhistleEngineThresholds();
            if (voipConfiguration.getJitterThresholdInMs() != null) {
                whistleEngineThresholds2.setJitterThreshold(voipConfiguration.getJitterThresholdInMs().getYellow(), voipConfiguration.getJitterThresholdInMs().getRed());
            }
            if (voipConfiguration.getSipThresholdInMs() != null) {
                whistleEngineThresholds2.setSIPThreshold(voipConfiguration.getSipThresholdInMs().getYellow(), voipConfiguration.getSipThresholdInMs().getRed());
            }
            if (voipConfiguration.getRttThresholdInMs() != null) {
                whistleEngineThresholds2.setRTTThreshold(voipConfiguration.getRttThresholdInMs().getYellow(), voipConfiguration.getRttThresholdInMs().getRed());
            }
            if (voipConfiguration.getPacketLosThresholdInPercent() != null) {
                whistleEngineThresholds2.setPacketLossThreshold(voipConfiguration.getPacketLosThresholdInPercent().getYellow(), voipConfiguration.getPacketLosThresholdInPercent().getRed());
            }
            whistleEngineThresholds = whistleEngineThresholds2;
            if (Log.isLoggable()) {
                Log.d("nf_voip", "Sets threshholds " + voipConfiguration);
                return whistleEngineThresholds2;
            }
        }
        return whistleEngineThresholds;
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
    
    private void onCallDisconnected(final int n) {
        if (this.mEngine != null) {
            if (this.mCurrentCall == null) {
                Log.w("nf_voip", "Call was NOT in progress and we received disconnect on line " + n);
            }
            else {
                if (this.mCurrentCall.line != n) {
                    Log.e("nf_voip", "Call is in progress on line " + this.mCurrentCall.line + " but we received disconnect on line " + n);
                    return;
                }
                final Iterator<IVoip$OutboundCallListener> iterator = this.mListeners.iterator();
                while (iterator.hasNext()) {
                    iterator.next().callDisconnected(this.mCurrentCall);
                }
            }
            return;
        }
        Log.e("nf_voip", "Engine is null and we received call disconnect! Should not happen!");
    }
    
    private void registerReceiver() {
        Log.d("nf_voip", "Registering VOIP receiver...");
        this.getContext().registerReceiver(this.mVoipReceiver, CallNotificationManager.getNotificationIntentFilter());
        Log.d("nf_voip", "Registered VOIP receiver");
    }
    
    private void releaseAudioFocus() {
        if (!this.mAudioFocusRequested.getAndSet(false)) {
            return;
        }
        Log.d("nf_voip", "We had audio focus, release it.");
        final AudioManager audioManager = (AudioManager)this.getContext().getSystemService("audio");
        if (audioManager == null) {
            return;
        }
        try {
            final int abandonAudioFocus = audioManager.abandonAudioFocus(this.mOnAudioFocusChangeListener);
            if (Log.isLoggable()) {
                Log.d("nf_voip", "Audio focus release is granted " + abandonAudioFocus);
            }
        }
        catch (Throwable t) {
            Log.e("nf_voip", "Failed to request audio focus release", t);
        }
    }
    
    private void requestAudioFocus() {
        if (this.mAudioFocusRequested.getAndSet(true)) {
            Log.w("nf_voip", "Already asked for audip focus...");
        }
        else {
            final AudioManager audioManager = (AudioManager)this.getContext().getSystemService("audio");
            if (audioManager != null) {
                try {
                    final int requestAudioFocus = audioManager.requestAudioFocus(this.mOnAudioFocusChangeListener, 0, 1);
                    if (Log.isLoggable()) {
                        Log.d("nf_voip", "Audio request is granted " + requestAudioFocus);
                    }
                }
                catch (Throwable t) {
                    Log.e("nf_voip", "Failed to request audio focus", t);
                }
            }
        }
    }
    
    private static void setUrgentAudioThreadPriority() {
        Process.setThreadPriority(10);
        Process.setThreadPriority(-19);
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
                    this.mEngine.start((WhistleEngineDelegate)this, configuration, this.getContext());
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
    
    public void authenticationNeeded(final boolean b) {
        if (Log.isLoggable()) {
            Log.e("nf_voip", "authenticationNeeded " + b);
        }
    }
    
    public void callConnected(final int n) {
        while (true) {
            while (true) {
                Label_0226: {
                    Label_0181: {
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
                                        break Label_0181;
                                    }
                                    final Iterator<IVoip$OutboundCallListener> iterator = this.mListeners.iterator();
                                    while (iterator.hasNext()) {
                                        iterator.next().callConnected(this.mCurrentCall);
                                    }
                                }
                                this.mConnectivityState = IVoip$ConnectivityState.GREEN;
                                this.mNotificationManager.updateConnectedNotification(this.getService(), this.getMainHandler());
                                CustomerServiceLogUtils.reportCallConnected(this.getContext(), CustomerServiceLogging$CallQuality.green);
                                Log.d("nf_voip", "Sets start time...");
                                this.mStartTime = System.currentTimeMillis();
                                return;
                            }
                            break Label_0226;
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
    
    public void callDisconnected(final int n) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("nf_voip", "Outbound call disconnected on line " + n);
            }
            this.onCallDisconnected(n);
            CustomerServiceLogUtils.reportCallSessionEnded(this.getContext(), CustomerServiceLogging$TerminationReason.canceledByNetflix, IClientLogging$CompletionReason.canceled, (Error)null);
            this.callCleanup();
        }
    }
    
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
        this.stopEngine();
        CustomerServiceLogUtils.reportCallSessionEnded(this.getContext(), CustomerServiceLogging$TerminationReason.canceledByNetflix, IClientLogging$CompletionReason.success, (Error)null);
        this.callCleanup();
    }
    // monitorexit(this)
    
    public void callFailed(final int p0, final String p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //     5: ifeq            54
        //     8: ldc             "nf_voip"
        //    10: new             Ljava/lang/StringBuilder;
        //    13: dup            
        //    14: invokespecial   java/lang/StringBuilder.<init>:()V
        //    17: ldc_w           "Outbound call failed on line "
        //    20: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    23: iload_1        
        //    24: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    27: ldc_w           ", error "
        //    30: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    33: aload_2        
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: ldc_w           ", sipCode "
        //    40: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    43: iload_3        
        //    44: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    47: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    50: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    53: pop            
        //    54: aload_0        
        //    55: invokevirtual   com/netflix/mediaclient/service/voip/WhistleVoipAgent.getService:()Lcom/netflix/mediaclient/service/NetflixService;
        //    58: invokevirtual   com/netflix/mediaclient/service/NetflixService.getErrorHandler:()Lcom/netflix/mediaclient/servicemgr/IErrorHandler;
        //    61: aload_0        
        //    62: invokevirtual   com/netflix/mediaclient/service/voip/WhistleVoipAgent.getContext:()Landroid/content/Context;
        //    65: aload_2        
        //    66: iload_3        
        //    67: invokestatic    com/netflix/mediaclient/service/voip/VoipErrorDialogDescriptorFactory.getHandlerForCallFailed:(Landroid/content/Context;Ljava/lang/String;I)Lcom/netflix/mediaclient/service/error/ErrorDescriptor;
        //    70: invokeinterface com/netflix/mediaclient/servicemgr/IErrorHandler.addError:(Lcom/netflix/mediaclient/service/error/ErrorDescriptor;)Z
        //    75: pop            
        //    76: aload_0        
        //    77: getfield        com/netflix/mediaclient/service/voip/WhistleVoipAgent.mEngine:Lcom/vailsys/whistleengine/WhistleEngine;
        //    80: ifnull          372
        //    83: aload_0        
        //    84: getfield        com/netflix/mediaclient/service/voip/WhistleVoipAgent.mCurrentCall:Lcom/netflix/mediaclient/service/voip/WhistleVoipAgent$WhistleCall;
        //    87: ifnonnull       266
        //    90: ldc             "nf_voip"
        //    92: new             Ljava/lang/StringBuilder;
        //    95: dup            
        //    96: invokespecial   java/lang/StringBuilder.<init>:()V
        //    99: ldc_w           "Call was NOT in progress and we received call failed on line "
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: iload_1        
        //   106: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   109: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   112: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   115: pop            
        //   116: aload_0        
        //   117: getfield        com/netflix/mediaclient/service/voip/WhistleVoipAgent.mConnectivityState:Lcom/netflix/mediaclient/servicemgr/IVoip$ConnectivityState;
        //   120: getstatic       com/netflix/mediaclient/servicemgr/IVoip$ConnectivityState.NO_CONNECTION:Lcom/netflix/mediaclient/servicemgr/IVoip$ConnectivityState;
        //   123: if_acmpeq       384
        //   126: getstatic       com/netflix/mediaclient/servicemgr/CustomerServiceLogging$TerminationReason.failedAfterConnected:Lcom/netflix/mediaclient/servicemgr/CustomerServiceLogging$TerminationReason;
        //   129: astore          4
        //   131: new             Ljava/util/ArrayList;
        //   134: dup            
        //   135: invokespecial   java/util/ArrayList.<init>:()V
        //   138: astore          5
        //   140: new             Lcom/netflix/mediaclient/service/logging/client/model/DeepErrorElement;
        //   143: dup            
        //   144: invokespecial   com/netflix/mediaclient/service/logging/client/model/DeepErrorElement.<init>:()V
        //   147: astore          6
        //   149: aload           5
        //   151: aload           6
        //   153: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   158: pop            
        //   159: aload           6
        //   161: iconst_1       
        //   162: invokevirtual   com/netflix/mediaclient/service/logging/client/model/DeepErrorElement.setFatal:(Z)V
        //   165: aload           6
        //   167: iload_3        
        //   168: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //   171: invokevirtual   com/netflix/mediaclient/service/logging/client/model/DeepErrorElement.setErrorCode:(Ljava/lang/String;)V
        //   174: new             Lcom/netflix/mediaclient/service/logging/client/model/DeepErrorElement$Debug;
        //   177: dup            
        //   178: invokespecial   com/netflix/mediaclient/service/logging/client/model/DeepErrorElement$Debug.<init>:()V
        //   181: astore          7
        //   183: new             Lorg/json/JSONObject;
        //   186: dup            
        //   187: invokespecial   org/json/JSONObject.<init>:()V
        //   190: astore          8
        //   192: aload           8
        //   194: ldc_w           "sipCode"
        //   197: iload_3        
        //   198: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   201: pop            
        //   202: aload_2        
        //   203: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //   206: ifeq            219
        //   209: aload           8
        //   211: ldc_w           "reason"
        //   214: aload_2        
        //   215: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   218: pop            
        //   219: aload           7
        //   221: aload           8
        //   223: invokevirtual   com/netflix/mediaclient/service/logging/client/model/DeepErrorElement$Debug.setMessage:(Lorg/json/JSONObject;)V
        //   226: aload           6
        //   228: aload           7
        //   230: invokevirtual   com/netflix/mediaclient/service/logging/client/model/DeepErrorElement.setDebug:(Lcom/netflix/mediaclient/service/logging/client/model/DeepErrorElement$Debug;)V
        //   233: new             Lcom/netflix/mediaclient/service/logging/client/model/Error;
        //   236: dup            
        //   237: getstatic       com/netflix/mediaclient/service/logging/client/model/RootCause.clientFailure:Lcom/netflix/mediaclient/service/logging/client/model/RootCause;
        //   240: aload           5
        //   242: invokespecial   com/netflix/mediaclient/service/logging/client/model/Error.<init>:(Lcom/netflix/mediaclient/service/logging/client/model/RootCause;Ljava/util/List;)V
        //   245: astore_2       
        //   246: aload_0        
        //   247: invokevirtual   com/netflix/mediaclient/service/voip/WhistleVoipAgent.getContext:()Landroid/content/Context;
        //   250: aload           4
        //   252: getstatic       com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.failed:Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //   255: aload_2        
        //   256: invokestatic    com/netflix/mediaclient/util/log/CustomerServiceLogUtils.reportCallSessionEnded:(Landroid/content/Context;Lcom/netflix/mediaclient/servicemgr/CustomerServiceLogging$TerminationReason;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/Error;)V
        //   259: aload_0        
        //   260: invokespecial   com/netflix/mediaclient/service/voip/WhistleVoipAgent.callCleanup:()V
        //   263: aload_0        
        //   264: monitorexit    
        //   265: return         
        //   266: aload_0        
        //   267: getfield        com/netflix/mediaclient/service/voip/WhistleVoipAgent.mCurrentCall:Lcom/netflix/mediaclient/service/voip/WhistleVoipAgent$WhistleCall;
        //   270: invokestatic    com/netflix/mediaclient/service/voip/WhistleVoipAgent$WhistleCall.access$400:(Lcom/netflix/mediaclient/service/voip/WhistleVoipAgent$WhistleCall;)I
        //   273: iload_1        
        //   274: if_icmpne       327
        //   277: aload_0        
        //   278: getfield        com/netflix/mediaclient/service/voip/WhistleVoipAgent.mListeners:Ljava/util/List;
        //   281: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   286: astore          4
        //   288: aload           4
        //   290: invokeinterface java/util/Iterator.hasNext:()Z
        //   295: ifeq            116
        //   298: aload           4
        //   300: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   305: checkcast       Lcom/netflix/mediaclient/servicemgr/IVoip$OutboundCallListener;
        //   308: aload_0        
        //   309: getfield        com/netflix/mediaclient/service/voip/WhistleVoipAgent.mCurrentCall:Lcom/netflix/mediaclient/service/voip/WhistleVoipAgent$WhistleCall;
        //   312: aload_2        
        //   313: iload_3        
        //   314: invokeinterface com/netflix/mediaclient/servicemgr/IVoip$OutboundCallListener.callFailed:(Lcom/netflix/mediaclient/servicemgr/IVoip$Call;Ljava/lang/String;I)V
        //   319: goto            288
        //   322: astore_2       
        //   323: aload_0        
        //   324: monitorexit    
        //   325: aload_2        
        //   326: athrow         
        //   327: ldc             "nf_voip"
        //   329: new             Ljava/lang/StringBuilder;
        //   332: dup            
        //   333: invokespecial   java/lang/StringBuilder.<init>:()V
        //   336: ldc_w           "Call is in progress on line "
        //   339: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   342: aload_0        
        //   343: getfield        com/netflix/mediaclient/service/voip/WhistleVoipAgent.mCurrentCall:Lcom/netflix/mediaclient/service/voip/WhistleVoipAgent$WhistleCall;
        //   346: invokestatic    com/netflix/mediaclient/service/voip/WhistleVoipAgent$WhistleCall.access$400:(Lcom/netflix/mediaclient/service/voip/WhistleVoipAgent$WhistleCall;)I
        //   349: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   352: ldc_w           " but we received call failed on line "
        //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: iload_1        
        //   359: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   362: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   365: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   368: pop            
        //   369: goto            263
        //   372: ldc             "nf_voip"
        //   374: ldc_w           "Engine is null and we received call failed! Should not happen!"
        //   377: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   380: pop            
        //   381: goto            116
        //   384: getstatic       com/netflix/mediaclient/servicemgr/CustomerServiceLogging$TerminationReason.failedBeforeConnected:Lcom/netflix/mediaclient/servicemgr/CustomerServiceLogging$TerminationReason;
        //   387: astore          4
        //   389: goto            131
        //   392: astore_2       
        //   393: goto            226
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  2      54     322    327    Any
        //  54     116    322    327    Any
        //  116    131    322    327    Any
        //  131    183    322    327    Any
        //  183    219    392    396    Lorg/json/JSONException;
        //  183    219    322    327    Any
        //  219    226    392    396    Lorg/json/JSONException;
        //  219    226    322    327    Any
        //  226    263    322    327    Any
        //  266    288    322    327    Any
        //  288    319    322    327    Any
        //  327    369    322    327    Any
        //  372    381    322    327    Any
        //  384    389    322    327    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0219:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
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
    
    public void callSecured(final int n, final boolean b) {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Not supported:::callSecured for line " + n + ", ? " + b);
        }
    }
    
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
            Label_0084: {
                synchronized (this) {
                    if (this.mDialRequested.get()) {
                        Log.d("nf_voip", "Request for dial is already in progress!");
                    }
                    else {
                        this.mSharedSessionId = ConsolidatedLoggingUtils.createGUID();
                        CustomerServiceLogUtils.reportCallSessionStarted(this.getContext(), this.mSharedSessionId, true);
                        this.mDialRequested.set(true);
                        this.start();
                        if (this.mReady.get()) {
                            break Label_0084;
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
        this.registerReceiver();
        this.initCompleted(CommonStatus.OK);
    }
    
    public void engineNotReady() {
        Log.w("nf_voip", "Engine is NOT ready!");
        this.mEngineReady.set(false);
    }
    
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
        return this.mConnectivityState != IVoip$ConnectivityState.NO_CONNECTION && this.isEnabled();
    }
    
    @Override
    public boolean isEnabled() {
        final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        return configurationAgent == null || !((ServiceAgent)configurationAgent).isReady() || !this.getConfigurationAgent().shouldDisableVoip();
    }
    
    @Override
    public boolean isReady() {
        synchronized (this) {
            return this.mReady.get() && this.mEngine != null;
        }
    }
    
    public void networkFailure(final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Network failure for line " + n);
        }
        this.getService().getErrorHandler().addError(VoipErrorDialogDescriptorFactory.getHandlerForCallFailed(this.getContext(), this.cancelAction));
        while (true) {
            while (true) {
                Label_0317: {
                    if (this.mEngine == null) {
                        break Label_0317;
                    }
                    if (this.mCurrentCall == null) {
                        Log.w("nf_voip", "Call was NOT in progress and we received network failure on line " + n);
                        break Label_0317;
                    }
                    Label_0223: {
                        break Label_0223;
                    Label_0187_Outer:
                        while (true) {
                            Object o = new ArrayList<DeepErrorElement>();
                            final DeepErrorElement deepErrorElement = new DeepErrorElement();
                            ((List<DeepErrorElement>)o).add(deepErrorElement);
                            deepErrorElement.setFatal(true);
                            deepErrorElement.setErrorCode("networkFailed");
                            final DeepErrorElement$Debug debug = new DeepErrorElement$Debug();
                            while (true) {
                                try {
                                    final JSONObject message = new JSONObject();
                                    message.put("reason", (Object)"networkFailed");
                                    debug.setMessage(message);
                                    deepErrorElement.setDebug(debug);
                                    o = new Error(RootCause.networkFailure, (List<DeepErrorElement>)o);
                                    Object o2 = null;
                                    CustomerServiceLogUtils.reportCallSessionEnded(this.getContext(), (CustomerServiceLogging$TerminationReason)o2, IClientLogging$CompletionReason.failed, (Error)o);
                                    this.callCleanup();
                                    return;
                                    while (true) {
                                        ((Iterator<IVoip$OutboundCallListener>)o2).next().networkFailed(this.mCurrentCall);
                                        Label_0244: {
                                            break Label_0244;
                                            Label_0274: {
                                                Log.e("nf_voip", "Call is in progress on line " + this.mCurrentCall.line + " but we received network failed on line " + n);
                                            }
                                            return;
                                            Log.e("nf_voip", "Engine is null and we received network failed! Should not happen!");
                                            break;
                                            o2 = CustomerServiceLogging$TerminationReason.failedBeforeConnected;
                                            continue Label_0187_Outer;
                                            o2 = this.mListeners.iterator();
                                        }
                                        continue;
                                    }
                                }
                                // iftrue(Label_0274:, WhistleVoipAgent$WhistleCall.access$400(this.mCurrentCall) != n)
                                // iftrue(Label_0096:, !o2.hasNext())
                                catch (JSONException ex) {
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                }
                if (this.mConnectivityState != IVoip$ConnectivityState.NO_CONNECTION) {
                    final Object o2 = CustomerServiceLogging$TerminationReason.failedAfterConnected;
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    @Override
    public boolean refreshAuthorizationTokens() {
        return this.mAuthorizationTokensManager.refreshAuthorizationTokens();
    }
    
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
    public void setSpeakerOn(final boolean speakerOn) {
        this.mLockManager.setSpeakerOn(speakerOn);
    }
    
    @Override
    public boolean start() {
        final boolean enabled = this.isEnabled();
        final boolean serviceStartedOrStarting = this.isServiceStartedOrStarting();
        if (enabled && !serviceStartedOrStarting) {
            Log.d("nf_voip", "VOIP service is enabled and it is not ready, start it.");
            this.mServiceState = WhistleVoipAgent$ServiceState.STARTING;
            this.getApplication().bindService(this.getServiceIntent(), this.mConnection, 1);
        }
        else {
            if (enabled) {
                Log.d("nf_voip", "VOIP service is NOT enabled or it started or starting, no need to start it.");
                return true;
            }
            if (Log.isLoggable()) {
                Log.w("nf_voip", "We should NOT be here: VOIP service is enabled " + enabled + " or it started or starting " + serviceStartedOrStarting + ", state: " + this.mServiceState);
                return false;
            }
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
        if (this.mReady.get() && this.isServiceStoppedOrStopping() && this.mConnection != null) {
            Log.d("nf_voip", "Stop VOIP service");
            this.mServiceState = WhistleVoipAgent$ServiceState.STOPPING;
            this.getService().unbindService(this.mConnection);
        }
        else if (Log.isLoggable()) {
            Log.w("nf_voip", "VOIP service is enabled " + this.isEnabled() + ", ready " + this.mReady.get() + ", connection is null " + (this.mConnection == null) + ", skip stop!");
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
            CustomerServiceLogging$TerminationReason customerServiceLogging$TerminationReason;
            if (this.mConnectivityState != IVoip$ConnectivityState.NO_CONNECTION) {
                customerServiceLogging$TerminationReason = CustomerServiceLogging$TerminationReason.canceledByUserAfterConnected;
            }
            else {
                customerServiceLogging$TerminationReason = CustomerServiceLogging$TerminationReason.canceledByUserBeforeConnected;
            }
            CustomerServiceLogUtils.reportCallSessionEnded(this.getContext(), customerServiceLogging$TerminationReason, IClientLogging$CompletionReason.canceled, (Error)null);
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
                    this.doTerminate(this.mCurrentCall.getId());
                }
                this.callCleanup();
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
