// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MaskedWalletRequest implements SafeParcelable
{
    public static final Parcelable$Creator<MaskedWalletRequest> CREATOR;
    private final int BR;
    Cart asA;
    String asl;
    String asr;
    boolean ate;
    boolean atf;
    boolean atg;
    String ath;
    String ati;
    boolean atj;
    boolean atk;
    CountrySpecification[] atl;
    boolean atm;
    boolean atn;
    ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> ato;
    
    static {
        CREATOR = (Parcelable$Creator)new l();
    }
    
    MaskedWalletRequest() {
        this.BR = 3;
        this.atm = true;
        this.atn = true;
    }
    
    MaskedWalletRequest(final int br, final String asr, final boolean ate, final boolean atf, final boolean atg, final String ath, final String asl, final String ati, final Cart asA, final boolean atj, final boolean atk, final CountrySpecification[] atl, final boolean atm, final boolean atn, final ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> ato) {
        this.BR = br;
        this.asr = asr;
        this.ate = ate;
        this.atf = atf;
        this.atg = atg;
        this.ath = ath;
        this.asl = asl;
        this.ati = ati;
        this.asA = asA;
        this.atj = atj;
        this.atk = atk;
        this.atl = atl;
        this.atm = atm;
        this.atn = atn;
        this.ato = ato;
    }
    
    public static MaskedWalletRequest$Builder newBuilder() {
        final MaskedWalletRequest maskedWalletRequest = new MaskedWalletRequest();
        maskedWalletRequest.getClass();
        return new MaskedWalletRequest$Builder(maskedWalletRequest, null);
    }
    
    public boolean allowDebitCard() {
        return this.atn;
    }
    
    public boolean allowPrepaidCard() {
        return this.atm;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> getAllowedCountrySpecificationsForShipping() {
        return this.ato;
    }
    
    public CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.atl;
    }
    
    public Cart getCart() {
        return this.asA;
    }
    
    public String getCurrencyCode() {
        return this.asl;
    }
    
    public String getEstimatedTotalPrice() {
        return this.ath;
    }
    
    public String getMerchantName() {
        return this.ati;
    }
    
    public String getMerchantTransactionId() {
        return this.asr;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public boolean isBillingAgreement() {
        return this.atk;
    }
    
    public boolean isPhoneNumberRequired() {
        return this.ate;
    }
    
    public boolean isShippingAddressRequired() {
        return this.atf;
    }
    
    public boolean shouldRetrieveWalletObjects() {
        return this.atj;
    }
    
    public boolean useMinimalBillingAddress() {
        return this.atg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        l.a(this, parcel, n);
    }
}
