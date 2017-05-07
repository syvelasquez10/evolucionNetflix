// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.internal.fq;
import android.os.Parcel;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletFragmentInitParams implements SafeParcelable
{
    public static final Parcelable$Creator<WalletFragmentInitParams> CREATOR;
    private MaskedWalletRequest acB;
    private int acO;
    private MaskedWallet acP;
    private String wG;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    private WalletFragmentInitParams() {
        this.xH = 1;
        this.acO = -1;
    }
    
    WalletFragmentInitParams(final int xh, final String wg, final MaskedWalletRequest acB, final int acO, final MaskedWallet acP) {
        this.xH = xh;
        this.wG = wg;
        this.acB = acB;
        this.acO = acO;
        this.acP = acP;
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
        return this.wG;
    }
    
    public MaskedWallet getMaskedWallet() {
        return this.acP;
    }
    
    public MaskedWalletRequest getMaskedWalletRequest() {
        return this.acB;
    }
    
    public int getMaskedWalletRequestCode() {
        return this.acO;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public WalletFragmentInitParams build() {
            final boolean b = true;
            fq.a((WalletFragmentInitParams.this.acP != null && WalletFragmentInitParams.this.acB == null) || (WalletFragmentInitParams.this.acP == null && WalletFragmentInitParams.this.acB != null), (Object)"Exactly one of MaskedWallet or MaskedWalletRequest is required");
            fq.a(WalletFragmentInitParams.this.acO >= 0 && b, (Object)"masked wallet request code is required and must be non-negative");
            return WalletFragmentInitParams.this;
        }
        
        public Builder setAccountName(final String s) {
            WalletFragmentInitParams.this.wG = s;
            return this;
        }
        
        public Builder setMaskedWallet(final MaskedWallet maskedWallet) {
            WalletFragmentInitParams.this.acP = maskedWallet;
            return this;
        }
        
        public Builder setMaskedWalletRequest(final MaskedWalletRequest maskedWalletRequest) {
            WalletFragmentInitParams.this.acB = maskedWalletRequest;
            return this;
        }
        
        public Builder setMaskedWalletRequestCode(final int n) {
            WalletFragmentInitParams.this.acO = n;
            return this;
        }
    }
}
