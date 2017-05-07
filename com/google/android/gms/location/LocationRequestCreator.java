// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class LocationRequestCreator implements Parcelable$Creator<LocationRequest>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final LocationRequest locationRequest, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, locationRequest.mPriority);
        b.c(parcel, 1000, locationRequest.getVersionCode());
        b.a(parcel, 2, locationRequest.Oc);
        b.a(parcel, 3, locationRequest.Od);
        b.a(parcel, 4, locationRequest.Oe);
        b.a(parcel, 5, locationRequest.NV);
        b.c(parcel, 6, locationRequest.Of);
        b.a(parcel, 7, locationRequest.Og);
        b.F(parcel, p3);
    }
    
    public LocationRequest createFromParcel(final Parcel parcel) {
        boolean c = false;
        final int o = a.o(parcel);
        int g = 102;
        long i = 3600000L;
        long j = 600000L;
        long k = Long.MAX_VALUE;
        int g2 = Integer.MAX_VALUE;
        float l = 0.0f;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 1000: {
                    g3 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    i = a.i(parcel, n);
                    continue;
                }
                case 3: {
                    j = a.i(parcel, n);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, n);
                    continue;
                }
                case 5: {
                    k = a.i(parcel, n);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 7: {
                    l = a.k(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new LocationRequest(g3, g, i, j, c, k, g2, l);
    }
    
    public LocationRequest[] newArray(final int n) {
        return new LocationRequest[n];
    }
}
