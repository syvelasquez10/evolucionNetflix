// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import java.util.Map;
import java.util.HashMap;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerGetCurrentState;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerGetCapabilities;
import com.netflix.mediaclient.service.mdx.message.controller.Handshake;
import com.netflix.mediaclient.service.mdx.message.target.PlayerState;
import com.netflix.mediaclient.service.mdx.message.target.PinNotRequired;
import com.netflix.mediaclient.service.mdx.message.target.PinRequired;
import com.netflix.mediaclient.service.mdx.message.target.PlayerStateChanged;
import com.netflix.mediaclient.service.mdx.message.target.PlayerCurrentState;
import org.json.JSONException;
import com.netflix.mediaclient.service.mdx.message.target.HandshakeAccepted;
import org.json.JSONObject;
import java.security.InvalidParameterException;
import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;
import android.os.Message;
import android.os.Looper;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;

public class TargetContext implements TargetStateManagerListener
{
    private static final long DUPLICATE_MESSAGE_REQUEST_WINDOWS = 2000L;
    private static final int MSG_COMMAND = 2;
    private static final int MSG_EVENT = 1;
    private static final int MSG_PERIODIC = 4;
    private static final int MSG_UPDATETARGET = 3;
    private static final long STATE_CHECK_PERIOD = 25000L;
    private static final String TAG = "nf_mdx";
    private boolean mActivated;
    private String mAudioSubtitleSettingBlob;
    private final Object mAudioSubtitleSettingBlobLock;
    private final MdxController mController;
    private String mDialUsn;
    private String mDialUuid;
    private String mFriendlyName;
    private String mLastError;
    private int mLaunchStatus;
    private String mLocation;
    private final NotifierInterface mNotifier;
    private String mPairingContext;
    private final PlayerStateManager mPlayerStateManager;
    private String mPostplayStateBlob;
    private long mPreviousAudioSubExchangeTime;
    private int mRegistrationAcceptance;
    private String mServiceType;
    private int mSessionId;
    private TargetStateManager mStateMachine;
    private String mTargetCapability;
    private Handler mTargetContextHandler;
    private HandlerThread mTargetContextThread;
    private String mTargetPlaybackSessionToken;
    private String mUsn;
    private String mUuid;
    
