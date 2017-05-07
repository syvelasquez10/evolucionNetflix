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
    String GA;
    String GX;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new k();
    }
    
    OfferWalletObject() {
        this.kg = 2;
    }
    
    OfferWalletObject(final int kg, final String ga, final String gx) {
        this.kg = kg;
        this.GA = ga;
        this.GX = gx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return this.GA;
    }
    
    public String getRedemptionCode() {
        return this.GX;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        k.a(this, parcel, n);
    }
}
