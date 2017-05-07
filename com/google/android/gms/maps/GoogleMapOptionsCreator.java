// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class GoogleMapOptionsCreator implements Parcelable$Creator<GoogleMapOptions>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final GoogleMapOptions googleMapOptions, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, googleMapOptions.getVersionCode());
        b.a(parcel, 2, googleMapOptions.ig());
        b.a(parcel, 3, googleMapOptions.ih());
        b.c(parcel, 4, googleMapOptions.getMapType());
        b.a(parcel, 5, (Parcelable)googleMapOptions.getCamera(), n, false);
        b.a(parcel, 6, googleMapOptions.ii());
        b.a(parcel, 7, googleMapOptions.ij());
        b.a(parcel, 8, googleMapOptions.ik());
        b.a(parcel, 9, googleMapOptions.il());
        b.a(parcel, 10, googleMapOptions.im());
        b.a(parcel, 11, googleMapOptions.in());
        b.F(parcel, p3);
    }
    
    public GoogleMapOptions createFromParcel(final Parcel parcel) {
        byte e = 0;
        final int o = a.o(parcel);
        CameraPosition cameraPosition = null;
        byte e2 = 0;
        byte e3 = 0;
        byte e4 = 0;
        byte e5 = 0;
        byte e6 = 0;
        int g = 0;
        byte e7 = 0;
        byte e8 = 0;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    e8 = a.e(parcel, n);
                    continue;
                }
                case 3: {
                    e7 = a.e(parcel, n);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 5: {
                    cameraPosition = a.a(parcel, n, (android.os.Parcelable$Creator<CameraPosition>)CameraPosition.CREATOR);
                    continue;
                }
                case 6: {
                    e6 = a.e(parcel, n);
                    continue;
                }
                case 7: {
                    e5 = a.e(parcel, n);
                    continue;
                }
                case 8: {
                    e4 = a.e(parcel, n);
                    continue;
                }
                case 9: {
                    e3 = a.e(parcel, n);
                    continue;
                }
                case 10: {
                    e2 = a.e(parcel, n);
                    continue;
                }
                case 11: {
                    e = a.e(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new GoogleMapOptions(g2, e8, e7, g, cameraPosition, e6, e5, e4, e3, e2, e);
    }
    
    public GoogleMapOptions[] newArray(final int n) {
        return new GoogleMapOptions[n];
    }
}
