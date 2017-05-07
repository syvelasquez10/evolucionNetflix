// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MaskedWalletRequest implements SafeParcelable
{
    public static final Parcelable$Creator<MaskedWalletRequest> CREATOR;
    boolean GK;
    boolean GL;
    boolean GM;
    String GN;
    String GO;
    boolean GP;
    boolean GQ;
    CountrySpecification[] GR;
    boolean GS;
    boolean GT;
    String Gk;
    String Go;
    Cart Gu;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    MaskedWalletRequest() {
        this.kg = 3;
        this.GS = true;
        this.GT = true;
    }
    
    MaskedWalletRequest(final int kg, final String go, final boolean gk, final boolean gl, final boolean gm, final String gn, final String gk2, final String go2, final Cart gu, final boolean gp, final boolean gq, final CountrySpecification[] gr, final boolean gs, final boolean gt) {
        this.kg = kg;
        this.Go = go;
        this.GK = gk;
        this.GL = gl;
        this.GM = gm;
        this.GN = gn;
        this.Gk = gk2;
        this.GO = go2;
        this.Gu = gu;
        this.GP = gp;
        this.GQ = gq;
        this.GR = gr;
        this.GS = gs;
        this.GT = gt;
    }
    
    public static Builder newBuilder() {
        final MaskedWalletRequest maskedWalletRequest = new MaskedWalletRequest();
        maskedWalletRequest.getClass();
        return new Builder();
    }
    
    public boolean allowDebitCard() {
        return this.GT;
    }
    
    public boolean allowPrepaidCard() {
        return this.GS;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.GR;
    }
    
    public Cart getCart() {
        return this.Gu;
    }
    
    public String getCurrencyCode() {
        return this.Gk;
    }
    
    public String getEstimatedTotalPrice() {
        return this.GN;
    }
    
    public String getMerchantName() {
        return this.GO;
    }
    
    public String getMerchantTransactionId() {
        return this.Go;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public boolean isBillingAgreement() {
        return this.GQ;
    }
    
    public boolean isPhoneNumberRequired() {
        return this.GK;
    }
    
    public boolean isShippingAddressRequired() {
        return this.GL;
    }
    
    public boolean shouldRetrieveWalletObjects() {
        return this.GP;
    }
    
    public boolean useMinimalBillingAddress() {
        return this.GM;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public MaskedWalletRequest build() {
            return MaskedWalletRequest.this;
        }
        
        public Builder setAllowDebitCard(final boolean gt) {
            MaskedWalletRequest.this.GT = gt;
            return this;
        }
        
        public Builder setAllowPrepaidCard(final boolean gs) {
            MaskedWalletRequest.this.GS = gs;
            return this;
        }
        
        public Builder setAllowedShippingCountrySpecifications(final CountrySpecification[] gr) {
            MaskedWalletRequest.this.GR = gr;
            return this;
        }
        
        public Builder setCart(final Cart gu) {
            MaskedWalletRequest.this.Gu = gu;
            return this;
        }
        
        public Builder setCurrencyCode(final String gk) {
            MaskedWalletRequest.this.Gk = gk;
            return this;
        }
        
        public Builder setEstimatedTotalPrice(final String gn) {
            MaskedWalletRequest.this.GN = gn;
            return this;
        }
        
        public Builder setIsBillingAgreement(final boolean gq) {
            MaskedWalletRequest.this.GQ = gq;
            return this;
        }
        
        public Builder setMerchantName(final String go) {
            MaskedWalletRequest.this.GO = go;
            return this;
        }
        
        public Builder setMerchantTransactionId(final String go) {
            MaskedWalletRequest.this.Go = go;
            return this;
        }
        
        public Builder setPhoneNumberRequired(final boolean gk) {
            MaskedWalletRequest.this.GK = gk;
            return this;
        }
        
        public Builder setShippingAddressRequired(final boolean gl) {
            MaskedWalletRequest.this.GL = gl;
            return this;
        }
        
        public Builder setShouldRetrieveWalletObjects(final boolean gp) {
            MaskedWalletRequest.this.GP = gp;
            return this;
        }
        
        public Builder setUseMinimalBillingAddress(final boolean gm) {
            MaskedWalletRequest.this.GM = gm;
            return this;
        }
    }
}
