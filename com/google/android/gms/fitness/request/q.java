// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class q implements Parcelable$Creator<p>
{
    static void a(final p p3, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, p3.jq(), false);
        b.c(parcel, 1000, p3.getVersionCode());
        b.a(parcel, 2, (Parcelable)p3.jl(), n, false);
        b.H(parcel, d);
    }
    
    public p bJ(final Parcel parcel) {
        PendingIntent pendingIntent = null;
        final int c = a.C(parcel);
        int g = 0;
        IBinder p = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    p = a.p(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    pendingIntent = a.a(parcel, b, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new p(g, p, pendingIntent);
    }
    
    public p[] db(final int n) {
        return new p[n];
    }
}
