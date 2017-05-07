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
    static void a(final LineItem lineItem, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, lineItem.getVersionCode());
        b.a(parcel, 2, lineItem.description, false);
        b.a(parcel, 3, lineItem.asE, false);
        b.a(parcel, 4, lineItem.asF, false);
        b.a(parcel, 5, lineItem.ask, false);
        b.c(parcel, 6, lineItem.asG);
        b.a(parcel, 7, lineItem.asl, false);
        b.H(parcel, d);
    }
    
    public LineItem du(final Parcel parcel) {
        int g = 0;
        String o = null;
        final int c = a.C(parcel);
        String o2 = null;
        String o3 = null;
        String o4 = null;
        String o5 = null;
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
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 7: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new LineItem(g2, o5, o4, o3, o2, g, o);
    }
    
    public LineItem[] fu(final int n) {
        return new LineItem[n];
    }
}
