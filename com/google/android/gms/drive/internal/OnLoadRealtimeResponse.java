// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnLoadRealtimeResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnLoadRealtimeResponse> CREATOR;
    final int BR;
    final boolean vR;
    
    static {
        CREATOR = (Parcelable$Creator)new aq();
    }
    
    OnLoadRealtimeResponse(final int br, final boolean vr) {
        this.BR = br;
        this.vR = vr;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aq.a(this, parcel, n);
    }
}
