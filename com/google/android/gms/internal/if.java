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

public class if implements Parcelable$Creator<ie>
{
    static void a(final ie ie, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        final Set<Integer> ja = ie.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, ie.getVersionCode());
        }
        if (ja.contains(2)) {
            b.a(parcel, 2, ie.getId(), true);
        }
        if (ja.contains(4)) {
            b.a(parcel, 4, (Parcelable)ie.jr(), n, true);
        }
        if (ja.contains(5)) {
            b.a(parcel, 5, ie.getStartDate(), true);
        }
        if (ja.contains(6)) {
            b.a(parcel, 6, (Parcelable)ie.js(), n, true);
        }
        if (ja.contains(7)) {
            b.a(parcel, 7, ie.getType(), true);
        }
        b.F(parcel, p3);
    }
    
    public ie aM(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        ic ic = null;
        String n2 = null;
        ic ic2 = null;
        String n3 = null;
        while (parcel.dataPosition() < o) {
            final int n4 = a.n(parcel);
            switch (a.R(n4)) {
                default: {
                    a.b(parcel, n4);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n4);
                    set.add(1);
                    continue;
                }
                case 2: {
                    n3 = a.n(parcel, n4);
                    set.add(2);
                    continue;
                }
                case 4: {
                    ic2 = a.a(parcel, n4, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(4);
                    continue;
                }
                case 5: {
                    n2 = a.n(parcel, n4);
                    set.add(5);
                    continue;
                }
                case 6: {
                    ic = a.a(parcel, n4, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(6);
                    continue;
                }
                case 7: {
                    n = a.n(parcel, n4);
                    set.add(7);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ie(set, g, n3, ic2, n2, ic, n);
    }
    
    public ie[] bP(final int n) {
        return new ie[n];
    }
}
