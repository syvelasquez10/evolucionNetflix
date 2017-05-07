// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LoadRealtimeRequest implements SafeParcelable
{
    public static final Parcelable$Creator<LoadRealtimeRequest> CREATOR;
    final int BR;
    final DriveId MO;
    final boolean Pc;
    
    static {
        CREATOR = (Parcelable$Creator)new ag();
    }
    
    LoadRealtimeRequest(final int br, final DriveId mo, final boolean pc) {
        this.BR = br;
        this.MO = mo;
        this.Pc = pc;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ag.a(this, parcel, n);
    }
}
