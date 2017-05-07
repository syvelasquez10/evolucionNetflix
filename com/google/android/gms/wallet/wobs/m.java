// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class m implements Parcelable$Creator<l>
{
    static void a(final l l, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, l.getVersionCode());
        b.a(parcel, 2, l.auz);
        b.a(parcel, 3, l.auA);
        b.H(parcel, d);
    }
    
    public l dM(final Parcel parcel) {
        long i = 0L;
        final int c = a.C(parcel);
        int g = 0;
        long j = 0L;
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
                    j = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new l(g, j, i);
    }
    
    public l[] fO(final int n) {
        return new l[n];
    }
}
