// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class EndCompoundOperationRequest implements SafeParcelable
{
    public static final Parcelable$Creator<EndCompoundOperationRequest> CREATOR;
    final int BR;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    public EndCompoundOperationRequest() {
        this(1);
    }
    
    EndCompoundOperationRequest(final int br) {
        this.BR = br;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
