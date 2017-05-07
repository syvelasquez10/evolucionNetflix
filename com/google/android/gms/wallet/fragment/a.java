// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<WalletFragmentInitParams>
{
    static void a(final WalletFragmentInitParams walletFragmentInitParams, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, walletFragmentInitParams.BR);
        b.a(parcel, 2, walletFragmentInitParams.getAccountName(), false);
        b.a(parcel, 3, (Parcelable)walletFragmentInitParams.getMaskedWalletRequest(), n, false);
        b.c(parcel, 4, walletFragmentInitParams.getMaskedWalletRequestCode());
        b.a(parcel, 5, (Parcelable)walletFragmentInitParams.getMaskedWallet(), n, false);
        b.H(parcel, d);
    }
    
    public WalletFragmentInitParams dD(final Parcel parcel) {
        MaskedWallet maskedWallet = null;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        int g2 = -1;
        MaskedWalletRequest maskedWalletRequest = null;
        String o = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 3: {
                    maskedWalletRequest = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, MaskedWalletRequest.CREATOR);
                    continue;
                }
                case 4: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 5: {
                    maskedWallet = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, MaskedWallet.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new WalletFragmentInitParams(g, o, maskedWalletRequest, g2, maskedWallet);
    }
    
    public WalletFragmentInitParams[] fE(final int n) {
        return new WalletFragmentInitParams[n];
    }
}
