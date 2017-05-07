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
        final int p3 = b.p(parcel);
        b.c(parcel, 1, cameraPosition.getVersionCode());
        b.a(parcel, 2, (Parcelable)cameraPosition.target, n, false);
        b.a(parcel, 3, cameraPosition.zoom);
        b.a(parcel, 4, cameraPosition.tilt);
        b.a(parcel, 5, cameraPosition.bearing);
        b.F(parcel, p3);
    }
    
    public CameraPosition createFromParcel(final Parcel parcel) {
        float k = 0.0f;
        final int o = a.o(parcel);
        int g = 0;
        LatLng latLng = null;
        float i = 0.0f;
        float j = 0.0f;
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
                case 2: {
                    latLng = a.a(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    j = a.k(parcel, n);
                    continue;
                }
                case 4: {
                    i = a.k(parcel, n);
                    continue;
                }
                case 5: {
                    k = a.k(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new CameraPosition(g, latLng, j, i, k);
    }
    
    public CameraPosition[] newArray(final int n) {
        return new CameraPosition[n];
    }
}
