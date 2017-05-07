// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CountrySpecification implements SafeParcelable
{
    public static final Parcelable$Creator<CountrySpecification> CREATOR;
    String qd;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    CountrySpecification(final int xh, final String qd) {
        this.xH = xh;
        this.qd = qd;
    }
    
    public CountrySpecification(final String qd) {
        this.xH = 1;
        this.qd = qd;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCountryCode() {
        return this.qd;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
