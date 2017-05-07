// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a$a;
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
        final int d = b.D(parcel);
        b.c(parcel, 1, maskedWalletRequest.getVersionCode());
        b.a(parcel, 2, maskedWalletRequest.asr, false);
        b.a(parcel, 3, maskedWalletRequest.ate);
        b.a(parcel, 4, maskedWalletRequest.atf);
        b.a(parcel, 5, maskedWalletRequest.atg);
        b.a(parcel, 6, maskedWalletRequest.ath, false);
        b.a(parcel, 7, maskedWalletRequest.asl, false);
        b.a(parcel, 8, maskedWalletRequest.ati, false);
        b.a(parcel, 9, (Parcelable)maskedWalletRequest.asA, n, false);
        b.a(parcel, 10, maskedWalletRequest.atj);
        b.a(parcel, 11, maskedWalletRequest.atk);
        b.a(parcel, 12, maskedWalletRequest.atl, n, false);
        b.a(parcel, 13, maskedWalletRequest.atm);
        b.a(parcel, 14, maskedWalletRequest.atn);
        b.c(parcel, 15, maskedWalletRequest.ato, false);
        b.H(parcel, d);
    }
    
    public MaskedWalletRequest dx(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        boolean c2 = false;
        boolean c3 = false;
        boolean c4 = false;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        Cart cart = null;
        boolean c5 = false;
        boolean c6 = false;
        com.google.android.gms.wallet.CountrySpecification[] array = null;
        boolean c7 = true;
        boolean c8 = true;
        ArrayList<CountrySpecification> c9 = null;
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
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 5: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 6: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 9: {
                    cart = a.a(parcel, b, Cart.CREATOR);
                    continue;
                }
                case 10: {
                    c5 = a.c(parcel, b);
                    continue;
                }
                case 11: {
                    c6 = a.c(parcel, b);
                    continue;
                }
                case 12: {
                    array = a.b(parcel, b, com.google.android.gms.wallet.CountrySpecification.CREATOR);
                    continue;
                }
                case 13: {
                    c7 = a.c(parcel, b);
                    continue;
                }
                case 14: {
                    c8 = a.c(parcel, b);
                    continue;
                }
                case 15: {
                    c9 = a.c(parcel, b, CountrySpecification.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new MaskedWalletRequest(g, o, c2, c3, c4, o2, o3, o4, cart, c5, c6, array, c7, c8, c9);
    }
    
    public MaskedWalletRequest[] fx(final int n) {
        return new MaskedWalletRequest[n];
    }
}
