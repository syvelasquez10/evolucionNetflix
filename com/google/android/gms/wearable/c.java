// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class c implements SafeParcelable
{
    public static final Parcelable$Creator<c> CREATOR;
    final int BR;
    private final int FD;
    private final String Ss;
    private final int auH;
    private final boolean auI;
    private boolean auJ;
    private String auK;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    c(final int br, final String mName, final String ss, final int fd, final int auH, final boolean auI, final boolean auJ, final String auK) {
        this.BR = br;
        this.mName = mName;
        this.Ss = ss;
        this.FD = fd;
        this.auH = auH;
        this.auI = auI;
        this.auJ = auJ;
        this.auK = auK;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof c) {
            final c c = (c)o;
            if (m.equal(this.BR, c.BR) && m.equal(this.mName, c.mName) && m.equal(this.Ss, c.Ss) && m.equal(this.FD, c.FD) && m.equal(this.auH, c.auH) && m.equal(this.auI, c.auI)) {
                return true;
            }
        }
        return false;
    }
    
    public String getAddress() {
        return this.Ss;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public int getRole() {
        return this.auH;
    }
    
    public int getType() {
        return this.FD;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.BR, this.mName, this.Ss, this.FD, this.auH, this.auI);
    }
    
    public boolean isConnected() {
        return this.auJ;
    }
    
    public boolean isEnabled() {
        return this.auI;
    }
    
    public String pQ() {
        return this.auK;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConnectionConfiguration[ ");
        sb.append("mName=" + this.mName);
        sb.append(", mAddress=" + this.Ss);
        sb.append(", mType=" + this.FD);
        sb.append(", mRole=" + this.auH);
        sb.append(", mEnabled=" + this.auI);
        sb.append(", mIsConnected=" + this.auJ);
        sb.append(", mEnabled=" + this.auK);
        sb.append("]");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
