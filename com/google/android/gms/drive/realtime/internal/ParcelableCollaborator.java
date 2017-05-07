// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableCollaborator implements SafeParcelable
{
    public static final Parcelable$Creator<ParcelableCollaborator> CREATOR;
    final int BR;
    final String Nz;
    final boolean Rc;
    final boolean Rd;
    final String Re;
    final String Rf;
    final String Rg;
    final String vL;
    
    static {
        CREATOR = (Parcelable$Creator)new p();
    }
    
    ParcelableCollaborator(final int br, final boolean rc, final boolean rd, final String vl, final String re, final String nz, final String rf, final String rg) {
        this.BR = br;
        this.Rc = rc;
        this.Rd = rd;
        this.vL = vl;
        this.Re = re;
        this.Nz = nz;
        this.Rf = rf;
        this.Rg = rg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof ParcelableCollaborator && this.vL.equals(((ParcelableCollaborator)o).vL));
    }
    
    @Override
    public int hashCode() {
        return this.vL.hashCode();
    }
    
    @Override
    public String toString() {
        return "Collaborator [isMe=" + this.Rc + ", isAnonymous=" + this.Rd + ", sessionId=" + this.vL + ", userId=" + this.Re + ", displayName=" + this.Nz + ", color=" + this.Rf + ", photoUrl=" + this.Rg + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        p.a(this, parcel, n);
    }
}
