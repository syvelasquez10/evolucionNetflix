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
    String Gn;
    String Go;
    Cart Gu;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    FullWalletRequest() {
        this.kg = 1;
    }
    
    FullWalletRequest(final int kg, final String gn, final String go, final Cart gu) {
        this.kg = kg;
        this.Gn = gn;
        this.Go = go;
        this.Gu = gu;
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
        return this.Gu;
    }
    
    public String getGoogleTransactionId() {
        return this.Gn;
    }
    
    public String getMerchantTransactionId() {
        return this.Go;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public FullWalletRequest build() {
            return FullWalletRequest.this;
        }
        
        public Builder setCart(final Cart gu) {
            FullWalletRequest.this.Gu = gu;
            return this;
        }
        
        public Builder setGoogleTransactionId(final String gn) {
            FullWalletRequest.this.Gn = gn;
            return this;
        }
        
        public Builder setMerchantTransactionId(final String go) {
            FullWalletRequest.this.Go = go;
            return this;
        }
    }
}
