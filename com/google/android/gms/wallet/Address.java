// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Address implements SafeParcelable
{
    public static final Parcelable$Creator<Address> CREATOR;
    String Ga;
    String Gb;
    String Gc;
    String Gd;
    String Ge;
    String Gf;
    String Gg;
    boolean Gh;
    String Gi;
    String id;
    private final int kg;
    String name;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Address() {
        this.kg = 1;
    }
    
    Address(final int kg, final String name, final String ga, final String gb, final String gc, final String id, final String gd, final String ge, final String gf, final String gg, final boolean gh, final String gi) {
        this.kg = kg;
        this.name = name;
        this.Ga = ga;
        this.Gb = gb;
        this.Gc = gc;
        this.id = id;
        this.Gd = gd;
        this.Ge = ge;
        this.Gf = gf;
        this.Gg = gg;
        this.Gh = gh;
        this.Gi = gi;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAddress1() {
        return this.Ga;
    }
    
    public String getAddress2() {
        return this.Gb;
    }
    
    public String getAddress3() {
        return this.Gc;
    }
    
    public String getCity() {
        return this.Gd;
    }
    
    public String getCompanyName() {
        return this.Gi;
    }
    
    public String getCountryCode() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPhoneNumber() {
        return this.Gg;
    }
    
    public String getPostalCode() {
        return this.Gf;
    }
    
    public String getState() {
        return this.Ge;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public boolean isPostBox() {
        return this.Gh;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
