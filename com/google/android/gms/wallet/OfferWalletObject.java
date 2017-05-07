// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class OfferWalletObject implements SafeParcelable
{
    public static final Parcelable$Creator<OfferWalletObject> CREATOR;
    private final int BR;
    String ats;
    CommonWalletObject att;
    String fl;
    
    static {
        CREATOR = (Parcelable$Creator)new n();
    }
    
    OfferWalletObject() {
        this.BR = 3;
    }
    
    OfferWalletObject(final int br, final String s, final String ats, final CommonWalletObject att) {
        this.BR = br;
        this.ats = ats;
        if (br < 3) {
            this.att = CommonWalletObject.pO().dc(s).pP();
            return;
        }
        this.att = att;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return this.att.getId();
    }
    
    public String getRedemptionCode() {
        return this.ats;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        n.a(this, parcel, n);
    }
}
