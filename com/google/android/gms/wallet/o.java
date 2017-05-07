// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class o implements Parcelable$Creator<ProxyCard>
{
    static void a(final ProxyCard proxyCard, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, proxyCard.getVersionCode());
        b.a(parcel, 2, proxyCard.ack, false);
        b.a(parcel, 3, proxyCard.acl, false);
        b.c(parcel, 4, proxyCard.acm);
        b.c(parcel, 5, proxyCard.acn);
        b.F(parcel, p3);
    }
    
    public ProxyCard bk(final Parcel parcel) {
        String n = null;
        int g = 0;
        final int o = a.o(parcel);
        int g2 = 0;
        String n2 = null;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n3);
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
                    g2 = a.g(parcel, n3);
                    continue;
                }
                case 5: {
                    g = a.g(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ProxyCard(g3, n2, n, g2, g);
    }
    
    public ProxyCard[] cw(final int n) {
        return new ProxyCard[n];
    }
}
