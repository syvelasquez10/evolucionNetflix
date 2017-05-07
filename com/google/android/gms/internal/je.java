// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class je implements Parcelable$Creator<jd>
{
    static void a(final jd jd, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, jd.getVersionCode());
        b.a(parcel, 2, (Parcelable)jd.ha(), n, false);
        b.H(parcel, d);
    }
    
    public jd F(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        jf jf = null;
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
                    jf = a.a(parcel, b, (android.os.Parcelable$Creator<jf>)com.google.android.gms.internal.jf.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new jd(g, jf);
    }
    
    public jd[] aF(final int n) {
        return new jd[n];
    }
}
