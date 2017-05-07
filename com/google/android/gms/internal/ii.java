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

public class ii implements Parcelable$Creator<ih>
{
    static void a(final ih ih, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        final Set<Integer> ja = ih.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, ih.getVersionCode());
        }
        if (ja.contains(2)) {
            b.a(parcel, 2, ih.getAboutMe(), true);
        }
        if (ja.contains(3)) {
            b.a(parcel, 3, (Parcelable)ih.jv(), n, true);
        }
        if (ja.contains(4)) {
            b.a(parcel, 4, ih.getBirthday(), true);
        }
        if (ja.contains(5)) {
            b.a(parcel, 5, ih.getBraggingRights(), true);
        }
        if (ja.contains(6)) {
            b.c(parcel, 6, ih.getCircledByCount());
        }
        if (ja.contains(7)) {
            b.a(parcel, 7, (Parcelable)ih.jw(), n, true);
        }
        if (ja.contains(8)) {
            b.a(parcel, 8, ih.getCurrentLocation(), true);
        }
        if (ja.contains(9)) {
            b.a(parcel, 9, ih.getDisplayName(), true);
        }
        if (ja.contains(12)) {
            b.c(parcel, 12, ih.getGender());
        }
        if (ja.contains(14)) {
            b.a(parcel, 14, ih.getId(), true);
        }
        if (ja.contains(15)) {
            b.a(parcel, 15, (Parcelable)ih.jx(), n, true);
        }
        if (ja.contains(16)) {
            b.a(parcel, 16, ih.isPlusUser());
        }
        if (ja.contains(19)) {
            b.a(parcel, 19, (Parcelable)ih.jy(), n, true);
        }
        if (ja.contains(18)) {
            b.a(parcel, 18, ih.getLanguage(), true);
        }
        if (ja.contains(21)) {
            b.c(parcel, 21, ih.getObjectType());
        }
        if (ja.contains(20)) {
            b.a(parcel, 20, ih.getNickname(), true);
        }
        if (ja.contains(23)) {
            b.b(parcel, 23, ih.jA(), true);
        }
        if (ja.contains(22)) {
            b.b(parcel, 22, ih.jz(), true);
        }
        if (ja.contains(25)) {
            b.c(parcel, 25, ih.getRelationshipStatus());
        }
        if (ja.contains(24)) {
            b.c(parcel, 24, ih.getPlusOneCount());
        }
        if (ja.contains(27)) {
            b.a(parcel, 27, ih.getUrl(), true);
        }
        if (ja.contains(26)) {
            b.a(parcel, 26, ih.getTagline(), true);
        }
        if (ja.contains(29)) {
            b.a(parcel, 29, ih.isVerified());
        }
        if (ja.contains(28)) {
            b.b(parcel, 28, ih.jB(), true);
        }
        b.F(parcel, p3);
    }
    
    public ih aN(final Parcel parcel) {
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        String n = null;
        SafeParcelable safeParcelable = null;
        String n2 = null;
        String n3 = null;
        int g2 = 0;
        SafeParcelable safeParcelable2 = null;
        String n4 = null;
        String n5 = null;
        int g3 = 0;
        String n6 = null;
        SafeParcelable safeParcelable3 = null;
        boolean c = false;
        String n7 = null;
        SafeParcelable safeParcelable4 = null;
        String n8 = null;
        int g4 = 0;
        List<ih.f> c2 = null;
        List<ih.g> c3 = null;
        int g5 = 0;
        int g6 = 0;
        String n9 = null;
        String n10 = null;
        List<ih.h> c4 = null;
        boolean c5 = false;
        while (parcel.dataPosition() < o) {
            final int n11 = a.n(parcel);
            switch (a.R(n11)) {
                default: {
                    a.b(parcel, n11);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n11);
                    set.add(1);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n11);
                    set.add(2);
                    continue;
                }
                case 3: {
                    safeParcelable = a.a(parcel, n11, (android.os.Parcelable$Creator<ih.a>)ih.a.CREATOR);
                    set.add(3);
                    continue;
                }
                case 4: {
                    n2 = a.n(parcel, n11);
                    set.add(4);
                    continue;
                }
                case 5: {
                    n3 = a.n(parcel, n11);
                    set.add(5);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, n11);
                    set.add(6);
                    continue;
                }
                case 7: {
                    safeParcelable2 = a.a(parcel, n11, (android.os.Parcelable$Creator<ih.b>)ih.b.CREATOR);
                    set.add(7);
                    continue;
                }
                case 8: {
                    n4 = a.n(parcel, n11);
                    set.add(8);
                    continue;
                }
                case 9: {
                    n5 = a.n(parcel, n11);
                    set.add(9);
                    continue;
                }
                case 12: {
                    g3 = a.g(parcel, n11);
                    set.add(12);
                    continue;
                }
                case 14: {
                    n6 = a.n(parcel, n11);
                    set.add(14);
                    continue;
                }
                case 15: {
                    safeParcelable3 = a.a(parcel, n11, (android.os.Parcelable$Creator<ih.c>)ih.c.CREATOR);
                    set.add(15);
                    continue;
                }
                case 16: {
                    c = a.c(parcel, n11);
                    set.add(16);
                    continue;
                }
                case 19: {
                    safeParcelable4 = a.a(parcel, n11, (android.os.Parcelable$Creator<ih.d>)ih.d.CREATOR);
                    set.add(19);
                    continue;
                }
                case 18: {
                    n7 = a.n(parcel, n11);
                    set.add(18);
                    continue;
                }
                case 21: {
                    g4 = a.g(parcel, n11);
                    set.add(21);
                    continue;
                }
                case 20: {
                    n8 = a.n(parcel, n11);
                    set.add(20);
                    continue;
                }
                case 23: {
                    c3 = a.c(parcel, n11, (android.os.Parcelable$Creator<ih.g>)ih.g.CREATOR);
                    set.add(23);
                    continue;
                }
                case 22: {
                    c2 = a.c(parcel, n11, (android.os.Parcelable$Creator<ih.f>)ih.f.CREATOR);
                    set.add(22);
                    continue;
                }
                case 25: {
                    g6 = a.g(parcel, n11);
                    set.add(25);
                    continue;
                }
                case 24: {
                    g5 = a.g(parcel, n11);
                    set.add(24);
                    continue;
                }
                case 27: {
                    n10 = a.n(parcel, n11);
                    set.add(27);
                    continue;
                }
                case 26: {
                    n9 = a.n(parcel, n11);
                    set.add(26);
                    continue;
                }
                case 29: {
                    c5 = a.c(parcel, n11);
                    set.add(29);
                    continue;
                }
                case 28: {
                    c4 = a.c(parcel, n11, (android.os.Parcelable$Creator<ih.h>)ih.h.CREATOR);
                    set.add(28);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ih(set, g, n, (ih.a)safeParcelable, n2, n3, g2, (ih.b)safeParcelable2, n4, n5, g3, n6, (ih.c)safeParcelable3, c, n7, (ih.d)safeParcelable4, n8, g4, c2, c3, g5, g6, n9, n10, c4, c5);
    }
    
    public ih[] bQ(final int n) {
        return new ih[n];
    }
}
