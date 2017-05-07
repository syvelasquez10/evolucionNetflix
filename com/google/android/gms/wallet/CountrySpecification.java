// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CountrySpecification implements SafeParcelable
{
    public static final Parcelable$Creator<CountrySpecification> CREATOR;
    String id;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    CountrySpecification(final int kg, final String id) {
        this.kg = kg;
        this.id = id;
    }
    
    public CountrySpecification(final String id) {
        this.kg = 1;
        this.id = id;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCountryCode() {
        return this.id;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
