// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class n implements Parcelable$Creator<OfferWalletObject>
{
    static void a(final OfferWalletObject offerWalletObject, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, offerWalletObject.getVersionCode());
        b.a(parcel, 2, offerWalletObject.fl, false);
        b.a(parcel, 3, offerWalletObject.ats, false);
        b.a(parcel, 4, (Parcelable)offerWalletObject.att, n, false);
        b.H(parcel, d);
    }
    
    public OfferWalletObject dz(final Parcel parcel) {
        CommonWalletObject commonWalletObject = null;
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
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
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    commonWalletObject = a.a(parcel, b, CommonWalletObject.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new OfferWalletObject(g, o2, o, commonWalletObject);
    }
    
    public OfferWalletObject[] fz(final int n) {
        return new OfferWalletObject[n];
    }
}
