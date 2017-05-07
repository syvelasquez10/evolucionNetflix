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
    String NB;
    String NC;
    String ND;
    String NI;
    String NK;
    boolean NL;
    String NM;
    String aba;
    String abb;
    String name;
    String qd;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Address() {
        this.xH = 1;
    }
    
    Address(final int xh, final String name, final String nb, final String nc, final String nd, final String qd, final String aba, final String abb, final String ni, final String nk, final boolean nl, final String nm) {
        this.xH = xh;
        this.name = name;
        this.NB = nb;
        this.NC = nc;
        this.ND = nd;
        this.qd = qd;
        this.aba = aba;
        this.abb = abb;
        this.NI = ni;
        this.NK = nk;
        this.NL = nl;
        this.NM = nm;
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
    
    public String getCity() {
        return this.aba;
    }
    
    public String getCompanyName() {
        return this.NM;
    }
    
    public String getCountryCode() {
        return this.qd;
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
    
    public String getState() {
        return this.abb;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public boolean isPostBox() {
        return this.NL;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
