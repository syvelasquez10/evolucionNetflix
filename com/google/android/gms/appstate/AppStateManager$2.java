// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Status;

final class AppStateManager$2 implements AppStateManager$StateResult
{
    final /* synthetic */ Status CW;
    
    AppStateManager$2(final Status cw) {
        this.CW = cw;
    }
    
    @Override
    public AppStateManager$StateConflictResult getConflictResult() {
        return null;
    }
    
    @Override
    public AppStateManager$StateLoadedResult getLoadedResult() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
