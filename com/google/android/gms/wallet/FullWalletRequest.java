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
    String abh;
    String abi;
    Cart abr;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    FullWalletRequest() {
        this.xH = 1;
    }
    
    FullWalletRequest(final int xh, final String abh, final String abi, final Cart abr) {
        this.xH = xh;
        this.abh = abh;
        this.abi = abi;
        this.abr = abr;
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
        return this.abr;
    }
    
    public String getGoogleTransactionId() {
        return this.abh;
    }
    
    public String getMerchantTransactionId() {
        return this.abi;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public FullWalletRequest build() {
            return FullWalletRequest.this;
        }
        
        public Builder setCart(final Cart abr) {
            FullWalletRequest.this.abr = abr;
            return this;
        }
        
        public Builder setGoogleTransactionId(final String abh) {
            FullWalletRequest.this.abh = abh;
            return this;
        }
        
        public Builder setMerchantTransactionId(final String abi) {
            FullWalletRequest.this.abi = abi;
            return this;
        }
    }
}
