// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<c>
{
    static void a(final c c, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, c.BR);
        b.a(parcel, 2, c.getName(), false);
        b.a(parcel, 3, c.getAddress(), false);
        b.c(parcel, 4, c.getType());
        b.c(parcel, 5, c.getRole());
        b.a(parcel, 6, c.isEnabled());
        b.a(parcel, 7, c.isConnected());
        b.a(parcel, 8, c.pQ(), false);
        b.H(parcel, d);
    }
    
    public c dQ(final Parcel parcel) {
        String o = null;
        boolean c = false;
        final int c2 = a.C(parcel);
        boolean c3 = false;
        int g = 0;
        int g2 = 0;
        String o2 = null;
        String o3 = null;
        int g3 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 7: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 8: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new c(g3, o3, o2, g2, g, c3, c, o);
    }
    
    public c[] fS(final int n) {
        return new c[n];
    }
}
