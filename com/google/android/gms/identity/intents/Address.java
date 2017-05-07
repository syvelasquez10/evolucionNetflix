// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.n;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.ll;
import com.google.android.gms.common.api.Api;

public final class Address
{
    public static final Api<AddressOptions> API;
    static final Api.c<ll> CU;
    private static final Api.b<ll, AddressOptions> CV;
    
    static {
        CU = new Api.c();
        CV = new Api.b<ll, AddressOptions>() {
            public ll a(final Context context, final Looper looper, final ClientSettings clientSettings, final AddressOptions addressOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                n.b(context instanceof Activity, (Object)"An Activity must be used for Address APIs");
                AddressOptions addressOptions2 = addressOptions;
                if (addressOptions == null) {
                    addressOptions2 = new AddressOptions();
                }
                return new ll((Activity)context, looper, connectionCallbacks, onConnectionFailedListener, clientSettings.getAccountName(), addressOptions2.theme);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<AddressOptions>((Api.b<C, AddressOptions>)Address.CV, (Api.c<C>)Address.CU, new Scope[0]);
    }
    
    public static void requestUserAddress(final GoogleApiClient googleApiClient, final UserAddressRequest userAddressRequest, final int n) {
        googleApiClient.a(new a() {
            protected void a(final ll ll) throws RemoteException {
                ll.a(userAddressRequest, n);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    public static final class AddressOptions implements HasOptions
    {
        public final int theme;
        
        public AddressOptions() {
            this.theme = 0;
        }
        
        public AddressOptions(final int theme) {
            this.theme = theme;
        }
    }
    
    private abstract static class a extends BaseImplementation.a<Status, ll>
    {
        public a() {
            super(Address.CU);
        }
        
        public Status d(final Status status) {
            return status;
        }
    }
}
