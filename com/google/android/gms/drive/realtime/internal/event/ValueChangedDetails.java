// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValueChangedDetails implements SafeParcelable
{
    public static final Parcelable$Creator<ValueChangedDetails> CREATOR;
    final int BR;
    final int RE;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    ValueChangedDetails(final int br, final int re) {
        this.BR = br;
        this.RE = re;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
}
