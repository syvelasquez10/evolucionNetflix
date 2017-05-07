// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface IVoip
{
    public static final String CATEGORY = "com.netflix.mediaclient.intent.category.VOIP";
    
    void addOutboundCallListener(final IVoip$OutboundCallListener p0);
    
    boolean dial();
    
    long getCallStartTimeInMs();
    
    IVoip$ConnectivityState getConnectivityState();
    
    IVoip$Call getCurrentCall();
    
    float getMicrophoneInputLevel();
    
    boolean isCallInProgress();
    
    boolean isConnected();
    
    boolean isEnabled();
    
    boolean isReady();
    
    boolean removeOutboundCallListener(final IVoip$OutboundCallListener p0);
    
    void setMicrophoneMute(final boolean p0);
    
    void setOutputVolume(final float p0);
    
    void setSpeakerOn(final boolean p0);
    
    boolean start();
    
    void startDTMF(final char p0);
    
    void stop();
    
    void stopDTMF();
    
    boolean terminate();
}
