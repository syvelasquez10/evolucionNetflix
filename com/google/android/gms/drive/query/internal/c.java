// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<FieldWithSortOrder>
{
    static void a(final FieldWithSortOrder fieldWithSortOrder, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1000, fieldWithSortOrder.xH);
        b.a(parcel, 1, fieldWithSortOrder.FM, false);
        b.a(parcel, 2, fieldWithSortOrder.GJ);
        b.F(parcel, p3);
    }
    
    public FieldWithSortOrder[] aK(final int n) {
        return new FieldWithSortOrder[n];
    }
    
    public FieldWithSortOrder ag(final Parcel parcel) {
        boolean c = false;
        final int o = a.o(parcel);
        String n = null;
        int g = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 1: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new FieldWithSortOrder(g, n, c);
    }
}
