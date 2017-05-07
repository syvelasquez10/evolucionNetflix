// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class TrashResourceRequest implements SafeParcelable
{
    public static final Parcelable$Creator<TrashResourceRequest> CREATOR;
    final int BR;
    final DriveId NV;
    
    static {
        CREATOR = (Parcelable$Creator)new bc();
    }
    
    TrashResourceRequest(final int br, final DriveId nv) {
        this.BR = br;
        this.NV = nv;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        bc.a(this, parcel, n);
    }
}
