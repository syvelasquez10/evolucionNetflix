// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<FilterHolder>
{
    static void a(final FilterHolder filterHolder, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)filterHolder.QG, n, false);
        b.c(parcel, 1000, filterHolder.BR);
        b.a(parcel, 2, (Parcelable)filterHolder.QH, n, false);
        b.a(parcel, 3, (Parcelable)filterHolder.QI, n, false);
        b.a(parcel, 4, (Parcelable)filterHolder.QJ, n, false);
        b.a(parcel, 5, (Parcelable)filterHolder.QK, n, false);
        b.a(parcel, 6, (Parcelable)filterHolder.QL, n, false);
        b.a(parcel, 7, (Parcelable)filterHolder.QM, n, false);
        b.H(parcel, d);
    }
    
    public FilterHolder aN(final Parcel parcel) {
        HasFilter<?> hasFilter = null;
        final int c = a.C(parcel);
        int g = 0;
        MatchAllFilter matchAllFilter = null;
        InFilter<?> inFilter = null;
        NotFilter notFilter = null;
        LogicalFilter logicalFilter = null;
        FieldOnlyFilter fieldOnlyFilter = null;
        ComparisonFilter<?> comparisonFilter = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    comparisonFilter = a.a(parcel, b, (android.os.Parcelable$Creator<ComparisonFilter<?>>)ComparisonFilter.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    fieldOnlyFilter = a.a(parcel, b, FieldOnlyFilter.CREATOR);
                    continue;
                }
                case 3: {
                    logicalFilter = a.a(parcel, b, LogicalFilter.CREATOR);
                    continue;
                }
                case 4: {
                    notFilter = a.a(parcel, b, NotFilter.CREATOR);
                    continue;
                }
                case 5: {
                    inFilter = a.a(parcel, b, (android.os.Parcelable$Creator<InFilter<?>>)InFilter.CREATOR);
                    continue;
                }
                case 6: {
                    matchAllFilter = a.a(parcel, b, (android.os.Parcelable$Creator<MatchAllFilter>)MatchAllFilter.CREATOR);
                    continue;
                }
                case 7: {
                    hasFilter = a.a(parcel, b, (android.os.Parcelable$Creator<HasFilter<?>>)HasFilter.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new FilterHolder(g, comparisonFilter, fieldOnlyFilter, logicalFilter, notFilter, inFilter, matchAllFilter, hasFilter);
    }
    
    public FilterHolder[] bZ(final int n) {
        return new FilterHolder[n];
    }
}
