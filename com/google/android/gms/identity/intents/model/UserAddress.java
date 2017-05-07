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
    String NB;
    String NC;
    String ND;
    String NE;
    String NF;
    String NG;
    String NH;
    String NI;
    String NJ;
    String NK;
    boolean NL;
    String NM;
    String NN;
    String name;
    String qd;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    UserAddress() {
        this.xH = 1;
    }
    
    UserAddress(final int xh, final String name, final String nb, final String nc, final String nd, final String ne, final String nf, final String ng, final String nh, final String qd, final String ni, final String nj, final String nk, final boolean nl, final String nm, final String nn) {
        this.xH = xh;
        this.name = name;
        this.NB = nb;
        this.NC = nc;
        this.ND = nd;
        this.NE = ne;
        this.NF = nf;
        this.NG = ng;
        this.NH = nh;
        this.qd = qd;
        this.NI = ni;
        this.NJ = nj;
        this.NK = nk;
        this.NL = nl;
        this.NM = nm;
        this.NN = nn;
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
        return this.NB;
    }
    
    public String getAddress2() {
        return this.NC;
    }
    
    public String getAddress3() {
        return this.ND;
    }
    
    public String getAddress4() {
        return this.NE;
    }
    
    public String getAddress5() {
        return this.NF;
    }
    
    public String getAdministrativeArea() {
        return this.NG;
    }
    
    public String getCompanyName() {
        return this.NM;
    }
    
    public String getCountryCode() {
        return this.qd;
    }
    
    public String getEmailAddress() {
        return this.NN;
    }
    
    public String getLocality() {
        return this.NH;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPhoneNumber() {
        return this.NK;
    }
    
    public String getPostalCode() {
        return this.NI;
    }
    
    public String getSortingCode() {
        return this.NJ;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public boolean isPostBox() {
        return this.NL;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
