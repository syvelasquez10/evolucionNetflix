// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a$a;
import android.location.Location;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class aw implements Parcelable$Creator<av>
{
    static void a(final av av, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, av.versionCode);
        b.a(parcel, 2, av.nT);
        b.a(parcel, 3, av.extras, false);
        b.c(parcel, 4, av.nU);
        b.b(parcel, 5, av.nV, false);
        b.a(parcel, 6, av.nW);
        b.c(parcel, 7, av.nX);
        b.a(parcel, 8, av.nY);
        b.a(parcel, 9, av.nZ, false);
        b.a(parcel, 10, (Parcelable)av.oa, n, false);
        b.a(parcel, 11, (Parcelable)av.ob, n, false);
        b.a(parcel, 12, av.oc, false);
        b.a(parcel, 13, av.od, false);
        b.H(parcel, d);
    }
    
    public av b(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        long i = 0L;
        Bundle q = null;
        int g2 = 0;
        List<String> c2 = null;
        boolean c3 = false;
        int g3 = 0;
        boolean c4 = false;
        String o = null;
        bj bj = null;
        Location location = null;
        String o2 = null;
        Bundle q2 = null;
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
                    i = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    q = a.q(parcel, b);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    c2 = a.C(parcel, b);
                    continue;
                }
                case 6: {
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 7: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 8: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 9: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 10: {
                    bj = a.a(parcel, b, (android.os.Parcelable$Creator<bj>)com.google.android.gms.internal.bj.CREATOR);
                    continue;
                }
                case 11: {
                    location = a.a(parcel, b, (android.os.Parcelable$Creator<Location>)Location.CREATOR);
                    continue;
                }
                case 12: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 13: {
                    q2 = a.q(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new av(g, i, q, g2, c2, c3, g3, c4, o, bj, location, o2, q2);
    }
    
    public av[] e(final int n) {
        return new av[n];
    }
}
