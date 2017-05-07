// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnListEntriesResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnListEntriesResponse> CREATOR;
    final DataHolder FJ;
    final boolean Fg;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new ad();
    }
    
    OnListEntriesResponse(final int xh, final DataHolder fj, final boolean fg) {
        this.xH = xh;
        this.FJ = fj;
        this.Fg = fg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DataHolder fN() {
        return this.FJ;
    }
    
    public boolean fO() {
        return this.Fg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ad.a(this, parcel, n);
    }
}
