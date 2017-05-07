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
    
    static void a(final LocationRequest locationRequest, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, locationRequest.mPriority);
        b.c(parcel, 1000, locationRequest.getVersionCode());
        b.a(parcel, 2, locationRequest.xB);
        b.a(parcel, 3, locationRequest.xC);
        b.a(parcel, 4, locationRequest.xD);
        b.a(parcel, 5, locationRequest.xu);
        b.c(parcel, 6, locationRequest.xE);
        b.a(parcel, 7, locationRequest.xF);
        b.D(parcel, o);
    }
    
    public LocationRequest createFromParcel(final Parcel parcel) {
        boolean c = false;
        final int n = a.n(parcel);
        int g = 102;
        long h = 3600000L;
        long h2 = 600000L;
        long h3 = Long.MAX_VALUE;
        int g2 = Integer.MAX_VALUE;
        float j = 0.0f;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 1000: {
                    g3 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    h = a.h(parcel, m);
                    continue;
                }
                case 3: {
                    h2 = a.h(parcel, m);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, m);
                    continue;
                }
                case 5: {
                    h3 = a.h(parcel, m);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 7: {
                    j = a.j(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new LocationRequest(g3, g, h, h2, c, h3, g2, j);
    }
    
    public LocationRequest[] newArray(final int n) {
        return new LocationRequest[n];
    }
}
