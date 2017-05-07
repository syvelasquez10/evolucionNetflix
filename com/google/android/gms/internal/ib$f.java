// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.appstate.AppStateBuffer;
import com.google.android.gms.appstate.AppStateManager$StateResult;
import com.google.android.gms.appstate.AppStateManager$StateLoadedResult;
import com.google.android.gms.appstate.AppStateManager$StateConflictResult;
import com.google.android.gms.common.api.a;

final class ib$f extends a implements AppStateManager$StateConflictResult, AppStateManager$StateLoadedResult, AppStateManager$StateResult
{
    private final int Df;
    private final AppStateBuffer Dg;
    
    public ib$f(final int df, final DataHolder dataHolder) {
        super(dataHolder);
        this.Df = df;
        this.Dg = new AppStateBuffer(dataHolder);
    }
    
    private boolean ft() {
        return this.CM.getStatusCode() == 2000;
    }
    
    @Override
    public AppStateManager$StateConflictResult getConflictResult() {
        if (this.ft()) {
            return this;
        }
        return null;
    }
    
    @Override
    public AppStateManager$StateLoadedResult getLoadedResult() {
        ib$f ib$f = this;
        if (this.ft()) {
            ib$f = null;
        }
        return ib$f;
    }
    
    @Override
    public byte[] getLocalData() {
        if (this.Dg.getCount() == 0) {
            return null;
        }
        return this.Dg.get(0).getLocalData();
    }
    
    @Override
    public String getResolvedVersion() {
        if (this.Dg.getCount() == 0) {
            return null;
        }
        return this.Dg.get(0).getConflictVersion();
    }
    
    @Override
    public byte[] getServerData() {
        if (this.Dg.getCount() == 0) {
            return null;
        }
        return this.Dg.get(0).getConflictData();
    }
    
    @Override
    public int getStateKey() {
        return this.Df;
    }
    
    @Override
    public void release() {
        this.Dg.close();
    }
}
