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
    public Status getStatus() {
        return this.CW;
    }
}
