// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hh implements Parcelable$Creator<hg>
{
    static void a(final hg hg, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.b(parcel, 1, hg.OA, false);
        b.c(parcel, 1000, hg.xH);
        b.a(parcel, 2, hg.hW(), false);
        b.a(parcel, 3, hg.hX());
        b.F(parcel, p3);
    }
    
    public hg aD(final Parcel parcel) {
        String n = null;
        boolean c = false;
        final int o = a.o(parcel);
        List<hm> c2 = null;
        int g = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    c2 = a.c(parcel, n2, (android.os.Parcelable$Creator<hm>)hm.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new hg(g, c2, n, c);
    }
    
    public hg[] bE(final int n) {
        return new hg[n];
    }
}
