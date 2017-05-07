// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class StatusCreator implements Parcelable$Creator<Status>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final Status status, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, status.getStatusCode());
        b.c(parcel, 1000, status.getVersionCode());
        b.a(parcel, 2, status.bt(), false);
        b.a(parcel, 3, (Parcelable)status.bs(), n, false);
        b.D(parcel, o);
    }
    
    public Status createFromParcel(final Parcel parcel) {
        PendingIntent pendingIntent = null;
        int g = 0;
        final int n = a.n(parcel);
        String m = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 3: {
                    pendingIntent = a.a(parcel, i, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new Status(g2, g, m, pendingIntent);
    }
    
    public Status[] newArray(final int n) {
        return new Status[n];
    }
}
