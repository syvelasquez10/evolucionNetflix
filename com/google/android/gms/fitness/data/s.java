// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class s implements Parcelable$Creator<Subscription>
{
    static void a(final Subscription subscription, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)subscription.getDataSource(), n, false);
        b.c(parcel, 1000, subscription.getVersionCode());
        b.a(parcel, 2, (Parcelable)subscription.getDataType(), n, false);
        b.a(parcel, 3, subscription.getSamplingRateMicros());
        b.c(parcel, 4, subscription.iQ());
        b.H(parcel, d);
    }
    
    public Subscription bw(final Parcel parcel) {
        DataType dataType = null;
        int g = 0;
        final int c = a.C(parcel);
        long i = 0L;
        DataSource dataSource = null;
        int g2 = 0;
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
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    dataType = a.a(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new Subscription(g2, dataSource, dataType, i, g);
    }
    
    public Subscription[] cN(final int n) {
        return new Subscription[n];
    }
}
