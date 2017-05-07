// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import android.os.Parcel;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UserAddressRequest implements SafeParcelable
{
    public static final Parcelable$Creator<UserAddressRequest> CREATOR;
    private final int BR;
    List<CountrySpecification> adz;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    UserAddressRequest() {
        this.BR = 1;
    }
    
    UserAddressRequest(final int br, final List<CountrySpecification> adz) {
        this.BR = br;
        this.adz = adz;
    }
    
    public static Builder newBuilder() {
        final UserAddressRequest userAddressRequest = new UserAddressRequest();
        userAddressRequest.getClass();
        return new Builder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public Builder addAllowedCountrySpecification(final CountrySpecification countrySpecification) {
            if (UserAddressRequest.this.adz == null) {
                UserAddressRequest.this.adz = new ArrayList<CountrySpecification>();
            }
            UserAddressRequest.this.adz.add(countrySpecification);
            return this;
        }
        
        public Builder addAllowedCountrySpecifications(final Collection<CountrySpecification> collection) {
            if (UserAddressRequest.this.adz == null) {
                UserAddressRequest.this.adz = new ArrayList<CountrySpecification>();
            }
            UserAddressRequest.this.adz.addAll(collection);
            return this;
        }
        
        public UserAddressRequest build() {
            if (UserAddressRequest.this.adz != null) {
                UserAddressRequest.this.adz = Collections.unmodifiableList((List<? extends CountrySpecification>)UserAddressRequest.this.adz);
            }
            return UserAddressRequest.this;
        }
    }
}
