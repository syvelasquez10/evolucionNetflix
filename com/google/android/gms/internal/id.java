// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class id implements Parcelable$Creator<ic>
{
    static void a(final ic ic, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        final Set<Integer> ja = ic.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, ic.getVersionCode());
        }
        if (ja.contains(2)) {
            b.a(parcel, 2, (Parcelable)ic.jb(), n, true);
        }
        if (ja.contains(3)) {
            b.a(parcel, 3, ic.getAdditionalName(), true);
        }
        if (ja.contains(4)) {
            b.a(parcel, 4, (Parcelable)ic.jc(), n, true);
        }
        if (ja.contains(5)) {
            b.a(parcel, 5, ic.getAddressCountry(), true);
        }
        if (ja.contains(6)) {
            b.a(parcel, 6, ic.getAddressLocality(), true);
        }
        if (ja.contains(7)) {
            b.a(parcel, 7, ic.getAddressRegion(), true);
        }
        if (ja.contains(8)) {
            b.b(parcel, 8, ic.jd(), true);
        }
        if (ja.contains(9)) {
            b.c(parcel, 9, ic.getAttendeeCount());
        }
        if (ja.contains(10)) {
            b.b(parcel, 10, ic.je(), true);
        }
        if (ja.contains(11)) {
            b.a(parcel, 11, (Parcelable)ic.jf(), n, true);
        }
        if (ja.contains(12)) {
            b.b(parcel, 12, ic.jg(), true);
        }
        if (ja.contains(13)) {
            b.a(parcel, 13, ic.getBestRating(), true);
        }
        if (ja.contains(14)) {
            b.a(parcel, 14, ic.getBirthDate(), true);
        }
        if (ja.contains(15)) {
            b.a(parcel, 15, (Parcelable)ic.jh(), n, true);
        }
        if (ja.contains(17)) {
            b.a(parcel, 17, ic.getContentSize(), true);
        }
        if (ja.contains(16)) {
            b.a(parcel, 16, ic.getCaption(), true);
        }
        if (ja.contains(19)) {
            b.b(parcel, 19, ic.ji(), true);
        }
        if (ja.contains(18)) {
            b.a(parcel, 18, ic.getContentUrl(), true);
        }
        if (ja.contains(21)) {
            b.a(parcel, 21, ic.getDateModified(), true);
        }
        if (ja.contains(20)) {
            b.a(parcel, 20, ic.getDateCreated(), true);
        }
        if (ja.contains(23)) {
            b.a(parcel, 23, ic.getDescription(), true);
        }
        if (ja.contains(22)) {
            b.a(parcel, 22, ic.getDatePublished(), true);
        }
        if (ja.contains(25)) {
            b.a(parcel, 25, ic.getEmbedUrl(), true);
        }
        if (ja.contains(24)) {
            b.a(parcel, 24, ic.getDuration(), true);
        }
        if (ja.contains(27)) {
            b.a(parcel, 27, ic.getFamilyName(), true);
        }
        if (ja.contains(26)) {
            b.a(parcel, 26, ic.getEndDate(), true);
        }
        if (ja.contains(29)) {
            b.a(parcel, 29, (Parcelable)ic.jj(), n, true);
        }
        if (ja.contains(28)) {
            b.a(parcel, 28, ic.getGender(), true);
        }
        if (ja.contains(31)) {
            b.a(parcel, 31, ic.getHeight(), true);
        }
        if (ja.contains(30)) {
            b.a(parcel, 30, ic.getGivenName(), true);
        }
        if (ja.contains(34)) {
            b.a(parcel, 34, (Parcelable)ic.jk(), n, true);
        }
        if (ja.contains(32)) {
            b.a(parcel, 32, ic.getId(), true);
        }
        if (ja.contains(33)) {
            b.a(parcel, 33, ic.getImage(), true);
        }
        if (ja.contains(38)) {
            b.a(parcel, 38, ic.getLongitude());
        }
        if (ja.contains(39)) {
            b.a(parcel, 39, ic.getName(), true);
        }
        if (ja.contains(36)) {
            b.a(parcel, 36, ic.getLatitude());
        }
        if (ja.contains(37)) {
            b.a(parcel, 37, (Parcelable)ic.jl(), n, true);
        }
        if (ja.contains(42)) {
            b.a(parcel, 42, ic.getPlayerType(), true);
        }
        if (ja.contains(43)) {
            b.a(parcel, 43, ic.getPostOfficeBoxNumber(), true);
        }
        if (ja.contains(40)) {
            b.a(parcel, 40, (Parcelable)ic.jm(), n, true);
        }
        if (ja.contains(41)) {
            b.b(parcel, 41, ic.jn(), true);
        }
        if (ja.contains(46)) {
            b.a(parcel, 46, (Parcelable)ic.jo(), n, true);
        }
        if (ja.contains(47)) {
            b.a(parcel, 47, ic.getStartDate(), true);
        }
        if (ja.contains(44)) {
            b.a(parcel, 44, ic.getPostalCode(), true);
        }
        if (ja.contains(45)) {
            b.a(parcel, 45, ic.getRatingValue(), true);
        }
        if (ja.contains(51)) {
            b.a(parcel, 51, ic.getThumbnailUrl(), true);
        }
        if (ja.contains(50)) {
            b.a(parcel, 50, (Parcelable)ic.jp(), n, true);
        }
        if (ja.contains(49)) {
            b.a(parcel, 49, ic.getText(), true);
        }
        if (ja.contains(48)) {
            b.a(parcel, 48, ic.getStreetAddress(), true);
        }
        if (ja.contains(55)) {
            b.a(parcel, 55, ic.getWidth(), true);
        }
        if (ja.contains(54)) {
            b.a(parcel, 54, ic.getUrl(), true);
        }
        if (ja.contains(53)) {
            b.a(parcel, 53, ic.getType(), true);
        }
        if (ja.contains(52)) {
            b.a(parcel, 52, ic.getTickerSymbol(), true);
        }
        if (ja.contains(56)) {
            b.a(parcel, 56, ic.getWorstRating(), true);
        }
        b.F(parcel, p3);
    }
    
    public ic aL(final Parcel parcel) {
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        ic ic = null;
        List<String> a = null;
        ic ic2 = null;
        String n = null;
        String n2 = null;
        String n3 = null;
        List<ic> c = null;
        int g2 = 0;
        List<ic> c2 = null;
        ic ic3 = null;
        List<ic> c3 = null;
        String n4 = null;
        String n5 = null;
        ic ic4 = null;
        String n6 = null;
        String n7 = null;
        String n8 = null;
        List<ic> c4 = null;
        String n9 = null;
        String n10 = null;
        String n11 = null;
        String n12 = null;
        String n13 = null;
        String n14 = null;
        String n15 = null;
        String n16 = null;
        String n17 = null;
        ic ic5 = null;
        String n18 = null;
        String n19 = null;
        String n20 = null;
        String n21 = null;
        ic ic6 = null;
        double l = 0.0;
        ic ic7 = null;
        double i = 0.0;
        String n22 = null;
        ic ic8 = null;
        List<ic> c5 = null;
        String n23 = null;
        String n24 = null;
        String n25 = null;
        String n26 = null;
        ic ic9 = null;
        String n27 = null;
        String n28 = null;
        String n29 = null;
        ic ic10 = null;
        String n30 = null;
        String n31 = null;
        String n32 = null;
        String n33 = null;
        String n34 = null;
        String n35 = null;
        while (parcel.dataPosition() < o) {
            final int n36 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n36)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n36);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n36);
                    set.add(1);
                    continue;
                }
                case 2: {
                    ic = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(2);
                    continue;
                }
                case 3: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, n36);
                    set.add(3);
                    continue;
                }
                case 4: {
                    ic2 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(4);
                    continue;
                }
                case 5: {
                    n = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(5);
                    continue;
                }
                case 6: {
                    n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(6);
                    continue;
                }
                case 7: {
                    n3 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(7);
                    continue;
                }
                case 8: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(8);
                    continue;
                }
                case 9: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n36);
                    set.add(9);
                    continue;
                }
                case 10: {
                    c2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(10);
                    continue;
                }
                case 11: {
                    ic3 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(11);
                    continue;
                }
                case 12: {
                    c3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(12);
                    continue;
                }
                case 13: {
                    n4 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(13);
                    continue;
                }
                case 14: {
                    n5 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(14);
                    continue;
                }
                case 15: {
                    ic4 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(15);
                    continue;
                }
                case 17: {
                    n7 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(17);
                    continue;
                }
                case 16: {
                    n6 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(16);
                    continue;
                }
                case 19: {
                    c4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(19);
                    continue;
                }
                case 18: {
                    n8 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(18);
                    continue;
                }
                case 21: {
                    n10 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(21);
                    continue;
                }
                case 20: {
                    n9 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(20);
                    continue;
                }
                case 23: {
                    n12 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(23);
                    continue;
                }
                case 22: {
                    n11 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(22);
                    continue;
                }
                case 25: {
                    n14 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(25);
                    continue;
                }
                case 24: {
                    n13 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(24);
                    continue;
                }
                case 27: {
                    n16 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(27);
                    continue;
                }
                case 26: {
                    n15 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(26);
                    continue;
                }
                case 29: {
                    ic5 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(29);
                    continue;
                }
                case 28: {
                    n17 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(28);
                    continue;
                }
                case 31: {
                    n19 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(31);
                    continue;
                }
                case 30: {
                    n18 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(30);
                    continue;
                }
                case 34: {
                    ic6 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(34);
                    continue;
                }
                case 32: {
                    n20 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(32);
                    continue;
                }
                case 33: {
                    n21 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(33);
                    continue;
                }
                case 38: {
                    i = com.google.android.gms.common.internal.safeparcel.a.l(parcel, n36);
                    set.add(38);
                    continue;
                }
                case 39: {
                    n22 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(39);
                    continue;
                }
                case 36: {
                    l = com.google.android.gms.common.internal.safeparcel.a.l(parcel, n36);
                    set.add(36);
                    continue;
                }
                case 37: {
                    ic7 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(37);
                    continue;
                }
                case 42: {
                    n23 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(42);
                    continue;
                }
                case 43: {
                    n24 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(43);
                    continue;
                }
                case 40: {
                    ic8 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(40);
                    continue;
                }
                case 41: {
                    c5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(41);
                    continue;
                }
                case 46: {
                    ic9 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(46);
                    continue;
                }
                case 47: {
                    n27 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(47);
                    continue;
                }
                case 44: {
                    n25 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(44);
                    continue;
                }
                case 45: {
                    n26 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(45);
                    continue;
                }
                case 51: {
                    n30 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(51);
                    continue;
                }
                case 50: {
                    ic10 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n36, (android.os.Parcelable$Creator<ic>)com.google.android.gms.internal.ic.CREATOR);
                    set.add(50);
                    continue;
                }
                case 49: {
                    n29 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(49);
                    continue;
                }
                case 48: {
                    n28 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(48);
                    continue;
                }
                case 55: {
                    n34 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(55);
                    continue;
                }
                case 54: {
                    n33 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(54);
                    continue;
                }
                case 53: {
                    n32 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(53);
                    continue;
                }
                case 52: {
                    n31 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(52);
                    continue;
                }
                case 56: {
                    n35 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n36);
                    set.add(56);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ic(set, g, ic, a, ic2, n, n2, n3, c, g2, c2, ic3, c3, n4, n5, ic4, n6, n7, n8, c4, n9, n10, n11, n12, n13, n14, n15, n16, n17, ic5, n18, n19, n20, n21, ic6, l, ic7, i, n22, ic8, c5, n23, n24, n25, n26, ic9, n27, n28, n29, ic10, n30, n31, n32, n33, n34, n35);
    }
    
    public ic[] bO(final int n) {
        return new ic[n];
    }
}
