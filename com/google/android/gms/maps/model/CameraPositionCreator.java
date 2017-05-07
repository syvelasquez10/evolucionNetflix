// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class CameraPositionCreator implements Parcelable$Creator<CameraPosition>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final CameraPosition cameraPosition, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, cameraPosition.getVersionCode());
        b.a(parcel, 2, (Parcelable)cameraPosition.target, n, false);
        b.a(parcel, 3, cameraPosition.zoom);
        b.a(parcel, 4, cameraPosition.tilt);
        b.a(parcel, 5, cameraPosition.bearing);
        b.D(parcel, o);
    }
    
    public CameraPosition createFromParcel(final Parcel parcel) {
        float j = 0.0f;
        final int n = a.n(parcel);
        int g = 0;
        LatLng latLng = null;
        float i = 0.0f;
        float k = 0.0f;
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
                case 2: {
                    latLng = a.a(parcel, m, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    k = a.j(parcel, m);
                    continue;
                }
                case 4: {
                    i = a.j(parcel, m);
                    continue;
                }
                case 5: {
                    j = a.j(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new CameraPosition(g, latLng, k, i, j);
    }
    
    public CameraPosition[] newArray(final int n) {
        return new CameraPosition[n];
    }
}
