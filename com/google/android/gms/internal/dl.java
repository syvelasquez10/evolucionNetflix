// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class dl implements Parcelable$Creator<dm>
{
    static void a(final dm dm, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, dm.versionCode);
        b.a(parcel, 2, (Parcelable)dm.rK, n, false);
        b.a(parcel, 3, dm.cc(), false);
        b.a(parcel, 4, dm.cd(), false);
        b.a(parcel, 5, dm.ce(), false);
        b.a(parcel, 6, dm.cf(), false);
        b.a(parcel, 7, dm.rP, false);
        b.a(parcel, 8, dm.rQ);
        b.a(parcel, 9, dm.rR, false);
        b.a(parcel, 10, dm.ch(), false);
        b.c(parcel, 11, dm.orientation);
        b.c(parcel, 12, dm.rT);
        b.a(parcel, 13, dm.rq, false);
        b.a(parcel, 14, (Parcelable)dm.lD, n, false);
        b.a(parcel, 15, dm.cg(), false);
        b.a(parcel, 17, (Parcelable)dm.rW, n, false);
        b.a(parcel, 16, dm.rV, false);
        b.H(parcel, d);
    }
    
    public dm f(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        dj dj = null;
        IBinder p = null;
        IBinder p2 = null;
        IBinder p3 = null;
        IBinder p4 = null;
        String o = null;
        boolean c2 = false;
        String o2 = null;
        IBinder p5 = null;
        int g2 = 0;
        int g3 = 0;
        String o3 = null;
        gt gt = null;
        IBinder p6 = null;
        String o4 = null;
        x x = null;
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
                case 2: {
                    dj = a.a(parcel, b, (android.os.Parcelable$Creator<dj>)com.google.android.gms.internal.dj.CREATOR);
                    continue;
                }
                case 3: {
                    p = a.p(parcel, b);
                    continue;
                }
                case 4: {
                    p2 = a.p(parcel, b);
                    continue;
                }
                case 5: {
                    p3 = a.p(parcel, b);
                    continue;
                }
                case 6: {
                    p4 = a.p(parcel, b);
                    continue;
                }
                case 7: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 9: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 10: {
                    p5 = a.p(parcel, b);
                    continue;
                }
                case 11: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 12: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 13: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 14: {
                    gt = a.a(parcel, b, (android.os.Parcelable$Creator<gt>)com.google.android.gms.internal.gt.CREATOR);
                    continue;
                }
                case 15: {
                    p6 = a.p(parcel, b);
                    continue;
                }
                case 17: {
                    x = a.a(parcel, b, (android.os.Parcelable$Creator<x>)com.google.android.gms.internal.x.CREATOR);
                    continue;
                }
                case 16: {
                    o4 = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new dm(g, dj, p, p2, p3, p4, o, c2, o2, p5, g2, g3, o3, gt, p6, o4, x);
    }
    
    public dm[] m(final int n) {
        return new dm[n];
    }
}
