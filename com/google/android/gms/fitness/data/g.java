// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<DataSource>
{
    static void a(final DataSource dataSource, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)dataSource.getDataType(), n, false);
        b.c(parcel, 1000, dataSource.getVersionCode());
        b.a(parcel, 2, dataSource.getName(), false);
        b.c(parcel, 3, dataSource.getType());
        b.a(parcel, 4, (Parcelable)dataSource.getDevice(), n, false);
        b.a(parcel, 5, (Parcelable)dataSource.iH(), n, false);
        b.a(parcel, 6, dataSource.getStreamName(), false);
        b.a(parcel, 7, dataSource.iJ());
        b.H(parcel, d);
    }
    
    public DataSource bn(final Parcel parcel) {
        boolean c = false;
        String o = null;
        final int c2 = a.C(parcel);
        com.google.android.gms.fitness.data.a a = null;
        Device device = null;
        int g = 0;
        String o2 = null;
        DataType dataType = null;
        int g2 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    dataType = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 1000: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 3: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 4: {
                    device = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, Device.CREATOR);
                    continue;
                }
                case 5: {
                    a = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, com.google.android.gms.fitness.data.a.CREATOR);
                    continue;
                }
                case 6: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 7: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new DataSource(g2, dataType, o2, g, device, a, o, c);
    }
    
    public DataSource[] cE(final int n) {
        return new DataSource[n];
    }
}
