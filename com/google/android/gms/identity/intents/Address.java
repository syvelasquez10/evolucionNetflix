// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.fq;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.fc;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.gw;
import com.google.android.gms.common.api.Api;

public final class Address
{
    public static final Api<AddressOptions> API;
    static final Api.c<gw> wx;
    private static final Api.b<gw, AddressOptions> wy;
    
    static {
        wx = new Api.c();
        wy = new Api.b<gw, AddressOptions>() {
            public gw a(final Context context, final Looper looper, final fc fc, final AddressOptions addressOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                fq.b(context instanceof Activity, "An Activity must be used for Address APIs");
                AddressOptions addressOptions2 = addressOptions;
                if (addressOptions == null) {
                    addressOptions2 = new AddressOptions();
                }
                return new gw((Activity)context, looper, connectionCallbacks, onConnectionFailedListener, fc.getAccountName(), addressOptions2.theme);
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api<AddressOptions>((Api.b<C, AddressOptions>)Address.wy, (Api.c<C>)Address.wx, new Scope[0]);
    }
    
    public static void requestUserAddress(final GoogleApiClient googleApiClient, final UserAddressRequest userAddressRequest, final int n) {
        googleApiClient.a(new a() {
            protected void a(final gw gw) throws RemoteException {
                gw.a(userAddressRequest, n);
                ((com.google.android.gms.common.api.a.a<R>)this).a((R)Status.Bv);
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
    
    private abstract static class a extends b<Status, gw>
    {
        public a() {
            super(Address.wx);
        }
        
        public Status f(final Status status) {
            return status;
        }
    }
}
