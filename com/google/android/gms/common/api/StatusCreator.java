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
        final int p3 = b.p(parcel);
        b.c(parcel, 1, status.getStatusCode());
        b.c(parcel, 1000, status.getVersionCode());
        b.a(parcel, 2, status.ep(), false);
        b.a(parcel, 3, (Parcelable)status.eo(), n, false);
        b.F(parcel, p3);
    }
    
    public Status createFromParcel(final Parcel parcel) {
        PendingIntent pendingIntent = null;
        int g = 0;
        final int o = a.o(parcel);
        String n = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    pendingIntent = a.a(parcel, n2, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new Status(g2, g, n, pendingIntent);
    }
    
    public Status[] newArray(final int n) {
        return new Status[n];
    }
}
