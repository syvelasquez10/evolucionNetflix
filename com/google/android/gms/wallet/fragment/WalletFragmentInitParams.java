// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletFragmentInitParams implements SafeParcelable
{
    public static final Parcelable$Creator<WalletFragmentInitParams> CREATOR;
    final int BR;
    private String Dd;
    private MaskedWalletRequest atL;
    private MaskedWallet atM;
    private int atZ;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    private WalletFragmentInitParams() {
        this.BR = 1;
        this.atZ = -1;
    }
    
    WalletFragmentInitParams(final int br, final String dd, final MaskedWalletRequest atL, final int atZ, final MaskedWallet atM) {
        this.BR = br;
        this.Dd = dd;
        this.atL = atL;
        this.atZ = atZ;
        this.atM = atM;
    }
    
    public static Builder newBuilder() {
        final WalletFragmentInitParams walletFragmentInitParams = new WalletFragmentInitParams();
        walletFragmentInitParams.getClass();
        return new Builder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountName() {
        return this.Dd;
    }
    
    public MaskedWallet getMaskedWallet() {
        return this.atM;
    }
    
    public MaskedWalletRequest getMaskedWalletRequest() {
        return this.atL;
    }
    
    public int getMaskedWalletRequestCode() {
        return this.atZ;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public WalletFragmentInitParams build() {
            final boolean b = true;
            n.a((WalletFragmentInitParams.this.atM != null && WalletFragmentInitParams.this.atL == null) || (WalletFragmentInitParams.this.atM == null && WalletFragmentInitParams.this.atL != null), (Object)"Exactly one of MaskedWallet or MaskedWalletRequest is required");
            n.a(WalletFragmentInitParams.this.atZ >= 0 && b, (Object)"masked wallet request code is required and must be non-negative");
            return WalletFragmentInitParams.this;
        }
        
        public Builder setAccountName(final String s) {
            WalletFragmentInitParams.this.Dd = s;
            return this;
        }
        
        public Builder setMaskedWallet(final MaskedWallet maskedWallet) {
            WalletFragmentInitParams.this.atM = maskedWallet;
            return this;
        }
        
        public Builder setMaskedWalletRequest(final MaskedWalletRequest maskedWalletRequest) {
            WalletFragmentInitParams.this.atL = maskedWalletRequest;
            return this;
        }
        
        public Builder setMaskedWalletRequestCode(final int n) {
            WalletFragmentInitParams.this.atZ = n;
            return this;
        }
    }
}
