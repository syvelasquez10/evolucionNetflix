// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import java.util.List;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<SortOrder>
{
    static void a(final SortOrder sortOrder, final Parcel parcel, int p3) {
        p3 = com.google.android.gms.common.internal.safeparcel.b.p(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, sortOrder.xH);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 1, sortOrder.GF, false);
        com.google.android.gms.common.internal.safeparcel.b.F(parcel, p3);
    }
    
    public SortOrder[] aH(final int n) {
        return new SortOrder[n];
    }
    
    public SortOrder ad(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        Object c = null;
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
                    c = a.c(parcel, n, (android.os.Parcelable$Creator<Object>)FieldWithSortOrder.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new SortOrder(g, (List<FieldWithSortOrder>)c);
    }
}
