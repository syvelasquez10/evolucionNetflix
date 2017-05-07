// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a$a;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class fj implements Parcelable$Creator<fi>
{
    static void a(final fi fi, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, fi.versionCode);
        b.a(parcel, 2, fi.tw, false);
        b.a(parcel, 3, (Parcelable)fi.tx, n, false);
        b.a(parcel, 4, (Parcelable)fi.lH, n, false);
        b.a(parcel, 5, fi.lA, false);
        b.a(parcel, 6, (Parcelable)fi.applicationInfo, n, false);
        b.a(parcel, 7, (Parcelable)fi.ty, n, false);
        b.a(parcel, 8, fi.tz, false);
        b.a(parcel, 9, fi.tA, false);
        b.a(parcel, 10, fi.tB, false);
        b.a(parcel, 11, (Parcelable)fi.lD, n, false);
        b.a(parcel, 12, fi.tC, false);
        b.c(parcel, 13, fi.tD);
        b.b(parcel, 14, fi.lS, false);
        b.a(parcel, 15, fi.tE, false);
        b.a(parcel, 16, fi.tF);
        b.H(parcel, d);
    }
    
    public fi h(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        Bundle q = null;
        av av = null;
        ay ay = null;
        String o = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        gt gt = null;
        Bundle q2 = null;
        int g2 = 0;
        List<String> c2 = null;
        Bundle q3 = null;
        boolean c3 = false;
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
                    q = a.q(parcel, b);
                    continue;
                }
                case 3: {
                    av = a.a(parcel, b, (android.os.Parcelable$Creator<av>)com.google.android.gms.internal.av.CREATOR);
                    continue;
                }
                case 4: {
                    ay = a.a(parcel, b, (android.os.Parcelable$Creator<ay>)com.google.android.gms.internal.ay.CREATOR);
                    continue;
                }
                case 5: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    applicationInfo = a.a(parcel, b, (android.os.Parcelable$Creator<ApplicationInfo>)ApplicationInfo.CREATOR);
                    continue;
                }
                case 7: {
                    packageInfo = a.a(parcel, b, (android.os.Parcelable$Creator<PackageInfo>)PackageInfo.CREATOR);
                    continue;
                }
                case 8: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 9: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 10: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 11: {
                    gt = a.a(parcel, b, (android.os.Parcelable$Creator<gt>)com.google.android.gms.internal.gt.CREATOR);
                    continue;
                }
                case 12: {
                    q2 = a.q(parcel, b);
                    continue;
                }
                case 13: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 14: {
                    c2 = a.C(parcel, b);
                    continue;
                }
                case 15: {
                    q3 = a.q(parcel, b);
                    continue;
                }
                case 16: {
                    c3 = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new fi(g, q, av, ay, o, applicationInfo, packageInfo, o2, o3, o4, gt, q2, g2, c2, q3, c3);
    }
    
    public fi[] p(final int n) {
        return new fi[n];
    }
}
