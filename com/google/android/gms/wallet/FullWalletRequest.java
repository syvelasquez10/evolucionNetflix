// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class FullWalletRequest implements SafeParcelable
{
    public static final Parcelable$Creator<FullWalletRequest> CREATOR;
    private final int BR;
    Cart asA;
    String asq;
    String asr;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    FullWalletRequest() {
        this.BR = 1;
    }
    
    FullWalletRequest(final int br, final String asq, final String asr, final Cart asA) {
        this.BR = br;
        this.asq = asq;
        this.asr = asr;
        this.asA = asA;
    }
    
    public static Builder newBuilder() {
        final FullWalletRequest fullWalletRequest = new FullWalletRequest();
        fullWalletRequest.getClass();
        return new Builder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Cart getCart() {
        return this.asA;
    }
    
    public String getGoogleTransactionId() {
        return this.asq;
    }
    
    public String getMerchantTransactionId() {
        return this.asr;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public FullWalletRequest build() {
            return FullWalletRequest.this;
        }
        
        public Builder setCart(final Cart asA) {
            FullWalletRequest.this.asA = asA;
            return this;
        }
        
        public Builder setGoogleTransactionId(final String asq) {
            FullWalletRequest.this.asq = asq;
            return this;
        }
        
        public Builder setMerchantTransactionId(final String asr) {
            FullWalletRequest.this.asr = asr;
            return this;
        }
    }
}
