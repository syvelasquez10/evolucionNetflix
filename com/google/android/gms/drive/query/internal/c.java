// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<FilterHolder>
{
    static void a(final FilterHolder filterHolder, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, (Parcelable)filterHolder.rU, n, false);
        b.c(parcel, 1000, filterHolder.kg);
        b.a(parcel, 2, (Parcelable)filterHolder.rV, n, false);
        b.a(parcel, 3, (Parcelable)filterHolder.rW, n, false);
        b.a(parcel, 4, (Parcelable)filterHolder.rX, n, false);
        b.a(parcel, 5, (Parcelable)filterHolder.rY, n, false);
        b.D(parcel, o);
    }
    
    public FilterHolder T(final Parcel parcel) {
        InFilter<?> inFilter = null;
        final int n = a.n(parcel);
        int g = 0;
        NotFilter notFilter = null;
        LogicalFilter logicalFilter = null;
        FieldOnlyFilter fieldOnlyFilter = null;
        ComparisonFilter<?> comparisonFilter = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    comparisonFilter = a.a(parcel, m, (android.os.Parcelable$Creator<ComparisonFilter<?>>)ComparisonFilter.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    fieldOnlyFilter = a.a(parcel, m, FieldOnlyFilter.CREATOR);
                    continue;
                }
                case 3: {
                    logicalFilter = a.a(parcel, m, LogicalFilter.CREATOR);
                    continue;
                }
                case 4: {
                    notFilter = a.a(parcel, m, NotFilter.CREATOR);
                    continue;
                }
                case 5: {
                    inFilter = a.a(parcel, m, (android.os.Parcelable$Creator<InFilter<?>>)InFilter.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new FilterHolder(g, comparisonFilter, fieldOnlyFilter, logicalFilter, notFilter, inFilter);
    }
    
    public FilterHolder[] at(final int n) {
        return new FilterHolder[n];
    }
}
