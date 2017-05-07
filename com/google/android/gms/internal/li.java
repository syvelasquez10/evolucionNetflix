// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class li implements Parcelable$Creator<lh>
{
    static void a(final lh lh, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)lh.getDataSource(), n, false);
        b.c(parcel, 1000, lh.getVersionCode());
        b.H(parcel, d);
    }
    
    public lh bz(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        DataSource dataSource = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    dataSource = a.a(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new lh(g, dataSource);
    }
    
    public lh[] cQ(final int n) {
        return new lh[n];
    }
}
