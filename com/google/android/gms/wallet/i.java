// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class i implements Parcelable$Creator<LineItem>
{
    static void a(final LineItem lineItem, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, lineItem.getVersionCode());
        b.a(parcel, 2, lineItem.description, false);
        b.a(parcel, 3, lineItem.abv, false);
        b.a(parcel, 4, lineItem.abw, false);
        b.a(parcel, 5, lineItem.abc, false);
        b.c(parcel, 6, lineItem.abx);
        b.a(parcel, 7, lineItem.abd, false);
        b.F(parcel, p3);
    }
    
    public LineItem be(final Parcel parcel) {
        int g = 0;
        String n = null;
        final int o = a.o(parcel);
        String n2 = null;
        String n3 = null;
        String n4 = null;
        String n5 = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n6 = a.n(parcel);
            switch (a.R(n6)) {
                default: {
                    a.b(parcel, n6);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n6);
                    continue;
                }
                case 2: {
                    n5 = a.n(parcel, n6);
                    continue;
                }
                case 3: {
                    n4 = a.n(parcel, n6);
                    continue;
                }
                case 4: {
                    n3 = a.n(parcel, n6);
                    continue;
                }
                case 5: {
                    n2 = a.n(parcel, n6);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, n6);
                    continue;
                }
                case 7: {
                    n = a.n(parcel, n6);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new LineItem(g2, n5, n4, n3, n2, g, n);
    }
    
    public LineItem[] cq(final int n) {
        return new LineItem[n];
    }
}
