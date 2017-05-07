// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class jr implements Parcelable$Creator<jo>
{
    static void a(final jo jo, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, jo.getVersionCode());
        b.a(parcel, 2, jo.label, false);
        b.a(parcel, 3, (Parcelable)jo.adg, n, false);
        b.a(parcel, 4, jo.type, false);
        b.a(parcel, 5, (Parcelable)jo.abJ, n, false);
        b.F(parcel, p3);
    }
    
    public jo bt(final Parcel parcel) {
        ju ju = null;
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        jp jp = null;
        String n2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 3: {
                    jp = a.a(parcel, n3, com.google.android.gms.internal.jp.CREATOR);
                    continue;
                }
                case 4: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 5: {
                    ju = a.a(parcel, n3, com.google.android.gms.internal.ju.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new jo(g, n2, jp, n, ju);
    }
    
    public jo[] cH(final int n) {
        return new jo[n];
    }
}
