// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;

public class d
{
    static void a(final LatLngBounds latLngBounds, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, latLngBounds.getVersionCode());
        b.a(parcel, 2, (Parcelable)latLngBounds.southwest, n, false);
        b.a(parcel, 3, (Parcelable)latLngBounds.northeast, n, false);
        b.F(parcel, p3);
    }
}
