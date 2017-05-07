// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.IntentSender$SendIntentException;
import com.google.android.gms.common.ConnectionResult;
import android.app.PendingIntent;
import android.app.PendingIntent$CanceledException;
import android.content.Intent;
import android.os.IInterface;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.os.Parcelable;
import android.accounts.Account;
import android.text.TextUtils;
import android.os.Bundle;
import com.google.android.gms.identity.intents.UserAddressRequest;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.app.Activity;
import com.google.android.gms.common.internal.d;

public class ll extends d<ln>
{
    private final String Dd;
    private ll$a adB;
    private final int mTheme;
    private Activity nr;
    
    public ll(final Activity nr, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String dd, final int mTheme) {
        super((Context)nr, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, new String[0]);
        this.Dd = dd;
        this.nr = nr;
        this.mTheme = mTheme;
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        k.d(d$e, 6111000, this.getContext().getPackageName());
    }
    
    public void a(final UserAddressRequest userAddressRequest, final int n) {
        this.lR();
        this.adB = new ll$a(n, this.nr);
        try {
            final Bundle bundle = new Bundle();
            bundle.putString("com.google.android.gms.identity.intents.EXTRA_CALLING_PACKAGE_NAME", this.getContext().getPackageName());
            if (!TextUtils.isEmpty((CharSequence)this.Dd)) {
                bundle.putParcelable("com.google.android.gms.identity.intents.EXTRA_ACCOUNT", (Parcelable)new Account(this.Dd, "com.google"));
            }
            bundle.putInt("com.google.android.gms.identity.intents.EXTRA_THEME", this.mTheme);
            this.lQ().a(this.adB, userAddressRequest, bundle);
        }
        catch (RemoteException ex) {
            Log.e("AddressClientImpl", "Exception requesting user address", (Throwable)ex);
            final Bundle bundle2 = new Bundle();
            bundle2.putInt("com.google.android.gms.identity.intents.EXTRA_ERROR_CODE", 555);
            this.adB.g(1, bundle2);
        }
    }
    
    protected ln aF(final IBinder binder) {
        return ln$a.aH(binder);
    }
    
    @Override
    public void disconnect() {
        super.disconnect();
        if (this.adB != null) {
            this.adB.setActivity(null);
            this.adB = null;
        }
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.identity.intents.internal.IAddressService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.identity.service.BIND";
    }
    
    protected ln lQ() {
        return super.gS();
    }
    
    protected void lR() {
        super.dK();
    }
}
