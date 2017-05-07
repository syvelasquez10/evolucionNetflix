// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class Asset$1 implements Parcelable$Creator<Asset>
{
    public Asset createFromParcel(final Parcel parcel) {
        return new Asset(parcel, null);
    }
    
    public Asset[] newArray(final int n) {
        return new Asset[n];
    }
}
