// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.internal.pm;
import com.google.android.gms.drive.internal.v;
import com.google.android.gms.internal.pl;
import com.google.android.gms.drive.internal.ah;
import android.util.Base64;
import com.google.android.gms.common.internal.n;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DriveId implements SafeParcelable
{
    public static final Parcelable$Creator<DriveId> CREATOR;
    final int BR;
    final String Na;
    final long Nb;
    final long Nc;
    private volatile String Nd;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    DriveId(final int br, final String na, final long nb, final long nc) {
        final boolean b = false;
        this.Nd = null;
        this.BR = br;
        this.Na = na;
        n.K(!"".equals(na));
        boolean b2 = false;
        Label_0058: {
            if (na == null) {
                b2 = b;
                if (nb == -1L) {
                    break Label_0058;
                }
            }
            b2 = true;
        }
        n.K(b2);
        this.Nb = nb;
        this.Nc = nc;
    }
    
    public DriveId(final String s, final long n, final long n2) {
        this(1, s, n, n2);
    }
    
    public static DriveId bg(final String s) {
        n.i(s);
        return new DriveId(s, -1L, -1L);
    }
    
    public static DriveId decodeFromString(final String s) {
        n.b(s.startsWith("DriveId:"), (Object)("Invalid DriveId: " + s));
        return f(Base64.decode(s.substring("DriveId:".length()), 10));
    }
    
    static DriveId f(final byte[] array) {
        while (true) {
            while (true) {
                ah g;
                try {
                    g = ah.g(array);
                    if ("".equals(g.Pd)) {
                        final String pd = null;
                        return new DriveId(g.versionCode, pd, g.Pe, g.Pf);
                    }
                }
                catch (pl pl) {
                    throw new IllegalArgumentException();
                }
                final String pd = g.Pd;
                continue;
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public final String encodeToString() {
        if (this.Nd == null) {
            this.Nd = "DriveId:" + Base64.encodeToString(this.hN(), 10);
        }
        return this.Nd;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof DriveId) {
            final DriveId driveId = (DriveId)o;
            if (driveId.Nc != this.Nc) {
                v.p("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
                return false;
            }
            if (driveId.Nb == -1L && this.Nb == -1L) {
                return driveId.Na.equals(this.Na);
            }
            if (driveId.Nb == this.Nb) {
                return true;
            }
        }
        return false;
    }
    
    public String getResourceId() {
        return this.Na;
    }
    
    final byte[] hN() {
        final ah ah = new ah();
        ah.versionCode = this.BR;
        String na;
        if (this.Na == null) {
            na = "";
        }
        else {
            na = this.Na;
        }
        ah.Pd = na;
        ah.Pe = this.Nb;
        ah.Pf = this.Nc;
        return pm.f(ah);
    }
    
    @Override
    public int hashCode() {
        if (this.Nb == -1L) {
            return this.Na.hashCode();
        }
        return (String.valueOf(this.Nc) + String.valueOf(this.Nb)).hashCode();
    }
    
    @Override
    public String toString() {
        return this.encodeToString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
