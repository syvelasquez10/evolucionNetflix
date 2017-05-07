// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class cy implements Parcelable$Creator<cx>
{
    static void a(final cx cx, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, cx.versionCode);
        b.a(parcel, 2, cx.pf, false);
        b.a(parcel, 3, (Parcelable)cx.pg, n, false);
        b.a(parcel, 4, (Parcelable)cx.kN, n, false);
        b.a(parcel, 5, cx.kH, false);
        b.a(parcel, 6, (Parcelable)cx.applicationInfo, n, false);
        b.a(parcel, 7, (Parcelable)cx.ph, n, false);
        b.a(parcel, 8, cx.pi, false);
        b.a(parcel, 9, cx.pj, false);
        b.a(parcel, 10, cx.pk, false);
        b.a(parcel, 11, (Parcelable)cx.kK, n, false);
        b.a(parcel, 12, cx.pl, false);
        b.F(parcel, p3);
    }
    
    public cx f(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        Bundle p = null;
        ah ah = null;
        ak ak = null;
        String n = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String n2 = null;
        String n3 = null;
        String n4 = null;
        dx dx = null;
        Bundle p2 = null;
        while (parcel.dataPosition() < o) {
            final int n5 = a.n(parcel);
            switch (a.R(n5)) {
                default: {
                    a.b(parcel, n5);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n5);
                    continue;
                }
                case 2: {
                    p = a.p(parcel, n5);
                    continue;
                }
                case 3: {
                    ah = a.a(parcel, n5, (android.os.Parcelable$Creator<ah>)com.google.android.gms.internal.ah.CREATOR);
                    continue;
                }
                case 4: {
                    ak = a.a(parcel, n5, (android.os.Parcelable$Creator<ak>)com.google.android.gms.internal.ak.CREATOR);
                    continue;
                }
                case 5: {
                    n = a.n(parcel, n5);
                    continue;
                }
                case 6: {
                    applicationInfo = a.a(parcel, n5, (android.os.Parcelable$Creator<ApplicationInfo>)ApplicationInfo.CREATOR);
                    continue;
                }
                case 7: {
                    packageInfo = a.a(parcel, n5, (android.os.Parcelable$Creator<PackageInfo>)PackageInfo.CREATOR);
                    continue;
                }
                case 8: {
                    n2 = a.n(parcel, n5);
                    continue;
                }
                case 9: {
                    n3 = a.n(parcel, n5);
                    continue;
                }
                case 10: {
                    n4 = a.n(parcel, n5);
                    continue;
                }
                case 11: {
                    dx = a.a(parcel, n5, (android.os.Parcelable$Creator<dx>)com.google.android.gms.internal.dx.CREATOR);
                    continue;
                }
                case 12: {
                    p2 = a.p(parcel, n5);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new cx(g, p, ah, ak, n, applicationInfo, packageInfo, n2, n3, n4, dx, p2);
    }
    
    public cx[] k(final int n) {
        return new cx[n];
    }
}
