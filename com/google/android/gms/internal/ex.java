// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ex implements Parcelable$Creator<ev.a>
{
    static void a(final ev.a a, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, a.versionCode);
        b.a(parcel, 2, a.className, false);
        b.b(parcel, 3, a.qv, false);
        b.D(parcel, o);
    }
    
    public ev.a[] U(final int n) {
        return new ev.a[n];
    }
    
    public ev.a w(final Parcel parcel) {
        ArrayList<ev.b> c = null;
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, i, (android.os.Parcelable$Creator<ev.b>)ev.b.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ev.a(g, m, c);
    }
}
