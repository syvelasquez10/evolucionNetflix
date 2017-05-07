// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class p implements Parcelable$Creator<Session>
{
    static void a(final Session session, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, session.getStartTimeMillis());
        b.c(parcel, 1000, session.getVersionCode());
        b.a(parcel, 2, session.getEndTimeMillis());
        b.a(parcel, 3, session.getName(), false);
        b.a(parcel, 4, session.getIdentifier(), false);
        b.a(parcel, 5, session.getDescription(), false);
        b.c(parcel, 7, session.getActivity());
        b.a(parcel, 8, (Parcelable)session.iH(), n, false);
        b.H(parcel, d);
    }
    
    public Session bu(final Parcel parcel) {
        long i = 0L;
        int g = 0;
        com.google.android.gms.fitness.data.a a = null;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        String o = null;
        String o2 = null;
        String o3 = null;
        long j = 0L;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    j = com.google.android.gms.common.internal.safeparcel.a.i(parcel, b);
                    continue;
                }
                case 1000: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    i = com.google.android.gms.common.internal.safeparcel.a.i(parcel, b);
                    continue;
                }
                case 3: {
                    o3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 5: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 7: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 8: {
                    a = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, com.google.android.gms.fitness.data.a.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new Session(g2, j, i, o3, o2, o, g, a);
    }
    
    public Session[] cL(final int n) {
        return new Session[n];
    }
}
