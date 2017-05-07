// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class nw implements Parcelable$Creator<nv>
{
    static void a(final nv nv, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        final Set<Integer> alR = nv.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, nv.BR);
        }
        if (alR.contains(2)) {
            b.a(parcel, 2, nv.BL, true);
        }
        if (alR.contains(4)) {
            b.a(parcel, 4, (Parcelable)nv.amM, n, true);
        }
        if (alR.contains(5)) {
            b.a(parcel, 5, nv.amE, true);
        }
        if (alR.contains(6)) {
            b.a(parcel, 6, (Parcelable)nv.amN, n, true);
        }
        if (alR.contains(7)) {
            b.a(parcel, 7, nv.uO, true);
        }
        b.H(parcel, d);
    }
    
    public nv dc(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        nt nt = null;
        String o2 = null;
        nt nt2 = null;
        String o3 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    set.add(1);
                    continue;
                }
                case 2: {
                    o3 = a.o(parcel, b);
                    set.add(2);
                    continue;
                }
                case 4: {
                    nt2 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(4);
                    continue;
                }
                case 5: {
                    o2 = a.o(parcel, b);
                    set.add(5);
                    continue;
                }
                case 6: {
                    nt = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(6);
                    continue;
                }
                case 7: {
                    o = a.o(parcel, b);
                    set.add(7);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new nv(set, g, o3, nt2, o2, nt, o);
    }
    
    public nv[] eT(final int n) {
        return new nv[n];
    }
}
