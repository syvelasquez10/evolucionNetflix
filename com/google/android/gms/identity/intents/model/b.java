// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<UserAddress>
{
    static void a(final UserAddress userAddress, final Parcel parcel, int d) {
        d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, userAddress.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, userAddress.name, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, userAddress.adC, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, userAddress.adD, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, userAddress.adE, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, userAddress.adF, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, userAddress.adG, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, userAddress.adH, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, userAddress.adI, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, userAddress.uW, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, userAddress.adJ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, userAddress.adK, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, userAddress.adL, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, userAddress.adM);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, userAddress.adN, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, userAddress.adO, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public UserAddress cr(final Parcel parcel) {
        final int c = a.C(parcel);
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
        String o10 = null;
        String o11 = null;
        String o12 = null;
        boolean c2 = false;
        String o13 = null;
        String o14 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    o6 = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    o7 = a.o(parcel, b);
                    continue;
                }
                case 9: {
                    o8 = a.o(parcel, b);
                    continue;
                }
                case 10: {
                    o9 = a.o(parcel, b);
                    continue;
                }
                case 11: {
                    o10 = a.o(parcel, b);
                    continue;
                }
                case 12: {
                    o11 = a.o(parcel, b);
                    continue;
                }
                case 13: {
                    o12 = a.o(parcel, b);
                    continue;
                }
                case 14: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 15: {
                    o13 = a.o(parcel, b);
                    continue;
                }
                case 16: {
                    o14 = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new UserAddress(g, o, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, c2, o13, o14);
    }
    
    public UserAddress[] dZ(final int n) {
        return new UserAddress[n];
    }
}
