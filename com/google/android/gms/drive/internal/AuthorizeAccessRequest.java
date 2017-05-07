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
    final long EU;
    final DriveId Ew;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    AuthorizeAccessRequest(final int xh, final long eu, final DriveId ew) {
        this.xH = xh;
        this.EU = eu;
        this.Ew = ew;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
