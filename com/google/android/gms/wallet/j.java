// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.ArrayList;
import com.google.android.gms.internal.jo;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.gi;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.js;
import com.google.android.gms.internal.jm;
import com.google.android.gms.maps.model.LatLng;
import android.os.Parcelable;
import com.google.android.gms.internal.jy;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class j implements Parcelable$Creator<LoyaltyWalletObject>
{
    static void a(final LoyaltyWalletObject loyaltyWalletObject, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, loyaltyWalletObject.getVersionCode());
        b.a(parcel, 2, loyaltyWalletObject.eC, false);
        b.a(parcel, 3, loyaltyWalletObject.abz, false);
        b.a(parcel, 4, loyaltyWalletObject.abA, false);
        b.a(parcel, 5, loyaltyWalletObject.abB, false);
        b.a(parcel, 6, loyaltyWalletObject.abC, false);
        b.a(parcel, 7, loyaltyWalletObject.abD, false);
        b.a(parcel, 8, loyaltyWalletObject.abE, false);
        b.a(parcel, 9, loyaltyWalletObject.abF, false);
        b.a(parcel, 10, loyaltyWalletObject.abG, false);
        b.a(parcel, 11, loyaltyWalletObject.abH, false);
        b.c(parcel, 12, loyaltyWalletObject.state);
        b.b(parcel, 13, loyaltyWalletObject.abI, false);
        b.a(parcel, 14, (Parcelable)loyaltyWalletObject.abJ, n, false);
        b.b(parcel, 15, loyaltyWalletObject.abK, false);
        b.a(parcel, 17, loyaltyWalletObject.abM, false);
        b.a(parcel, 16, loyaltyWalletObject.abL, false);
        b.a(parcel, 19, loyaltyWalletObject.abO);
        b.b(parcel, 18, loyaltyWalletObject.abN, false);
        b.b(parcel, 21, loyaltyWalletObject.abQ, false);
        b.b(parcel, 20, loyaltyWalletObject.abP, false);
        b.a(parcel, 23, (Parcelable)loyaltyWalletObject.abS, n, false);
        b.b(parcel, 22, loyaltyWalletObject.abR, false);
        b.F(parcel, p3);
    }
    
    public LoyaltyWalletObject bf(final Parcel parcel) {
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
        int g2 = 0;
        ArrayList<jy> list = gi.fs();
        ju ju = null;
        ArrayList<LatLng> list2 = gi.fs();
        String n11 = null;
        String n12 = null;
        ArrayList<jm> list3 = gi.fs();
        boolean c = false;
        ArrayList<jw> list4 = gi.fs();
        ArrayList<js> list5 = gi.fs();
        ArrayList<jw> list6 = gi.fs();
        jo jo = null;
        while (parcel.dataPosition() < o) {
            final int n13 = a.n(parcel);
            switch (a.R(n13)) {
                default: {
                    a.b(parcel, n13);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n13);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n13);
                    continue;
                }
                case 3: {
                    n2 = a.n(parcel, n13);
                    continue;
                }
                case 4: {
                    n3 = a.n(parcel, n13);
                    continue;
                }
                case 5: {
                    n4 = a.n(parcel, n13);
                    continue;
                }
                case 6: {
                    n5 = a.n(parcel, n13);
                    continue;
                }
                case 7: {
                    n6 = a.n(parcel, n13);
                    continue;
                }
                case 8: {
                    n7 = a.n(parcel, n13);
                    continue;
                }
                case 9: {
                    n8 = a.n(parcel, n13);
                    continue;
                }
                case 10: {
                    n9 = a.n(parcel, n13);
                    continue;
                }
                case 11: {
                    n10 = a.n(parcel, n13);
                    continue;
                }
                case 12: {
                    g2 = a.g(parcel, n13);
                    continue;
                }
                case 13: {
                    list = a.c(parcel, n13, jy.CREATOR);
                    continue;
                }
                case 14: {
                    ju = a.a(parcel, n13, com.google.android.gms.internal.ju.CREATOR);
                    continue;
                }
                case 15: {
                    list2 = a.c(parcel, n13, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 17: {
                    n12 = a.n(parcel, n13);
                    continue;
                }
                case 16: {
                    n11 = a.n(parcel, n13);
                    continue;
                }
                case 19: {
                    c = a.c(parcel, n13);
                    continue;
                }
                case 18: {
                    list3 = a.c(parcel, n13, jm.CREATOR);
                    continue;
                }
                case 21: {
                    list5 = a.c(parcel, n13, js.CREATOR);
                    continue;
                }
                case 20: {
                    list4 = a.c(parcel, n13, jw.CREATOR);
                    continue;
                }
                case 23: {
                    jo = a.a(parcel, n13, com.google.android.gms.internal.jo.CREATOR);
                    continue;
                }
                case 22: {
                    list6 = a.c(parcel, n13, jw.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new LoyaltyWalletObject(g, n, n2, n3, n4, n5, n6, n7, n8, n9, n10, g2, list, ju, list2, n11, n12, list3, c, list4, list5, list6, jo);
    }
    
    public LoyaltyWalletObject[] cr(final int n) {
        return new LoyaltyWalletObject[n];
    }
}
