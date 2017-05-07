// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class d implements SafeParcelable
{
    public static final Parcelable$Creator<d> CREATOR;
    private final int BR;
    LoyaltyWalletObject aso;
    OfferWalletObject asp;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    d() {
        this.BR = 2;
    }
    
    d(final int br, final LoyaltyWalletObject aso, final OfferWalletObject asp) {
        this.BR = br;
        this.aso = aso;
        this.asp = asp;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
