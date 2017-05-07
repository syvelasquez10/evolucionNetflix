// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<FullWallet>
{
    static void a(final FullWallet fullWallet, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, fullWallet.getVersionCode());
        b.a(parcel, 2, fullWallet.Gn, false);
        b.a(parcel, 3, fullWallet.Go, false);
        b.a(parcel, 4, (Parcelable)fullWallet.Gp, n, false);
        b.a(parcel, 5, fullWallet.Gq, false);
        b.a(parcel, 6, (Parcelable)fullWallet.Gr, n, false);
        b.a(parcel, 7, (Parcelable)fullWallet.Gs, n, false);
        b.a(parcel, 8, fullWallet.Gt, false);
        b.D(parcel, o);
    }
    
    public FullWallet aH(final Parcel parcel) {
        String[] x = null;
        final int n = a.n(parcel);
        int g = 0;
        Address address = null;
        Address address2 = null;
        String m = null;
        ProxyCard proxyCard = null;
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
                    proxyCard = a.a(parcel, k, ProxyCard.CREATOR);
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
                    x = a.x(parcel, k);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new FullWallet(g, j, i, proxyCard, m, address2, address, x);
    }
    
    public FullWallet[] bz(final int n) {
        return new FullWallet[n];
    }
}
