// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class k implements Parcelable$Creator<OfferWalletObject>
{
    static void a(final OfferWalletObject offerWalletObject, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, offerWalletObject.getVersionCode());
        b.a(parcel, 2, offerWalletObject.GA, false);
        b.a(parcel, 3, offerWalletObject.GX, false);
        b.D(parcel, o);
    }
    
    public OfferWalletObject aO(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        int g = 0;
        String i = null;
        while (parcel.dataPosition() < n) {
            final int j = a.m(parcel);
            switch (a.M(j)) {
                default: {
                    a.b(parcel, j);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, j);
                    continue;
                }
                case 2: {
                    i = a.m(parcel, j);
                    continue;
                }
                case 3: {
                    m = a.m(parcel, j);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new OfferWalletObject(g, i, m);
    }
    
    public OfferWalletObject[] bG(final int n) {
        return new OfferWalletObject[n];
    }
}
