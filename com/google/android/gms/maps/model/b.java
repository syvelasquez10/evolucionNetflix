// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcelable;
import android.os.Parcel;

public class b
{
    static void a(final CameraPosition cameraPosition, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, cameraPosition.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)cameraPosition.target, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, cameraPosition.zoom);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, cameraPosition.tilt);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, cameraPosition.bearing);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
}
