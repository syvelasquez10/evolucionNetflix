// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Moments$LoadMomentsResult;

class nr$a$1 implements Moments$LoadMomentsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ nr$a alK;
    
    nr$a$1(final nr$a alK, final Status cw) {
        this.alK = alK;
        this.CW = cw;
    }
    
    @Override
    public MomentBuffer getMomentBuffer() {
        return null;
    }
    
    @Override
    public String getNextPageToken() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public String getUpdated() {
        return null;
    }
    
    @Override
    public void release() {
    }
}
