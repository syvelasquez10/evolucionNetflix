// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a$a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<CameraPosition>
{
    static void a(final CameraPosition cameraPosition, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, cameraPosition.getVersionCode());
        b.a(parcel, 2, (Parcelable)cameraPosition.target, n, false);
        b.a(parcel, 3, cameraPosition.zoom);
        b.a(parcel, 4, cameraPosition.tilt);
        b.a(parcel, 5, cameraPosition.bearing);
        b.H(parcel, d);
    }
    
    public CameraPosition cI(final Parcel parcel) {
        float l = 0.0f;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        LatLng latLng = null;
        float i = 0.0f;
        float j = 0.0f;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    latLng = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    j = com.google.android.gms.common.internal.safeparcel.a.l(parcel, b);
                    continue;
                }
                case 4: {
                    i = com.google.android.gms.common.internal.safeparcel.a.l(parcel, b);
                    continue;
                }
                case 5: {
                    l = com.google.android.gms.common.internal.safeparcel.a.l(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new CameraPosition(g, latLng, j, i, l);
    }
    
    public CameraPosition[] ex(final int n) {
        return new CameraPosition[n];
    }
}
