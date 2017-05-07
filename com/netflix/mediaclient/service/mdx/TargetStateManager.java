// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.Log;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class TargetStateManager
{
    private static final int DEFAULT_RETRY_INTERNAL = 1000;
    private static final int LAUNCH_TIMEOUT = 40000;
    private static final int PAIRING_RETRY_INTERVAL = 3000;
    private static final int PAIRING_TIMEOUT = 24000;
    private static final int REGPAIRING_RETRY_INTERVAL = 4000;
    private static final int REGPAIRING_TIMEOUT = 32000;
    private static final int SESSION_RETRY_INTERVAL = 1000;
    private static final int STATE_TIMEOUT = 8000;
    private static final String TAG = "nf_mdx";
    private boolean mActivated;
    TargetState mCurrentState;
    private Map<StateId, Runnable> mDefaultAction;
    private boolean mHasUiCommand;
    private boolean mIsPreviouslyPaired;
    private boolean mIsTargetSelected;
    private boolean mLaunched;
    private TargetStateManagerListener mListener;
    TargetState mPreviousState;
    private int mRegistrationAcceptance;
    int mRetryCurrentAction;
    int mRetryCurrentInterval;
    private ArrayList<Runnable> mSessionRequested;
    
    TargetStateManager(final TargetStateManagerListener mListener, final TargetState mCurrentState, final boolean mIsTargetSelected) {
        this.mSessionRequested = new ArrayList<Runnable>();
        this.mDefaultAction = new HashMap<StateId, Runnable>();
        if (Log.isLoggable("nf_mdx", 3)) {
            Log.d("nf_mdx", "StateMachine: init state " + mCurrentState.getName());
        }
        this.mListener = mListener;
        this.mIsTargetSelected = mIsTargetSelected;
        this.mCurrentState = mCurrentState;
        this.mRetryCurrentAction = mCurrentState.getRetry();
        this.mHasUiCommand = false;
    }
    
    private void scheduleRetry(final TargetContextEvent targetContextEvent) {
        if (Log.isLoggable("nf_mdx", 3)) {
            Log.d("nf_mdx", "TargetStateManager: schedule retry for " + this.mCurrentState.mName + " in " + this.mRetryCurrentInterval);
        }
        this.mListener.scheduleEvent(targetContextEvent, this.mRetryCurrentInterval);
    }
    
    private void sessionEnded() {
        this.mHasUiCommand = false;
        this.mSessionRequested.clear();
        this.transitionStateTo(TargetState.StateSessionEnd);
    }
    
    private void transitionStateTo(final TargetState mCurrentState) {
        if (Log.isLoggable("nf_mdx", 3)) {
            Log.d("nf_mdx", "TargetStateManager: from " + this.mCurrentState.mName + " to " + mCurrentState.mName);
        }
        if (this.mCurrentState == mCurrentState) {
            if (this.mRetryCurrentAction <= 0) {
                this.transitionStateTo(TargetState.StateRetryExhausted);
                this.mListener.stateHasExhaustedRetry(this.mPreviousState);
                return;
            }
            --this.mRetryCurrentAction;
            this.mRetryCurrentInterval += this.mRetryCurrentInterval;
            this.mListener.removeEvents(TargetContextEvent.Timeout);
            this.mListener.removeEvents(TargetContextEvent.SessionRetry);
            this.mListener.removeEvents(TargetContextEvent.PairingRetry);
        }
        else {
            this.mListener.removeEvents(TargetContextEvent.Timeout);
            this.mListener.removeEvents(TargetContextEvent.SessionRetry);
            this.mListener.removeEvents(TargetContextEvent.PairingRetry);
            this.mPreviousState = this.mCurrentState;
            this.mCurrentState = mCurrentState;
            this.mRetryCurrentAction = this.mCurrentState.getRetry();
            this.mRetryCurrentInterval = this.mCurrentState.getRetryInterval();
        }
        final Runnable runnable = this.mDefaultAction.get(this.mCurrentState.getId());
        if (runnable != null) {
            runnable.run();
            if (this.mCurrentState.getTimeOut() > 0) {
                this.mListener.scheduleEvent(TargetContextEvent.Timeout, this.mCurrentState.getTimeOut());
            }
        }
    }
    
    public void addSessionRequest(final Runnable runnable) {
        this.mSessionRequested.add(runnable);
    }
    
    public void addUiCommand(final Runnable runnable) {
        this.mHasUiCommand = true;
        this.mSessionRequested.add(runnable);
    }
    
    public boolean isSessionActive() {
        return TargetState.StateSessionReady.equals(this.mCurrentState) || TargetState.StateSendingMessage.equals(this.mCurrentState);
    }
    
    public void receivedEvent(final TargetContextEvent targetContextEvent) {
        if (TargetContextEvent.LaunchFailed.equals(targetContextEvent) && this.mCurrentState.getId() != StateId.StateNeedLaunched) {
            this.transitionStateTo(TargetState.StateNotLaunched);
            this.mLaunched = false;
        }
        else {
            switch (this.mCurrentState.getId()) {
                case StateLaunched: {
                    break;
                }
                default: {}
                case StateNotLaunched: {
                    if (TargetContextEvent.StartTarget.equals(targetContextEvent) || TargetContextEvent.SessionCommandReceived.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateNeedLaunched);
                        return;
                    }
                    if ((!TargetContextEvent.TargetUpdate.equals(targetContextEvent) || !this.mLaunched) && !TargetContextEvent.LaunchSucceed.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateNotLaunched);
                        return;
                    }
                    if (this.mIsPreviouslyPaired) {
                        this.transitionStateTo(TargetState.StateHasPair);
                        return;
                    }
                    this.transitionStateTo(TargetState.StateNoPair);
                }
                case StateNeedLaunched: {
                    if (TargetContextEvent.LaunchSucceed.equals(targetContextEvent)) {
                        if (this.mIsPreviouslyPaired) {
                            this.transitionStateTo(TargetState.StateHasPair);
                            return;
                        }
                        if (!this.mActivated) {
                            this.transitionStateTo(TargetState.StateNeedRegPair);
                            return;
                        }
                        this.transitionStateTo(TargetState.StateNoPair);
                        return;
                    }
                    else {
                        if (TargetContextEvent.LaunchFailed.equals(targetContextEvent)) {
                            this.scheduleRetry(TargetContextEvent.LaunchRetry);
                            return;
                        }
                        if (TargetContextEvent.LaunchRetry.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateNeedLaunched);
                            return;
                        }
                        if (TargetContextEvent.TargetUpdate.equals(targetContextEvent) && this.mLaunched) {
                            if (this.mIsPreviouslyPaired) {
                                this.transitionStateTo(TargetState.StateHasPair);
                                return;
                            }
                            this.transitionStateTo(TargetState.StateNoPair);
                            return;
                        }
                        else {
                            if (TargetContextEvent.Timeout.equals(targetContextEvent)) {
                                this.transitionStateTo(TargetState.StateTimeout);
                                this.mListener.stateHasTimedOut(this.mPreviousState);
                                return;
                            }
                            break;
                        }
                    }
                    break;
                }
                case StateHasPair: {
                    if (TargetContextEvent.StartSessionSucceed.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateNeedHandShake);
                        return;
                    }
                    if (TargetContextEvent.SendMessageFailedNeedRepair.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateBadPair);
                        return;
                    }
                    if (TargetContextEvent.SendMessageFailed.equals(targetContextEvent) || TargetContextEvent.SendMessageFailedNeedNewSession.equals(targetContextEvent)) {
                        this.scheduleRetry(TargetContextEvent.SessionRetry);
                        return;
                    }
                    if (TargetContextEvent.SessionRetry.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateHasPair);
                        return;
                    }
                    if (TargetContextEvent.Timeout.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateHasPair);
                        return;
                    }
                    break;
                }
                case StateBadPair: {
                    if (TargetContextEvent.DeletePairSucceed.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateNoPair);
                        return;
                    }
                    if (TargetContextEvent.Timeout.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateTimeout);
                        this.mListener.stateHasTimedOut(this.mPreviousState);
                        return;
                    }
                    break;
                }
                case StateNoPair: {
                    if (TargetContextEvent.PairSucceed.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateHasPair);
                        return;
                    }
                    if (TargetContextEvent.PairFailedNeedRegPair.equals(targetContextEvent)) {
                        if (this.mRegistrationAcceptance != 0 && this.mHasUiCommand) {
                            this.transitionStateTo(TargetState.StateNeedRegPair);
                            return;
                        }
                        if (this.mRegistrationAcceptance != 0) {
                            this.transitionStateTo(TargetState.StateNoPairNeedRegPair);
                            return;
                        }
                        this.transitionStateTo(TargetState.StateHasError);
                        this.mListener.stateHasError(this.mPreviousState);
                        return;
                    }
                    else {
                        if (TargetContextEvent.PairFailedExistedPair.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateBadPair);
                            return;
                        }
                        if (TargetContextEvent.PairFailed.equals(targetContextEvent)) {
                            this.scheduleRetry(TargetContextEvent.PairingRetry);
                            return;
                        }
                        if (TargetContextEvent.PairingRetry.equals(targetContextEvent) || TargetContextEvent.RegistrationInProgress.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateNoPair);
                            return;
                        }
                        if (TargetContextEvent.Timeout.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateTimeout);
                            this.mListener.stateHasTimedOut(this.mPreviousState);
                            return;
                        }
                        break;
                    }
                    break;
                }
                case StateNoPairNeedRegPair: {
                    if (TargetContextEvent.SessionCommandReceived.equals(targetContextEvent) && this.mRegistrationAcceptance != 0) {
                        this.transitionStateTo(TargetState.StateNeedRegPair);
                        return;
                    }
                    break;
                }
                case StateNeedRegPair: {
                    if (TargetContextEvent.PairSucceed.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateHasPair);
                        return;
                    }
                    if (TargetContextEvent.PairFailedExistedPair.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateBadPair);
                        return;
                    }
                    if (TargetContextEvent.Timeout.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateTimeout);
                        this.mListener.stateHasTimedOut(this.mPreviousState);
                        return;
                    }
                    if (TargetContextEvent.PairFailed.equals(targetContextEvent) || TargetContextEvent.PairFailedNeedRegPair.equals(targetContextEvent)) {
                        this.scheduleRetry(TargetContextEvent.PairingRetry);
                        return;
                    }
                    if (TargetContextEvent.PairingRetry.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateNeedRegPair);
                        return;
                    }
                    if (TargetContextEvent.RegistrationInProgress.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateNoPair);
                        return;
                    }
                    break;
                }
                case StateNeedHandShake: {
                    if (TargetContextEvent.SessionEnd.equals(targetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (TargetContextEvent.HandShakeSucceed.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateSessionReady);
                        if (!this.mSessionRequested.isEmpty()) {
                            this.mListener.scheduleEvent(TargetContextEvent.SessionCommandReceived, 0);
                            return;
                        }
                        break;
                    }
                    else {
                        if (TargetContextEvent.HandShakeFailed.equals(targetContextEvent)) {
                            this.scheduleRetry(TargetContextEvent.SessionRetry);
                            return;
                        }
                        if (TargetContextEvent.SessionRetry.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateNeedHandShake);
                            return;
                        }
                        if (TargetContextEvent.Timeout.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateNeedHandShake);
                            return;
                        }
                        break;
                    }
                    break;
                }
                case StateSessionReady: {
                    if (TargetContextEvent.SessionEnd.equals(targetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (!TargetContextEvent.SessionCommandReceived.equals(targetContextEvent)) {
                        break;
                    }
                    if (!this.mSessionRequested.isEmpty()) {
                        this.setDefaultAction(StateId.StateSendingMessage, this.mSessionRequested.remove(0));
                        this.transitionStateTo(TargetState.StateSendingMessage);
                        return;
                    }
                    Log.e("nf_mdx", "StateMachine: SessionCommandReceived, but no task!");
                }
                case StateSendingMessage: {
                    if (TargetContextEvent.SessionEnd.equals(targetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (TargetContextEvent.SendMessageSucceed.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateSessionReady);
                        if (!this.mSessionRequested.isEmpty()) {
                            this.mListener.scheduleEvent(TargetContextEvent.SessionCommandReceived, 0);
                            return;
                        }
                        break;
                    }
                    else {
                        if (TargetContextEvent.SendMessageFailedNeedRepair.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateBadPair);
                            return;
                        }
                        if (TargetContextEvent.SendMessageFailedNeedNewSession.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateHasPair);
                            return;
                        }
                        if (TargetContextEvent.SendMessageFailed.equals(targetContextEvent)) {
                            this.scheduleRetry(TargetContextEvent.SessionRetry);
                            return;
                        }
                        if (TargetContextEvent.SessionRetry.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateSendingMessage);
                            return;
                        }
                        if (TargetContextEvent.Timeout.equals(targetContextEvent)) {
                            this.transitionStateTo(TargetState.StateSendingMessage);
                            return;
                        }
                        break;
                    }
                    break;
                }
                case StateSessionEnd: {
                    if (TargetContextEvent.SessionCommandReceived.equals(targetContextEvent)) {
                        this.transitionStateTo(TargetState.StateHasPair);
                        return;
                    }
                    break;
                }
                case StateRetryExhausted: {
                    if (TargetContextEvent.SessionEnd.equals(targetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (TargetContextEvent.SessionCommandReceived.equals(targetContextEvent) && this.mPreviousState != null) {
                        this.transitionStateTo(this.mPreviousState);
                        return;
                    }
                    break;
                }
                case StateTimeout: {
                    if (TargetContextEvent.SessionEnd.equals(targetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (TargetContextEvent.SessionCommandReceived.equals(targetContextEvent) && this.mPreviousState != null) {
                        this.transitionStateTo(this.mPreviousState);
                        return;
                    }
                    break;
                }
                case StateHasError: {
                    if (TargetContextEvent.SessionEnd.equals(targetContextEvent)) {
                        this.sessionEnded();
                        return;
                    }
                    if (TargetContextEvent.SessionCommandReceived.equals(targetContextEvent) && this.mPreviousState != null) {
                        this.transitionStateTo(this.mPreviousState);
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public void setDefaultAction(final StateId stateId, final Runnable runnable) {
        this.mDefaultAction.put(stateId, runnable);
    }
    
    public void start(final boolean mIsPreviouslyPaired, final int mRegistrationAcceptance, final boolean mActivated, final int n) {
        this.mIsPreviouslyPaired = mIsPreviouslyPaired;
        this.mRegistrationAcceptance = mRegistrationAcceptance;
        this.mActivated = mActivated;
        this.mLaunched = (n != 0);
        if (this.mCurrentState.getId() == StateId.StateNotLaunched) {
            if (this.mIsTargetSelected) {
                this.transitionStateTo(TargetState.StateNeedLaunched);
            }
        }
        else if (this.mCurrentState.getId() == StateId.StateLaunched) {
            if (this.mIsPreviouslyPaired) {
                this.transitionStateTo(TargetState.StateHasPair);
                return;
            }
            this.transitionStateTo(TargetState.StateNoPair);
        }
        else if (Log.isLoggable("nf_mdx", 3)) {
            Log.d("nf_mdx", "StateMachine: init state is not handled " + this.mCurrentState.getName());
        }
    }
    
    public void updateTarget(final boolean mIsPreviouslyPaired, final int mRegistrationAcceptance, final boolean mActivated, final int n) {
        this.mIsPreviouslyPaired = mIsPreviouslyPaired;
        this.mRegistrationAcceptance = mRegistrationAcceptance;
        this.mActivated = mActivated;
        this.mLaunched = (n != 0);
    }
    
    public enum StateId
    {
        StateBadPair, 
        StateHasError, 
        StateHasPair, 
        StateLaunched, 
        StateNeedHandShake, 
        StateNeedLaunched, 
        StateNeedRegPair, 
        StateNoPair, 
        StateNoPairNeedRegPair, 
        StateNotLaunched, 
        StateRetryExhausted, 
        StateSendingMessage, 
        StateSessionEnd, 
        StateSessionReady, 
        StateTimeout;
    }
    
    public enum TargetContextEvent
    {
        DeletePairSucceed, 
        HandShakeFailed, 
        HandShakeSucceed, 
        LaunchFailed, 
        LaunchRetry, 
        LaunchSucceed, 
        PairFailed, 
        PairFailedExistedPair, 
        PairFailedNeedRegPair, 
        PairNotAllowed, 
        PairSucceed, 
        PairingRetry, 
        RegistrationInProgress, 
        SendMessageFailed, 
        SendMessageFailedNeedNewSession, 
        SendMessageFailedNeedRepair, 
        SendMessageSucceed, 
        SessionCommandReceived, 
        SessionEnd, 
        SessionRetry, 
        StartSessionSucceed, 
        StartTarget, 
        TargetUpdate, 
        Timeout;
    }
    
    public enum TargetState
    {
        StateBadPair(StateId.StateBadPair, "badpair", 0, 0, 1000), 
        StateHasError(StateId.StateHasError, "haserror", 0, 0, 1000), 
        StateHasPair(StateId.StateHasPair, "haspair", 2, 8000, 1000), 
        StateLaunched(StateId.StateLaunched, "launched", 0, 0, 1000), 
        StateNeedHandShake(StateId.StateNeedHandShake, "needhandshake", 2, 8000, 1000), 
        StateNeedLaunched(StateId.StateNeedLaunched, "needlaunch", 1, 40000, 7000), 
        StateNeedRegPair(StateId.StateNeedRegPair, "needregpair", 3, 32000, 4000), 
        StateNoPair(StateId.StateNoPair, "nopair", 3, 24000, 3000), 
        StateNoPairNeedRegPair(StateId.StateNoPairNeedRegPair, "nopairneedregpair", 0, 0, 1000), 
        StateNotLaunched(StateId.StateNotLaunched, "notlaunched", 0, 0, 1000), 
        StateRetryExhausted(StateId.StateRetryExhausted, "retryexhausted", 0, 0, 1000), 
        StateSendingMessage(StateId.StateSendingMessage, "sendingmessage", 2, 8000, 1000), 
        StateSessionEnd(StateId.StateSessionEnd, "sessionend", 0, 8000, 1000), 
        StateSessionReady(StateId.StateSessionReady, "sessionready", 0, 0, 1000), 
        StateTimeout(StateId.StateTimeout, "timeout", 0, 0, 1000);
        
        private int mBaseRetryIntreval;
        private StateId mId;
        private String mName;
        private int mRetry;
        private int mTimeOut;
        
        private TargetState(final StateId mId, final String mName, final int mRetry, final int mTimeOut, final int mBaseRetryIntreval) {
            this.mRetry = 0;
            this.mBaseRetryIntreval = 0;
            this.mName = mName;
            this.mRetry = mRetry;
            this.mId = mId;
            this.mTimeOut = mTimeOut;
            this.mBaseRetryIntreval = mBaseRetryIntreval;
        }
        
        public StateId getId() {
            return this.mId;
        }
        
        public String getName() {
            return this.mName;
        }
        
        public int getRetry() {
            return this.mRetry;
        }
        
        public int getRetryInterval() {
            return this.mBaseRetryIntreval;
        }
        
        public int getTimeOut() {
            return this.mTimeOut;
        }
    }
    
    public interface TargetStateManagerListener
    {
        void removeEvents(final TargetContextEvent p0);
        
        void scheduleEvent(final TargetContextEvent p0, final int p1);
        
        void stateHasError(final TargetState p0);
        
        void stateHasExhaustedRetry(final TargetState p0);
        
        void stateHasTimedOut(final TargetState p0);
    }
}
