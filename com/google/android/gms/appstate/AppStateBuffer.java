// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class AppStateBuffer extends DataBuffer<AppState>
{
    public AppStateBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    public AppState get(final int n) {
        return new b(this.BB, n);
    }
}
