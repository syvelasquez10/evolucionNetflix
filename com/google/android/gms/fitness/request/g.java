// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.List;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<DataSourcesRequest>
{
    static void a(final DataSourcesRequest dataSourcesRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, dataSourcesRequest.getDataTypes(), false);
        b.c(parcel, 1000, dataSourcesRequest.getVersionCode());
        b.a(parcel, 2, dataSourcesRequest.jj(), false);
        b.a(parcel, 3, dataSourcesRequest.ji());
        b.H(parcel, d);
    }
    
    public DataSourcesRequest bE(final Parcel parcel) {
        List<Integer> b = null;
        boolean c = false;
        final int c2 = a.C(parcel);
        List<DataType> c3 = null;
        int g = 0;
        while (parcel.dataPosition() < c2) {
            final int b2 = a.B(parcel);
            switch (a.aD(b2)) {
                default: {
                    a.b(parcel, b2);
                    continue;
                }
                case 1: {
                    c3 = a.c(parcel, b2, DataType.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b2);
                    continue;
                }
                case 2: {
                    b = a.B(parcel, b2);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new DataSourcesRequest(g, c3, b, c);
    }
    
    public DataSourcesRequest[] cV(final int n) {
        return new DataSourcesRequest[n];
    }
}
