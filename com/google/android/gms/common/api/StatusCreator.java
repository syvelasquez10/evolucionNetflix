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
        final int d = b.D(parcel);
        b.c(parcel, 1, status.getStatusCode());
        b.c(parcel, 1000, status.getVersionCode());
        b.a(parcel, 2, status.getStatusMessage(), false);
        b.a(parcel, 3, (Parcelable)status.getPendingIntent(), n, false);
        b.H(parcel, d);
    }
    
    public Status createFromParcel(final Parcel parcel) {
        PendingIntent pendingIntent = null;
        int g = 0;
        final int c = a.C(parcel);
        String o = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    pendingIntent = a.a(parcel, b, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new Status(g2, g, o, pendingIntent);
    }
    
    public Status[] newArray(final int n) {
        return new Status[n];
    }
}
