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

public class jg implements Parcelable$Creator<jf>
{
    static void a(final jf jf, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, jf.getVersionCode());
        b.c(parcel, 2, jf.hc(), false);
        b.H(parcel, d);
    }
    
    public jf G(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        ArrayList<jf$a> c2 = null;
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
                    c2 = a.c(parcel, b, (android.os.Parcelable$Creator<jf$a>)jf$a.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new jf(g, c2);
    }
    
    public jf[] aG(final int n) {
        return new jf[n];
    }
}
