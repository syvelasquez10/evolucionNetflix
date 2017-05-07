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

public class ew implements Parcelable$Creator<ev>
{
    static void a(final ev ev, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, ev.getVersionCode());
        b.b(parcel, 2, ev.cy(), false);
        b.a(parcel, 3, ev.cz(), false);
        b.D(parcel, o);
    }
    
    public ev[] T(final int n) {
        return new ev[n];
    }
    
    public ev v(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        int g = 0;
        ArrayList<ev.a> c = null;
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
                    c = a.c(parcel, i, (android.os.Parcelable$Creator<ev.a>)ev.a.CREATOR);
                    continue;
                }
                case 3: {
                    m = a.m(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ev(g, c, m);
    }
}
