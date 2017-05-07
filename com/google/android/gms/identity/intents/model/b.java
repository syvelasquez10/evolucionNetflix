// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<UserAddress>
{
    static void a(final UserAddress userAddress, final Parcel parcel, int p3) {
        p3 = com.google.android.gms.common.internal.safeparcel.b.p(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, userAddress.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, userAddress.name, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, userAddress.NB, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, userAddress.NC, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, userAddress.ND, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, userAddress.NE, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, userAddress.NF, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, userAddress.NG, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, userAddress.NH, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, userAddress.qd, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, userAddress.NI, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, userAddress.NJ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, userAddress.NK, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, userAddress.NL);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, userAddress.NM, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, userAddress.NN, false);
        com.google.android.gms.common.internal.safeparcel.b.F(parcel, p3);
    }
    
    public UserAddress aA(final Parcel parcel) {
        final int o = a.o(parcel);
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
        String n10 = null;
        String n11 = null;
        String n12 = null;
        boolean c = false;
        String n13 = null;
        String n14 = null;
        while (parcel.dataPosition() < o) {
            final int n15 = a.n(parcel);
            switch (a.R(n15)) {
                default: {
                    a.b(parcel, n15);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n15);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n15);
                    continue;
                }
                case 3: {
                    n2 = a.n(parcel, n15);
                    continue;
                }
                case 4: {
                    n3 = a.n(parcel, n15);
                    continue;
                }
                case 5: {
                    n4 = a.n(parcel, n15);
                    continue;
                }
                case 6: {
                    n5 = a.n(parcel, n15);
                    continue;
                }
                case 7: {
                    n6 = a.n(parcel, n15);
                    continue;
                }
                case 8: {
                    n7 = a.n(parcel, n15);
                    continue;
                }
                case 9: {
                    n8 = a.n(parcel, n15);
                    continue;
                }
                case 10: {
                    n9 = a.n(parcel, n15);
                    continue;
                }
                case 11: {
                    n10 = a.n(parcel, n15);
                    continue;
                }
                case 12: {
                    n11 = a.n(parcel, n15);
                    continue;
                }
                case 13: {
                    n12 = a.n(parcel, n15);
                    continue;
                }
                case 14: {
                    c = a.c(parcel, n15);
                    continue;
                }
                case 15: {
                    n13 = a.n(parcel, n15);
                    continue;
                }
                case 16: {
                    n14 = a.n(parcel, n15);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new UserAddress(g, n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, c, n13, n14);
    }
    
    public UserAddress[] bu(final int n) {
        return new UserAddress[n];
    }
}
