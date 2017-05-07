// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ht implements Parcelable$Creator<hs>
{
    static void a(final hs hs, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.a(parcel, 1, hs.Rl, false);
        b.c(parcel, 1000, hs.versionCode);
        b.a(parcel, 2, hs.Rm, false);
        b.F(parcel, p3);
    }
    
    public hs aI(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        int g = 0;
        String n2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new hs(g, n2, n);
    }
    
    public hs[] bJ(final int n) {
        return new hs[n];
    }
}
