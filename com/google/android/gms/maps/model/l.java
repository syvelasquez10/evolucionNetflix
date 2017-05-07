// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;

public class l
{
    static void a(final MarkerOptions markerOptions, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, markerOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)markerOptions.getPosition(), n, false);
        b.a(parcel, 3, markerOptions.getTitle(), false);
        b.a(parcel, 4, markerOptions.getSnippet(), false);
        b.a(parcel, 5, markerOptions.mN(), false);
        b.a(parcel, 6, markerOptions.getAnchorU());
        b.a(parcel, 7, markerOptions.getAnchorV());
        b.a(parcel, 8, markerOptions.isDraggable());
        b.a(parcel, 9, markerOptions.isVisible());
        b.H(parcel, d);
    }
}
