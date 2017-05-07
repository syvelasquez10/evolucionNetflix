// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class jc implements Parcelable$Creator<jb>
{
    static void a(final jb jb, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, jb.BR);
        b.a(parcel, 2, jb.Mi, false);
        b.c(parcel, 3, jb.Mj);
        b.H(parcel, d);
    }
    
    public jb E(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        String o = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new jb(g2, o, g);
    }
    
    public jb[] aE(final int n) {
        return new jb[n];
    }
}
