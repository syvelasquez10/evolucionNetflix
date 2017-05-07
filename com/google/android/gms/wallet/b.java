// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a$a;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<Cart>
{
    static void a(final Cart cart, final Parcel parcel, int d) {
        d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, cart.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, cart.ask, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, cart.asl, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, cart.asm, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public Cart do(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        ArrayList<LineItem> c2 = new ArrayList<LineItem>();
        String o2 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    c2 = a.c(parcel, b, LineItem.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new Cart(g, o2, o, c2);
    }
    
    public Cart[] fo(final int n) {
        return new Cart[n];
    }
}
