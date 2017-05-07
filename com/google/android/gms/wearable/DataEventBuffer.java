// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.h;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.g;

public class DataEventBuffer extends g<DataEvent> implements Result
{
    private final Status CM;
    
    public DataEventBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        this.CM = new Status(dataHolder.getStatusCode());
    }
    
    @Override
    protected String gE() {
        return "path";
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    protected DataEvent p(final int n, final int n2) {
        return new h(this.IC, n, n2);
    }
}
