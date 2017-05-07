// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.Log;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;

public class TargetStateManager
{
    private static final int DEFAULT_RETRY_INTERNAL = 1000;
    private static final long LAUNCH_ATTEMPT_TIMEOUT_MS;
    private static final int LAUNCH_TIMEOUT = 64000;
    private static final int MAX_STATEMACHINE_RESET_ON_ERROR = 3;
    private static final int PAIRING_RETRY_INTERVAL = 3000;
    private static final int PAIRING_TIMEOUT = 24000;
    private static final int REGPAIRING_RETRY_INTERVAL = 4000;
    private static final int REGPAIRING_TIMEOUT = 32000;
    private static final int SESSION_MESSAGE_RETRY = 4;
    private static final int SESSION_RETRY_INTERVAL = 1000;
    private static final int STATE_TIMEOUT = 8000;
    private static final String TAG = "nf_mdx";
    private boolean mActivated;
    TargetStateManager$TargetState mCurrentState;
    private Map<TargetStateManager$StateId, Runnable> mDefaultAction;
    private boolean mHasUiCommand;
    private boolean mIsPreviouslyPaired;
    private boolean mIsTargetSelected;
    private boolean mLaunched;
    private TargetStateManager$TargetStateManagerListener mListener;
    private Runnable mPendingSessionAction;
    private AtomicLong mPreviousLaunchAttemptMs;
    TargetStateManager$TargetState mPreviousState;
    private int mRegistrationAcceptance;
    int mRetryCurrentAction;
    int mRetryCurrentInterval;
    private ArrayList<Runnable> mSessionRequested;
    private int mStateMachineResetCountSince;
    
    static {
        LAUNCH_ATTEMPT_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(5L);
    }
    
    TargetStateManager(final TargetStateManager$TargetStateManagerListener mListener, final TargetStateManager$TargetState mCurrentState, final boolean mIsTargetSelected) {
        this.mSessionRequested = new ArrayList<Runnable>();
        this.mDefaultAction = new HashMap<TargetStateManager$StateId, Runnable>();
        this.mStateMachineResetCountSince = 0;
        this.mPreviousLaunchAttemptMs = new AtomicLong(0L);
        if (Log.isLoggable()) {
            Log.d("nf_mdx", "StateMachine: init state " + mCurrentState.getName());
        }
        this.mListener = mListener;
        this.mIsTargetSelected = mIsTargetSelected;
        this.mCurrentState = mCurrentState;
        this.mRetryCurrentAction = mCurrentState.getRetry();
        this.mHasUiCommand = false;
    }
    
    private void resetStateMachineResetCount() {
        this.mStateMachineResetCountSince = 0;
    }
    
    private void scheduleRetry(final TargetStateManager$TargetContextEvent targetStateManager$TargetContextEvent) {
        if (Log.isLoggable()) {
            Log.d("nf_mdx", "TargetStateManager: schedule retry for " + this.mCurrentState.mName + " in " + this.mRetryCurrentInterval);
        }
        this.mListener.scheduleEvent(targetStateManager$TargetContextEvent, this.mRetryCurrentInterval);
    }
    
    private void sessionEnded() {
        this.mHasUiCommand = false;
        this.mSessionRequested.clear();
        this.transitionStateTo(TargetStateManager$TargetState.StateSessionEnd);
    }
    
