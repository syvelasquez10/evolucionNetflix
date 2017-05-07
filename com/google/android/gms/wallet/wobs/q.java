// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class q implements Parcelable$Creator<p>
{
    static void a(final p p3, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, p3.getVersionCode());
        b.a(parcel, 2, p3.auy, false);
        b.a(parcel, 3, p3.tG, false);
        b.a(parcel, 4, (Parcelable)p3.auC, n, false);
        b.a(parcel, 5, (Parcelable)p3.auD, n, false);
        b.a(parcel, 6, (Parcelable)p3.auE, n, false);
        b.H(parcel, d);
    }
    
    public p dO(final Parcel parcel) {
        n n = null;
        final int c = a.C(parcel);
        int g = 0;
        n n2 = null;
        l l = null;
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
                    l = a.a(parcel, b, com.google.android.gms.wallet.wobs.l.CREATOR);
                    continue;
                }
                case 5: {
                    n2 = a.a(parcel, b, com.google.android.gms.wallet.wobs.n.CREATOR);
                    continue;
                }
                case 6: {
                    n = a.a(parcel, b, com.google.android.gms.wallet.wobs.n.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new p(g, o2, o, l, n2, n);
    }
    
    public p[] fQ(final int n) {
        return new p[n];
    }
}
