// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ai implements Parcelable$Creator<ah>
{
    static void a(final ah ah, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)ah.getDataType(), n, false);
        b.c(parcel, 1000, ah.getVersionCode());
        b.a(parcel, 2, (Parcelable)ah.getDataSource(), n, false);
        b.H(parcel, d);
    }
    
    public ah bU(final Parcel parcel) {
        DataSource dataSource = null;
        final int c = a.C(parcel);
        int g = 0;
        DataType dataType = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    dataType = a.a(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    dataSource = a.a(parcel, b, DataSource.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ah(g, dataType, dataSource);
    }
    
    public ah[] dm(final int n) {
        return new ah[n];
    }
}
