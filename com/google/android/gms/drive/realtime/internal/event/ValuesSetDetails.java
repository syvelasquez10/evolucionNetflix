// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValuesSetDetails implements SafeParcelable
{
    public static final Parcelable$Creator<ValuesSetDetails> CREATOR;
    final int BR;
    final int Rj;
    final int Rk;
    final int mIndex;
    
    static {
        CREATOR = (Parcelable$Creator)new j();
    }
    
    ValuesSetDetails(final int br, final int mIndex, final int rj, final int rk) {
        this.BR = br;
        this.mIndex = mIndex;
        this.Rj = rj;
        this.Rk = rk;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
