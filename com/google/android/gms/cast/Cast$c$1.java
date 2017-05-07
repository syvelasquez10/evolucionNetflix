// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;

class Cast$c$1 implements Cast$ApplicationConnectionResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ Cast$c EQ;
    
    Cast$c$1(final Cast$c eq, final Status cw) {
        this.EQ = eq;
        this.CW = cw;
    }
    
    @Override
    public ApplicationMetadata getApplicationMetadata() {
        return null;
    }
    
    @Override
    public String getApplicationStatus() {
        return null;
    }
    
    @Override
    public String getSessionId() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public boolean getWasLaunched() {
        return false;
    }
}
