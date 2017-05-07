// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.identity.intents.model.UserAddress;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class FullWallet implements SafeParcelable
{
    public static final Parcelable$Creator<FullWallet> CREATOR;
    private final int BR;
    String asq;
    String asr;
    ProxyCard ass;
    String ast;
    Address asu;
    Address asv;
    String[] asw;
    UserAddress asx;
    UserAddress asy;
    InstrumentInfo[] asz;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    private FullWallet() {
        this.BR = 1;
    }
    
    FullWallet(final int br, final String asq, final String asr, final ProxyCard ass, final String ast, final Address asu, final Address asv, final String[] asw, final UserAddress asx, final UserAddress asy, final InstrumentInfo[] asz) {
        this.BR = br;
        this.asq = asq;
        this.asr = asr;
        this.ass = ass;
        this.ast = ast;
        this.asu = asu;
        this.asv = asv;
        this.asw = asw;
        this.asx = asx;
        this.asy = asy;
        this.asz = asz;
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
    
    public String getMerchantTransactionId() {
        return this.asr;
    }
    
    public String[] getPaymentDescriptions() {
        return this.asw;
    }
    
    public ProxyCard getProxyCard() {
        return this.ass;
    }
    
    @Deprecated
    public Address getShippingAddress() {
        return this.asv;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
