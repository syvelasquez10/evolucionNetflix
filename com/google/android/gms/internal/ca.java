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

public class ca implements Parcelable$Creator<bz>
{
    static void a(final bz bz, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, bz.versionCode);
        b.a(parcel, 2, bz.hq, false);
        b.a(parcel, 3, (Parcelable)bz.hr, n, false);
        b.a(parcel, 4, (Parcelable)bz.em, n, false);
        b.a(parcel, 5, bz.adUnitId, false);
        b.a(parcel, 6, (Parcelable)bz.applicationInfo, n, false);
        b.a(parcel, 7, (Parcelable)bz.hs, n, false);
        b.a(parcel, 8, bz.ht, false);
        b.a(parcel, 9, bz.hu, false);
        b.a(parcel, 10, bz.hv, false);
        b.a(parcel, 11, (Parcelable)bz.ej, n, false);
        b.D(parcel, o);
    }
    
    public bz f(final Parcel parcel) {
        cu cu = null;
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        String i = null;
        String j = null;
        PackageInfo packageInfo = null;
        ApplicationInfo applicationInfo = null;
        String k = null;
        x x = null;
        v v = null;
        Bundle o = null;
        while (parcel.dataPosition() < n) {
            final int l = a.m(parcel);
            switch (a.M(l)) {
                default: {
                    a.b(parcel, l);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, l);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, l);
                    continue;
                }
                case 3: {
                    v = a.a(parcel, l, (android.os.Parcelable$Creator<v>)com.google.android.gms.internal.v.CREATOR);
                    continue;
                }
                case 4: {
                    x = a.a(parcel, l, (android.os.Parcelable$Creator<x>)com.google.android.gms.internal.x.CREATOR);
                    continue;
                }
                case 5: {
                    k = a.m(parcel, l);
                    continue;
                }
                case 6: {
                    applicationInfo = a.a(parcel, l, (android.os.Parcelable$Creator<ApplicationInfo>)ApplicationInfo.CREATOR);
                    continue;
                }
                case 7: {
                    packageInfo = a.a(parcel, l, (android.os.Parcelable$Creator<PackageInfo>)PackageInfo.CREATOR);
                    continue;
                }
                case 8: {
                    j = a.m(parcel, l);
                    continue;
                }
                case 9: {
                    i = a.m(parcel, l);
                    continue;
                }
                case 10: {
                    m = a.m(parcel, l);
                    continue;
                }
                case 11: {
                    cu = a.a(parcel, l, (android.os.Parcelable$Creator<cu>)com.google.android.gms.internal.cu.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new bz(g, o, v, x, k, applicationInfo, packageInfo, j, i, m, cu);
    }
    
    public bz[] k(final int n) {
        return new bz[n];
    }
}
