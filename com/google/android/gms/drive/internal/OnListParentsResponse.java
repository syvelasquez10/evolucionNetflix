// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.i;

public class OnListParentsResponse extends i implements SafeParcelable
{
    public static final Parcelable$Creator<OnListParentsResponse> CREATOR;
    final int BR;
    final DataHolder Pn;
    
    static {
        CREATOR = (Parcelable$Creator)new ao();
    }
    
    OnListParentsResponse(final int br, final DataHolder pn) {
        this.BR = br;
        this.Pn = pn;
    }
    
    @Override
    protected void I(final Parcel parcel, final int n) {
        ao.a(this, parcel, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DataHolder ik() {
        return this.Pn;
    }
}
