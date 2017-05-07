// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.List;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.location.LocationRequest;
import android.app.PendingIntent;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class o implements Parcelable$Creator<n>
{
    static void a(final n n, final Parcel parcel, final int n2) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)n.getDataSource(), n2, false);
        b.c(parcel, 1000, n.getVersionCode());
        b.a(parcel, 2, (Parcelable)n.getDataType(), n2, false);
        b.a(parcel, 3, n.jq(), false);
        b.c(parcel, 4, n.Uq);
        b.c(parcel, 5, n.Ur);
        b.a(parcel, 6, n.getSamplingRateMicros());
        b.a(parcel, 7, n.jn());
        b.a(parcel, 8, (Parcelable)n.jl(), n2, false);
        b.a(parcel, 9, n.jm());
        b.c(parcel, 10, n.iQ());
        b.c(parcel, 11, n.jo(), false);
        b.a(parcel, 12, n.jp());
        b.H(parcel, d);
    }
    
    public n bI(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        DataSource dataSource = null;
        DataType dataType = null;
        IBinder p = null;
        int g2 = 0;
        int g3 = 0;
        long i = 0L;
        long j = 0L;
        PendingIntent pendingIntent = null;
        long k = 0L;
        int g4 = 0;
        List<LocationRequest> c2 = null;
        long l = 0L;
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
                case 2: {
                    dataType = a.a(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 3: {
                    p = a.p(parcel, b);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 7: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 8: {
                    pendingIntent = a.a(parcel, b, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
                case 9: {
                    k = a.i(parcel, b);
                    continue;
                }
                case 10: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 11: {
                    c2 = a.c(parcel, b, (android.os.Parcelable$Creator<LocationRequest>)LocationRequest.CREATOR);
                    continue;
                }
                case 12: {
                    l = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new n(g, dataSource, dataType, p, g2, g3, i, j, pendingIntent, k, g4, c2, l);
    }
    
    public n[] cZ(final int n) {
        return new n[n];
    }
}
