// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class jo implements Parcelable$Creator<jm.a>
{
    static void a(final jm.a a, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, a.versionCode);
        b.a(parcel, 2, a.className, false);
        b.c(parcel, 3, a.MD, false);
        b.H(parcel, d);
    }
    
    public jm.a L(final Parcel parcel) {
        ArrayList<jm.b> c = null;
        final int c2 = a.C(parcel);
        int g = 0;
        String o = null;
        while (parcel.dataPosition() < c2) {
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
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b, (android.os.Parcelable$Creator<jm.b>)jm.b.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new jm.a(g, o, c);
    }
    
    public jm.a[] aL(final int n) {
        return new jm.a[n];
    }
}
