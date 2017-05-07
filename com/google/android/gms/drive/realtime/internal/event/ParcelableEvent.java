// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableEvent implements SafeParcelable
{
    public static final Parcelable$Creator<ParcelableEvent> CREATOR;
    final int BR;
    final String Re;
    final String Rh;
    final List<String> Rl;
    final boolean Rm;
    final String Rn;
    final TextInsertedDetails Ro;
    final TextDeletedDetails Rp;
    final ValuesAddedDetails Rq;
    final ValuesRemovedDetails Rr;
    final ValuesSetDetails Rs;
    final ValueChangedDetails Rt;
    final ReferenceShiftedDetails Ru;
    final ObjectChangedDetails Rv;
    final String vL;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    ParcelableEvent(final int br, final String vl, final String re, final List<String> rl, final boolean rm, final String rh, final String rn, final TextInsertedDetails ro, final TextDeletedDetails rp, final ValuesAddedDetails rq, final ValuesRemovedDetails rr, final ValuesSetDetails rs, final ValueChangedDetails rt, final ReferenceShiftedDetails ru, final ObjectChangedDetails rv) {
        this.BR = br;
        this.vL = vl;
        this.Re = re;
        this.Rl = rl;
        this.Rm = rm;
        this.Rh = rh;
        this.Rn = rn;
        this.Ro = ro;
        this.Rp = rp;
        this.Rq = rq;
        this.Rr = rr;
        this.Rs = rs;
        this.Rt = rt;
        this.Ru = ru;
        this.Rv = rv;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
