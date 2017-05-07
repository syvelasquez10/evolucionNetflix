// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class BeginCompoundOperationRequest implements SafeParcelable
{
    public static final Parcelable$Creator<BeginCompoundOperationRequest> CREATOR;
    final int BR;
    final boolean Ra;
    final boolean Rb;
    final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    BeginCompoundOperationRequest(final int br, final boolean ra, final String mName, final boolean rb) {
        this.BR = br;
        this.Ra = ra;
        this.mName = mName;
        this.Rb = rb;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
