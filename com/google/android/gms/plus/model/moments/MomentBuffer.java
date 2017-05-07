// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.moments;

import com.google.android.gms.internal.nx;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class MomentBuffer extends DataBuffer<Moment>
{
    public MomentBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    public Moment get(final int n) {
        return new nx(this.IC, n);
    }
}
