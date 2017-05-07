// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.servicemgr.IVoip$CallState;
import com.netflix.mediaclient.servicemgr.IVoip$Call;

class WhistleVoipAgent$WhistleCall implements IVoip$Call
{
    private int line;
    private IVoip$CallState state;
    
    public WhistleVoipAgent$WhistleCall(final int line) {
        this.state = IVoip$CallState.CONNECTING;
        this.line = line;
    }
    
    @Override
    public int getId() {
        return this.line;
    }
    
    @Override
    public IVoip$CallState getState() {
        return this.state;
    }
}
