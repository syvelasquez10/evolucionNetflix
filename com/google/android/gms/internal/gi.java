// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class gi implements Parcelable$Creator<gh>
{
    static void a(final gh gh, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1000, gh.getVersionCode());
        b.a(parcel, 2, gh.isEnabled());
        b.a(parcel, 3, gh.dD());
        b.a(parcel, 4, gh.dE());
        b.a(parcel, 5, gh.dF());
        b.b(parcel, 6, gh.dG(), false);
        b.D(parcel, o);
    }
    
    public gh[] aU(final int n) {
        return new gh[n];
    }
    
    public gh ah(final Parcel parcel) {
        boolean c = false;
        final int n = a.n(parcel);
        ArrayList<el> c2 = null;
        boolean c3 = false;
        boolean c4 = false;
        boolean c5 = false;
        int g = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    c5 = a.c(parcel, m);
                    continue;
                }
                case 3: {
                    c4 = a.c(parcel, m);
                    continue;
                }
                case 4: {
                    c3 = a.c(parcel, m);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, m);
                    continue;
                }
                case 6: {
                    c2 = a.c(parcel, m, (android.os.Parcelable$Creator<el>)el.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new gh(g, c5, c4, c3, c, c2);
    }
}
