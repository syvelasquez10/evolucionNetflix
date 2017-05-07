// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StorageStats implements SafeParcelable
{
    public static final Parcelable$Creator<StorageStats> CREATOR;
    final int BR;
    final long Nt;
    final long Nu;
    final long Nv;
    final long Nw;
    final int Nx;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    StorageStats(final int br, final long nt, final long nu, final long nv, final long nw, final int nx) {
        this.BR = br;
        this.Nt = nt;
        this.Nu = nu;
        this.Nv = nv;
        this.Nw = nw;
        this.Nx = nx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
}
