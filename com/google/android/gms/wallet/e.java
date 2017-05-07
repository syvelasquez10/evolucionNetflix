// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<FullWalletRequest>
{
    static void a(final FullWalletRequest fullWalletRequest, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, fullWalletRequest.getVersionCode());
        b.a(parcel, 2, fullWalletRequest.Gn, false);
        b.a(parcel, 3, fullWalletRequest.Go, false);
        b.a(parcel, 4, (Parcelable)fullWalletRequest.Gu, n, false);
        b.D(parcel, o);
    }
    
    public FullWalletRequest aI(final Parcel parcel) {
        Cart cart = null;
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
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
                case 4: {
                    cart = a.a(parcel, j, Cart.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new FullWalletRequest(g, i, m, cart);
    }
    
    public FullWalletRequest[] bA(final int n) {
        return new FullWalletRequest[n];
    }
}
