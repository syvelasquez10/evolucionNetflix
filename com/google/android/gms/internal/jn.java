// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class jn implements Parcelable$Creator<jm>
{
    static void a(final jm jm, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, jm.getVersionCode());
        b.c(parcel, 2, jm.hu(), false);
        b.a(parcel, 3, jm.hv(), false);
        b.H(parcel, d);
    }
    
    public jm K(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        ArrayList<jm$a> c2 = null;
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
                    c2 = a.c(parcel, b, (android.os.Parcelable$Creator<jm$a>)jm$a.CREATOR);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new jm(g, c2, o);
    }
    
    public jm[] aK(final int n) {
        return new jm[n];
    }
}
