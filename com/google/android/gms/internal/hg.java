// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hg implements Parcelable$Creator<hf>
{
    static void a(final hf hf, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.a(parcel, 1, hf.name, false);
        b.c(parcel, 1000, hf.versionCode);
        b.a(parcel, 2, hf.Bf, false);
        b.a(parcel, 3, hf.Bg, false);
        b.a(parcel, 4, hf.Bh, false);
        b.a(parcel, 5, hf.Bi, false);
        b.D(parcel, o);
    }
    
    public hf ap(final Parcel parcel) {
        List<String> y = null;
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        String i = null;
        String j = null;
        String k = null;
        while (parcel.dataPosition() < n) {
            final int l = a.m(parcel);
            switch (a.M(l)) {
                default: {
                    a.b(parcel, l);
                    continue;
                }
                case 1: {
                    k = a.m(parcel, l);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, l);
                    continue;
                }
                case 2: {
                    j = a.m(parcel, l);
                    continue;
                }
                case 3: {
                    i = a.m(parcel, l);
                    continue;
                }
                case 4: {
                    m = a.m(parcel, l);
                    continue;
                }
                case 5: {
                    y = a.y(parcel, l);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new hf(g, k, j, i, m, y);
    }
    
    public hf[] bf(final int n) {
        return new hf[n];
    }
}
