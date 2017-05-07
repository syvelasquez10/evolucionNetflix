// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class j implements Parcelable$Creator<h>
{
    static void a(final h h, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.a(parcel, 1, h.getAccountName(), false);
        b.c(parcel, 1000, h.getVersionCode());
        b.a(parcel, 2, h.iP(), false);
        b.a(parcel, 3, h.iQ(), false);
        b.a(parcel, 4, h.iR(), false);
        b.a(parcel, 5, h.iS(), false);
        b.a(parcel, 6, h.iT(), false);
        b.a(parcel, 7, h.iU(), false);
        b.a(parcel, 8, h.iV(), false);
        b.a(parcel, 9, (Parcelable)h.iW(), n, false);
        b.F(parcel, p3);
    }
    
    public h aK(final Parcel parcel) {
        PlusCommonExtras plusCommonExtras = null;
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        String n2 = null;
        String n3 = null;
        String n4 = null;
        String[] z = null;
        String[] z2 = null;
        String[] z3 = null;
        String n5 = null;
        while (parcel.dataPosition() < o) {
            final int n6 = a.n(parcel);
            switch (a.R(n6)) {
                default: {
                    a.b(parcel, n6);
                    continue;
                }
                case 1: {
                    n5 = a.n(parcel, n6);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n6);
                    continue;
                }
                case 2: {
                    z3 = a.z(parcel, n6);
                    continue;
                }
                case 3: {
                    z2 = a.z(parcel, n6);
                    continue;
                }
                case 4: {
                    z = a.z(parcel, n6);
                    continue;
                }
                case 5: {
                    n4 = a.n(parcel, n6);
                    continue;
                }
                case 6: {
                    n3 = a.n(parcel, n6);
                    continue;
                }
                case 7: {
                    n2 = a.n(parcel, n6);
                    continue;
                }
                case 8: {
                    n = a.n(parcel, n6);
                    continue;
                }
                case 9: {
                    plusCommonExtras = a.a(parcel, n6, (android.os.Parcelable$Creator<PlusCommonExtras>)PlusCommonExtras.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new h(g, n5, z3, z2, z, n4, n3, n2, n, plusCommonExtras);
    }
    
    public h[] bN(final int n) {
        return new h[n];
    }
}
