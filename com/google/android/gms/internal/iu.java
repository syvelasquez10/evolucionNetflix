// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.PendingIntent$CanceledException;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import com.google.android.gms.common.ConnectionResult;
import android.app.PendingIntent;
import android.os.IInterface;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import android.util.Log;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Parcelable;
import android.accounts.Account;
import android.text.TextUtils;
import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.app.Activity;

public class iu extends dw<is>
{
    private final int Hi;
    private final Activity gs;
    private final String jG;
    private final int mTheme;
    
    public iu(final Activity activity, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final int n, final String s, final int n2) {
        this(activity, new c(connectionCallbacks), new g(onConnectionFailedListener), n, s, n2);
    }
    
    public iu(final Activity gs, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final int hi, final String jg, final int mTheme) {
        super((Context)gs, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.gs = gs;
        this.Hi = hi;
        this.jG = jg;
        this.mTheme = mTheme;
    }
    
    private Bundle fT() {
        final Bundle bundle = new Bundle();
        bundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", this.Hi);
        bundle.putString("androidPackageName", this.gs.getPackageName());
        if (!TextUtils.isEmpty((CharSequence)this.jG)) {
            bundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", (Parcelable)new Account(this.jG, "com.google"));
        }
        bundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", this.mTheme);
        return bundle;
    }
    
    @Override
    protected void a(final ec ec, final e e) throws RemoteException {
        ec.a(e, 4242000);
    }
    
    protected is aB(final IBinder binder) {
        return is.a.az(binder);
    }
    
    @Override
    protected String am() {
        return "com.google.android.gms.wallet.service.BIND";
    }
    
    @Override
    protected String an() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }
    
    public void changeMaskedWallet(final String s, final String s2, final int n) {
        final Bundle ft = this.fT();
        final a a = new a(n);
        try {
            this.bQ().a(s, s2, ft, a);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException changing masked wallet", (Throwable)ex);
            a.a(8, (MaskedWallet)null, null);
        }
    }
    
    public void checkForPreAuthorization(final int n) {
        final Bundle ft = this.fT();
        final a a = new a(n);
        try {
            this.bQ().a(ft, a);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", (Throwable)ex);
            a.a(8, false, null);
        }
    }
    
    public void loadFullWallet(final FullWalletRequest fullWalletRequest, final int n) {
        final a a = new a(n);
        final Bundle ft = this.fT();
        try {
            this.bQ().a(fullWalletRequest, ft, a);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException getting full wallet", (Throwable)ex);
            a.a(8, (FullWallet)null, null);
        }
    }
    
    public void loadMaskedWallet(final MaskedWalletRequest maskedWalletRequest, final int n) {
        final Bundle ft = this.fT();
        final a a = new a(n);
        try {
            this.bQ().a(maskedWalletRequest, ft, a);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException getting masked wallet", (Throwable)ex);
            a.a(8, (MaskedWallet)null, null);
        }
    }
    
    public void notifyTransactionStatus(final NotifyTransactionStatusRequest notifyTransactionStatusRequest) {
        final Bundle ft = this.fT();
        try {
            this.bQ().a(notifyTransactionStatusRequest, ft);
        }
        catch (RemoteException ex) {}
    }
    
    final class a extends it.a
    {
        private final int oZ;
        
        public a(final int oz) {
            this.oZ = oz;
        }
        
        public void a(final int n, final FullWallet fullWallet, final Bundle bundle) {
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
            }
            final ConnectionResult connectionResult = new ConnectionResult(n, pendingIntent);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(iu.this.gs, this.oZ);
                    return;
                }
                catch (IntentSender$SendIntentException ex) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", (Throwable)ex);
                    return;
                }
            }
            int n2;
            Intent intent2;
            if (connectionResult.isSuccess()) {
                n2 = -1;
                final Intent intent = new Intent();
                intent.putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", (Parcelable)fullWallet);
                intent2 = intent;
            }
            else {
                if (n == 408) {
                    n2 = 0;
                }
                else {
                    n2 = 1;
                }
                intent2 = new Intent();
                intent2.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", n);
            }
            final PendingIntent pendingResult = iu.this.gs.createPendingResult(this.oZ, intent2, 1073741824);
            if (pendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
                return;
            }
            try {
                pendingResult.send(n2);
            }
            catch (PendingIntent$CanceledException ex2) {
                Log.w("WalletClientImpl", "Exception setting pending result", (Throwable)ex2);
            }
        }
        
        public void a(final int n, final MaskedWallet maskedWallet, final Bundle bundle) {
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
            }
            final ConnectionResult connectionResult = new ConnectionResult(n, pendingIntent);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(iu.this.gs, this.oZ);
                    return;
                }
                catch (IntentSender$SendIntentException ex) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", (Throwable)ex);
                    return;
                }
            }
            int n2;
            Intent intent2;
            if (connectionResult.isSuccess()) {
                n2 = -1;
                final Intent intent = new Intent();
                intent.putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", (Parcelable)maskedWallet);
                intent2 = intent;
            }
            else {
                if (n == 408) {
                    n2 = 0;
                }
                else {
                    n2 = 1;
                }
                intent2 = new Intent();
                intent2.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", n);
            }
            final PendingIntent pendingResult = iu.this.gs.createPendingResult(this.oZ, intent2, 1073741824);
            if (pendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
                return;
            }
            try {
                pendingResult.send(n2);
            }
            catch (PendingIntent$CanceledException ex2) {
                Log.w("WalletClientImpl", "Exception setting pending result", (Throwable)ex2);
            }
        }
        
        public void a(final int n, final boolean b, final Bundle bundle) {
            final Intent intent = new Intent();
            intent.putExtra("com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED", b);
            final PendingIntent pendingResult = iu.this.gs.createPendingResult(this.oZ, intent, 1073741824);
            if (pendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
                return;
            }
            try {
                pendingResult.send(-1);
            }
            catch (PendingIntent$CanceledException ex) {
                Log.w("WalletClientImpl", "Exception setting pending result", (Throwable)ex);
            }
        }
    }
}
