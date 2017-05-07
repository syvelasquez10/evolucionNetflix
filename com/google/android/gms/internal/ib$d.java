// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.appstate.AppStateBuffer;
import com.google.android.gms.appstate.AppStateManager$StateListResult;
import com.google.android.gms.common.api.a;

final class ib$d extends a implements AppStateManager$StateListResult
{
    private final AppStateBuffer Dg;
    
    public ib$d(final DataHolder dataHolder) {
        super(dataHolder);
        this.Dg = new AppStateBuffer(dataHolder);
    }
    
    @Override
    public AppStateBuffer getStateBuffer() {
        return this.Dg;
    }
}
