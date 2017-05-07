// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import com.netflix.mediaclient.event.nrdp.mdx.session.SessionEndedEvent;
import com.netflix.mediaclient.event.nrdp.mdx.session.MessagingErrorEvent;
import com.netflix.mediaclient.event.nrdp.mdx.session.MessageEvent;
import com.netflix.mediaclient.event.nrdp.mdx.session.MessageDeliveredEvent;
import com.netflix.mediaclient.event.nrdp.mdx.session.StartSessionResponseEvent;
import com.netflix.mediaclient.event.nrdp.mdx.pair.PairingDeletedEvent;
import com.netflix.mediaclient.event.nrdp.mdx.pair.RegPairResponseEvent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.event.nrdp.mdx.pair.PairingResponseEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerStop;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerSkip;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerSetVolume;
import com.netflix.mediaclient.service.mdx.message.controller.SetAudioSubtitles;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerSetCurrentTime;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerResume;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerPlay;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerPause;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerGetCurrentState;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerGetCapabilities;
import com.netflix.mediaclient.service.mdx.message.controller.GetAudioSubtitles;
import com.netflix.mediaclient.service.mdx.message.controller.DialogResponse;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerChangeMetaData;
import com.netflix.mediaclient.service.mdx.message.controller.PlayerSetAutoAdvance;
import com.netflix.mediaclient.service.mdx.message.controller.PinConfirmed;
import com.netflix.mediaclient.service.mdx.message.controller.PinCancelled;
import com.netflix.mediaclient.util.WebApiUtils;
import java.util.concurrent.atomic.AtomicLong;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import com.netflix.mediaclient.javabridge.ui.EventListener;

public class TargetManager implements EventListener, CommandInterface
{
    private static final String TAG = "nf_mdx";
    private MdxController mController;
    private String mEsn;
    private NotifierInterface mNotify;
    private AtomicLong mRecentMessageTime;
    private boolean mTaregtLaunchingOrLaunched;
    private TargetContext mTarget;
    
    TargetManager(final NotifierInterface mNotify, final MdxController mController, final String mEsn) {
        this.mRecentMessageTime = new AtomicLong();
        this.mTaregtLaunchingOrLaunched = true;
        this.mNotify = mNotify;
        this.mController = mController;
        this.mEsn = mEsn;
        this.mRecentMessageTime.set(System.currentTimeMillis());
    }
    
    public long getTimeOfMostRecentIncomingMessage() {
        return this.mRecentMessageTime.get();
    }
    
    public WebApiUtils.VideoIds getVideoIds() {
        if (this.mTarget != null) {
            return this.mTarget.getVideoIds();
        }
        return null;
    }
    
    public boolean hasActiveSession() {
        return this.mTarget != null && this.mTarget.hasActiveSession();
    }
    
    public boolean isTargetHaveContext(final String s) {
        return this.mTarget != null && this.mTarget.isThisTargetUuid(s);
    }
    
    public boolean isTargetLaunchingOrLaunched(final String s) {
        return this.mTaregtLaunchingOrLaunched;
    }
    
    @Override
    public void pinCancelled(final String s) {
        if (this.mTarget != null) {
            final PinCancelled pinCancelled = new PinCancelled();
            this.mTarget.sendCommand(pinCancelled.messageName(), pinCancelled.messageObject());
        }
    }
    
    @Override
    public void pinConfirmed(final String s) {
        if (this.mTarget != null) {
            final PinConfirmed pinConfirmed = new PinConfirmed();
            this.mTarget.sendCommand(pinConfirmed.messageName(), pinConfirmed.messageObject());
        }
    }
    
    @Override
    public void playerAutoAdvance(final String s, final int n) {
        if (this.mTarget != null) {
            final PlayerSetAutoAdvance playerSetAutoAdvance = new PlayerSetAutoAdvance(this.mTarget.getTargetPlaybackSessionToken(), n);
            this.mTarget.sendCommand(playerSetAutoAdvance.messageName(), playerSetAutoAdvance.messageObject());
        }
    }
    
    @Override
    public void playerChangeMetaData(final String s, final String s2, final String s3, final String s4) {
        if (this.mTarget != null) {
            final PlayerChangeMetaData playerChangeMetaData = new PlayerChangeMetaData(s2, s3, s4);
            this.mTarget.sendCommand(playerChangeMetaData.messageName(), playerChangeMetaData.messageObject());
        }
    }
    
    @Override
    public void playerDialogReponse(final String s, final String s2, final String s3) {
        if (this.mTarget != null) {
            final DialogResponse dialogResponse = new DialogResponse(s2, s3);
            this.mTarget.sendCommand(dialogResponse.messageName(), dialogResponse.messageObject());
        }
    }
    