    private void transitionStateTo(final TargetStateManager$TargetState mCurrentState) {
        if (Log.isLoggable()) {
            Log.d("nf_mdx", "TargetStateManager: from " + this.mCurrentState.mName + " to " + mCurrentState.mName);
        }
        this.mPreviousState = this.mCurrentState;
        if (this.mCurrentState == mCurrentState) {
            if (this.mRetryCurrentAction <= 0) {
                this.transitionStateTo(TargetStateManager$TargetState.StateRetryExhausted);
                this.mListener.stateHasExhaustedRetry(this.mPreviousState);
                return;
            }
            --this.mRetryCurrentAction;
            this.mRetryCurrentInterval += this.mRetryCurrentInterval;
            this.mListener.removeEvents(TargetStateManager$TargetContextEvent.Timeout);
            this.mListener.removeEvents(TargetStateManager$TargetContextEvent.SessionRetry);
            this.mListener.removeEvents(TargetStateManager$TargetContextEvent.PairingRetry);
        }
        else {
            this.mListener.removeEvents(TargetStateManager$TargetContextEvent.Timeout);
            this.mListener.removeEvents(TargetStateManager$TargetContextEvent.SessionRetry);
            this.mListener.removeEvents(TargetStateManager$TargetContextEvent.PairingRetry);
            this.mCurrentState = mCurrentState;
            this.mRetryCurrentAction = this.mCurrentState.getRetry();
            this.mRetryCurrentInterval = this.mCurrentState.getRetryInterval();
        }
        final Runnable runnable = this.mDefaultAction.get(this.mCurrentState.getId());
        if (runnable != null) {
            runnable.run();
            if (this.mCurrentState.getTimeOut() > 0) {
                this.mListener.scheduleEvent(TargetStateManager$TargetContextEvent.Timeout, this.mCurrentState.getTimeOut());
            }
        }
    }
    
    public void addSessionRequest(final Runnable runnable) {
        this.mSessionRequested.add(runnable);
    }
    
    public void addUiCommand(final Runnable runnable, final boolean mHasUiCommand) {
        Log.d("nf_mdx", "StateMachine: addUiCommand ");
        this.mHasUiCommand = mHasUiCommand;
        this.mSessionRequested.add(runnable);
    }
    
    public boolean canRestartState(final TargetStateManager$TargetState targetStateManager$TargetState) {
        return this.mStateMachineResetCountSince < 3 && (TargetStateManager$TargetState.StateHasPair.equals(targetStateManager$TargetState) || TargetStateManager$TargetState.StateNeedHandShake.equals(targetStateManager$TargetState) || TargetStateManager$TargetState.StateSendingMessage.equals(targetStateManager$TargetState));
    }
    
    public boolean isSessionActive() {
        return TargetStateManager$TargetState.StateSessionReady.equals(this.mCurrentState) || TargetStateManager$TargetState.StateSendingMessage.equals(this.mCurrentState);
    }
    
