// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<Address>
{
    static void a(final Address address, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, address.getVersionCode());
        b.a(parcel, 2, address.name, false);
        b.a(parcel, 3, address.adC, false);
        b.a(parcel, 4, address.adD, false);
        b.a(parcel, 5, address.adE, false);
        b.a(parcel, 6, address.uW, false);
        b.a(parcel, 7, address.asi, false);
        b.a(parcel, 8, address.asj, false);
        b.a(parcel, 9, address.adJ, false);
        b.a(parcel, 10, address.adL, false);
        b.a(parcel, 11, address.adM);
        b.a(parcel, 12, address.adN, false);
        b.H(parcel, d);
    }
    
    public Address dn(final Parcel parcel) {
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        String o5 = null;
        String o6 = null;
        String o7 = null;
        String o8 = null;
        String o9 = null;
        boolean c2 = false;
        String o10 = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 5: {
                    o4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 6: {
                    o5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 7: {
                    o6 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 8: {
                    o7 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 9: {
                    o8 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 10: {
                    o9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 11: {
                    c2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b);
                    continue;
                }
                case 12: {
                    o10 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c, parcel);
        }
        return new Address(g, o, o2, o3, o4, o5, o6, o7, o8, o9, c2, o10);
    }
    
    public Address[] fn(final int n) {
        return new Address[n];
    }
}
