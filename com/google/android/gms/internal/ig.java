// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ig implements SafeParcelable
{
    public static final Parcelable$Creator<ig> CREATOR;
    private final int BR;
    private String Gn;
    
    static {
        CREATOR = (Parcelable$Creator)new ih();
    }
    
    public ig() {
        this(1, null);
    }
    
    ig(final int br, final String gn) {
        this.BR = br;
        this.Gn = gn;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof ig && ik.a(this.Gn, ((ig)o).Gn));
    }
    
    public String fz() {
        return this.Gn;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Gn);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ih.a(this, parcel, n);
    }
}
