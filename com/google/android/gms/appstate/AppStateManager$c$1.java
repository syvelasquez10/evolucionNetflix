// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.Status;

class AppStateManager$c$1 implements AppStateManager$StateListResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ AppStateManager$c Dc;
    
    AppStateManager$c$1(final AppStateManager$c dc, final Status cw) {
        this.Dc = dc;
        this.CW = cw;
    }
    
    @Override
    public AppStateBuffer getStateBuffer() {
        return new AppStateBuffer(null);
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
