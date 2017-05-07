// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.content.Intent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UserAddress implements SafeParcelable
{
    public static final Parcelable$Creator<UserAddress> CREATOR;
    private final int BR;
    String adC;
    String adD;
    String adE;
    String adF;
    String adG;
    String adH;
    String adI;
    String adJ;
    String adK;
    String adL;
    boolean adM;
    String adN;
    String adO;
    String name;
    String uW;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    UserAddress() {
        this.BR = 1;
    }
    
    UserAddress(final int br, final String name, final String adC, final String adD, final String adE, final String adF, final String adG, final String adH, final String adI, final String uw, final String adJ, final String adK, final String adL, final boolean adM, final String adN, final String adO) {
        this.BR = br;
        this.name = name;
        this.adC = adC;
        this.adD = adD;
        this.adE = adE;
        this.adF = adF;
        this.adG = adG;
        this.adH = adH;
        this.adI = adI;
        this.uW = uw;
        this.adJ = adJ;
        this.adK = adK;
        this.adL = adL;
        this.adM = adM;
        this.adN = adN;
        this.adO = adO;
    }
    
    public static UserAddress fromIntent(final Intent intent) {
        if (intent == null || !intent.hasExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS")) {
            return null;
        }
        return (UserAddress)intent.getParcelableExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS");
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
    
    public String getAddress4() {
        return this.adF;
    }
    
    public String getAddress5() {
        return this.adG;
    }
    
    public String getAdministrativeArea() {
        return this.adH;
    }
    
    public String getCompanyName() {
        return this.adN;
    }
    
    public String getCountryCode() {
        return this.uW;
    }
    
    public String getEmailAddress() {
        return this.adO;
    }
    
    public String getLocality() {
        return this.adI;
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
    
    public String getSortingCode() {
        return this.adK;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public boolean isPostBox() {
        return this.adM;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
