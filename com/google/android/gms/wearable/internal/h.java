// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.common.data.d;

public final class h extends d implements DataEvent
{
    private final int aaz;
    
    public h(final DataHolder dataHolder, final int n, final int aaz) {
        super(dataHolder, n);
        this.aaz = aaz;
    }
    
    @Override
    public DataItem getDataItem() {
        return new o(this.IC, this.JQ, this.aaz);
    }
    
    @Override
    public int getType() {
        return this.getInteger("event_type");
    }
    
    public DataEvent pU() {
        return new g(this);
    }
}
