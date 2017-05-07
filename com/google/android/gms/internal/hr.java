// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hr implements Parcelable$Creator<hq>
{
    static void a(final hq hq, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, hq.name, false);
        b.c(parcel, 1000, hq.BR);
        b.a(parcel, 2, hq.Co, false);
        b.a(parcel, 3, hq.Cp);
        b.c(parcel, 4, hq.weight);
        b.a(parcel, 5, hq.Cq);
        b.a(parcel, 6, hq.Cr, false);
        b.a(parcel, 7, hq.Cs, n, false);
        b.a(parcel, 8, hq.Ct, false);
        b.a(parcel, 11, hq.Cu, false);
        b.H(parcel, d);
    }
    
    public hq[] Q(final int n) {
        return new hq[n];
    }
    
    public hq r(final Parcel parcel) {
        boolean c = false;
        String o = null;
        final int c2 = a.C(parcel);
        int g = 1;
        int[] u = null;
        hk[] array = null;
        String o2 = null;
        boolean c3 = false;
        String o3 = null;
        String o4 = null;
        int g2 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 6: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    array = a.b(parcel, b, (android.os.Parcelable$Creator<hk>)hk.CREATOR);
                    continue;
                }
                case 8: {
                    u = a.u(parcel, b);
                    continue;
                }
                case 11: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new hq(g2, o4, o3, c3, g, c, o2, array, u, o);
    }
}
