// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValuesRemovedDetails implements SafeParcelable
{
    public static final Parcelable$Creator<ValuesRemovedDetails> CREATOR;
    final int BR;
    final String RH;
    final int RI;
    final int Rj;
    final int Rk;
    final int mIndex;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    ValuesRemovedDetails(final int br, final int mIndex, final int rj, final int rk, final String rh, final int ri) {
        this.BR = br;
        this.mIndex = mIndex;
        this.Rj = rj;
        this.Rk = rk;
        this.RH = rh;
        this.RI = ri;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
}
