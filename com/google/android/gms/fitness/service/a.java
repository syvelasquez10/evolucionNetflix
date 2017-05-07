// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.service;

import android.os.IBinder;
import com.google.android.gms.fitness.data.DataSource;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<FitnessSensorServiceRequest>
{
    static void a(final FitnessSensorServiceRequest fitnessSensorServiceRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)fitnessSensorServiceRequest.getDataSource(), n, false);
        b.c(parcel, 1000, fitnessSensorServiceRequest.getVersionCode());
        b.a(parcel, 2, fitnessSensorServiceRequest.jq(), false);
        b.a(parcel, 3, fitnessSensorServiceRequest.getSamplingRateMicros());
        b.a(parcel, 4, fitnessSensorServiceRequest.getBatchIntervalMicros());
        b.H(parcel, d);
    }
    
    public FitnessSensorServiceRequest cc(final Parcel parcel) {
        long i = 0L;
        IBinder p = null;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        long j = 0L;
        DataSource dataSource = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    dataSource = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    p = com.google.android.gms.common.internal.safeparcel.a.p(parcel, b);
                    continue;
                }
                case 3: {
                    j = com.google.android.gms.common.internal.safeparcel.a.i(parcel, b);
                    continue;
                }
                case 4: {
                    i = com.google.android.gms.common.internal.safeparcel.a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c, parcel);
        }
        return new FitnessSensorServiceRequest(g, dataSource, p, j, i);
    }
    
    public FitnessSensorServiceRequest[] du(final int n) {
        return new FitnessSensorServiceRequest[n];
    }
}
