// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataEvent;

public class g implements DataEvent
{
    private int FD;
    private DataItem avh;
    
    public g(final DataEvent dataEvent) {
        this.FD = dataEvent.getType();
        this.avh = dataEvent.getDataItem().freeze();
    }
    
    @Override
    public DataItem getDataItem() {
        return this.avh;
    }
    
    @Override
    public int getType() {
        return this.FD;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public DataEvent pU() {
        return this;
    }
}
