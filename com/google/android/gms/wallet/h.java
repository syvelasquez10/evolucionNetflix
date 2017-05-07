// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class h implements Parcelable$Creator<MaskedWallet>
{
    static void a(final MaskedWallet maskedWallet, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, maskedWallet.getVersionCode());
        b.a(parcel, 2, maskedWallet.Gn, false);
        b.a(parcel, 3, maskedWallet.Go, false);
        b.a(parcel, 4, maskedWallet.Gt, false);
        b.a(parcel, 5, maskedWallet.Gq, false);
        b.a(parcel, 6, (Parcelable)maskedWallet.Gr, n, false);
        b.a(parcel, 7, (Parcelable)maskedWallet.Gs, n, false);
        b.a(parcel, 8, maskedWallet.GI, n, false);
        b.a(parcel, 9, maskedWallet.GJ, n, false);
        b.D(parcel, o);
    }
    
    public MaskedWallet aL(final Parcel parcel) {
        OfferWalletObject[] array = null;
        final int n = a.n(parcel);
        int g = 0;
        LoyaltyWalletObject[] array2 = null;
        Address address = null;
        Address address2 = null;
        String m = null;
        String[] x = null;
        String i = null;
        String j = null;
        while (parcel.dataPosition() < n) {
            final int k = a.m(parcel);
            switch (a.M(k)) {
                default: {
                    a.b(parcel, k);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, k);
                    continue;
                }
                case 2: {
                    j = a.m(parcel, k);
                    continue;
                }
                case 3: {
                    i = a.m(parcel, k);
                    continue;
                }
                case 4: {
                    x = a.x(parcel, k);
                    continue;
                }
                case 5: {
                    m = a.m(parcel, k);
                    continue;
                }
                case 6: {
                    address2 = a.a(parcel, k, Address.CREATOR);
                    continue;
                }
                case 7: {
                    address = a.a(parcel, k, Address.CREATOR);
                    continue;
                }
                case 8: {
                    array2 = a.b(parcel, k, LoyaltyWalletObject.CREATOR);
                    continue;
                }
                case 9: {
                    array = a.b(parcel, k, OfferWalletObject.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new MaskedWallet(g, j, i, x, m, address2, address, array2, array);
    }
    
    public MaskedWallet[] bD(final int n) {
        return new MaskedWallet[n];
    }
}
