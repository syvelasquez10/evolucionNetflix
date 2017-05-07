// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<DataReadResult>
{
    static void a(final DataReadResult dataReadResult, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 1, dataReadResult.jH(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataReadResult.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)dataReadResult.getStatus(), n, false);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 3, dataReadResult.jG(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, dataReadResult.jF());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, dataReadResult.iG(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, dataReadResult.jI(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public DataReadResult bW(final Parcel parcel) {
        int g = 0;
        List<DataType> c = null;
        final int c2 = a.C(parcel);
        final ArrayList<RawDataSet> list = new ArrayList<RawDataSet>();
        final ArrayList<RawBucket> list2 = new ArrayList<RawBucket>();
        List<DataSource> c3 = null;
        Status status = null;
        int g2 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    a.a(parcel, b, list, this.getClass().getClassLoader());
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    status = a.a(parcel, b, (android.os.Parcelable$Creator<Status>)Status.CREATOR);
                    continue;
                }
                case 3: {
                    a.a(parcel, b, list2, this.getClass().getClassLoader());
                    continue;
                }
                case 5: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    c3 = a.c(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 7: {
                    c = a.c(parcel, b, DataType.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new DataReadResult(g2, list, status, list2, g, c3, c);
    }
    
    public DataReadResult[] do(final int n) {
        return new DataReadResult[n];
    }
}
