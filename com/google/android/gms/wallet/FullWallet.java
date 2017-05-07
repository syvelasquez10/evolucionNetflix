// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class FullWallet implements SafeParcelable
{
    public static final Parcelable$Creator<FullWallet> CREATOR;
    String Gn;
    String Go;
    ProxyCard Gp;
    String Gq;
    Address Gr;
    Address Gs;
    String[] Gt;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    FullWallet() {
        this.kg = 1;
    }
    
    FullWallet(final int kg, final String gn, final String go, final ProxyCard gp, final String gq, final Address gr, final Address gs, final String[] gt) {
        this.kg = kg;
        this.Gn = gn;
        this.Go = go;
        this.Gp = gp;
        this.Gq = gq;
        this.Gr = gr;
        this.Gs = gs;
        this.Gt = gt;
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
    
    public String getMerchantTransactionId() {
        return this.Go;
    }
    
    public String[] getPaymentDescriptions() {
        return this.Gt;
    }
    
    public ProxyCard getProxyCard() {
        return this.Gp;
    }
    
    public Address getShippingAddress() {
        return this.Gs;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
