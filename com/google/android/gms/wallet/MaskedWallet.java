// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.identity.intents.model.UserAddress;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MaskedWallet implements SafeParcelable
{
    public static final Parcelable$Creator<MaskedWallet> CREATOR;
    private final int BR;
    String asq;
    String asr;
    String ast;
    Address asu;
    Address asv;
    String[] asw;
    UserAddress asx;
    UserAddress asy;
    InstrumentInfo[] asz;
    LoyaltyWalletObject[] atb;
    OfferWalletObject[] atc;
    
    static {
        CREATOR = (Parcelable$Creator)new k();
    }
    
    private MaskedWallet() {
        this.BR = 2;
    }
    
    MaskedWallet(final int br, final String asq, final String asr, final String[] asw, final String ast, final Address asu, final Address asv, final LoyaltyWalletObject[] atb, final OfferWalletObject[] atc, final UserAddress asx, final UserAddress asy, final InstrumentInfo[] asz) {
        this.BR = br;
        this.asq = asq;
        this.asr = asr;
        this.asw = asw;
        this.ast = ast;
        this.asu = asu;
        this.asv = asv;
        this.atb = atb;
        this.atc = atc;
        this.asx = asx;
        this.asy = asy;
        this.asz = asz;
    }
    
    public static MaskedWallet$Builder newBuilderFrom(final MaskedWallet maskedWallet) {
        n.i(maskedWallet);
        return pK().setGoogleTransactionId(maskedWallet.getGoogleTransactionId()).setMerchantTransactionId(maskedWallet.getMerchantTransactionId()).setPaymentDescriptions(maskedWallet.getPaymentDescriptions()).setInstrumentInfos(maskedWallet.getInstrumentInfos()).setEmail(maskedWallet.getEmail()).setLoyaltyWalletObjects(maskedWallet.getLoyaltyWalletObjects()).setOfferWalletObjects(maskedWallet.getOfferWalletObjects()).setBuyerBillingAddress(maskedWallet.getBuyerBillingAddress()).setBuyerShippingAddress(maskedWallet.getBuyerShippingAddress());
    }
    
    public static MaskedWallet$Builder pK() {
        final MaskedWallet maskedWallet = new MaskedWallet();
        maskedWallet.getClass();
        return new MaskedWallet$Builder(maskedWallet, null);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Deprecated
    public Address getBillingAddress() {
        return this.asu;
    }
    
    public UserAddress getBuyerBillingAddress() {
        return this.asx;
    }
    
    public UserAddress getBuyerShippingAddress() {
        return this.asy;
    }
    
    public String getEmail() {
        return this.ast;
    }
    
    public String getGoogleTransactionId() {
        return this.asq;
    }
    
    public InstrumentInfo[] getInstrumentInfos() {
        return this.asz;
    }
    
    public LoyaltyWalletObject[] getLoyaltyWalletObjects() {
        return this.atb;
    }
    
    public String getMerchantTransactionId() {
        return this.asr;
    }
    
    public OfferWalletObject[] getOfferWalletObjects() {
        return this.atc;
    }
    
    public String[] getPaymentDescriptions() {
        return this.asw;
    }
    
    @Deprecated
    public Address getShippingAddress() {
        return this.asv;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        k.a(this, parcel, n);
    }
}
