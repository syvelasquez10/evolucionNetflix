// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ih implements Parcelable$Creator<ig>
{
    static void a(final ig ig, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        final Set<Integer> fa = ig.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, ig.getVersionCode());
        }
        if (fa.contains(2)) {
            b.a(parcel, 2, ig.getAboutMe(), true);
        }
        if (fa.contains(3)) {
            b.a(parcel, 3, (Parcelable)ig.fv(), n, true);
        }
        if (fa.contains(4)) {
            b.a(parcel, 4, ig.getBirthday(), true);
        }
        if (fa.contains(5)) {
            b.a(parcel, 5, ig.getBraggingRights(), true);
        }
        if (fa.contains(6)) {
            b.c(parcel, 6, ig.getCircledByCount());
        }
        if (fa.contains(7)) {
            b.a(parcel, 7, (Parcelable)ig.fw(), n, true);
        }
        if (fa.contains(8)) {
            b.a(parcel, 8, ig.getCurrentLocation(), true);
        }
        if (fa.contains(9)) {
            b.a(parcel, 9, ig.getDisplayName(), true);
        }
        if (fa.contains(12)) {
            b.c(parcel, 12, ig.getGender());
        }
        if (fa.contains(14)) {
            b.a(parcel, 14, ig.getId(), true);
        }
        if (fa.contains(15)) {
            b.a(parcel, 15, (Parcelable)ig.fx(), n, true);
        }
        if (fa.contains(16)) {
            b.a(parcel, 16, ig.isPlusUser());
        }
        if (fa.contains(19)) {
            b.a(parcel, 19, (Parcelable)ig.fy(), n, true);
        }
        if (fa.contains(18)) {
            b.a(parcel, 18, ig.getLanguage(), true);
        }
        if (fa.contains(21)) {
            b.c(parcel, 21, ig.getObjectType());
        }
        if (fa.contains(20)) {
            b.a(parcel, 20, ig.getNickname(), true);
        }
        if (fa.contains(23)) {
            b.b(parcel, 23, ig.fA(), true);
        }
        if (fa.contains(22)) {
            b.b(parcel, 22, ig.fz(), true);
        }
        if (fa.contains(25)) {
            b.c(parcel, 25, ig.getRelationshipStatus());
        }
        if (fa.contains(24)) {
            b.c(parcel, 24, ig.getPlusOneCount());
        }
        if (fa.contains(27)) {
            b.a(parcel, 27, ig.getUrl(), true);
        }
        if (fa.contains(26)) {
            b.a(parcel, 26, ig.getTagline(), true);
        }
        if (fa.contains(29)) {
            b.a(parcel, 29, ig.isVerified());
        }
        if (fa.contains(28)) {
            b.b(parcel, 28, ig.fB(), true);
        }
        b.D(parcel, o);
    }
    
    public ig au(final Parcel parcel) {
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        String m = null;
        SafeParcelable safeParcelable = null;
        String i = null;
        String j = null;
        int g2 = 0;
        SafeParcelable safeParcelable2 = null;
        String k = null;
        String l = null;
        int g3 = 0;
        String m2 = null;
        SafeParcelable safeParcelable3 = null;
        boolean c = false;
        String m3 = null;
        SafeParcelable safeParcelable4 = null;
        String m4 = null;
        int g4 = 0;
        List<ig.f> c2 = null;
        List<ig.g> c3 = null;
        int g5 = 0;
        int g6 = 0;
        String m5 = null;
        String m6 = null;
        List<ig.h> c4 = null;
        boolean c5 = false;
        while (parcel.dataPosition() < n) {
            final int m7 = a.m(parcel);
            switch (a.M(m7)) {
                default: {
                    a.b(parcel, m7);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m7);
                    set.add(1);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, m7);
                    set.add(2);
                    continue;
                }
                case 3: {
                    safeParcelable = a.a(parcel, m7, (android.os.Parcelable$Creator<ig.a>)ig.a.CREATOR);
                    set.add(3);
                    continue;
                }
                case 4: {
                    i = a.m(parcel, m7);
                    set.add(4);
                    continue;
                }
                case 5: {
                    j = a.m(parcel, m7);
                    set.add(5);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, m7);
                    set.add(6);
                    continue;
                }
                case 7: {
                    safeParcelable2 = a.a(parcel, m7, (android.os.Parcelable$Creator<ig.b>)ig.b.CREATOR);
                    set.add(7);
                    continue;
                }
                case 8: {
                    k = a.m(parcel, m7);
                    set.add(8);
                    continue;
                }
                case 9: {
                    l = a.m(parcel, m7);
                    set.add(9);
                    continue;
                }
                case 12: {
                    g3 = a.g(parcel, m7);
                    set.add(12);
                    continue;
                }
                case 14: {
                    m2 = a.m(parcel, m7);
                    set.add(14);
                    continue;
                }
                case 15: {
                    safeParcelable3 = a.a(parcel, m7, (android.os.Parcelable$Creator<ig.c>)ig.c.CREATOR);
                    set.add(15);
                    continue;
                }
                case 16: {
                    c = a.c(parcel, m7);
                    set.add(16);
                    continue;
                }
                case 19: {
                    safeParcelable4 = a.a(parcel, m7, (android.os.Parcelable$Creator<ig.d>)ig.d.CREATOR);
                    set.add(19);
                    continue;
                }
                case 18: {
                    m3 = a.m(parcel, m7);
                    set.add(18);
                    continue;
                }
                case 21: {
                    g4 = a.g(parcel, m7);
                    set.add(21);
                    continue;
                }
                case 20: {
                    m4 = a.m(parcel, m7);
                    set.add(20);
                    continue;
                }
                case 23: {
                    c3 = a.c(parcel, m7, (android.os.Parcelable$Creator<ig.g>)ig.g.CREATOR);
                    set.add(23);
                    continue;
                }
                case 22: {
                    c2 = a.c(parcel, m7, (android.os.Parcelable$Creator<ig.f>)ig.f.CREATOR);
                    set.add(22);
                    continue;
                }
                case 25: {
                    g6 = a.g(parcel, m7);
                    set.add(25);
                    continue;
                }
                case 24: {
                    g5 = a.g(parcel, m7);
                    set.add(24);
                    continue;
                }
                case 27: {
                    m6 = a.m(parcel, m7);
                    set.add(27);
                    continue;
                }
                case 26: {
                    m5 = a.m(parcel, m7);
                    set.add(26);
                    continue;
                }
                case 29: {
                    c5 = a.c(parcel, m7);
                    set.add(29);
                    continue;
                }
                case 28: {
                    c4 = a.c(parcel, m7, (android.os.Parcelable$Creator<ig.h>)ig.h.CREATOR);
                    set.add(28);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ig(set, g, m, (ig.a)safeParcelable, i, j, g2, (ig.b)safeParcelable2, k, l, g3, m2, (ig.c)safeParcelable3, c, m3, (ig.d)safeParcelable4, m4, g4, c2, c3, g5, g6, m5, m6, c4, c5);
    }
    
    public ig[] bm(final int n) {
        return new ig[n];
    }
}
