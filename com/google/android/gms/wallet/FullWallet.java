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
    String abh;
    String abi;
    ProxyCard abj;
    String abk;
    Address abl;
    Address abm;
    String[] abn;
    UserAddress abo;
    UserAddress abp;
    InstrumentInfo[] abq;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    private FullWallet() {
        this.xH = 1;
    }
    
    FullWallet(final int xh, final String abh, final String abi, final ProxyCard abj, final String abk, final Address abl, final Address abm, final String[] abn, final UserAddress abo, final UserAddress abp, final InstrumentInfo[] abq) {
        this.xH = xh;
        this.abh = abh;
        this.abi = abi;
        this.abj = abj;
        this.abk = abk;
        this.abl = abl;
        this.abm = abm;
        this.abn = abn;
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
    
    public String getMerchantTransactionId() {
        return this.abi;
    }
    
    public String[] getPaymentDescriptions() {
        return this.abn;
    }
    
    public ProxyCard getProxyCard() {
        return this.abj;
    }
    
    @Deprecated
    public Address getShippingAddress() {
        return this.abm;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
