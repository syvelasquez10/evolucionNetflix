// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class gu implements Parcelable$Creator<gt>
{
    static void a(final gt gt, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, gt.dO());
        b.c(parcel, 1000, gt.kg);
        b.b(parcel, 2, gt.yg, false);
        b.a(parcel, 3, gt.dP(), false);
        b.a(parcel, 4, gt.dQ(), false);
        b.a(parcel, 5, gt.dR());
        b.D(parcel, o);
    }
    
    public gt[] aY(final int n) {
        return new gt[n];
    }
    
    public gt aj(final Parcel parcel) {
        String m = null;
        boolean c = false;
        final int n = a.n(parcel);
        String i = null;
        List<gx> c2 = null;
        int g = 0;
        int g2 = 0;
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
                case 1000: {
                    g2 = a.g(parcel, j);
                    continue;
                }
                case 2: {
                    c2 = a.c(parcel, j, (android.os.Parcelable$Creator<gx>)gx.CREATOR);
                    continue;
                }
                case 3: {
                    i = a.m(parcel, j);
                    continue;
                }
                case 4: {
                    m = a.m(parcel, j);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, j);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new gt(g2, g, c2, i, m, c);
    }
}
