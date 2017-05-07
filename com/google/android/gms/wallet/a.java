// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<Address>
{
    static void a(final Address address, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, address.getVersionCode());
        b.a(parcel, 2, address.name, false);
        b.a(parcel, 3, address.Ga, false);
        b.a(parcel, 4, address.Gb, false);
        b.a(parcel, 5, address.Gc, false);
        b.a(parcel, 6, address.id, false);
        b.a(parcel, 7, address.Gd, false);
        b.a(parcel, 8, address.Ge, false);
        b.a(parcel, 9, address.Gf, false);
        b.a(parcel, 10, address.Gg, false);
        b.a(parcel, 11, address.Gh);
        b.a(parcel, 12, address.Gi, false);
        b.D(parcel, o);
    }
    
    public Address aE(final Parcel parcel) {
        final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
        int g = 0;
        String m = null;
        String i = null;
        String j = null;
        String k = null;
        String l = null;
        String m2 = null;
        String m3 = null;
        String m4 = null;
        String m5 = null;
        boolean c = false;
        String m6 = null;
        while (parcel.dataPosition() < n) {
            final int m7 = com.google.android.gms.common.internal.safeparcel.a.m(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.M(m7)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, m7);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m7);
                    continue;
                }
                case 2: {
                    m = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
                case 3: {
                    i = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
                case 4: {
                    j = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
                case 5: {
                    k = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
                case 6: {
                    l = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
                case 7: {
                    m2 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
                case 8: {
                    m3 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
                case 9: {
                    m4 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
                case 10: {
                    m5 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
                case 11: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, m7);
                    continue;
                }
                case 12: {
                    m6 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m7);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + n, parcel);
        }
        return new Address(g, m, i, j, k, l, m2, m3, m4, m5, c, m6);
    }
    
    public Address[] bw(final int n) {
        return new Address[n];
    }
}
