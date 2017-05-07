// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ReferenceShiftedDetails implements SafeParcelable
{
    public static final Parcelable$Creator<ReferenceShiftedDetails> CREATOR;
    final int BR;
    final String RA;
    final int RB;
    final int RC;
    final String Rz;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    ReferenceShiftedDetails(final int br, final String rz, final String ra, final int rb, final int rc) {
        this.BR = br;
        this.Rz = rz;
        this.RA = ra;
        this.RB = rb;
        this.RC = rc;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
