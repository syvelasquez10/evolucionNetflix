// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;

public class a
{
    static void a(final GoogleMapOptions googleMapOptions, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, googleMapOptions.getVersionCode());
        b.a(parcel, 2, googleMapOptions.eo());
        b.a(parcel, 3, googleMapOptions.ep());
        b.c(parcel, 4, googleMapOptions.getMapType());
        b.a(parcel, 5, (Parcelable)googleMapOptions.getCamera(), n, false);
        b.a(parcel, 6, googleMapOptions.eq());
        b.a(parcel, 7, googleMapOptions.er());
        b.a(parcel, 8, googleMapOptions.es());
        b.a(parcel, 9, googleMapOptions.et());
        b.a(parcel, 10, googleMapOptions.eu());
        b.a(parcel, 11, googleMapOptions.ev());
        b.D(parcel, o);
    }
}
