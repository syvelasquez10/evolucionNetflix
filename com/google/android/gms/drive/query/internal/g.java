// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<NotFilter>
{
    static void a(final NotFilter notFilter, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1000, notFilter.kg);
        b.a(parcel, 1, (Parcelable)notFilter.sc, n, false);
        b.D(parcel, o);
    }
    
    public NotFilter W(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 1: {
                    filterHolder = a.a(parcel, m, FilterHolder.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new NotFilter(g, filterHolder);
    }
    
    public NotFilter[] aw(final int n) {
        return new NotFilter[n];
    }
}
