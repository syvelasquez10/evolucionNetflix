// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.identity.intents.model.CountrySpecification;

public final class MaskedWalletRequest$Builder
{
    final /* synthetic */ MaskedWalletRequest atp;
    
    private MaskedWalletRequest$Builder(final MaskedWalletRequest atp) {
        this.atp = atp;
    }
    
    public MaskedWalletRequest$Builder addAllowedCountrySpecificationForShipping(final CountrySpecification countrySpecification) {
        if (this.atp.ato == null) {
            this.atp.ato = new ArrayList<CountrySpecification>();
        }
        this.atp.ato.add(countrySpecification);
        return this;
    }
    
    public MaskedWalletRequest$Builder addAllowedCountrySpecificationsForShipping(final Collection<CountrySpecification> collection) {
        if (collection != null) {
            if (this.atp.ato == null) {
                this.atp.ato = new ArrayList<CountrySpecification>();
            }
            this.atp.ato.addAll(collection);
        }
        return this;
    }
    
    public MaskedWalletRequest build() {
        return this.atp;
    }
    
    public MaskedWalletRequest$Builder setAllowDebitCard(final boolean atn) {
        this.atp.atn = atn;
        return this;
    }
    
    public MaskedWalletRequest$Builder setAllowPrepaidCard(final boolean atm) {
        this.atp.atm = atm;
        return this;
    }
    
    public MaskedWalletRequest$Builder setCart(final Cart asA) {
        this.atp.asA = asA;
        return this;
    }
    
    public MaskedWalletRequest$Builder setCurrencyCode(final String asl) {
        this.atp.asl = asl;
        return this;
    }
    
    public MaskedWalletRequest$Builder setEstimatedTotalPrice(final String ath) {
        this.atp.ath = ath;
        return this;
    }
    
    public MaskedWalletRequest$Builder setIsBillingAgreement(final boolean atk) {
        this.atp.atk = atk;
        return this;
    }
    
    public MaskedWalletRequest$Builder setMerchantName(final String ati) {
        this.atp.ati = ati;
        return this;
    }
    
    public MaskedWalletRequest$Builder setMerchantTransactionId(final String asr) {
        this.atp.asr = asr;
        return this;
    }
    
    public MaskedWalletRequest$Builder setPhoneNumberRequired(final boolean ate) {
        this.atp.ate = ate;
        return this;
    }
    
    public MaskedWalletRequest$Builder setShippingAddressRequired(final boolean atf) {
        this.atp.atf = atf;
        return this;
    }
    
    public MaskedWalletRequest$Builder setShouldRetrieveWalletObjects(final boolean atj) {
        this.atp.atj = atj;
        return this;
    }
    
    public MaskedWalletRequest$Builder setUseMinimalBillingAddress(final boolean atg) {
        this.atp.atg = atg;
        return this;
    }
}
