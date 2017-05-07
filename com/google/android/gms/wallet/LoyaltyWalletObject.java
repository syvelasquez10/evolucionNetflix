// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LoyaltyWalletObject implements SafeParcelable
{
    public static final Parcelable$Creator<LoyaltyWalletObject> CREATOR;
    String GA;
    String GB;
    String GC;
    String GD;
    String GE;
    String GF;
    String GG;
    String GH;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    LoyaltyWalletObject() {
        this.kg = 3;
    }
    
    LoyaltyWalletObject(final int kg, final String ga, final String gb, final String gc, final String gd, final String ge, final String gf, final String gg, final String gh) {
        this.kg = kg;
        this.GA = ga;
        this.GB = gb;
        this.GC = gc;
        this.GD = gd;
        this.GE = ge;
        this.GF = gf;
        this.GG = gg;
        this.GH = gh;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountId() {
        return this.GB;
    }
    
    public String getAccountName() {
        return this.GE;
    }
    
    public String getBarcodeAlternateText() {
        return this.GF;
    }
    
    public String getBarcodeType() {
        return this.GG;
    }
    
    public String getBarcodeValue() {
        return this.GH;
    }
    
    public String getId() {
        return this.GA;
    }
    
    public String getIssuerName() {
        return this.GC;
    }
    
    public String getProgramName() {
        return this.GD;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
}
