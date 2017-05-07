// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UserMetadata implements SafeParcelable
{
    public static final Parcelable$Creator<UserMetadata> CREATOR;
    final int BR;
    final String NA;
    final boolean NB;
    final String NC;
    final String Ny;
    final String Nz;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    UserMetadata(final int br, final String ny, final String nz, final String na, final boolean nb, final String nc) {
        this.BR = br;
        this.Ny = ny;
        this.Nz = nz;
        this.NA = na;
        this.NB = nb;
        this.NC = nc;
    }
    
    public UserMetadata(final String s, final String s2, final String s3, final boolean b, final String s4) {
        this(1, s, s2, s3, b, s4);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", this.Ny, this.Nz, this.NA, this.NB, this.NC);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