    TargetContext(final MdxController mController, final NotifierInterface mNotifier, final RemoteDevice remoteDevice, final boolean b) {
        this.mPlayerStateManager = new PlayerStateManager();
        this.mAudioSubtitleSettingBlobLock = new Object();
        this.mPreviousAudioSubExchangeTime = 0L;
        if (Log.isLoggable("nf_mdx", 3)) {
            Log.d("nf_mdx", "TargetContext: for device " + remoteDevice);
        }
        this.updateTargetProperty(remoteDevice);
        this.mSessionId = -1;
        this.mController = mController;
        this.mNotifier = mNotifier;
        final boolean notEmpty = StringUtils.isNotEmpty(this.mPairingContext);
        (this.mTargetContextThread = new HandlerThread("MDX TargetContext")).start();
        this.mTargetContextHandler = new Handler(this.mTargetContextThread.getLooper()) {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    default: {}
                    case 2: {
                        if (Log.isLoggable("nf_mdx", 3)) {
                            Log.d("nf_mdx", "TargetContext: received a command at state " + TargetContext.this.mStateMachine.mCurrentState.getName());
                        }
                        TargetContext.this.mStateMachine.addUiCommand((Runnable)message.obj);
                        TargetContext.this.mStateMachine.receivedEvent(TargetContextEvent.SessionCommandReceived);
                    }
                    case 1: {
                        TargetContext.this.mStateMachine.receivedEvent((TargetContextEvent)message.obj);
                    }
                    case 3: {
                        final boolean notEmpty = StringUtils.isNotEmpty(TargetContext.this.mPairingContext);
                        Log.d("nf_mdx", "TargetContext: MSG_UPDATETARGET " + notEmpty + ", " + TargetContext.this.mRegistrationAcceptance + ", " + TargetContext.this.mActivated + ", " + TargetContext.this.mLaunchStatus);
                        TargetContext.this.mStateMachine.updateTarget(notEmpty, TargetContext.this.mRegistrationAcceptance, TargetContext.this.mActivated, TargetContext.this.mLaunchStatus);
                        TargetContext.this.mStateMachine.receivedEvent(TargetContextEvent.TargetUpdate);
                    }
                    case 4: {
                        if (TargetContext.this.mStateMachine.isSessionActive()) {
                            TargetContext.this.requestStateCheck();
                            return;
                        }
                        Log.d("nf_mdx", "TargetContext: MSG_PERIODIC,target is not active");
                    }
                }
            }
        };
        if (this.mLaunchStatus == 0) {
            this.mStateMachine = new TargetStateManager((TargetStateManager.TargetStateManagerListener)this, TargetState.StateNotLaunched, b);
        }
        else {
            this.mStateMachine = new TargetStateManager((TargetStateManager.TargetStateManagerListener)this, TargetState.StateLaunched, b);
        }
        this.mStateMachine.setDefaultAction(StateId.StateNeedLaunched, new LaunchNetflix());
        this.mStateMachine.setDefaultAction(StateId.StateNoPair, new DoPair());
        this.mStateMachine.setDefaultAction(StateId.StateBadPair, new DeletePair());
        this.mStateMachine.setDefaultAction(StateId.StateNeedRegPair, new DoRegPair());
        this.mStateMachine.setDefaultAction(StateId.StateHasPair, new StartSession());
        this.mStateMachine.setDefaultAction(StateId.StateNeedHandShake, new DoHandShake());
        this.mStateMachine.addSessionRequest(new GetCapabilies());
        this.mStateMachine.addSessionRequest(new GetState());
        this.mStateMachine.start(notEmpty, this.mRegistrationAcceptance, this.mActivated, this.mLaunchStatus);
    }
    
    private int determineStateErrorCode(final TargetState targetState, final String s) {
        final int n = 300;
        int n2;
        if (TargetState.StateNoPair.equals(targetState) || TargetState.StateNoPairNeedRegPair.equals(targetState)) {
            n2 = 104;
        }
        else {
            if (TargetState.StateHasPair.equals(targetState) || TargetState.StateNeedHandShake.equals(targetState)) {
                return 105;
            }
            if (TargetState.StateSendingMessage.equals(targetState)) {
                n2 = n;
                if (StringUtils.isNotEmpty(s)) {
                    n2 = n;
                    if (MdxMessage.isUserCommand(s)) {
                        return 105;
                    }
                }
            }
            else {
                n2 = n;
                if (TargetState.StateNeedLaunched.equals(targetState)) {
                    return 100;
                }
            }
        }
        return n2;
    }
    
    private String getTargetProperty(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = new String();
        }
        return s2;
    }
    
    private void handlePostplayState(final String s, final String mPostplayStateBlob) {
        if (StringUtils.isNotEmpty(mPostplayStateBlob) && !mPostplayStateBlob.equals(this.mPostplayStateBlob)) {
            this.mNotifier.postplayState(s, mPostplayStateBlob);
        }
        this.mPostplayStateBlob = mPostplayStateBlob;
    }
    
    private void invalidateCachedLanguageData() {
        synchronized (this.mAudioSubtitleSettingBlobLock) {
            this.mAudioSubtitleSettingBlob = null;
            this.mPreviousAudioSubExchangeTime = 0L;
        }
    }
    
    private void playbackEnd(final String s, final String s2) {
        Log.d("nf_mdx", "TargetContext: playbackEnd");
        this.mNotifier.playbackEnd(s, s2);
    }
    
    private void playbackStart(final String s) {
        Log.d("nf_mdx", "TargetContext: playbackStart");
        this.invalidateCachedLanguageData();
        this.mNotifier.playbackStart(s);
    }
    
    private void requestStateCheck() {
        this.mStateMachine.addSessionRequest(new GetState());
        this.mStateMachine.receivedEvent(TargetContextEvent.SessionCommandReceived);
    }
    
    private void scheduleStateCheck() {
        this.mTargetContextHandler.removeMessages(4);
        final Message message = new Message();
        message.what = 4;
        this.mTargetContextHandler.sendMessageDelayed(message, 25000L);
    }
    
    public void destroy() {
        if (this.mTargetContextHandler != null) {
            this.mTargetContextHandler.removeMessages(2);
            this.mTargetContextHandler.removeMessages(1);
            this.mTargetContextHandler = null;
        }
        if (this.mTargetContextThread == null) {
            return;
        }
        this.mTargetContextThread.quit();
        while (true) {
            try {
                this.mTargetContextThread.join();
                this.mTargetContextThread = null;
            }
            catch (InterruptedException ex) {
                Log.e("nf_mdx", "mTargetContextThread interrupted");
                continue;
            }
            break;
        }
    }
    
    String getTargetPlaybackSessionToken() {
        return this.mTargetPlaybackSessionToken;
    }
    
    public WebApiUtils.VideoIds getVideoIds() {
        final PlayerStateManager mPlayerStateManager = this.mPlayerStateManager;
        // monitorenter(mPlayerStateManager)
        String catalogId;
        String episodeId;
        try {
            catalogId = this.mPlayerStateManager.getCatalogId();
            episodeId = this.mPlayerStateManager.getEpisodeId();
            // monitorexit(mPlayerStateManager)
            final String s = catalogId;
            final String s2 = episodeId;
            final WebApiUtils.VideoIds isd = WebApiUtils.extractIsd(s, s2);
            return isd;
        }
        finally {
            final String s3;
            catalogId = s3;
        }
        // monitorexit(mPlayerStateManager)
        try {
            final String s = catalogId;
            final String s2 = episodeId;
            final WebApiUtils.VideoIds isd2;
            final WebApiUtils.VideoIds isd = isd2 = WebApiUtils.extractIsd(s, s2);
            return isd2;
        }
        catch (InvalidParameterException ex) {
            Log.d("nf_mdx", "TargetContext: fail to getVideoIds " + catalogId + ", " + episodeId);
            return null;
        }
    }
    
    public boolean hasActiveSession() {
        synchronized (this.mPlayerStateManager) {
            final String targetPlayerState = this.mPlayerStateManager.getTargetPlayerState();
            // monitorexit(this.mPlayerStateManager)
            if (StringUtils.isNotEmpty(targetPlayerState) && !"STOP".equals(targetPlayerState) && !"FATAL_ERROR".equals(targetPlayerState) && !"END_PLAYBACK".equals(targetPlayerState)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isThisSession(final int n) {
        return n == this.mSessionId;
    }
    
    public boolean isThisTargetPairingContext(final String s) {
        return StringUtils.isNotEmpty(s) && this.mPairingContext.equals(s);
    }
    
    public boolean isThisTargetUuid(final String s) {
        return (StringUtils.isNotEmpty(this.mUuid) && this.mUuid.equals(s)) || (StringUtils.isNotEmpty(this.mDialUuid) && this.mDialUuid.equals(s));
    }
    
    public void launchResult(final boolean b) {
        final Message message = new Message();
        message.what = 1;
        if (b) {
            message.obj = TargetContextEvent.LaunchSucceed;
        }
        else {
            message.obj = TargetContextEvent.LaunchFailed;
        }
        this.mTargetContextHandler.sendMessage(message);
        Log.d("nf_mdx", "TargetContext: send launchResult");
    }
    
    public void messageDelivered(final int n) {
        final Message message = new Message();
        message.what = 1;
        message.obj = TargetContextEvent.SendMessageSucceed;
        this.mTargetContextHandler.sendMessage(message);
    }
    
    public void messageError(final int n, final String s, final String s2) {
        this.mLastError = " message error " + s + ": " + s2;
        final Message message = new Message();
        message.what = 1;
        if ("6".equals(s) || "5".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_SESSION_INVALID_HMAC");
            message.obj = TargetContextEvent.SendMessageFailedNeedRepair;
        }
        else if ("11".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_SESSION_INVALID_SID");
            message.obj = TargetContextEvent.SendMessageFailedNeedNewSession;
        }
        else if ("4".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_SESSION_INVALID_NONCE");
            message.obj = TargetContextEvent.SendMessageFailed;
        }
        else if ("10".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_SESSION_NETWORK_ERROR");
            message.obj = TargetContextEvent.SendMessageFailed;
        }
        else {
            Log.d("nf_mdx", "TargetContext:  SESSION unknown error");
            message.obj = TargetContextEvent.SendMessageFailedNeedRepair;
        }
        this.mTargetContextHandler.sendMessage(message);
    }
    
    public void messageReceived(int videoId, String s, final JSONObject jsonObject) {
        if (videoId != this.mSessionId) {
            Log.e("nf_mdx", "messageReceived doesn't match sessionId: " + s);
        }
        else {
            String s2 = null;
            Label_0113: {
                if (!StringUtils.isNotEmpty(this.mDialUuid)) {
                    break Label_0113;
                }
                s2 = this.mDialUuid;
                while (true) {
                    this.scheduleStateCheck();
                    if (!"HANDSHAKE_ACCEPTED".equals(s)) {
                        break Label_0113;
                    }
                    try {
                        if (new HandshakeAccepted(jsonObject).isAccepted()) {
                            s = (String)new Message();
                            ((Message)s).what = 1;
                            ((Message)s).obj = TargetContextEvent.HandShakeSucceed;
                            this.mTargetContextHandler.sendMessage((Message)s);
                            return;
                        }
                        return;
                        s2 = this.mUuid;
                        continue;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_mdx", "messageReceived failed to parse " + s);
                        return;
                    }
                    break;
                }
            }
            if ("PLAYER_CURRENT_STATE".equals(s)) {
                PlayerState playerState;
                try {
                    playerState = new PlayerCurrentState(jsonObject).getPlayerState();
                    if (playerState != null) {
                        this.mTargetPlaybackSessionToken = playerState.getXid();
                        s = (String)this.mPlayerStateManager;
                        // monitorenter(s)
                        final TargetContext targetContext = this;
                        final PlayerStateManager playerStateManager = targetContext.mPlayerStateManager;
                        final PlayerState playerState2 = playerState;
                        playerStateManager.updateState(playerState2);
                        final String s3 = s;
                        // monitorexit(s3)
                        final TargetContext targetContext2 = this;
                        final String s4 = s2;
                        final PlayerState playerState3 = playerState;
                        final String s5 = playerState3.getPostplayState();
                        targetContext2.handlePostplayState(s4, s5);
                    }
                    return;
                }
                catch (JSONException ex2) {
                    Log.e("nf_mdx", "messageReceived failed to parse " + s);
                    return;
                }
                try {
                    final TargetContext targetContext = this;
                    final PlayerStateManager playerStateManager = targetContext.mPlayerStateManager;
                    final PlayerState playerState2 = playerState;
                    playerStateManager.updateState(playerState2);
                    final String s3 = s;
                    // monitorexit(s3)
                    final TargetContext targetContext2 = this;
                    final String s4 = s2;
                    final PlayerState playerState3 = playerState;
                    final String s5 = playerState3.getPostplayState();
                    targetContext2.handlePostplayState(s4, s5);
                    return;
                }
                finally {
                }
                // monitorexit(s)
            }
            if ("PLAYER_STATE_CHANGED".equals(s)) {
                PlayerState playerState4;
                try {
                    playerState4 = new PlayerStateChanged(jsonObject).getPlayerState();
                    if (playerState4 != null) {
                        this.mTargetPlaybackSessionToken = playerState4.getXid();
                        s = (String)this.mPlayerStateManager;
                        // monitorenter(s)
                        final TargetContext targetContext3 = this;
                        final PlayerStateManager playerStateManager2 = targetContext3.mPlayerStateManager;
                        final PlayerState playerState5 = playerState4;
                        playerStateManager2.changeState(playerState5);
                        final String s6 = s;
                        // monitorexit(s6)
                        final TargetContext targetContext4 = this;
                        final String s7 = s2;
                        final PlayerState playerState6 = playerState4;
                        final String s8 = playerState6.getPostplayState();
                        targetContext4.handlePostplayState(s7, s8);
                    }
                    return;
                }
                catch (JSONException ex3) {
                    Log.e("nf_mdx", "messageReceived failed to parse " + s);
                    return;
                }
                try {
                    final TargetContext targetContext3 = this;
                    final PlayerStateManager playerStateManager2 = targetContext3.mPlayerStateManager;
                    final PlayerState playerState5 = playerState4;
                    playerStateManager2.changeState(playerState5);
                    final String s6 = s;
                    // monitorexit(s6)
                    final TargetContext targetContext4 = this;
                    final String s7 = s2;
                    final PlayerState playerState6 = playerState4;
                    final String s8 = playerState6.getPostplayState();
                    targetContext4.handlePostplayState(s7, s8);
                    return;
                }
                finally {
                }
                // monitorexit(s)
            }
            if ("PLAYER_CAPABILITIES".equals(s)) {
                this.mTargetCapability = jsonObject.toString();
                this.mNotifier.capability(s2, this.mTargetCapability);
                return;
            }
            if ("AUDIO_SUBTITLES_CHANGED".equals(s) || "AUDIO_SUBTITLES_SETTINGS".equals(s)) {
                synchronized (this.mAudioSubtitleSettingBlobLock) {
                    this.mPreviousAudioSubExchangeTime = System.currentTimeMillis();
                    this.mAudioSubtitleSettingBlob = jsonObject.toString();
                    this.mNotifier.audiosub(s2, this.mAudioSubtitleSettingBlob);
                    return;
                }
            }
            if ("DIALOG_SHOW".equals(s)) {
                this.mNotifier.dialogShow(s2, jsonObject.toString());
                return;
            }
            if ("DIALOG_CANCEL".equals(s)) {
                this.mNotifier.dialogCancel(s2, jsonObject.toString());
                return;
            }
            if ("META_DATA_CHANGED".equals(s)) {
                this.mNotifier.metaData(s2, jsonObject.toString());
                return;
            }
            if ("PIN_VERIFICATION_SHOW".equals(s)) {
                try {
                    final PinRequired pinRequired = new PinRequired(jsonObject);
                    final String title = pinRequired.getTitle();
                    videoId = pinRequired.getVideoId();
                    this.mNotifier.requestPinVerification(s2, title, videoId, pinRequired.getAncestorVideoId(), pinRequired.getAncestorVideoType());
                    return;
                }
                catch (JSONException ex4) {
                    Log.e("nf_mdx", "messageReceived failed to parse " + s);
                    return;
                }
            }
            if ("PIN_VERIFICATION_NOT_REQUIRED".equals(s)) {
                try {
                    this.mNotifier.abortPinVerification(s2, new PinNotRequired(jsonObject).getIsPinVerified());
                    return;
                }
                catch (JSONException ex5) {
                    Log.e("nf_mdx", "messageReceived failed to parse " + s);
                    return;
                }
            }
            Log.d("nf_mdx", "messageReceived not handle ");
        }
    }
    
    public void pairingDeleted() {
        final Message message = new Message();
        message.what = 1;
        message.obj = TargetContextEvent.DeletePairSucceed;
        this.mTargetContextHandler.sendMessage(message);
    }
    
    public void pairingFail(final String s, final String s2) {
        this.mLastError = " pairing error " + s + ": " + s2;
        final Message message = new Message();
        message.what = 1;
        if ("30".equals(s) || "USER_MISMATCH".equals(s) || "20".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_PAIRING_USER_MISMATCH");
            message.obj = TargetContextEvent.PairFailedNeedRegPair;
        }
        else if ("31".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_PAIRING_ALREADY_PAIRED");
            message.obj = TargetContextEvent.PairFailedExistedPair;
        }
        else if ("22".equals(s) || "21".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  PAIRING CTICKET error ");
            this.mController.pingNccp();
            message.obj = TargetContextEvent.PairFailed;
        }
        else if ("99".equals(s) | "13".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  PAIRING NETWORK error ");
            message.obj = TargetContextEvent.PairFailed;
        }
        else if ("11".equals(s) || "12".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  PAIRING SERVER/TARGET error ");
            message.obj = TargetContextEvent.PairFailed;
        }
        else if ("0".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  PAIRING NOERROR error ");
            message.obj = TargetContextEvent.PairSucceed;
        }
        else if ("42".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_REGISTRATION_PAIRING_IN_PROGRESS error ");
            message.obj = TargetContextEvent.RegistrationInProgress;
        }
        else {
            Log.d("nf_mdx", "TargetContext:  PAIRING unknown error ");
            message.obj = TargetContextEvent.PairFailedNeedRegPair;
        }
        this.mTargetContextHandler.sendMessage(message);
    }
    
    public void pairingSucceed(final String mPairingContext) {
        final Message message = new Message();
        message.what = 1;
        message.obj = TargetContextEvent.PairSucceed;
        synchronized (this.mPairingContext) {
            // monitorexit(this.mPairingContext = mPairingContext)
            this.mTargetContextHandler.sendMessage(message);
        }
    }
    
    @Override
    public void removeEvents(final TargetContextEvent targetContextEvent) {
        this.mTargetContextHandler.removeMessages(1, (Object)targetContextEvent);
    }
    
    @Override
    public void scheduleEvent(final TargetContextEvent obj, final int n) {
        final Message message = new Message();
        message.what = 1;
        message.obj = obj;
        if (n > 0) {
            this.mTargetContextHandler.sendMessageDelayed(message, (long)n);
            return;
        }
        this.mTargetContextHandler.sendMessage(message);
    }
    
    public void sendCommand(String s, final JSONObject jsonObject) {
        if (Log.isLoggable("nf_mdx", 3)) {
            Log.d("nf_mdx", "TargetContext: sendCommand: " + s + ", msgObj: " + jsonObject);
        }
        Label_0234: {
            if ("PLAYER_GET_CAPABILITIES".equals(s)) {
                if (!StringUtils.isNotEmpty(this.mTargetCapability)) {
                    break Label_0234;
                }
                Log.d("nf_mdx", "TargetContext: reutrn cached CAPABILITY");
                if (StringUtils.isNotEmpty(this.mDialUuid)) {
                    s = this.mDialUuid;
                }
                else {
                    s = this.mUuid;
                }
                this.mNotifier.capability(s, this.mTargetCapability);
            }
            else {
                if ("GET_AUDIO_SUBTITLES".equals(s)) {
                    while (true) {
                        while (true) {
                            synchronized (this.mAudioSubtitleSettingBlobLock) {
                                if (!StringUtils.isNotEmpty(this.mAudioSubtitleSettingBlob)) {
                                    break;
                                }
                                Log.d("nf_mdx", "TargetContext: reutrn cached AUDIO_SUBTITLES_SETTING");
                                if (StringUtils.isNotEmpty(this.mDialUuid)) {
                                    final String s2 = this.mDialUuid;
                                    this.mNotifier.audiosub(s2, this.mAudioSubtitleSettingBlob);
                                    if (System.currentTimeMillis() - this.mPreviousAudioSubExchangeTime < 2000L) {
                                        Log.d("nf_mdx", "TargetContext: no need to get GET_AUDIO_SUBTITLES_SETTING");
                                        return;
                                    }
                                    break;
                                }
                            }
                            final String s2 = this.mUuid;
                            continue;
                        }
                    }
                    this.mPreviousAudioSubExchangeTime = System.currentTimeMillis();
                }
                // monitorexit(o)
                break Label_0234;
            }
            return;
        }
        final Message message = new Message();
        message.what = 2;
        message.obj = new Runnable() {
            @Override
            public void run() {
                TargetContext.this.mController.getSession().sendMessage(TargetContext.this.mSessionId, s, jsonObject);
            }
        };
        this.mTargetContextHandler.sendMessage(message);
        if ("PLAYER_SET_VOLUME".equals(s)) {
            final int optInt = jsonObject.optInt("volume", -1);
            if (optInt < 0) {
                return;
            }
            synchronized (this.mPlayerStateManager) {
                this.mPlayerStateManager.setTargetVolume(optInt);
                return;
            }
        }
        if ("SET_AUDIO_SUBTITLES".equals(s)) {
            this.invalidateCachedLanguageData();
            return;
        }
        synchronized (this.mPlayerStateManager) {
            this.mPlayerStateManager.receivedCommand(s);
        }
    }
    
    public void sessionEnd() {
        Label_0079: {
            if (!StringUtils.isNotEmpty(this.mDialUuid)) {
                break Label_0079;
            }
            final String mDialUuid = this.mDialUuid;
            while (true) {
                synchronized (this.mPlayerStateManager) {
                    this.mPlayerStateManager.forceToEndPlayback(mDialUuid, null);
                    // monitorexit(this.mPlayerStateManager)
                    this.mNotifier.error(mDialUuid, 200, "session ended");
                    final Message message = new Message();
                    message.what = 1;
                    message.obj = TargetContextEvent.SessionEnd;
                    this.mTargetContextHandler.sendMessage(message);
                    return;
                    final String mUuid = this.mUuid;
                }
            }
        }
    }
    
    public void sessionStarted(final int mSessionId) {
        this.mSessionId = mSessionId;
        final Message message = new Message();
        message.what = 1;
        message.obj = TargetContextEvent.StartSessionSucceed;
        this.mTargetContextHandler.sendMessage(message);
    }
    
    @Override
    public void stateHasError(final TargetState targetState) {
        String s;
        if (StringUtils.isNotEmpty(this.mDialUuid)) {
            s = this.mDialUuid;
        }
        else {
            s = this.mUuid;
        }
        final int determineStateErrorCode = this.determineStateErrorCode(targetState, this.mController.getSession().getLastMessageName(this.mSessionId));
        String string = new String();
        if (targetState != null) {
            string = targetState.getName() + " target error";
        }
        this.mNotifier.error(s, determineStateErrorCode, string);
    }
    
    @Override
    public void stateHasExhaustedRetry(final TargetState targetState) {
        String s;
        if (StringUtils.isNotEmpty(this.mDialUuid)) {
            s = this.mDialUuid;
        }
        else {
            s = this.mUuid;
        }
        final int determineStateErrorCode = this.determineStateErrorCode(targetState, this.mController.getSession().getLastMessageName(this.mSessionId));
        String string = new String();
        if (targetState != null) {
            string = targetState.getName() + ", failed: " + this.mLastError;
        }
        this.mNotifier.error(s, determineStateErrorCode, string);
    }
    
    @Override
    public void stateHasTimedOut(final TargetState targetState) {
        String s;
        if (StringUtils.isNotEmpty(this.mDialUuid)) {
            s = this.mDialUuid;
        }
        else {
            s = this.mUuid;
        }
        String string = new String();
        if (targetState != null) {
            string = targetState.getName() + ", timeout: " + this.mLastError;
        }
        this.mNotifier.error(s, 100, string);
    }
    
    public void updateTargetProperty(final RemoteDevice remoteDevice) {
        this.mUsn = this.getTargetProperty(remoteDevice.usn);
        this.mUuid = this.getTargetProperty(remoteDevice.uuid);
        this.mLocation = this.getTargetProperty(remoteDevice.location);
        this.mFriendlyName = this.getTargetProperty(remoteDevice.friendlyName);
        this.mDialUsn = this.getTargetProperty(remoteDevice.dialUsn);
        this.mDialUuid = this.getTargetProperty(remoteDevice.dialUuid);
        this.mPairingContext = this.getTargetProperty(remoteDevice.pairingContext);
        this.mServiceType = this.getTargetProperty(remoteDevice.serviceType);
        this.mLaunchStatus = remoteDevice.launchStatus;
        this.mRegistrationAcceptance = remoteDevice.registrationAcceptance;
        this.mActivated = remoteDevice.activated;
        if (this.mTargetContextHandler != null) {
            final Message message = new Message();
            message.what = 3;
            this.mTargetContextHandler.sendMessage(message);
        }
    }
    
    class DeletePair implements Runnable
    {
        @Override
        public void run() {
            TargetContext.this.mController.getPairing().deletePairing(TargetContext.this.mPairingContext);
            final Message message = new Message();
            message.what = 1;
            message.obj = TargetContextEvent.DeletePairSucceed;
            TargetContext.this.mTargetContextHandler.sendMessageDelayed(message, 20L);
        }
    }
    
    class DoHandShake implements Runnable
    {
        @Override
        public void run() {
            final Handshake handshake = new Handshake();
            TargetContext.this.mController.getSession().sendMessage(TargetContext.this.mSessionId, handshake.messageName(), handshake.messageObject());
        }
    }
    
    class DoPair implements Runnable
    {
        @Override
        public void run() {
            TargetContext.this.mController.getPairing().pairingRequest(TargetContext.this.mUuid);
        }
    }
    
    class DoRegPair implements Runnable
    {
        @Override
        public void run() {
            if (TargetContext.this.mRegistrationAcceptance == 1) {
                TargetContext.this.mController.getPairing().registrationPairingRequest(TargetContext.this.mUuid);
            }
            else if (TargetContext.this.mRegistrationAcceptance == 2) {
                TargetContext.this.mController.getPairing().registrationPairingRequest(TargetContext.this.mUuid, "00000");
            }
        }
    }
    
    class GetCapabilies implements Runnable
    {
        @Override
        public void run() {
            final PlayerGetCapabilities playerGetCapabilities = new PlayerGetCapabilities();
            TargetContext.this.mController.getSession().sendMessage(TargetContext.this.mSessionId, playerGetCapabilities.messageName(), playerGetCapabilities.messageObject());
        }
    }
    
    class GetState implements Runnable
    {
        @Override
        public void run() {
            final PlayerGetCurrentState playerGetCurrentState = new PlayerGetCurrentState();
            TargetContext.this.mController.getSession().sendMessage(TargetContext.this.mSessionId, playerGetCurrentState.messageName(), playerGetCurrentState.messageObject());
        }
    }
    
    class LaunchNetflix implements Runnable
    {
        @Override
        public void run() {
            TargetContext.this.mController.getDiscovery().isRemoteDeviceReady(TargetContext.this.mDialUsn);
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("intent", "sync");
            if (StringUtils.isNotEmpty(TargetContext.this.mDialUsn)) {
                TargetContext.this.mController.getDiscovery().launchNetflix(TargetContext.this.mDialUsn, hashMap);
                return;
            }
            TargetContext.this.mController.getDiscovery().launchNetflix(TargetContext.this.mUsn, hashMap);
        }
    }
    
    class PlayerStateManager
    {
        private static final long TIMEOUT_WAITING_FOR_STATE_CHANGE = 30000L;
        private static final long TIME_WINDOW_IGNORE_VOLUME = 3000L;
        private String mCatalogId;
        private String mCurrentState;
        private int mDuration;
        private String mEpisodeId;
        private int mExpectedVolume;
        private boolean mTargetStateTransitionStarted;
        private int mTime;
        private long mTimeMarked4StateTransition;
        private long mTimeSetVolume;
        private int mVolume;
        
        PlayerStateManager() {
            this.mTime = -1;
            this.mVolume = -1;
            this.mDuration = -1;
            this.mTargetStateTransitionStarted = true;
            this.mTimeMarked4StateTransition = 0L;
        }
        
        private void notifyState(final String s, final PlayerState playerState) {
            this.mTime = playerState.getTime();
            if (System.currentTimeMillis() > this.mTimeSetVolume + 3000L) {
                this.mVolume = playerState.getVolume();
            }
            else {
                this.mVolume = this.mExpectedVolume;
                Log.d("nf_mdx", "TargetContext: PlayerStateManager overide volume");
            }
            if (Log.isLoggable("nf_mdx", 3)) {
                Log.d("nf_mdx", "TargetContext: PlayerStateManager notifyState " + this.mCurrentState + ", volume = " + this.mVolume + ", time = " + this.mTime);
            }
            TargetContext.this.mNotifier.state(s, this.mCurrentState, this.mTime, this.mVolume);
            if (!StringUtils.safeEquals(this.mCatalogId, playerState.getCatalogId()) || !StringUtils.safeEquals(this.mEpisodeId, playerState.getEpisodeId()) || this.mDuration != playerState.getDuration()) {
                this.mCatalogId = playerState.getCatalogId();
                this.mEpisodeId = playerState.getEpisodeId();
                this.mDuration = playerState.getDuration();
                TargetContext.this.mNotifier.movieMetaData(s, this.mCatalogId, this.mEpisodeId, this.mDuration);
            }
        }
        
        public void changeState(final PlayerState playerState) {
            if (playerState != null) {
                String s;
                if (StringUtils.isNotEmpty(TargetContext.this.mDialUuid)) {
                    s = TargetContext.this.mDialUuid;
                }
                else {
                    s = TargetContext.this.mUuid;
                }
                final String currentState = playerState.getCurrentState();
                if (StringUtils.isEmpty(currentState)) {
                    Log.e("nf_mdx", "TargetContext: changeState, new state is null");
                    return;
                }
                final String postplayState = playerState.getPostplayState();
                if ("PLAYING".equals(currentState) && !"PAUSE".equals(this.mCurrentState) && !"prepause".equals(this.mCurrentState) && !"preseek".equals(this.mCurrentState) && !"PLAYING".equals(this.mCurrentState)) {
                    TargetContext.this.playbackStart(s);
                }
                else if ("STOP".equals(currentState) || "END_PLAYBACK".equals(currentState) || "FATAL_ERROR".equals(currentState)) {
                    TargetContext.this.playbackEnd(s, postplayState);
                }
                if ("PLAYING".equals(currentState) && !currentState.equals(this.mCurrentState)) {
                    TargetContext.this.mNotifier.simplePlaybackState(s, false, false, postplayState);
                }
                else if ("PAUSE".equals(currentState) && !currentState.equals(this.mCurrentState)) {
                    TargetContext.this.mNotifier.simplePlaybackState(s, true, false, postplayState);
                }
                if ("PLAY".equals(currentState)) {
                    TargetContext.this.mNotifier.state(s, "preplay", this.mTime, this.mVolume);
                }
                if ("PROGRESS".equals(currentState) || "PLAY".equals(currentState)) {
                    this.mTargetStateTransitionStarted = true;
                    this.mTimeMarked4StateTransition = System.currentTimeMillis();
                }
                else if (this.mTargetStateTransitionStarted) {
                    this.mCurrentState = currentState;
                    this.notifyState(s, playerState);
                }
                if (Log.isLoggable("nf_mdx", 3)) {
                    Log.d("nf_mdx", "TargetContext: PlayerStateManager state changed to " + this.mCurrentState);
                }
            }
        }
        
        public void forceToEndPlayback(final String s, final String s2) {
            this.mCurrentState = "END_PLAYBACK";
            TargetContext.this.playbackEnd(s, s2);
        }
        
        public String getCatalogId() {
            return this.mCatalogId;
        }
        
        public String getEpisodeId() {
            return this.mEpisodeId;
        }
        
        public String getTargetPlayerState() {
            return this.mCurrentState;
        }
        
        public void receivedCommand(final String s) {
            String s2;
            if (StringUtils.isNotEmpty(TargetContext.this.mDialUuid)) {
                s2 = TargetContext.this.mDialUuid;
            }
            else {
                s2 = TargetContext.this.mUuid;
            }
            if ("PLAYER_PLAY".equals(s)) {
                this.mTargetStateTransitionStarted = false;
                this.mTimeMarked4StateTransition = System.currentTimeMillis();
                this.mCurrentState = "preplay";
                this.mCatalogId = new String();
                this.mEpisodeId = new String();
                this.mTime = -1;
                this.mVolume = -1;
                this.mDuration = -1;
                TargetContext.this.mNotifier.simplePlaybackState(s2, false, true, null);
            }
            else if ("PLAYER_RESUME".equals(s)) {
                this.mTimeMarked4StateTransition = System.currentTimeMillis();
                this.mTargetStateTransitionStarted = false;
                this.mCurrentState = "preplay";
                TargetContext.this.mNotifier.simplePlaybackState(s2, false, true, null);
            }
            else if ("PLAYER_PAUSE".endsWith(s)) {
                this.mTimeMarked4StateTransition = System.currentTimeMillis();
                this.mTargetStateTransitionStarted = true;
                this.mCurrentState = "prepause";
                TargetContext.this.mNotifier.simplePlaybackState(s2, true, true, null);
            }
            else if ("PLAYER_SKIP".equals(s) || "PLAYER_SET_CURRENT_TIME".equals(s)) {
                this.mTimeMarked4StateTransition = System.currentTimeMillis();
                this.mTargetStateTransitionStarted = false;
                this.mCurrentState = "preseek";
                TargetContext.this.mNotifier.simplePlaybackState(s2, false, true, null);
            }
            else {
                if (!"PLAYER_GET_CURRENT_STATE".equals(s)) {
                    return;
                }
                TargetContext.this.mNotifier.state(s2, this.mCurrentState, this.mTime, this.mVolume);
                TargetContext.this.mNotifier.movieMetaData(s2, this.mCatalogId, this.mEpisodeId, this.mDuration);
            }
            TargetContext.this.mNotifier.state(s2, this.mCurrentState, this.mTime, this.mVolume);
        }
        
        public void setTargetVolume(final int mExpectedVolume) {
            this.mExpectedVolume = mExpectedVolume;
            this.mTimeSetVolume = System.currentTimeMillis();
        }
        
        public void updateState(final PlayerState playerState) {
            if (playerState == null) {
                return;
            }
            String s;
            if (StringUtils.isNotEmpty(TargetContext.this.mDialUuid)) {
                s = TargetContext.this.mDialUuid;
            }
            else {
                s = TargetContext.this.mUuid;
            }
            final String currentState = playerState.getCurrentState();
            if (StringUtils.isEmpty(currentState)) {
                Log.e("nf_mdx", "TargetContext: updateState, new state is null");
                return;
            }
            final String postplayState = playerState.getPostplayState();
            int n;
            if (System.currentTimeMillis() - this.mTimeMarked4StateTransition >= 30000L) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n == 0) {
                boolean b;
                if ("PLAYING".equals(currentState) && this.mTargetStateTransitionStarted) {
                    b = true;
                }
                else {
                    b = false;
                }
                if ("preplay".equals(this.mCurrentState) && !b) {
                    Log.d("nf_mdx", "TargetContext: updateState, still in preplay");
                    return;
                }
                if ("prepause".equals(this.mCurrentState) && "PAUSE".equals(currentState)) {
                    Log.d("nf_mdx", "TargetContext: updateState, still in prepause");
                    return;
                }
                if ("preseek".equals(this.mCurrentState) && !b) {
                    Log.d("nf_mdx", "TargetContext: updateState, still in preseek");
                    return;
                }
            }
            if ("PLAYING".equals(currentState) && !currentState.equals(this.mCurrentState)) {
                TargetContext.this.playbackStart(s);
                TargetContext.this.mNotifier.simplePlaybackState(s, false, false, postplayState);
            }
            else if ("PAUSE".equals(currentState) && !currentState.equals(this.mCurrentState)) {
                TargetContext.this.playbackStart(s);
                TargetContext.this.mNotifier.simplePlaybackState(s, true, false, postplayState);
            }
            if (StringUtils.isEmpty(this.mCurrentState)) {
                this.mCurrentState = currentState;
            }
            else if (!currentState.equals(this.mCurrentState)) {
                this.mCurrentState = currentState;
                Log.e("nf_mdx", "TargetContext: updateState, state updated before stateChange from " + this.mCurrentState + " to " + currentState);
            }
            this.notifyState(s, playerState);
        }
    }
    
    class StartSession implements Runnable
    {
        @Override
        public void run() {
            TargetContext.this.mController.getSession().startSession(TargetContext.this.mPairingContext);
        }
    }
}
