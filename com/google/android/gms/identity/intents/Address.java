// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.internal.ll;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.common.api.Api;

public final class Address
{
    public static final Api<Address$AddressOptions> API;
    static final Api$c<ll> CU;
    private static final Api$b<ll, Address$AddressOptions> CV;
    
    static {
        CU = new Api$c<ll>();
        CV = new Address$1();
        API = new Api<Address$AddressOptions>((Api$b<C, Address$AddressOptions>)Address.CV, (Api$c<C>)Address.CU, new Scope[0]);
    }
    
    public static void requestUserAddress(final GoogleApiClient googleApiClient, final UserAddressRequest userAddressRequest, final int n) {
        googleApiClient.a(new Address$2(userAddressRequest, n));
    }
}
