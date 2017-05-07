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

public class ie implements Parcelable$Creator<id>
{
    static void a(final id id, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        final Set<Integer> fa = id.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, id.getVersionCode());
        }
        if (fa.contains(2)) {
            b.a(parcel, 2, id.getId(), true);
        }
        if (fa.contains(4)) {
            b.a(parcel, 4, (Parcelable)id.fr(), n, true);
        }
        if (fa.contains(5)) {
            b.a(parcel, 5, id.getStartDate(), true);
        }
        if (fa.contains(6)) {
            b.a(parcel, 6, (Parcelable)id.fs(), n, true);
        }
        if (fa.contains(7)) {
            b.a(parcel, 7, id.getType(), true);
        }
        b.D(parcel, o);
    }
    
    public id at(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        ib ib = null;
        String i = null;
        ib ib2 = null;
        String j = null;
        while (parcel.dataPosition() < n) {
            final int k = a.m(parcel);
            switch (a.M(k)) {
                default: {
                    a.b(parcel, k);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, k);
                    set.add(1);
                    continue;
                }
                case 2: {
                    j = a.m(parcel, k);
                    set.add(2);
                    continue;
                }
                case 4: {
                    ib2 = a.a(parcel, k, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(4);
                    continue;
                }
                case 5: {
                    i = a.m(parcel, k);
                    set.add(5);
                    continue;
                }
                case 6: {
                    ib = a.a(parcel, k, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(6);
                    continue;
                }
                case 7: {
                    m = a.m(parcel, k);
                    set.add(7);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new id(set, g, j, ib2, i, ib, m);
    }
    
    public id[] bl(final int n) {
        return new id[n];
    }
}
