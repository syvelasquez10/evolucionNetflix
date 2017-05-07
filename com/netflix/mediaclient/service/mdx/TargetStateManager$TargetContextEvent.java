// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

public enum TargetStateManager$TargetContextEvent
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