    @Override
    public void playerGetAudioSubtitle(final String s) {
        if (this.mTarget != null) {
            final GetAudioSubtitles getAudioSubtitles = new GetAudioSubtitles();
            this.mTarget.sendCommand(getAudioSubtitles.messageName(), getAudioSubtitles.messageObject());
        }
    }
    
    @Override
    public void playerGetCapability(final String s) {
        if (this.mTarget != null) {
            final PlayerGetCapabilities playerGetCapabilities = new PlayerGetCapabilities();
            this.mTarget.sendCommand(playerGetCapabilities.messageName(), playerGetCapabilities.messageObject());
        }
    }
    
    @Override
    public void playerGetCurrentState(final String s) {
        if (this.mTarget != null) {
            final PlayerGetCurrentState playerGetCurrentState = new PlayerGetCurrentState();
            this.mTarget.sendCommand(playerGetCurrentState.messageName(), playerGetCurrentState.messageObject());
        }
    }
    
    @Override
    public void playerPause(final String s) {
        if (this.mTarget != null) {
            final PlayerPause playerPause = new PlayerPause(this.mTarget.getTargetPlaybackSessionToken());
            this.mTarget.sendCommand(playerPause.messageName(), playerPause.messageObject());
        }
    }
    
    @Override
    public void playerPlay(final String s, final String s2, final int n, final String s3, final int n2) {
        if (this.mTarget != null) {
            final PlayerPlay playerPlay = new PlayerPlay(s2, n, this.mEsn, s3, n2);
            this.mTarget.sendCommand(playerPlay.messageName(), playerPlay.messageObject());
        }
    }
    
    @Override
    public void playerResume(final String s) {
        if (this.mTarget != null) {
            final PlayerResume playerResume = new PlayerResume(this.mTarget.getTargetPlaybackSessionToken());
            this.mTarget.sendCommand(playerResume.messageName(), playerResume.messageObject());
        }
    }
    
    @Override
    public void playerSeek(final String s, final int n) {
        if (this.mTarget != null) {
            final PlayerSetCurrentTime playerSetCurrentTime = new PlayerSetCurrentTime(this.mTarget.getTargetPlaybackSessionToken(), n);
            this.mTarget.sendCommand(playerSetCurrentTime.messageName(), playerSetCurrentTime.messageObject());
        }
    }
    
    @Override
    public void playerSetAudioSubtitle(final String s, final String s2, final String s3) {
        if (this.mTarget != null) {
            final SetAudioSubtitles setAudioSubtitles = new SetAudioSubtitles(s2, s3);
            this.mTarget.sendCommand(setAudioSubtitles.messageName(), setAudioSubtitles.messageObject());
        }
    }
    
    @Override
    public void playerSetVolume(final String s, final int n) {
        if (this.mTarget != null) {
            final PlayerSetVolume playerSetVolume = new PlayerSetVolume(this.mTarget.getTargetPlaybackSessionToken(), n);
            this.mTarget.sendCommand(playerSetVolume.messageName(), playerSetVolume.messageObject());
        }
    }
    
    @Override
    public void playerSkip(final String s, final int n) {
        if (this.mTarget != null) {
            final PlayerSkip playerSkip = new PlayerSkip(this.mTarget.getTargetPlaybackSessionToken(), n);
            this.mTarget.sendCommand(playerSkip.messageName(), playerSkip.messageObject());
        }
    }
    
    @Override
    public void playerStop(final String s) {
        if (this.mTarget != null) {
            final PlayerStop playerStop = new PlayerStop(this.mTarget.getTargetPlaybackSessionToken());
            this.mTarget.sendCommand(playerStop.messageName(), playerStop.messageObject());
        }
    }
    
