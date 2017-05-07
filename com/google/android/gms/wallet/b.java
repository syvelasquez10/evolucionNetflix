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
    static void a(final Cart cart, final Parcel parcel, int o) {
        o = com.google.android.gms.common.internal.safeparcel.b.o(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, cart.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, cart.Gj, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, cart.Gk, false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 4, cart.Gl, false);
        com.google.android.gms.common.internal.safeparcel.b.D(parcel, o);
    }
    
    public Cart aF(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        int g = 0;
        ArrayList<LineItem> c = new ArrayList<LineItem>();
        String i = null;
        while (parcel.dataPosition() < n) {
            final int j = a.m(parcel);
            switch (a.M(j)) {
                default: {
                    a.b(parcel, j);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, j);
                    continue;
                }
                case 2: {
                    i = a.m(parcel, j);
                    continue;
                }
                case 3: {
                    m = a.m(parcel, j);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, j, LineItem.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new Cart(g, i, m, c);
    }
    
    public Cart[] bx(final int n) {
        return new Cart[n];
    }
}
