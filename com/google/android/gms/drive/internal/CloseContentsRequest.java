// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CloseContentsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CloseContentsRequest> CREATOR;
    final int BR;
    final Contents NX;
    final Boolean NZ;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    CloseContentsRequest(final int br, final Contents nx, final Boolean nz) {
        this.BR = br;
        this.NX = nx;
        this.NZ = nz;
    }
    
    public CloseContentsRequest(final Contents contents, final boolean b) {
        this(1, contents, b);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