    @Override
    public void received(final UIEvent uiEvent) {
        if (this.mTarget == null) {
            Log.d("nf_mdx", "TargetManager: no active target for event");
        }
        else {
            this.mRecentMessageTime.set(System.currentTimeMillis());
            if (uiEvent instanceof PairingResponseEvent) {
                final PairingResponseEvent pairingResponseEvent = (PairingResponseEvent)uiEvent;
                if (this.mTarget.isThisTargetUuid(pairingResponseEvent.getRemoteDevice())) {
                    if (StringUtils.isNotEmpty(pairingResponseEvent.getPairingContext()) && (StringUtils.isEmpty(pairingResponseEvent.getErrorCode()) || "0".equals(pairingResponseEvent.getErrorCode()))) {
                        Log.d("nf_mdx", "TargetManager: pairingSucceed");
                        this.mTarget.pairingSucceed(pairingResponseEvent.getPairingContext());
                        return;
                    }
                    Log.d("nf_mdx", "TargetManager: pairingFail");
                    this.mTarget.pairingFail(pairingResponseEvent.getErrorCode(), pairingResponseEvent.getErrorDesc());
                }
            }
            else if (uiEvent instanceof RegPairResponseEvent) {
                final RegPairResponseEvent regPairResponseEvent = (RegPairResponseEvent)uiEvent;
                if (this.mTarget.isThisTargetUuid(regPairResponseEvent.getRemoteDevice())) {
                    if (StringUtils.isNotEmpty(regPairResponseEvent.getPairingContext()) && (StringUtils.isEmpty(regPairResponseEvent.getErrorCode()) || "0".equals(regPairResponseEvent.getErrorCode()))) {
                        this.mTarget.pairingSucceed(regPairResponseEvent.getPairingContext());
                        return;
                    }
                    this.mTarget.pairingFail(regPairResponseEvent.getErrorCode(), regPairResponseEvent.getErrorDesc());
                }
            }
            else if (uiEvent instanceof PairingDeletedEvent) {
                if (this.mTarget.isThisTargetPairingContext(((PairingDeletedEvent)uiEvent).getPairingContext())) {
                    this.mTarget.pairingDeleted();
                }
            }
            else if (uiEvent instanceof StartSessionResponseEvent) {
                final StartSessionResponseEvent startSessionResponseEvent = (StartSessionResponseEvent)uiEvent;
                if (this.mTarget.isThisTargetPairingContext(startSessionResponseEvent.getPairingContext())) {
                    this.mTarget.sessionStarted(startSessionResponseEvent.getSid());
                }
            }
            else if (uiEvent instanceof MessageDeliveredEvent) {
                final MessageDeliveredEvent messageDeliveredEvent = (MessageDeliveredEvent)uiEvent;
                if (this.mTarget.isThisTargetPairingContext(messageDeliveredEvent.getPairingContext())) {
                    this.mTarget.messageDelivered(messageDeliveredEvent.getTransactionId());
                }
            }
            else if (uiEvent instanceof MessageEvent) {
                final MessageEvent messageEvent = (MessageEvent)uiEvent;
                if (this.mTarget.isThisTargetPairingContext(messageEvent.getPairingContext())) {
                    this.mTarget.messageReceived(messageEvent.getSid(), messageEvent.getType(), messageEvent.getMsgObject());
                }
            }
            else if (uiEvent instanceof MessagingErrorEvent) {
                final MessagingErrorEvent messagingErrorEvent = (MessagingErrorEvent)uiEvent;
                if (this.mTarget.isThisTargetPairingContext(messagingErrorEvent.getPairingContext())) {
                    this.mTarget.messageError(messagingErrorEvent.getTransactionId(), messagingErrorEvent.getError().toString(), new String());
                }
            }
            else {
                if (!(uiEvent instanceof SessionEndedEvent)) {
                    Log.e("nf_mdx", "unexpected event " + uiEvent);
                    return;
                }
                if (this.mTarget.isThisSession(((SessionEndedEvent)uiEvent).getSid())) {
                    this.mTarget.sessionEnd();
                }
            }
        }
    }
    
    public void targetFound(final RemoteDevice remoteDevice) {
        if (this.mTarget == null) {
            this.mTarget = new TargetContext(this.mController, this.mNotify, remoteDevice, false);
            return;
        }
        if (this.mTarget.isThisTargetUuid(remoteDevice.uuid) || this.mTarget.isThisTargetUuid(remoteDevice.dialUuid)) {
            Log.d("nf_mdx", "TargetManager: targetFound same target");
            synchronized (this.mTarget) {
                this.mTarget.updateTargetProperty(remoteDevice);
                return;
            }
        }
        Log.e("nf_mdx", "TargetManager: targetFound different target");
    }
    
    public void targetGone(final String s) {
        if (this.mTarget != null) {
            this.mTarget.destroy();
            this.mTarget = null;
        }
    }
    
    public void targetLaunched(final String s, final boolean mTaregtLaunchingOrLaunched) {
        this.mTaregtLaunchingOrLaunched = mTaregtLaunchingOrLaunched;
        if (this.mTarget == null) {
            Log.e("nf_mdx", "no active target for targetLaunched");
            return;
        }
        this.mTarget.launchResult(mTaregtLaunchingOrLaunched);
    }
    
    public void targetSelected(final RemoteDevice remoteDevice) {
        if (remoteDevice == null) {
            Log.e("nf_mdx", "TargetManager: targetSelected is null");
            return;
        }
        if (this.mTarget != null) {
            if (this.mTarget.isThisTargetUuid(remoteDevice.uuid) || this.mTarget.isThisTargetUuid(remoteDevice.dialUuid)) {
                Log.d("nf_mdx", "TargetManager: targetSelected same target");
            }
            else {
                Log.d("nf_mdx", "TargetManager: targetSelected new target");
            }
        }
        this.mTaregtLaunchingOrLaunched = true;
        if (this.mTarget != null) {
            this.mTarget.destroy();
        }
        this.mTarget = new TargetContext(this.mController, this.mNotify, remoteDevice, true);
    }
}
