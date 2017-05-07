// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableIndexReference implements SafeParcelable
{
    public static final Parcelable$Creator<ParcelableIndexReference> CREATOR;
    final int BR;
    final String Rh;
    final boolean Ri;
    final int mIndex;
    
    static {
        CREATOR = (Parcelable$Creator)new q();
    }
    
    ParcelableIndexReference(final int br, final String rh, final int mIndex, final boolean ri) {
        this.BR = br;
        this.Rh = rh;
        this.mIndex = mIndex;
        this.Ri = ri;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        q.a(this, parcel, n);
    }
}
