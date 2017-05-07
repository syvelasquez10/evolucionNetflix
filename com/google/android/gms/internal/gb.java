// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class gb implements Parcelable$Creator<ga.a>
{
    static void a(final ga.a a, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, a.getVersionCode());
        b.c(parcel, 2, a.eW());
        b.a(parcel, 3, a.fc());
        b.c(parcel, 4, a.eX());
        b.a(parcel, 5, a.fd());
        b.a(parcel, 6, a.fe(), false);
        b.c(parcel, 7, a.ff());
        b.a(parcel, 8, a.fh(), false);
        b.a(parcel, 9, (Parcelable)a.fj(), n, false);
        b.F(parcel, p3);
    }
    
    public ga.a[] V(final int n) {
        return new ga.a[n];
    }
    
    public ga.a t(final Parcel parcel) {
        fv fv = null;
        int g = 0;
        final int o = a.o(parcel);
        String n = null;
        String n2 = null;
        boolean c = false;
        int g2 = 0;
        boolean c2 = false;
        int g3 = 0;
        int g4 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g4 = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    g3 = a.g(parcel, n3);
                    continue;
                }
                case 3: {
                    c2 = a.c(parcel, n3);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, n3);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, n3);
                    continue;
                }
                case 6: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 7: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 8: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 9: {
                    fv = a.a(parcel, n3, (android.os.Parcelable$Creator<fv>)com.google.android.gms.internal.fv.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ga.a(g4, g3, c2, g2, c, n2, g, n, fv);
    }
}
