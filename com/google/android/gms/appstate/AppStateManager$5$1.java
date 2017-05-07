// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Status;

class AppStateManager$5$1 implements AppStateManager$StateDeletedResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ AppStateManager$5 CZ;
    
    AppStateManager$5$1(final AppStateManager$5 cz, final Status cw) {
        this.CZ = cz;
        this.CW = cw;
    }
    
    @Override
    public int getStateKey() {
        return this.CZ.CX;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
