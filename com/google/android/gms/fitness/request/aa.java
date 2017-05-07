// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.safeparcel.a$a;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class aa implements Parcelable$Creator<z>
{
    static void a(final z z, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)z.jl(), n, false);
        b.c(parcel, 1000, z.getVersionCode());
        b.H(parcel, d);
    }
    
    public z bP(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    pendingIntent = a.a(parcel, b, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new z(g, pendingIntent);
    }
    
    public z[] dh(final int n) {
        return new z[n];
    }
}
