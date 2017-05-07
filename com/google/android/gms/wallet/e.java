// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<d>
{
    static void a(final d d, final Parcel parcel, final int n) {
        final int d2 = b.D(parcel);
        b.c(parcel, 1, d.getVersionCode());
        b.a(parcel, 2, (Parcelable)d.aso, n, false);
        b.a(parcel, 3, (Parcelable)d.asp, n, false);
        b.H(parcel, d2);
    }
    
    public d dq(final Parcel parcel) {
        OfferWalletObject offerWalletObject = null;
        final int c = a.C(parcel);
        int g = 0;
        LoyaltyWalletObject loyaltyWalletObject = null;
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
                    loyaltyWalletObject = a.a(parcel, b, LoyaltyWalletObject.CREATOR);
                    continue;
                }
                case 3: {
                    offerWalletObject = a.a(parcel, b, OfferWalletObject.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new d(g, loyaltyWalletObject, offerWalletObject);
    }
    
    public d[] fq(final int n) {
        return new d[n];
    }
}
