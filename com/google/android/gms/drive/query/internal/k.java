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

public class k implements Parcelable$Creator<NotFilter>
{
    static void a(final NotFilter notFilter, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1000, notFilter.BR);
        b.a(parcel, 1, (Parcelable)notFilter.QQ, n, false);
        b.H(parcel, d);
    }
    
    public NotFilter aS(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < c) {
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
                    filterHolder = a.a(parcel, b, FilterHolder.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new NotFilter(g, filterHolder);
    }
    
    public NotFilter[] ce(final int n) {
        return new NotFilter[n];
    }
}
