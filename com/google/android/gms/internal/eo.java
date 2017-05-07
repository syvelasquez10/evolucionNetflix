// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class eo implements Parcelable$Creator<en>
{
    static void a(final en en, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, en.getVersionCode());
        b.a(parcel, 2, (Parcelable)en.ce(), n, false);
        b.D(parcel, o);
    }
    
    public en[] O(final int n) {
        return new en[n];
    }
    
    public en q(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        ep ep = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    ep = a.a(parcel, m, (android.os.Parcelable$Creator<ep>)com.google.android.gms.internal.ep.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new en(g, ep);
    }
}
