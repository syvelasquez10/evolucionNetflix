// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AuthorizeAccessRequest implements SafeParcelable
{
    public static final Parcelable$Creator<AuthorizeAccessRequest> CREATOR;
    final int BR;
    final DriveId MO;
    final long NT;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    AuthorizeAccessRequest(final int br, final long nt, final DriveId mo) {
        this.BR = br;
        this.NT = nt;
        this.MO = mo;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
