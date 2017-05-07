// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import java.util.ArrayList;
import com.google.android.gms.internal.jr;
import com.google.android.gms.maps.model.LatLng;
import android.os.Parcelable;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<CommonWalletObject>
{
    static void a(final CommonWalletObject commonWalletObject, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, commonWalletObject.getVersionCode());
        b.a(parcel, 2, commonWalletObject.fl, false);
        b.a(parcel, 3, commonWalletObject.asP, false);
        b.a(parcel, 4, commonWalletObject.name, false);
        b.a(parcel, 5, commonWalletObject.asJ, false);
        b.a(parcel, 6, commonWalletObject.asL, false);
        b.a(parcel, 7, commonWalletObject.asM, false);
        b.a(parcel, 8, commonWalletObject.asN, false);
        b.a(parcel, 9, commonWalletObject.asO, false);
        b.c(parcel, 10, commonWalletObject.state);
        b.c(parcel, 11, commonWalletObject.asQ, false);
        b.a(parcel, 12, (Parcelable)commonWalletObject.asR, n, false);
        b.c(parcel, 13, commonWalletObject.asS, false);
        b.a(parcel, 14, commonWalletObject.asT, false);
        b.a(parcel, 15, commonWalletObject.asU, false);
        b.a(parcel, 17, commonWalletObject.asW);
        b.c(parcel, 16, commonWalletObject.asV, false);
        b.c(parcel, 19, commonWalletObject.asY, false);
        b.c(parcel, 18, commonWalletObject.asX, false);
        b.c(parcel, 20, commonWalletObject.asZ, false);
        b.H(parcel, d);
    }
    
    public CommonWalletObject dG(final Parcel parcel) {
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
        int g2 = 0;
        ArrayList<p> list = jr.hz();
        l l = null;
        ArrayList<LatLng> list2 = jr.hz();
        String o9 = null;
        String o10 = null;
        ArrayList<d> list3 = jr.hz();
        boolean c2 = false;
        ArrayList<n> list4 = jr.hz();
        ArrayList<j> list5 = jr.hz();
        ArrayList<n> list6 = jr.hz();
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
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 11: {
                    list = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b, p.CREATOR);
                    continue;
                }
                case 12: {
                    l = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, com.google.android.gms.wallet.wobs.l.CREATOR);
                    continue;
                }
                case 13: {
                    list2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 14: {
                    o9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 15: {
                    o10 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 17: {
                    c2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b);
                    continue;
                }
                case 16: {
                    list3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b, d.CREATOR);
                    continue;
                }
                case 19: {
                    list5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b, j.CREATOR);
                    continue;
                }
                case 18: {
                    list4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b, n.CREATOR);
                    continue;
                }
                case 20: {
                    list6 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b, n.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c, parcel);
        }
        return new CommonWalletObject(g, o, o2, o3, o4, o5, o6, o7, o8, g2, list, l, list2, o9, o10, list3, c2, list4, list5, list6);
    }
    
    public CommonWalletObject[] fI(final int n) {
        return new CommonWalletObject[n];
    }
}
