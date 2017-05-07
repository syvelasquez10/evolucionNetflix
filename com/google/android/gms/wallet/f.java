// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class f implements Parcelable$Creator<LineItem>
{
    static void a(final LineItem lineItem, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, lineItem.getVersionCode());
        b.a(parcel, 2, lineItem.description, false);
        b.a(parcel, 3, lineItem.Gw, false);
        b.a(parcel, 4, lineItem.Gx, false);
        b.a(parcel, 5, lineItem.Gj, false);
        b.c(parcel, 6, lineItem.Gy);
        b.a(parcel, 7, lineItem.Gk, false);
        b.D(parcel, o);
    }
    
    public LineItem aJ(final Parcel parcel) {
        int g = 0;
        String m = null;
        final int n = a.n(parcel);
        String i = null;
        String j = null;
        String k = null;
        String l = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int m2 = a.m(parcel);
            switch (a.M(m2)) {
                default: {
                    a.b(parcel, m2);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, m2);
                    continue;
                }
                case 2: {
                    l = a.m(parcel, m2);
                    continue;
                }
                case 3: {
                    k = a.m(parcel, m2);
                    continue;
                }
                case 4: {
                    j = a.m(parcel, m2);
                    continue;
                }
                case 5: {
                    i = a.m(parcel, m2);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, m2);
                    continue;
                }
                case 7: {
                    m = a.m(parcel, m2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new LineItem(g2, l, k, j, i, g, m);
    }
    
    public LineItem[] bB(final int n) {
        return new LineItem[n];
    }
}
