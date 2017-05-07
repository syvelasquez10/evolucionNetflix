// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<a>
{
    static void a(final a a, final Parcel parcel, final int n) {
        final int o = com.google.android.gms.common.internal.safeparcel.b.o(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable)a.dB(), n, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, a.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.D(parcel, o);
    }
    
    public a[] aN(final int n) {
        return new a[n];
    }
    
    public a ae(final Parcel parcel) {
        final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
        int g = 0;
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < n) {
            final int m = com.google.android.gms.common.internal.safeparcel.a.m(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.M(m)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, m);
                    continue;
                }
                case 1: {
                    pendingIntent = com.google.android.gms.common.internal.safeparcel.a.a(parcel, m, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + n, parcel);
        }
        return new a(g, pendingIntent);
    }
}
