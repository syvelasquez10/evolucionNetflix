// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class az implements Parcelable$Creator<ay>
{
    static void a(final ay ay, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, ay.versionCode);
        b.a(parcel, 2, ay.of, false);
        b.c(parcel, 3, ay.height);
        b.c(parcel, 4, ay.heightPixels);
        b.a(parcel, 5, ay.og);
        b.c(parcel, 6, ay.width);
        b.c(parcel, 7, ay.widthPixels);
        b.a(parcel, 8, ay.oh, n, false);
        b.H(parcel, d);
    }
    
    public ay c(final Parcel parcel) {
        ay[] array = null;
        int g = 0;
        final int c = a.C(parcel);
        int g2 = 0;
        boolean c2 = false;
        int g3 = 0;
        int g4 = 0;
        String o = null;
        int g5 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g5 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 7: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 8: {
                    array = a.b(parcel, b, (android.os.Parcelable$Creator<ay>)ay.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ay(g5, o, g4, g3, c2, g2, g, array);
    }
    
    public ay[] f(final int n) {
        return new ay[n];
    }
}
