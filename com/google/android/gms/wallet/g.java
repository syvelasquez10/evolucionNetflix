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
        final int p3 = b.p(parcel);
        b.c(parcel, 1, fullWalletRequest.getVersionCode());
        b.a(parcel, 2, fullWalletRequest.abh, false);
        b.a(parcel, 3, fullWalletRequest.abi, false);
        b.a(parcel, 4, (Parcelable)fullWalletRequest.abr, n, false);
        b.F(parcel, p3);
    }
    
    public FullWalletRequest bc(final Parcel parcel) {
        Cart cart = null;
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        String n2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 4: {
                    cart = a.a(parcel, n3, Cart.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new FullWalletRequest(g, n2, n, cart);
    }
    
    public FullWalletRequest[] co(final int n) {
        return new FullWalletRequest[n];
    }
}
