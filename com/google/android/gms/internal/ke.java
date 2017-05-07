// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.d;
import com.google.android.gms.common.data.b;

public class ke extends b implements d
{
    public ke(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    @Override
    public String getId() {
        return this.getString("asset_id");
    }
    
    @Override
    public String mc() {
        return this.getString("asset_key");
    }
    
    public d mf() {
        return new kd(this);
    }
}
