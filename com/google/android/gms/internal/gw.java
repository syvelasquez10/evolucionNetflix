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
import android.os.RemoteException;
import android.util.Log;
import android.os.Parcelable;
import android.accounts.Account;
import android.text.TextUtils;
import android.os.Bundle;
import com.google.android.gms.identity.intents.UserAddressRequest;
import android.os.IBinder;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.app.Activity;

public class gw extends ff<gy>
{
    private a NA;
    private final int mTheme;
    private Activity nS;
    private final String wG;
    
    public gw(final Activity ns, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String wg, final int mTheme) {
        super((Context)ns, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.wG = wg;
        this.nS = ns;
        this.mTheme = mTheme;
    }
    
    protected gy R(final IBinder binder) {
        return gy.a.T(binder);
    }
    
    public void a(final UserAddressRequest userAddressRequest, final int n) {
        this.hO();
        this.NA = new a(n, this.nS);
        try {
            final Bundle bundle = new Bundle();
            bundle.putString("com.google.android.gms.identity.intents.EXTRA_CALLING_PACKAGE_NAME", this.getContext().getPackageName());
            if (!TextUtils.isEmpty((CharSequence)this.wG)) {
                bundle.putParcelable("com.google.android.gms.identity.intents.EXTRA_ACCOUNT", (Parcelable)new Account(this.wG, "com.google"));
            }
            bundle.putInt("com.google.android.gms.identity.intents.EXTRA_THEME", this.mTheme);
            this.hN().a(this.NA, userAddressRequest, bundle);
        }
        catch (RemoteException ex) {
            Log.e("AddressClientImpl", "Exception requesting user address", (Throwable)ex);
            final Bundle bundle2 = new Bundle();
            bundle2.putInt("com.google.android.gms.identity.intents.EXTRA_ERROR_CODE", 555);
            this.NA.d(1, bundle2);
        }
    }
    
    @Override
    protected void a(final fm fm, final e e) throws RemoteException {
        fm.d(e, 4452000, this.getContext().getPackageName());
    }
    
    @Override
    protected String bg() {
        return "com.google.android.gms.identity.service.BIND";
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.identity.intents.internal.IAddressService";
    }
    
    @Override
    public void disconnect() {
        super.disconnect();
        if (this.NA != null) {
            this.NA.setActivity(null);
            this.NA = null;
        }
    }
    
    protected gy hN() {
        return super.eM();
    }
    
    protected void hO() {
        super.bT();
    }
    
    public static final class a extends gx.a
    {
        private final int CV;
        private Activity nS;
        
        public a(final int cv, final Activity ns) {
            this.CV = cv;
            this.nS = ns;
        }
        
        private void setActivity(final Activity ns) {
            this.nS = ns;
        }
        
        public void d(final int n, final Bundle bundle) {
            Label_0056: {
                if (n != 1) {
                    break Label_0056;
                }
                final Intent intent = new Intent();
                intent.putExtras(bundle);
                final PendingIntent pendingResult = this.nS.createPendingResult(this.CV, intent, 1073741824);
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
                    connectionResult.startResolutionForResult(this.nS, this.CV);
                    return;
                }
                catch (IntentSender$SendIntentException ex2) {
                    Log.w("AddressClientImpl", "Exception starting pending intent", (Throwable)ex2);
                    return;
                }
            }
            try {
                final PendingIntent pendingResult2 = this.nS.createPendingResult(this.CV, new Intent(), 1073741824);
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
