// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<FullWalletRequest>
{
    static void a(final FullWalletRequest fullWalletRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, fullWalletRequest.getVersionCode());
        b.a(parcel, 2, fullWalletRequest.asq, false);
        b.a(parcel, 3, fullWalletRequest.asr, false);
        b.a(parcel, 4, (Parcelable)fullWalletRequest.asA, n, false);
        b.H(parcel, d);
    }
    
    public FullWalletRequest ds(final Parcel parcel) {
        Cart cart = null;
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
                    cart = a.a(parcel, b, Cart.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new FullWalletRequest(g, o2, o, cart);
    }
    
    public FullWalletRequest[] fs(final int n) {
        return new FullWalletRequest[n];
    }
}
