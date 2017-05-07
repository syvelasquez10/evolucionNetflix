// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<WalletFragmentOptions>
{
    static void a(final WalletFragmentOptions walletFragmentOptions, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, walletFragmentOptions.BR);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, walletFragmentOptions.getEnvironment());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, walletFragmentOptions.getTheme());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable)walletFragmentOptions.getFragmentStyle(), n, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, walletFragmentOptions.getMode());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public WalletFragmentOptions dE(final Parcel parcel) {
        int g = 1;
        int g2 = 0;
        final int c = a.C(parcel);
        WalletFragmentStyle walletFragmentStyle = null;
        int g3 = 1;
        int g4 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    walletFragmentStyle = a.a(parcel, b, WalletFragmentStyle.CREATOR);
                    continue;
                }
                case 5: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new WalletFragmentOptions(g4, g3, g2, walletFragmentStyle, g);
    }
    
    public WalletFragmentOptions[] fF(final int n) {
        return new WalletFragmentOptions[n];
    }
}