    public void receivedEvent(final TargetStateManager$TargetContextEvent targetStateManager$TargetContextEvent) {
        if (TargetStateManager$TargetContextEvent.LaunchFailed.equals(targetStateManager$TargetContextEvent) && this.mCurrentState.getId() != TargetStateManager$StateId.StateNeedLaunched) {
            this.transitionStateTo(TargetStateManager$TargetState.StateNotLaunched);
            this.mLaunched = false;
        }
        else {
            switch (TargetStateManager$1.$SwitchMap$com$netflix$mediaclient$service$mdx$TargetStateManager$StateId[this.mCurrentState.getId().ordinal()]) {
                case 3: {
                    break;
                }
                default: {}
                case 1: {
                    if (TargetStateManager$TargetContextEvent.StartTarget.equals(targetStateManager$TargetContextEvent) || TargetStateManager$TargetContextEvent.SessionCommandReceived.equals(targetStateManager$TargetContextEvent)) {
                        this.mPreviousLaunchAttemptMs.set(System.currentTimeMillis());
                        this.transitionStateTo(TargetStateManager$TargetState.StateNeedLaunched);
                        return;
                    }
                    if ((!TargetStateManager$TargetContextEvent.TargetUpdate.equals(targetStateManager$TargetContextEvent) || !this.mLaunched) && !TargetStateManager$TargetContextEvent.LaunchSucceed.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateNotLaunched);
                        return;
                    }
                    if (this.mIsPreviouslyPaired) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                        return;
                    }
                    this.transitionStateTo(TargetStateManager$TargetState.StateNoPair);
                }
                case 2: {
                    if (TargetStateManager$TargetContextEvent.LaunchSucceed.equals(targetStateManager$TargetContextEvent)) {
                        if (this.mIsPreviouslyPaired) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                            return;
                        }
                        if (!this.mActivated) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateNeedRegPair);
                            return;
                        }
                        this.transitionStateTo(TargetStateManager$TargetState.StateNoPair);
                        return;
                    }
                    else {
                        if (TargetStateManager$TargetContextEvent.LaunchFailed.equals(targetStateManager$TargetContextEvent)) {
                            this.scheduleRetry(TargetStateManager$TargetContextEvent.LaunchRetry);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.LaunchRetry.equals(targetStateManager$TargetContextEvent)) {
                            final long value = this.mPreviousLaunchAttemptMs.get();
                            final long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis < TargetStateManager.LAUNCH_ATTEMPT_TIMEOUT_MS + value && value > 0L) {
                                this.mPreviousLaunchAttemptMs.set(currentTimeMillis);
                                this.transitionStateTo(TargetStateManager$TargetState.StateNeedLaunched);
                                return;
                            }
                            this.transitionStateTo(TargetStateManager$TargetState.StateTimeout);
                            this.mListener.stateHasTimedOut(this.mPreviousState);
                            return;
                        }
                        else if (TargetStateManager$TargetContextEvent.TargetUpdate.equals(targetStateManager$TargetContextEvent) && this.mLaunched) {
                            if (this.mIsPreviouslyPaired) {
                                this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                                return;
                            }
                            this.transitionStateTo(TargetStateManager$TargetState.StateNoPair);
                            return;
                        }
                        else {
                            if (TargetStateManager$TargetContextEvent.Timeout.equals(targetStateManager$TargetContextEvent)) {
                                this.transitionStateTo(TargetStateManager$TargetState.StateTimeout);
                                this.mListener.stateHasTimedOut(this.mPreviousState);
                                return;
                            }
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    if (TargetStateManager$TargetContextEvent.StartSessionSucceed.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateNeedHandShake);
                        this.resetStateMachineResetCount();
                        this.mIsTargetSelected = false;
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.SendMessageFailedNeedRepair.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateBadPair);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.SendMessageFailed.equals(targetStateManager$TargetContextEvent) || TargetStateManager$TargetContextEvent.SendMessageFailedNeedNewSession.equals(targetStateManager$TargetContextEvent)) {
                        this.scheduleRetry(TargetStateManager$TargetContextEvent.SessionRetry);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.SessionRetry.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.Timeout.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                        return;
                    }
                    break;
                }
                case 5: {
                    if (TargetStateManager$TargetContextEvent.DeletePairSucceed.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateNoPair);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.Timeout.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateTimeout);
                        this.mListener.stateHasTimedOut(this.mPreviousState);
                        return;
                    }
                    break;
                }
                case 6: {
                    if (TargetStateManager$TargetContextEvent.PairSucceed.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.PairFailedNeedRegPair.equals(targetStateManager$TargetContextEvent)) {
                        if (this.mRegistrationAcceptance != 0 && (this.mHasUiCommand || this.mIsTargetSelected)) {
                            if (Log.isLoggable()) {
                                Log.d("nf_mdx", "StateMachine: StateNoPair, mHasUiCommand =" + this.mHasUiCommand + ", mIsTargetSelected = " + this.mIsTargetSelected);
                            }
                            this.transitionStateTo(TargetStateManager$TargetState.StateNeedRegPair);
                            return;
                        }
                        if (this.mRegistrationAcceptance != 0) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateNoPairNeedRegPair);
                            this.mListener.stateHasError(TargetStateManager$TargetState.StateNoPair);
                            return;
                        }
                        this.transitionStateTo(TargetStateManager$TargetState.StateHasError);
                        this.mListener.stateHasError(this.mPreviousState);
                        return;
                    }
                    else {
                        if (TargetStateManager$TargetContextEvent.PairFailedExistedPair.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateBadPair);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.PairFailed.equals(targetStateManager$TargetContextEvent)) {
                            this.scheduleRetry(TargetStateManager$TargetContextEvent.PairingRetry);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.PairingRetry.equals(targetStateManager$TargetContextEvent) || TargetStateManager$TargetContextEvent.RegistrationInProgress.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateNoPair);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.Timeout.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateTimeout);
                            this.mListener.stateHasTimedOut(this.mPreviousState);
                            return;
                        }
                        break;
                    }
                    break;
                }
                case 7: {
                    if (TargetStateManager$TargetContextEvent.SessionCommandReceived.equals(targetStateManager$TargetContextEvent) && this.mRegistrationAcceptance != 0) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateNeedRegPair);
                        return;
                    }
                    break;
                }
                case 8: {
                    if (TargetStateManager$TargetContextEvent.PairSucceed.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.PairFailedExistedPair.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateBadPair);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.Timeout.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateTimeout);
                        this.mListener.stateHasTimedOut(this.mPreviousState);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.PairFailed.equals(targetStateManager$TargetContextEvent) || TargetStateManager$TargetContextEvent.PairFailedNeedRegPair.equals(targetStateManager$TargetContextEvent)) {
                        this.scheduleRetry(TargetStateManager$TargetContextEvent.PairingRetry);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.PairingRetry.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateNeedRegPair);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.RegistrationInProgress.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateNoPair);
                        return;
                    }
                    break;
                }
                case 9: {
                    if (TargetStateManager$TargetContextEvent.SessionEnd.equals(targetStateManager$TargetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.HandShakeSucceed.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateSessionReady);
                        this.resetStateMachineResetCount();
                        if (!this.mSessionRequested.isEmpty()) {
                            this.mListener.scheduleEvent(TargetStateManager$TargetContextEvent.SessionCommandReceived, 0);
                            return;
                        }
                        break;
                    }
                    else {
                        if (TargetStateManager$TargetContextEvent.HandShakeFailed.equals(targetStateManager$TargetContextEvent)) {
                            this.scheduleRetry(TargetStateManager$TargetContextEvent.SessionRetry);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.SessionRetry.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateNeedHandShake);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.Timeout.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateNeedHandShake);
                            return;
                        }
                        break;
                    }
                    break;
                }
                case 10: {
                    if (TargetStateManager$TargetContextEvent.SessionEnd.equals(targetStateManager$TargetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.SessionCommandReceived.equals(targetStateManager$TargetContextEvent)) {
                        if (!this.mSessionRequested.isEmpty()) {
                            this.mPendingSessionAction = this.mSessionRequested.remove(0);
                            this.setDefaultAction(TargetStateManager$StateId.StateSendingMessage, this.mPendingSessionAction);
                            this.transitionStateTo(TargetStateManager$TargetState.StateSendingMessage);
                            return;
                        }
                        Log.e("nf_mdx", "StateMachine: SessionCommandReceived, but no task!");
                        return;
                    }
                    else if (TargetStateManager$TargetContextEvent.SendMessageFailedNeedRepair.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateBadPair);
                        if (this.mPendingSessionAction != null) {
                            this.addUiCommand(this.mPendingSessionAction, false);
                            return;
                        }
                        break;
                    }
                    else {
                        if (!TargetStateManager$TargetContextEvent.SendMessageFailedNeedNewSession.equals(targetStateManager$TargetContextEvent)) {
                            break;
                        }
                        this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                        if (this.mPendingSessionAction != null) {
                            this.addUiCommand(this.mPendingSessionAction, false);
                            return;
                        }
                        break;
                    }
                    break;
                }
                case 11: {
                    if (TargetStateManager$TargetContextEvent.SessionEnd.equals(targetStateManager$TargetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.SendMessageSucceed.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateSessionReady);
                        this.resetStateMachineResetCount();
                        if (!this.mSessionRequested.isEmpty()) {
                            this.mListener.scheduleEvent(TargetStateManager$TargetContextEvent.SessionCommandReceived, 0);
                            return;
                        }
                        break;
                    }
                    else {
                        if (TargetStateManager$TargetContextEvent.SendMessageFailedNeedRepair.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateBadPair);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.SendMessageFailedNeedNewSession.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.SendMessageFailed.equals(targetStateManager$TargetContextEvent)) {
                            this.scheduleRetry(TargetStateManager$TargetContextEvent.SessionRetry);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.SessionRetry.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateSendingMessage);
                            return;
                        }
                        if (TargetStateManager$TargetContextEvent.Timeout.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateSendingMessage);
                            return;
                        }
                        break;
                    }
                    break;
                }
                case 12: {
                    if (TargetStateManager$TargetContextEvent.SessionCommandReceived.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.LaunchFailed.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateNotLaunched);
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.LaunchSucceed.equals(targetStateManager$TargetContextEvent)) {
                        this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                        return;
                    }
                    break;
                }
                case 13:
                case 14:
                case 15: {
                    if (TargetStateManager$TargetContextEvent.SessionEnd.equals(targetStateManager$TargetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (TargetStateManager$TargetContextEvent.SessionCommandReceived.equals(targetStateManager$TargetContextEvent)) {
                        if (this.mPreviousState != null) {
                            this.transitionStateTo(this.mPreviousState);
                            this.mListener.scheduleEvent(TargetStateManager$TargetContextEvent.SessionCommandReceived, 0);
                            return;
                        }
                        break;
                    }
                    else {
                        if (TargetStateManager$TargetContextEvent.LaunchSucceed.equals(targetStateManager$TargetContextEvent)) {
                            this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                            return;
                        }
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public void restart(final int n) {
        this.mCurrentState = TargetStateManager$TargetState.StateLaunched;
        this.mRetryCurrentAction = this.mCurrentState.getRetry();
        this.mHasUiCommand = false;
        this.mPreviousLaunchAttemptMs.set(0L);
        this.start(this.mIsPreviouslyPaired, this.mRegistrationAcceptance, this.mActivated, n);
        ++this.mStateMachineResetCountSince;
    }
    
    public void setDefaultAction(final TargetStateManager$StateId targetStateManager$StateId, final Runnable runnable) {
        this.mDefaultAction.put(targetStateManager$StateId, runnable);
    }
    
    public void start(final boolean mIsPreviouslyPaired, final int mRegistrationAcceptance, final boolean mActivated, final int n) {
        this.mIsPreviouslyPaired = mIsPreviouslyPaired;
        this.mRegistrationAcceptance = mRegistrationAcceptance;
        this.mActivated = mActivated;
        this.mLaunched = (n != 0);
        this.mPreviousLaunchAttemptMs.set(0L);
        if (this.mCurrentState.getId() == TargetStateManager$StateId.StateNotLaunched) {
            if (this.mIsTargetSelected) {
                this.mPreviousLaunchAttemptMs.set(System.currentTimeMillis());
                this.transitionStateTo(TargetStateManager$TargetState.StateNeedLaunched);
            }
        }
        else if (this.mCurrentState.getId() == TargetStateManager$StateId.StateLaunched) {
            if (this.mIsPreviouslyPaired) {
                this.transitionStateTo(TargetStateManager$TargetState.StateHasPair);
                return;
            }
            this.transitionStateTo(TargetStateManager$TargetState.StateNoPair);
        }
        else if (Log.isLoggable()) {
            Log.d("nf_mdx", "StateMachine: init state is not handled " + this.mCurrentState.getName());
        }
    }
    
    public void updateTarget(final boolean mIsPreviouslyPaired, final int mRegistrationAcceptance, final boolean mActivated, final int n) {
        this.mIsPreviouslyPaired = mIsPreviouslyPaired;
        this.mRegistrationAcceptance = mRegistrationAcceptance;
        this.mActivated = mActivated;
        this.mLaunched = (n != 0);
    }
}
