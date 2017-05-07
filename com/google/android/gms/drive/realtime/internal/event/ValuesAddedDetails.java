// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValuesAddedDetails implements SafeParcelable
{
    public static final Parcelable$Creator<ValuesAddedDetails> CREATOR;
    final int BR;
    final String RF;
    final int RG;
    final int Rj;
    final int Rk;
    final int mIndex;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    ValuesAddedDetails(final int br, final int mIndex, final int rj, final int rk, final String rf, final int rg) {
        this.BR = br;
        this.mIndex = mIndex;
        this.Rj = rj;
        this.Rk = rk;
        this.RF = rf;
        this.RG = rg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
