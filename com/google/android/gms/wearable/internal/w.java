// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class w implements Parcelable$Creator<v>
{
    static void a(final v v, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, v.versionCode);
        b.c(parcel, 2, v.statusCode);
        b.c(parcel, 3, v.avo, false);
        b.H(parcel, d);
    }
    
    public v dY(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        List<ak> c2 = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    c2 = a.c(parcel, b, ak.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new v(g2, g, c2);
    }
    
    public v[] ga(final int n) {
        return new v[n];
    }
}
