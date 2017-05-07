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
    final int kg;
    DriveId rr;
    
    static {
        CREATOR = (Parcelable$Creator)new t();
    }
    
    OnDriveIdResponse(final int kg, final DriveId rr) {
        this.kg = kg;
        this.rr = rr;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DriveId getDriveId() {
        return this.rr;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        t.a(this, parcel, n);
    }
}
