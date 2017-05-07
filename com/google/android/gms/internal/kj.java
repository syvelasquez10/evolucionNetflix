// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class kj implements Parcelable$Creator<ki>
{
    static void a(final ki ki, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, ki.xH);
        b.c(parcel, 2, ki.fA());
        b.a(parcel, 3, ki.getPath(), false);
        b.a(parcel, 4, ki.getData(), false);
        b.a(parcel, 5, ki.getSource(), false);
        b.F(parcel, p3);
    }
    
    public ki by(final Parcel parcel) {
        int g = 0;
        String n = null;
        final int o = a.o(parcel);
        byte[] q = null;
        String n2 = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 3: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 4: {
                    q = a.q(parcel, n3);
                    continue;
                }
                case 5: {
                    n = a.n(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ki(g2, g, n2, q, n);
    }
    
    public ki[] cN(final int n) {
        return new ki[n];
    }
}
