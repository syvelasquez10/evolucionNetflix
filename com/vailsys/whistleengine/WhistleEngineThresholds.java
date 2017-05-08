// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

public class WhistleEngineThresholds
{
    private WhistleEngineThresholds$Threshold jitter;
    private WhistleEngineThresholds$Threshold packetLoss;
    private WhistleEngineThresholds$Threshold rtt;
    private WhistleEngineThresholds$Threshold sip;
    
    public WhistleEngineThresholds() {
        this.sip = new WhistleEngineThresholds$Threshold(this);
        this.rtt = new WhistleEngineThresholds$Threshold(this);
        this.jitter = new WhistleEngineThresholds$Threshold(this);
        this.packetLoss = new WhistleEngineThresholds$Threshold(this);
    }
    
    public WhistleEngineThresholds$Threshold getJitterThreshold() {
        return this.jitter;
    }
    
    public WhistleEngineThresholds$Threshold getPacketLossThreshold() {
        return this.packetLoss;
    }
    
    public WhistleEngineThresholds$Threshold getRTTThreshold() {
        return this.rtt;
    }
    
    public WhistleEngineThresholds$Threshold getSIPThreshold() {
        return this.sip;
    }
    
    public void setJitterThreshold(final int n, final int n2) {
        this.jitter.setYellow(n);
        this.jitter.setRed(n2);
    }
    
    public void setPacketLossThreshold(final int n, final int n2) {
        this.packetLoss.setYellow(n);
        this.packetLoss.setRed(n2);
    }
    
    public void setRTTThreshold(final int n, final int n2) {
        this.rtt.setYellow(n);
        this.rtt.setRed(n2);
    }
    
    public void setSIPThreshold(final int n, final int n2) {
        this.sip.setYellow(n);
        this.sip.setRed(n2);
    }
}
