// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<WalletFragmentInitParams>
{
    static void a(final WalletFragmentInitParams walletFragmentInitParams, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, walletFragmentInitParams.xH);
        b.a(parcel, 2, walletFragmentInitParams.getAccountName(), false);
        b.a(parcel, 3, (Parcelable)walletFragmentInitParams.getMaskedWalletRequest(), n, false);
        b.c(parcel, 4, walletFragmentInitParams.getMaskedWalletRequestCode());
        b.a(parcel, 5, (Parcelable)walletFragmentInitParams.getMaskedWallet(), n, false);
        b.F(parcel, p3);
    }
    
    public WalletFragmentInitParams bn(final Parcel parcel) {
        MaskedWallet maskedWallet = null;
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int g = 0;
        int g2 = -1;
        MaskedWalletRequest maskedWalletRequest = null;
        String n = null;
        while (parcel.dataPosition() < o) {
            final int n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n2)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    maskedWalletRequest = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n2, MaskedWalletRequest.CREATOR);
                    continue;
                }
                case 4: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n2);
                    continue;
                }
                case 5: {
                    maskedWallet = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n2, MaskedWallet.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new WalletFragmentInitParams(g, n, maskedWalletRequest, g2, maskedWallet);
    }
    
    public WalletFragmentInitParams[] cA(final int n) {
        return new WalletFragmentInitParams[n];
    }
}
