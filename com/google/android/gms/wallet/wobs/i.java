// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class i implements Parcelable$Creator<f>
{
    static void a(final f f, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, f.getVersionCode());
        b.a(parcel, 2, f.label, false);
        b.a(parcel, 3, (Parcelable)f.aur, n, false);
        b.a(parcel, 4, f.type, false);
        b.a(parcel, 5, (Parcelable)f.asR, n, false);
        b.H(parcel, d);
    }
    
    public f dK(final Parcel parcel) {
        l l = null;
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        g g2 = null;
        String o2 = null;
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
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    g2 = a.a(parcel, b, com.google.android.gms.wallet.wobs.g.CREATOR);
                    continue;
                }
                case 4: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    l = a.a(parcel, b, com.google.android.gms.wallet.wobs.l.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new f(g, o2, g2, o, l);
    }
    
    public f[] fM(final int n) {
        return new f[n];
    }
}
