// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;

public class d
{
    static void a(final CircleOptions circleOptions, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, circleOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)circleOptions.getCenter(), n, false);
        b.a(parcel, 3, circleOptions.getRadius());
        b.a(parcel, 4, circleOptions.getStrokeWidth());
        b.c(parcel, 5, circleOptions.getStrokeColor());
        b.c(parcel, 6, circleOptions.getFillColor());
        b.a(parcel, 7, circleOptions.getZIndex());
        b.a(parcel, 8, circleOptions.isVisible());
        b.H(parcel, d);
    }
}
