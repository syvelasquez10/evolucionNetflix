// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.Collection;
import android.os.Parcel;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MaskedWalletRequest implements SafeParcelable
{
    public static final Parcelable$Creator<MaskedWalletRequest> CREATOR;
    boolean abV;
    boolean abW;
    boolean abX;
    String abY;
    String abZ;
    String abd;
    String abi;
    Cart abr;
    boolean aca;
    boolean acb;
    CountrySpecification[] acc;
    boolean acd;
    boolean ace;
    ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> acf;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new l();
    }
    
    MaskedWalletRequest() {
        this.xH = 3;
        this.acd = true;
        this.ace = true;
    }
    
    MaskedWalletRequest(final int xh, final String abi, final boolean abV, final boolean abW, final boolean abX, final String abY, final String abd, final String abZ, final Cart abr, final boolean aca, final boolean acb, final CountrySpecification[] acc, final boolean acd, final boolean ace, final ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> acf) {
        this.xH = xh;
        this.abi = abi;
        this.abV = abV;
        this.abW = abW;
        this.abX = abX;
        this.abY = abY;
        this.abd = abd;
        this.abZ = abZ;
        this.abr = abr;
        this.aca = aca;
        this.acb = acb;
        this.acc = acc;
        this.acd = acd;
        this.ace = ace;
        this.acf = acf;
    }
    
    public static Builder newBuilder() {
        final MaskedWalletRequest maskedWalletRequest = new MaskedWalletRequest();
        maskedWalletRequest.getClass();
        return new Builder();
    }
    
    public boolean allowDebitCard() {
        return this.ace;
    }
    
    public boolean allowPrepaidCard() {
        return this.acd;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> getAllowedCountrySpecificationsForShipping() {
        return this.acf;
    }
    
    public CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.acc;
    }
    
    public Cart getCart() {
        return this.abr;
    }
    
    public String getCurrencyCode() {
        return this.abd;
    }
    
    public String getEstimatedTotalPrice() {
        return this.abY;
    }
    
    public String getMerchantName() {
        return this.abZ;
    }
    
    public String getMerchantTransactionId() {
        return this.abi;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public boolean isBillingAgreement() {
        return this.acb;
    }
    
    public boolean isPhoneNumberRequired() {
        return this.abV;
    }
    
    public boolean isShippingAddressRequired() {
        return this.abW;
    }
    
    public boolean shouldRetrieveWalletObjects() {
        return this.aca;
    }
    
    public boolean useMinimalBillingAddress() {
        return this.abX;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        l.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public Builder addAllowedCountrySpecificationForShipping(final com.google.android.gms.identity.intents.model.CountrySpecification countrySpecification) {
            if (MaskedWalletRequest.this.acf == null) {
                MaskedWalletRequest.this.acf = new ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification>();
            }
            MaskedWalletRequest.this.acf.add(countrySpecification);
            return this;
        }
        
        public Builder addAllowedCountrySpecificationsForShipping(final Collection<com.google.android.gms.identity.intents.model.CountrySpecification> collection) {
            if (collection != null) {
                if (MaskedWalletRequest.this.acf == null) {
                    MaskedWalletRequest.this.acf = new ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification>();
                }
                MaskedWalletRequest.this.acf.addAll(collection);
            }
            return this;
        }
        
        public MaskedWalletRequest build() {
            return MaskedWalletRequest.this;
        }
        
        public Builder setAllowDebitCard(final boolean ace) {
            MaskedWalletRequest.this.ace = ace;
            return this;
        }
        
        public Builder setAllowPrepaidCard(final boolean acd) {
            MaskedWalletRequest.this.acd = acd;
            return this;
        }
        
        public Builder setCart(final Cart abr) {
            MaskedWalletRequest.this.abr = abr;
            return this;
        }
        
        public Builder setCurrencyCode(final String abd) {
            MaskedWalletRequest.this.abd = abd;
            return this;
        }
        
        public Builder setEstimatedTotalPrice(final String abY) {
            MaskedWalletRequest.this.abY = abY;
            return this;
        }
        
        public Builder setIsBillingAgreement(final boolean acb) {
            MaskedWalletRequest.this.acb = acb;
            return this;
        }
        
        public Builder setMerchantName(final String abZ) {
            MaskedWalletRequest.this.abZ = abZ;
            return this;
        }
        
        public Builder setMerchantTransactionId(final String abi) {
            MaskedWalletRequest.this.abi = abi;
            return this;
        }
        
        public Builder setPhoneNumberRequired(final boolean abV) {
            MaskedWalletRequest.this.abV = abV;
            return this;
        }
        
        public Builder setShippingAddressRequired(final boolean abW) {
            MaskedWalletRequest.this.abW = abW;
            return this;
        }
        
        public Builder setShouldRetrieveWalletObjects(final boolean aca) {
            MaskedWalletRequest.this.aca = aca;
            return this;
        }
        
        public Builder setUseMinimalBillingAddress(final boolean abX) {
            MaskedWalletRequest.this.abX = abX;
            return this;
        }
    }
}
