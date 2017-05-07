// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;

public class k
{
    static void a(final VisibleRegion visibleRegion, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, visibleRegion.getVersionCode());
        b.a(parcel, 2, (Parcelable)visibleRegion.nearLeft, n, false);
        b.a(parcel, 3, (Parcelable)visibleRegion.nearRight, n, false);
        b.a(parcel, 4, (Parcelable)visibleRegion.farLeft, n, false);
        b.a(parcel, 5, (Parcelable)visibleRegion.farRight, n, false);
        b.a(parcel, 6, (Parcelable)visibleRegion.latLngBounds, n, false);
        b.D(parcel, o);
    }
}
