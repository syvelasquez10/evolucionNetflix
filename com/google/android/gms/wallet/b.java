// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<Cart>
{
    static void a(final Cart cart, final Parcel parcel, int p3) {
        p3 = com.google.android.gms.common.internal.safeparcel.b.p(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, cart.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, cart.abc, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, cart.abd, false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 4, cart.abe, false);
        com.google.android.gms.common.internal.safeparcel.b.F(parcel, p3);
    }
    
    public Cart aY(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        int g = 0;
        ArrayList<LineItem> c = new ArrayList<LineItem>();
        String n2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, n3, LineItem.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new Cart(g, n2, n, c);
    }
    
    public Cart[] ck(final int n) {
        return new Cart[n];
    }
}
