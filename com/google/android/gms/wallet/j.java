// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.ArrayList;
import com.google.android.gms.wallet.wobs.f;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.internal.jr;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.d;
import com.google.android.gms.maps.model.LatLng;
import android.os.Parcelable;
import com.google.android.gms.wallet.wobs.p;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class j implements Parcelable$Creator<LoyaltyWalletObject>
{
    static void a(final LoyaltyWalletObject loyaltyWalletObject, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, loyaltyWalletObject.getVersionCode());
        b.a(parcel, 2, loyaltyWalletObject.fl, false);
        b.a(parcel, 3, loyaltyWalletObject.asI, false);
        b.a(parcel, 4, loyaltyWalletObject.asJ, false);
        b.a(parcel, 5, loyaltyWalletObject.asK, false);
        b.a(parcel, 6, loyaltyWalletObject.Dv, false);
        b.a(parcel, 7, loyaltyWalletObject.asL, false);
        b.a(parcel, 8, loyaltyWalletObject.asM, false);
        b.a(parcel, 9, loyaltyWalletObject.asN, false);
        b.a(parcel, 10, loyaltyWalletObject.asO, false);
        b.a(parcel, 11, loyaltyWalletObject.asP, false);
        b.c(parcel, 12, loyaltyWalletObject.state);
        b.c(parcel, 13, loyaltyWalletObject.asQ, false);
        b.a(parcel, 14, (Parcelable)loyaltyWalletObject.asR, n, false);
        b.c(parcel, 15, loyaltyWalletObject.asS, false);
        b.a(parcel, 17, loyaltyWalletObject.asU, false);
        b.a(parcel, 16, loyaltyWalletObject.asT, false);
        b.a(parcel, 19, loyaltyWalletObject.asW);
        b.c(parcel, 18, loyaltyWalletObject.asV, false);
        b.c(parcel, 21, loyaltyWalletObject.asY, false);
        b.c(parcel, 20, loyaltyWalletObject.asX, false);
        b.a(parcel, 23, (Parcelable)loyaltyWalletObject.ata, n, false);
        b.c(parcel, 22, loyaltyWalletObject.asZ, false);
        b.H(parcel, d);
    }
    
    public LoyaltyWalletObject dv(final Parcel parcel) {
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
        int g2 = 0;
        ArrayList<p> list = jr.hz();
        l l = null;
        ArrayList<LatLng> list2 = jr.hz();
        String o11 = null;
        String o12 = null;
        ArrayList<d> list3 = jr.hz();
        boolean c2 = false;
        ArrayList<n> list4 = jr.hz();
        ArrayList<com.google.android.gms.wallet.wobs.j> list5 = jr.hz();
        ArrayList<n> list6 = jr.hz();
        f f = null;
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
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 13: {
                    list = a.c(parcel, b, p.CREATOR);
                    continue;
                }
                case 14: {
                    l = a.a(parcel, b, com.google.android.gms.wallet.wobs.l.CREATOR);
                    continue;
                }
                case 15: {
                    list2 = a.c(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 17: {
                    o12 = a.o(parcel, b);
                    continue;
                }
                case 16: {
                    o11 = a.o(parcel, b);
                    continue;
                }
                case 19: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 18: {
                    list3 = a.c(parcel, b, d.CREATOR);
                    continue;
                }
                case 21: {
                    list5 = a.c(parcel, b, com.google.android.gms.wallet.wobs.j.CREATOR);
                    continue;
                }
                case 20: {
                    list4 = a.c(parcel, b, n.CREATOR);
                    continue;
                }
                case 23: {
                    f = a.a(parcel, b, com.google.android.gms.wallet.wobs.f.CREATOR);
                    continue;
                }
                case 22: {
                    list6 = a.c(parcel, b, n.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new LoyaltyWalletObject(g, o, o2, o3, o4, o5, o6, o7, o8, o9, o10, g2, list, l, list2, o11, o12, list3, c2, list4, list5, list6, f);
    }
    
    public LoyaltyWalletObject[] fv(final int n) {
        return new LoyaltyWalletObject[n];
    }
}
