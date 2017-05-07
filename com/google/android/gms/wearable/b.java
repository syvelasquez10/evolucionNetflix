// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.internal.kc;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.d;

public class b extends d<a> implements Result
{
    private final Status wJ;
    
    public b(final DataHolder dataHolder) {
        super(dataHolder);
        this.wJ = new Status(dataHolder.getStatusCode());
    }
    
    protected a g(final int n, final int n2) {
        return new kc(this.BB, n, n2);
    }
    
    @Override
    protected String getPrimaryDataMarkerColumn() {
        return "path";
    }
    
    @Override
    public Status getStatus() {
        return this.wJ;
    }
}
