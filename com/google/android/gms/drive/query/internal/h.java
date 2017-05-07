// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class h implements Parcelable$Creator<MatchAllFilter>
{
    static void a(final MatchAllFilter matchAllFilter, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1000, matchAllFilter.xH);
        b.F(parcel, p3);
    }
    
    public MatchAllFilter[] aO(final int n) {
        return new MatchAllFilter[n];
    }
    
    public MatchAllFilter ak(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new MatchAllFilter(g);
    }
}
