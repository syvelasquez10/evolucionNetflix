// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import java.util.List;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableEventList implements SafeParcelable
{
    public static final Parcelable$Creator<ParcelableEventList> CREATOR;
    final int BR;
    final DataHolder Rw;
    final boolean Rx;
    final List<String> Ry;
    final List<ParcelableEvent> me;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    ParcelableEventList(final int br, final List<ParcelableEvent> me, final DataHolder rw, final boolean rx, final List<String> ry) {
        this.BR = br;
        this.me = me;
        this.Rw = rw;
        this.Rx = rx;
        this.Ry = ry;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
