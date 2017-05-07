// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class u implements Parcelable$Creator<Value>
{
    static void a(final Value value, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, value.getFormat());
        b.c(parcel, 1000, value.getVersionCode());
        b.a(parcel, 2, value.isSet());
        b.a(parcel, 3, value.iS());
        b.H(parcel, d);
    }
    
    public Value bx(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        float l = 0.0f;
        int g = 0;
        int g2 = 0;
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
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 3: {
                    l = a.l(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new Value(g2, g, c, l);
    }
    
    public Value[] cO(final int n) {
        return new Value[n];
    }
}
