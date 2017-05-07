// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class OfferWalletObject implements SafeParcelable
{
    public static final Parcelable$Creator<OfferWalletObject> CREATOR;
    String acj;
    String eC;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new n();
    }
    
    OfferWalletObject() {
        this.xH = 2;
    }
    
    OfferWalletObject(final int xh, final String ec, final String acj) {
        this.xH = xh;
        this.eC = ec;
        this.acj = acj;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return this.eC;
    }
    
    public String getRedemptionCode() {
        return this.acj;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        n.a(this, parcel, n);
    }
}
