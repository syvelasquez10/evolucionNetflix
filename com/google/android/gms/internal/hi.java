// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hi implements SafeParcelable
{
    public static final hj CREATOR;
    public static final int Cc;
    private static final hq Cd;
    final int BR;
    public final String Ce;
    final hq Cf;
    public final int Cg;
    public final byte[] Ch;
    
    static {
        Cc = Integer.parseInt("-1");
        CREATOR = new hj();
        Cd = new hq$a("SsbContext").E(true).at("blob").fn();
    }
    
    hi(final int br, String fl, final hq cf, final int cg, final byte[] ch) {
        n.b(cg == hi.Cc || hp.O(cg) != null, (Object)("Invalid section type " + cg));
        this.BR = br;
        this.Ce = fl;
        this.Cf = cf;
        this.Cg = cg;
        this.Ch = ch;
        fl = this.fl();
        if (fl != null) {
            throw new IllegalArgumentException(fl);
        }
    }
    
    public hi(final String s, final hq hq) {
        this(1, s, hq, hi.Cc, null);
    }
    
    public hi(final String s, final hq hq, final String s2) {
        this(1, s, hq, hp.as(s2), null);
    }
    
    public hi(final byte[] array, final hq hq) {
        this(1, null, hq, hi.Cc, array);
    }
    
    public int describeContents() {
        final hj creator = hi.CREATOR;
        return 0;
    }
    
    public String fl() {
        if (this.Cg != hi.Cc && hp.O(this.Cg) == null) {
            return "Invalid section type " + this.Cg;
        }
        if (this.Ce != null && this.Ch != null) {
            return "Both content and blobContent set";
        }
        return null;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hj creator = hi.CREATOR;
        hj.a(this, parcel, n);
    }
}
