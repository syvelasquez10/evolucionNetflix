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

public class f implements Parcelable$Creator<LogicalFilter>
{
    static void a(final LogicalFilter logicalFilter, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1000, logicalFilter.kg);
        b.a(parcel, 1, (Parcelable)logicalFilter.rR, n, false);
        b.b(parcel, 2, logicalFilter.sb, false);
        b.D(parcel, o);
    }
    
    public LogicalFilter V(final Parcel parcel) {
        List<FilterHolder> c = null;
        final int n = a.n(parcel);
        int g = 0;
        Operator operator = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 1: {
                    operator = a.a(parcel, m, Operator.CREATOR);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, m, FilterHolder.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new LogicalFilter(g, operator, c);
    }
    
    public LogicalFilter[] av(final int n) {
        return new LogicalFilter[n];
    }
}
