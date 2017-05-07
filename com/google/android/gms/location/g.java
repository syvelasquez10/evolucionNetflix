// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<f>
{
    static void a(final f f, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, (Parcelable)f.dB(), n, false);
        b.c(parcel, 1000, f.getVersionCode());
        b.a(parcel, 2, f.dC(), false);
        b.D(parcel, o);
    }
    
    public f[] aT(final int n) {
        return new f[n];
    }
    
    public f ag(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        int g = 0;
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    pendingIntent = a.a(parcel, i, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new f(g, pendingIntent, m);
    }
}
