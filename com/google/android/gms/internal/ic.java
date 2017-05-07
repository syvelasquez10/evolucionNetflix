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

public class ic implements Parcelable$Creator<ib>
{
    static void a(final ib ib, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        final Set<Integer> fa = ib.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, ib.getVersionCode());
        }
        if (fa.contains(2)) {
            b.a(parcel, 2, (Parcelable)ib.fb(), n, true);
        }
        if (fa.contains(3)) {
            b.a(parcel, 3, ib.getAdditionalName(), true);
        }
        if (fa.contains(4)) {
            b.a(parcel, 4, (Parcelable)ib.fc(), n, true);
        }
        if (fa.contains(5)) {
            b.a(parcel, 5, ib.getAddressCountry(), true);
        }
        if (fa.contains(6)) {
            b.a(parcel, 6, ib.getAddressLocality(), true);
        }
        if (fa.contains(7)) {
            b.a(parcel, 7, ib.getAddressRegion(), true);
        }
        if (fa.contains(8)) {
            b.b(parcel, 8, ib.fd(), true);
        }
        if (fa.contains(9)) {
            b.c(parcel, 9, ib.getAttendeeCount());
        }
        if (fa.contains(10)) {
            b.b(parcel, 10, ib.fe(), true);
        }
        if (fa.contains(11)) {
            b.a(parcel, 11, (Parcelable)ib.ff(), n, true);
        }
        if (fa.contains(12)) {
            b.b(parcel, 12, ib.fg(), true);
        }
        if (fa.contains(13)) {
            b.a(parcel, 13, ib.getBestRating(), true);
        }
        if (fa.contains(14)) {
            b.a(parcel, 14, ib.getBirthDate(), true);
        }
        if (fa.contains(15)) {
            b.a(parcel, 15, (Parcelable)ib.fh(), n, true);
        }
        if (fa.contains(17)) {
            b.a(parcel, 17, ib.getContentSize(), true);
        }
        if (fa.contains(16)) {
            b.a(parcel, 16, ib.getCaption(), true);
        }
        if (fa.contains(19)) {
            b.b(parcel, 19, ib.fi(), true);
        }
        if (fa.contains(18)) {
            b.a(parcel, 18, ib.getContentUrl(), true);
        }
        if (fa.contains(21)) {
            b.a(parcel, 21, ib.getDateModified(), true);
        }
        if (fa.contains(20)) {
            b.a(parcel, 20, ib.getDateCreated(), true);
        }
        if (fa.contains(23)) {
            b.a(parcel, 23, ib.getDescription(), true);
        }
        if (fa.contains(22)) {
            b.a(parcel, 22, ib.getDatePublished(), true);
        }
        if (fa.contains(25)) {
            b.a(parcel, 25, ib.getEmbedUrl(), true);
        }
        if (fa.contains(24)) {
            b.a(parcel, 24, ib.getDuration(), true);
        }
        if (fa.contains(27)) {
            b.a(parcel, 27, ib.getFamilyName(), true);
        }
        if (fa.contains(26)) {
            b.a(parcel, 26, ib.getEndDate(), true);
        }
        if (fa.contains(29)) {
            b.a(parcel, 29, (Parcelable)ib.fj(), n, true);
        }
        if (fa.contains(28)) {
            b.a(parcel, 28, ib.getGender(), true);
        }
        if (fa.contains(31)) {
            b.a(parcel, 31, ib.getHeight(), true);
        }
        if (fa.contains(30)) {
            b.a(parcel, 30, ib.getGivenName(), true);
        }
        if (fa.contains(34)) {
            b.a(parcel, 34, (Parcelable)ib.fk(), n, true);
        }
        if (fa.contains(32)) {
            b.a(parcel, 32, ib.getId(), true);
        }
        if (fa.contains(33)) {
            b.a(parcel, 33, ib.getImage(), true);
        }
        if (fa.contains(38)) {
            b.a(parcel, 38, ib.getLongitude());
        }
        if (fa.contains(39)) {
            b.a(parcel, 39, ib.getName(), true);
        }
        if (fa.contains(36)) {
            b.a(parcel, 36, ib.getLatitude());
        }
        if (fa.contains(37)) {
            b.a(parcel, 37, (Parcelable)ib.fl(), n, true);
        }
        if (fa.contains(42)) {
            b.a(parcel, 42, ib.getPlayerType(), true);
        }
        if (fa.contains(43)) {
            b.a(parcel, 43, ib.getPostOfficeBoxNumber(), true);
        }
        if (fa.contains(40)) {
            b.a(parcel, 40, (Parcelable)ib.fm(), n, true);
        }
        if (fa.contains(41)) {
            b.b(parcel, 41, ib.fn(), true);
        }
        if (fa.contains(46)) {
            b.a(parcel, 46, (Parcelable)ib.fo(), n, true);
        }
        if (fa.contains(47)) {
            b.a(parcel, 47, ib.getStartDate(), true);
        }
        if (fa.contains(44)) {
            b.a(parcel, 44, ib.getPostalCode(), true);
        }
        if (fa.contains(45)) {
            b.a(parcel, 45, ib.getRatingValue(), true);
        }
        if (fa.contains(51)) {
            b.a(parcel, 51, ib.getThumbnailUrl(), true);
        }
        if (fa.contains(50)) {
            b.a(parcel, 50, (Parcelable)ib.fp(), n, true);
        }
        if (fa.contains(49)) {
            b.a(parcel, 49, ib.getText(), true);
        }
        if (fa.contains(48)) {
            b.a(parcel, 48, ib.getStreetAddress(), true);
        }
        if (fa.contains(55)) {
            b.a(parcel, 55, ib.getWidth(), true);
        }
        if (fa.contains(54)) {
            b.a(parcel, 54, ib.getUrl(), true);
        }
        if (fa.contains(53)) {
            b.a(parcel, 53, ib.getType(), true);
        }
        if (fa.contains(52)) {
            b.a(parcel, 52, ib.getTickerSymbol(), true);
        }
        if (fa.contains(56)) {
            b.a(parcel, 56, ib.getWorstRating(), true);
        }
        b.D(parcel, o);
    }
    
    public ib as(final Parcel parcel) {
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        ib ib = null;
        List<String> y = null;
        ib ib2 = null;
        String m = null;
        String i = null;
        String j = null;
        List<ib> c = null;
        int g2 = 0;
        List<ib> c2 = null;
        ib ib3 = null;
        List<ib> c3 = null;
        String k = null;
        String l = null;
        ib ib4 = null;
        String m2 = null;
        String m3 = null;
        String m4 = null;
        List<ib> c4 = null;
        String m5 = null;
        String m6 = null;
        String m7 = null;
        String m8 = null;
        String m9 = null;
        String m10 = null;
        String m11 = null;
        String m12 = null;
        String m13 = null;
        ib ib5 = null;
        String m14 = null;
        String m15 = null;
        String m16 = null;
        String m17 = null;
        ib ib6 = null;
        double k2 = 0.0;
        ib ib7 = null;
        double k3 = 0.0;
        String m18 = null;
        ib ib8 = null;
        List<ib> c5 = null;
        String m19 = null;
        String m20 = null;
        String m21 = null;
        String m22 = null;
        ib ib9 = null;
        String m23 = null;
        String m24 = null;
        String m25 = null;
        ib ib10 = null;
        String m26 = null;
        String m27 = null;
        String m28 = null;
        String m29 = null;
        String m30 = null;
        String m31 = null;
        while (parcel.dataPosition() < n) {
            final int m32 = a.m(parcel);
            switch (a.M(m32)) {
                default: {
                    a.b(parcel, m32);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m32);
                    set.add(1);
                    continue;
                }
                case 2: {
                    ib = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(2);
                    continue;
                }
                case 3: {
                    y = a.y(parcel, m32);
                    set.add(3);
                    continue;
                }
                case 4: {
                    ib2 = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(4);
                    continue;
                }
                case 5: {
                    m = a.m(parcel, m32);
                    set.add(5);
                    continue;
                }
                case 6: {
                    i = a.m(parcel, m32);
                    set.add(6);
                    continue;
                }
                case 7: {
                    j = a.m(parcel, m32);
                    set.add(7);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(8);
                    continue;
                }
                case 9: {
                    g2 = a.g(parcel, m32);
                    set.add(9);
                    continue;
                }
                case 10: {
                    c2 = a.c(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(10);
                    continue;
                }
                case 11: {
                    ib3 = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(11);
                    continue;
                }
                case 12: {
                    c3 = a.c(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(12);
                    continue;
                }
                case 13: {
                    k = a.m(parcel, m32);
                    set.add(13);
                    continue;
                }
                case 14: {
                    l = a.m(parcel, m32);
                    set.add(14);
                    continue;
                }
                case 15: {
                    ib4 = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(15);
                    continue;
                }
                case 17: {
                    m3 = a.m(parcel, m32);
                    set.add(17);
                    continue;
                }
                case 16: {
                    m2 = a.m(parcel, m32);
                    set.add(16);
                    continue;
                }
                case 19: {
                    c4 = a.c(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(19);
                    continue;
                }
                case 18: {
                    m4 = a.m(parcel, m32);
                    set.add(18);
                    continue;
                }
                case 21: {
                    m6 = a.m(parcel, m32);
                    set.add(21);
                    continue;
                }
                case 20: {
                    m5 = a.m(parcel, m32);
                    set.add(20);
                    continue;
                }
                case 23: {
                    m8 = a.m(parcel, m32);
                    set.add(23);
                    continue;
                }
                case 22: {
                    m7 = a.m(parcel, m32);
                    set.add(22);
                    continue;
                }
                case 25: {
                    m10 = a.m(parcel, m32);
                    set.add(25);
                    continue;
                }
                case 24: {
                    m9 = a.m(parcel, m32);
                    set.add(24);
                    continue;
                }
                case 27: {
                    m12 = a.m(parcel, m32);
                    set.add(27);
                    continue;
                }
                case 26: {
                    m11 = a.m(parcel, m32);
                    set.add(26);
                    continue;
                }
                case 29: {
                    ib5 = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(29);
                    continue;
                }
                case 28: {
                    m13 = a.m(parcel, m32);
                    set.add(28);
                    continue;
                }
                case 31: {
                    m15 = a.m(parcel, m32);
                    set.add(31);
                    continue;
                }
                case 30: {
                    m14 = a.m(parcel, m32);
                    set.add(30);
                    continue;
                }
                case 34: {
                    ib6 = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(34);
                    continue;
                }
                case 32: {
                    m16 = a.m(parcel, m32);
                    set.add(32);
                    continue;
                }
                case 33: {
                    m17 = a.m(parcel, m32);
                    set.add(33);
                    continue;
                }
                case 38: {
                    k3 = a.k(parcel, m32);
                    set.add(38);
                    continue;
                }
                case 39: {
                    m18 = a.m(parcel, m32);
                    set.add(39);
                    continue;
                }
                case 36: {
                    k2 = a.k(parcel, m32);
                    set.add(36);
                    continue;
                }
                case 37: {
                    ib7 = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(37);
                    continue;
                }
                case 42: {
                    m19 = a.m(parcel, m32);
                    set.add(42);
                    continue;
                }
                case 43: {
                    m20 = a.m(parcel, m32);
                    set.add(43);
                    continue;
                }
                case 40: {
                    ib8 = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(40);
                    continue;
                }
                case 41: {
                    c5 = a.c(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(41);
                    continue;
                }
                case 46: {
                    ib9 = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(46);
                    continue;
                }
                case 47: {
                    m23 = a.m(parcel, m32);
                    set.add(47);
                    continue;
                }
                case 44: {
                    m21 = a.m(parcel, m32);
                    set.add(44);
                    continue;
                }
                case 45: {
                    m22 = a.m(parcel, m32);
                    set.add(45);
                    continue;
                }
                case 51: {
                    m26 = a.m(parcel, m32);
                    set.add(51);
                    continue;
                }
                case 50: {
                    ib10 = a.a(parcel, m32, (android.os.Parcelable$Creator<ib>)com.google.android.gms.internal.ib.CREATOR);
                    set.add(50);
                    continue;
                }
                case 49: {
                    m25 = a.m(parcel, m32);
                    set.add(49);
                    continue;
                }
                case 48: {
                    m24 = a.m(parcel, m32);
                    set.add(48);
                    continue;
                }
                case 55: {
                    m30 = a.m(parcel, m32);
                    set.add(55);
                    continue;
                }
                case 54: {
                    m29 = a.m(parcel, m32);
                    set.add(54);
                    continue;
                }
                case 53: {
                    m28 = a.m(parcel, m32);
                    set.add(53);
                    continue;
                }
                case 52: {
                    m27 = a.m(parcel, m32);
                    set.add(52);
                    continue;
                }
                case 56: {
                    m31 = a.m(parcel, m32);
                    set.add(56);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ib(set, g, ib, y, ib2, m, i, j, c, g2, c2, ib3, c3, k, l, ib4, m2, m3, m4, c4, m5, m6, m7, m8, m9, m10, m11, m12, m13, ib5, m14, m15, m16, m17, ib6, k2, ib7, k3, m18, ib8, c5, m19, m20, m21, m22, ib9, m23, m24, m25, ib10, m26, m27, m28, m29, m30, m31);
    }
    
    public ib[] bk(final int n) {
        return new ib[n];
    }
}
