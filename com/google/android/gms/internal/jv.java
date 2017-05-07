// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class jv implements Parcelable$Creator<ju>
{
    static void a(final ju ju, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, ju.getVersionCode());
        b.a(parcel, 2, ju.ado);
        b.a(parcel, 3, ju.adp);
        b.F(parcel, p3);
    }
    
    public ju bv(final Parcel parcel) {
        long i = 0L;
        final int o = a.o(parcel);
        int g = 0;
        long j = 0L;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    j = a.i(parcel, n);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ju(g, j, i);
    }
    
    public ju[] cJ(final int n) {
        return new ju[n];
    }
}
