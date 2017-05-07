// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class j implements Parcelable$Creator<h>
{
    static void a(final h h, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, h.getAccountName(), false);
        b.c(parcel, 1000, h.getVersionCode());
        b.a(parcel, 2, h.ne(), false);
        b.a(parcel, 3, h.nf(), false);
        b.a(parcel, 4, h.ng(), false);
        b.a(parcel, 5, h.nh(), false);
        b.a(parcel, 6, h.ni(), false);
        b.a(parcel, 7, h.nj(), false);
        b.a(parcel, 8, h.nk(), false);
        b.a(parcel, 9, (Parcelable)h.nl(), n, false);
        b.H(parcel, d);
    }
    
    public h da(final Parcel parcel) {
        PlusCommonExtras plusCommonExtras = null;
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        String[] a = null;
        String[] a2 = null;
        String[] a3 = null;
        String o5 = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    a3 = com.google.android.gms.common.internal.safeparcel.a.A(parcel, b);
                    continue;
                }
                case 3: {
                    a2 = com.google.android.gms.common.internal.safeparcel.a.A(parcel, b);
                    continue;
                }
                case 4: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, b);
                    continue;
                }
                case 5: {
                    o4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 6: {
                    o3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 7: {
                    o2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 8: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 9: {
                    plusCommonExtras = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<PlusCommonExtras>)PlusCommonExtras.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new h(g, o5, a3, a2, a, o4, o3, o2, o, plusCommonExtras);
    }
    
    public h[] eR(final int n) {
        return new h[n];
    }
}
