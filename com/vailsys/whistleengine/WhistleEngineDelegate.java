// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

public interface WhistleEngineDelegate
{
    void authenticationNeeded(final boolean p0);
    
    void callConnected(final int p0);
    
    void callDisconnected(final int p0);
    
    void callEnded(final int p0);
    
    void callFailed(final int p0, final String p1, final int p2);
    
    void callRinging(final int p0);
    
    void callSecured(final int p0, final boolean p1);
    
    void connectivityUpdate(final int p0, final WhistleEngineDelegate$ConnectivityState p1);
    
    void engineNotReady();
    
    void engineReady();
    
    void incomingCall(final int p0, final String p1, final String p2);
    
    void networkFailure(final int p0);
    
    void registrationSuccessful();
    
    void selectedCodec(final int p0, final String p1, final int p2);
}
