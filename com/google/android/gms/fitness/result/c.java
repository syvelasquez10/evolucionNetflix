// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import java.util.List;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<DataSourcesResult>
{
    static void a(final DataSourcesResult dataSourcesResult, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, dataSourcesResult.getDataSources(), false);
        b.c(parcel, 1000, dataSourcesResult.getVersionCode());
        b.a(parcel, 2, (Parcelable)dataSourcesResult.getStatus(), n, false);
        b.H(parcel, d);
    }
    
    public DataSourcesResult bX(final Parcel parcel) {
        Status status = null;
        final int c = a.C(parcel);
        int g = 0;
        List<DataSource> c2 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c2 = a.c(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    status = a.a(parcel, b, (android.os.Parcelable$Creator<Status>)Status.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new DataSourcesResult(g, c2, status);
    }
    
    public DataSourcesResult[] dp(final int n) {
        return new DataSourcesResult[n];
    }
}
