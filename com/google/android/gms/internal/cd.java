// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class cd implements Parcelable$Creator<ce>
{
    static void a(final ce ce, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, ce.versionCode);
        b.a(parcel, 2, (Parcelable)ce.og, n, false);
        b.a(parcel, 3, ce.aO(), false);
        b.a(parcel, 4, ce.aP(), false);
        b.a(parcel, 5, ce.aQ(), false);
        b.a(parcel, 6, ce.aR(), false);
        b.a(parcel, 7, ce.ol, false);
        b.a(parcel, 8, ce.om);
        b.a(parcel, 9, ce.on, false);
        b.a(parcel, 10, ce.aT(), false);
        b.c(parcel, 11, ce.orientation);
        b.c(parcel, 12, ce.op);
        b.a(parcel, 13, ce.nO, false);
        b.a(parcel, 14, (Parcelable)ce.kK, n, false);
        b.a(parcel, 15, ce.aS(), false);
        b.a(parcel, 16, ce.or, false);
        b.F(parcel, p3);
    }
    
    public ce e(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        cb cb = null;
        IBinder o2 = null;
        IBinder o3 = null;
        IBinder o4 = null;
        IBinder o5 = null;
        String n = null;
        boolean c = false;
        String n2 = null;
        IBinder o6 = null;
        int g2 = 0;
        int g3 = 0;
        String n3 = null;
        dx dx = null;
        IBinder o7 = null;
        String n4 = null;
        while (parcel.dataPosition() < o) {
            final int n5 = a.n(parcel);
            switch (a.R(n5)) {
                default: {
                    a.b(parcel, n5);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n5);
                    continue;
                }
                case 2: {
                    cb = a.a(parcel, n5, (android.os.Parcelable$Creator<cb>)com.google.android.gms.internal.cb.CREATOR);
                    continue;
                }
                case 3: {
                    o2 = a.o(parcel, n5);
                    continue;
                }
                case 4: {
                    o3 = a.o(parcel, n5);
                    continue;
                }
                case 5: {
                    o4 = a.o(parcel, n5);
                    continue;
                }
                case 6: {
                    o5 = a.o(parcel, n5);
                    continue;
                }
                case 7: {
                    n = a.n(parcel, n5);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, n5);
                    continue;
                }
                case 9: {
                    n2 = a.n(parcel, n5);
                    continue;
                }
                case 10: {
                    o6 = a.o(parcel, n5);
                    continue;
                }
                case 11: {
                    g2 = a.g(parcel, n5);
                    continue;
                }
                case 12: {
                    g3 = a.g(parcel, n5);
                    continue;
                }
                case 13: {
                    n3 = a.n(parcel, n5);
                    continue;
                }
                case 14: {
                    dx = a.a(parcel, n5, (android.os.Parcelable$Creator<dx>)com.google.android.gms.internal.dx.CREATOR);
                    continue;
                }
                case 15: {
                    o7 = a.o(parcel, n5);
                    continue;
                }
                case 16: {
                    n4 = a.n(parcel, n5);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ce(g, cb, o2, o3, o4, o5, n, c, n2, o6, g2, g3, n3, dx, o7, n4);
    }
    
    public ce[] i(final int n) {
        return new ce[n];
    }
}
