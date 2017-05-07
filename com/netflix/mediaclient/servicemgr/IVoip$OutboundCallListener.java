// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface IVoip$OutboundCallListener
{
    void callConnected(final IVoip$Call p0);
    
    void callDisconnected(final IVoip$Call p0);
    
    void callEnded(final IVoip$Call p0);
    
    void callFailed(final IVoip$Call p0, final String p1, final int p2);
    
    void callRinging(final IVoip$Call p0);
    
    void engineStatusChanged(final boolean p0);
}
