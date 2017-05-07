// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.os.Parcelable;
import android.os.Parcel;

public class b
{
    static void a(final GoogleMapOptions googleMapOptions, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, googleMapOptions.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, googleMapOptions.mp());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, googleMapOptions.mq());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, googleMapOptions.getMapType());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable)googleMapOptions.getCamera(), n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, googleMapOptions.mr());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, googleMapOptions.ms());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, googleMapOptions.mt());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, googleMapOptions.mu());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, googleMapOptions.mv());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, googleMapOptions.mw());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
}
