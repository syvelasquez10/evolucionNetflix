// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

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
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import android.os.Message;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;

public class TargetContext implements TargetStateManager$TargetStateManagerListener
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
    private final TargetContext$PlayerStateManager mPlayerStateManager;
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
        this.mPlayerStateManager = new TargetContext$PlayerStateManager(this);
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
        this.mTargetContextHandler = new TargetContext$1(this, this.mTargetContextThread.getLooper());
        if (this.mLaunchStatus == 0) {
            this.mStateMachine = new TargetStateManager(this, TargetStateManager$TargetState.StateNotLaunched, b);
        }
        else {
            this.mStateMachine = new TargetStateManager(this, TargetStateManager$TargetState.StateLaunched, b);
        }
        this.mStateMachine.setDefaultAction(TargetStateManager$StateId.StateNeedLaunched, new TargetContext$LaunchNetflix(this));
        this.mStateMachine.setDefaultAction(TargetStateManager$StateId.StateNoPair, new TargetContext$DoPair(this));
        this.mStateMachine.setDefaultAction(TargetStateManager$StateId.StateBadPair, new TargetContext$DeletePair(this));
        this.mStateMachine.setDefaultAction(TargetStateManager$StateId.StateNeedRegPair, new TargetContext$DoRegPair(this));
        this.mStateMachine.setDefaultAction(TargetStateManager$StateId.StateHasPair, new TargetContext$StartSession(this));
        this.mStateMachine.setDefaultAction(TargetStateManager$StateId.StateNeedHandShake, new TargetContext$DoHandShake(this));
        this.mStateMachine.addSessionRequest(new TargetContext$GetCapabilies(this));
        this.mStateMachine.addSessionRequest(new TargetContext$GetState(this));
        this.mStateMachine.start(notEmpty, this.mRegistrationAcceptance, this.mActivated, this.mLaunchStatus);
    }
    
    private int determineStateErrorCode(final TargetStateManager$TargetState targetStateManager$TargetState, final String s) {
        final int n = 105;
        int n2;
        if (TargetStateManager$TargetState.StateNoPair.equals(targetStateManager$TargetState) || TargetStateManager$TargetState.StateNoPairNeedRegPair.equals(targetStateManager$TargetState)) {
            n2 = 104;
        }
        else {
            n2 = n;
            if (!TargetStateManager$TargetState.StateHasPair.equals(targetStateManager$TargetState)) {
                n2 = n;
                if (!TargetStateManager$TargetState.StateNeedHandShake.equals(targetStateManager$TargetState)) {
                    if (TargetStateManager$TargetState.StateSendingMessage.equals(targetStateManager$TargetState)) {
                        if (StringUtils.isNotEmpty(s)) {
                            n2 = n;
                            if (MdxMessage.isUserCommand(s)) {
                                return n2;
                            }
                        }
                    }
                    else if (TargetStateManager$TargetState.StateNeedLaunched.equals(targetStateManager$TargetState)) {
                        return 100;
                    }
                    return 300;
                }
            }
        }
        return n2;
    }
    
    private void endPlaybackWithError(final String s, final int n) {
        if (n < 300) {
            this.playbackEnd(s, null);
        }
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
        this.mStateMachine.addSessionRequest(new TargetContext$GetState(this));
        this.mStateMachine.receivedEvent(TargetStateManager$TargetContextEvent.SessionCommandReceived);
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
    
    public WebApiUtils$VideoIds getVideoIds() {
        final TargetContext$PlayerStateManager mPlayerStateManager = this.mPlayerStateManager;
        // monitorenter(mPlayerStateManager)
        String catalogId;
        String episodeId;
        try {
            catalogId = this.mPlayerStateManager.getCatalogId();
            episodeId = this.mPlayerStateManager.getEpisodeId();
            // monitorexit(mPlayerStateManager)
            final String s = catalogId;
            final String s2 = episodeId;
            final WebApiUtils$VideoIds ids = WebApiUtils.extractIds(s, s2);
            return ids;
        }
        finally {
            final String s3;
            catalogId = s3;
        }
        // monitorexit(mPlayerStateManager)
        try {
            final String s = catalogId;
            final String s2 = episodeId;
            final WebApiUtils$VideoIds ids2;
            final WebApiUtils$VideoIds ids = ids2 = WebApiUtils.extractIds(s, s2);
            return ids2;
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
            message.obj = TargetStateManager$TargetContextEvent.LaunchSucceed;
        }
        else {
            message.obj = TargetStateManager$TargetContextEvent.LaunchFailed;
        }
        this.mTargetContextHandler.sendMessage(message);
        Log.d("nf_mdx", "TargetContext: send launchResult");
    }
    
    public void messageDelivered(final int n) {
        final Message message = new Message();
        message.what = 1;
        message.obj = TargetStateManager$TargetContextEvent.SendMessageSucceed;
        this.mTargetContextHandler.sendMessage(message);
    }
    
    public void messageError(final int n, final String s, final String s2) {
        this.mLastError = " message error " + s + ": " + s2;
        final Message message = new Message();
        message.what = 1;
        if ("6".equals(s) || "5".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_SESSION_INVALID_HMAC");
            message.obj = TargetStateManager$TargetContextEvent.SendMessageFailedNeedRepair;
        }
        else if ("11".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_SESSION_INVALID_SID");
            message.obj = TargetStateManager$TargetContextEvent.SendMessageFailedNeedNewSession;
        }
        else if ("4".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_SESSION_INVALID_NONCE");
            message.obj = TargetStateManager$TargetContextEvent.SendMessageFailed;
        }
        else if ("10".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_SESSION_NETWORK_ERROR");
            message.obj = TargetStateManager$TargetContextEvent.SendMessageFailed;
        }
        else {
            Log.d("nf_mdx", "TargetContext:  SESSION unknown error");
            message.obj = TargetStateManager$TargetContextEvent.SendMessageFailedNeedRepair;
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
                            ((Message)s).obj = TargetStateManager$TargetContextEvent.HandShakeSucceed;
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
                        final TargetContext$PlayerStateManager targetContext$PlayerStateManager = targetContext.mPlayerStateManager;
                        final PlayerState playerState2 = playerState;
                        targetContext$PlayerStateManager.updateState(playerState2);
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
                    final TargetContext$PlayerStateManager targetContext$PlayerStateManager = targetContext.mPlayerStateManager;
                    final PlayerState playerState2 = playerState;
                    targetContext$PlayerStateManager.updateState(playerState2);
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
                        final TargetContext$PlayerStateManager targetContext$PlayerStateManager2 = targetContext3.mPlayerStateManager;
                        final PlayerState playerState5 = playerState4;
                        targetContext$PlayerStateManager2.changeState(playerState5);
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
                    final TargetContext$PlayerStateManager targetContext$PlayerStateManager2 = targetContext3.mPlayerStateManager;
                    final PlayerState playerState5 = playerState4;
                    targetContext$PlayerStateManager2.changeState(playerState5);
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
        message.obj = TargetStateManager$TargetContextEvent.DeletePairSucceed;
        this.mTargetContextHandler.sendMessage(message);
    }
    
    public void pairingFail(final String s, final String s2) {
        this.mLastError = " pairing error " + s + ": " + s2;
        final Message message = new Message();
        message.what = 1;
        if ("30".equals(s) || "USER_MISMATCH".equals(s) || "20".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_PAIRING_USER_MISMATCH");
            message.obj = TargetStateManager$TargetContextEvent.PairFailedNeedRegPair;
        }
        else if ("31".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_PAIRING_ALREADY_PAIRED");
            message.obj = TargetStateManager$TargetContextEvent.PairFailedExistedPair;
        }
        else if ("22".equals(s) || "21".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  PAIRING CTICKET error ");
            this.mController.pingNccp();
            message.obj = TargetStateManager$TargetContextEvent.PairFailed;
        }
        else if ("99".equals(s) | "13".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  PAIRING NETWORK error ");
            message.obj = TargetStateManager$TargetContextEvent.PairFailed;
        }
        else if ("11".equals(s) || "12".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  PAIRING SERVER/TARGET error ");
            message.obj = TargetStateManager$TargetContextEvent.PairFailed;
        }
        else if ("0".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  PAIRING NOERROR error ");
            message.obj = TargetStateManager$TargetContextEvent.PairSucceed;
        }
        else if ("42".equals(s)) {
            Log.d("nf_mdx", "TargetContext:  MDX_REGISTRATION_PAIRING_IN_PROGRESS error ");
            message.obj = TargetStateManager$TargetContextEvent.RegistrationInProgress;
        }
        else {
            Log.d("nf_mdx", "TargetContext:  PAIRING unknown error ");
            message.obj = TargetStateManager$TargetContextEvent.PairFailedNeedRegPair;
        }
        this.mTargetContextHandler.sendMessage(message);
    }
    
    public void pairingSucceed(final String mPairingContext) {
        final Message message = new Message();
        message.what = 1;
        message.obj = TargetStateManager$TargetContextEvent.PairSucceed;
        synchronized (this.mPairingContext) {
            // monitorexit(this.mPairingContext = mPairingContext)
            this.mTargetContextHandler.sendMessage(message);
        }
    }
    
    @Override
    public void removeEvents(final TargetStateManager$TargetContextEvent targetStateManager$TargetContextEvent) {
        if (this.mTargetContextHandler != null) {
            this.mTargetContextHandler.removeMessages(1, (Object)targetStateManager$TargetContextEvent);
        }
    }
    
    @Override
    public void scheduleEvent(final TargetStateManager$TargetContextEvent obj, final int n) {
        final Message message = new Message();
        message.what = 1;
        message.obj = obj;
        if (n > 0) {
            if (this.mTargetContextHandler != null) {
                this.mTargetContextHandler.sendMessageDelayed(message, (long)n);
            }
        }
        else if (this.mTargetContextHandler != null) {
            this.mTargetContextHandler.sendMessage(message);
        }
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
        message.obj = new TargetContext$2(this, s, jsonObject);
        if (this.mTargetContextHandler == null) {
            return;
        }
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
                    this.mNotifier.error(mDialUuid, 201, "session ended");
                    final Message message = new Message();
                    message.what = 1;
                    message.obj = TargetStateManager$TargetContextEvent.SessionEnd;
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
        message.obj = TargetStateManager$TargetContextEvent.StartSessionSucceed;
        this.mTargetContextHandler.sendMessage(message);
    }
    
    @Override
    public void stateHasError(final TargetStateManager$TargetState targetStateManager$TargetState) {
        String s;
        if (StringUtils.isNotEmpty(this.mDialUuid)) {
            s = this.mDialUuid;
        }
        else {
            s = this.mUuid;
        }
        final int determineStateErrorCode = this.determineStateErrorCode(targetStateManager$TargetState, this.mController.getSession().getLastMessageName(this.mSessionId));
        String string = new String();
        if (targetStateManager$TargetState != null) {
            string = targetStateManager$TargetState.getName() + " target error";
        }
        this.endPlaybackWithError(s, determineStateErrorCode);
        this.mNotifier.error(s, determineStateErrorCode, string);
    }
    
    @Override
    public void stateHasExhaustedRetry(final TargetStateManager$TargetState targetStateManager$TargetState) {
        String s;
        if (StringUtils.isNotEmpty(this.mDialUuid)) {
            s = this.mDialUuid;
        }
        else {
            s = this.mUuid;
        }
        final int determineStateErrorCode = this.determineStateErrorCode(targetStateManager$TargetState, this.mController.getSession().getLastMessageName(this.mSessionId));
        String string = new String();
        if (targetStateManager$TargetState != null) {
            string = targetStateManager$TargetState.getName() + ", failed: " + this.mLastError;
        }
        this.endPlaybackWithError(s, determineStateErrorCode);
        this.mNotifier.error(s, determineStateErrorCode, string);
    }
    
    @Override
    public void stateHasTimedOut(final TargetStateManager$TargetState targetStateManager$TargetState) {
        String s;
        if (StringUtils.isNotEmpty(this.mDialUuid)) {
            s = this.mDialUuid;
        }
        else {
            s = this.mUuid;
        }
        String string = new String();
        if (targetStateManager$TargetState != null) {
            string = targetStateManager$TargetState.getName() + ", timeout: " + this.mLastError;
        }
        this.endPlaybackWithError(s, 100);
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
}
