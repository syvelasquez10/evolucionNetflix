// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.PendingIntent$CanceledException;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import com.google.android.gms.common.ConnectionResult;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.FullWallet;
import android.util.Log;
import com.google.android.gms.wallet.FullWalletRequest;
import android.os.RemoteException;
import android.os.Parcelable;
import android.accounts.Account;
import android.text.TextUtils;
import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.app.Activity;

public class jg extends ff<jb>
{
    private final int acq;
    private final int mTheme;
    private final Activity nS;
    private final String wG;
    
    public jg(final Activity ns, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final int acq, final String wg, final int mTheme) {
        super((Context)ns, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.nS = ns;
        this.acq = acq;
        this.wG = wg;
        this.mTheme = mTheme;
    }
    
    public static Bundle a(final int n, final String s, final String s2, final int n2) {
        final Bundle bundle = new Bundle();
        bundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", n);
        bundle.putString("androidPackageName", s);
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            bundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", (Parcelable)new Account(s2, "com.google"));
        }
        bundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", n2);
        return bundle;
    }
    
    private Bundle lX() {
        return a(this.acq, this.nS.getPackageName(), this.wG, this.mTheme);
    }
    
    @Override
    protected void a(final fm fm, final e e) throws RemoteException {
        fm.a(e, 4452000);
    }
    
    public void a(final FullWalletRequest fullWalletRequest, final int n) {
        final b b = new b(n);
        final Bundle lx = this.lX();
        try {
            this.eM().a(fullWalletRequest, lx, b);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException getting full wallet", (Throwable)ex);
            b.a(8, (FullWallet)null, Bundle.EMPTY);
        }
    }
    
    public void a(final MaskedWalletRequest maskedWalletRequest, final int n) {
        final Bundle lx = this.lX();
        final b b = new b(n);
        try {
            this.eM().a(maskedWalletRequest, lx, b);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException getting masked wallet", (Throwable)ex);
            b.a(8, (MaskedWallet)null, Bundle.EMPTY);
        }
    }
    
    public void a(final NotifyTransactionStatusRequest notifyTransactionStatusRequest) {
        final Bundle lx = this.lX();
        try {
            this.eM().a(notifyTransactionStatusRequest, lx);
        }
        catch (RemoteException ex) {}
    }
    
    protected jb aY(final IBinder binder) {
        return jb.a.aU(binder);
    }
    
    @Override
    protected String bg() {
        return "com.google.android.gms.wallet.service.BIND";
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }
    
    public void cD(final int n) {
        final Bundle lx = this.lX();
        final b b = new b(n);
        try {
            this.eM().a(lx, b);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", (Throwable)ex);
            b.a(8, false, Bundle.EMPTY);
        }
    }
    
    public void d(final String s, final String s2, final int n) {
        final Bundle lx = this.lX();
        final b b = new b(n);
        try {
            this.eM().a(s, s2, lx, b);
        }
        catch (RemoteException ex) {
            Log.e("WalletClientImpl", "RemoteException changing masked wallet", (Throwable)ex);
            b.a(8, (MaskedWallet)null, Bundle.EMPTY);
        }
    }
    
    private static class a extends je.a
    {
        public void a(final int n, final FullWallet fullWallet, final Bundle bundle) {
        }
        
        public void a(final int n, final MaskedWallet maskedWallet, final Bundle bundle) {
        }
        
        public void a(final int n, final boolean b, final Bundle bundle) {
        }
        
        public void a(final Status status, final ix ix, final Bundle bundle) {
        }
        
        public void f(final int n, final Bundle bundle) {
        }
    }
    
    final class b extends a
    {
        private final int CV;
        
        public b(final int cv) {
            this.CV = cv;
        }
        
        @Override
        public void a(final int n, final FullWallet fullWallet, final Bundle bundle) {
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
            }
            final ConnectionResult connectionResult = new ConnectionResult(n, pendingIntent);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(jg.this.nS, this.CV);
                    return;
                }
                catch (IntentSender$SendIntentException ex) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", (Throwable)ex);
                    return;
                }
            }
            final Intent intent = new Intent();
            int n2;
            if (connectionResult.isSuccess()) {
                n2 = -1;
                intent.putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", (Parcelable)fullWallet);
            }
            else {
                if (n == 408) {
                    n2 = 0;
                }
                else {
                    n2 = 1;
                }
                intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", n);
            }
            final PendingIntent pendingResult = jg.this.nS.createPendingResult(this.CV, intent, 1073741824);
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
        
        @Override
        public void a(final int n, final MaskedWallet maskedWallet, final Bundle bundle) {
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
            }
            final ConnectionResult connectionResult = new ConnectionResult(n, pendingIntent);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(jg.this.nS, this.CV);
                    return;
                }
                catch (IntentSender$SendIntentException ex) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", (Throwable)ex);
                    return;
                }
            }
            final Intent intent = new Intent();
            int n2;
            if (connectionResult.isSuccess()) {
                n2 = -1;
                intent.putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", (Parcelable)maskedWallet);
            }
            else {
                if (n == 408) {
                    n2 = 0;
                }
                else {
                    n2 = 1;
                }
                intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", n);
            }
            final PendingIntent pendingResult = jg.this.nS.createPendingResult(this.CV, intent, 1073741824);
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
        
        @Override
        public void a(final int n, final boolean b, final Bundle bundle) {
            final Intent intent = new Intent();
            intent.putExtra("com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED", b);
            final PendingIntent pendingResult = jg.this.nS.createPendingResult(this.CV, intent, 1073741824);
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
        
        @Override
        public void f(final int n, final Bundle bundle) {
            fq.b(bundle, "Bundle should not be null");
            final ConnectionResult connectionResult = new ConnectionResult(n, (PendingIntent)bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(jg.this.nS, this.CV);
                    return;
                }
                catch (IntentSender$SendIntentException ex) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", (Throwable)ex);
                    return;
                }
            }
            Log.e("WalletClientImpl", "Create Wallet Objects confirmation UI will not be shown connection result: " + connectionResult);
            final Intent intent = new Intent();
            intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413);
            final PendingIntent pendingResult = jg.this.nS.createPendingResult(this.CV, intent, 1073741824);
            if (pendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
                return;
            }
            try {
                pendingResult.send(1);
            }
            catch (PendingIntent$CanceledException ex2) {
                Log.w("WalletClientImpl", "Exception setting pending result", (Throwable)ex2);
            }
        }
    }
}
