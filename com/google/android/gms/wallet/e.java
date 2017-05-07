// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<d>
{
    static void a(final d d, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, d.getVersionCode());
        b.a(parcel, 2, (Parcelable)d.abg, n, false);
        b.F(parcel, p3);
    }
    
    public d ba(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        LoyaltyWalletObject loyaltyWalletObject = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    loyaltyWalletObject = a.a(parcel, n, LoyaltyWalletObject.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new d(g, loyaltyWalletObject);
    }
    
    public d[] cm(final int n) {
        return new d[n];
    }
}
