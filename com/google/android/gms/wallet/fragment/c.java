// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<WalletFragmentStyle>
{
    static void a(final WalletFragmentStyle walletFragmentStyle, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, walletFragmentStyle.BR);
        b.a(parcel, 2, walletFragmentStyle.aud, false);
        b.c(parcel, 3, walletFragmentStyle.aue);
        b.H(parcel, d);
    }
    
    public WalletFragmentStyle dF(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        Bundle q = null;
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
                    q = a.q(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new WalletFragmentStyle(g2, q, g);
    }
    
    public WalletFragmentStyle[] fG(final int n) {
        return new WalletFragmentStyle[n];
    }
}
