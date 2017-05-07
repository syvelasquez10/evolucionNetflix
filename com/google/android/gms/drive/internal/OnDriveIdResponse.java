// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnDriveIdResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnDriveIdResponse> CREATOR;
    DriveId EV;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new ab();
    }
    
    OnDriveIdResponse(final int xh, final DriveId ev) {
        this.xH = xh;
        this.EV = ev;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DriveId getDriveId() {
        return this.EV;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ab.a(this, parcel, n);
    }
}
