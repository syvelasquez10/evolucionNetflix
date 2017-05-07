// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class w implements Parcelable$Creator<v>
{
    static void a(final v v, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)v.getSession(), n, false);
        b.c(parcel, 1000, v.getVersionCode());
        b.H(parcel, d);
    }
    
    public v bN(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        Session session = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    session = a.a(parcel, b, Session.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new v(g, session);
    }
    
    public v[] df(final int n) {
        return new v[n];
    }
}
