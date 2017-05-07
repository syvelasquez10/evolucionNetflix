// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnSyncMoreResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnSyncMoreResponse> CREATOR;
    final boolean Fg;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new ag();
    }
    
    OnSyncMoreResponse(final int xh, final boolean fg) {
        this.xH = xh;
        this.Fg = fg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ag.a(this, parcel, n);
    }
}
