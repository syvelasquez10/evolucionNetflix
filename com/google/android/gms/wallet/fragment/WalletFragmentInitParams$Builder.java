// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.common.internal.n;

public final class WalletFragmentInitParams$Builder
{
    final /* synthetic */ WalletFragmentInitParams aua;
    
    private WalletFragmentInitParams$Builder(final WalletFragmentInitParams aua) {
        this.aua = aua;
    }
    
    public WalletFragmentInitParams build() {
        final boolean b = true;
        n.a((this.aua.atM != null && this.aua.atL == null) || (this.aua.atM == null && this.aua.atL != null), (Object)"Exactly one of MaskedWallet or MaskedWalletRequest is required");
        n.a(this.aua.atZ >= 0 && b, (Object)"masked wallet request code is required and must be non-negative");
        return this.aua;
    }
    
    public WalletFragmentInitParams$Builder setAccountName(final String s) {
        this.aua.Dd = s;
        return this;
    }
    
    public WalletFragmentInitParams$Builder setMaskedWallet(final MaskedWallet maskedWallet) {
        this.aua.atM = maskedWallet;
        return this;
    }
    
    public WalletFragmentInitParams$Builder setMaskedWalletRequest(final MaskedWalletRequest maskedWalletRequest) {
        this.aua.atL = maskedWalletRequest;
        return this;
    }
    
    public WalletFragmentInitParams$Builder setMaskedWalletRequestCode(final int n) {
        this.aua.atZ = n;
        return this;
    }
}
