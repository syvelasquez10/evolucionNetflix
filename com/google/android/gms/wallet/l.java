// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class l implements Parcelable$Creator<MaskedWalletRequest>
{
    static void a(final MaskedWalletRequest maskedWalletRequest, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, maskedWalletRequest.getVersionCode());
        b.a(parcel, 2, maskedWalletRequest.abi, false);
        b.a(parcel, 3, maskedWalletRequest.abV);
        b.a(parcel, 4, maskedWalletRequest.abW);
        b.a(parcel, 5, maskedWalletRequest.abX);
        b.a(parcel, 6, maskedWalletRequest.abY, false);
        b.a(parcel, 7, maskedWalletRequest.abd, false);
        b.a(parcel, 8, maskedWalletRequest.abZ, false);
        b.a(parcel, 9, (Parcelable)maskedWalletRequest.abr, n, false);
        b.a(parcel, 10, maskedWalletRequest.aca);
        b.a(parcel, 11, maskedWalletRequest.acb);
        b.a(parcel, 12, maskedWalletRequest.acc, n, false);
        b.a(parcel, 13, maskedWalletRequest.acd);
        b.a(parcel, 14, maskedWalletRequest.ace);
        b.b(parcel, 15, maskedWalletRequest.acf, false);
        b.F(parcel, p3);
    }
    
    public MaskedWalletRequest bh(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        boolean c = false;
        boolean c2 = false;
        boolean c3 = false;
        String n2 = null;
        String n3 = null;
        String n4 = null;
        Cart cart = null;
        boolean c4 = false;
        boolean c5 = false;
        com.google.android.gms.wallet.CountrySpecification[] array = null;
        boolean c6 = true;
        boolean c7 = true;
        ArrayList<CountrySpecification> c8 = null;
        while (parcel.dataPosition() < o) {
            final int n5 = a.n(parcel);
            switch (a.R(n5)) {
                default: {
                    a.b(parcel, n5);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n5);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n5);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, n5);
                    continue;
                }
                case 4: {
                    c2 = a.c(parcel, n5);
                    continue;
                }
                case 5: {
                    c3 = a.c(parcel, n5);
                    continue;
                }
                case 6: {
                    n2 = a.n(parcel, n5);
                    continue;
                }
                case 7: {
                    n3 = a.n(parcel, n5);
                    continue;
                }
                case 8: {
                    n4 = a.n(parcel, n5);
                    continue;
                }
                case 9: {
                    cart = a.a(parcel, n5, Cart.CREATOR);
                    continue;
                }
                case 10: {
                    c4 = a.c(parcel, n5);
                    continue;
                }
                case 11: {
                    c5 = a.c(parcel, n5);
                    continue;
                }
                case 12: {
                    array = a.b(parcel, n5, com.google.android.gms.wallet.CountrySpecification.CREATOR);
                    continue;
                }
                case 13: {
                    c6 = a.c(parcel, n5);
                    continue;
                }
                case 14: {
                    c7 = a.c(parcel, n5);
                    continue;
                }
                case 15: {
                    c8 = a.c(parcel, n5, CountrySpecification.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new MaskedWalletRequest(g, n, c, c2, c3, n2, n3, n4, cart, c4, c5, array, c6, c7, c8);
    }
    
    public MaskedWalletRequest[] ct(final int n) {
        return new MaskedWalletRequest[n];
    }
}
