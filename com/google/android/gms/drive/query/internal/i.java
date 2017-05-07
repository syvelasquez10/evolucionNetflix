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

public class i implements Parcelable$Creator<LogicalFilter>
{
    static void a(final LogicalFilter logicalFilter, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1000, logicalFilter.BR);
        b.a(parcel, 1, (Parcelable)logicalFilter.QC, n, false);
        b.c(parcel, 2, logicalFilter.QP, false);
        b.H(parcel, d);
    }
    
    public LogicalFilter aQ(final Parcel parcel) {
        List<FilterHolder> c = null;
        final int c2 = a.C(parcel);
        int g = 0;
        Operator operator = null;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 1: {
                    operator = a.a(parcel, b, Operator.CREATOR);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, b, FilterHolder.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new LogicalFilter(g, operator, c);
    }
    
    public LogicalFilter[] cc(final int n) {
        return new LogicalFilter[n];
    }
}
