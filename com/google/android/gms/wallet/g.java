// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<LoyaltyWalletObject>
{
    static void a(final LoyaltyWalletObject loyaltyWalletObject, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, loyaltyWalletObject.getVersionCode());
        b.a(parcel, 2, loyaltyWalletObject.GA, false);
        b.a(parcel, 3, loyaltyWalletObject.GB, false);
        b.a(parcel, 4, loyaltyWalletObject.GC, false);
        b.a(parcel, 5, loyaltyWalletObject.GD, false);
        b.a(parcel, 6, loyaltyWalletObject.GE, false);
        b.a(parcel, 7, loyaltyWalletObject.GF, false);
        b.a(parcel, 8, loyaltyWalletObject.GG, false);
        b.a(parcel, 9, loyaltyWalletObject.GH, false);
        b.D(parcel, o);
    }
    
    public LoyaltyWalletObject aK(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        int g = 0;
        String i = null;
        String j = null;
        String k = null;
        String l = null;
        String m2 = null;
        String m3 = null;
        String m4 = null;
        while (parcel.dataPosition() < n) {
            final int m5 = a.m(parcel);
            switch (a.M(m5)) {
                default: {
                    a.b(parcel, m5);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m5);
                    continue;
                }
                case 2: {
                    m4 = a.m(parcel, m5);
                    continue;
                }
                case 3: {
                    m3 = a.m(parcel, m5);
                    continue;
                }
                case 4: {
                    m2 = a.m(parcel, m5);
                    continue;
                }
                case 5: {
                    l = a.m(parcel, m5);
                    continue;
                }
                case 6: {
                    k = a.m(parcel, m5);
                    continue;
                }
                case 7: {
                    j = a.m(parcel, m5);
                    continue;
                }
                case 8: {
                    i = a.m(parcel, m5);
                    continue;
                }
                case 9: {
                    m = a.m(parcel, m5);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new LoyaltyWalletObject(g, m4, m3, m2, l, k, j, i, m);
    }
    
    public LoyaltyWalletObject[] bC(final int n) {
        return new LoyaltyWalletObject[n];
    }
}
