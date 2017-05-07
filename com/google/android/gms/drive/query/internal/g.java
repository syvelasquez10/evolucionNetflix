// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<LogicalFilter>
{
    static void a(final LogicalFilter logicalFilter, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1000, logicalFilter.xH);
        b.a(parcel, 1, (Parcelable)logicalFilter.GG, n, false);
        b.b(parcel, 2, logicalFilter.GS, false);
        b.F(parcel, p3);
    }
    
    public LogicalFilter[] aN(final int n) {
        return new LogicalFilter[n];
    }
    
    public LogicalFilter aj(final Parcel parcel) {
        List<FilterHolder> c = null;
        final int o = a.o(parcel);
        int g = 0;
        Operator operator = null;
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
                case 1: {
                    operator = a.a(parcel, n, Operator.CREATOR);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, n, FilterHolder.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new LogicalFilter(g, operator, c);
    }
}
