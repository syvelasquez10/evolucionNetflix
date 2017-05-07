// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class i implements Parcelable$Creator<MaskedWalletRequest>
{
    static void a(final MaskedWalletRequest maskedWalletRequest, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, maskedWalletRequest.getVersionCode());
        b.a(parcel, 2, maskedWalletRequest.Go, false);
        b.a(parcel, 3, maskedWalletRequest.GK);
        b.a(parcel, 4, maskedWalletRequest.GL);
        b.a(parcel, 5, maskedWalletRequest.GM);
        b.a(parcel, 6, maskedWalletRequest.GN, false);
        b.a(parcel, 7, maskedWalletRequest.Gk, false);
        b.a(parcel, 8, maskedWalletRequest.GO, false);
        b.a(parcel, 9, (Parcelable)maskedWalletRequest.Gu, n, false);
        b.a(parcel, 10, maskedWalletRequest.GP);
        b.a(parcel, 11, maskedWalletRequest.GQ);
        b.a(parcel, 12, maskedWalletRequest.GR, n, false);
        b.a(parcel, 13, maskedWalletRequest.GS);
        b.a(parcel, 14, maskedWalletRequest.GT);
        b.D(parcel, o);
    }
    
    public MaskedWalletRequest aM(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        boolean c = false;
        boolean c2 = false;
        boolean c3 = false;
        String i = null;
        String j = null;
        String k = null;
        Cart cart = null;
        boolean c4 = false;
        boolean c5 = false;
        CountrySpecification[] array = null;
        boolean c6 = true;
        boolean c7 = true;
        while (parcel.dataPosition() < n) {
            final int l = a.m(parcel);
            switch (a.M(l)) {
                default: {
                    a.b(parcel, l);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, l);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, l);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, l);
                    continue;
                }
                case 4: {
                    c2 = a.c(parcel, l);
                    continue;
                }
                case 5: {
                    c3 = a.c(parcel, l);
                    continue;
                }
                case 6: {
                    i = a.m(parcel, l);
                    continue;
                }
                case 7: {
                    j = a.m(parcel, l);
                    continue;
                }
                case 8: {
                    k = a.m(parcel, l);
                    continue;
                }
                case 9: {
                    cart = a.a(parcel, l, Cart.CREATOR);
                    continue;
                }
                case 10: {
                    c4 = a.c(parcel, l);
                    continue;
                }
                case 11: {
                    c5 = a.c(parcel, l);
                    continue;
                }
                case 12: {
                    array = a.b(parcel, l, CountrySpecification.CREATOR);
                    continue;
                }
                case 13: {
                    c6 = a.c(parcel, l);
                    continue;
                }
                case 14: {
                    c7 = a.c(parcel, l);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new MaskedWalletRequest(g, m, c, c2, c3, i, j, k, cart, c4, c5, array, c6, c7);
    }
    
    public MaskedWalletRequest[] bE(final int n) {
        return new MaskedWalletRequest[n];
    }
}
