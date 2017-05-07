// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.appstate.AppStateManager$StateDeletedResult;

final class ib$b implements AppStateManager$StateDeletedResult
{
    private final Status CM;
    private final int Df;
    
    public ib$b(final Status cm, final int df) {
        this.CM = cm;
        this.Df = df;
    }
    
    @Override
    public int getStateKey() {
        return this.Df;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
