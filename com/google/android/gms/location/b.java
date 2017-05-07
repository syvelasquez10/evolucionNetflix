// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<LocationRequest>
{
    static void a(final LocationRequest locationRequest, final Parcel parcel, int d) {
        d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, locationRequest.mPriority);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, locationRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, locationRequest.aeh);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, locationRequest.aei);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, locationRequest.Uz);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, locationRequest.adX);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, locationRequest.aej);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, locationRequest.aek);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, locationRequest.ael);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public LocationRequest cs(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        int g2 = 102;
        long i = 3600000L;
        long j = 600000L;
        boolean c2 = false;
        long k = Long.MAX_VALUE;
        int g3 = Integer.MAX_VALUE;
        float l = 0.0f;
        long m = 0L;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 5: {
                    k = a.i(parcel, b);
                    continue;
                }
                case 6: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 7: {
                    l = a.l(parcel, b);
                    continue;
                }
                case 8: {
                    m = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new LocationRequest(g, g2, i, j, c2, k, g3, l, m);
    }
    
    public LocationRequest[] ec(final int n) {
        return new LocationRequest[n];
    }
}
