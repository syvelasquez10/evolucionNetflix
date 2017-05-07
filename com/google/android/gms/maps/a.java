// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.maps.model.CameraPosition;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<GoogleMapOptions>
{
    static void a(final GoogleMapOptions googleMapOptions, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, googleMapOptions.getVersionCode());
        b.a(parcel, 2, googleMapOptions.mp());
        b.a(parcel, 3, googleMapOptions.mq());
        b.c(parcel, 4, googleMapOptions.getMapType());
        b.a(parcel, 5, (Parcelable)googleMapOptions.getCamera(), n, false);
        b.a(parcel, 6, googleMapOptions.mr());
        b.a(parcel, 7, googleMapOptions.ms());
        b.a(parcel, 8, googleMapOptions.mt());
        b.a(parcel, 9, googleMapOptions.mu());
        b.a(parcel, 10, googleMapOptions.mv());
        b.a(parcel, 11, googleMapOptions.mw());
        b.H(parcel, d);
    }
    
    public GoogleMapOptions cG(final Parcel parcel) {
        byte e = 0;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
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
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    e8 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, b);
                    continue;
                }
                case 3: {
                    e7 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, b);
                    continue;
                }
                case 4: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 5: {
                    cameraPosition = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<CameraPosition>)CameraPosition.CREATOR);
                    continue;
                }
                case 6: {
                    e6 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, b);
                    continue;
                }
                case 7: {
                    e5 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, b);
                    continue;
                }
                case 8: {
                    e4 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, b);
                    continue;
                }
                case 9: {
                    e3 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, b);
                    continue;
                }
                case 10: {
                    e2 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, b);
                    continue;
                }
                case 11: {
                    e = com.google.android.gms.common.internal.safeparcel.a.e(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new GoogleMapOptions(g2, e8, e7, g, cameraPosition, e6, e5, e4, e3, e2, e);
    }
    
    public GoogleMapOptions[] ev(final int n) {
        return new GoogleMapOptions[n];
    }
}
