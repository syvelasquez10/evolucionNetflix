// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetDriveIdFromUniqueIdentifierRequest implements SafeParcelable
{
    public static final Parcelable$Creator<GetDriveIdFromUniqueIdentifierRequest> CREATOR;
    final int BR;
    final String OZ;
    final boolean Pa;
    
    static {
        CREATOR = (Parcelable$Creator)new z();
    }
    
    GetDriveIdFromUniqueIdentifierRequest(final int br, final String oz, final boolean pa) {
        this.BR = br;
        this.OZ = oz;
        this.Pa = pa;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        z.a(this, parcel, n);
    }
}
