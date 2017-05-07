// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<FilterHolder>
{
    static void a(final FilterHolder filterHolder, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.a(parcel, 1, (Parcelable)filterHolder.GK, n, false);
        b.c(parcel, 1000, filterHolder.xH);
        b.a(parcel, 2, (Parcelable)filterHolder.GL, n, false);
        b.a(parcel, 3, (Parcelable)filterHolder.GM, n, false);
        b.a(parcel, 4, (Parcelable)filterHolder.GN, n, false);
        b.a(parcel, 5, (Parcelable)filterHolder.GO, n, false);
        b.a(parcel, 6, (Parcelable)filterHolder.GP, n, false);
        b.F(parcel, p3);
    }
    
    public FilterHolder[] aL(final int n) {
        return new FilterHolder[n];
    }
    
    public FilterHolder ah(final Parcel parcel) {
        MatchAllFilter matchAllFilter = null;
        final int o = a.o(parcel);
        int g = 0;
        InFilter<?> inFilter = null;
        NotFilter notFilter = null;
        LogicalFilter logicalFilter = null;
        FieldOnlyFilter fieldOnlyFilter = null;
        ComparisonFilter<?> comparisonFilter = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    comparisonFilter = a.a(parcel, n, (android.os.Parcelable$Creator<ComparisonFilter<?>>)ComparisonFilter.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    fieldOnlyFilter = a.a(parcel, n, FieldOnlyFilter.CREATOR);
                    continue;
                }
                case 3: {
                    logicalFilter = a.a(parcel, n, LogicalFilter.CREATOR);
                    continue;
                }
                case 4: {
                    notFilter = a.a(parcel, n, NotFilter.CREATOR);
                    continue;
                }
                case 5: {
                    inFilter = a.a(parcel, n, (android.os.Parcelable$Creator<InFilter<?>>)InFilter.CREATOR);
                    continue;
                }
                case 6: {
                    matchAllFilter = a.a(parcel, n, (android.os.Parcelable$Creator<MatchAllFilter>)MatchAllFilter.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new FilterHolder(g, comparisonFilter, fieldOnlyFilter, logicalFilter, notFilter, inFilter, matchAllFilter);
    }
}
