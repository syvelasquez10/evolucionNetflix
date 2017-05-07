// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetMetadataRequest implements SafeParcelable
{
    public static final Parcelable$Creator<GetMetadataRequest> CREATOR;
    final DriveId EV;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new t();
    }
    
    GetMetadataRequest(final int xh, final DriveId ev) {
        this.xH = xh;
        this.EV = ev;
    }
    
    public GetMetadataRequest(final DriveId driveId) {
        this(1, driveId);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        t.a(this, parcel, n);
    }
}
