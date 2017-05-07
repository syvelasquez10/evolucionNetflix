// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OpenContentsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<OpenContentsRequest> CREATOR;
    final int BR;
    final int MN;
    final DriveId NV;
    final int Pp;
    
    static {
        CREATOR = (Parcelable$Creator)new au();
    }
    
    OpenContentsRequest(final int br, final DriveId nv, final int mn, final int pp) {
        this.BR = br;
        this.NV = nv;
        this.MN = mn;
        this.Pp = pp;
    }
    
    public OpenContentsRequest(final DriveId driveId, final int n, final int n2) {
        this(1, driveId, n, n2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        au.a(this, parcel, n);
    }
}
