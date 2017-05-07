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
import android.util.Log;
import android.os.Parcelable;
import android.accounts.Account;
import android.text.TextUtils;
import android.os.Bundle;
import com.google.android.gms.identity.intents.UserAddressRequest;
import android.os.RemoteException;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.k;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.app.Activity;
import com.google.android.gms.common.internal.d;

public class ll extends d<ln>
{
    private final String Dd;
    private a adB;
    private final int mTheme;
    private Activity nr;
    
    public ll(final Activity nr, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String dd, final int mTheme) {
        super((Context)nr, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.Dd = dd;
        this.nr = nr;
        this.mTheme = mTheme;
    }
    
    @Override
    protected void a(final k k, final e e) throws RemoteException {
        k.d(e, 6111000, this.getContext().getPackageName());
    }
    
    public void a(final UserAddressRequest userAddressRequest, final int n) {
        this.lR();
        this.adB = new a(n, this.nr);
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
        return ln.a.aH(binder);
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
    
    public static final class a extends lm.a
    {
        private final int Lm;
        private Activity nr;
        
        public a(final int lm, final Activity nr) {
            this.Lm = lm;
            this.nr = nr;
        }
        
        private void setActivity(final Activity nr) {
            this.nr = nr;
        }
        
        public void g(final int n, final Bundle bundle) {
            Label_0056: {
                if (n != 1) {
                    break Label_0056;
                }
                final Intent intent = new Intent();
                intent.putExtras(bundle);
                final PendingIntent pendingResult = this.nr.createPendingResult(this.Lm, intent, 1073741824);
                if (pendingResult != null) {
                    try {
                        pendingResult.send(1);
                        return;
                    }
                    catch (PendingIntent$CanceledException ex) {
                        Log.w("AddressClientImpl", "Exception settng pending result", (Throwable)ex);
                        return;
                    }
                    break Label_0056;
                }
                return;
            }
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT");
            }
            final ConnectionResult connectionResult = new ConnectionResult(n, pendingIntent);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this.nr, this.Lm);
                    return;
                }
                catch (IntentSender$SendIntentException ex2) {
                    Log.w("AddressClientImpl", "Exception starting pending intent", (Throwable)ex2);
                    return;
                }
            }
            try {
                final PendingIntent pendingResult2 = this.nr.createPendingResult(this.Lm, new Intent(), 1073741824);
                if (pendingResult2 != null) {
                    pendingResult2.send(1);
                }
            }
            catch (PendingIntent$CanceledException ex3) {
                Log.w("AddressClientImpl", "Exception setting pending result", (Throwable)ex3);
            }
        }
    }
}
