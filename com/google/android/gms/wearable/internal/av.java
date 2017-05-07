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

public class av implements Parcelable$Creator<au>
{
    static void a(final au au, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, au.versionCode);
        b.c(parcel, 2, au.statusCode);
        b.a(parcel, 3, au.avC);
        b.c(parcel, 4, au.avE, false);
        b.H(parcel, d);
    }
    
    public au ei(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        long i = 0L;
        List<am> c2 = null;
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
                    i = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    c2 = a.c(parcel, b, am.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new au(g2, g, i, c2);
    }
    
    public au[] gk(final int n) {
        return new au[n];
    }
}
