// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@Deprecated
public final class Address implements SafeParcelable
{
    public static final Parcelable$Creator<Address> CREATOR;
    private final int BR;
    String adC;
    String adD;
    String adE;
    String adJ;
    String adL;
    boolean adM;
    String adN;
    String asi;
    String asj;
    String name;
    String uW;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Address() {
        this.BR = 1;
    }
    
    Address(final int br, final String name, final String adC, final String adD, final String adE, final String uw, final String asi, final String asj, final String adJ, final String adL, final boolean adM, final String adN) {
        this.BR = br;
        this.name = name;
        this.adC = adC;
        this.adD = adD;
        this.adE = adE;
        this.uW = uw;
        this.asi = asi;
        this.asj = asj;
        this.adJ = adJ;
        this.adL = adL;
        this.adM = adM;
        this.adN = adN;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAddress1() {
        return this.adC;
    }
    
    public String getAddress2() {
        return this.adD;
    }
    
    public String getAddress3() {
        return this.adE;
    }
    
    public String getCity() {
        return this.asi;
    }
    
    public String getCompanyName() {
        return this.adN;
    }
    
    public String getCountryCode() {
        return this.uW;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPhoneNumber() {
        return this.adL;
    }
    
    public String getPostalCode() {
        return this.adJ;
    }
    
    public String getState() {
        return this.asj;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public boolean isPostBox() {
        return this.adM;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
