// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItemAsset;

public class i implements DataItemAsset
{
    private final String BL;
    private final String JH;
    
    public i(final DataItemAsset dataItemAsset) {
        this.BL = dataItemAsset.getId();
        this.JH = dataItemAsset.getDataItemKey();
    }
    
    @Override
    public String getDataItemKey() {
        return this.JH;
    }
    
    @Override
    public String getId() {
        return this.BL;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public DataItemAsset pV() {
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DataItemAssetEntity[");
        sb.append("@");
        sb.append(Integer.toHexString(this.hashCode()));
        if (this.BL == null) {
            sb.append(",noid");
        }
        else {
            sb.append(",");
            sb.append(this.BL);
        }
        sb.append(", key=");
        sb.append(this.JH);
        sb.append("]");
        return sb.toString();
    }
}
