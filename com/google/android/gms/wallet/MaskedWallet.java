// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.identity.intents.model.UserAddress;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MaskedWallet implements SafeParcelable
{
    public static final Parcelable$Creator<MaskedWallet> CREATOR;
    LoyaltyWalletObject[] abT;
    OfferWalletObject[] abU;
    String abh;
    String abi;
    String abk;
    Address abl;
    Address abm;
    String[] abn;
    UserAddress abo;
    UserAddress abp;
    InstrumentInfo[] abq;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new k();
    }
    
    private MaskedWallet() {
        this.xH = 2;
    }
    
    MaskedWallet(final int xh, final String abh, final String abi, final String[] abn, final String abk, final Address abl, final Address abm, final LoyaltyWalletObject[] abT, final OfferWalletObject[] abU, final UserAddress abo, final UserAddress abp, final InstrumentInfo[] abq) {
        this.xH = xh;
        this.abh = abh;
        this.abi = abi;
        this.abn = abn;
        this.abk = abk;
        this.abl = abl;
        this.abm = abm;
        this.abT = abT;
        this.abU = abU;
        this.abo = abo;
        this.abp = abp;
        this.abq = abq;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Deprecated
    public Address getBillingAddress() {
        return this.abl;
    }
    
    public UserAddress getBuyerBillingAddress() {
        return this.abo;
    }
    
    public UserAddress getBuyerShippingAddress() {
        return this.abp;
    }
    
    public String getEmail() {
        return this.abk;
    }
    
    public String getGoogleTransactionId() {
        return this.abh;
    }
    
    public InstrumentInfo[] getInstrumentInfos() {
        return this.abq;
    }
    
    public LoyaltyWalletObject[] getLoyaltyWalletObjects() {
        return this.abT;
    }
    
    public String getMerchantTransactionId() {
        return this.abi;
    }
    
    public OfferWalletObject[] getOfferWalletObjects() {
        return this.abU;
    }
    
    public String[] getPaymentDescriptions() {
        return this.abn;
    }
    
    @Deprecated
    public Address getShippingAddress() {
        return this.abm;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        k.a(this, parcel, n);
    }
}
