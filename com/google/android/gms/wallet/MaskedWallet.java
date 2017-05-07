// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MaskedWallet implements SafeParcelable
{
    public static final Parcelable$Creator<MaskedWallet> CREATOR;
    LoyaltyWalletObject[] GI;
    OfferWalletObject[] GJ;
    String Gn;
    String Go;
    String Gq;
    Address Gr;
    Address Gs;
    String[] Gt;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    MaskedWallet() {
        this.kg = 2;
    }
    
    MaskedWallet(final int kg, final String gn, final String go, final String[] gt, final String gq, final Address gr, final Address gs, final LoyaltyWalletObject[] gi, final OfferWalletObject[] gj) {
        this.kg = kg;
        this.Gn = gn;
        this.Go = go;
        this.Gt = gt;
        this.Gq = gq;
        this.Gr = gr;
        this.Gs = gs;
        this.GI = gi;
        this.GJ = gj;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Address getBillingAddress() {
        return this.Gr;
    }
    
    public String getEmail() {
        return this.Gq;
    }
    
    public String getGoogleTransactionId() {
        return this.Gn;
    }
    
    public LoyaltyWalletObject[] getLoyaltyWalletObjects() {
        return this.GI;
    }
    
    public String getMerchantTransactionId() {
        return this.Go;
    }
    
    public OfferWalletObject[] getOfferWalletObjects() {
        return this.GJ;
    }
    
    public String[] getPaymentDescriptions() {
        return this.Gt;
    }
    
    public Address getShippingAddress() {
        return this.Gs;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
