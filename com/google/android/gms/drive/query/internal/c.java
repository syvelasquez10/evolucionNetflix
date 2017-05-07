// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<FieldWithSortOrder>
{
    static void a(final FieldWithSortOrder fieldWithSortOrder, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1000, fieldWithSortOrder.BR);
        b.a(parcel, 1, fieldWithSortOrder.Pt, false);
        b.a(parcel, 2, fieldWithSortOrder.QF);
        b.H(parcel, d);
    }
    
    public FieldWithSortOrder aM(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        String o = null;
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
                    o = a.o(parcel, b);
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
        return new FieldWithSortOrder(g, o, c);
    }
    
    public FieldWithSortOrder[] bY(final int n) {
        return new FieldWithSortOrder[n];
    }
}
