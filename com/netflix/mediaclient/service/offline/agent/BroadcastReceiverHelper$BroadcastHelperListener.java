// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

interface BroadcastReceiverHelper$BroadcastHelperListener
{
    void onAccountDataFetched();
    
    void onStreamingPlayStartReceived();
    
    void onStreamingPlayStopReceived();
    
    void onUserAccountActive();
    
    void onUserAccountInActive();
}
