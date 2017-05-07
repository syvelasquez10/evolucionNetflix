// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.identity.intents.model.CountrySpecification;

public final class UserAddressRequest$Builder
{
    final /* synthetic */ UserAddressRequest adA;
    
    private UserAddressRequest$Builder(final UserAddressRequest adA) {
        this.adA = adA;
    }
    
    public UserAddressRequest$Builder addAllowedCountrySpecification(final CountrySpecification countrySpecification) {
        if (this.adA.adz == null) {
            this.adA.adz = new ArrayList<CountrySpecification>();
        }
        this.adA.adz.add(countrySpecification);
        return this;
    }
    
    public UserAddressRequest$Builder addAllowedCountrySpecifications(final Collection<CountrySpecification> collection) {
        if (this.adA.adz == null) {
            this.adA.adz = new ArrayList<CountrySpecification>();
        }
        this.adA.adz.addAll(collection);
        return this;
    }
    
    public UserAddressRequest build() {
        if (this.adA.adz != null) {
            this.adA.adz = Collections.unmodifiableList((List<? extends CountrySpecification>)this.adA.adz);
        }
        return this.adA;
    }
}
