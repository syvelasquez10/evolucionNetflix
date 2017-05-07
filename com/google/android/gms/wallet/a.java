// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<Address>
{
    static void a(final Address address, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, address.getVersionCode());
        b.a(parcel, 2, address.name, false);
        b.a(parcel, 3, address.NB, false);
        b.a(parcel, 4, address.NC, false);
        b.a(parcel, 5, address.ND, false);
        b.a(parcel, 6, address.qd, false);
        b.a(parcel, 7, address.aba, false);
        b.a(parcel, 8, address.abb, false);
        b.a(parcel, 9, address.NI, false);
        b.a(parcel, 10, address.NK, false);
        b.a(parcel, 11, address.NL);
        b.a(parcel, 12, address.NM, false);
        b.F(parcel, p3);
    }
    
    public Address aX(final Parcel parcel) {
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int g = 0;
        String n = null;
        String n2 = null;
        String n3 = null;
        String n4 = null;
        String n5 = null;
        String n6 = null;
        String n7 = null;
        String n8 = null;
        String n9 = null;
        boolean c = false;
        String n10 = null;
        while (parcel.dataPosition() < o) {
            final int n11 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n11)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n11);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n11);
                    continue;
                }
                case 2: {
                    n = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
                case 3: {
                    n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
                case 4: {
                    n3 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
                case 5: {
                    n4 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
                case 6: {
                    n5 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
                case 7: {
                    n6 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
                case 8: {
                    n7 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
                case 9: {
                    n8 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
                case 10: {
                    n9 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
                case 11: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n11);
                    continue;
                }
                case 12: {
                    n10 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n11);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new Address(g, n, n2, n3, n4, n5, n6, n7, n8, n9, c, n10);
    }
    
    public Address[] cj(final int n) {
        return new Address[n];
    }
}
