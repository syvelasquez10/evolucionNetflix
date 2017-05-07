// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ca implements Parcelable$Creator<cb>
{
    static void a(final cb cb, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, cb.versionCode);
        b.a(parcel, 2, cb.nN, false);
        b.a(parcel, 3, cb.nO, false);
        b.a(parcel, 4, cb.mimeType, false);
        b.a(parcel, 5, cb.packageName, false);
        b.a(parcel, 6, cb.nP, false);
        b.a(parcel, 7, cb.nQ, false);
        b.a(parcel, 8, cb.nR, false);
        b.F(parcel, p3);
    }
    
    public cb d(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        int g = 0;
        String n2 = null;
        String n3 = null;
        String n4 = null;
        String n5 = null;
        String n6 = null;
        String n7 = null;
        while (parcel.dataPosition() < o) {
            final int n8 = a.n(parcel);
            switch (a.R(n8)) {
                default: {
                    a.b(parcel, n8);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n8);
                    continue;
                }
                case 2: {
                    n7 = a.n(parcel, n8);
                    continue;
                }
                case 3: {
                    n6 = a.n(parcel, n8);
                    continue;
                }
                case 4: {
                    n5 = a.n(parcel, n8);
                    continue;
                }
                case 5: {
                    n4 = a.n(parcel, n8);
                    continue;
                }
                case 6: {
                    n3 = a.n(parcel, n8);
                    continue;
                }
                case 7: {
                    n2 = a.n(parcel, n8);
                    continue;
                }
                case 8: {
                    n = a.n(parcel, n8);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new cb(g, n7, n6, n5, n4, n3, n2, n);
    }
    
    public cb[] h(final int n) {
        return new cb[n];
    }
}
