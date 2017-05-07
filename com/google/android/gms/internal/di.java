// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class di implements Parcelable$Creator<dj>
{
    static void a(final dj dj, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, dj.versionCode);
        b.a(parcel, 2, dj.rp, false);
        b.a(parcel, 3, dj.rq, false);
        b.a(parcel, 4, dj.mimeType, false);
        b.a(parcel, 5, dj.packageName, false);
        b.a(parcel, 6, dj.rr, false);
        b.a(parcel, 7, dj.rs, false);
        b.a(parcel, 8, dj.rt, false);
        b.H(parcel, d);
    }
    
    public dj e(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        String o5 = null;
        String o6 = null;
        String o7 = null;
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
                    o7 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o6 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new dj(g, o7, o6, o5, o4, o3, o2, o);
    }
    
    public dj[] l(final int n) {
        return new dj[n];
    }
}
