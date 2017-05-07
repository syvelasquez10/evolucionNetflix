// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.StorageStats;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnStorageStatsResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnStorageStatsResponse> CREATOR;
    final int BR;
    StorageStats Po;
    
    static {
        CREATOR = (Parcelable$Creator)new as();
    }
    
    OnStorageStatsResponse(final int br, final StorageStats po) {
        this.BR = br;
        this.Po = po;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        as.a(this, parcel, n);
    }
}
