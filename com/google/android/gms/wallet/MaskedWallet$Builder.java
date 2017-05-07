// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.identity.intents.model.UserAddress;

public final class MaskedWallet$Builder
{
    final /* synthetic */ MaskedWallet atd;
    
    private MaskedWallet$Builder(final MaskedWallet atd) {
        this.atd = atd;
    }
    
    public MaskedWallet build() {
        return this.atd;
    }
    
    public MaskedWallet$Builder setBillingAddress(final Address asu) {
        this.atd.asu = asu;
        return this;
    }
    
    public MaskedWallet$Builder setBuyerBillingAddress(final UserAddress asx) {
        this.atd.asx = asx;
        return this;
    }
    
    public MaskedWallet$Builder setBuyerShippingAddress(final UserAddress asy) {
        this.atd.asy = asy;
        return this;
    }
    
    public MaskedWallet$Builder setEmail(final String ast) {
        this.atd.ast = ast;
        return this;
    }
    
    public MaskedWallet$Builder setGoogleTransactionId(final String asq) {
        this.atd.asq = asq;
        return this;
    }
    
    public MaskedWallet$Builder setInstrumentInfos(final InstrumentInfo[] asz) {
        this.atd.asz = asz;
        return this;
    }
    
    public MaskedWallet$Builder setLoyaltyWalletObjects(final LoyaltyWalletObject[] atb) {
        this.atd.atb = atb;
        return this;
    }
    
    public MaskedWallet$Builder setMerchantTransactionId(final String asr) {
        this.atd.asr = asr;
        return this;
    }
    
    public MaskedWallet$Builder setOfferWalletObjects(final OfferWalletObject[] atc) {
        this.atd.atc = atc;
        return this;
    }
    
    public MaskedWallet$Builder setPaymentDescriptions(final String[] asw) {
        this.atd.asw = asw;
        return this;
    }
    
    public MaskedWallet$Builder setShippingAddress(final Address asv) {
        this.atd.asv = asv;
        return this;
    }
}
