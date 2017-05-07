// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class f implements Parcelable$Creator<DataSet>
{
    static void a(final DataSet set, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)set.getDataSource(), n, false);
        b.c(parcel, 1000, set.getVersionCode());
        b.a(parcel, 2, (Parcelable)set.getDataType(), n, false);
        b.d(parcel, 3, set.iF(), false);
        b.c(parcel, 4, set.iG(), false);
        b.a(parcel, 5, set.iB());
        b.H(parcel, d);
    }
    
    public DataSet bm(final Parcel parcel) {
        boolean c = false;
        List<DataSource> c2 = null;
        final int c3 = a.C(parcel);
        final ArrayList<RawDataPoint> list = new ArrayList<RawDataPoint>();
        DataType dataType = null;
        DataSource dataSource = null;
        int g = 0;
        while (parcel.dataPosition() < c3) {
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
                case 2: {
                    dataType = a.a(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 3: {
                    a.a(parcel, b, list, this.getClass().getClassLoader());
                    continue;
                }
                case 4: {
                    c2 = a.c(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c3) {
            throw new a.a("Overread allowed size end=" + c3, parcel);
        }
        return new DataSet(g, dataSource, dataType, list, c2, c);
    }
    
    public DataSet[] cD(final int n) {
        return new DataSet[n];
    }
}
