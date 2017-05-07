// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class i implements Parcelable$Creator<NotFilter>
{
    static void a(final NotFilter notFilter, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1000, notFilter.xH);
        b.a(parcel, 1, (Parcelable)notFilter.GT, n, false);
        b.F(parcel, p3);
    }
    
    public NotFilter[] aP(final int n) {
        return new NotFilter[n];
    }
    
    public NotFilter al(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        FilterHolder filterHolder = null;
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
                    filterHolder = a.a(parcel, n, FilterHolder.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new NotFilter(g, filterHolder);
    }
}
