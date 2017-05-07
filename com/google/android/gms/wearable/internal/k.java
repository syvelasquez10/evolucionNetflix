// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.common.data.d;

public class k extends d implements DataItemAsset
{
    public k(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    @Override
    public String getDataItemKey() {
        return this.getString("asset_key");
    }
    
    @Override
    public String getId() {
        return this.getString("asset_id");
    }
    
    public DataItemAsset pV() {
        return new i(this);
    }
}
