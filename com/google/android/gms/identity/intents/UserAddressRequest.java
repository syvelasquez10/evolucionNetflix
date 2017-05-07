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
    List<CountrySpecification> Ny;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    UserAddressRequest() {
        this.xH = 1;
    }
    
    UserAddressRequest(final int xh, final List<CountrySpecification> ny) {
        this.xH = xh;
        this.Ny = ny;
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
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public Builder addAllowedCountrySpecification(final CountrySpecification countrySpecification) {
            if (UserAddressRequest.this.Ny == null) {
                UserAddressRequest.this.Ny = new ArrayList<CountrySpecification>();
            }
            UserAddressRequest.this.Ny.add(countrySpecification);
            return this;
        }
        
        public Builder addAllowedCountrySpecifications(final Collection<CountrySpecification> collection) {
            if (UserAddressRequest.this.Ny == null) {
                UserAddressRequest.this.Ny = new ArrayList<CountrySpecification>();
            }
            UserAddressRequest.this.Ny.addAll(collection);
            return this;
        }
        
        public UserAddressRequest build() {
            if (UserAddressRequest.this.Ny != null) {
                UserAddressRequest.this.Ny = Collections.unmodifiableList((List<? extends CountrySpecification>)UserAddressRequest.this.Ny);
            }
            return UserAddressRequest.this;
        }
    }
}
