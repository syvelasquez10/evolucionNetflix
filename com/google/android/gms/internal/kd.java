// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.wearable.d;

public class kd implements d
{
    private final String Xy;
    private final String wp;
    
    public kd(final d d) {
        this.wp = d.getId();
        this.Xy = d.mc();
    }
    
    @Override
    public String getId() {
        return this.wp;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String mc() {
        return this.Xy;
    }
    
    public d mf() {
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DataItemAssetEntity[");
        sb.append("@");
        sb.append(Integer.toHexString(this.hashCode()));
        if (this.wp == null) {
            sb.append(",noid");
        }
        else {
            sb.append(",");
            sb.append(this.wp);
        }
        sb.append(", key=");
        sb.append(this.Xy);
        sb.append("]");
        return sb.toString();
    }
}
