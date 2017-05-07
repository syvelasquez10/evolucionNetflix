// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class l implements Parcelable$Creator<ProxyCard>
{
    static void a(final ProxyCard proxyCard, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, proxyCard.getVersionCode());
        b.a(parcel, 2, proxyCard.GY, false);
        b.a(parcel, 3, proxyCard.GZ, false);
        b.c(parcel, 4, proxyCard.Ha);
        b.c(parcel, 5, proxyCard.Hb);
        b.D(parcel, o);
    }
    
    public ProxyCard aP(final Parcel parcel) {
        String m = null;
        int g = 0;
        final int n = a.n(parcel);
        int g2 = 0;
        String i = null;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int j = a.m(parcel);
            switch (a.M(j)) {
                default: {
                    a.b(parcel, j);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, j);
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
                    g2 = a.g(parcel, j);
                    continue;
                }
                case 5: {
                    g = a.g(parcel, j);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ProxyCard(g3, i, m, g2, g);
    }
    
    public ProxyCard[] bH(final int n) {
        return new ProxyCard[n];
    }
}
