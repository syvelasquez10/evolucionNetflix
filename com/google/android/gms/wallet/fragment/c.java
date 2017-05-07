// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<WalletFragmentStyle>
{
    static void a(final WalletFragmentStyle walletFragmentStyle, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, walletFragmentStyle.xH);
        b.a(parcel, 2, walletFragmentStyle.acT, false);
        b.c(parcel, 3, walletFragmentStyle.acU);
        b.F(parcel, p3);
    }
    
    public WalletFragmentStyle bp(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        Bundle p = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    p = a.p(parcel, n);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new WalletFragmentStyle(g2, p, g);
    }
    
    public WalletFragmentStyle[] cC(final int n) {
        return new WalletFragmentStyle[n];
    }
}
