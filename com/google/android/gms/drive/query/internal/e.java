// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<InFilter>
{
    static void a(final InFilter inFilter, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1000, inFilter.kg);
        b.a(parcel, 1, (Parcelable)inFilter.rS, n, false);
        b.D(parcel, o);
    }
    
    public InFilter U(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        MetadataBundle metadataBundle = null;
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
                    metadataBundle = a.a(parcel, m, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new InFilter(g, metadataBundle);
    }
    
    public InFilter[] au(final int n) {
        return new InFilter[n];
    }
}
