// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import android.os.Parcelable$Creator;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataItemAssetParcelable implements SafeParcelable, DataItemAsset
{
    public static final Parcelable$Creator<DataItemAssetParcelable> CREATOR;
    private final String BL;
    final int BR;
    private final String JH;
    
    static {
        CREATOR = (Parcelable$Creator)new j();
    }
    
    DataItemAssetParcelable(final int br, final String bl, final String jh) {
        this.BR = br;
        this.BL = bl;
        this.JH = jh;
    }
    
    public DataItemAssetParcelable(final DataItemAsset dataItemAsset) {
        this.BR = 1;
        this.BL = n.i(dataItemAsset.getId());
        this.JH = n.i(dataItemAsset.getDataItemKey());
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String getDataItemKey() {
        return this.JH;
    }
    
    @Override
    public String getId() {
        return this.BL;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    public DataItemAsset pV() {
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DataItemAssetParcelable[");
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
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
