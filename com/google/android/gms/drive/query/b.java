// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<SortOrder>
{
    static void a(final SortOrder sortOrder, final Parcel parcel, int d) {
        d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, sortOrder.BR);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, sortOrder.QA, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, sortOrder.QB);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public SortOrder aJ(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        Object c3 = null;
        int g = 0;
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
                    c3 = a.c(parcel, b, (android.os.Parcelable$Creator<Object>)FieldWithSortOrder.CREATOR);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new SortOrder(g, (List<FieldWithSortOrder>)c3, c);
    }
    
    public SortOrder[] bV(final int n) {
        return new SortOrder[n];
    }
}
